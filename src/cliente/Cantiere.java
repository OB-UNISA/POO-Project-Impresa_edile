package cliente;

import java.io.Serializable;

/**
 * Classe che definisce il cantiere
 */
public class Cantiere implements Cloneable, Serializable {

	private static final long serialVersionUID = 8878684692201344394L;
	/**
	 * Variabile di istanza del codice del cantiere
	 */
	private int codice;
	/**
	 * Variabile di istanza della località del cantiere
	 */
	private String località;
	/**
	 * Variabile di istanza del valore del cantiere
	 */
	private long valore;

	/**
	 * Costruttore per istanziare un oggetto <code>Cantiere</code> con campi vuoti
	 */
	public Cantiere() {
		codice = 0;
		località = "";
		valore = 000L;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Cantiere</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota, se la variabile primitiva <code>long</code> è <
	 * 0, verrà assegnato 0
	 * 
	 * @param codice   del cantiere
	 * @param località del cantiere
	 * @param valore   del cantiere
	 */
	public Cantiere(int codice, String località, long valore) {
		this.codice = codice;
		this.località = (località == null ? "" : località);
		this.valore = (valore < 0 ? 0 : valore);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il codice
	 * del cantiere
	 * 
	 * @return codice del cantiere
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la località del
	 * cantiere
	 * 
	 * @return località del cantiere
	 */
	public String getLocalità() {
		return località;
	}

	/**
	 * Restituisce una variabile primitiva <code>long</code> in cui vi è il valore
	 * del cantiere
	 * 
	 * @return valore del cantiere
	 */
	public long getValore() {
		return valore;
	}

	/**
	 * Assegna o sostituisce il codice del cantiere
	 * 
	 * @param codice del cantiere
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * Assegna o sostituisce la località del cantiere, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param località del cantiere
	 */
	public void setLocalità(String località) {
		this.località = (località == null ? "" : località);
	}

	/**
	 * Assegna o sostituisce il valore del cantiere, se la variabile primitiva
	 * <code>long</code> passata è < 0, verrà assegnato 0
	 * 
	 * @param valore del cantiere
	 */
	public void setValore(long valore) {
		this.valore = (valore < 0 ? 0 : valore);
	}

	public String toString() {
		return this.getClass().getName() + "[codice=" + codice + ",località=" + località + ",valore=" + valore + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Cantiere other = (Cantiere) otherObject;
		return codice == other.codice && località.equals(other.località) && valore == other.valore;
	}

	public Cantiere clone() {
		try {
			return (Cantiere) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
