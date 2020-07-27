package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando il personale che si cerca
 * di aggiungere è già esistente
 */
public class PersonaleEsistenteException extends Exception {

	private static final long serialVersionUID = -3763448862854907246L;

	public PersonaleEsistenteException() {
		super();
	}

	public PersonaleEsistenteException(String message) {
		super(message);
	}

}
