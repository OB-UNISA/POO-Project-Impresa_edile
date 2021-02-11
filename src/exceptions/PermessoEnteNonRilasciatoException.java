package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando si cerca di rendere
 * operativo un cantiere il cui permesso è inisistente o nello stato di
 * lavorazione
 */
public class PermessoEnteNonRilasciatoException extends RuntimeException {

	private static final long serialVersionUID = -7013909876367925352L;

	public PermessoEnteNonRilasciatoException() {
		super();
	}

	public PermessoEnteNonRilasciatoException(String message) {
		super(message);
	}
}
