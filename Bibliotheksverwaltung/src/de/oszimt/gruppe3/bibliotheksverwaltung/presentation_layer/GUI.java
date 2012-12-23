package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class GUI implements IUserInterface, WindowListener {

	private JFrame frame;
	private JTextField txtFieldSearch;
	private JButton btnBookSearch;
	private JButton btnCustomerSearch;
	private JTable mainTable;
	private JMenu menuFile;
	private JMenu menuCustomer;
	private JMenu menuBook;
	private JMenu menuLoan;
	private JMenuItem menuItemDataStorage;
	private JSeparator separator;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemCreateCustomer;
	private JMenuItem menuItemEditCustomer;
	private JMenuItem menuItemDeleteCustomer;
	private JMenuItem menuItemLoanForCustomer;
	private JMenuItem menuItemCreateBook;
	private JMenuItem menuItemEditBook;
	private JMenuItem menuItemDeleteBook;
	private JMenuItem menuItemLoanForBook;
	private JMenuItem menuItemDeleteLoan;
	private JMenuItem menuItemCreateLoan;
	private JMenuItem menuItemEditLoan;
	private JMenuItem menuItemCustomerForLoan;
	private JMenuItem menuItemBookForLoan;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JSeparator separator_5;
	private IBusinessLogic logic;
	private GUIActionListener actionListener;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
//	public static void main(String[] args)  {
//		new GUI();
//	}

	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }            
	    } 
	    catch (Exception e) {
	    	// Set System L&F
	        try {
				UIManager.setLookAndFeel(
				    UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e1) {
				System.exit(1) ;
			}
	    }
	    
	}
	/**
	 * Create the application.
	 */
	public GUI() {
		this.actionListener = GUIActionListener.getInstance(this);
		initialize();
	}

	public JTextField getTxtFieldSearch() {
		return txtFieldSearch;
	}

	public JButton getBtnBookSearch() {
		return btnBookSearch;
	}

	public JButton getBtnCustomerSearch() {
		return btnCustomerSearch;
	}

	public JMenu getMenuFile() {
		return menuFile;
	}

	public JMenu getMenuCustomer() {
		return menuCustomer;
	}

	public JMenu getMenuBook() {
		return menuBook;
	}

	public JMenu getMenuLoan() {
		return menuLoan;
	}

	public JMenuItem getMenuItemDataStorage() {
		return menuItemDataStorage;
	}

	public JMenuItem getMenuItemExit() {
		return menuItemExit;
	}

	public JMenuItem getMenuItemCreateCustomer() {
		return menuItemCreateCustomer;
	}

	public JMenuItem getMenuItemEditCustomer() {
		return menuItemEditCustomer;
	}

	public JMenuItem getMenuItemDeleteCustomer() {
		return menuItemDeleteCustomer;
	}

	public JMenuItem getMenuItemLoanForCustomer() {
		return menuItemLoanForCustomer;
	}

	public JMenuItem getMenuItemCreateBook() {
		return menuItemCreateBook;
	}

	public JMenuItem getMenuItemEditBook() {
		return menuItemEditBook;
	}

	public JMenuItem getMenuItemDeleteBook() {
		return menuItemDeleteBook;
	}

	public JMenuItem getMenuItemLoanForBook() {
		return menuItemLoanForBook;
	}

	public JMenuItem getMenuItemDeleteLoan() {
		return menuItemDeleteLoan;
	}

	public JMenuItem getMenuItemCreateLoan() {
		return menuItemCreateLoan;
	}

	public JMenuItem getMenuItemEditLoan() {
		return menuItemEditLoan;
	}

	public JMenuItem getMenuItemCustomerForLoan() {
		return menuItemCustomerForLoan;
	}

	public JMenuItem getMenuItemBookForLoan() {
		return menuItemBookForLoan;
	}

	public IBusinessLogic getLogic() {
		return logic;
	}

	public void showDataBooks(List<Book> books){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"ISBN","Titel","Autor","Preis"});
		clearTable(dftm);
		if(books != null)
		for (Book book : books) {
			dftm.addRow(new Object[]{book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPrice()});
		}
	}
	
	public void showDataCustomer(List<Customer> customers){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"Kunden Nr.","Vorname","Nachname","Adrese","Ausleihvorgänge"});
		clearTable(dftm);
		if(customers != null)
		for (Customer customer : customers) {
			dftm.addRow(new Object[]{customer.getCustomerID(),customer.getName(),customer.getSurname(),customer.getAddress(),customer.getLoanList().size()});
		}
		
		
	}
	
	public void showDataLoan(List<Loan> loans){
		DefaultTableModel dftm = (DefaultTableModel) mainTable.getModel();
		dftm.setColumnIdentifiers(new String[]{"Ausleih Nr.","Buch Titel","Buch Isbn","Kunden Nr.","Kunde Vorname","Kunden Nachname"," Ausgeliehen am","Ausgelihen bis"});
		clearTable(dftm);
		if(loans != null)
		for (Loan loan : loans) {
			dftm.addRow(new Object[]{loan.getLoanID(),loan.getBook().getTitle(),loan.getBook().getIsbn(),loan.getCostumer().getCustomerID(),loan.getCostumer().getName(),loan.getCostumer().getSurname(),loan.getStartOfLoan(),loan.getEndOfLoan()});
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
		frame = new JFrame() ;
		frame.addWindowListener(this) ;
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuFile = new JMenu("Datei");
		menuBar.add(menuFile);
		
		menuItemDataStorage = new JMenuItem("Datenhaltung wechseln");
		menuItemDataStorage.addActionListener(actionListener);
		menuFile.add(menuItemDataStorage);
		
		separator = new JSeparator();
		menuFile.add(separator);
		
		menuItemExit = new JMenuItem("Beenden");
		menuItemExit.addActionListener(actionListener);
		menuFile.add(menuItemExit);
		
		menuCustomer = new JMenu("Kunden");
		menuBar.add(menuCustomer);
		
		menuItemCreateCustomer = new JMenuItem("Kunde Erstellen");
		menuItemCreateCustomer.addActionListener(actionListener);
		menuCustomer.add(menuItemCreateCustomer);
		
		separator_1 = new JSeparator();
		menuCustomer.add(separator_1);
		
		menuItemEditCustomer = new JMenuItem("Kunde Bearbeiten");
		menuItemEditCustomer.addActionListener(actionListener);
		menuCustomer.add(menuItemEditCustomer);
		
		menuItemDeleteCustomer = new JMenuItem("Kunde L\u00F6schen");
		menuItemDeleteCustomer.addActionListener(actionListener);
		menuCustomer.add(menuItemDeleteCustomer);
		
		separator_2 = new JSeparator();
		menuCustomer.add(separator_2);
		
		menuItemLoanForCustomer = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuItemLoanForCustomer.addActionListener(actionListener);
		menuCustomer.add(menuItemLoanForCustomer);
		
		menuBook = new JMenu("B\u00FCcher");
		menuBar.add(menuBook);
		
		menuItemCreateBook = new JMenuItem("Buch Erstellen");
		menuItemCreateBook.addActionListener(actionListener);
		menuBook.add(menuItemCreateBook);
			
		separator_3 = new JSeparator();
		menuBook.add(separator_3);
		
		menuItemEditBook = new JMenuItem("Buch Bearbeiten");
		menuItemEditBook.addActionListener(actionListener);
		menuBook.add(menuItemEditBook);
		
		menuItemDeleteBook = new JMenuItem("Buch L\u00F6schen");
		menuItemDeleteBook.addActionListener(actionListener);
		menuBook.add(menuItemDeleteBook);
		
		separator_4 = new JSeparator();
		menuBook.add(separator_4);
		
		menuItemLoanForBook = new JMenuItem("Ausleihvorg\u00E4nge anzeigen");
		menuItemLoanForBook.addActionListener(actionListener);
		menuBook.add(menuItemLoanForBook);
		
		menuLoan = new JMenu("Leihen");
		menuBar.add(menuLoan);
		
		menuItemCreateLoan = new JMenuItem("Ausleihvorgang Erstellen");
		menuItemCreateLoan.addActionListener(actionListener);
		menuLoan.add(menuItemCreateLoan);
		
		menuItemDeleteLoan = new JMenuItem("Ausleihvorgang Löschen");
		menuItemDeleteLoan.addActionListener(actionListener);
		menuLoan.add(menuItemDeleteLoan);
		
		menuItemEditLoan = new JMenuItem("Ausleihvorgang Bearbeiten");
		menuItemEditLoan.addActionListener(actionListener);
		menuLoan.add(menuItemEditLoan);
		
		separator_5 = new JSeparator();
		menuLoan.add(separator_5);
		
		menuItemCustomerForLoan = new JMenuItem("Kunde Für Ausleihvorgang");
		menuItemCustomerForLoan.addActionListener(actionListener);
		menuLoan.add(menuItemCustomerForLoan);
		
		menuItemBookForLoan = new JMenuItem("Buch für Ausleihvorgang");
		menuItemBookForLoan.addActionListener(actionListener);
		menuLoan.add(menuItemBookForLoan);
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		txtFieldSearch = new JTextField();
		GridBagConstraints gbc_textFeldSuche = new GridBagConstraints();
		gbc_textFeldSuche.insets = new Insets(0, 0, 5, 0);
		gbc_textFeldSuche.gridwidth = 3;
		gbc_textFeldSuche.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFeldSuche.gridx = 8;
		gbc_textFeldSuche.gridy = 0;
		frame.getContentPane().add(txtFieldSearch, gbc_textFeldSuche);
		txtFieldSearch.setColumns(10);
		
		btnCustomerSearch = new JButton("Kunden");
		btnCustomerSearch.addActionListener(actionListener);
		GridBagConstraints gbc_btnKundenSuche = new GridBagConstraints();
		gbc_btnKundenSuche.insets = new Insets(0, 0, 5, 5);
		gbc_btnKundenSuche.gridx = 9;
		gbc_btnKundenSuche.gridy = 1;
		frame.getContentPane().add(btnCustomerSearch, gbc_btnKundenSuche);
		
		btnBookSearch = new JButton("B\u00FCcher");
		btnBookSearch.addActionListener(actionListener);
		GridBagConstraints gbc_btnBuecherSuche = new GridBagConstraints();
		gbc_btnBuecherSuche.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuecherSuche.gridx = 10;
		gbc_btnBuecherSuche.gridy = 1;
		frame.getContentPane().add(btnBookSearch, gbc_btnBuecherSuche);
		
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

	
	
	

	@Override
	public void setLogic(IBusinessLogic logic) {
		this.logic = logic;
		
	}

	@Override
	public void start() {
		this.actionListener.setLogic(logic) ;
		frame.setVisible(true);
		refreshTableToDefault();
	}

	public Customer getSelectedCustomer() {
		int row = mainTable.getSelectedRow();
		if(row == -1) return null;
		int customerID = Integer.parseInt(mainTable.getValueAt(row, 0).toString());
		Customer customer =  logic.readCustomer(customerID);
		return customer;
	}

	public Book getSelectetedBook() {
		int row = mainTable.getSelectedRow();
		if(row == -1) return null;
		String isbn =(String) mainTable.getValueAt(row, 0);
		Book book = logic.readBook(isbn);
		return book;
	}

	public Loan getSelectedLoan() {
		int row = mainTable.getSelectedRow();
		if(row == -1) return null;
		int loanId = (Integer) mainTable.getValueAt(row, 0);
		Loan loan = logic.readLoan(loanId);
		return loan;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
    	logic.finish() ;
        System.exit( 0 );          		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}

