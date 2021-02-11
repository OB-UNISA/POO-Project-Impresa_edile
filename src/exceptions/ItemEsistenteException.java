package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando si cerca di aggiungere un
 * item già esistente
 */
public class ItemEsistenteException extends Exception {

	private static final long serialVersionUID = -3763448862854907246L;

	public ItemEsistenteException() {
		super();
	}

	public ItemEsistenteException(String message) {
		super(message);
	}

}
