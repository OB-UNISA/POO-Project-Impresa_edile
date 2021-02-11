package impresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import cliente.Cliente;
import exceptions.ItemEsistenteException;
import exceptions.ItemNonEsistenteException;
import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;
import interfacce.Clone;
import personale.Dirigente;
import personale.Personale;
import utilità.Utilità;

/**
 * Classe che definisce l'impresa
 */
public class Impresa implements Cloneable, Serializable {

	private static final long serialVersionUID = -2745852400992786695L;
	/**
	 * Variabile di istanza della partitaIVA dell'impresa
	 */
	private String partitaIVA;
	/**
	 * Variabile di istanza del nome dell'impresa
	 */
	private String nome;
	/**
	 * Variabile di istanza della lista dei fornitori dell'impresa
	 */
	private ArrayList<Fornitore> listaFornitori;
	/**
	 * Variabile di istanza della lista dei prodotti ricevuti dell'impresa
	 */
	private ArrayList<Prodotto> listaProdottiRicevuti;
	/**
	 * Variabile di istanza della lista dei servizi ricevuti dell'impresa
	 */
	private ArrayList<Servizio> listaServiziRicevuti;
	/**
	 * Variabile di istanza della lista del personale dell'impresa
	 */
	private ArrayList<Personale> listaPersonale;
	/**
	 * Variabile di istanza dello stato degli stipendi dell'impresa
	 */
	private Hashtable<Personale, Hashtable<String, Double>> statoStipendi;
	/**
	 * Variabile di istanza della lista dei cantieri dell'impresa
	 */
	private ArrayList<CantiereImpresa> listaCantieri;
	/**
	 * Variabile di istanza della lista dei clienti dell'impresa
	 */
	private ArrayList<Cliente> listaClienti;

	/**
	 * Costruttore per istanziare un oggetto <code>Impresa</code> con campi vuoti
	 */
	public Impresa() {
		partitaIVA = "";
		nome = "";
		listaFornitori = new ArrayList<>();
		listaProdottiRicevuti = new ArrayList<>();
		listaServiziRicevuti = new ArrayList<>();
		listaPersonale = new ArrayList<>();
		statoStipendi = new Hashtable<>();
		listaCantieri = new ArrayList<>();
		listaClienti = new ArrayList<>();
	}

