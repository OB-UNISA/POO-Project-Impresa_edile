package fornitore;

import java.io.Serializable;
import java.util.ArrayList;

import interfacce.Clone;
import utilità.Utilità;

/**
 * Classe che definisce il fornitore
 */
public class Fornitore implements Clone<Fornitore>, Serializable {

	private static final long serialVersionUID = 111921157549197835L;
	/**
	 * Variabile di istanza della partitaIVA del fornitore
	 */
	private String partitaIVA;
	/**
	 * Variabile di istanza del nome del fornitore
	 */
	private String nome;
	/**
	 * Variabile di istanza del capitale del fornitore
	 */
	private long capitale;
	/**
	 * Variabile di istanza dei prodotti disponibili del fornitore
	 */
	private ArrayList<Prodotto> prodottiDisponibili;
	/**
	 * Variabile di istanza dei servizi disponibili del fornitore
	 */
	private ArrayList<Servizio> serviziDisponibili;

	/**
	 * Costruttore per istanziare un oggetto <code>Fornitore</code> con campi vuoti
	 */
	public Fornitore() {
		partitaIVA = "";
		nome = "";
		capitale = 0L;
		prodottiDisponibili = new ArrayList<>();
		serviziDisponibili = new ArrayList<>();
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Fornitore</code> con campi non
	 * vuoti e la lista dei prodotti e quella dei servizi vuota, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param partitaIVA del fornitore
	 * @param nome       del fornitore
	 * @param capitale   del fornitore
	 */
	public Fornitore(String partitaIVA, String nome, long capitale) {
		this.partitaIVA = (partitaIVA == null ? "" : partitaIVA);
		this.nome = (nome == null ? "" : nome);
		this.capitale = capitale;
		prodottiDisponibili = new ArrayList<>();
		serviziDisponibili = new ArrayList<>();
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Fornitore</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota
	 * 
	 * @param partitaIVA          del fornitore
	 * @param nome                del fornitore
	 * @param capitale            del fornitore
	 * @param prodottiDisponibili del fornitore
	 * @param serviziDisponibili  del fornitore
	 */
	public Fornitore(String partitaIVA, String nome, long capitale, ArrayList<Prodotto> prodottiDisponibili,
			ArrayList<Servizio> serviziDisponibili) {
		this.partitaIVA = (partitaIVA == null ? "" : partitaIVA);
		this.nome = (nome == null ? "" : nome);
		this.capitale = capitale;
		this.prodottiDisponibili = Utilità.cloneArray(prodottiDisponibili);
		this.serviziDisponibili = Utilità.cloneArray(serviziDisponibili);
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la partitaIVA del
	 * fornitore
	 * 
	 * @return partitaIVA del fornitore
	 */
	public String getPartitaIVA() {
		return partitaIVA;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome del fornitore
	 * 
	 * @return nome del fornitore
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce una variabile primitiva <code>int</code> in cui vi è il capitale
	 * del fornitore
	 * 
	 * @return capitale del fornitore
	 */
	public long getCapitale() {
		return capitale;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Prodotto&gt;</code> in cui vi è la
	 * lista dei prodotti disponibili del fornitore
	 * 
	 * @return prodottiDisponibili del fornitore
	 */
	public ArrayList<Prodotto> getProdottiDisponibili() {
		return Utilità.cloneArray(prodottiDisponibili);
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Servizio&gt;</code> in cui vi è la
	 * lista dei servizi disponibili del fornitore
	 * 
	 * @return serviziDisponibili del fornitore
	 */
	public ArrayList<Servizio> getServiziDisponibili() {
		return Utilità.cloneArray(serviziDisponibili);
	}

	/**
	 * Assegna o sostituisce la partitaIVA del fornitore, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param partitaIVA del fornitore
	 */
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = (partitaIVA == null ? "" : partitaIVA);
	}

	/**
	 * Assegna o sostituisce il nome del fornitore, se l'oggetto <code>String</code>
	 * passato è <code>null</code>, verrà assegnata una stringa vuota
	 * 
	 * @param nome del fornitore
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce il capitale del fornitore
	 * 
	 * @param capitale del fornitore
	 */
	public void setCapitale(long capitale) {
		this.capitale = capitale;
	}

	/**
	 * Assegna o sostituisce la lista dei prodotti disponibili del fornitore, se
	 * l'array passato è <code>null</code>, verrà assegnato un array vuoto
	 * 
	 * @param prodottiDisponibili del fornitore
	 */
	public void setProdottiDisponibili(ArrayList<Prodotto> prodottiDisponibili) {
		this.prodottiDisponibili = Utilità.cloneArray(prodottiDisponibili);
	}

	/**
	 * Assegna o sostituisce la lista dei servizi disponibili del fornitore, se
	 * l'array passato è <code>null</code>, verrà assegnato un array vuoto
	 * 
	 * @param serviziDisponibili del fornitore
	 */
	public void setServiziDisponibili(ArrayList<Servizio> serviziDisponibili) {
		this.serviziDisponibili = Utilità.cloneArray(serviziDisponibili);
	}

	/**
	 * Aggiunge alla lista dei prodotti o servizi un prodotto o servizio
	 * 
	 * @param <T>               un oggetto che ha implementato l'interfaccia
	 *                          <code>Clone</code>
	 * @param prodottoOservizio da aggiungere
	 */
	public <T extends Clone<T>> void addDisponibile(T prodottoOservizio) {
		if (prodottoOservizio != null) {
			if (prodottoOservizio.getClass() == Prodotto.class)
				prodottiDisponibili.add((Prodotto) prodottoOservizio.clone());
			else if (prodottoOservizio.getClass() == Servizio.class)
				serviziDisponibili.add((Servizio) prodottoOservizio.clone());
		}
	}

	/**
	 * Rimuove lista dei prodotti o servizi un prodotto o servizio e lo restituisce,
	 * restituisce null se il prodotto o servizio non è stato trovato
	 * 
	 * @param <T>               un oggetto che ha implementato l'interfaccia
	 *                          <code>Clone</code>
	 * @param prodottoOservizio da rimuovere
	 * @return prodotto o servizio rimosso, null se il prodotto non è stato trovato
	 */
	public <T extends Clone<T>> T removeDisponibile(T prodottoOservizio) {
		if (prodottoOservizio != null) {
			if (prodottoOservizio.getClass() == Prodotto.class) {
				int index = prodottiDisponibili.indexOf(prodottoOservizio);
				if (index != -1) {
					T reference = (T) prodottiDisponibili.get(index);
					prodottiDisponibili.remove(index);
					return reference;
				} else
					return null;
			}
			if (prodottoOservizio.getClass() == Servizio.class) {
				int index = serviziDisponibili.indexOf(prodottoOservizio);
				if (index != -1) {
					T reference = (T) serviziDisponibili.get(index);
					serviziDisponibili.remove(index);
					return reference;
				} else
					return null;
			}
		}
		return null;
	}

	public String toString() {
		return this.getClass().getName() + "[partitaIVA=" + partitaIVA + ",nome=" + nome + ",capitale=" + capitale
				+ ",prodottiDisponibili=" + prodottiDisponibili + ",serviziDisponibili=" + serviziDisponibili + "]";
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Fornitore other = (Fornitore) otherObject;
		return partitaIVA.equals(other.partitaIVA) && nome.equals(other.nome) && capitale == other.capitale
				&& prodottiDisponibili.equals(other.prodottiDisponibili)
				&& serviziDisponibili.equals(other.serviziDisponibili);
	}

	public Fornitore clone() {
		try {
			Fornitore cloned = (Fornitore) super.clone();
			cloned.prodottiDisponibili = Utilità.cloneArray(prodottiDisponibili);
			cloned.serviziDisponibili = Utilità.cloneArray(serviziDisponibili);
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
