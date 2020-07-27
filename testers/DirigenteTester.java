package testers;

import exceptions.NumeroMassimoCantieriException;
import personale.Dirigente;

public class DirigenteTester {

	public static void main(String[] args) {

		String cfSnn1 = "ABC123", nome1 = "Mario", cognome1 = "Rossi";
		int numeroMassimoCantieri1 = 4, numeroAttualeCantieri1 = 2;
		System.out.println("--Testing costruttore con i seguenti parametri--\ncfSnn=" + cfSnn1 + ",nome=" + nome1
				+ ",cognome=" + cognome1 + ",numeroMassimoCantieri=" + numeroMassimoCantieri1
				+ ",numeroAttualeCantieri=" + numeroAttualeCantieri1);
		Dirigente dirigente1 = new Dirigente(cfSnn1, nome1, cognome1, numeroMassimoCantieri1, numeroAttualeCantieri1);
		System.out.println(
				"-Istanza dell'oggetto con il costruttore + testing metodo toString-\n" + dirigente1.toString());

		System.out.println("\n--Testing metodi get con i valori dell'istanza precedente--\nnumeroMassimoCantieri="
				+ dirigente1.getNumeroMassimoCantieri() + ",numeroAttualeCantieri="
				+ dirigente1.getNumeroAttualeCantieri());

		int numeroMassimoCantieri2 = 6, numeroAttualeCantieri2 = 3;
		;
		System.out.println("\n--Testing metodi set con i seguenti parametri--\nnumeroMassimoCantieri="
				+ numeroMassimoCantieri2 + ",numeroAttualeCantieri=" + numeroAttualeCantieri2
				+ "\n-Valore dei campi dell'oggetto prima di invocare i metodi set-\n" + dirigente1.toString());
		dirigente1.setNumeroMassimoCantieri(numeroMassimoCantieri2);
		dirigente1.setNumeroAttualeCantieri(numeroAttualeCantieri2);
		System.out.println("-Valore dei campi dell'oggetto dopo aver invocato i metodi set-\n" + dirigente1.toString());

		System.out.println(
				"\n--Testing metodo equals confrontandolo l'oggetto con se stesso--\n" + dirigente1.equals(dirigente1));
		Dirigente dirigente2 = new Dirigente(cfSnn1, nome1, cognome1, numeroMassimoCantieri1);
		System.out.println("--Testing metodo equals con i seguenti oggetti--\n" + dirigente1.toString() + "\n"
				+ dirigente2.toString() + "\n" + dirigente1.equals(dirigente2));

		System.out.println("\n--Testing metodo clone, clonando il seguente oggetto--\n" + dirigente1.toString());
		Dirigente dirigente3 = dirigente1.clone();
		System.out.println("-Oggetto clonato-\n" + dirigente3.toString());

		System.out.println(
				"\n--Testing lancio dell'eccezione NumeroMassimoCantieriException, se è lanciata verrà stampato il StackTrace--");
		try {
			dirigente3.setNumeroAttualeCantieri(dirigente3.getNumeroMassimoCantieri() + 1);
		} catch (NumeroMassimoCantieriException e) {
			e.printStackTrace();
		}
	}
}
