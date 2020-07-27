package testers;

import cliente.Cantiere;
import cliente.Cliente;

public class ClienteTester {

	public static void main(String[] args) {

		String cfSnn1 = "ABCD123", nome1 = "Mario", cognome1 = "Rossi";
		long numeroTelefono1 = 43805634086L;
		System.out.println("--Testing costruttore con i seguenti parametri--\ncfSnn=" + cfSnn1 + ",nome=" + nome1
				+ ",cognome=" + cognome1 + ",numeroTelefono=" + numeroTelefono1);
		Cliente cliente1 = new Cliente(cfSnn1, nome1, cognome1, numeroTelefono1);

		System.out
				.println("-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + cliente1.toString());

		Cantiere cantiere1 = new Cantiere(5574, "Milano", 85478L);
		System.out.println("\n--Testing secondo costruttore con i seguenti parametri--\ncfSnn=" + cfSnn1 + ",nome="
				+ nome1 + ",cognome=" + cognome1 + ",numeroTelefono=" + numeroTelefono1 + ",cantiere=" + cantiere1);
		Cliente cliente2 = new Cliente(cfSnn1, nome1, cognome1, numeroTelefono1, cantiere1);
		System.out.println("-Istanza dell'oggetto con il costruttore\n" + cliente2.toString());

		System.out.println("\n--Testing metodi get con gli stessi valori dell'istantazione precedente--\ncfSnn="
				+ cliente2.getCfSnn() + ",nome=" + cliente2.getNome() + ",cognome=" + cliente2.getCognome()
				+ ",numeroTelefono=" + cliente2.getNumeroTelefono() + ",cantiere=" + cliente2.getCantiere());

		String cfSnn2 = "EFGH456", nome2 = "Luigi", cognome2 = "Verdi";
		long numeroTelefono2 = 98752485L;
		Cantiere cantiere2 = new Cantiere(7894, "Genova", 987558L);
		System.out.println("\n--Testing metodi set con i seguenti parametri--\ncfSnn=" + cfSnn2 + ",nome=" + nome2
				+ ",cognome=" + cognome2 + ",numeroTelefono=" + numeroTelefono2 + ",cantiere=" + cantiere2);
		System.out.println("-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + cliente2.toString());
		cliente2.setCfSnn(cfSnn2);
		cliente2.setNome(nome2);
		cliente2.setCognome(cognome2);
		cliente2.setNumeroTelefono(numeroTelefono2);
		cliente2.setCantiere(cantiere2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i metodi set-\n" + cliente2.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + cliente2.equals(cliente2));
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + cliente2.toString() + "\n"
				+ cliente1.toString() + "\n" + cliente2.equals(cliente1));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + cliente2.toString());
		Cliente cliente3 = cliente2.clone();
		System.out.println("-Oggetto clonato-\n" + cliente3.toString());

		Cliente cliente4 = new Cliente();
		System.out.println("\n--Testing metodo controllaCantiereVuoto con il seguente cliente--\n" + cliente4 + "\n"
				+ cliente4.controllaCantiereVuoto());
	}
}
