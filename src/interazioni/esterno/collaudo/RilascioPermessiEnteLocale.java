package interazioni.esterno.collaudo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import entelocale.EnteLocale;
import entelocale.Permesso;
import impresa.CantiereImpresa;

/**
 * Classe per simulare il rilascio dei permessi per i cantieri dell'impresa.
 * Criterio di rilascio dei permessi: <br/>
 * <b>negato</b>: il cantiere non dispone di un reponsabile <br/>
 * <b>lavorazione</b>: il cantiere dispsone di un responsabile, ma manca il
 * caposquadra o il personale <br/>
 * <b>concesso</b>: il cantiere dipsone di un responsabile, di un caposquadra e
 * del personale
 *
 */
public class RilascioPermessiEnteLocale {

	public static void main(String[] args) {

		EnteLocale ente = new EnteLocale();

		File fileI = new File("PermessiRichieste.txt");
		ArrayList<CantiereImpresa> richiestePermesso = new ArrayList<>();
		try (FileInputStream inF = new FileInputStream(fileI); ObjectInputStream inO = new ObjectInputStream(inF);) {
			while (true)
				richiestePermesso.add((CantiereImpresa) inO.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			if (richiestePermesso.size() <= 0)
				System.out.println("Non ci sono richieste");
			else
				System.out.println("Le richieste sono state importate");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (richiestePermesso.size() > 0) {
			File fileO = new File("PermessiRilasciati.txt");
			ArrayList<Permesso> permessiRilasciatiOld = new ArrayList<>();

			try (FileInputStream inR = new FileInputStream(fileO);
					ObjectInputStream objInR = new ObjectInputStream(inR)) {
				while (true)
					permessiRilasciatiOld.add((Permesso) objInR.readObject());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (EOFException e) {
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try (FileOutputStream outF = new FileOutputStream(fileO);
					ObjectOutputStream outO = new ObjectOutputStream(outF)) {
				ArrayList<Permesso> permessiRilasciatiOldClone = new ArrayList<>();
				// neccessario per evitare il lancio dell'eccezione di concorrenza
				for (Permesso permesso : permessiRilasciatiOld)
					permessiRilasciatiOldClone.add(new Permesso(permesso.getCodice(), permesso.getStato()));

				// i cantieri che hanno il responsabile avranno il permesso nello stato di
				// lavorazione,
				// coloro senza responsabile avranno il permesso negato,
				// coloro con responsabile, caposquadra e personale, avranno il permesso
				// concesso
				for (CantiereImpresa cantiereImpresa : richiestePermesso) {
					for (Permesso oldRilasciato : permessiRilasciatiOld) {
						if (oldRilasciato.getCodice() == cantiereImpresa.getCodice())
							permessiRilasciatiOldClone.remove(oldRilasciato);
					}
					if (cantiereImpresa.getResponsabile() == null)
						permessiRilasciatiOldClone.add(ente.setPermessoCantiere(cantiereImpresa, Permesso.NEGATO));
					else if (cantiereImpresa.getResponsabile() != null
							&& (cantiereImpresa.getCaposquadra() == null || cantiereImpresa.getPersonale() == null))
						permessiRilasciatiOldClone.add(ente.setPermessoCantiere(cantiereImpresa, Permesso.LAVORAZIONE));
					else if (cantiereImpresa.getResponsabile() != null && cantiereImpresa.getCaposquadra() != null
							&& cantiereImpresa.getPersonale() != null)
						permessiRilasciatiOldClone.add(ente.setPermessoCantiere(cantiereImpresa, Permesso.CONCESSO));
				}
				for (Permesso rilasciati : permessiRilasciatiOldClone)
					outO.writeObject(rilasciati);
				System.out.println("Permessi rilasciati");
				Path path = Paths.get(fileI.getAbsolutePath());
				Files.deleteIfExists(path);
				Files.createFile(path);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
