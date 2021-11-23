package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.exceptions.PazienteNotFoundException;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService {

	private PazienteRepository pazienteRepository;

	@Autowired
	@Override
	public List<Paziente> listAll() {
		return (List<Paziente>) pazienteRepository.findAll();
	}

	@Override
	public Page<Paziente> searchAndPaginate(Paziente pazienteExample, Integer pageNo, Integer pageSize, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paziente get(Long idInput) {
		return pazienteRepository.findById(idInput)
				.orElseThrow(() -> new PazienteNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public Paziente save(Paziente input) {
		return pazienteRepository.save(input);
	}

	@Override
	public void delete(Paziente input) {
		pazienteRepository.delete(input);

	}

}
