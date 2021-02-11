package testers;

import java.util.ArrayList;

import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;

public class FornitoreTester {

	public static void main(String[] args) {

		String partitaIVA1 = "IT8497394", nome1 = "Prodotti&Servizi";
		long capitale1 = 84709340934L;
		System.out.println("--Testing costruttore con i seguenti parametri--\npartitaIVA=" + partitaIVA1 + ",nome="
				+ nome1 + ",capitale=" + capitale1);
		Fornitore fornitore1 = new Fornitore(partitaIVA1, nome1, capitale1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + fornitore1.toString());

		ArrayList<Prodotto> prodotti1 = new ArrayList<>();
		prodotti1.add(new Prodotto(15, "Chiave", 58.25, "Una semplice chiave"));
		prodotti1.add(new Prodotto(78, "Serratura", 178.97, "Una serratura"));
		ArrayList<Servizio> servizi1 = new ArrayList<>();
		servizi1.add(new Servizio("Idraulico", 87.58, 1234567890L, "Aggiusta i tubi"));
		servizi1.add(new Servizio("Muratore", 98.98, 9874561230L, "Costruisce muri"));
		System.out.println("\n--Testing secondo costruttore con i seguenti parametri--\npartitaIVA=" + partitaIVA1
				+ ",nome=" + nome1 + ",capitale=" + capitale1 + "\n,prodottiDisponibili=");
		for (Prodotto prod : prodotti1)
			System.out.println(prod);
		System.out.println(",serviziDisponibili=");
		for (Servizio serv : servizi1)
			System.out.println(serv);
		Fornitore fornitore2 = new Fornitore(partitaIVA1, nome1, capitale1, prodotti1, servizi1);
		System.out.println("-Istanza dell'oggetto con il costruttore-\n" + fornitore2.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\npartitaIVA="
				+ fornitore2.getPartitaIVA() + ",nome=" + fornitore2.getNome() + ",capitale=" + fornitore2.getCapitale()
				+ ",prodottiDisponibili=" + fornitore2.getProdottiDisponibili() + ",serviziDisponibili="
				+ fornitore2.getServiziDisponibili());

		String partitaIVA2 = "BR76858989", nome2 = "Products&Services";
		long capitale2 = 68768753453L;
		ArrayList<Prodotto> prodotti2 = new ArrayList<>();
		prodotti2.add(new Prodotto(30, "Key", 89.78, "A simple key"));
		prodotti2.add(new Prodotto(150, "Lock", 278.97, "A lock"));
		ArrayList<Servizio> servizi2 = new ArrayList<>();
		servizi2.add(new Servizio("Plumber", 450.65, 9766465465L, "Fix the pipes"));
		servizi2.add(new Servizio("Bricklayer", 786.97, 87845415165L, "Build walls"));
		System.out.println("\n--Testing metodi set con i seguenti parametri--\npartitaIVA=" + partitaIVA2 + ",nome="
				+ nome2 + ",capitale=" + capitale2 + "\n,prodottiDisponibili=");
		for (Prodotto prod : prodotti2)
			System.out.println(prod);
		System.out.println(",serviziDisponibili=");
		for (Servizio serv : servizi2)
			System.out.println(serv);
		System.out.println("-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + fornitore2.toString());
		fornitore2.setPartitaIVA(partitaIVA2);
		fornitore2.setNome(nome2);
		fornitore2.setCapitale(capitale2);
		fornitore2.setProdottiDisponibili(prodotti2);
		fornitore2.setServiziDisponibili(servizi2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i metodi set-\n" + fornitore2.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + fornitore2.equals(fornitore2));
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + fornitore2.toString() + "\n"
				+ fornitore1.toString() + "\n" + fornitore2.equals(fornitore1));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + fornitore2.toString());
		Fornitore fornitore3 = fornitore2.clone();
		System.out.println("-Oggetto clonato-\n" + fornitore3.toString());

		Prodotto prodotto1 = new Prodotto(789, "Vite", 78.70, "Una vite");
		Servizio servizio1 = new Servizio("Autista", 785.54, 87687646786L, "Guida qualsiasi veicolo");
		System.out.println(
				"\n--Testing medoto addDisponibile con il seguente array del fornitore e parametro un Prodotto--\n"
						+ fornitore2.getProdottiDisponibili() + "\n" + prodotto1);
		fornitore2.addDisponibile(prodotto1);
		System.out
				.println("-Array del fornitore dopo il metodo addDisponibile-\n" + fornitore2.getProdottiDisponibili());
		System.out.println(
				"--Testing medoto addDisponibile con il seguente array del fornitore e parametro un Servizio--\n"
						+ fornitore2.getServiziDisponibili() + "\n" + servizio1);
		fornitore2.addDisponibile(servizio1);
		System.out
				.println("-Array del fornitore dopo il metodo addDisponibile-\n" + fornitore2.getServiziDisponibili());

		System.out.println(
				"\n--Testing medoto removeDisponibile con il seguente array del fornitore e parametro un Prodotto--\n"
						+ fornitore2.getProdottiDisponibili() + "\n" + prodotto1);
		Prodotto removedP = fornitore2.removeDisponibile(prodotto1);
		System.out.println("-Array del fornitore dopo il metodo removeDisponibile + oggetto ritornato dal metodo-\n"
				+ fornitore2.getProdottiDisponibili() + "\n" + removedP);
		System.out.println(
				"--Testing medoto removeDisponibile con il seguente array del fornitore e parametro un Servizio--\n"
						+ fornitore2.getServiziDisponibili() + "\n" + servizio1);
		Servizio removedS = fornitore2.removeDisponibile(servizio1);
		System.out.println("-Array del fornitore dopo il metodo removeDisponibile + oggetto ritornato dal metodo-\n"
				+ fornitore2.getServiziDisponibili() + "\n" + removedS);

	}
}
