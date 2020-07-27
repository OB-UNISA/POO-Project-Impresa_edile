package fornitore;

import java.io.Serializable;

import interfacce.Clone;

/**
 * Classe che definisce il prodotto
 */
public class Prodotto implements Clone<Prodotto>, Serializable{

	private static final long serialVersionUID = 7589336867391329196L;
	/**
	 * Variabile di istanza del codice del prodotto
	 */
	private int codice;
	/**
	 * Variabile di istanza del nome del prodotto
	 */
	private String nome;
	/**
	 * Variabile di istanza del prezzo del prodotto
	 */
	private double prezzo;
	/**
	 * Variabile di istanza della descrizione del prodotto
	 */
	private String descrizione;

	/**
	 * Costruttore per istanziare un oggetto <code>Prodotto</code> con campi vuoti
	 */
	public Prodotto() {
		codice = 0;
		nome = "";
		prezzo = 0.0;
		descrizione = "";
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Prodotto</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota, se la variabile primitiva <code>double</code> è
	 * < 0.0, verrà assegnato 0.0
	 * 
	 * @param codice      del prodotto
	 * @param nome        del prodotto
	 * @param prezzo      del prodotto
	 * @param descrizione del prodotto
	 */
	public Prodotto(int codice, String nome, double prezzo, String descrizione) {
		this.codice = codice;
		this.nome = (nome == null ? "" : nome);
		this.prezzo = (prezzo < 0.0 ? 0 : prezzo);
		this.descrizione = (descrizione == null ? "" : descrizione);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il codice
	 * del prodotto
	 * 
	 * @return codice del prodotto
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome del prodotto
	 * 
	 * @return nome del prodotto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce una variabile primitiva <code>double</code> in cui vi è il prezzo
	 * del prodotto
	 * 
	 * @return prezzo del prodotto
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la descrizione del
	 * prodotto
	 * 
	 * @return descrizione del prodotto
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Assegna o sostituisce il codice del prodotto
	 * 
	 * @param codice del prodotto
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * Assegna o sostituisce il nome del prodotto, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param nome del prodotto
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce il prezzo del prodotto, se la variabile primitiva
	 * <code>double</code> passata è < 0.0, verrà assegnato 0.0
	 * 
	 * @param prezzo del prodotto
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = (prezzo < 0.0 ? 0 : prezzo);
	}

	/**
	 * Assegna o sostituisce la descrizione del prodotto, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param descrizione del prodotto
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = (descrizione == null ? "" : descrizione);
	}

	public String toString() {
		return this.getClass().getName() + "[codice=" + codice + ",nome=" + nome + ",prezzo=" + prezzo + ",descrizione="
				+ descrizione + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Prodotto other = (Prodotto) otherObject;
		return codice == other.codice && nome.equals(other.nome) && prezzo == other.prezzo
				&& descrizione.equals(other.descrizione);
	}

	public Prodotto clone() {
		try {
			return (Prodotto) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
