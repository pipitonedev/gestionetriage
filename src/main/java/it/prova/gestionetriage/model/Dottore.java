package it.prova.gestionetriage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dottore")
public class Dottore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String codiceDipendente;
	
	@OneToOne
	private Paziente pazienteAttualmenteInVisita;

	public Dottore() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dottore(Long id, String nome, String cognome, String codiceDipendente, Paziente pazienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	public Dottore(Long id, String nome, String cognome, String codiceDipendente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}

	public Dottore(String nome, String cognome, String codiceDipendente, Paziente pazienteAttualmenteInVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	public Dottore(String nome, String cognome, String codiceDipendente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
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

	public String getCodiceDipendente() {
		return codiceDipendente;
	}

	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public Paziente getPazienteAttualmenteInVisita() {
		return pazienteAttualmenteInVisita;
	}

	public void setPazienteAttualmenteInVisita(Paziente pazienteAttualmenteInVisita) {
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

}
