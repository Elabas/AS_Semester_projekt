package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JTable;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.xml.bind.DataBindingException;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.GuiDbFactory;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

public class GUI {

	private JFrame frame;
	private JTextField textFeldSuche;
	private JButton btnB�cherSuche;
	private JButton btnKundenSuche;
	private JTable mainTable;
	private JMenu menuDatei;
	private JMenu menuKunden;
	private JMenu menuB�cher;
	private JMenu menuLeihen;
	private JMenuItem menuItemDatenbank;
	private JMenuItem menuItemXML;
	private JSeparator separator;
	private JMenuItem menuItemBeenden;
	private JMenuItem menuItemKundeErstellen;
	private JMenuItem menuItemKundeBearbeiten;
	private JMenuItem menuItemKundeSuchen;
	private JMenuItem menuItemKundeL�schen;
	private JMenuItem menuItemAusleihF�rKunde;
	private JMenuItem menuItemBuchErstellen;
	private JMenuItem menuItemBuchSuchen;
	private JMenuItem menuItemBuchBearbeiten;
	private JMenuItem menuItemBuchL�schen;
	private JMenuItem menuItemAusleihF�rBuch;
	private JMenuItem menuItem_5;
	private JMenuItem menuItem_6;
	private JMenuItem menuItem_7;
	private JMenuItem menuItem_8;
	private JMenuItem menuItem_9;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private Logic logic;
	private GUIActionListener actionListener;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JDOMException, IOException {
		new GUI(new Logic(new XML()));
	}

	/**
	 * Create the application.
	 */
	public GUI(Logic logic) {
		this.logic = logic;
		this.actionListener = GUIActionListener.getInstance(this, logic);
		initialize();
		frame.setVisible(true);
	}

