package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando il personale che si cerca
 * di rimuovere non è esistente
 */
public class PersonaleNonEsistenteException extends Exception {

	private static final long serialVersionUID = -2785361005084290316L;

	public PersonaleNonEsistenteException() {
		super();
	}

	public PersonaleNonEsistenteException(String message) {
		super(message);
	}
}
