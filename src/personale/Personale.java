package personale;

import java.io.Serializable;

import interfacce.Clone;

/**
 * Classe che definisce il personale
 */
public class Personale implements Clone<Personale>, Serializable {

	private static final long serialVersionUID = 2653439766172607764L;
	/**
	 * Variabile di istanza del cfSnn del personale
	 */
	private String cfSnn;
	/**
	 * Variabile di istanza del nome del personale
	 */
	private String nome;
	/**
	 * Variabile di istanza del cognome del personale
	 */
	private String cognome;
	/**
	 * Variabile di istanza della professione del personale
	 */
	private String professione;

	/**
	 * Costruttore per istanziare un oggetto <code>Personale</code> con campi vuoti
	 */
	public Personale() {
		cfSnn = "";
		nome = "";
		cognome = "";
		professione = "";
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Personale</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota
	 * 
	 * @param cfSnn       del personale
	 * @param nome        del personale
	 * @param cognome     del personale
	 * @param professione del personale
	 */
	public Personale(String cfSnn, String nome, String cognome, String professione) {
		this.cfSnn = (cfSnn == null ? "" : cfSnn);
		this.nome = (nome == null ? "" : nome);
		this.cognome = (cognome == null ? "" : cognome);
		this.professione = (professione == null ? "" : professione);
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il cf/ssn del
	 * personale
	 * 
	 * @return cf/ssn del personale
	 */
	public String getCfSnn() {
		return cfSnn;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome del personale
	 * 
	 * @return nome del personale
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il cognome del
	 * personale
	 * 
	 * @return cognome del personale
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la professione del
	 * personale
	 * 
	 * @return professione del personale
	 */
	public String getProfessione() {
		return professione;
	}

	/**
	 * Assegna o sostituisce il cf/ssn del personale, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà inserita una stringa
	 * vuota
	 * 
	 * @param cfSnn del personale
	 */
	public void setCfSnn(String cfSnn) {
		this.cfSnn = (cfSnn == null ? "" : cfSnn);
	}

	/**
	 * Assegna o sostituisce il nome del personale, se l'oggetto <code>String</code>
	 * passato è <code>null</code>, verrà inserita una stringa vuota
	 * 
	 * @param nome del personale
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce il cognome del personale, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà inserita una stringa
	 * vuota
	 * 
	 * @param cognome del personale
	 */
	public void setCognome(String cognome) {
		this.cognome = (cognome == null ? "" : cognome);
	}

	/**
	 * Assegna o sostituisce la professione del personale, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà inserita una stringa
	 * vuota
	 * 
	 * @param professione del personale
	 */
	public void setProfessione(String professione) {
		this.professione = (professione == null ? "" : professione);
	}

	public String toString() {
		return this.getClass().getName() + "[cfSnn=" + cfSnn + ",nome=" + nome + ",cognome=" + cognome + ",professione="
				+ professione + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Personale other = (Personale) otherObject;
		return cfSnn.equals(other.cfSnn) && nome.equals(other.nome) && cognome.equals(other.cognome)
				&& professione.equals(other.professione);
	}

	public Personale clone() {
		try {
			return (Personale) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
