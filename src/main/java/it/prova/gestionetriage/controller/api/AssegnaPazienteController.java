package it.prova.gestionetriage.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionetriage.dto.DottoreRequestDTO;
import it.prova.gestionetriage.dto.DottoreResponseDTO;
import it.prova.gestionetriage.exceptions.DottoreNotFoundException;
import it.prova.gestionetriage.exceptions.DottoreOccupatoException;
import it.prova.gestionetriage.exceptions.PazienteNotFoundException;
import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.service.DottoreService;
import it.prova.gestionetriage.service.PazienteService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/assegnapaziente", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AssegnaPazienteController {

	@Autowired
	private PazienteService pazienteService;

	@Autowired
	private DottoreService dottoreService;

	@Autowired
	private WebClient webClient;

	@PostMapping
	public void assegnaPaziente(@RequestParam String codiceFiscale, @RequestParam String codiceDipendente) {

		Paziente paziente = pazienteService.findByCodiceFiscale(codiceFiscale);
		Dottore dottore = dottoreService.findByCodiceDipendente(codiceDipendente);

		if (paziente == null)
			throw new PazienteNotFoundException("Il paziente non è stato trovato");

		if (dottore == null)
			throw new DottoreNotFoundException("Il dottore non è stato trovato");

		ResponseEntity<DottoreResponseDTO> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/verifica/{codiceDipendente}").build(codiceDipendente)).retrieve()
				.toEntity(DottoreResponseDTO.class).block();

		DottoreResponseDTO rispostaDottore = response.getBody();

		if (!rispostaDottore.isInServizio() || rispostaDottore.isInVisita())
			throw new DottoreOccupatoException("Dottore non disponibile");

		ResponseEntity<DottoreResponseDTO> imposta = webClient.post().uri("/impostaInVisita")
				.body(Mono.just(new DottoreRequestDTO(dottore.getCodiceDipendente())), DottoreRequestDTO.class)
				.retrieve().toEntity(DottoreResponseDTO.class).block();

		if (imposta.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("Errore!!");
		
		paziente.setDottore(dottore);
		dottore.setPazienteAttualmenteInVisita(paziente);
		pazienteService.save(paziente);
		dottoreService.save(dottore);
		
		return;
	}

}
