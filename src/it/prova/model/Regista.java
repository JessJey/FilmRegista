package it.prova.model;

public class Regista {

	private Long id;
	private String nome;
	private String cognome;
	private int nuomeroOscarVinti;

	public Regista() {
	}

	public Regista( String nome, String cognome, int nuomeroOscarVinti) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.nuomeroOscarVinti = nuomeroOscarVinti;
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

	public int getNuomeroOscarVinti() {
		return nuomeroOscarVinti;
	}

	public void setNuomeroOscarVinti(int nuomeroOscarVinti) {
		this.nuomeroOscarVinti = nuomeroOscarVinti;
	}

	public String toString() {
		return "Regista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", nuomeroOscarVinti="
				+ nuomeroOscarVinti + "]";
	}

}
