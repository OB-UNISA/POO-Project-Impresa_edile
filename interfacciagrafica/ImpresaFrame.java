
package interfacciagrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import cliente.Cantiere;
import cliente.Cliente;
import entelocale.Permesso;
import exceptions.ItemEsistenteException;
import exceptions.ItemNonEsistenteException;
import exceptions.NumeroMassimoCantieriException;
import exceptions.PermessoEnteNegatoException;
import exceptions.PermessoEnteNonRilasciatoException;
import exceptions.ResponsabileException;
import fornitore.Fornitore;
import fornitore.Prodotto;
import fornitore.Servizio;
import impresa.CantiereImpresa;
import impresa.Impresa;
import personale.Dirigente;
import personale.Personale;
import report.Report;
import utilità.Utilità;

/**
 * Classe che definile l'interfaccia per l'impresa edile
 */
public class ImpresaFrame extends JFrame {

	private static final long serialVersionUID = -737794913017415653L;
	private static final int WIDTH = 1100;
	private static final int HEIGHT = 650;
	private JTextArea textArea;
	private JPanel panel;
	private JTextArea msgSistema;
	private Color buttonsColor1 = new Color(149, 179, 215);
	private Color buttonsColor2 = new Color(149, 179, 230);
	private ActionListener autoScrollBarV;
	private MouseListener erroreFieldListener;
	private GridLayout gridLayout;
	private Impresa impresa;
	private String nomeFileImpresa;
	private String professione[] = { "Impiegato", "Operaio", "Quadro", "Dirigente" };
	private String etichettePersonale[] = { "COGNOME", "NOME", "CFSSN", "PROFESSIONE", "CANTIERI ATTUALI",
			"CANTIERI MASSIMI" };

