package testers;

import fornitore.Servizio;

public class ServizioTester {

	public static void main(String[] args) {

		long numeroServizioClienti1 = 1234567890L;
		String nome1 = "Idraulico", descrizione1 = "Sistema i tubi";
		double prezzo1 = 150.99;
		System.out.println("--Testing costruttore con i seguenti parametri--\nnome=" + nome1 + ",prezzo=" + prezzo1 + ",numeroServizioClienti=" + numeroServizioClienti1 
				+ ",descrizione=" + descrizione1);
		Servizio servizio1 = new Servizio(nome1, prezzo1, numeroServizioClienti1, descrizione1);
		System.out
				.println("-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + servizio1.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\nnome=" + servizio1.getNome() + ",numeroServizioClienti=" + servizio1.getNumeroServizioClienti()
				+ ",prezzo=" + servizio1.getPrezzo() + ",descrizione=" + servizio1.getDescrizione());

		long numeroServizioClienti2 = 9876543210L;
		String nome2 = "Muratore", descrizione2 = "Costruisce muri";
		double prezzo2 = 180.89;
		System.out.println("\n--Testing metodi set con i seguenti parametri--\nnome=" + nome2 + ",prezzo=" + prezzo2 + ",numeroServizioClienti=" + numeroServizioClienti2
				+",descrizione=" + descrizione2 + "\n-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + servizio1.toString());
		servizio1.setNumeroServizioClienti(numeroServizioClienti2);
		servizio1.setNome(nome2);
		servizio1.setPrezzo(prezzo2);
		servizio1.setDescrizione(descrizione2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i medodi set-\n" + servizio1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + servizio1.equals(servizio1));
		Servizio servizio2 = new Servizio(nome1, prezzo1, numeroServizioClienti1, descrizione1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + servizio1.toString() + "\n"
				+ servizio2.toString() + "\n" + servizio1.equals(servizio2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + servizio1.toString());
		Servizio servizio3 = servizio1.clone();
		System.out.println("-Oggetto clonato-\n" + servizio3.toString());

	}
}
