package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando si cerca di assegnare un
 * responsabile ed esso non è un quadro o dirigente
 */
public class ResponsabileException extends RuntimeException {

	private static final long serialVersionUID = 6106801808346186834L;

	public ResponsabileException() {
		super();
	}

	public ResponsabileException(String message) {
		super(message);
	}

}
