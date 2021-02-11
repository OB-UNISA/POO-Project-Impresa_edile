package testers;

import fornitore.Prodotto;

public class ProdottoTester {
	public static void main(String[] args) {

		int codice1 = 123456;
		String nome1 = "Chiave inglese", descrizione1 = "Strumento professionale per lavori professionali";
		double prezzo1 = 25.58;
		System.out.println("--Testing costruttore con i seguenti parametri--\ncodice=" + codice1 + ",nome=" + nome1
				+ ",prezzo=" + prezzo1 + ",descrizione=" + descrizione1);
		Prodotto prodotto1 = new Prodotto(codice1, nome1, prezzo1, descrizione1);
		System.out
				.println("-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + prodotto1.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\ncodice="
				+ prodotto1.getCodice() + ",nome=" + prodotto1.getNome() + ",prezzo=" + prodotto1.getPrezzo()
				+ ",descrizione=" + prodotto1.getDescrizione());

		int codice2 = 789123;
		String nome2 = "Vite", descrizione2 = "Da avvitare";
		double prezzo2 = 35.78;
		System.out.println("\n--Testing metodi set con i seguenti parametri--\ncodice=" + codice2 + ",nome=" + nome2
				+ ",prezzo=" + prezzo2 + ",descrizione=" + descrizione2
				+ "\n-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + prodotto1.toString());
		prodotto1.setCodice(codice2);
		prodotto1.setNome(nome2);
		prodotto1.setPrezzo(prezzo2);
		prodotto1.setDescrizione(descrizione2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i medodi set-\n" + prodotto1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + prodotto1.equals(prodotto1));
		Prodotto prodotto2 = new Prodotto(codice1, nome1, prezzo1, descrizione1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + prodotto1.toString() + "\n"
				+ prodotto2.toString() + "\n" + prodotto1.equals(prodotto2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + prodotto1.toString());
		Prodotto prodotto3 = prodotto1.clone();
		System.out.println("-Oggetto clonato-\n" + prodotto3.toString());

	}
}
