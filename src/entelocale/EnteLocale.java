package entelocale;

import java.io.Serializable;

import impresa.CantiereImpresa;
import interfacce.Clone;

/**
 * Classe che definisce l'ente locale
 */
public class EnteLocale implements Clone<EnteLocale>, Serializable {

	private static final long serialVersionUID = 6591784274694318384L;
	/**
	 * Variabile di istanza del codice del'ente locale
	 */
	private int codice;
	/**
	 * Variabile di istanza del codice del nome dell'ente locale
	 */
	private String nome;

	/**
	 * Costruttore per istanziare un oggetto <code>EnteLocale</code> con campi vuoti
	 */
	public EnteLocale() {
		codice = 0;
		nome = "";
	}

	/**
	 * Costruttore per istanziare un oggetto <code>EnteLocale</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota
	 * 
	 * @param codice dell'ente locale
	 * @param nome   dell'ente locale
	 */
	public EnteLocale(int codice, String nome) {
		this.codice = codice;
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il codice
	 * dell'ente locale
	 * 
	 * @return codice dell'ente locale
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome dell'ente
	 * locale
	 * 
	 * @return nome dell'ente locale
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Assegna o sostituisce codice dell'ente locale
	 * 
	 * @param codice dell'ente locale
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * Assegna o sostituisce il nome dell'ente locale, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param nome dell'ente locale
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna lo stato al permesso del cantiere dell'impresa. Il codice del
	 * permesso sarà uguale a quello del cantiere dell'impresa
	 * 
	 * @param cantiereImpresa del cantiere dell'impresa
	 * @param stato           del permesso
	 * @return permesso del cantiere
	 */
	public Permesso setPermessoCantiere(CantiereImpresa cantiereImpresa, String stato) {
		return new Permesso(cantiereImpresa.getCodice(), stato);
	}

	public String toString() {
		return this.getClass().getName() + "[codice=" + codice + ",nome=" + nome + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		EnteLocale other = (EnteLocale) otherObject;
		return codice == other.codice && nome.equals(other.nome);
	}

	public EnteLocale clone() {
		try {
			return (EnteLocale) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
