package impresa;

import java.util.ArrayList;

import cliente.Cantiere;
import entelocale.Permesso;
import exceptions.ItemEsistenteException;
import exceptions.ItemNonEsistenteException;
import exceptions.NumeroMassimoCantieriException;
import exceptions.PermessoEnteNegatoException;
import exceptions.PermessoEnteNonRilasciatoException;
import exceptions.ResponsabileException;
import interfacce.Clone;
import personale.Dirigente;
import personale.Personale;
import utilità.Utilità;

/**
 * Classe che definisce il cantiere dell'impresa
 */
public class CantiereImpresa extends Cantiere implements Clone<CantiereImpresa> {

	private static final long serialVersionUID = -9103801628414282915L;
	/**
	 * Variabile di istanza del personale sul cantiere del cantiere dell'impresa
	 */
	private ArrayList<Personale> personaleSulCantiere;
	/**
	 * Variabile di istanza del responsabile del cantiere dell'impresa
	 */
	private Personale caposquadra;
	/**
	 * Variabile di istanza del caposquadra del cantiere dell'impresa
	 */
	private Personale responsabile;
	/**
	 * Variabile di istanza del permesso del cantiere dell'impresa
	 */
	private Permesso permesso;
	/**
	 * Variabile di istanza dello stato del cantiere dell'impresa
	 */
	private String stato = NON_OPERATIVO;
	/**
	 * Costante per definire lo stato del cantiere dell'impresa
	 */
	public final static String NON_OPERATIVO = "NON OPERATIVO", OPERATIVO = "OPERATIVO";

