package testers;

import java.util.ArrayList;

import cliente.Cantiere;
import entelocale.Permesso;
import exceptions.PermessoEnteNegatoException;
import exceptions.PermessoEnteNonRilasciatoException;
import exceptions.ItemEsistenteException;
import exceptions.ItemNonEsistenteException;
import impresa.CantiereImpresa;
import personale.Dirigente;
import personale.Personale;


public class CantiereImpresaTester {

	public static void main(String[] args) {

		Cantiere cantiere1 = new Cantiere(785, "Milano", 75875845L);
		System.out.println("--Testing costruttore con i seguenti parametri--\ncantiere=" + cantiere1);
		CantiereImpresa cantiereImpresa1 = new CantiereImpresa(cantiere1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + cantiereImpresa1.toString());

		ArrayList<Personale> personale1 = new ArrayList<>();
		personale1.add(new Personale("DFJDN577", "Mario", "Rossi", "Quadro"));
		personale1.add(new Personale("IJNS6887", "Luigi", "Verdi", "Operaio"));
		Personale responsabile1 = new Dirigente("SFH86FDY", "Franco", "Gialli", 5, 1);
		personale1.add(responsabile1);
		Permesso permesso1 = new Permesso(785, Permesso.LAVORAZIONE);
		System.out.println(
				"\n--Testing secondo costruttore con i seguenti parametri--\ncantiere=" + cantiere1 + "\n,personale=");
		for (Personale per : personale1)
			System.out.println(per);
		System.out.println(",responsabile=" + responsabile1 + "\n,permesso=" + permesso1);
		CantiereImpresa cantiereImpresa2 = new CantiereImpresa(cantiere1, personale1, responsabile1, permesso1);
		System.out.println("-Istanza dell'oggetto con il costruttore \n" + cantiereImpresa2.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\npersonale="
				+ cantiereImpresa2.getPersonale() + "\n,responsabile=" + cantiereImpresa2.getResponsabile()
				+ ",permesso=" + cantiereImpresa2.getPermesso() + ",stato=" + cantiereImpresa2.getStato());

		ArrayList<Personale> personale2 = new ArrayList<>();
		personale2.add(new Personale("LJJOO4R4", "Giovanni", "Bianchi", "Operaio"));
		personale2.add(new Personale("JH8957EF", "Paolo", "Neri", "Operaio"));
		Personale responsabile2 = new Personale("HNEWD878DCX", "Michele", "Violi", "Quadro");
		personale2.add(responsabile2);
		Permesso permesso2 = new Permesso(785, Permesso.CONCESSO);
		String stato1 = CantiereImpresa.OPERATIVO;
		System.out.println("\n--Testing metodi set con i seguenti parametri--\n,personale=");
		for (Personale per : personale2)
			System.out.println(per);
		System.out.println(",responsabile=" + responsabile2 + "\n,permesso=" + permesso2 + ",stato=" + stato1);
		System.out.println(
				"-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + cantiereImpresa2.toString());
		cantiereImpresa2.setPersonale(personale2);
		cantiereImpresa2.setResponsabile(responsabile2);
		cantiereImpresa2.setPermesso(permesso2);
		cantiereImpresa2.setStato(CantiereImpresa.OPERATIVO);
		System.out.println(
				"-Valore dei campi dell'oggetto dopo aver invocato i metodi set-\n" + cantiereImpresa2.toString());

		System.out.println("\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n"
				+ cantiereImpresa2.equals(cantiereImpresa2));
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + cantiereImpresa2.toString() + "\n"
				+ cantiereImpresa1.toString() + "\n" + cantiereImpresa2.equals(cantiereImpresa1));
		 cantiereImpresa2.equals(cantiereImpresa2);
		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + cantiereImpresa2.toString());
		CantiereImpresa cantiereImpresa3 = cantiereImpresa2.clone();
		System.out.println("-Oggetto clonato-\n" + cantiereImpresa3.toString());

		System.out.println(
				"\n--Testing lancio dell'eccezione PermessoEnteNonRilasciatoException, se è lanciata verrà stampato il StackTrace--");
		cantiereImpresa2.setStato(CantiereImpresa.NON_OPERATIVO);
		try {
			cantiereImpresa2.setPermesso(permesso1);
			cantiereImpresa2.setStato(CantiereImpresa.OPERATIVO);
		} catch (PermessoEnteNonRilasciatoException e) {
			e.printStackTrace();
		}

		System.out.println(
				"\n--Testing lancio dell'eccezione PermessoEnteNegatoRilasciatoException, se è lanciata verrà stampato il StackTrace--");
		try {
			Permesso permesso3 = new Permesso(785, Permesso.NEGATO);
			cantiereImpresa2.setPermesso(permesso3);
			cantiereImpresa2.setStato(CantiereImpresa.OPERATIVO);
		} catch (PermessoEnteNegatoException e) {
			e.printStackTrace();
		}

		Personale personale4 = new Personale("BVUSHDOU88", "Marco", "Ciani", "Operaio");
		System.out.println("\n--Testing metodo addPersonale con il seguente array e parametro--\n"
				+ cantiereImpresa2.getPersonale() + "\n" + personale4);
		try {
			cantiereImpresa2.addPersonale(personale4);
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo il metodo addPersonale-\n" + cantiereImpresa2.getPersonale());
		System.out.println(
				"--Testing lancio dell'eccezione PersonaleEsistenteException, se è lanciata verrà stampato il StackTrace--");
		try {
			cantiereImpresa2.addPersonale(personale4);
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}

		System.out.println("\n--Testing metodo removePersonale con il seguente array e parametro--\n"
				+ cantiereImpresa2.getPersonale() + "\n" + personale4);
		Personale personale5 = null;
		try {
			personale5 = cantiereImpresa2.removePersonale(personale4);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo il metodo removePersonale + oggetto ritornato-\n"
				+ cantiereImpresa2.getPersonale() + "\n" + personale5);
		System.out.println(
				"--Testing lancio dell'eccezione PersonaleNonEsistenteException, se è lanciata verrà stampato il StackTrace--");
		try {
			cantiereImpresa2.removePersonale(personale4);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}

	}
}
