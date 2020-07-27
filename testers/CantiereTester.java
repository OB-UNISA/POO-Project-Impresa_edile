package testers;

import cliente.Cantiere;

public class CantiereTester {

	public static void main(String[] args) {

		int codice1 = 123;
		String località1 = "Milano";
		long valore1 = 34324932L;
		System.out.println("--Testing costruttore con i seguenti parametri--\ncodice=" + codice1 + ",località="
				+ località1 + ",valore=" + valore1);
		Cantiere cantiere1 = new Cantiere(codice1, località1, valore1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + cantiere1.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\ncodice="
				+ cantiere1.getCodice() + ",località=" + cantiere1.getLocalità() + ",valore=" + cantiere1.getValore());

		int codice2 = 345;
		String località2 = "Genova";
		long valore2 = 6897657L;
		System.out.println("\n--Testing metodi set con i seguenti parametri--\ncodice=" + codice2 + ",località="
				+ località2 + ",valore=" + valore2
				+ "\n-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + cantiere1.toString());
		cantiere1.setCodice(codice2);
		cantiere1.setLocalità(località2);
		cantiere1.setValore(valore2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i medodi set-\n" + cantiere1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + cantiere1.equals(cantiere1));
		Cantiere cantiere2 = new Cantiere(codice1, località1, valore1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + cantiere1.toString() + "\n"
				+ cantiere2.toString() + "\n" + cantiere1.equals(cantiere2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + cantiere1.toString());
		Cantiere cantiere3 = cantiere1.clone();
		System.out.println("-Oggetto clonato-\n" + cantiere3.toString());

	}
}
