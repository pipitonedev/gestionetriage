package it.prova.gestionetriage.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private Date dataRegistrazione;
	private StatoPaziente statoPaziente;
	private DottoreDTO dottore;

	public PazienteDTO() {
	}

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, Date dataRegistrazione,
			StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public DottoreDTO getDottore() {
		return dottore;
	}

	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(), pazienteModel.getCognome(),
				pazienteModel.getCodiceFiscale(), pazienteModel.getDataRegistrazione(), pazienteModel.getStato(),
				DottoreDTO.buildDottoreDTOFromModel(pazienteModel.getDottore()));
		return result;
	}

	public Paziente buildPazienteModel() {
		return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione,
				this.statoPaziente, this.dottore.buildDottoreModel());
	}

}
