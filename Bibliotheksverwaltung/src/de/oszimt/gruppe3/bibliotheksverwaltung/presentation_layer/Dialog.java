package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

/**
 * Kurzbeschreibung der Klasse
 * 
 * @version 1.0 vom 30.11.2012
 * @author Tobias Hunziger
 */
public class Dialog {

	public static Customer showCustomerDialog(Customer customer) {
		JTextField txtName = new JTextField(18);
		JLabel lbName = new JLabel("Vorname: ");
		JTextField txtSurename = new JTextField(18);
		JLabel lbSurename = new JLabel("Nochname: ");
		JTextField txtAddress = new JTextField(18);
		JLabel lbAddress = new JLabel("Adresse: ");
		JComponent[] inputs = new JComponent[] { lbName, txtName, lbSurename,
				txtSurename, lbAddress, txtAddress };

		if (customer != null) {
			txtName.setText(customer.getName());
			txtSurename.setText(customer.getSurname());
			txtAddress.setText(customer.getAddress());
		}

		int result = JOptionPane.showOptionDialog(null, inputs, "Benutzer Anlegen",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new String[] { "Speichern", "Abbrechen" }, "Speichern");

		if(result == JOptionPane.CANCEL_OPTION) return null;
		
		if (customer != null) {
			customer.setAddress(txtAddress.getText());
			customer.setName(txtName.getText());
			customer.setSurname(txtSurename.getText());
			return customer;
		}

		return new Customer(txtName.getText(), txtSurename.getText(),
				txtAddress.getText());
	}

	public static Customer showCustomerDialog() {
		return showCustomerDialog(null);
	}

	public static Book showBookDialog() {
		return showBookDialog(null);
	}
	
	public static Loan showLoanDialog(IBusinessLogic logic){
		return showLoanDialog(null,logic);
	}

	public static Loan showLoanDialog(Loan loan,IBusinessLogic logic) {
		JLabel lbBook = new JLabel("Bitte Buch Auswählen");
		JTable tableBook = new JTable();
		JLabel lbCustomer = new JLabel("Bitte Kunde Auswählen");
		JTable tableCustomer = new JTable();
		JLabel lbStartOfLoan = new JLabel("Ausleihdatum: ");
		JTextField txtStartOfLoan = new JTextField();
		JLabel lbEndOfLoan = new JLabel("Ausleihenddatum: ");
		JTextField txtEndOfLoan = new JTextField();
		
		DefaultTableModel dftm = (DefaultTableModel) tableBook.getModel();
		dftm.setColumnIdentifiers(new String[]{"ISBN","Titel","Autor","Preis"});
		List<Book> books = logic.getBooks();
		for (Book book : books) {
			dftm.addRow(new Object[]{book.getIsbn(),book.getTitle(),book.getAuthor(),book.getPrice()});
		}
		
		DefaultTableModel dftmc = (DefaultTableModel) tableCustomer.getModel();
		dftmc.setColumnIdentifiers(new String[]{"Kunden Nr.","Vorname","Nachname","Adrese","Ausleihvorgänge"});
		List<Customer> customers = logic.getCustomers();
		for (Customer customer : customers) {
			dftmc.addRow(new Object[]{customer.getCustomerID(),customer.getName(),customer.getSurname(),customer.getAddress(),customer.getLoanList().size()});
		}
		
		JComponent[] inputs = new JComponent[] { lbBook, tableBook, lbCustomer, tableCustomer, lbStartOfLoan, txtStartOfLoan, lbEndOfLoan, txtEndOfLoan };
		if(loan != null){
			txtStartOfLoan.setText(loan.getStartOfLoan());
			txtEndOfLoan.setText(loan.getEndOfLoan());
		}
	int result =	JOptionPane.showOptionDialog(null, inputs, "Ausleihvorgang Anlegen",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new String[] { "Speichern", "Abbrechen" }, "Speichern");

		if(result == JOptionPane.CANCEL_OPTION) return null;
		
		int row = tableBook.getSelectedRow();
		String isbn =(String) tableBook.getValueAt(row, 0);
		Book book = logic.readBook(isbn);
		
		row = tableCustomer.getSelectedRow();
		System.out.println(row);
		int customerID = Integer.parseInt(tableCustomer.getValueAt(row, 0).toString());
		Customer customer =  logic.readCustomer(customerID);
		
		if(loan != null){
			loan.setBook(book);
			loan.setCostumer(customer);
			loan.setStartOfLoan(txtStartOfLoan.getText());
			loan.setEndOfLoan(txtEndOfLoan.getText());
			return loan;
		}
		
		return new Loan(book, customer, txtStartOfLoan.getText(), txtEndOfLoan.getText());
	}

	public static Book showBookDialog(Book book) {
		JTextField txtIsbn = new JTextField(18);
		JLabel lbIsbn = new JLabel("ISBN: ");
		JTextField txtTitle = new JTextField(18);
		JLabel lbTitle = new JLabel("Titel: ");
		JTextField txtAuthor = new JTextField(18);
		JLabel lbAuthor = new JLabel("Author: ");
		JTextField txtPrize = new JTextField(18);
		JLabel lbPrize = new JLabel("Preis: ");
		JComponent[] inputs = new JComponent[] { lbIsbn,txtIsbn,lbTitle,txtTitle,lbAuthor,txtAuthor,lbPrize,txtPrize};

		if (book != null) {
			txtIsbn.setText(book.getIsbn());
			txtTitle.setText(book.getTitle());
			txtAuthor.setText(book.getAuthor());
			txtPrize.setText(book.getPrice()+"");
		}

		int result = JOptionPane.showOptionDialog(null, inputs, "Benutzer Anlegen",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new String[] { "Speichern", "Abbrechen" }, "Speichern");

		if(result == JOptionPane.CANCEL_OPTION) return null;
		
		if (book != null) {
			book.setIsbn(txtIsbn.getText());
			book.setAuthor(txtAuthor.getText());
			book.setTitle(txtTitle.getText());
			book.setPrice(Double.parseDouble(txtPrize.getText()));
			return book;
		}

		return new Book(txtIsbn.getText(), txtTitle.getText(), txtAuthor.getText(), Double.parseDouble(txtPrize.getText()));
	}

}
