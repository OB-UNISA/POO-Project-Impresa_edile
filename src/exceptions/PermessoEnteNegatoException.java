package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando si cerca di rendere
 * operativo un cantiere il cui permesso è stato negato
 */
public class PermessoEnteNegatoException extends RuntimeException {

	private static final long serialVersionUID = -5127681571611619460L;

	public PermessoEnteNegatoException() {
		super();
	}

	public PermessoEnteNegatoException(String message) {
		super(message);
	}

}
