package entelocale;

import java.io.Serializable;

/**
 * Classe che definisce il permesso
 */
public final class Permesso implements Serializable {

	private static final long serialVersionUID = 2280261201074823016L;
	/**
	 * Variabile di istanza del codice del permesso
	 */
	private final int codice;
	/**
	 * Variabile di istanza dello stato del permesso
	 */
	private final String stato;
	/**
	 * Costante per definire lo stato del permesso
	 */
	public static final String LAVORAZIONE = "LAVORAZIONE", NEGATO = "NEGATO", CONCESSO = "CONCESSO";

	/**
	 * Costruttore per istanziare un oggetto <code>Permesso</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code> o diverso
	 * dalle costanti, verrà assegnata la costante LAVORAZIONE
	 * 
	 * @param codice
	 * @param stato
	 */
	public Permesso(int codice, String stato) {
		this.codice = codice;
		this.stato = (stato.equals(LAVORAZIONE) || stato.equals(NEGATO) || stato.equals(CONCESSO) ? stato
				: LAVORAZIONE);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il codice
	 * del permesso
	 * 
	 * @return codice del permesso
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è lo stato del permesso
	 * 
	 * @return stato del permesso
	 */
	public String getStato() {
		return stato;
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Permesso other = (Permesso) otherObject;
		return codice == other.codice && stato.equals(other.stato);
	}

	public String toString() {
		return this.getClass().getName() + "[codice=" + codice + ",stato=" + stato + "]";
	}
}