	/**
	 * Costruttore dell'interfaccia
	 * 
	 * @param string nome dell'interfaccia
	 */
	public ImpresaFrame(String string) {

		nomeFileImpresa = "Impresa.txt";
		setTitle(string);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder());
		textArea.setEditable(false);
		panel = new JPanel();
		gridLayout = new GridLayout();
		panel.setLayout(gridLayout);

		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(WIDTH - WIDTH / 3, HEIGHT));
		JScrollBar scrollBarV = scrollPane.getVerticalScrollBar();
		autoScrollBarV = (ActionEvent eventAutoScrollBarV) -> scrollBarV.setValue(scrollBarV.getMaximum());

		erroreFieldListener = new ErroreFieldListener();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(217, 217, 217));
		setJMenuBar(menuBar);
		menuBar.add(gestioneInternoMenu());
		menuBar.add(gestioneEsternoMenu());
		menuBar.add(reportMenu());

		addComponentListener(new ComponentListener() {

			public void componentShown(ComponentEvent e) {

			}

			public void componentResized(ComponentEvent e) {
				panel.setPreferredSize(new Dimension(getWidth() / 3, HEIGHT));
				panel.updateUI();
			}

			public void componentMoved(ComponentEvent e) {

			}

			public void componentHidden(ComponentEvent e) {

			}
		});

		msgSistema = new JTextArea(
				"Benvenuto, usa i menù sovrastanti per eseguire le operazioni desiderate. Per uscire premere sulla X in alto a destra");
		msgSistema.setEditable(false);
		msgSistema.setFont(new Font(msgSistema.getFont().getFontName(), Font.BOLD, msgSistema.getFont().getSize()));
		msgSistema.setBorder(new EtchedBorder());

		add(scrollPane, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
		add(panel, BorderLayout.EAST);
		add(msgSistema, BorderLayout.NORTH);

		aperturaAndChiusura();
	}

	private JMenu gestioneInternoMenu() {

		JMenu menu = new JMenu("Gestione Interno");

		int columns = 15;

		menu.add(itemMenu("Gestione Personale", (ActionEvent eventGetsionePersonale) -> {

			JButton assunzioni = new JButton("Assumi");
			assunzioni.setBackground(buttonsColor1);
			assunzioni.setToolTipText("Apre l'interfaccia per assumere il personale");

			JButton licenziamenti = new JButton("Licenzia");
			licenziamenti.setToolTipText("Apre l'interfaccia per licenziare il personale");
			licenziamenti.setBackground(buttonsColor1);

			JButton stipendi = new JButton("Paga stipendio");
			stipendi.setToolTipText("Apre l'interfaccia per pagare gli stipendi");
			stipendi.setBackground(buttonsColor1);

			JButton stipendioTutti = new JButton("Paga stipendio a tutti");
			stipendioTutti.setToolTipText("Paga lo stipendio a tutto il personale");
			stipendioTutti.setBackground(buttonsColor2);

			JButton stipendioTuttiCantiere = new JButton("Paga stipendio personale sul cantiere");
			stipendioTuttiCantiere.setToolTipText("Paga lo stipendio a tutto il personale del cantiere");
			stipendioTuttiCantiere.setBackground(buttonsColor2);

			JPanel panelStipendio = new JPanel();
			panelStipendio.setLayout(new BorderLayout());
			panelStipendio.add(stipendi, BorderLayout.CENTER);
			JPanel panelStip = new JPanel(new GridLayout(2, 1));
			panelStip.add(stipendioTuttiCantiere);
			panelStip.add(stipendioTutti);
			panelStipendio.add(panelStip, BorderLayout.SOUTH);

			JButton annullaButton = new JButton("Annulla");
			annullaButton.setBackground(buttonsColor1);
			annullaButton.setToolTipText("Torna alla schermata iniziale");
			annullaButton.addActionListener((ActionEvent eventAnullaButton) -> {
				panel.removeAll();
				gridLayout.setRows(3);
				gridLayout.setColumns(1);
				panel.add(assunzioni);
				panel.add(licenziamenti);
				panel.add(panelStipendio);
				panel.updateUI();
			});

			assunzioni.addActionListener((ActionEvent eventAssunzioni) -> {

				JLabel cfSnnLabel = new JLabel("CF/SNN");
				JTextField cfSnnField = new JTextField(columns);
				cfSnnField.addMouseListener(erroreFieldListener);
				cfSnnField.setToolTipText("Codice Fiscale/Social Security Number del nuovo personale");

				JLabel nomeLabel = new JLabel("Nome");
				JTextField nomeField = new JTextField(columns);
				nomeField.setToolTipText("Nome del nuovo personale");
				nomeField.addMouseListener(erroreFieldListener);

				JLabel cognomeLabel = new JLabel("Cognome");
				JTextField cognomeField = new JTextField(columns);
				cognomeField.setToolTipText("Cognome del nuovo personale");
				cognomeField.addMouseListener(erroreFieldListener);

				JLabel numeroCantieriMaxDirigenteLabel = new JLabel("Numero max cantieri");
				JSlider numeroCantieriMaxDirigenteSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
				numeroCantieriMaxDirigenteSlider.setToolTipText(
						"Numero massimo dei cantieri nei quali il dirigente può ricomprire la figura del responsabbile");
				numeroCantieriMaxDirigenteSlider.setMajorTickSpacing(1);
				numeroCantieriMaxDirigenteSlider.setPaintTicks(true);
				numeroCantieriMaxDirigenteSlider.setPaintLabels(true);
				numeroCantieriMaxDirigenteSlider.setPaintTrack(true);

				JLabel professioneLabel = new JLabel("Professione");
				String[] professioni = { "Impiegato", "Operaio", "Quadro", "Dirigente" };
				JComboBox<String> professioneComboBox = new JComboBox<>(professioni);
				professioneComboBox.setToolTipText("Professione del nuovo personale");
				professioneComboBox.setEditable(false);
				professioneComboBox.setBackground(buttonsColor1);
				professioneComboBox.addActionListener((ActionEvent eventProfessioneComboBox) -> {
					if (professioneComboBox.getSelectedIndex() == 3) {
						gridLayout.setRows(6);
						gridLayout.setColumns(2);
						panel.add(numeroCantieriMaxDirigenteLabel, 8);
						panel.add(numeroCantieriMaxDirigenteSlider, 9);
						panel.updateUI();
					} else {
						gridLayout.setRows(5);
						gridLayout.setColumns(2);
						panel.remove(numeroCantieriMaxDirigenteLabel);
						panel.remove(numeroCantieriMaxDirigenteSlider);
						panel.updateUI();
					}
				});

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Registra il nuovo personale");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!nomeField.getText().isEmpty() && !cognomeField.getText().isEmpty()
							&& !Utilità.rewindCast(professioneComboBox.getSelectedItem(), String.class).isEmpty()
							&& !cfSnnField.getText().isEmpty()) {

						try {
							if (Utilità.rewindCast(professioneComboBox.getSelectedItem(), String.class)
									.equals("Dirigente")) {
								Dirigente dirigente = new Dirigente(cfSnnField.getText(), nomeField.getText(),
										cognomeField.getText(), numeroCantieriMaxDirigenteSlider.getValue());
								impresa.addToImpresa(dirigente);
								inviaButton.setBackground(Color.GREEN);
								timerColor.start();
								msgSistema.setText(statoUscita(0) + "Personale aggiunto");
								nomeField.setText("");
								cognomeField.setText("");
								cfSnnField.setText("");

							} else {
								Personale personale = new Personale(cfSnnField.getText(), nomeField.getText(),
										cognomeField.getText(),
										Utilità.rewindCast(professioneComboBox.getSelectedItem(), String.class));
								impresa.addToImpresa(personale);
								inviaButton.setBackground(Color.GREEN);
								timerColor.start();
								msgSistema.setText(statoUscita(0) + "Personale aggiunto");
								nomeField.setText("");
								cognomeField.setText("");
								cfSnnField.setText("");
							}
						} catch (ItemEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Personale già esistente");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (nomeField.getText().isEmpty())
							nomeField.setBackground(Color.RED);
						if (cognomeField.getText().isEmpty())
							cognomeField.setBackground(Color.RED);
						if (cfSnnField.getText().isEmpty())
							cfSnnField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(5);
				gridLayout.setColumns(2);
				panel.add(cfSnnLabel);
				panel.add(cfSnnField);
				panel.add(nomeLabel);
				panel.add(nomeField);
				panel.add(cognomeLabel);
				panel.add(cognomeField);
				panel.add(professioneLabel);
				panel.add(professioneComboBox);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			licenziamenti.addActionListener((ActionEvent eventLicenziamenti) -> {

				JLabel cfSnnLabel = new JLabel("CF/SNN");
				JTextField cfSnnField = new JTextField(columns);
				cfSnnField.addMouseListener(erroreFieldListener);
				cfSnnField.setToolTipText("Codice Fiscale/Social Security Number del personale da licenziare");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Licenzia il personale");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!cfSnnField.getText().isEmpty()) {
						try {
							ArrayList<Personale> personaleNonImpiegato = Report
									.personaleNonImpiegato(impresa.getListaPersonale(), impresa.getListaCantieri());
							Personale personale = impresa.trovaItem(cfSnnField.getText(), Personale.class);
							if (personale == null)
								throw new ItemNonEsistenteException();
							if (personale.getProfessione().equals("Impiegato"))
								;
							else {
								if (personaleNonImpiegato == null || !personaleNonImpiegato
										.contains(impresa.trovaItem(cfSnnField.getText(), Personale.class)))
									throw new ItemEsistenteException();
							}
							Dirigente dirigente = Utilità.rewindCast(personale, Dirigente.class);
							if (dirigente != null)
								if (dirigente.getNumeroAttualeCantieri() > 0)
									throw new ItemEsistenteException();

							impresa.removeFromImpresa(cfSnnField.getText(), Personale.class);
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Personale licenziato");

							cfSnnField.setText("");
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Personale non esistente");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemEsistenteException e) {
							msgSistema.setText(statoUscita(1)
									+ "Personale impiegato su un cantiere, rimuoverlo dal cantiere prima di licenziarlo");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						cfSnnField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(cfSnnLabel);
				panel.add(cfSnnField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			stipendi.addActionListener((ActionEvent event) -> {

				JLabel cfSnnLabel = new JLabel("CF/SNN");
				JTextField cfSnnField = new JTextField(columns);
				cfSnnField.addMouseListener(erroreFieldListener);
				cfSnnField.setToolTipText(
						"Codice Fiscale/Social Security Number del personale a cui pagare lo stipendio");

				JLabel importoLabel = new JLabel("Importo");
				JTextField importoField = new JTextField(columns);
				importoField.addMouseListener(erroreFieldListener);
				importoField.setToolTipText("Importo dello stipendio");

				JLabel dataLabel = new JLabel("Data");
				JTextField dataField = new JTextField(columns);
				dataField.addMouseListener(erroreFieldListener);
				dataField.setToolTipText("Data del pagamento dello stipendio");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Licenzia il personale");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!cfSnnField.getText().isEmpty() && !importoField.getText().isEmpty()) {
						try {

							double importo = Double.parseDouble(importoField.getText());

							Personale personale = impresa.trovaItem(cfSnnField.getText(), Personale.class);
							if (personale == null)
								throw new ItemNonEsistenteException();

							impresa.pagaStipendo(personale, dataField.getText(), importo, importo, importo, importo);
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Personale pagato");
							cfSnnField.setText("");
							dataField.setText("");
							importoField.setText("");
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Personale non esistente");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Formato campo non valido");
							importoField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (cfSnnField.getText().isEmpty())
							cfSnnField.setBackground(Color.RED);
						if (dataField.getText().isEmpty())
							dataField.setBackground(Color.RED);
						if (importoField.getText().isEmpty())
							importoField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(cfSnnLabel);
				panel.add(cfSnnField);
				panel.add(importoLabel);
				panel.add(importoField);
				panel.add(dataLabel);
				panel.add(dataField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			stipendioTutti.addActionListener((ActionEvent event) -> {

				JLabel dataLabel = new JLabel("Data");
				JTextField dataField = new JTextField(columns);
				dataField.addMouseListener(erroreFieldListener);
				dataField.setToolTipText("Data del pagamento dello stipendio");

				JLabel importoImpiegatoLabel = new JLabel("Impiegato");
				JTextField importoImpiegatoField = new JTextField(columns);
				importoImpiegatoField.addMouseListener(erroreFieldListener);
				importoImpiegatoField.setToolTipText("Importo dello stipendio dell'impiegato");

				JLabel importoOperaioLabel = new JLabel("Operaio");
				JTextField importoOperaioField = new JTextField(columns);
				importoOperaioField.addMouseListener(erroreFieldListener);
				importoOperaioField.setToolTipText("Importo dello stipendio dell'operaio");

				JLabel importoQuadroLabel = new JLabel("Quadro");
				JTextField importoQuadroField = new JTextField(columns);
				importoQuadroField.addMouseListener(erroreFieldListener);
				importoQuadroField.setToolTipText("Importo dello stipendio del quadro");

				JLabel importoDirigenteLabel = new JLabel("Dirigente");
				JTextField importoDirigenteField = new JTextField(columns);
				importoDirigenteField.addMouseListener(erroreFieldListener);
				importoDirigenteField.setToolTipText("Importo dello stipendio del dirigente");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Paga tutto il personale");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!dataField.getText().isEmpty() && !importoImpiegatoField.getText().isEmpty()
							&& !importoOperaioField.getText().isEmpty() && !importoQuadroField.getText().isEmpty()
							&& !importoDirigenteField.getText().isEmpty()) {
						try {

							double importoI = Double.parseDouble(importoImpiegatoField.getText());
							double importoO = Double.parseDouble(importoOperaioField.getText());
							double importoQ = Double.parseDouble(importoQuadroField.getText());
							double importoD = Double.parseDouble(importoDirigenteField.getText());

							impresa.pagaStipendo(null, dataField.getText(), importoI, importoO, importoQ, importoD);

							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Tutto il personale è stato pagato");
							dataField.setText("");
							importoImpiegatoField.setText("");
							importoOperaioField.setText("");
							importoQuadroField.setText("");
							importoDirigenteField.setText("");
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Personale non esistente");
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Uno o più formati dei campi  non sono validi");
							if (importoImpiegatoField.getText().matches("\\d+\\.\\d+")
									|| importoImpiegatoField.getText().matches("\\d+"))
								;
							else
								importoImpiegatoField.setBackground(Color.RED);
							if (importoOperaioField.getText().matches("\\d+\\.\\d+")
									|| importoOperaioField.getText().matches("\\d+"))
								;
							else
								importoOperaioField.setBackground(Color.RED);
							if (importoQuadroField.getText().matches("\\d+\\.\\d+")
									|| importoQuadroField.getText().matches("\\d+"))
								;
							else
								importoQuadroField.setBackground(Color.RED);
							if (importoDirigenteField.getText().matches("\\d+\\.\\d+")
									|| importoDirigenteField.getText().matches("\\d+"))
								;
							else
								importoDirigenteField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (dataField.getText().isEmpty())
							dataField.setBackground(Color.RED);
						if (importoImpiegatoField.getText().isEmpty())
							importoImpiegatoField.setBackground(Color.RED);
						if (importoOperaioField.getText().isEmpty())
							importoOperaioField.setBackground(Color.RED);
						if (importoQuadroField.getText().isEmpty())
							importoQuadroField.setBackground(Color.RED);
						if (importoDirigenteField.getText().isEmpty())
							importoDirigenteField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(6);
				gridLayout.setColumns(2);
				panel.add(dataLabel);
				panel.add(dataField);
				panel.add(importoImpiegatoLabel);
				panel.add(importoImpiegatoField);
				panel.add(importoOperaioLabel);
				panel.add(importoOperaioField);
				panel.add(importoQuadroLabel);
				panel.add(importoQuadroField);
				panel.add(importoDirigenteLabel);
				panel.add(importoDirigenteField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			stipendioTuttiCantiere.addActionListener((ActionEvent event6) -> {

				JLabel codiceLabel = new JLabel("Codice");
				JTextField codiceField = new JTextField(columns);
				codiceField.addMouseListener(erroreFieldListener);
				codiceField.setToolTipText("Codice del cantiere");

				JLabel dataLabel = new JLabel("Data");
				JTextField dataField = new JTextField(columns);
				dataField.addMouseListener(erroreFieldListener);
				dataField.setToolTipText("Data del pagamento dello stipendio");

				JLabel importoOperaioLabel = new JLabel("Operaio");
				JTextField importoOperaioField = new JTextField(columns);
				importoOperaioField.addMouseListener(erroreFieldListener);
				importoOperaioField.setToolTipText("Importo dello stipendio dell'operaio");

				JLabel importoQuadroLabel = new JLabel("Quadro");
				JTextField importoQuadroField = new JTextField(columns);
				importoQuadroField.addMouseListener(erroreFieldListener);
				importoQuadroField.setToolTipText("Importo dello stipendio del quadro");

				JLabel importoDirigenteLabel = new JLabel("Dirigente");
				JTextField importoDirigenteField = new JTextField(columns);
				importoDirigenteField.addMouseListener(erroreFieldListener);
				importoDirigenteField.setToolTipText("Importo dello stipendio del dirigente");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Paga tutto il personale");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!codiceField.getText().isEmpty() && !dataField.getText().isEmpty()
							&& !importoOperaioField.getText().isEmpty() && !importoQuadroField.getText().isEmpty()
							&& !importoDirigenteField.getText().isEmpty()) {
						try {
							int codice = Integer.parseInt(codiceField.getText());
							double importoO = Double.parseDouble(importoOperaioField.getText());
							double importoQ = Double.parseDouble(importoQuadroField.getText());
							double importoD = Double.parseDouble(importoDirigenteField.getText());

							CantiereImpresa cantiereImpresa = impresa.trovaItem(String.valueOf(codice),
									CantiereImpresa.class);
							if (cantiereImpresa == null)
								throw new ItemNonEsistenteException();
							if (cantiereImpresa.getPersonale() == null) {
								msgSistema.setText(statoUscita(1) + "Il cantiere non ha personale");
								codiceField.setBackground(Color.RED);
								inviaButton.setBackground(Color.RED);
								timerColor.start();
							} else {
								for (Personale per : cantiereImpresa.getPersonale())
									impresa.pagaStipendo(per, dataField.getText(), 0.0, importoO, importoQ, importoD);
								inviaButton.setBackground(Color.GREEN);
								timerColor.start();
								msgSistema.setText(statoUscita(0) + "Tutto il personale del cantiere è stato pagato");
								codiceField.setText("");
								dataField.setText("");
								importoOperaioField.setText("");
								importoQuadroField.setText("");
								importoDirigenteField.setText("");
							}
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Cantiere non esistente");
							codiceField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Uno o più formati dei campi  non sono validi");
							if (!codiceField.getText().matches("\\d+"))
								codiceField.setBackground(Color.RED);
							if (importoOperaioField.getText().matches("\\d+\\.\\d+")
									|| importoOperaioField.getText().matches("\\d+"))
								;
							else
								importoOperaioField.setBackground(Color.RED);
							if (importoQuadroField.getText().matches("\\d+\\.\\d+")
									|| importoQuadroField.getText().matches("\\d+"))
								;
							else
								importoQuadroField.setBackground(Color.RED);
							if (importoDirigenteField.getText().matches("\\d+\\.\\d+")
									|| importoDirigenteField.getText().matches("\\d+"))
								;
							else
								importoDirigenteField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (codiceField.getText().isEmpty())
							codiceField.setBackground(Color.RED);
						if (dataField.getText().isEmpty())
							dataField.setBackground(Color.RED);
						if (importoOperaioField.getText().isEmpty())
							importoOperaioField.setBackground(Color.RED);
						if (importoQuadroField.getText().isEmpty())
							importoQuadroField.setBackground(Color.RED);
						if (importoDirigenteField.getText().isEmpty())
							importoDirigenteField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(6);
				gridLayout.setColumns(2);
				panel.add(codiceLabel);
				panel.add(codiceField);
				panel.add(dataLabel);
				panel.add(dataField);
				panel.add(importoOperaioLabel);
				panel.add(importoOperaioField);
				panel.add(importoQuadroLabel);
				panel.add(importoQuadroField);
				panel.add(importoDirigenteLabel);
				panel.add(importoDirigenteField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			panel.removeAll();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			panel.add(assunzioni);
			panel.add(licenziamenti);
			panel.add(panelStipendio);
			panel.updateUI();
		}));
		menu.add(itemMenu("Gestione Cantieri", (ActionEvent event) -> {

			JButton aggiungi = new JButton("Aggiungi");
			aggiungi.setBackground(buttonsColor1);
			aggiungi.setToolTipText("Apre l'interfaccia per aggiungere i cantieri");

			JButton aggiungiDaCliente = new JButton("Aggiungi dal cliente");
			aggiungiDaCliente.setBackground(buttonsColor2);
			aggiungiDaCliente.setToolTipText("Apre l'interfaccia per aggiungere i cantiere dai clienti");

			JPanel panelAggiungi = new JPanel();
			panelAggiungi.setLayout(new BorderLayout());
			panelAggiungi.add(aggiungi, BorderLayout.CENTER);
			panelAggiungi.add(aggiungiDaCliente, BorderLayout.SOUTH);

			JButton rimuovi = new JButton("Rimuovi");
			rimuovi.setToolTipText("Apre l'interfaccia per rimuovere i cantieri");
			rimuovi.setBackground(buttonsColor1);

			JButton aggiorna = new JButton("Aggiorna");
			aggiorna.setBackground(buttonsColor1);
			aggiorna.setToolTipText("Aggiorna le informazioni dei cantieri");

			JButton annullaButton = new JButton("Annulla");
			annullaButton.setBackground(buttonsColor1);
			annullaButton.setToolTipText("Torna alla schermata iniziale");
			annullaButton.addActionListener((ActionEvent eventAnullaButton) -> {
				panel.removeAll();
				gridLayout.setRows(3);
				gridLayout.setColumns(1);
				panel.add(panelAggiungi);
				panel.add(rimuovi);
				panel.add(aggiorna);
				panel.updateUI();
			});

			aggiungi.addActionListener((ActionEvent event2) -> {

				JLabel codiceLabel = new JLabel("Codice");
				JTextField codiceField = new JTextField(columns);
				codiceField.addMouseListener(erroreFieldListener);
				codiceField.setToolTipText("Codice del cantiere");

				JLabel localitàLabel = new JLabel("Località");
				JTextField localitàField = new JTextField(columns);
				localitàField.addMouseListener(erroreFieldListener);
				localitàField.setToolTipText("Località del cantiere");

				JLabel valoreLabel = new JLabel("Valore");
				JTextField valoreField = new JTextField(columns);
				valoreField.addMouseListener(erroreFieldListener);
				valoreField.setToolTipText("Valore del cantiere");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Aggiungi il nuovo cantiere");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event3) -> {

					if (!codiceField.getText().isEmpty() && !localitàField.getText().isEmpty()
							&& !valoreField.getText().isEmpty()) {
						try {

							int codice = Integer.parseInt(codiceField.getText());
							long valore = Long.parseLong(valoreField.getText());

							CantiereImpresa cantiereImpresa = new CantiereImpresa(
									new Cantiere(codice, localitàField.getText(), valore));
							impresa.addToImpresa(cantiereImpresa);

							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Cantiere aggiunto");
							codiceField.setText("");
							localitàField.setText("");
							valoreField.setText("");

						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Uno o più formati dei campi  non sono validi");
							if (!codiceField.getText().matches("\\d+"))
								codiceField.setBackground(Color.RED);
							if (!valoreField.getText().matches("\\d+"))
								valoreField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Cantiere già esistente");
							inviaButton.setBackground(Color.RED);
							codiceField.setBackground(Color.RED);
							timerColor.start();
						}

					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (codiceField.getText().isEmpty())
							codiceField.setBackground(Color.RED);
						if (localitàField.getText().isEmpty())
							localitàField.setBackground(Color.RED);
						if (valoreField.getText().isEmpty())
							valoreField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(codiceLabel);
				panel.add(codiceField);
				panel.add(localitàLabel);
				panel.add(localitàField);
				panel.add(valoreLabel);
				panel.add(valoreField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			aggiungiDaCliente.addActionListener((ActionEvent event3) -> {

				JLabel cfSnnClienteLabel = new JLabel("CF/SSN");
				JTextField cfSnnClienteField = new JTextField(columns);
				cfSnnClienteField.addMouseListener(erroreFieldListener);
				cfSnnClienteField.setToolTipText("Codice Fiscale/Social Security Number del cliente");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Aggiungi il nuovo cantiere del cliente");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event4) -> {
					if (!cfSnnClienteField.getText().isEmpty()) {

						try {
							Cliente cliente = impresa.trovaItem(cfSnnClienteField.getText(), Cliente.class);
							if (cliente == null)
								throw new ItemNonEsistenteException();
							if (cliente.controllaCantiereVuoto()) {
								inviaButton.setBackground(Color.RED);
								timerColor.start();
								cfSnnClienteField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Cantiere del cliente vuoto");
							} else {
								CantiereImpresa cantiereImpresa = new CantiereImpresa(cliente.getCantiere());
								impresa.addToImpresa(cantiereImpresa);
								inviaButton.setBackground(Color.GREEN);
								timerColor.start();
								cfSnnClienteField.setText("");
								msgSistema.setText(statoUscita(0) + "Cantiere aggiunto");
							}

						} catch (ItemNonEsistenteException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							cfSnnClienteField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Cliente non esistente");
						} catch (ItemEsistenteException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							cfSnnClienteField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Cantiere del cliente già esistente");
						}

					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						cfSnnClienteField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(cfSnnClienteLabel);
				panel.add(cfSnnClienteField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			rimuovi.addActionListener((ActionEvent event3) -> {

				JLabel codiceCantiereLabel = new JLabel("Codice");
				JTextField codiceCantiereField = new JTextField(columns);
				codiceCantiereField.addMouseListener(erroreFieldListener);
				codiceCantiereField.setToolTipText("Codice del cantiere da rimuovere");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Rimuovi il cantiere");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event4) -> {
					if (!codiceCantiereField.getText().isEmpty()) {

						try {
							int codice = Integer.parseInt(codiceCantiereField.getText());
							CantiereImpresa cantiere = impresa.trovaItem(String.valueOf(codice), CantiereImpresa.class);
							if (cantiere == null)
								throw new ItemNonEsistenteException();
							Personale responsabile = cantiere.getResponsabile();
							if (responsabile != null)
								if (responsabile.getClass() == Dirigente.class) {
									Dirigente dirigente = impresa.trovaItem(responsabile.getCfSnn(), Dirigente.class);
									dirigente.setNumeroAttualeCantieri(dirigente.getNumeroAttualeCantieri() - 1);
								}
							impresa.removeFromImpresa(String.valueOf(cantiere.getCodice()), CantiereImpresa.class);
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							codiceCantiereField.setText("");
							msgSistema.setText(statoUscita(0) + "Cantiere rimosso");

						} catch (ItemNonEsistenteException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							codiceCantiereField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Cantiere non esistente");
						} catch (NumberFormatException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							codiceCantiereField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Formato campo non valido");
						}

					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						codiceCantiereField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(codiceCantiereLabel);
				panel.add(codiceCantiereField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			aggiorna.addActionListener((ActionEvent event3) -> {

				JLabel codiceCantiereLabel = new JLabel("Codice");
				JTextField codiceCantiereField = new JTextField(columns);
				codiceCantiereField.addMouseListener(erroreFieldListener);
				codiceCantiereField.setToolTipText("Codice del cantiere da aggiornare");

				JButton avantiButton = new JButton("Avanti");
				avantiButton.setBackground(buttonsColor1);
				avantiButton.setToolTipText("Prosegui alla schermata successiva");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> avantiButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				avantiButton.addActionListener((ActionEvent event4) -> {
					if (!codiceCantiereField.getText().isEmpty()) {
						try {
							int codice = Integer.parseInt(codiceCantiereField.getText());
							CantiereImpresa cantiere = impresa.trovaItem(String.valueOf(codice), CantiereImpresa.class);
							if (cantiere == null)
								throw new ItemNonEsistenteException();
							avantiButton.setBackground(Color.GREEN);
							timerColor.start();
							codiceCantiereField.setText("");
							msgSistema.setText(statoUscita(0) + "Cantiere trovato");

							JButton aggiungiPersonaleButton = new JButton("Aggiungi personale");
							aggiungiPersonaleButton.setBackground(buttonsColor1);
							aggiungiPersonaleButton.setToolTipText("Aggiungi personale al cantiere");

							JButton rimuoviPersonaleButton = new JButton("Rimuovi personale");
							rimuoviPersonaleButton.setBackground(buttonsColor1);
							rimuoviPersonaleButton.setToolTipText("Rimuovi personale al cantiere");

							JButton assegnaResponsabileButton = new JButton("Assegna responsabile");
							assegnaResponsabileButton.setBackground(buttonsColor1);
							assegnaResponsabileButton.setToolTipText("Assegna il responsabile del cantiere");

							JButton assegnaCaposquadraButton = new JButton("Assegna caposquadra");
							assegnaCaposquadraButton.setBackground(buttonsColor1);
							assegnaCaposquadraButton.setToolTipText("Assegna il caposquadra del cantiere");

							JButton rendiOperativoButton = new JButton("Rendi Operativo");
							rendiOperativoButton.setBackground(buttonsColor2);
							rendiOperativoButton.setToolTipText("Rendi operativo il cantiere");

							JPanel panelAnOp = new JPanel(new BorderLayout());
							panelAnOp.add(rendiOperativoButton, BorderLayout.CENTER);
							panelAnOp.add(annullaButton, BorderLayout.WEST);

							JButton indietroButton = new JButton("Indietro");
							indietroButton.setBackground(buttonsColor1);
							indietroButton.setToolTipText("Torna alla schermata precedente");
							indietroButton.addActionListener((ActionEvent event5) -> {

								panel.removeAll();
								gridLayout.setRows(5);
								gridLayout.setColumns(1);
								panel.add(aggiungiPersonaleButton);
								panel.add(rimuoviPersonaleButton);
								panel.add(assegnaResponsabileButton);
								panel.add(assegnaCaposquadraButton);
								panel.add(panelAnOp);
								panel.updateUI();
							});

							panel.removeAll();
							gridLayout.setRows(5);
							gridLayout.setColumns(1);
							panel.add(aggiungiPersonaleButton);
							panel.add(rimuoviPersonaleButton);
							panel.add(assegnaResponsabileButton);
							panel.add(assegnaCaposquadraButton);
							panel.add(panelAnOp);
							panel.updateUI();

							aggiungiPersonaleButton.addActionListener((ActionEvent event2) -> {

								JLabel cfSnnAggiungerePersonaleLabel = new JLabel("CF/SSN");
								JTextField cfSnnAggiungerePersonaleField = new JTextField(columns);
								cfSnnAggiungerePersonaleField.addMouseListener(erroreFieldListener);
								cfSnnAggiungerePersonaleField.setToolTipText("CF/SSN del personale da aggiungere");

								JButton inviaButton = new JButton("Invia");
								inviaButton.setBackground(buttonsColor1);
								inviaButton.setToolTipText("Aggiungi il personale al cantiere");

								Timer timerColor2 = new Timer(600,
										(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
								timerColor2.setRepeats(false);

								inviaButton.addActionListener((ActionEvent event8) -> {

									if (!cfSnnAggiungerePersonaleField.getText().isEmpty()) {
										ArrayList<Personale> personaleNonImpiegato = Report.personaleNonImpiegato(
												impresa.getListaPersonale(), impresa.getListaCantieri());

										try {
											Personale personale = impresa.trovaItem(
													cfSnnAggiungerePersonaleField.getText(), Personale.class);
											if (personale == null)
												throw new ItemNonEsistenteException();
											if (personaleNonImpiegato == null
													|| !personaleNonImpiegato.contains(personale)) {
												inviaButton.setBackground(Color.RED);
												timerColor2.start();
												cfSnnAggiungerePersonaleField.setBackground(Color.RED);
												msgSistema.setText(statoUscita(1) + "Personale già impiegato");
											} else {
												cantiere.addPersonale(personale);
												inviaButton.setBackground(Color.GREEN);
												timerColor2.start();
												cfSnnAggiungerePersonaleField.setText("");
												msgSistema.setText(statoUscita(0) + "Personale aggiunto");
											}
										} catch (ItemNonEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAggiungerePersonaleField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1) + "Personale non esistente");
										} catch (ItemEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAggiungerePersonaleField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1) + "Personale già esistente");
										}
									} else {
										inviaButton.setBackground(Color.RED);
										timerColor2.start();
										cfSnnAggiungerePersonaleField.setBackground(Color.RED);
										msgSistema.setText(statoUscita(1) + "Riempire i campi");
									}
								});

								panel.removeAll();
								gridLayout.setRows(4);
								gridLayout.setColumns(2);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(cfSnnAggiungerePersonaleLabel);
								panel.add(cfSnnAggiungerePersonaleField);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(indietroButton);
								panel.add(inviaButton);
								panel.updateUI();
							});

							rimuoviPersonaleButton.addActionListener((ActionEvent event6) -> {

								JLabel cfSnnRimuoverePersonaleLabel = new JLabel("CF/SSN");
								JTextField cfSnnRimuoverePersonaleField = new JTextField(columns);
								cfSnnRimuoverePersonaleField.addMouseListener(erroreFieldListener);
								cfSnnRimuoverePersonaleField.setToolTipText("CF/SSN del personale da rimuovere");

								JButton inviaButton = new JButton("Invia");
								inviaButton.setBackground(buttonsColor1);
								inviaButton.setToolTipText("Rimuovi il personale dal cantiere");

								Timer timerColor2 = new Timer(600,
										(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
								timerColor2.setRepeats(false);

								inviaButton.addActionListener((ActionEvent event9) -> {

									if (!cfSnnRimuoverePersonaleField.getText().isEmpty()) {
										Personale personale = impresa.trovaItem(cfSnnRimuoverePersonaleField.getText(),
												Personale.class);
										try {
											if (personale == null)
												throw new ItemNonEsistenteException();
											if (personale.equals(cantiere.getResponsabile())
													|| personale.equals(cantiere.getCaposquadra()))
												throw new ItemEsistenteException();
											else {
												cantiere.removePersonale(personale);
												inviaButton.setBackground(Color.GREEN);
												timerColor2.start();
												cfSnnRimuoverePersonaleField.setText("");
												msgSistema.setText(statoUscita(0) + "Personale rimosso");
											}
										} catch (ItemNonEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnRimuoverePersonaleField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1) + "Personale non esistente");
										} catch (ItemEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnRimuoverePersonaleField.setBackground(Color.RED);
											if (personale.equals(cantiere.getResponsabile()))
												msgSistema.setText(statoUscita(1)
														+ "Il personale selezionato è il responsabile, sostituirlo prima di rimuoverlo");
											if (personale.equals(cantiere.getCaposquadra()))
												msgSistema.setText(statoUscita(1)
														+ "Il personale selezionato è il caposquadra, sostituirlo prima di rimuoverlo");
										}
									} else {
										inviaButton.setBackground(Color.RED);
										timerColor2.start();
										cfSnnRimuoverePersonaleField.setBackground(Color.RED);
										msgSistema.setText(statoUscita(1) + "Riempire i campi");
									}
								});

								panel.removeAll();
								gridLayout.setRows(4);
								gridLayout.setColumns(2);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(cfSnnRimuoverePersonaleLabel);
								panel.add(cfSnnRimuoverePersonaleField);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(indietroButton);
								panel.add(inviaButton);
								panel.updateUI();
							});

							assegnaResponsabileButton.addActionListener((ActionEvent event6) -> {

								JLabel cfSnnAssegnaResponsabileLabel = new JLabel("CF/SSN");
								JTextField cfSnnAssegnaResponsabileField = new JTextField(columns);
								cfSnnAssegnaResponsabileField.addMouseListener(erroreFieldListener);
								cfSnnAssegnaResponsabileField.setToolTipText("CF/SSN del responsabile da assegnare");

								JButton inviaButton = new JButton("Invia");
								inviaButton.setBackground(buttonsColor1);
								inviaButton.setToolTipText("Assegna il responsabile del cantiere");

								Timer timerColor2 = new Timer(600,
										(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
								timerColor2.setRepeats(false);

								inviaButton.addActionListener((ActionEvent event9) -> {

									if (!cfSnnAssegnaResponsabileField.getText().isEmpty()) {
										ArrayList<Personale> personaleNonImpiegato = Report.personaleNonImpiegato(
												impresa.getListaPersonale(), impresa.getListaCantieri());
										Personale personale = impresa.trovaItem(cfSnnAssegnaResponsabileField.getText(),
												Personale.class);
										try {
											Personale oldResponsabile = cantiere.getResponsabile();
											if (personale == null)
												throw new ItemNonEsistenteException();
											if (personale.equals(cantiere.getResponsabile()))
												throw new ItemEsistenteException();
											if (personaleNonImpiegato == null
													|| !personaleNonImpiegato.contains(personale)) {
												inviaButton.setBackground(Color.RED);
												timerColor2.start();
												cfSnnAssegnaResponsabileField.setBackground(Color.RED);
												msgSistema.setText(statoUscita(1) + "Personale già impiegato");
											} else {
												if (oldResponsabile != null
														&& oldResponsabile.getClass() == Dirigente.class) {
													Dirigente dirigente = Utilità.rewindCast(impresa
															.trovaItem(oldResponsabile.getCfSnn(), Dirigente.class),
															Dirigente.class);
													dirigente.setNumeroAttualeCantieri(
															dirigente.getNumeroAttualeCantieri() - 1);
												}
												cantiere.setResponsabile(personale);
												inviaButton.setBackground(Color.GREEN);
												timerColor2.start();
												cfSnnAssegnaResponsabileField.setText("");
												msgSistema.setText(statoUscita(0) + "Responsabile assegnato");
											}

										} catch (ItemNonEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaResponsabileField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1) + "Personale non esistente");
										} catch (ItemEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaResponsabileField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1)
													+ "Il personale selezionato è già responsabile di questo cantiere");
										} catch (NumeroMassimoCantieriException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaResponsabileField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1)
													+ "Il dirigente ha già raggiunto il numero massimo di cantieri");
										} catch (ResponsabileException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaResponsabileField.setBackground(Color.RED);
											if (cantiere.getValore() > 500000
													&& !personale.getProfessione().equals("Dirigente"))
												msgSistema.setText(statoUscita(1)
														+ "Solo un dirigente può essere il responsabile di questo cantiere, valore superiore a 500000");
											else
												msgSistema.setText(statoUscita(1)
														+ "Solo un quadro o dirigente può essere il responsabile");
										}
									} else {
										inviaButton.setBackground(Color.RED);
										timerColor2.start();
										cfSnnAssegnaResponsabileField.setBackground(Color.RED);
										msgSistema.setText(statoUscita(1) + "Riempire i campi");
									}
								});

								panel.removeAll();
								gridLayout.setRows(4);
								gridLayout.setColumns(2);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(cfSnnAssegnaResponsabileLabel);
								panel.add(cfSnnAssegnaResponsabileField);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(indietroButton);
								panel.add(inviaButton);
								panel.updateUI();
							});

							assegnaCaposquadraButton.addActionListener((ActionEvent event6) -> {

								JLabel cfSnnAssegnaCaposquadraLabel = new JLabel("CF/SSN");
								JTextField cfSnnAssegnaCaposquadraField = new JTextField(columns);
								cfSnnAssegnaCaposquadraField.addMouseListener(erroreFieldListener);
								cfSnnAssegnaCaposquadraField.setToolTipText("CF/SSN del caposquadra da assegnare");

								JButton inviaButton = new JButton("Invia");
								inviaButton.setBackground(buttonsColor1);
								inviaButton.setToolTipText("Assegna il caposquadra del cantiere");

								Timer timerColor2 = new Timer(600,
										(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
								timerColor2.setRepeats(false);

								inviaButton.addActionListener((ActionEvent event9) -> {

									if (!cfSnnAssegnaCaposquadraField.getText().isEmpty()) {
										ArrayList<Personale> personaleNonImpiegato = Report.personaleNonImpiegato(
												impresa.getListaPersonale(), impresa.getListaCantieri());
										Personale personale = impresa.trovaItem(cfSnnAssegnaCaposquadraField.getText(),
												Personale.class);
										try {
											Personale oldCaposquadra = cantiere.getCaposquadra();
											if (personale == null)
												throw new ItemNonEsistenteException();
											if (personale.equals(cantiere.getCaposquadra()))
												throw new ItemEsistenteException();
											if (personaleNonImpiegato == null
													|| !personaleNonImpiegato.contains(personale)) {
												inviaButton.setBackground(Color.RED);
												timerColor2.start();
												cfSnnAssegnaCaposquadraField.setBackground(Color.RED);
												msgSistema.setText(statoUscita(1) + "Personale già impiegato");
											} else {
												cantiere.setCaposquadra(personale);
												if ((oldCaposquadra == null && cantiere.getCaposquadra() == null)
														|| cantiere.getCaposquadra().equals(oldCaposquadra)) {
													inviaButton.setBackground(Color.RED);
													timerColor2.start();
													cfSnnAssegnaCaposquadraField.setBackground(Color.RED);
													msgSistema.setText(statoUscita(1)
															+ "Solo un quadro può essere il caposquadra");
												} else {
													inviaButton.setBackground(Color.GREEN);
													timerColor2.start();
													cfSnnAssegnaCaposquadraField.setText("");
													msgSistema.setText(statoUscita(0) + "Caposquadra assegnato");
												}
											}
										} catch (ItemNonEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaCaposquadraField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1) + "Personale non esistente");
										} catch (ItemEsistenteException e) {
											inviaButton.setBackground(Color.RED);
											timerColor2.start();
											cfSnnAssegnaCaposquadraField.setBackground(Color.RED);
											msgSistema.setText(statoUscita(1)
													+ "Il personale selezionato è già caposquadra di questo cantiere");
										}
									} else {
										inviaButton.setBackground(Color.RED);
										timerColor2.start();
										cfSnnAssegnaCaposquadraField.setBackground(Color.RED);
										msgSistema.setText(statoUscita(1) + "Riempire i campi");
									}
								});

								panel.removeAll();
								gridLayout.setRows(4);
								gridLayout.setColumns(2);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(cfSnnAssegnaCaposquadraLabel);
								panel.add(cfSnnAssegnaCaposquadraField);
								panel.add(Box.createGlue());
								panel.add(Box.createGlue());
								panel.add(indietroButton);
								panel.add(inviaButton);
								panel.updateUI();
							});

							rendiOperativoButton.addActionListener((ActionEvent event6) -> {

								Timer timerColor2 = new Timer(600, (ActionEvent eventTimerColor) -> rendiOperativoButton
										.setBackground(buttonsColor2));
								timerColor2.setRepeats(false);

								try {
									String msgErr = "";
									if (cantiere.getPersonale() == null)
										msgErr += "Personale, ";
									if (cantiere.getResponsabile() == null)
										msgErr += "Responsabile, ";
									if (cantiere.getCaposquadra() == null)
										msgErr += "Caposquadra ";
									if (!msgErr.isEmpty())
										msgSistema.setText(statoUscita(1) + msgErr + "assente");
									else {
										if (cantiere.getStato().equals(CantiereImpresa.OPERATIVO))
											msgSistema.setText(statoUscita(1) + "Il cantiere è già operativo");
										else {
											cantiere.setStato(CantiereImpresa.OPERATIVO);
											rendiOperativoButton.setBackground(Color.GREEN);
											timerColor2.start();
											msgSistema.setText(statoUscita(0) + "Cantiere reso operativo");
										}
									}
								} catch (PermessoEnteNonRilasciatoException e) {
									rendiOperativoButton.setBackground(Color.RED);
									timerColor2.start();
									msgSistema.setText(
											statoUscita(1) + "Permesso ente locale assente o in fase di lavorazione");
								} catch (PermessoEnteNegatoException e) {
									rendiOperativoButton.setBackground(Color.RED);
									timerColor2.start();
									msgSistema.setText(statoUscita(1) + "Permesso ente locale negato");
								}
							});

						} catch (ItemNonEsistenteException e) {
							avantiButton.setBackground(Color.RED);
							timerColor.start();
							codiceCantiereField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Cantiere non esistente");
						} catch (NumberFormatException e) {
							avantiButton.setBackground(Color.RED);
							timerColor.start();
							codiceCantiereField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1) + "Formato campo non valido");
						}

					} else {
						avantiButton.setBackground(Color.RED);
						timerColor.start();
						codiceCantiereField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}

				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(codiceCantiereLabel);
				panel.add(codiceCantiereField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(avantiButton);
				panel.updateUI();

			});

			panel.removeAll();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			panel.add(panelAggiungi);
			panel.add(rimuovi);
			panel.add(aggiorna);
			panel.updateUI();

		}));

		return menu;

	}

	private JMenu gestioneEsternoMenu() {

		int columns = 15;

		JMenu menu = new JMenu("Gestione Esterno");

		JMenuItem itemAggiungi = itemMenu("Ottieni prodotto o servizio", (ActionEvent event2) -> {

			ArrayList<Fornitore> fornitori = aggiornaFornitori();
			if (fornitori != null) {

				impresa.setListaFornitori(fornitori);

				JRadioButton prodottoRButton = new JRadioButton("Prodotto");
				prodottoRButton.setSelected(true);

				JRadioButton servizioRButton = new JRadioButton("Servizio");

				ButtonGroup group = new ButtonGroup();
				group.add(prodottoRButton);
				group.add(servizioRButton);

				JLabel partitaIVALabel = new JLabel("PartitaIVA");
				JTextField partitaIVAField = new JTextField(columns);
				partitaIVAField.addMouseListener(erroreFieldListener);
				partitaIVAField.setToolTipText("PartitaIVA del fornitore");

				JLabel codiceLabel = new JLabel("Codice prodotto");
				JTextField codiceField = new JTextField(columns);
				codiceField.addMouseListener(erroreFieldListener);
				codiceField.setToolTipText("Codice del prodotto");

				servizioRButton.addActionListener((ActionEvent event4) -> {
					codiceLabel.setText("Numero telefono servizio");
					codiceField.setToolTipText("Numero di telefono del servizio");
				});

				prodottoRButton.addActionListener((ActionEvent event4) -> {
					codiceLabel.setText("Codice del prodotto");
					codiceField.setToolTipText("Codice prodotto");
				});

				JButton ottieniButton = new JButton("Ottieni");
				ottieniButton.setBackground(buttonsColor1);
				ottieniButton.setToolTipText("Ottieni il prodotto o il servizio selezionato dal fornitore selezionato");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> ottieniButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				JPanel panel2 = new JPanel(new GridLayout(5, 2));

				ottieniButton.addActionListener((ActionEvent event5) -> {
					if (!partitaIVAField.getText().isEmpty() && !codiceField.getText().isEmpty()) {

						if (prodottoRButton.isSelected()) {
							try {
								int codice = Integer.parseInt(codiceField.getText());
								Fornitore fornitore = impresa.trovaItem(partitaIVAField.getText(), Fornitore.class);
								if (fornitore == null)
									throw new ItemNonEsistenteException();
								boolean trovato = false;
								for (Prodotto prodotto : fornitore.getProdottiDisponibili())
									if (prodotto.getCodice() == codice) {
										trovato = true;
										impresa.addToImpresa(prodotto);
									}
								if (!trovato) {
									ottieniButton.setBackground(Color.RED);
									timerColor.start();
									codiceField.setBackground(Color.RED);
									msgSistema.setText(statoUscita(1)
											+ "Il fornitore selezionato non possiede il prodotto selezionato");
								} else {
									ottieniButton.setBackground(Color.GREEN);
									timerColor.start();
									partitaIVAField.setText("");
									codiceField.setText("");
									msgSistema.setText(statoUscita(0) + "Prodotto ottenuto e aggiunto all'impresa");
								}

							} catch (NumberFormatException e1) {
								ottieniButton.setBackground(Color.RED);
								timerColor.start();
								codiceField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Formato campo non valido");
							} catch (ItemNonEsistenteException e) {
								ottieniButton.setBackground(Color.RED);
								timerColor.start();
								partitaIVAField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Fornitore non esistente");
							} catch (ItemEsistenteException e) {
							}
						} else {
							try {
								long codice = Long.parseLong(codiceField.getText());
								Fornitore fornitore = impresa.trovaItem(partitaIVAField.getText(), Fornitore.class);
								if (fornitore == null)
									throw new ItemNonEsistenteException();
								boolean trovato = false;
								for (Servizio servizio : fornitore.getServiziDisponibili())
									if (servizio.getNumeroServizioClienti() == codice) {
										trovato = true;
										impresa.addToImpresa(servizio);
									}
								if (!trovato) {
									ottieniButton.setBackground(Color.RED);
									timerColor.start();
									codiceField.setBackground(Color.RED);
									msgSistema.setText(statoUscita(1)
											+ "Il fornitore selezionato non possiede il servizio selezionato");
								} else {
									ottieniButton.setBackground(Color.GREEN);
									timerColor.start();
									partitaIVAField.setText("");
									codiceField.setText("");
									msgSistema.setText(statoUscita(0) + "Servizio ottenuto e aggiunto all'impresa");
								}

							} catch (NumberFormatException e1) {
								ottieniButton.setBackground(Color.RED);
								timerColor.start();
								codiceField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Formato campo non valido");
							} catch (ItemNonEsistenteException e) {
								ottieniButton.setBackground(Color.RED);
								timerColor.start();
								partitaIVAField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Fornitore non esistente");
							} catch (ItemEsistenteException e) {
							}
						}

					} else {
						if (partitaIVAField.getText().isEmpty())
							partitaIVAField.setBackground(Color.RED);
						if (codiceField.getText().isEmpty())
							codiceField.setBackground(Color.RED);
						ottieniButton.setBackground(Color.RED);
						timerColor.start();
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}

				});

				panel.removeAll();
				gridLayout.setRows(1);
				gridLayout.setColumns(1);
				panel2.add(prodottoRButton);
				panel2.add(servizioRButton);
				panel2.add(partitaIVALabel);
				panel2.add(partitaIVAField);
				panel2.add(codiceLabel);
				panel2.add(codiceField);
				panel2.add(Box.createGlue());
				panel2.add(Box.createGlue());
				panel2.add(Box.createGlue());
				panel2.add(ottieniButton);
				panel.add(panel2);
				panel.updateUI();
			}
		});
		itemAggiungi.setToolTipText("Ottieni un prodotto o un servizio del fornitore e aggiungilo all'impresa");
		menu.add(menu("Fornitori", itemAggiungi));

		JMenuItem itemRichiestaPermesso = itemMenu("Richiedi permesso", (ActionEvent event6) -> {

			JFrame frameEnte = new JFrame("Richiesta permesso cantiere");
			frameEnte.setSize(WIDTH / 3, HEIGHT / 5);
			frameEnte.setLocationRelativeTo(panel);

			JLabel codiceCantiereLabel = new JLabel("Codice cantiere");
			JTextField codiceCantiereField = new JTextField(columns);
			codiceCantiereField.addMouseListener(erroreFieldListener);
			codiceCantiereField.setToolTipText("Codice del cantiere da aggiornare");

			JButton inviaButton = new JButton("Invia");
			inviaButton.setBackground(buttonsColor1);
			inviaButton.setToolTipText("Invia la richiesta");

			JButton annullaButton = new JButton("Annulla");
			annullaButton.setBackground(buttonsColor1);
			annullaButton.setToolTipText("Annulla l'operazione");
			annullaButton.addActionListener((ActionEvent event7) -> frameEnte.setVisible(false));

			JPanel panel = new JPanel(new GridLayout(2, 2));
			panel.setBorder(new EtchedBorder());

			Timer timerColor = new Timer(600,
					(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
			timerColor.setRepeats(false);

			inviaButton.addActionListener((ActionEvent event4) -> {
				if (!codiceCantiereField.getText().isEmpty()) {
					try {
						int codice = Integer.parseInt(codiceCantiereField.getText());
						CantiereImpresa cantiere = impresa.trovaItem(String.valueOf(codice), CantiereImpresa.class);
						if (cantiere == null)
							throw new ItemNonEsistenteException();
						boolean richiestaEsitente = false;
						File fileI = new File("PermessiRichieste.txt");
						ArrayList<CantiereImpresa> richiesteEsistenti = new ArrayList<>();
						try (FileInputStream inFr = new FileInputStream(fileI);) {
							try (ObjectInputStream inOr = new ObjectInputStream(inFr)) {
								while (true)
									richiesteEsistenti.add((CantiereImpresa) inOr.readObject());
							} catch (EOFException e) {
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}

							if (richiesteEsistenti.contains(cantiere)) {
								richiestaEsitente = true;
								throw new ItemEsistenteException();
							}

							richiesteEsistenti.add(cantiere);

							try (FileOutputStream outF = new FileOutputStream(fileI);
									ObjectOutputStream outO = new ObjectOutputStream(outF);) {
								for (CantiereImpresa cantiereI : richiesteEsistenti)
									outO.writeObject(cantiereI);
							}

						} catch (FileNotFoundException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							msgSistema.setText(statoUscita(1) + "File" + fileI.getName() + "non trovato");
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ItemEsistenteException e) {
							inviaButton.setBackground(Color.RED);
							timerColor.start();
							codiceCantiereField.setBackground(Color.RED);
							msgSistema.setText(statoUscita(1)
									+ "Esiste già una richiesta di permesso per questo cantiere e non è stata ancora esaminata dall'ente locale");
						}
						if (!richiestaEsitente) {
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Richiesta inviata");
							codiceCantiereField.setText("");
							frameEnte.setVisible(false);
						}
					} catch (ItemNonEsistenteException e) {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						codiceCantiereField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Cantiere non esistente");
					} catch (NumberFormatException e) {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						codiceCantiereField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Formato campo non valido");
					}

				} else {
					inviaButton.setBackground(Color.RED);
					timerColor.start();
					codiceCantiereField.setBackground(Color.RED);
					msgSistema.setText(statoUscita(1) + "Riempire i campi");
				}

			});

			panel.add(codiceCantiereLabel);
			panel.add(codiceCantiereField);
			panel.add(annullaButton);
			panel.add(inviaButton);
			frameEnte.add(panel);
			frameEnte.setVisible(true);
		});
		itemRichiestaPermesso.setToolTipText("Richiedi all'ente locale il permesso per il cantiere");

		JMenuItem itemControllaNuoviPermessi = itemMenu("Controlla nuovi permessi", (ActionEvent event7) -> {
			// neccessario per evitare l'eccezione di sicurezza, in quanto un altro processo
			// sta usando il file
			boolean done = false;
			File fileI = new File("PermessiRilasciati.txt");
			try (FileInputStream inF = new FileInputStream(fileI); ObjectInputStream inO = new ObjectInputStream(inF)) {
				ArrayList<Permesso> permessi = new ArrayList<>();
				try {
					while (true)
						permessi.add((Permesso) inO.readObject());
				} catch (EOFException e) {
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				for (CantiereImpresa cantiereI : impresa.getListaCantieri()) {
					for (Permesso permessoP : permessi)
						if (permessoP.getCodice() == cantiereI.getCodice())
							cantiereI.setPermesso(permessoP);
				}

				String etichettePermesso[] = { "CODICE PERMESSO", "STATO PERMESSO" };
				stampaEtichette(etichettePermesso);
				stampaArray(permessi);
				stampaDivisore();

				msgSistema.setText(statoUscita(0) + "I nuovi permessi sono stati aggiungi ai cantieri");
				done = true;
			} catch (FileNotFoundException e) {
				msgSistema.setText(statoUscita(1) + "File" + fileI.getName() + "non trovato");
			} catch (EOFException e) {
				msgSistema.setText(statoUscita(1) + "Non ci sono nuovi permessi rilasciati dall'ente");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (done) {
				Path path = Paths.get(fileI.getAbsolutePath());
				try {
					Files.deleteIfExists(path);
					Files.createFile(path);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		itemControllaNuoviPermessi.setToolTipText(
				"Controlla se l'ente ha rilasciato nuovi permessi, nel caso ci fosse un permesso che non corrispondesse a nessun cantiere attualmente dell'impresa, esso verrà cancellato");

		JMenu enteLocale = menu("Ente locale", itemRichiestaPermesso, itemControllaNuoviPermessi);
		menu.add(enteLocale);

		menu.add(itemMenu("Clienti", (ActionEvent event) -> {

			JButton aggiungiClienteButton = new JButton("Aggiungi");
			aggiungiClienteButton.setBackground(buttonsColor1);
			aggiungiClienteButton.setToolTipText("Aggiungi nuovi clienti");

			JButton rimuoviClienteButton = new JButton("Rimuovi");
			rimuoviClienteButton.setBackground(buttonsColor1);
			rimuoviClienteButton.setToolTipText(
					"Rimuovi clienti, il loro cantiere se è già stato aggiunto all'impresa, non verrà rimosso");

			JButton aggiungiCantiereButton = new JButton("Aggiungi cantiere al cliente");
			aggiungiCantiereButton.setBackground(buttonsColor1);
			aggiungiCantiereButton.setToolTipText("Aggiungi un cantiere al cliente");

			JButton assegnaCantiereDaImpresaButton = new JButton(
					"Assegna cantiere al cliente dai cantieri dell'impresa");
			assegnaCantiereDaImpresaButton.setBackground(buttonsColor2);
			assegnaCantiereDaImpresaButton.setToolTipText(
					"Assegna un cantiere al cliente dai cantieri dell'impresa già esistenti e non appartenenti a nessun cliente");

			JPanel panelAggiungiCantiere = new JPanel(new BorderLayout());
			panelAggiungiCantiere.add(aggiungiCantiereButton, BorderLayout.CENTER);
			panelAggiungiCantiere.add(assegnaCantiereDaImpresaButton, BorderLayout.SOUTH);

			JButton annullaButton = new JButton("Annulla");
			annullaButton.setBackground(buttonsColor1);
			annullaButton.setToolTipText("Torna alla scermata iniziale");
			annullaButton.addActionListener((ActionEvent event6) -> {
				panel.removeAll();
				gridLayout.setRows(3);
				gridLayout.setColumns(1);
				panel.add(aggiungiClienteButton);
				panel.add(rimuoviClienteButton);
				panel.add(panelAggiungiCantiere);
				panel.updateUI();
			});

			aggiungiClienteButton.addActionListener((ActionEvent event6) -> {

				JLabel cfSnnLabel = new JLabel("CF/SNN");
				JTextField cfSnnField = new JTextField(columns);
				cfSnnField.addMouseListener(erroreFieldListener);
				cfSnnField.setToolTipText("Codice Fiscale/Social Security Number del nuovo cliente");

				JLabel nomeLabel = new JLabel("Nome");
				JTextField nomeField = new JTextField(columns);
				nomeField.setToolTipText("Nome del nuovo cliente");
				nomeField.addMouseListener(erroreFieldListener);

				JLabel cognomeLabel = new JLabel("Cognome");
				JTextField cognomeField = new JTextField(columns);
				cognomeField.setToolTipText("Cognome del nuovo cliente");
				cognomeField.addMouseListener(erroreFieldListener);

				JLabel numeroTelefonoLabel = new JLabel("Numero di telefono");
				JTextField numeroTelefonoField = new JTextField(columns);
				numeroTelefonoField.setToolTipText("Numero di telefono del nuovo cliente");
				numeroTelefonoField.addMouseListener(erroreFieldListener);

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Registra il nuovo cliente");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event7) -> {
					if (!cfSnnField.getText().isEmpty() && !nomeField.getText().isEmpty()
							&& !cognomeField.getText().isEmpty() && !numeroTelefonoField.getText().isEmpty()) {
						try {

							long numeroTelefono = Long.parseLong(numeroTelefonoField.getText());
							Cliente cliente = new Cliente(cfSnnField.getText(), nomeField.getText(),
									cognomeField.getText(), numeroTelefono);

							impresa.addToImpresa(cliente);
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Cliente aggiunto");
							cfSnnField.setText("");
							nomeField.setText("");
							cognomeField.setText("");
							numeroTelefonoField.setText("");
						} catch (ItemEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Cliente già esistente");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Formato campo non valido");
							inviaButton.setBackground(Color.RED);
							numeroTelefonoField.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (cfSnnField.getText().isEmpty())
							cfSnnField.setBackground(Color.RED);
						if (nomeField.getText().isEmpty())
							nomeField.setBackground(Color.RED);
						if (cognomeField.getText().isEmpty())
							cognomeField.setBackground(Color.RED);
						if (numeroTelefonoField.getText().isEmpty())
							numeroTelefonoField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(5);
				gridLayout.setColumns(2);
				panel.add(cfSnnLabel);
				panel.add(cfSnnField);
				panel.add(nomeLabel);
				panel.add(nomeField);
				panel.add(cognomeLabel);
				panel.add(cognomeField);
				panel.add(numeroTelefonoLabel);
				panel.add(numeroTelefonoField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			rimuoviClienteButton.addActionListener((ActionEvent event6) -> {

				JLabel cfSnnLabel = new JLabel("CF/SNN");
				JTextField cfSnnField = new JTextField(columns);
				cfSnnField.addMouseListener(erroreFieldListener);
				cfSnnField.setToolTipText("Codice Fiscale/Social Security Number del cliente da rimuovere");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText(
						"Rimuovi il cliente, il cantiere del cliente non verrà rimosso se è stato già aggiunto all'impresa");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent eventInviaButton) -> {

					if (!cfSnnField.getText().isEmpty()) {
						try {
							impresa.removeFromImpresa(cfSnnField.getText(), Cliente.class);
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Cliente rimosso");
							cfSnnField.setText("");
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Cliente non esistente");
							inviaButton.setBackground(Color.RED);
							cfSnnField.setBackground(Color.RED);
							timerColor.start();
						}
					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						cfSnnField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(cfSnnLabel);
				panel.add(cfSnnField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();
			});

			aggiungiCantiereButton.addActionListener((ActionEvent event6) -> {

				JLabel cfSnnClienteLabel = new JLabel("CF/SSN");
				JTextField cfSnnClienteField = new JTextField(columns);
				cfSnnClienteField.addMouseListener(erroreFieldListener);
				cfSnnClienteField.setToolTipText(
						"Codice Fiscale/Social Security Number del cliente a cui aggiungere un cantiere");

				JLabel codiceLabel = new JLabel("Codice");
				JTextField codiceField = new JTextField(columns);
				codiceField.addMouseListener(erroreFieldListener);
				codiceField.setToolTipText("Codice del cantiere");

				JLabel localitàLabel = new JLabel("Località");
				JTextField localitàField = new JTextField(columns);
				localitàField.addMouseListener(erroreFieldListener);
				localitàField.setToolTipText("Località del cantiere");

				JLabel valoreLabel = new JLabel("Valore");
				JTextField valoreField = new JTextField(columns);
				valoreField.addMouseListener(erroreFieldListener);
				valoreField.setToolTipText("Valore del cantiere");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Aggiungi il nuovo cantiere al cliente");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event3) -> {

					if (!cfSnnClienteField.getText().isEmpty() && !codiceField.getText().isEmpty()
							&& !localitàField.getText().isEmpty() && !valoreField.getText().isEmpty()) {
						Cliente cliente = impresa.trovaItem(cfSnnClienteField.getText(), Cliente.class);
						try {

							int codice = Integer.parseInt(codiceField.getText());
							long valore = Long.parseLong(valoreField.getText());

							if (cliente == null)
								throw new ItemNonEsistenteException();
							if (!cliente.controllaCantiereVuoto())
								throw new ItemEsistenteException();
							if (impresa.trovaItem(String.valueOf(codice), CantiereImpresa.class) != null)
								throw new ItemEsistenteException();

							boolean esistente = false;
							for (Cliente cli : impresa.getListaClienti())
								if (cli.getCantiere().getCodice() == codice)
									esistente = true;
							if (esistente)
								throw new ItemEsistenteException();

							cliente.setCantiere(new Cantiere(codice, localitàField.getText(), valore));

							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Cantiere aggiunto al cliente");
							cfSnnClienteField.setText("");
							codiceField.setText("");
							localitàField.setText("");
							valoreField.setText("");

						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Uno o più formati dei campi  non sono validi");
							if (!codiceField.getText().matches("\\d+"))
								codiceField.setBackground(Color.RED);
							if (!valoreField.getText().matches("\\d+"))
								valoreField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemEsistenteException e) {
							if (!cliente.controllaCantiereVuoto()) {
								msgSistema.setText(statoUscita(1) + "Il cliente ha già un cantiere");
								cfSnnClienteField.setBackground(Color.RED);
							} else {
								msgSistema.setText(statoUscita(1) + "Cantiere già esistente");
								codiceField.setBackground(Color.RED);
							}
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemNonEsistenteException e) {
							msgSistema.setText(statoUscita(1) + "Cliente non esitente");

							inviaButton.setBackground(Color.RED);
							cfSnnClienteField.setBackground(Color.RED);
							timerColor.start();
						}

					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (cfSnnClienteField.getText().isEmpty())
							cfSnnClienteField.setBackground(Color.RED);
						if (codiceField.getText().isEmpty())
							codiceField.setBackground(Color.RED);
						if (localitàField.getText().isEmpty())
							localitàField.setBackground(Color.RED);
						if (valoreField.getText().isEmpty())
							valoreField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(5);
				gridLayout.setColumns(2);
				panel.add(cfSnnClienteLabel);
				panel.add(cfSnnClienteField);
				panel.add(codiceLabel);
				panel.add(codiceField);
				panel.add(localitàLabel);
				panel.add(localitàField);
				panel.add(valoreLabel);
				panel.add(valoreField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			assegnaCantiereDaImpresaButton.addActionListener((ActionEvent event6) -> {
				JLabel cfSnnClienteLabel = new JLabel("CF/SSN");
				JTextField cfSnnClienteField = new JTextField(columns);
				cfSnnClienteField.addMouseListener(erroreFieldListener);
				cfSnnClienteField.setToolTipText(
						"Codice Fiscale/Social Security Number del cliente a cui aggiungere un cantiere");

				JLabel codiceLabel = new JLabel("Codice");
				JTextField codiceField = new JTextField(columns);
				codiceField.addMouseListener(erroreFieldListener);
				codiceField.setToolTipText("Codice del cantiere");

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Assegna il cantiere al cliente");

				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);

				inviaButton.addActionListener((ActionEvent event3) -> {

					if (!cfSnnClienteField.getText().isEmpty() && !codiceField.getText().isEmpty()) {
						Cliente cliente = impresa.trovaItem(cfSnnClienteField.getText(), Cliente.class);
						try {

							int codice = Integer.parseInt(codiceField.getText());

							if (cliente == null)
								throw new ItemNonEsistenteException();
							if (!cliente.controllaCantiereVuoto())
								throw new ItemEsistenteException();
							CantiereImpresa cantiereImpresa = impresa.trovaItem(String.valueOf(codice),
									CantiereImpresa.class);
							if (cantiereImpresa == null)
								throw new ItemNonEsistenteException();

							boolean esistente = false;
							for (Cliente cli : impresa.getListaClienti())
								if (cli.getCantiere().getCodice() == codice)
									esistente = true;
							if (esistente)
								throw new ItemEsistenteException();

							cliente.setCantiere(
									new Cantiere(codice, cantiereImpresa.getLocalità(), cantiereImpresa.getValore()));
							inviaButton.setBackground(Color.GREEN);
							timerColor.start();
							msgSistema.setText(statoUscita(0) + "Cantiere assegnato al cliente");
							cfSnnClienteField.setText("");
							codiceField.setText("");

						} catch (NumberFormatException e) {
							msgSistema.setText(statoUscita(1) + "Formato campo non valido");
							codiceField.setBackground(Color.RED);
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemEsistenteException e) {
							if (!cliente.controllaCantiereVuoto()) {
								msgSistema.setText(statoUscita(1) + "Il cliente ha già un cantiere");
								cfSnnClienteField.setBackground(Color.RED);
							} else {
								msgSistema.setText(statoUscita(1) + "Cantiere già appartenente ad un altro cliente");
								codiceField.setBackground(Color.RED);
							}
							inviaButton.setBackground(Color.RED);
							timerColor.start();
						} catch (ItemNonEsistenteException e) {
							if (cliente == null) {
								cfSnnClienteField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Cliente non esitente");
							} else {
								codiceField.setBackground(Color.RED);
								msgSistema.setText(statoUscita(1) + "Cantiere non esitente");
							}

							inviaButton.setBackground(Color.RED);
							timerColor.start();
						}

					} else {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (cfSnnClienteField.getText().isEmpty())
							cfSnnClienteField.setBackground(Color.RED);
						if (codiceField.getText().isEmpty())
							codiceField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire tutti i campi");
					}
				});

				panel.removeAll();
				gridLayout.setRows(4);
				gridLayout.setColumns(2);
				panel.add(cfSnnClienteLabel);
				panel.add(cfSnnClienteField);
				panel.add(codiceLabel);
				panel.add(codiceField);
				panel.add(Box.createGlue());
				panel.add(Box.createGlue());
				panel.add(annullaButton);
				panel.add(inviaButton);
				panel.updateUI();

			});

			panel.removeAll();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			panel.add(aggiungiClienteButton);
			panel.add(rimuoviClienteButton);
			panel.add(panelAggiungiCantiere);
			panel.updateUI();

		}));

		return menu;

	}

	private JMenu reportMenu() {

		JMenu menu = new JMenu("Report");

		JMenuItem itemPersonaleOrdineAlfabetico = itemMenu("Ordine alfabetico", (ActionEvent event1) -> {
			stampaEtichette(etichettePersonale);
			stampaArray(Report.sort(impresa.getListaPersonale(), (Personale o1, Personale o2) -> {
				if (o1.getCognome().compareTo(o2.getCognome()) == 0)
					return 0;
				if (o1.getCognome().compareTo(o2.getCognome()) > 0)
					return 1;
				return -1;
			}));
			stampaDivisore();
			msgSistema.setText(statoUscita(0) + "Personale in ordine alfabetico stampato");
		});
		itemPersonaleOrdineAlfabetico.setToolTipText("Stampa il personale in ordine alfabetico");

		JMenuItem itemPersonaleOrdineProfessione = itemMenu("Ordine professione", (ActionEvent event1) -> {
			stampaEtichette(etichettePersonale);
			for (String str : professione) {
				ArrayList<Personale> daStampare = new ArrayList<>();
				for (Personale per : impresa.getListaPersonale())
					if (per.getProfessione().equals(str))
						daStampare.add(per);
				stampaArray(daStampare);
			}
			stampaDivisore();
			msgSistema.setText(statoUscita(0) + "Personale in ordine professione stampato");
		});
		itemPersonaleOrdineProfessione.setToolTipText("Stampa il personale raggruppato per professione");

		JMenuItem itemPersonaleNonImpiegato = itemMenu("Personale non impiegato", (ActionEvent event1) -> {
			stampaEtichette(etichettePersonale);
			stampaArray(Report.personaleNonImpiegato(impresa.getListaPersonale(), impresa.getListaCantieri()));
			stampaDivisore();
			msgSistema.setText(statoUscita(0) + "Personale non impiegato stampato");

		});
		itemPersonaleNonImpiegato.setToolTipText("Stampa il personale non impiegato");

		JMenuItem itemStipendio = itemMenu("Stampa stipendi", (ActionEvent event1) -> {
			String etichetteStipendio[] = { "COGNOME", "NOME", "CFSSN", "{DATA=STIPENDIO, DATA=STIPENDIO...}" };
			stampaEtichette(etichetteStipendio);
			if (impresa.getListaPersonale().size() <= 0)
				textArea.append("Nessun risulato\n");
			else
				for (Personale per : impresa.getListaPersonale()) {
					textArea.append(per.getCognome() + " - " + per.getNome() + " - " + per.getCfSnn() + " - "
							+ impresa.getStatoStipendi().get(per) + "\n");
				}
			stampaDivisore();
			stampaDivisore();
			msgSistema.setText(statoUscita(0) + "Stipendi stampato");
		});
		itemStipendio.setToolTipText("Stampa lo storico dello stipendio di ogni personale");

		JMenu personaleMenu = menu("Personale", menu("Stampa personale", itemPersonaleOrdineAlfabetico,
				itemPersonaleOrdineProfessione, itemPersonaleNonImpiegato), itemStipendio);
		menu.add(personaleMenu);

		String etichetteCantiere[] = { "CODICE", "LOCALITA'", "VALORE", "RESPONSABILE", "CAPOSQUADRA", "PERMESSO",
				"STATO" };
		JMenuItem itemStampaCantieri = itemMenu("Stampa tutti i cantieri", (ActionEvent event1) -> {
			stampaEtichette(etichetteCantiere);
			stampaArray(impresa.getListaCantieri());
			msgSistema.setText(statoUscita(0) + "Cantieri stampato");
		});
		itemStampaCantieri.setToolTipText("Stampa tutti i cantieri dell'impresa");

		JMenuItem itemStampaCantieriValoriCompresi = itemMenu("Stampa cantieri min max", (ActionEvent event1) -> {
			JFrame valoriFrame = new JFrame();
			valoriFrame.setUndecorated(true);
			valoriFrame.setSize(getWidth() / 3, getHeight() / 5);
			valoriFrame.setLocationRelativeTo(null);

			JPanel panel = new JPanel(new GridLayout(3, 2));
			panel.setBorder(new EtchedBorder());

			JTextField minField = new JTextField(7);
			minField.addMouseListener(erroreFieldListener);
			minField.setToolTipText("Valore minimo del cantiere");

			JTextField maxField = new JTextField(7);
			maxField.addMouseListener(erroreFieldListener);
			maxField.setToolTipText("Valore massimo del cantiere");

			JButton annullaButton = new JButton("Annulla");
			annullaButton.setBackground(buttonsColor1);
			annullaButton.setToolTipText("Annulla l'operazione");
			annullaButton.addActionListener((ActionEvent event) -> valoriFrame.setVisible(false));

			JButton inviaButton = new JButton("Invia");
			inviaButton.setToolTipText("Stampa i cantieri che rispettino le condizioni");
			inviaButton.setBackground(buttonsColor1);
			inviaButton.addActionListener((ActionEvent event) -> {
				Timer timerColor = new Timer(600,
						(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
				timerColor.setRepeats(false);
				CantiereImpresa min = new CantiereImpresa();
				CantiereImpresa max = new CantiereImpresa();
				if (!minField.getText().isEmpty() && !maxField.getText().isEmpty()) {
					try {
						min.setValore(Long.parseLong(minField.getText()));
						max.setValore(Long.parseLong(maxField.getText()));
						stampaEtichette(etichetteCantiere);
						stampaArray(Report.valoriCompresi(impresa.getListaCantieri(), min, max,
								(CantiereImpresa o1, CantiereImpresa o2) -> {
									if (o1.getValore() == o2.getValore())
										return 0;
									if (o1.getValore() > o2.getValore())
										return 1;
									return -1;
								}));
						valoriFrame.setVisible(false);
						msgSistema.setText(statoUscita(0) + "Cantieri che rispettino le condizioni stampato");
					} catch (NumberFormatException e) {
						inviaButton.setBackground(Color.RED);
						timerColor.start();
						if (!minField.getText().matches("\\d+"))
							minField.setBackground(Color.RED);
						if (!maxField.getText().matches("\\d+"))
							maxField.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Uno o più formati dei campi  non sono validi");
					}
				} else {
					inviaButton.setBackground(Color.RED);
					timerColor.start();
					if (minField.getText().isEmpty())
						minField.setBackground(Color.RED);
					if (maxField.getText().isEmpty())
						maxField.setBackground(Color.RED);
					msgSistema.setText(statoUscita(1) + "Riempire i campi");
				}
			});
			panel.add(new JLabel("Valore minimo"));
			panel.add(minField);
			panel.add(new JLabel("Valore massimo"));
			panel.add(maxField);
			panel.add(annullaButton);
			panel.add(inviaButton);
			valoriFrame.add(panel);
			valoriFrame.setVisible(true);

		});
		itemStampaCantieriValoriCompresi
				.setToolTipText("Stampa tutti i cantieri che rispettino le condizioni del valore");

		JMenu cantiereMenu = menu("Cantieri", itemStampaCantieri, itemStampaCantieriValoriCompresi);
		menu.add(cantiereMenu);

		JMenuItem itemTrovaProdotto = itemMenu("Trova prodotto", (ActionEvent event) -> {

			ArrayList<Fornitore> fornitori = aggiornaFornitori();
			if (fornitori != null) {

				impresa.setListaFornitori(fornitori);

				JFrame trovaPFrame = new JFrame();
				trovaPFrame.setUndecorated(true);
				trovaPFrame.setSize(getWidth() / 3, getHeight() / 5);
				trovaPFrame.setLocationRelativeTo(null);

				JTextField nomeField = new JTextField(15);
				nomeField.addMouseListener(erroreFieldListener);
				nomeField.setToolTipText("Nome del prodotto");

				JPanel panel = new JPanel(new GridLayout(2, 2));
				panel.setBorder(new EtchedBorder());

				JButton annullaButton = new JButton("Annulla");
				annullaButton.setBackground(buttonsColor1);
				annullaButton.setToolTipText("Annulla l'operazione");
				annullaButton.addActionListener((ActionEvent event1) -> trovaPFrame.setVisible(false));

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText("Stampa i fornitori che hanno il prodotto e il nome e prezzo del prodotto");
				inviaButton.addActionListener((ActionEvent event1) -> {
					Timer timerColor = new Timer(600,
							(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
					String nome = nomeField.getText();
					String etichette[] = { "NOME FORNITORE", "IVA FORNITORE", "NOME PRODOTTO", "CODICE PRODOTTO",
							"PREZZO" };
					if (nome.isEmpty()) {
						nomeField.setBackground(Color.RED);
						inviaButton.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
						timerColor.start();
					} else {
						stampaEtichette(etichette);
						Prodotto prodotto = new Prodotto();
						prodotto.setNome(nome);
						ArrayList<Fornitore> arrayF = Report
								.fornitoriSpecificoProdottoOrServizio(impresa.getListaFornitori(), prodotto);
						if (arrayF == null) {
							trovaPFrame.setVisible(false);
							textArea.append("Nessun risultato\n");
							msgSistema.setText(statoUscita(0) + "Fornitori e prodotto stampato");
							stampaDivisore();
						} else {
							for (Fornitore forni : arrayF) {
								textArea.append(forni.getNome() + " - " + forni.getPartitaIVA() + " - ");
								for (Prodotto prod : forni.getProdottiDisponibili())
									if (prod.getNome().equalsIgnoreCase(prodotto.getNome()))
										textArea.append(prod.getNome() + " - " + prod.getCodice() + " - "
												+ prod.getPrezzo() + "\n");
							}
							trovaPFrame.setVisible(false);
							msgSistema.setText(statoUscita(0) + "Fornitori e prodotto stampato");
							stampaDivisore();
							stampaDivisore();
						}
					}
				});

				panel.add(new JLabel("Nome prodotto"));
				panel.add(nomeField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				trovaPFrame.add(panel);
				trovaPFrame.setVisible(true);
			}
		});
		itemTrovaProdotto.setToolTipText("Trova un prodotto da tutti i fornitori");

		JMenuItem itemTrovaServizio = itemMenu("Trova servizio", (ActionEvent event) -> {

			ArrayList<Fornitore> fornitori = aggiornaFornitori();
			if (fornitori != null) {

				impresa.setListaFornitori(fornitori);

				JFrame trovaSFrame = new JFrame();
				trovaSFrame.setUndecorated(true);
				trovaSFrame.setSize(getWidth() / 3, getHeight() / 5);
				trovaSFrame.setLocationRelativeTo(null);

				JTextField nomeField = new JTextField(15);
				nomeField.addMouseListener(erroreFieldListener);
				nomeField.setToolTipText("Nome del servizio");

				JPanel panel = new JPanel(new GridLayout(2, 2));
				panel.setBorder(new EtchedBorder());

				JButton annullaButton = new JButton("Annulla");
				annullaButton.setBackground(buttonsColor1);
				annullaButton.setToolTipText("Annulla l'operazione");
				annullaButton.addActionListener((ActionEvent event1) -> trovaSFrame.setVisible(false));

				JButton inviaButton = new JButton("Invia");
				inviaButton.setBackground(buttonsColor1);
				inviaButton.setToolTipText(
						"Stampa i fornitori che hanno il servizio e il nome e numero di telefono del servizio");
				inviaButton.addActionListener((ActionEvent event1) -> {
					Timer timerColor = new Timer(600,
							(ActionEvent eventTimerColor) -> inviaButton.setBackground(buttonsColor1));
					String nome = nomeField.getText();
					String etichette[] = { "NOME FORNITORE", "IVA FORNITORE", "NOME SERVIZIO", "NUMERO TEL SERVIZIO",
							"PREZZO" };
					if (nome.isEmpty()) {
						nomeField.setBackground(Color.RED);
						inviaButton.setBackground(Color.RED);
						msgSistema.setText(statoUscita(1) + "Riempire i campi");
						timerColor.start();
					} else {
						stampaEtichette(etichette);
						Servizio servizio = new Servizio();
						servizio.setNome(nome);
						ArrayList<Fornitore> arrayF = Report
								.fornitoriSpecificoProdottoOrServizio(impresa.getListaFornitori(), servizio);
						if (arrayF == null) {
							trovaSFrame.setVisible(false);
							textArea.append("Nessun risultato\n");
							msgSistema.setText(statoUscita(0) + "Fornitori e servizio stampato");
						} else {
							for (Fornitore forni : arrayF) {
								textArea.append(forni.getNome() + " - " + forni.getPartitaIVA() + " - ");
								for (Servizio serv : forni.getServiziDisponibili())
									if (serv.getNome().equalsIgnoreCase(servizio.getNome()))
										textArea.append(serv.getNome() + " - " + serv.getNumeroServizioClienti() + " - "
												+ serv.getPrezzo() + "\n");
							}
							trovaSFrame.setVisible(false);
							msgSistema.setText(statoUscita(0) + "Fornitori e servizio stampato");
						}
						stampaDivisore();
						stampaDivisore();
					}
				});

				panel.add(new JLabel("Nome servizio"));
				panel.add(nomeField);
				panel.add(annullaButton);
				panel.add(inviaButton);
				trovaSFrame.add(panel);
				trovaSFrame.setVisible(true);
			}
		});
		itemTrovaServizio.setToolTipText("Trova un servizio da tutti i fornitori");

		JMenu fornitoriMenu = menu("Fornitori", itemTrovaProdotto, itemTrovaServizio);
		menu.add(fornitoriMenu);

		JMenuItem itemStampaClienti = itemMenu("Stampa clienti", (ActionEvent) -> {
			String etichetteClienti[] = { "CFSSN", "NOME", "COGNOME", "NUMERO TELEFONO", "CODICE CANTIERE" };
			stampaEtichette(etichetteClienti);
			stampaArray(impresa.getListaClienti());
			stampaDivisore();
			msgSistema.setText(statoUscita(0) + "Clienti stampato");
		});
		itemStampaClienti.setToolTipText("Stampa tutti i clienti");

		JMenu clientiMenu = menu("Clienti", itemStampaClienti);
		menu.add(clientiMenu);

		return menu;
	}

	private <T> void stampaArray(ArrayList<T> array) {

		if (array != null && array.size() > 0) {
			if (array.get(0).getClass() == Personale.class || array.get(0).getClass() == Dirigente.class) {
				for (T element : array) {
					Dirigente dirigente = Utilità.rewindCast(element, Dirigente.class);
					Personale per = Utilità.rewindCast(element, Personale.class);
					if (dirigente != null)
						textArea.append(dirigente.getCognome() + " - " + dirigente.getNome() + " - "
								+ dirigente.getCfSnn() + " - " + dirigente.getProfessione() + " - "
								+ dirigente.getNumeroAttualeCantieri() + " - " + dirigente.getNumeroMassimoCantieri()
								+ "\n");
					else
						textArea.append(per.getCognome() + " - " + per.getNome() + " - " + per.getCfSnn() + " - "
								+ per.getProfessione() + "\n");
				}
			} else if (array.get(0).getClass() == CantiereImpresa.class) {
				for (T element : array) {
					CantiereImpresa cantiere = Utilità.rewindCast(element, CantiereImpresa.class);
					textArea.append(cantiere.getCodice() + " - " + cantiere.getLocalità() + " - " + cantiere.getValore()
							+ " - ");
					if (cantiere.getResponsabile() == null)
						textArea.append("Nessun risultato" + " - ");
					else
						textArea.append(cantiere.getResponsabile().getCfSnn() + " - ");
					if (cantiere.getCaposquadra() == null)
						textArea.append("Nessun risultato" + " - ");
					else
						textArea.append(cantiere.getCaposquadra().getCfSnn() + " - ");
					if (cantiere.getPermesso() == null)
						textArea.append("Nessun risultato" + " - ");
					else
						textArea.append(cantiere.getPermesso().getStato() + " - ");
					textArea.append(cantiere.getStato() + "\n - PERSONALE SUL CANTIERE: ");
					stampaEtichette(etichettePersonale);
					if (cantiere.getPersonale() == null) {
						textArea.append("Nessun risultato\n\n");
						stampaDivisore();
					} else {
						stampaArray(cantiere.getPersonale());
					}
				}
			} else if (array.get(0).getClass() == Cliente.class) {
				for (T element : array) {
					Cliente cliente = Utilità.rewindCast(element, Cliente.class);
					textArea.append(cliente.getCfSnn() + " - " + cliente.getNome() + " - " + cliente.getCognome()
							+ " - " + cliente.getNumeroTelefono() + " - ");
					if (cliente.controllaCantiereVuoto())
						textArea.append("cantiere vuoto\n");
					else
						textArea.append(cliente.getCantiere().getCodice() + "\n");
				}
			} else if (array.get(0).getClass() == Permesso.class) {
				for (T element : array) {
					Permesso permesso = Utilità.rewindCast(element, Permesso.class);
					textArea.append(permesso.getCodice() + " - " + permesso.getStato() + "\n");
				}
			}
		} else
			textArea.append("Nessun risultato\n");
		stampaDivisore();
	}

	private void stampaDivisore() {
		for (int i = 0; i < WIDTH / 7; i++)
			textArea.append("-");
		textArea.append("\n");
	}

	private void stampaEtichette(String... etichette) {

		for (String str : etichette)
			textArea.append(" - " + str);
		textArea.append("\n");
	}

	private JMenu menu(String string, JMenuItem... menuItems) {

		JMenu menu = new JMenu(string);
		for (JMenuItem item : menuItems)
			menu.add(item);
		return menu;
	}

	private JMenuItem itemMenu(String string, ActionListener listener) {

		JMenuItem item = new JMenuItem(string);
		item.addActionListener(listener);
		item.addActionListener(autoScrollBarV);

		return item;
	}

	private String statoUscita(int stato) {

		Timer timerColor = new Timer(700,
				(ActionEvent event) -> msgSistema.setBackground(new JTextArea().getBackground()));
		timerColor.setRepeats(false);

		if (stato == 0) {
			msgSistema.setBackground(Color.GREEN);
			timerColor.start();
			return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - COMPLETATO - ";
		}
		if (stato == 1) {
			msgSistema.setBackground(Color.RED);
			timerColor.start();
			return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - ERRORE - ";
		} else
			return "";
	}

	/**
	 * Metodo per assegnare il path name relativo da dove caricare l'impresa.
	 * Default: Impresa.txt
	 * 
	 * @param nomeFileImpresa
	 */
	public void setNomeFileImpresa(String nomeFileImpresa) {

		this.nomeFileImpresa = nomeFileImpresa;
	}

	private ArrayList<Fornitore> aggiornaFornitori() {
		File file = new File("Fornitori.txt");
		ArrayList<Fornitore> fornitori = new ArrayList<>();
		try (FileInputStream inF = new FileInputStream(file); ObjectInputStream inO = new ObjectInputStream(inF);) {
			while (true)
				fornitori.add((Fornitore) inO.readObject());
		} catch (FileNotFoundException e1) {
			msgSistema.setText(
					statoUscita(1) + "File " + file.getName() + " non trovato. Impossibile aggiornare i fornitori");
			return null;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (EOFException e1) {
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return fornitori;

	}

	private void aperturaAndChiusura() {

		JFrame frameAperturaAndChiusura = new JFrame();
		frameAperturaAndChiusura.setUndecorated(true);
		JLabel stato = new JLabel("", SwingConstants.CENTER);
		frameAperturaAndChiusura.add(stato, BorderLayout.CENTER);
		stato.setBorder(new EtchedBorder());

		Timer timer = new Timer(2000, (ActionEvent event) -> System.exit(0));
		timer.setRepeats(false);

		this.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
				try (FileInputStream fileIn = new FileInputStream(nomeFileImpresa);
						ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
					impresa = (Impresa) objIn.readObject();
					if (impresa == null) {
						frameAperturaAndChiusura.setSize(getWidth() / 3, getHeight() / 5);
						frameAperturaAndChiusura.setLocationRelativeTo(null);
						stato.setText("File " + nomeFileImpresa + " vuoto." + " Chiusura in corso ...");
						frameAperturaAndChiusura.setVisible(true);
						timer.start();
					}
				} catch (FileNotFoundException e1) {
					frameAperturaAndChiusura.setSize(getWidth() / 3, getHeight() / 5);
					frameAperturaAndChiusura.setLocationRelativeTo(null);
					stato.setText("File " + nomeFileImpresa + " non trovato." + " Chiusura in corso ...");
					frameAperturaAndChiusura.setVisible(true);
					timer.start();
				} catch (IOException | ClassNotFoundException e1) {
					stato.setText("Errore. Chiusura in corso ...");
					timer.start();
				}
			}

			public void windowIconified(WindowEvent e) {

			}

			public void windowDeiconified(WindowEvent e) {

			}

			public void windowDeactivated(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {
				frameAperturaAndChiusura.setSize(getWidth() / 3, getHeight() / 5);
				frameAperturaAndChiusura.setLocationRelativeTo(null);
				stato.setText("Salvataggio in corso ...");
				frameAperturaAndChiusura.setVisible(true);

				impresa.setListaFornitori(new ArrayList<>());

				try (FileOutputStream fileOut = new FileOutputStream(nomeFileImpresa);
						ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
					objOut.writeObject(impresa);

				} catch (IOException e1) {
					stato.setText("Errore salvataggio. Chiusura in corso ...");
				} finally {
					timer.start();
				}

			}

			public void windowClosed(WindowEvent e) {

			}

			public void windowActivated(WindowEvent e) {

			}
		});
	}

	private class ErroreFieldListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			((JTextField) e.getSource()).setBackground(new JTextField().getBackground());

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

	}

}