	/**
	 * Costruttore per istanziare un oggetto <code>CantiereImpresa</code> con campi
	 * vuoti
	 */
	public CantiereImpresa() {
		super();
		personaleSulCantiere = null;
		responsabile = null;
		caposquadra = null;
		permesso = null;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>CantiereImpresa</code> con campi
	 * non vuoti e il personale sul cantiere dell'impresa, responsabile, permesso
	 * vuoti
	 * 
	 * @param cantiere del cliente
	 */
	public CantiereImpresa(Cantiere cantiere) {
		super(cantiere.getCodice(), cantiere.getLocalità(), cantiere.getValore());
		personaleSulCantiere = null;
		responsabile = null;
		permesso = null;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>CantiereImpresa</code> con campi
	 * non vuoti, incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param cantiere     del cliente
	 * @param personale    del cantiere dell'impresa
	 * @param responsabile del cantiere dell'impresa
	 * @param caposquadra  del cantiere dell'impresa
	 * @param permesso     del cantiere dell'impresa
	 */
	public CantiereImpresa(Cantiere cantiere, ArrayList<Personale> personale, Personale responsabile,
			Personale caposquadra, Permesso permesso) {
		super(cantiere.getCodice(), cantiere.getLocalità(), cantiere.getValore());
		this.personaleSulCantiere = personale;
		setResponsabile(responsabile);
		setCaposquadra(caposquadra);
		this.permesso = permesso;
	}

	/**
	 * Costruttore per istanziare un oggetto <code>CantiereImpresa</code> con campi
	 * non vuoti, incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param cantiere     del cliente
	 * @param personale    del cantiere dell'impresa
	 * @param responsabile del cantiere dell'impresa
	 * @param permesso     del cantiere dell'impresa
	 */
	public CantiereImpresa(Cantiere cantiere, ArrayList<Personale> personale, Personale responsabile,
			Permesso permesso) {
		super(cantiere.getCodice(), cantiere.getLocalità(), cantiere.getValore());
		this.personaleSulCantiere = personale;
		setResponsabile(responsabile);
		caposquadra = null;
		this.permesso = permesso;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Personale&gt;</code> in cui vi è la
	 * lista del personale sul cantiere del cantiere dell'impresa, incapsulamento
	 * non mantenuto per avere la consistenza sui dati
	 * 
	 * @return personaleSulCantiere del cantiere dell'impresa
	 */
	public ArrayList<Personale> getPersonale() {
		return personaleSulCantiere;
	}

	/**
	 * Restituisce un oggetto <code>Personale</code> in cui vi è il responsabile del
	 * cantiere dell'impresa, incapsulamento non mantenuto per avere la consistenza
	 * sui dati
	 * 
	 * @return responsabile del cantiere dell'impresa
	 */
	public Personale getResponsabile() {
		return responsabile;
	}

	/**
	 * Restituisce un oggetto <code>Personale</code> in cui vi è il caposquadra del
	 * cantiere dell'impresa, incapsulamento non mantenuto per avere la consistenza
	 * sui dati
	 * 
	 * @return caposquadra del cantiere dell'impresa
	 */
	public Personale getCaposquadra() {
		return caposquadra;
	}

	/**
	 * Restituisce un oggetto <code>Permesso</code> in cui vi è il permesso del
	 * cantiere dell'impresa
	 * 
	 * @return permesso del cantiere dell'impresa
	 */
	public Permesso getPermesso() {
		return permesso;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è lo stato del cantiere
	 * dell'impresa
	 * 
	 * @return stato del cantiere dell'impresa
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Assegna o sostituisce il personale sul cantiere del cantiere dell'impresa,
	 * incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param personale sul cantiere del cantiere dell'impresa
	 */
	public void setPersonale(ArrayList<Personale> personale) {
		this.personaleSulCantiere = personale;
	}

	/**
	 * Assegna o sostituisce il responsabile del cantiere dell'impresa,,
	 * incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param responsabile del cantiere dell'impresa
	 * @throws NumeroMassimoCantieriException
	 * @throws ResponsabileException
	 */
	public void setResponsabile(Personale responsabile) throws NumeroMassimoCantieriException, ResponsabileException {
		if (responsabile != null) {
			if (responsabile.getProfessione().equals("Quadro") || responsabile.getProfessione().equals("Dirigente")) {
				if (getValore() > 500000 && !responsabile.getProfessione().equals("Dirigente"))
					throw new ResponsabileException(
							"Solo il dirigente può essere responsabile di un cantiere con valore > 500000");
			} else
				throw new ResponsabileException("Responsabile non è un quadro o dirigente");
			Dirigente dirigente = Utilità.rewindCast(responsabile, Dirigente.class);
			if (dirigente != null) {
				dirigente.setNumeroAttualeCantieri(dirigente.getNumeroAttualeCantieri() + 1);
				this.responsabile = responsabile;

			} else
				this.responsabile = responsabile;
			if (personaleSulCantiere == null)
				personaleSulCantiere = new ArrayList<>();
			if (!personaleSulCantiere.contains(responsabile))
				personaleSulCantiere.add(responsabile);
		} else
			this.responsabile = null;

	}

	/**
	 * Assegna o sostituisce il caposquadra del cantiere dell'impresa,
	 * incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param caposquadra del cantiere dell'impresa
	 */
	public void setCaposquadra(Personale caposquadra) {
		if (caposquadra != null) {
			if (caposquadra.getProfessione().equals("Quadro")) {
				this.caposquadra = caposquadra;
				if (personaleSulCantiere == null)
					personaleSulCantiere = new ArrayList<>();
				if (!personaleSulCantiere.contains(caposquadra))
					personaleSulCantiere.add(caposquadra);
			}
		} else
			this.caposquadra = null;

	}

	/**
	 * Assegna o sostituisce il permesso del cantiere dell'impresa
	 * 
	 * @param permesso del cantiere dell'impresa
	 */
	public void setPermesso(Permesso permesso) {
		this.permesso = permesso;
	}

	/**
	 * Assegna o sostituisce lo stato del cantiere dell'impresa, se si tenta di
	 * rendere operativo il cantiere senza avere il permesso, verranno lanciate le
	 * eccezioni a seconda dei casi
	 * 
	 * @param stato del cantiere dell'impresa
	 * @throws PermessoEnteNonRilasciatoException
	 * @throws PermessoEnteNegatoException
	 * 
	 */
	public void setStato(String stato) {
		if (stato.equals(OPERATIVO)) {
			if (permesso == null || permesso.getStato().equals(Permesso.LAVORAZIONE)) {
				this.stato = NON_OPERATIVO;
				throw new PermessoEnteNonRilasciatoException("Permesso non esistente o in lavorazione");
			}
			if (permesso.getStato().equals(Permesso.NEGATO)) {
				this.stato = NON_OPERATIVO;
				throw new PermessoEnteNegatoException("Permesso negato");
			}
			this.stato = OPERATIVO;

		} else
			this.stato = NON_OPERATIVO;
	}

	/**
	 * Aggiunge alla lista del personale sul cantiere un nuovo personale. Se il
	 * personale che si cerca di aggiungere è già esistente verrà lanciata
	 * un'eccezione, incapsulamento non mantenuto per avere la consistenza sui dati
	 * 
	 * @param personale da aggiungere
	 * @throws ItemEsistenteException
	 */
	public void addPersonale(Personale personale) throws ItemEsistenteException {
		if (personale != null) {
			if (personaleSulCantiere == null)
				personaleSulCantiere = new ArrayList<>();
			if (personaleSulCantiere.contains(personale))
				throw new ItemEsistenteException("Personale già esistente");
			else
				personaleSulCantiere.add(personale);
		}
	}

	/**
	 * Rimuove della lista del personale del cantiere uno specifico personale, se il
	 * personale che si cerca di rimuovere non è esistente, verrà lanciata
	 * un'eccezione
	 * 
	 * @param personale da rimuovere
	 * @return personale rimosso o null se il parametro è <code>null</code>
	 * @throws ItemNonEsistenteException
	 */
	public Personale removePersonale(Personale personale) throws ItemNonEsistenteException {
		if (personale != null) {
			if (personaleSulCantiere == null)
				throw new ItemNonEsistenteException("Personale non esistente");
			int index = personaleSulCantiere.indexOf(personale);
			if (index != -1) {
				Personale reference = personaleSulCantiere.get(index);
				personaleSulCantiere.remove(index);
				if (personaleSulCantiere.size() <= 0)
					personaleSulCantiere = null;
				return reference;
			} else
				throw new ItemNonEsistenteException("Personale non esistente");
		}
		return null;
	}

	public String toString() {
		return super.toString() + "[personaleSulCantiere=" + personaleSulCantiere + ",responsabile=" + responsabile
				+ ",caposquadra=" + caposquadra + ",permesso=" + permesso + ",stato=" + stato + "]";
	}

	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject))
			return false;
		CantiereImpresa other = (CantiereImpresa) otherObject;

		boolean result1;
		if (personaleSulCantiere == null && other.personaleSulCantiere == null)
			result1 = true;
		else if ((personaleSulCantiere == null && other.personaleSulCantiere != null)
				|| ((personaleSulCantiere != null && other.personaleSulCantiere == null)))
			result1 = false;
		else
			result1 = personaleSulCantiere.equals(other.personaleSulCantiere);

		boolean result2;
		if (responsabile == null && other.responsabile == null)
			result2 = true;
		else if ((responsabile == null && other.responsabile != null)
				|| ((responsabile != null && other.responsabile == null)))
			result2 = false;
		else
			result2 = responsabile.equals(other.responsabile);

		boolean result4;
		if (caposquadra == null && other.caposquadra == null)
			result4 = true;
		else if ((caposquadra == null && other.caposquadra != null)
				|| ((caposquadra != null && other.caposquadra == null)))
			result4 = false;
		else
			result4 = caposquadra.equals(other.caposquadra);

		boolean result3;
		if (permesso == null && other.permesso == null)
			result3 = true;
		else if ((permesso == null && other.permesso != null) || ((permesso != null && other.permesso == null)))
			result3 = false;
		else
			result3 = permesso.equals(other.permesso);

		return result1 && result2 && result4 && result3;
	}

	public CantiereImpresa clone() {
		CantiereImpresa cloned = (CantiereImpresa) super.clone();
		cloned.personaleSulCantiere = Utilità.cloneArray(personaleSulCantiere);
		cloned.responsabile = (responsabile == null ? null : responsabile.clone());
		cloned.caposquadra = (caposquadra == null ? null : caposquadra.clone());
		return cloned;
	}

}
