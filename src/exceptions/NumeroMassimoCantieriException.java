package exceptions;

/**
 * Classe che defenisce l'eccezione da lanciare quando il dirigente ha un numero
 * attuale di cantieri > del numero massimo dei cantieri a lui consentiti
 */
public class NumeroMassimoCantieriException extends RuntimeException {

	private static final long serialVersionUID = 6960816955817981433L;

	public NumeroMassimoCantieriException() {
		super();
	}

	public NumeroMassimoCantieriException(String string) {
		super(string);
	}

}
