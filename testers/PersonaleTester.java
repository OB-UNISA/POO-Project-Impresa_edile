package testers;

import personale.Personale;

public class PersonaleTester {

	public static void main(String[] args) {

		String cfSnn1 = "ABC123", nome1 = "Mario", cognome1 = "Rossi", professione1 = "Operaio";
		System.out.println("--Testing costruttore con i seguenti parametri--\ncfSnn=" + cfSnn1 + ",nome=" + nome1
				+ ",cognome=" + cognome1 + ",professione=" + professione1);
		Personale personale1 = new Personale(cfSnn1, nome1, cognome1, professione1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + personale1.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\ncfSnn="
				+ personale1.getCfSnn() + ",nome=" + personale1.getNome() + ",cognome=" + personale1.getCognome()
				+ ",professione=" + personale1.getProfessione());

		String cfSnn2 = "DEF456", nome2 = "Luigi", cognome2 = "Verdi", professione2 = "Quadro";
		System.out.println("\n--Testing metodi set con i seguenti parametri--\ncfSnn=" + cfSnn2 + ",nome=" + nome2
				+ ",cognome=" + cognome2 + ",professione=" + professione2
				+ "\n-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + personale1.toString());
		personale1.setCfSnn(cfSnn2);
		personale1.setNome(nome2);
		personale1.setCognome(cognome2);
		personale1.setProfessione(professione2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i medodi set-\n" + personale1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + personale1.equals(personale1));
		Personale personale2 = new Personale(cfSnn1, nome1, cognome1, professione1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + personale1.toString() + "\n"
				+ personale2.toString() + "\n" + personale1.equals(personale2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + personale1.toString());
		Personale personale3 = personale1.clone();
		System.out.println("-Oggetto clonato-\n" + personale3.toString());

	}
}
