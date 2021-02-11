package cliente;

import java.io.Serializable;

import interfacce.Clone;

/**
 * Classe che definisce il cliente
 */
public class Cliente implements Clone<Cliente>, Serializable {

	private static final long serialVersionUID = -2544973208123452297L;
	/**
	 * Variabile di istanza del cfSnn del cliente
	 */
	private String cfSnn;
	/**
	 * Variabile di istanza del nome del cliente
	 */
	private String nome;
	/**
	 * Variabile di istanza del cognome del cliente
	 */
	private String cognome;
	/**
	 * Variabile di istanza del numero di telefono del cliente
	 */
	private long numeroTelefono;
	/**
	 * Variabile di istanza del cantiere del cliente
	 */
	private Cantiere cantiere;

	/**
	 * Costruttore per istanziare un oggetto <code>Cliente</code> con campi vuoti
	 */
	public Cliente() {
		cfSnn = "";
		nome = "";
		cognome = "";
		numeroTelefono = 0000000000L;
		cantiere = new Cantiere();
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Cliente</code> con campi non
	 * vuoti e un cantiere vuoto, se l'oggetto <code>String</code> passato è
	 * <code>null</code>, verrà assegnata una stringa vuota
	 * 
	 * @param cfSnn          del cliente
	 * @param nome           del cliente
	 * @param cognome        del cliente
	 * @param numeroTelefono del cliente
	 */
	public Cliente(String cfSnn, String nome, String cognome, long numeroTelefono) {
		this.cfSnn = (cfSnn == null ? "" : cfSnn);
		this.nome = (nome == null ? "" : nome);
		this.cognome = (cognome == null ? "" : cognome);
		this.numeroTelefono = numeroTelefono;
		this.cantiere = new Cantiere();
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Cliente</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota, se l'oggetto <code>Cantiere</code> passato è
	 * <code>null</code>, verrà assegnato un cantiere vuoto
	 * 
	 * @param cfSnn          del cliente
	 * @param nome           del cliente
	 * @param cognome        del cliente
	 * @param numeroTelefono del cliente
	 * @param cantiere       del cliente
	 */
	public Cliente(String cfSnn, String nome, String cognome, long numeroTelefono, Cantiere cantiere) {
		this.cfSnn = (cfSnn == null ? "" : cfSnn);
		this.nome = (nome == null ? "" : nome);
		this.cognome = (cognome == null ? "" : cognome);
		this.numeroTelefono = numeroTelefono;
		this.cantiere = (cantiere == null ? new Cantiere() : cantiere.clone());
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il cf/ssn del cliente
	 * 
	 * @return cf/ssn del cliente
	 */
	public String getCfSnn() {
		return cfSnn;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome del cliente
	 * 
	 * @return nome del cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il cognome del cliente
	 * 
	 * @return cognome del cliente
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Restituisce una variabile primitiva <code>long</code> in cui vi è il numero
	 * di telefono del cliente
	 * 
	 * @return numeroTelefono del cliente
	 */
	public long getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * Restituisce un oggetto <code>Cantiere</code> in cui vi è il cantiere del
	 * cliente
	 * 
	 * @return cantiere del cliente
	 */
	public Cantiere getCantiere() {
		return cantiere.clone();
	}

	/**
	 * Assegna o sostituisce il cfSnn del cliente, se l'oggetto <code>String</code>
	 * passato è <code>null</code>, verrà assegnata una stringa vuota
	 * 
	 * @param cfSnn del cliente
	 */
	public void setCfSnn(String cfSnn) {
		this.cfSnn = (cfSnn == null ? "" : cfSnn);
	}

	/**
	 * Assegna o sostituisce il nome del cliente, se l'oggetto <code>String</code>
	 * passato è <code>null</code>, verrà assegnata una stringa vuota
	 * 
	 * @param nome del cliente
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce il cognome del cliente, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param cognome del cliente
	 */
	public void setCognome(String cognome) {
		this.cognome = (cognome == null ? "" : cognome);
	}

	/**
	 * Assegna o sostituisce il numero di telefono del cliente
	 * 
	 * @param numeroTelefono del cliente
	 */
	public void setNumeroTelefono(long numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * Assegna o sostituisce il cantiere del cliente, se l'oggetto
	 * <code>Cantiere</code> passato è <code>null</code>, verrà assegnato un
	 * cantiere vuoto
	 * 
	 * @param cantiere del cliente
	 */
	public void setCantiere(Cantiere cantiere) {
		this.cantiere = (cantiere == null ? new Cantiere() : cantiere.clone());
	}

	/**
	 * Controlla se il cantiere del cliente è vuoto
	 * 
	 * @return <code>true</code> se è vuoto, <code>false</code> altrimenti
	 */
	public boolean controllaCantiereVuoto() {
		return cantiere.equals(new Cantiere());
	}

	public String toString() {
		return this.getClass().getName() + "[cfSnn=" + cfSnn + ",nome=" + nome + ",cognome=" + cognome
				+ ",numeroTelefono=" + numeroTelefono + ",cantiere=" + cantiere + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Cliente other = (Cliente) otherObject;
		return cfSnn.equals(other.cfSnn) && nome.equals(other.nome) && cognome.equals(other.cognome)
				&& numeroTelefono == other.numeroTelefono && cantiere.equals(other.cantiere);
	}

	public Cliente clone() {
		try {
			Cliente cloned = (Cliente) super.clone();
			cloned.cantiere = cantiere.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