	public void showDataBooks(List<Book> books){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"ISBN","Titel","Autor","Preis"});
		clearTable(dftm);
		for (Book book : books) {
			dftm.addRow(new Object[]{book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPrice()});
		}
	}
	
	public void showDataCustomer(List<Customer> customers){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"Kunden Nr.","Vorname","Nachname","Adrese","Ausleihvorgänge"});
		clearTable(dftm);
		
		for (Customer customer : customers) {
			dftm.addRow(new Object[]{customer.getCustomerID(),customer.getName(),customer.getSurname(),customer.getAddress(),customer.getLoanList().size()});
		}
		
		
	}
	
	public void showDataLoan(List<Loan> loans){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"Buch Titel","Buch Isbn","Kunden Nr.","Kunde Vorname","Kunden Nachname"," Ausgeliehen am","Ausgelihen bis"});
		clearTable(dftm);
		
		for (Loan loan : loans) {
			dftm.addRow(new Object[]{loan.getBook().getTitle(),loan.getBook().getIsbn(),loan.getCostumer().getCustomerID(),loan.getCostumer().getName(),loan.getCostumer().getSurname(),loan.getStartOfLoan(),loan.getEndOfLoan()});
		}	
	}
	
	private void clearTable(DefaultTableModel dftm){
		for (int i = dftm.getRowCount()-1; i >= 0; i--) {
			dftm.removeRow(i);
		}
	}
	
	public void refreshTableToDefault() {
		List<Customer> customers = logic.getCustomers();
		showDataCustomer(customers);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuDatei = new JMenu("Datei");
		menuBar.add(menuDatei);
		
		menuItemDatenbank = new JMenuItem("Datenbank Benutzen");
		menuDatei.add(menuItemDatenbank);
		
		menuItemXML = new JMenuItem("XML Benutzen");
		menuDatei.add(menuItemXML);
		
		separator = new JSeparator();
		menuDatei.add(separator);
		
		menuItemBeenden = new JMenuItem("Beenden");
		menuDatei.add(menuItemBeenden);
		
		menuKunden = new JMenu("Kunden");
		menuBar.add(menuKunden);
		
		menuItemKundeErstellen = new JMenuItem("Kunde Erstellen");
		menuKunden.add(menuItemKundeErstellen);
		
		menuItemKundeSuchen = new JMenuItem("Kunde Suchen");
		menuKunden.add(menuItemKundeSuchen);
		
		separator_1 = new JSeparator();
		menuKunden.add(separator_1);
		
		menuItemKundeBearbeiten = new JMenuItem("Kunde Bearbeiten");
		menuKunden.add(menuItemKundeBearbeiten);
		
		menuItemKundeL�schen = new JMenuItem("Kunde L\u00F6schen");
		menuKunden.add(menuItemKundeL�schen);
		
		separator_2 = new JSeparator();
		menuKunden.add(separator_2);
		
		menuItemAusleihF�rKunde = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuKunden.add(menuItemAusleihF�rKunde);
		
		menuB�cher = new JMenu("B\u00FCcher");
		menuBar.add(menuB�cher);
		
		menuItemBuchErstellen = new JMenuItem("Buch Erstellen");
		menuB�cher.add(menuItemBuchErstellen);
		
		menuItemBuchSuchen = new JMenuItem("Buch Suchen");
		menuB�cher.add(menuItemBuchSuchen);
		
		separator_3 = new JSeparator();
		menuB�cher.add(separator_3);
		
		menuItemBuchBearbeiten = new JMenuItem("Buch Bearbeiten");
		menuB�cher.add(menuItemBuchBearbeiten);
		
		menuItemBuchL�schen = new JMenuItem("Buch L\u00F6schen");
		menuB�cher.add(menuItemBuchL�schen);
		
		separator_4 = new JSeparator();
		menuB�cher.add(separator_4);
		
		menuItemAusleihF�rBuch = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuB�cher.add(menuItemAusleihF�rBuch);
		
		menuLeihen = new JMenu("Leihen");
		menuBar.add(menuLeihen);
		
		menuItem_5 = new JMenuItem("Kunde Erstellen");
		menuLeihen.add(menuItem_5);
		
		menuItem_6 = new JMenuItem("Kunde Suchen");
		menuLeihen.add(menuItem_6);
		
		separator_5 = new JSeparator();
		menuLeihen.add(separator_5);
		
		menuItem_7 = new JMenuItem("Kunde Bearbeiten");
		menuLeihen.add(menuItem_7);
		
		menuItem_8 = new JMenuItem("Kunde L\u00F6schen");
		menuLeihen.add(menuItem_8);
		
		separator_6 = new JSeparator();
		menuLeihen.add(separator_6);
		
		menuItem_9 = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuLeihen.add(menuItem_9);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		textFeldSuche = new JTextField();
		GridBagConstraints gbc_textFeldSuche = new GridBagConstraints();
		gbc_textFeldSuche.insets = new Insets(0, 0, 5, 0);
		gbc_textFeldSuche.gridwidth = 3;
		gbc_textFeldSuche.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFeldSuche.gridx = 8;
		gbc_textFeldSuche.gridy = 0;
		frame.getContentPane().add(textFeldSuche, gbc_textFeldSuche);
		textFeldSuche.setColumns(10);
		
		btnKundenSuche = new JButton("Kunden");
		GridBagConstraints gbc_btnKundenSuche = new GridBagConstraints();
		gbc_btnKundenSuche.insets = new Insets(0, 0, 5, 5);
		gbc_btnKundenSuche.gridx = 9;
		gbc_btnKundenSuche.gridy = 1;
		frame.getContentPane().add(btnKundenSuche, gbc_btnKundenSuche);
		
		btnB�cherSuche = new JButton("B\u00FCcher");
		GridBagConstraints gbc_btnB�cherSuche = new GridBagConstraints();
		gbc_btnB�cherSuche.insets = new Insets(0, 0, 5, 0);
		gbc_btnB�cherSuche.gridx = 10;
		gbc_btnB�cherSuche.gridy = 1;
		frame.getContentPane().add(btnB�cherSuche, gbc_btnB�cherSuche);
		
		mainTable = new JTable();
		GridBagConstraints gbc_mainTable = new GridBagConstraints();
		gbc_mainTable.gridwidth = 11;
		gbc_mainTable.insets = new Insets(0, 0, 0, 5);
		gbc_mainTable.fill = GridBagConstraints.BOTH;
		gbc_mainTable.gridx = 0;
		gbc_mainTable.gridy = 2;
		frame.getContentPane().add(mainTable, gbc_mainTable);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTable getMainTable() {
		return mainTable;
	}

	public void setMainTable(JTable mainTable) {
		this.mainTable = mainTable;
	}

	public JTextField getTextFeldSuche() {
		return textFeldSuche;
	}

	public JButton getBtnB�cherSuche() {
		return btnB�cherSuche;
	}

	public JButton getBtnKundenSuche() {
		return btnKundenSuche;
	}

	public JMenu getMenuKunden() {
		return menuKunden;
	}

	public JMenu getMenuB�cher() {
		return menuB�cher;
	}

	public JMenu getMenuLeihen() {
		return menuLeihen;
	}

	public JMenuItem getMenuItemDatenbank() {
		return menuItemDatenbank;
	}

	public JMenuItem getMenuItemXML() {
		return menuItemXML;
	}

	public JMenuItem getMenuItemBeenden() {
		return menuItemBeenden;
	}

	public JMenuItem getMenuItemKundeErstellen() {
		return menuItemKundeErstellen;
	}

	public JMenuItem getMenuItemKundeBearbeiten() {
		return menuItemKundeBearbeiten;
	}

	public JMenuItem getMenuItemKundeSuchen() {
		return menuItemKundeSuchen;
	}

	public JMenuItem getMenuItemKundeL�schen() {
		return menuItemKundeL�schen;
	}

	public JMenuItem getMenuItemAusleihF�rKunde() {
		return menuItemAusleihF�rKunde;
	}

	public JMenuItem getMenuItemBuchErstellen() {
		return menuItemBuchErstellen;
	}

	public JMenuItem getMenuItemBuchSuchen() {
		return menuItemBuchSuchen;
	}

	public JMenuItem getMenuItemBuchBearbeiten() {
		return menuItemBuchBearbeiten;
	}

	public JMenuItem getMenuItemBuchL�schen() {
		return menuItemBuchL�schen;
	}

	public JMenuItem getMenuItemAusleihF�rBuch() {
		return menuItemAusleihF�rBuch;
	}

	public void setMenuDatei(JMenu menuDatei) {
		this.menuDatei = menuDatei;
	}



}

