package personale;

import exceptions.NumeroMassimoCantieriException;

/**
 * Classe che definisce il dirigente, eredita la classe <code>dirigente</code>
 */
public class Dirigente extends Personale {

	private static final long serialVersionUID = -1906140671825107041L;
	/**
	 * Variabile di istanta del numero massimo di cantieri di un dirigente
	 */
	private int numeroMassimoCantieri;
	private int numeroAttualeCantieri;

	/**
	 * Costruttore per istanziare un oggetto <code>Dirigente</code> con campi vuoti
	 */
	public Dirigente() {
		super();
		setProfessione("Dirigente");
		numeroMassimoCantieri = 1;
		numeroAttualeCantieri = 0;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Dirigente</code> con campi non
	 * vuoti e numero attuale cantieri a 0, se l'oggetto <code>String</code> passato
	 * è <code>null</code>, verrà assegnata una stringa vuota, se la variabile
	 * primitiva <code>int</code> è < 0, verrà assegnato 0
	 * 
	 * @param cfSnn                 del dirigente
	 * @param nome                  del dirigente
	 * @param cognome               del dirigente
	 * @param numeroMassimoCantieri del dirigente
	 */
	public Dirigente(String cfSnn, String nome, String cognome, int numeroMassimoCantieri) {
		super(cfSnn, nome, cognome, "Dirigente");
		this.numeroMassimoCantieri = (numeroMassimoCantieri < 0 ? 0 : numeroMassimoCantieri);
		this.numeroAttualeCantieri = 0;

	}

	/**
	 * Costruttore per istanziare un oggetto <code>Dirigente</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota, se la variabile primitiva <code>int</code> è <
	 * 0, verrà assegnato 0. Lancia una NumeroMassimoCantieriException se il
	 * parametro numeroAttualeCantieri > numeroMassimoCantieri
	 * 
	 * @param cfSnn                 del dirigente
	 * @param nome                  del dirigente
	 * @param cognome               del dirigente
	 * @param numeroMassimoCantieri del dirigente
	 * @param numeroAttualeCantieri del dirigente
	 * @throws NumeroMassimoCantieriException
	 */
	public Dirigente(String cfSnn, String nome, String cognome, int numeroMassimoCantieri, int numeroAttualeCantieri) {
		super(cfSnn, nome, cognome, "Dirigente");
		this.numeroMassimoCantieri = (numeroMassimoCantieri < 0 ? 0 : numeroMassimoCantieri);
		this.numeroAttualeCantieri = (numeroAttualeCantieri < 0 ? 0 : numeroAttualeCantieri);
		if (numeroAttualeCantieri > numeroMassimoCantieri)
			throw new NumeroMassimoCantieriException("Numero massimo cantieri superato");
		else
			this.numeroAttualeCantieri = (numeroAttualeCantieri < 0 ? 0 : numeroAttualeCantieri);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il numero
	 * massimo dei cantieri del dirigente
	 * 
	 * @return numero massimo cantieri del dirigente
	 */
	public int getNumeroMassimoCantieri() {
		return numeroMassimoCantieri;
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il numero
	 * attuale dei cantieri del dirigente
	 * 
	 * @return numero attuale cantieri del dirigente
	 */
	public int getNumeroAttualeCantieri() {
		return numeroAttualeCantieri;
	}

	/**
	 * Assegna o sostituisce il numero massimo dei cantieri del dirigente, se la
	 * variabile primitiva <code>int</code> passata è < 0, verrà assegnato 0
	 * 
	 * @param numeroMassimoCantieri del dirigente
	 */
	public void setNumeroMassimoCantieri(int numeroMassimoCantieri) {
		this.numeroMassimoCantieri = (numeroMassimoCantieri < 0 ? 0 : numeroMassimoCantieri);
	}

	/**
	 * Assegna o sostituisce il numero attuale dei cantieri del dirigente, se la
	 * variabile primitiva <code>int</code> passata è < 0, verrà assegnato 0. Lancia
	 * una NumeroMassimoCantieriException se il parametro numeroAttualeCantieri >
	 * numeroMassimoCantieri
	 * 
	 * @param numeroAttualeCantieri del dirigente
	 * @throws NumeroMassimoCantieriException
	 */
	public void setNumeroAttualeCantieri(int numeroAttualeCantieri) {
		if (numeroAttualeCantieri > numeroMassimoCantieri)
			throw new NumeroMassimoCantieriException("Numero massimo cantieri superato");
		else
			this.numeroAttualeCantieri = (numeroAttualeCantieri < 0 ? 0 : numeroAttualeCantieri);
	}

	public String toString() {
		return super.toString() + "[numeroMassimoCantieri=" + numeroMassimoCantieri + ",numeroAttualeCantieri="
				+ numeroAttualeCantieri + "]";
	}

	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject))
			return false;
		Dirigente other = (Dirigente) otherObject;
		return numeroMassimoCantieri == other.numeroMassimoCantieri
				&& numeroAttualeCantieri == other.numeroAttualeCantieri;
	}

	public Dirigente clone() {
		return (Dirigente) super.clone();
	}

}
