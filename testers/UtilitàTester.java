package testers;

import java.util.ArrayList;

import cliente.Cliente;
import fornitore.Prodotto;
import personale.Dirigente;
import personale.Personale;
import utilit‡.Utilit‡;

public class Utilit‡Tester {

	public static void main(String[] args) {

		System.out.println("--Testing metodo cloneArray con il seguente array--");
		ArrayList<Prodotto> prodotti1 = new ArrayList<>();
		prodotti1.add(new Prodotto(1, "a", 1.0, "desc"));
		prodotti1.add(new Prodotto());
		for (Prodotto prod : prodotti1)
			System.out.println(prod);
		System.out.println("-Array clonato-");
		ArrayList<Prodotto> prodotti2 = Utilit‡.cloneArray(prodotti1);
		for (Prodotto prod : prodotti2)
			System.out.println(prod);
		System.out.println(
				"\n--Testing per controllare che il riferimento agli oggetti sia differente--\n-Testing codice identificatore sullo stesso oggetto dello stesso array, se il riferimento Ë uguale, i due codici saranno identici-");
		System.out.println(System.identityHashCode(prodotti1.get(0)));
		System.out.println(System.identityHashCode(prodotti1.get(0)));
		System.out.println(
				"-Testing codice identificatore su oggetti con valori uguali nei campi, il primo appartiene all'array originale, l'altro a quello clonato-\n"
						+ prodotti1.get(0) + "\n" + prodotti2.get(0));
		System.out.println(System.identityHashCode(prodotti1.get(0)));
		System.out.println(System.identityHashCode(prodotti2.get(0)));

		Dirigente dirigente1 = new Dirigente("ABC123", "Mario", "Rossi", 5, 3);
		System.out.println(
				"\n--Testing metodo rewindCast dove F = Dirigente e T = Personale--\n-Valore dei campi dell'oggetto F-\n"
						+ dirigente1.toString());
		Personale personale1 = (Personale) dirigente1;
		System.out.println("-Valore dei campi dell'oggetto F castato a T-\n" + personale1.toString());
		Dirigente dirigente2 = Utilit‡.rewindCast(personale1, Dirigente.class);
		System.out.println(
				"-Valore dei campi dell'oggetto T castato a F con il metodo rewindCast-\n" + dirigente2.toString());
		Cliente cliente1 = new Cliente();
		Dirigente dirigente3 = Utilit‡.rewindCast(cliente1, Dirigente.class);
		System.out.println(
				"--Testing per controllare che il metodo ritorni null quando le precondizioni non sono rispettate--\n-Testing con F = Dirigente e T = Cliente-\n"
						+ dirigente3);

	}
}
