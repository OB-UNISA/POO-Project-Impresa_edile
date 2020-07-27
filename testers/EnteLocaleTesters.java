package testers;

import cliente.Cantiere;
import entelocale.EnteLocale;
import entelocale.Permesso;
import impresa.CantiereImpresa;

public class EnteLocaleTesters {

	public static void main(String[] args) {

		int codice1 = 123;
		String nome1 = "ABC";
		System.out.println("--Testing costruttore con i seguenti parametri--\ncodice=" + codice1 + ",nome=" + nome1);
		EnteLocale enteLocale1 = new EnteLocale(codice1, nome1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + enteLocale1.toString());

		System.out.println("\n--Testing metodi get con i valori dell'istanza precedente--\ncodice="
				+ enteLocale1.getCodice() + ",nome=" + enteLocale1.getNome());

		System.out.println("\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n"
				+ enteLocale1.equals(enteLocale1));
		EnteLocale enteLocale2 = new EnteLocale(456, "DEF");
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + enteLocale1.toString() + "\n"
				+ enteLocale2.toString() + "\n" + enteLocale1.equals(enteLocale2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + enteLocale1.toString());
		EnteLocale enteLocale3 = enteLocale1.clone();
		System.out.println("-Oggetto clonato-\n" + enteLocale3.toString());

		CantiereImpresa cantiereImpresa1 = new CantiereImpresa(new Cantiere(789, "Milano", 7851655L));
		String stato1 = Permesso.CONCESSO;
		System.out.println("\n--Testing metodo setPermessoCantiere con i seguenti parametri--\ncantiereImpresa="
				+ cantiereImpresa1 + "\n,stato=" + stato1);
		cantiereImpresa1.setPermesso(enteLocale1.setPermessoCantiere(cantiereImpresa1, stato1));
		System.out.println("-Valore dei campi dell'oggeto cantiereImpresa dopo aver invocato i metodi-\n"
				+ cantiereImpresa1.toString());
	}
}
