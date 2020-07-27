package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando si cerca di rimuovere un
 * item dall'impresa non esistente
 */
public class ItemNonEsistenteException extends Exception {

	private static final long serialVersionUID = -2785361005084290316L;

	public ItemNonEsistenteException() {
		super();
	}

	public ItemNonEsistenteException(String message) {
		super(message);
	}
}
