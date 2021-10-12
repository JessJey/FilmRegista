package it.prova.model;

public class Film {

	private Long id;
	private String titolo;
	private String genere;
	private int durata;
	private Regista regista;

	public Film() {
	}

	public Film( String titolo, String genere, int durata, Regista regista) {
		super();
		this.titolo = titolo;
		this.genere = genere;
		this.durata = durata;
		this.regista = regista;
	}

	public Film(String titolo, String genere, int durata) {
		super();
		this.titolo = titolo;
		this.genere = genere;
		this.durata = durata;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public Regista getRegista() {
		return regista;
	}

	public void setRegista(Regista regista) {
		this.regista = regista;
	}

	public String toString() {
		return "Film [id=" + id + ", titolo=" + titolo + ", genere=" + genere + ", durata=" + durata + ", regista="
				+ regista + "]";
	}

}
