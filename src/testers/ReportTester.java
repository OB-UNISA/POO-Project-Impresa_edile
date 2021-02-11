package testers;

import java.util.ArrayList;

import cliente.Cantiere;
import cliente.Cliente;
import exceptions.ItemEsistenteException;
import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;
import impresa.CantiereImpresa;
import personale.Dirigente;
import personale.Personale;
import report.Report;

public class ReportTester {

	public static void main(String[] args) {

		ArrayList<Personale> listaPersonale1 = new ArrayList<>();
		listaPersonale1.add(new Personale("UY89W3YFC", "Mario", "Rossi", "Operaio"));
		listaPersonale1.add(new Personale("ADSJO343", "Luigi", "Verdi", "Impiegato"));
		listaPersonale1.add(new Personale("LAWOIDN3R", "Marco", "Bruni", "Quadro"));
		listaPersonale1.add(new Dirigente("GFW698YEWHEC8", "Franco", "Ciani", 6));
		listaPersonale1.add(new Personale("WXQDOWE", "Jonh", "Red", "Operaio"));
		listaPersonale1.add(new Personale("32FCJEK", "Louis", "Green", "Impiegato"));
		listaPersonale1.add(new Personale("SI21E09UDJC", "Carl", "Brown", "Quadro"));
		listaPersonale1.add(new Dirigente("Q24E9UD3CO", "Frank", "Cyan", 7));

		System.out.println("--Testing metodo sort con il seguente parametro--\n" + listaPersonale1
				+ "\n-Oggetto ritornato dal metodo-\n" + Report.sort(listaPersonale1, (Personale o1, Personale o2) -> {
					if (o1.getNome().compareTo(o2.getNome()) == 0)
						return 0;
					if (o1.getNome().compareTo(o2.getNome()) > 0)
						return 1;
					return -1;
				}));

		ArrayList<Cliente> listaCliente1 = new ArrayList<>();
		listaCliente1.add(
				new Cliente("UIG34U5R", "Cliente1", "Cliente12", 7687687L, new Cantiere(78545, "Milano", 796649L)));
		listaCliente1.add(
				new Cliente("BASUHD3049", "Cliente2", "Cliente23", 8754368L, new Cantiere(5872, "Genova", 24616L)));
		listaCliente1.add(new Cliente("BWDO34", "Cliente3", "Cliente32", 8752465L, new Cantiere(5945, "Roma", 66546L)));
		listaCliente1
				.add(new Cliente("LJR23IFN", "Cliente4", "Cliente43", 7687L, new Cantiere(785752, "Rimini", 16876L)));

		ArrayList<CantiereImpresa> listaCantieri1 = new ArrayList<>();
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(0).getCantiere()));
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(1).getCantiere()));
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(2).getCantiere()));
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(3).getCantiere()));

		CantiereImpresa min = new CantiereImpresa(new Cantiere(000, "nd", 17000));
		CantiereImpresa max = new CantiereImpresa(new Cantiere(000, "nd", 750000));

		System.out.println("\n--Testing metodo valoriCompresi con i seguenti parametri--\ncantieri=" + listaCantieri1
				+ "\nmin=" + min + "\nmax=" + max + "\n-Oggetto ritornato dal metodo-\n"
				+ Report.valoriCompresi(listaCantieri1, min, max, (CantiereImpresa o1, CantiereImpresa o2) -> {
					if (o1.getValore() == o2.getValore())
						return 0;
					if (o1.getValore() > o2.getValore())
						return 1;
					return -1;
				}));

		ArrayList<Fornitore> listaFornitori1 = new ArrayList<>();

		ArrayList<Prodotto> listaProdotti1F = new ArrayList<>();
		listaProdotti1F.add(new Prodotto(654, "Vite", 78.56, "Vite1"));
		listaProdotti1F.add(new Prodotto(741, "Chiave", 45.05, "Chiave1"));
		ArrayList<Servizio> listaServizi1F = new ArrayList<>();
		listaServizi1F.add(new Servizio("Idraulico", 789.58, 065323464L, "Idraulico1"));
		listaServizi1F.add(new Servizio("Muratore", 248.58, 87654635411L, "Muratore1"));
		Fornitore fornitore1 = new Fornitore("DFUHCO490R09", "Fornitore1", 676876584L, listaProdotti1F, listaServizi1F);

		ArrayList<Prodotto> listaProdotti2F = new ArrayList<>();
		listaProdotti2F.add(new Prodotto(102, "Legno", 98.56, "Legno1"));
		listaProdotti2F.add(new Prodotto(78, "Presa", 15.78, "Presa1"));
		ArrayList<Servizio> listaServizi2F = new ArrayList<>();
		listaServizi2F.add(new Servizio("Imbianchino", 547.58, 8768464L, "Imbianchino1"));
		listaServizi2F.add(new Servizio("Muratore", 248.58, 87654635411L, "Muratore1"));
		Fornitore fornitore2 = new Fornitore("IUEHWF8", "Fornitore2", 7658765878L, listaProdotti2F, listaServizi2F);

		listaFornitori1.add(fornitore1);
		listaFornitori1.add(fornitore2);

		ArrayList<Prodotto> listaProdotti3F = new ArrayList<>();
		listaProdotti3F.add(new Prodotto(654, "Martello", 36.07, "Martello1"));
		listaProdotti3F.add(new Prodotto(741, "Fodera", 88.30, "Fodera1"));
		ArrayList<Servizio> listaServizi3F = new ArrayList<>();
		listaServizi3F.add(new Servizio("Falegname", 275.89, 24892584521L, "Falegname1"));
		listaServizi3F.add(new Servizio("Architetto", 899.99, 68768746887L, "Architetto1"));
		Fornitore fornitore3 = new Fornitore("4BAOKFOJ2", "Fornitore3", 354921L, listaProdotti3F, listaServizi3F);

		ArrayList<Prodotto> listaProdotti4F = new ArrayList<>();
		listaProdotti4F.add(new Prodotto(102, "Ferro", 946.85, "Ferro1"));
		listaProdotti4F.add(new Prodotto(78, "Cemento", 1482.00, "Cemento1"));
		ArrayList<Servizio> listaServizi4F = new ArrayList<>();
		listaServizi4F.add(new Servizio("Ingegnere", 879.04, 68654514L, "Ingegnere1"));
		listaServizi4F.add(new Servizio("Saldatore", 145.06, 765468L, "Saldatore1"));
		Fornitore fornitore4 = new Fornitore("VUICOWH3", "Fornitore4", 846878L, listaProdotti4F, listaServizi4F);
		fornitore4.addDisponibile(listaProdotti3F.get(0));
		fornitore4.addDisponibile(listaServizi3F.get(0));

		listaFornitori1.add(fornitore1);
		listaFornitori1.add(fornitore2);
		listaFornitori1.add(fornitore3);
		listaFornitori1.add(fornitore4);
		System.out.println(
				"\n--Testing metodo fornitoriSpecificoProdottoOrServizio con un Prodotto con i seguenti parametri--\nfornitori="
						+ listaFornitori1 + "\nprodotto=" + listaProdotti3F.get(0)
						+ "\n-Oggetto ritornato dal metodo-\n"
						+ Report.fornitoriSpecificoProdottoOrServizio(listaFornitori1, listaProdotti3F.get(0)));

		System.out.println(
				"--Testing metodo fornitoriSpecificoProdottoOrServizio con un Servizio con i seguenti parametri--\nfornitori="
						+ listaFornitori1 + "\nprodotto=" + listaServizi3F.get(0) + "\n-Oggetto ritornato dal metodo-\n"
						+ Report.fornitoriSpecificoProdottoOrServizio(listaFornitori1, listaServizi3F.get(0)));
		try {
			listaCantieri1.get(0).addPersonale(listaPersonale1.get(0));
			listaCantieri1.get(3).addPersonale(listaPersonale1.get(7));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}

		System.out.println("\n--Testing metodo personaleNonImpiegato con i seguenti parametri--\npersonale="
				+ listaPersonale1 + "\ncantieri:" + listaCantieri1 + "\n-Oggetto ritornato dal metodo-\n"
				+ Report.personaleNonImpiegato(listaPersonale1, listaCantieri1));
	}

}
