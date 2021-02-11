package interazioni.esterno.collaudo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;

/**
 * Classe per simulare un server di fornitori. Quando, dall'interfaccia grafica,
 * si eseguono operazioni che coinvolgono i fornitori, prima di ogni operazione
 * viene aggiornata la lista dei fornitori dell'impresa, tale lista viene
 * generata in questa classe
 */
public class AggiornamentoFornitori {

	public static void main(String[] args) {

		ArrayList<Fornitore> fornitori = new ArrayList<>();

		ArrayList<Prodotto> prodotti1 = new ArrayList<>();
		prodotti1.add(new Prodotto(14, "Chiodi", 12.59, ""));
		prodotti1.add(new Prodotto(87, "Martello", 19.99, ""));
		prodotti1.add(new Prodotto(47, "Trapano", 28.0, ""));
		prodotti1.add(new Prodotto(52, "Trave", 70.87, ""));
		prodotti1.add(new Prodotto(178, "Vernice", 37.05, ""));

		ArrayList<Servizio> servizi1 = new ArrayList<>();
		servizi1.add(new Servizio("Idraulico", 158.56, 3854964780L, ""));
		servizi1.add(new Servizio("Elettricista", 240.82, 4789624597L, ""));
		servizi1.add(new Servizio("Imbianchino", 78.98, 2478621586L, ""));
		servizi1.add(new Servizio("Saldatore", 300.00, 3785413697L, ""));
		servizi1.add(new Servizio("Pavimentista", 420.04, 6578424597L, ""));

		fornitori.add(new Fornitore("IT876454878", "AiutiEdili", 5784587L, prodotti1, servizi1));

		ArrayList<Prodotto> prodotti2 = new ArrayList<>();
		prodotti2.add(new Prodotto(45, "Chiodi", 19.78, ""));
		prodotti2.add(new Prodotto(78, "Chiave inglese", 11.99, ""));
		prodotti2.add(new Prodotto(12, "Trapano", 15.07, ""));
		prodotti2.add(new Prodotto(178, "Presa", 7.50, ""));
		prodotti2.add(new Prodotto(136, "Prolunga", 17.97, ""));

		ArrayList<Servizio> servizi2 = new ArrayList<>();
		servizi2.add(new Servizio("Muratore", 158.56, 687465467L, ""));
		servizi2.add(new Servizio("Elettricista", 350.78, 986456787L, ""));
		servizi2.add(new Servizio("Imbianchino", 57.45, 16894655L, ""));
		servizi2.add(new Servizio("Cementista", 3000.01, 87865454L, ""));
		servizi2.add(new Servizio("Pavimentista", 420.04, 35468454568L, ""));

		fornitori.add(new Fornitore("IT1284563", "BianchiMarioEdile", 5784587L, prodotti2, servizi2));

		ArrayList<Prodotto> prodotti3 = new ArrayList<>();
		prodotti3.add(new Prodotto(44, "Cemento", 85.78, ""));
		prodotti3.add(new Prodotto(25, "Tubo", 25.78, ""));
		prodotti3.add(new Prodotto(45, "Trapano", 9.78, ""));
		prodotti3.add(new Prodotto(18, "Trave", 74.50, ""));
		prodotti3.add(new Prodotto(16, "Vernice", 19.97, ""));

		ArrayList<Servizio> servizi3 = new ArrayList<>();
		servizi3.add(new Servizio("Muratore", 158.56, 28814654L, ""));
		servizi3.add(new Servizio("Pavimentista", 350.78, 167348787L, ""));
		servizi3.add(new Servizio("Saldatore", 150.45, 3487854L, ""));
		servizi3.add(new Servizio("Cementista", 2998.01, 782485146L, ""));
		servizi3.add(new Servizio("Pavimentista", 399.07, 68424855L, ""));

		fornitori.add(new Fornitore("IT7647687", "BianchiLuigiEdile", 5784587L, prodotti3, servizi3));

		File file = new File("Fornitori.txt");

		try (FileOutputStream outF = new FileOutputStream(file);
				ObjectOutputStream outO = new ObjectOutputStream(outF)) {

			for (Fornitore fornitore : fornitori)
				outO.writeObject(fornitore);

			System.out.println("Finito");
		} catch (FileNotFoundException e) {
			System.out.println("File " + file.getName() + " non trovato");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
