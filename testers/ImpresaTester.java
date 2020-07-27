package testers;

import java.util.ArrayList;

import cliente.Cantiere;
import cliente.Cliente;
import exceptions.ItemEsistenteException;
import exceptions.ItemNonEsistenteException;
import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;
import impresa.CantiereImpresa;
import impresa.Impresa;
import personale.Dirigente;
import personale.Personale;

public class ImpresaTester {

	public static void main(String[] args) {

		String partitaIVA1I = "ABC123", nome1I = "Edili";
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

		ArrayList<Personale> listaPersonale1 = new ArrayList<>();
		listaPersonale1.add(new Dirigente("UY89W3YFC", "Mario", "Rossi", 8));
		listaPersonale1.add(new Personale("ADSJO343", "Luigi", "Verdi", "Impiegato"));
		listaPersonale1.add(new Personale("LAWOIDN3R", "Marco", "Bruni", "Quadro"));
		listaPersonale1.add(new Dirigente("GFW698YEWHEC8", "Franco", "Ciani", 6));

		ArrayList<Cliente> listaCliente1 = new ArrayList<>();
		listaCliente1.add(
				new Cliente("UIG34U5R", "Cliente1", "Cliente12", 7687687L, new Cantiere(78545, "Milano", 796649L)));
		listaCliente1.add(
				new Cliente("BASUHD3049", "Cliente2", "Cliente23", 8754368L, new Cantiere(5872, "Genova", 24616L)));

		ArrayList<CantiereImpresa> listaCantieri1 = new ArrayList<>();
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(0).getCantiere()));
		listaCantieri1.add(new CantiereImpresa(listaCliente1.get(1).getCantiere()));
		System.out.println("--Testing costruttore con i seguenti parametri--\npartitaIVA=" + partitaIVA1I + ",nome="
				+ nome1I + "\n,listaFornitori=" + listaFornitori1 + "\n,listaProdottiRicevuti=" + listaProdotti1F
				+ "\n,listaServiziRicevuti=" + listaServizi2F + "\n,listaPersonale=" + listaPersonale1
				+ "\n,listaCantieri=" + listaCantieri1 + "\n,listaClienti=" + listaCliente1);

		Impresa impresa1 = new Impresa(partitaIVA1I, nome1I, listaFornitori1, listaProdotti1F, listaServizi2F,
				listaPersonale1, listaCantieri1, listaCliente1);
		System.out
				.println("-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + impresa1.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\npartitaIVA="
				+ impresa1.getPartitaIVA() + ",nome=" + impresa1.getNome() + "\n,listaFornitori="
				+ impresa1.getListaFornitori() + "\n,listaProdottiRicevuti=" + impresa1.getListaProdottiRicevuti()
				+ "\n,listaServiziRicevuti=" + impresa1.getListaServiziRicevuti() + "\n,listaPersonale="
				+ impresa1.getListaPersonale() + "\n,statoStipendi=" + impresa1.getStatoStipendi() + "\n,listaCantieri="
				+ impresa1.getListaCantieri() + "\n,listaClienti=" + impresa1.getListaClienti());

		String partitaIVA2I = "DEF456", nome2I = "Edili2";
		ArrayList<Fornitore> listaFornitori2 = new ArrayList<>();

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

		listaFornitori2.add(fornitore3);
		listaFornitori2.add(fornitore4);

		ArrayList<Personale> listaPersonale2 = new ArrayList<>();
		listaPersonale2.add(new Personale("WXQDOWE", "Jonh", "Red", "Operaio"));
		listaPersonale2.add(new Personale("32FCJEK", "Louis", "Green", "Impiegato"));
		listaPersonale2.add(new Personale("SI21E09UDJC", "Carl", "Brown", "Quadro"));
		listaPersonale2.add(new Dirigente("Q24E9UD3CO", "Frank", "Cyan", 7));

		ArrayList<Cliente> listaCliente2 = new ArrayList<>();
		listaCliente2.add(new Cliente("BWDO34", "Cliente3", "Cliente32", 8752465L, new Cantiere(5945, "Roma", 66546L)));
		listaCliente2
				.add(new Cliente("LJR23IFN", "Cliente4", "Cliente43", 7687L, new Cantiere(785752, "Rimini", 16876L)));

		ArrayList<CantiereImpresa> listaCantieri2 = new ArrayList<>();
		listaCantieri2.add(new CantiereImpresa(listaCliente2.get(0).getCantiere()));
		listaCantieri2.add(new CantiereImpresa(listaCliente2.get(1).getCantiere()));

		System.out.println("\n--Testing metodi set con i seguenti parametri--\npartitaIVA=" + partitaIVA2I + ",nome="
				+ nome2I + "\n,listaFornitori=" + listaFornitori2 + "\n,listaProdottiRicevuti=" + listaProdotti3F
				+ "\n,listaServiziRicevuti=" + listaServizi4F + "\n,listaPersonale=" + listaPersonale2
				+ "\n,listaCantieri=" + listaCantieri2 + "\n,listaClienti=" + listaCliente2);
		System.out.println("-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + impresa1.toString());
		impresa1.setPartitaIVA(partitaIVA2I);
		impresa1.setNome(nome2I);
		impresa1.setListaFornitori(listaFornitori2);
		impresa1.setListaProdottiRicevuti(listaProdotti3F);
		impresa1.setListaServiziRicevuti(listaServizi4F);
		impresa1.setListaPersonale(listaPersonale2);
		impresa1.setListaCantieri(listaCantieri2);
		impresa1.setListaClienti(listaCliente2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i metodi set-\n" + impresa1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + impresa1.equals(impresa1));
		Impresa impresa2 = new Impresa(partitaIVA1I, nome1I, listaFornitori1, listaProdotti1F, listaServizi2F,
				listaPersonale1, listaCantieri1, listaCliente1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + impresa2.toString() + "\n"
				+ impresa1.toString() + "\n" + impresa2.equals(impresa1));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + impresa2.toString());
		Impresa impresa3 = impresa2.clone();
		System.out.println("-Oggetto clonato-\n" + impresa3.toString());

		System.out.println("\n--Testing metodo trovaItem con il seguente parametro--\ncodice="
				+ listaPersonale1.get(0).getCfSnn() + "\n-Oggetto ritornato dal metodo-\n"
				+ impresa2.trovaItem(listaPersonale1.get(0).getCfSnn(), Personale.class));

		System.out.println("\n--Testing metodo addToImpresa con un Fornitore con il seguente parametro--\n" + fornitore3
				+ "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaFornitori());
		try {
			impresa2.addToImpresa(fornitore3);
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaFornitori());
		System.out.println(
				"--Testing metodo addToImpresa con un Prodotto con il seguente parametro--\n" + listaProdotti4F.get(0)
						+ "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaProdottiRicevuti());
		try {
			impresa2.addToImpresa(listaProdotti4F.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaProdottiRicevuti());
		System.out.println(
				"--Testing metodo addToImpresa con un Servizio con il seguente parametro--\n" + listaServizi3F.get(0)
						+ "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaServiziRicevuti());
		try {
			impresa2.addToImpresa(listaServizi3F.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaServiziRicevuti());
		System.out.println(
				"--Testing metodo addToImpresa con un Personale con il seguente parametro--\n" + listaPersonale2.get(0)
						+ "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaPersonale());
		try {
			impresa2.addToImpresa(listaPersonale2.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaPersonale());
		System.out.println("--Testing metodo addToImpresa con un CantiereImpresa con il seguente parametro--\n"
				+ listaCantieri2.get(0) + "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaCantieri());
		try {
			impresa2.addToImpresa(listaCantieri2.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaCantieri());
		System.out.println("--Testing metodo addToImpresa con un Cliente con il seguente parametro--\n"
				+ listaCliente2.get(0) + "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaClienti());
		try {
			impresa2.addToImpresa(listaCliente2.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Array dopo aver invocato il metodo-\n" + impresa2.getListaClienti());

		System.out.println("\n--Testing lancio eccezione ItemEsistente, se è lanciata verrà stampato il StackTrace--");
		try {
			impresa2.addToImpresa(listaPersonale1.get(0));
		} catch (ItemEsistenteException e) {
			e.printStackTrace();
		}

		System.out.println("\n--Testing metodo removeFromImpresa con un Fornitore con il seguente parametro--\n"
				+ fornitore3 + "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaFornitori());
		try {
			Fornitore removed = impresa2.removeFromImpresa(fornitore3.getPartitaIVA(), Fornitore.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
					+ impresa2.getListaFornitori() + "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("--Testing metodo removeFromImpresa con un Prodotto con il seguente parametro--\n"
				+ listaProdotti4F.get(0) + "\n-Array prima di aver invocato il metodo-\n"
				+ impresa2.getListaProdottiRicevuti());
		try {
			Prodotto removed = impresa2.removeFromImpresa(String.valueOf(listaProdotti4F.get(0).getCodice()),
					Prodotto.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
					+ impresa2.getListaProdottiRicevuti() + "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("--Testing metodo removeFromImpresa con un Servizio con il seguente parametro--\n"
				+ listaServizi3F.get(0) + "\n-Array prima di aver invocato il metodo-\n"
				+ impresa2.getListaServiziRicevuti());
		try {
			Servizio removed = impresa2.removeFromImpresa(
					String.valueOf(listaServizi3F.get(0).getNumeroServizioClienti()), Servizio.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
					+ impresa2.getListaServiziRicevuti() + "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("--Testing metodo removeFromImpresa con un Personale con il seguente parametro--\n"
				+ listaPersonale1.get(0) + "\n-Array prima di aver invocato il metodo-\n"
				+ impresa2.getListaPersonale());
		try {
			Personale removed = impresa2.removeFromImpresa(listaPersonale1.get(0).getCfSnn(), Personale.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
					+ impresa2.getListaPersonale() + "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("--Testing metodo removeFromImpresa con un CantiereImpresa con il seguente parametro--\n"
				+ listaCantieri2.get(0) + "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaCantieri());
		try {
			CantiereImpresa removed = impresa2.removeFromImpresa(String.valueOf(listaCantieri2.get(0).getCodice()),
					CantiereImpresa.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
					+ impresa2.getListaCantieri() + "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("--Testing metodo removeFromImpresa con un Cliente con il seguente parametro--\n"
				+ listaCliente2.get(0) + "\n-Array prima di aver invocato il metodo-\n" + impresa2.getListaClienti());
		try {
			Cliente removed = impresa2.removeFromImpresa(listaCliente2.get(0).getCfSnn(), Cliente.class);
			System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n" + impresa2.getListaClienti()
					+ "\n" + removed);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out
				.println("\n--Testing lancio eccezione ItemNonEsistente, se è lanciata verrà stampato il StackTrace--");
		try {
			impresa2.removeFromImpresa(listaPersonale2.get(1).getCfSnn(), Personale.class);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}

		System.out.println("\n--Testing medoto pagaStipendio con il seguente parametro--\n" + listaPersonale1.get(1)
				+ "\n-Valore stipendi prima di invocare il metodo-\n" + impresa2.getStatoStipendi());
		try {
			impresa2.pagaStipendo(listaPersonale1.get(1), "31/01/2019", 1700.0, 1800.0, 1900.0, 2000.0);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		try {
			impresa2.addToImpresa(new Personale("UDHC", "Pippo", "Sec", "Operaio"));
		} catch (ItemEsistenteException e1) {
			e1.printStackTrace();
		}
		System.out.println("-Valore stipendi dopo aver invocato il metodo-\n" + impresa2.getStatoStipendi());
		System.out.println("--Testing medoto pagaStipendio con il seguente parametro--\n" + null
				+ "\n-Valore stipendi prima di invocare il metodo-\n" + impresa2.getStatoStipendi());
		try {
			impresa2.pagaStipendo(null, "31/01/2019", 1700.0, 1800.0, 1900.0, 2000.0);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}
		System.out.println("-Valore stipendi dopo aver invocato il metodo-\n" + impresa2.getStatoStipendi());
		System.out.println(
				"\n--Testing lancio eccezione ItemNonEsistenteException, se è lanciata verrà stampato il StackTrace--");
		try {
			impresa2.pagaStipendo(listaPersonale2.get(3), "31/01/2019", 1700.0, 1800.0, 1900.0, 2000.0);
		} catch (ItemNonEsistenteException e) {
			e.printStackTrace();
		}

		System.out.println("\n--Testing metodo getProdottoOrServizio con un Prodotto con il seguente parametro--\n"
				+ fornitore1.getProdottiDisponibili().get(0));
		System.out.println("-Array prima di invocare il metodo-\n" + impresa2.getListaProdottiRicevuti() + "\n"
				+ fornitore1.getProdottiDisponibili());
		Prodotto returned1 = impresa2.getProdottoOrServizioFornitori(fornitore1.getProdottiDisponibili().get(0));
		System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
				+ impresa2.getListaProdottiRicevuti() + "\n" + fornitore1.getProdottiDisponibili() + "\n" + returned1);
		System.out.println("--Testing metodo getProdottoOrServizio con un Servizio con il seguente parametro--\n"
				+ fornitore1.getServiziDisponibili().get(0));
		System.out.println("-Array prima di invocare il metodo-\n" + impresa2.getListaServiziRicevuti() + "\n"
				+ fornitore1.getServiziDisponibili());
		Servizio returned2 = impresa2.getProdottoOrServizioFornitori(fornitore1.getServiziDisponibili().get(0));
		System.out.println("-Array dopo aver invocato il metodo + oggetto ritornato-\n"
				+ impresa2.getListaServiziRicevuti() + "\n" + fornitore1.getServiziDisponibili() + "\n" + returned2);

	}
}
