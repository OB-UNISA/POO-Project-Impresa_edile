package report;

import java.util.ArrayList;
import java.util.Comparator;

import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;
import impresa.CantiereImpresa;
import interfacce.Clone;
import personale.Dirigente;
import personale.Personale;
import utilità.Utilità;

/**
 * Classe che definisce il report
 */
public class Report {

	/**
	 * Metodo per ordinare un array dal minore al maggiore
	 * 
	 * @param <T>        classe che ha implementato l'interfaccia <code>Clone</code>
	 * @param array      da ordinare
	 * @param comparator criterio di ordine
	 * @return array ordinato
	 */
	public static <T extends Clone<T>> ArrayList<T> sort(ArrayList<T> array, Comparator<T> comparator) {
		if (array.size() > 0) {
			ArrayList<T> arraySorted = new ArrayList<>();
			ArrayList<T> arrayCopied = Utilità.cloneArray(array);
			T minimum = arrayCopied.get(0);
			for (; arrayCopied.size() > 0;) {
				for (T element : arrayCopied)
					if (comparator.compare(element, minimum) < 0)
						minimum = element;
				arraySorted.add(minimum);
				arrayCopied.remove(minimum);
				if (arrayCopied.size() > 0)
					minimum = arrayCopied.get(0);
			}
			return arraySorted;
		} else
			return array;
	}

	/**
	 * Metodo per trovare nell'array solo gli elementi che rispettino i due criteri
	 * di selezione
	 * 
	 * @param <T>
	 * @param array      da cui trovare gli elementi
	 * @param comparator criterio di selezione
	 * @param min        oggetto che rapresenta il minimo
	 * @param max        oggetto che rappresenta il massimo
	 * @return array con gli oggetti che rispettino il criterio oppure
	 *         <code>null</code> se nessun oggetto è stato trovato
	 */
	public static <T> ArrayList<T> valoriCompresi(ArrayList<T> array, T min, T max, Comparator<T> comparator) {
		ArrayList<T> arrayRisultato = new ArrayList<>();
		for (T element : array)
			if ((comparator.compare(element, max) < 0) && (comparator.compare(element, min) > 0))
				arrayRisultato.add(element);
		return (arrayRisultato.size() == 0 ? null : arrayRisultato);
	}

	/**
	 * Metodo per trovare nell'array i fornitori che possiedono un determinato
	 * prodotto o servizio
	 * 
	 * @param <F>
	 * @param array  di fornitori
	 * @param valore è un oggetto <code>Prodotto</code> oppure <code>Servizio</code>
	 * @return array con i fornitori che possidono il prodotto o servizio
	 */
	public static <F> ArrayList<Fornitore> fornitoriSpecificoProdottoOrServizio(ArrayList<Fornitore> array, F valore) {
		ArrayList<Fornitore> arrayRisultato = new ArrayList<>();
		if (valore != null) {
			if (valore.getClass() == Prodotto.class) {
				for (Fornitore fornit : array)
					for (Prodotto pro : fornit.getProdottiDisponibili())
						if (pro.getNome().equalsIgnoreCase(Utilità.rewindCast(valore, Prodotto.class).getNome()))
							arrayRisultato.add(fornit);
				return (arrayRisultato.size() == 0 ? null : arrayRisultato);
			}
			if (valore.getClass() == Servizio.class) {
				for (Fornitore fornit : array)
					for (Servizio serv : fornit.getServiziDisponibili())
						if (serv.getNome().equalsIgnoreCase(Utilità.rewindCast(valore, Servizio.class).getNome()))
							arrayRisultato.add(fornit);
				return (arrayRisultato.size() == 0 ? null : arrayRisultato);
			}
		}
		return null;
	}

	/**
	 * Metodo per trovare il personale non impiegato in alcun cantiere, vengono
	 * esclusi gli impiegati
	 * 
	 * @param arrayPersonale lista di tutto il personale
	 * @param arrayCantieri  lista dei cantieri
	 * @return array con il personale non impiegato
	 */
	public static ArrayList<Personale> personaleNonImpiegato(ArrayList<Personale> arrayPersonale,
			ArrayList<CantiereImpresa> arrayCantieri) {
		ArrayList<Personale> personaleNonImpiegato = new ArrayList<>();
		if (arrayPersonale != null && arrayCantieri != null) {
			for (Personale per : arrayPersonale) {
				if (!per.getProfessione().equals("Impiegato") && !per.getProfessione().equals("Dirigente")) {
					boolean nonImpiegato = true;
					for (CantiereImpresa cantiere : arrayCantieri)
						if (cantiere.getPersonale() != null && cantiere.getPersonale().contains(per))
							nonImpiegato = false;
					if (nonImpiegato)
						personaleNonImpiegato.add(per);
				}
				if (per.getProfessione().equals("Dirigente")) {
					Dirigente dirigente = Utilità.rewindCast(per, Dirigente.class);
					if (dirigente != null) {
						if (dirigente.getNumeroAttualeCantieri() < dirigente.getNumeroMassimoCantieri())
							personaleNonImpiegato.add(per);
					}
				}
			}
			return (personaleNonImpiegato.size() == 0 ? null : personaleNonImpiegato);

		}
		return null;
	}

}
