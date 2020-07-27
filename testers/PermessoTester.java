package testers;

import entelocale.Permesso;

public class PermessoTester {

	public static void main(String[] args) {

		int codice1 = 123;
		String stato1 = Permesso.LAVORAZIONE;
		System.out.println("--Testing costruttore con i seguenti parametri--\ncodice=" + codice1 + ",stato=" + stato1);
		Permesso permesso1 = new Permesso(codice1, stato1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + permesso1.toString());

		System.out.println("\n--Testing metodi get con i valori dell'istanza precedente--\ncodice="
				+ permesso1.getCodice() + ",stato=" + permesso1.getStato());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + permesso1.equals(permesso1));
		Permesso permesso2 = new Permesso(456, Permesso.CONCESSO);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + permesso1.toString() + "\n"
				+ permesso2.toString() + "\n" + permesso1.equals(permesso2));
	}
}
