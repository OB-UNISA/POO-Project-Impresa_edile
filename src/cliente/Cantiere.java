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
	 * Variabile di istanza della localit� del cantiere
	 */
	private String localit�;
	/**
	 * Variabile di istanza del valore del cantiere
	 */
	private long valore;

	/**
	 * Costruttore per istanziare un oggetto <code>Cantiere</code> con campi vuoti
	 */
	public Cantiere() {
		codice = 0;
		localit� = "";
		valore = 000L;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Cantiere</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato � <code>null</code>, verr�
	 * assegnata una stringa vuota, se la variabile primitiva <code>long</code> � <
	 * 0, verr� assegnato 0
	 * 
	 * @param codice   del cantiere
	 * @param localit� del cantiere
	 * @param valore   del cantiere
	 */
	public Cantiere(int codice, String localit�, long valore) {
		this.codice = codice;
		this.localit� = (localit� == null ? "" : localit�);
		this.valore = (valore < 0 ? 0 : valore);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi � il codice
	 * del cantiere
	 * 
	 * @return codice del cantiere
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi � la localit� del
	 * cantiere
	 * 
	 * @return localit� del cantiere
	 */
	public String getLocalit�() {
		return localit�;
	}

	/**
	 * Restituisce una variabile primitiva <code>long</code> in cui vi � il valore
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
	 * Assegna o sostituisce la localit� del cantiere, se l'oggetto
	 * <code>String</code> passato � <code>null</code>, verr� assegnata una stringa
	 * vuota
	 * 
	 * @param localit� del cantiere
	 */
	public void setLocalit�(String localit�) {
		this.localit� = (localit� == null ? "" : localit�);
	}

	/**
	 * Assegna o sostituisce il valore del cantiere, se la variabile primitiva
	 * <code>long</code> passata � < 0, verr� assegnato 0
	 * 
	 * @param valore del cantiere
	 */
	public void setValore(long valore) {
		this.valore = (valore < 0 ? 0 : valore);
	}

	public String toString() {
		return this.getClass().getName() + "[codice=" + codice + ",localit�=" + localit� + ",valore=" + valore + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Cantiere other = (Cantiere) otherObject;
		return codice == other.codice && localit�.equals(other.localit�) && valore == other.valore;
	}

	public Cantiere clone() {
		try {
			return (Cantiere) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