	/**
	 * Costruttore per istanziare un oggetto <code>Impresa</code> con campi non
	 * vuoti, se l'oggetto <code>String</code> passato è <code>null</code>, verrà
	 * assegnata una stringa vuota. Non si rispetta l'incapsulamento nella lista dei
	 * fornitori in quanto si vuole mantenere il dato consistente
	 * 
	 * @param partitaIVA            dell'impresa
	 * @param nome                  dell'impresa
	 * @param listaFornitori        dell'impresa
	 * @param listaProdottiRicevuti dell'impresa
	 * @param listaServiziRicevuti  dell'impresa
	 * @param listaPersonale        dell'impresa
	 * @param statoStipendi         dell'impresa
	 * @param listaCantieri         dell'impresa
	 * @param listaClienti          dell'impresa
	 */
	public Impresa(String partitaIVA, String nome, ArrayList<Fornitore> listaFornitori,
			ArrayList<Prodotto> listaProdottiRicevuti, ArrayList<Servizio> listaServiziRicevuti,
			ArrayList<Personale> listaPersonale, ArrayList<CantiereImpresa> listaCantieri,
			ArrayList<Cliente> listaClienti) {
		this.partitaIVA = (partitaIVA == null ? "" : partitaIVA);
		this.nome = (nome == null ? "" : nome);
		this.listaFornitori = listaFornitori;
		this.listaProdottiRicevuti = Utilità.cloneArray(listaProdottiRicevuti);
		this.listaServiziRicevuti = Utilità.cloneArray(listaServiziRicevuti);
		this.listaPersonale = Utilità.cloneArray(listaPersonale);
		this.statoStipendi = new Hashtable<>();
		for (Personale per : this.listaPersonale) {
			Hashtable<String, Double> stipendio = new Hashtable<>();
			statoStipendi.put(per, stipendio);
		}
		this.listaCantieri = Utilità.cloneArray(listaCantieri);
		this.listaClienti = Utilità.cloneArray(listaClienti);
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è la partitaIVA
	 * dell'impresa
	 * 
	 * @return partitaIVA dell'impresa
	 */
	public String getPartitaIVA() {
		return partitaIVA;
	}

	/**
	 * Restituisce un oggetto <code>String</code> in cui vi è il nome dell'impresa
	 * 
	 * @return nome dell'impresa
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Fornitore&gt;</code> in cui vi è la
	 * lista dei fornitori dell'impresa. Non si rispetta l'incapsulamento in quanto
	 * si vuole mantenere il dato consistente
	 * 
	 * @return listaFornitori dell'impresa
	 */
	public ArrayList<Fornitore> getListaFornitori() {
		return listaFornitori;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Prodotto&gt;</code> in cui vi è la
	 * lista dei prodotti ricevuti dell'impresa
	 * 
	 * @return listaProdottiRicevuti dell'imoresa
	 */
	public ArrayList<Prodotto> getListaProdottiRicevuti() {
		return Utilità.cloneArray(listaProdottiRicevuti);
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Servizio&gt;</code> in cui vi è la
	 * lista dei servizi ricevuti dell'impresa
	 * 
	 * @return listaServiziRicevuti dell'impresa
	 */
	public ArrayList<Servizio> getListaServiziRicevuti() {
		return Utilità.cloneArray(listaServiziRicevuti);
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Personale&gt;</code> in cui vi è la
	 * lista del personale dell'impresa. Non si mantiere l'incapsulamento, in quanto
	 * lo si vuole modificare dall'esterno
	 * 
	 * @return listaPersonale dell'impresa
	 */
	public ArrayList<Personale> getListaPersonale() {
		return listaPersonale;
	}

	/**
	 * Restituisce un oggetto
	 * <code>Hashtable&lt;Personale, Hashtable&lt;String, Double&gt;&gt;</code> in
	 * cui vi è lo stato degli stipendi dell'impresa
	 * 
	 * @return statoStipendi dell'impresa
	 */
	public Hashtable<Personale, Hashtable<String, Double>> getStatoStipendi() {
		return statoStipendi;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;CantiereImpresa&gt;</code> in cui
	 * vi è la lista dei cantieri dell'impresa. Non si mantiere l'incapsulamento, in
	 * quanto lo si vuole modificare dall'esterno
	 * 
	 * @return listaCantieri dell'impresa
	 */
	public ArrayList<CantiereImpresa> getListaCantieri() {
		return listaCantieri;
	}

	/**
	 * Restituisce un oggetto <code>ArrayList&lt;Cliente&gt;</code> in cui vi è la
	 * lista dei clienti dell'impresa. Non si mantiere l'incapsulamento, in quanto
	 * lo si vuole modificare dall'esterno
	 * 
	 * @return listaClienti dell'impresa
	 */
	public ArrayList<Cliente> getListaClienti() {
		return this.listaClienti;
	}

	/**
	 * Assegna o sostituisce la partitaIVA dell'impresa, se l'oggetto
	 * <code>String</code> passato è <code>null</code>, verrà assegnata una stringa
	 * vuota
	 * 
	 * @param partitaIVA dell'impresa
	 */
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = (partitaIVA == null ? "" : partitaIVA);
	}

	/**
	 * Assegna o sostituisce il nome dell'impresa, se l'oggetto <code>String</code>
	 * passato è <code>null</code>, verrà assegnata una stringa vuota
	 * 
	 * @param nome dell'impresa
	 */
	public void setNome(String nome) {
		this.nome = (nome == null ? "" : nome);
	}

	/**
	 * Assegna o sostituisce la lista dei fornitori dell'impresa
	 * 
	 * @param listaFornitori dell'impresa
	 */
	public void setListaFornitori(ArrayList<Fornitore> listaFornitori) {
		this.listaFornitori = Utilità.cloneArray(listaFornitori);
	}

	/**
	 * Assegna o sostituisce la lista dei prodotti ricevuti dell'impresa
	 * 
	 * @param listaProdottiRicevuti dell'impresa
	 */
	public void setListaProdottiRicevuti(ArrayList<Prodotto> listaProdottiRicevuti) {
		this.listaProdottiRicevuti = Utilità.cloneArray(listaProdottiRicevuti);
	}

	/**
	 * Assegna o sostituisce la lista dei servizi ricevuti dell'impresa
	 * 
	 * @param listaServiziRicevuti dell'impresa
	 */
	public void setListaServiziRicevuti(ArrayList<Servizio> listaServiziRicevuti) {
		this.listaServiziRicevuti = Utilità.cloneArray(listaServiziRicevuti);
	}

	/**
	 * Assegna o sostituisce la lista del personale dell'impresa
	 * 
	 * @param listaPersonale dell'impresa
	 */
	public void setListaPersonale(ArrayList<Personale> listaPersonale) {
		this.listaPersonale = Utilità.cloneArray(listaPersonale);
		statoStipendi.clear();
		for (Personale per : this.listaPersonale) {
			Hashtable<String, Double> stipendio = new Hashtable<>();
			statoStipendi.put(per, stipendio);
		}
	}

	/**
	 * Assegna o sostituisce la lista dei cantieri dell'impresa
	 * 
	 * @param listaCantieri dell'impresa
	 */
	public void setListaCantieri(ArrayList<CantiereImpresa> listaCantieri) {
		this.listaCantieri = Utilità.cloneArray(listaCantieri);
	}

	/**
	 * Assegna o sostituisce la lista dei clienti dell'impresa
	 * 
	 * @param listaClienti dell'impresa
	 */
	public void setListaClienti(ArrayList<Cliente> listaClienti) {
		this.listaClienti = Utilità.cloneArray(listaClienti);
	}

	/**
	 * Aggiunge un oggetto compatibile all'impresa
	 * 
	 * @param <T>    una classe che ha implementato l'interfaccia <code>Clone</code>
	 * @param object un oggetto di una delle seguenti classi:
	 *               <code>Fornitore</code>, <code>Prodotto</code>,
	 *               <code>Servizio</code>, <code>Personale</code>,
	 *               <code>CantiereImpresa</code>, <code>Cliente</code>
	 * @throws ItemEsistenteException
	 */
	public <T extends Clone<T>> void addToImpresa(T object) throws ItemEsistenteException {
		if (object != null) {

			if (object.getClass() == Fornitore.class) {
				if (trovaItem(Utilità.rewindCast(object, Fornitore.class).getPartitaIVA(), Fornitore.class) != null)
					throw new ItemEsistenteException("Fornitore già esistente");
				else
					listaFornitori.add((Fornitore) object.clone());
			}

			else if (object.getClass() == Prodotto.class)
				listaProdottiRicevuti.add((Prodotto) object.clone());

			else if (object.getClass() == Servizio.class)
				listaServiziRicevuti.add((Servizio) object.clone());

			else if (object.getClass() == Personale.class) {
				if (trovaItem(Utilità.rewindCast(object, Personale.class).getCfSnn(), Personale.class) != null)
					throw new ItemEsistenteException("Personale già esistente");
				else {
					listaPersonale.add((Personale) object.clone());
					Hashtable<String, Double> stipendio = new Hashtable<>();
					int index = listaPersonale.indexOf(object);
					statoStipendi.put(listaPersonale.get(index), stipendio);
				}
			}

			else if (object.getClass() == Dirigente.class) {
				if (trovaItem(Utilità.rewindCast(object, Dirigente.class).getCfSnn(), Dirigente.class) != null)
					throw new ItemEsistenteException("Dirigente già esistente");
				else {
					listaPersonale.add((Personale) object.clone());
					Hashtable<String, Double> stipendio = new Hashtable<>();
					int index = listaPersonale.indexOf(object);
					statoStipendi.put(listaPersonale.get(index), stipendio);
				}
			}

			else if (object.getClass() == CantiereImpresa.class) {
				if (trovaItem(String.valueOf(Utilità.rewindCast(object, CantiereImpresa.class).getCodice()),
						CantiereImpresa.class) != null)
					throw new ItemEsistenteException("CantiereImpresa già esistente");
				else
					listaCantieri.add((CantiereImpresa) object.clone());
			}

			else if (object.getClass() == Cliente.class) {
				if (trovaItem(Utilità.rewindCast(object, Cliente.class).getCfSnn(), Cliente.class) != null)
					throw new ItemEsistenteException("Cliente già esistente");
				else
					listaClienti.add((Cliente) object.clone());
			}
		}
	}

	/**
	 * Rimuove un oggetto compatibile dall'impresa e lo ritorna, se l'oggetto non è
	 * compatibile oppure non esistente restituisce <code>null</code>
	 * 
	 * @param <T>    una classe che ha implementato l'interfaccia <code>Clone</code>
	 * @param codice un oggetto di una delle seguenti classi:
	 *               <code>Fornitore</code>, <code>Prodotto</code>,
	 *               <code>Servizio</code>, <code>Personale</code>,
	 *               <code>CantiereImpresa</code>, <code>Cliente</code>
	 * @return l'oggetto rimosso, null se l'oggetto non è compatibile
	 * @throws ItemNonEsistenteException
	 */
	public <T extends Clone<T>> T removeFromImpresa(String codice, Class<T> clazz) throws ItemNonEsistenteException {
		if (codice != null) {
			if (clazz == Fornitore.class) {
				Fornitore fornitore = trovaItem(codice, Fornitore.class);
				if (fornitore != null) {
					int index = listaFornitori.indexOf(fornitore);
					T reference = (T) listaFornitori.get(index);
					listaFornitori.remove(index);
					return reference;
				} else
					return null;
			}
			if (clazz == Prodotto.class) {
				Prodotto prodotto = trovaItem(codice, Prodotto.class);
				if (prodotto != null) {
					int index = listaProdottiRicevuti.indexOf(prodotto);
					T reference = (T) listaProdottiRicevuti.get(index);
					listaProdottiRicevuti.remove(index);
					return reference;
				} else
					throw new ItemNonEsistenteException("Prodotto non esistente");
			}
			if (clazz == Servizio.class) {
				Servizio servizio = trovaItem(codice, Servizio.class);
				if (servizio != null) {
					int index = listaServiziRicevuti.indexOf(servizio);
					T reference = (T) listaServiziRicevuti.get(index);
					listaServiziRicevuti.remove(index);
					return reference;
				} else
					throw new ItemNonEsistenteException("Servizio non esistente");
			}
			if (clazz == Personale.class || clazz == Dirigente.class) {
				Personale personale = trovaItem(codice, Personale.class);
				if (personale != null) {
					int index = listaPersonale.indexOf(personale);
					T reference = (T) listaPersonale.get(index);
					statoStipendi.remove(listaPersonale.get(index));
					listaPersonale.remove(index);
					return reference;
				} else
					throw new ItemNonEsistenteException("Personale non esistente");
			}

			if (clazz == CantiereImpresa.class) {
				CantiereImpresa cantiereImpresa = trovaItem(codice, CantiereImpresa.class);
				if (cantiereImpresa != null) {
					int index = listaCantieri.indexOf(cantiereImpresa);
					T reference = (T) listaCantieri.get(index);
					listaCantieri.remove(index);
					return reference;
				} else
					throw new ItemNonEsistenteException("CantiereImpresa non esistente");
			}
			if (clazz == Cliente.class) {
				Cliente cliente = trovaItem(codice, Cliente.class);
				if (cliente != null) {
					int index = listaClienti.indexOf(cliente);
					T reference = (T) listaClienti.get(index);
					listaClienti.remove(index);
					return reference;
				} else
					throw new ItemNonEsistenteException("Cliente non esistente");
			}
		}
		return null;
	}

	/**
	 * Metodo per trovare un oggetto all'interno dell'impresa. Incapsulamento non
	 * mantenuto in quanto per alcuni scopi serve mantenere il codice hash originale
	 * o modificarne il contenuto
	 * 
	 * @param codice dell'oggetto
	 * @return l'oggetto se esiste con quel codice, altrimenti <code>null</code>
	 */
	public <T> T trovaItem(String codice, Class<T> clazz) {
		if (clazz == Personale.class || clazz == Dirigente.class) {
			for (Personale personale : listaPersonale)
				if (personale.getCfSnn().equals(codice))
					return (T) personale;
		} else if (clazz == Prodotto.class) {
			for (Prodotto prodotto : listaProdottiRicevuti)
				if (String.valueOf(prodotto.getCodice()).equals(codice))
					return (T) prodotto;
		} else if (clazz == Servizio.class) {
			for (Servizio servizio : listaServiziRicevuti)
				if (String.valueOf(servizio.getNumeroServizioClienti()).equals(codice))
					return (T) servizio;
		} else if (clazz == Fornitore.class) {
			for (Fornitore fornitore : listaFornitori)
				if (fornitore.getPartitaIVA().equals(codice))
					return (T) fornitore;
		} else if (clazz == CantiereImpresa.class) {
			for (CantiereImpresa cantiereImpresa : listaCantieri)
				if (String.valueOf(cantiereImpresa.getCodice()).equals(codice))
					return (T) cantiereImpresa;
		} else

		if (clazz == Cliente.class)
			for (Cliente cliente : listaClienti)
				if (cliente.getCfSnn().equals(codice))
					return (T) cliente;

		return null;
	}

	/**
	 * Metodo per pagare lo stipendi ad un determinato personale oppure a tutti. Se
	 * l'oggetto <code>Personale</code> passato è null, verrà pagato lo stipendio a
	 * tutti. Lo schema del pagamento sarà l'ultimo parametro, verranno considerati
	 * solamente i primi 4 valori dei varargs e verranno assegnati per le seguenti
	 * professioni nel seguente ordine: Impiegato, Operaio, Quadro, Dirigente
	 * 
	 * @param personale      dell'impresa
	 * @param data           del pagamento
	 * @param doublesAmmount schema di pagamento
	 * @throws ItemNonEsistenteException
	 */
	public void pagaStipendo(Personale personale, String data, Double... doublesAmmount)
			throws ItemNonEsistenteException {
		if (personale == null) {
			for (Personale per : listaPersonale) {
				if (per.getProfessione().equals("Impiegato"))
					statoStipendi.get(per).put(data, doublesAmmount[0]);
				else if (per.getProfessione().equals("Operaio"))
					statoStipendi.get(per).put(data, doublesAmmount[1]);
				else if (per.getProfessione().equals("Quadro"))
					statoStipendi.get(per).put(data, doublesAmmount[2]);
				else if (per.getProfessione().equals("Dirigente"))
					statoStipendi.get(per).put(data, doublesAmmount[3]);
			}
		} else {
			int index = listaPersonale.indexOf(personale);
			if (index != -1) {
				Personale per = listaPersonale.get(index);
				if (per.getProfessione().equals("Impiegato"))
					statoStipendi.get(per).put(data, doublesAmmount[0]);
				else if (per.getProfessione().equals("Operaio"))
					statoStipendi.get(per).put(data, doublesAmmount[1]);
				else if (per.getProfessione().equals("Quadro"))
					statoStipendi.get(per).put(data, doublesAmmount[2]);
				else if (per.getProfessione().equals("Dirigente"))
					statoStipendi.get(per).put(data, doublesAmmount[3]);
			} else
				throw new ItemNonEsistenteException("Personale non esistente");
		}
	}

	/**
	 * Metodo per ottenere un prodotto o servizio dal primo fornitore che lo ha
	 * disponibile, se disponibile verrà aggiunto alla lista dei prodotti o servizi
	 * ricevuti e sarà ritornato, altrimenti sarà ritornato <code>null</code>
	 * 
	 * @param <T>               una classe che ha implementato l'interfaccia
	 *                          <code>Clone</code>
	 * @param prodottoOServizio da ottenere
	 * @return prodottoOServizio ottenuto
	 */
	public <T extends Clone<T>> T getProdottoOrServizioFornitori(T prodottoOServizio) {
		if (prodottoOServizio != null) {
			if (prodottoOServizio.getClass() == Prodotto.class) {
				for (Fornitore forni : listaFornitori) {
					int index = forni.getProdottiDisponibili().indexOf(prodottoOServizio);
					if (index != -1) {
						T reference = (T) forni.getProdottiDisponibili().get(index);
						forni.removeDisponibile(prodottoOServizio);
						try {
							addToImpresa(reference);
						} catch (ItemEsistenteException e) {
							e.printStackTrace();
						}
						return reference;
					}
				}
			} else if (prodottoOServizio.getClass() == Servizio.class) {
				for (Fornitore forni : listaFornitori) {
					int index = forni.getServiziDisponibili().indexOf(prodottoOServizio);
					if (index != -1) {
						T reference = (T) forni.getServiziDisponibili().get(index);
						forni.removeDisponibile(prodottoOServizio);
						try {
							addToImpresa(reference);
						} catch (ItemEsistenteException e) {
							e.printStackTrace();
						}
						return reference;
					}
				}
			}
		}
		return null;
	}

	public String toString() {
		return this.getClass().getName() + "[partitaIVA=" + partitaIVA + ",nome=" + nome + ",listaFornitori="
				+ listaFornitori + ",listaProdottiRicevuti=" + listaProdottiRicevuti + ",listaServiziRicevuti="
				+ listaServiziRicevuti + ",listaPersonale=" + listaPersonale + ",statoStipendi=" + statoStipendi
				+ ",listaCantieri=" + listaCantieri + ",listaClienti=" + listaClienti;
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null || this.getClass() != otherObject.getClass())
			return false;
		Impresa other = (Impresa) otherObject;
		return partitaIVA.equals(other.partitaIVA) && nome.equals(other.nome)
				&& listaFornitori.equals(other.listaFornitori)
				&& listaProdottiRicevuti.equals(other.listaProdottiRicevuti)
				&& listaServiziRicevuti.equals(other.listaServiziRicevuti)
				&& listaPersonale.equals(other.listaPersonale) && listaCantieri.equals(other.listaCantieri)
				&& listaClienti.equals(other.listaClienti);
	}

	public Impresa clone() {
		try {
			Impresa cloned = (Impresa) super.clone();
			cloned.listaFornitori = Utilità.cloneArray(listaFornitori);
			cloned.listaProdottiRicevuti = Utilità.cloneArray(listaProdottiRicevuti);
			cloned.listaServiziRicevuti = Utilità.cloneArray(listaServiziRicevuti);
			cloned.listaPersonale = Utilità.cloneArray(listaPersonale);
			cloned.statoStipendi = statoStipendi;
			cloned.listaCantieri = Utilità.cloneArray(listaCantieri);
			cloned.listaClienti = Utilità.cloneArray(listaClienti);
			return cloned;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
