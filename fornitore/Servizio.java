package fornitore;

import java.io.Serializable;

import interfacce.Clone;

/**
 * Classe che definisce il servzio
 */
public class Servizio implements Clone<Servizio>, Serializable {

	private static final long serialVersionUID = -5859739524998846597L;
	/**
	 * Variabile di istanza del nome del servizio
	 */
	private String nome;
	/**
	 * Variabile di istanza del prezzo del servizio
	 */
	private double prezzo;
	/**
	 * Variabile di istanza del numero del servizio clienti del servizio
	 */
	private long numeroServizioClienti;
	/**
	 * Variabile di istanza della descrizione del servizio
	 */
	private String descrizione;

	/**
	 * Costruttore per istanziare un oggetto <code>Servizio</code> con campi vuoti
	 */
	public Servizio() {
		nome = "";
		prezzo = 0.0;
		numeroServizioClienti = 0000000000L;
		descrizione = "";
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Servizio</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota, se la variabile primitiva <code>double</code> è
	 * < 0.0, verrà assegnato 0.0
	 * 
	 * @param nome                  del servizio
	 * @param prezzo                del servizio
	 * @param numeroServizioClienti del servizio
	 * @param descrizione           del servizio
	 */
	public Servizio(String nome, double prezzo, long numeroServizioClienti, String descrizione) {
		this.nome = (nome == null ? "" : nome);
		this.prezzo = (prezzo < 0.0 ? 0.0 : prezzo);
		this.numeroServizioClienti = numeroServizioClienti;
		this.descrizione = (descrizione == null ? "" : descrizione);
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome del servizio
	 * 
	 * @return nome del servizio
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce una variabile primitiva <code>double</code> in cui vi è il prezzo
	 * del servizio
	 * 
	 * @return prezzo del servizio
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Restituisce una variabile primitiva <code>long</code> in cui vi è il numero del
	 * servizio clienti del servizio
	 * 
	 * @return numero servizio clienti
	 */
	public long getNumeroServizioClienti() {
		return numeroServizioClienti;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la descrizione del
	 * servizio
	 * 
	 * @return descrizione del servizio
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Assegna o sostituisce il nome del servizio, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param nome del servizio
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce il prezzo del servizio, se la variabile primitiva
	 * <code>double</code> passata è < 0.0, verrà assegnato 0.0
	 * 
	 * @param prezzo del servizio
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = (prezzo < 0.0 ? 0.0 : prezzo);
	}

	/**
	 * Assegna o sostituisce il numero del servizio clienti del servizio
	 * 
	 * @param numeroServizioClienti del servizio
	 */
	public void setNumeroServizioClienti(long numeroServizioClienti) {
		this.numeroServizioClienti = numeroServizioClienti;
	}

	/**
	 * Assegna o sostituisce la descrizione del servizio, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param descrizione del servizio
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = (descrizione == null ? "" : descrizione);
	}

	public String toString() {
		return this.getClass().getName() + "[nome=" + nome + ",prezzo=" + prezzo + ",numeroServizioClienti="
				+ numeroServizioClienti + ",descrizione=" + descrizione + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Servizio other = (Servizio) otherObject;
		return nome.equals(other.nome) && prezzo == other.prezzo && numeroServizioClienti == other.numeroServizioClienti
				&& descrizione.equals(other.descrizione);
	}

	public Servizio clone() {
		try {
			return (Servizio) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
