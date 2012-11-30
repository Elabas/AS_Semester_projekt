package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DataBindingException;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.GuiDbFactory;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

public class GUI implements IUserInterface {

	private JFrame frame;
	private JTextField textFeldSuche;
	private JButton btnBuecherSuche;
	private JButton btnKundenSuche;
	private JTable mainTable;
	private JMenu menuDatei;
	private JMenu menuKunden;
	private JMenu menuBuecher;
	private JMenu menuLeihen;
	private JMenuItem menuItemDatenbank;
	private JMenuItem menuItemXML;
	private JSeparator separator;
	private JMenuItem menuItemBeenden;
	private JMenuItem menuItemKundeErstellen;
	private JMenuItem menuItemKundeBearbeiten;
	private JMenuItem menuItemKundeSuchen;
	private JMenuItem menuItemKundeLoeschen;
	private JMenuItem menuItemAusleihFuerKunde;
	private JMenuItem menuItemBuchErstellen;
	private JMenuItem menuItemBuchSuchen;
	private JMenuItem menuItemBuchBearbeiten;
	private JMenuItem menuItemBuchLoeschen;
	private JMenuItem menuItemAusleihFuerBuch;
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
	private IBusinessLogic logic;
	private GUIActionListener actionListener;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  {
		new GUI();
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	public IBusinessLogic getLogic() {
		return logic;
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
		dftm.setColumnIdentifiers(new String[]{"Kunden Nr.","Vorname","Nachname","Adrese","AusleihvorgÃ¤nge"});
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
		menuItemDatenbank.addActionListener(actionListener);
		menuDatei.add(menuItemDatenbank);
		
		menuItemXML = new JMenuItem("XML Benutzen");
		menuItemXML.addActionListener(actionListener);
		menuDatei.add(menuItemXML);
		
		separator = new JSeparator();
		menuDatei.add(separator);
		
		menuItemBeenden = new JMenuItem("Beenden");
		menuItemBeenden.addActionListener(actionListener);
		menuDatei.add(menuItemBeenden);
		
		menuKunden = new JMenu("Kunden");
		menuBar.add(menuKunden);
		
		menuItemKundeErstellen = new JMenuItem("Kunde Erstellen");
		menuItemKundeErstellen.addActionListener(actionListener);
		menuKunden.add(menuItemKundeErstellen);
		
		menuItemKundeSuchen = new JMenuItem("Kunde Suchen");
		menuItemKundeSuchen.addActionListener(actionListener);
		menuKunden.add(menuItemKundeSuchen);
		
		separator_1 = new JSeparator();
		menuKunden.add(separator_1);
		
		menuItemKundeBearbeiten = new JMenuItem("Kunde Bearbeiten");
		menuItemKundeBearbeiten.addActionListener(actionListener);
		menuKunden.add(menuItemKundeBearbeiten);
		
		menuItemKundeLoeschen = new JMenuItem("Kunde L\u00F6schen");
		menuItemKundeLoeschen.addActionListener(actionListener);
		menuKunden.add(menuItemKundeLoeschen);
		
		separator_2 = new JSeparator();
		menuKunden.add(separator_2);
		
		menuItemAusleihFuerKunde = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuItemAusleihFuerKunde.addActionListener(actionListener);
		menuKunden.add(menuItemAusleihFuerKunde);
		
		menuBuecher = new JMenu("B\u00FCcher");
		menuBar.add(menuBuecher);
		
		menuItemBuchErstellen = new JMenuItem("Buch Erstellen");
		menuItemBuchErstellen.addActionListener(actionListener);
		menuBuecher.add(menuItemBuchErstellen);
		
		menuItemBuchSuchen = new JMenuItem("Buch Suchen");
		menuItemBuchSuchen.addActionListener(actionListener);
		menuBuecher.add(menuItemBuchSuchen);
		
		separator_3 = new JSeparator();
		menuBuecher.add(separator_3);
		
		menuItemBuchBearbeiten = new JMenuItem("Buch Bearbeiten");
		menuItemBuchBearbeiten.addActionListener(actionListener);
		menuBuecher.add(menuItemBuchBearbeiten);
		
		menuItemBuchLoeschen = new JMenuItem("Buch L\u00F6schen");
		menuItemBuchLoeschen.addActionListener(actionListener);
		menuBuecher.add(menuItemBuchLoeschen);
		
		separator_4 = new JSeparator();
		menuBuecher.add(separator_4);
		
		menuItemAusleihFuerBuch = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuItemAusleihFuerBuch.addActionListener(actionListener);
		menuBuecher.add(menuItemAusleihFuerBuch);
		
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
		btnKundenSuche.addActionListener(actionListener);
		GridBagConstraints gbc_btnKundenSuche = new GridBagConstraints();
		gbc_btnKundenSuche.insets = new Insets(0, 0, 5, 5);
		gbc_btnKundenSuche.gridx = 9;
		gbc_btnKundenSuche.gridy = 1;
		frame.getContentPane().add(btnKundenSuche, gbc_btnKundenSuche);
		
		btnBuecherSuche = new JButton("B\u00FCcher");
		btnBuecherSuche.addActionListener(actionListener);
		GridBagConstraints gbc_btnBuecherSuche = new GridBagConstraints();
		gbc_btnBuecherSuche.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuecherSuche.gridx = 10;
		gbc_btnBuecherSuche.gridy = 1;
		frame.getContentPane().add(btnBuecherSuche, gbc_btnBuecherSuche);
		
		mainTable = new JTable();
		JScrollPane pane = new JScrollPane();
		mainTable.setModel(new DefaultTableModel());
		GridBagConstraints gbc_mainTable = new GridBagConstraints();
		gbc_mainTable.gridwidth = 11;
		gbc_mainTable.insets = new Insets(0, 0, 0, 5);
		gbc_mainTable.fill = GridBagConstraints.BOTH;
		gbc_mainTable.gridx = 0;
		gbc_mainTable.gridy = 2;
		pane.setViewportView(mainTable);
		frame.getContentPane().add(pane, gbc_mainTable);
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

	public JButton getBtnBuecherSuche() {
		return btnBuecherSuche;
	}

	public JButton getBtnKundenSuche() {
		return btnKundenSuche;
	}

	public JMenu getMenuKunden() {
		return menuKunden;
	}

	public JMenu getMenuBuecher() {
		return menuBuecher;
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

	public JMenuItem getMenuItemKundeLueschen() {
		return menuItemKundeLoeschen;
	}

	public JMenuItem getMenuItemAusleihFuerKunde() {
		return menuItemAusleihFuerKunde;
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

	public JMenuItem getMenuItemBuchLueschen() {
		return menuItemBuchLoeschen;
	}

	public JMenuItem getMenuItemAusleihFuerBuch() {
		return menuItemAusleihFuerBuch;
	}

	public void setMenuDatei(JMenu menuDatei) {
		this.menuDatei = menuDatei;
	}

	@Override
	public void setLogic(IBusinessLogic logic) {
		this.logic = logic;
		
	}

	@Override
	public void start() {
		this.actionListener = GUIActionListener.getInstance(this,logic);
		frame.setVisible(true);
	}



}

