package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class GUIActionListener implements ActionListener {
	private GUI gui;
	private IBusinessLogic logic;
	private static GUIActionListener instance;

	private GUIActionListener(GUI gui) {
		this.gui = gui;
	}

	public static GUIActionListener getInstance(GUI gui) {
		if (instance == null)
			instance = new GUIActionListener(gui);
		return instance;
	}

	public void setLogic(IBusinessLogic logic) {
		this.logic = logic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(gui.getBtnBookSearch()))
			searchBooks();
		else if (e.getSource().equals(gui.getBtnCustomerSearch()))
			searchCustomer();
		else if (e.getSource().equals(gui.getMenuItemDataStorage()))
			changePersistence();
		else if (e.getSource().equals(gui.getMenuItemExit()))
			closeAplication();
		else if (e.getSource().equals(gui.getMenuItemCreateCustomer()))
			createCustomer();
		else if (e.getSource().equals(gui.getMenuItemEditCustomer()))
			updateCustomer();
		else if (e.getSource().equals(gui.getMenuItemDeleteCustomer()))
			deleteCustomer();
		else if (e.getSource().equals(gui.getMenuItemCreateBook()))
			createBook();
		else if (e.getSource().equals(gui.getMenuItemEditBook()))
			updateBook();
		else if (e.getSource().equals(gui.getMenuItemDeleteBook()))
			deleteBook();
		else if (e.getSource().equals(gui.getMenuItemLoanForCustomer()))
			displayLoanForCustomer();
		else if (e.getSource().equals(gui.getMenuItemLoanForBook()))
			displayLoanForBook();
		else if (e.getSource().equals(gui.getMenuItemEditLoan()))
			updateLoan();
		else if (e.getSource().equals(gui.getMenuItemCustomerForLoan()))
			displayCustomerForLoan();
		else if (e.getSource().equals(gui.getMenuItemBookForLoan()))
			displayBookForLoan();
		else if (e.getSource().equals(gui.getMenuItemDeleteLoan()))
			deleteLoan();
		else if (e.getSource().equals(gui.getMenuItemCreateLoan()))
			createLoan();

	}

	private void deleteLoan() {
		Loan loan = gui.getSelectedLoan();
		if (loan != null) {
			if (!logic.deleteLoan(loan))
				JOptionPane
						.showMessageDialog(null,
								"Beim Löschen des Ausleihvorganges ist ein Fehler aufgetreten");
			gui.showDataLoan(logic.getLoans());
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben keinen Ausleihvorgang ausgewählt");
	}

	private void createLoan() {
		Loan loan = Dialog.showLoanDialog(logic);
		if (loan == null)
			return;
		if (logic.isAvailable(loan.getBook(), loan.getStartOfLoan(),
				loan.getEndOfLoan())) {
			if (!logic.saveLoan(loan))
				JOptionPane
						.showMessageDialog(null,
								"Beim Bearbeiten des Ausleihvorganges ist ein Fehler aufgetreten");
			gui.showDataLoan(logic.getLoans());
		} else {
			JOptionPane.showMessageDialog(null,
					"Das Buch wird an diesen Tagen schon ausgeliehen");
		}
	}

	private void displayBookForLoan() {
		Loan loan = gui.getSelectedLoan();
		if (loan != null) {
			ArrayList<Book> list = new ArrayList<Book>();
			list.add(loan.getBook());
			gui.showDataBooks(list);
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben keinen Ausleihvorgang ausgewählt");
	}

	private void displayCustomerForLoan() {
		Loan loan = gui.getSelectedLoan();
		if (loan != null) {
			ArrayList<Customer> list = new ArrayList<Customer>();
			list.add(loan.getCostumer());
			gui.showDataCustomer(list);
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben keinen Ausleihvorgang ausgewählt");
	}

	private void updateLoan() {
		Loan loan = gui.getSelectedLoan();
		if (loan != null) {
			loan = Dialog.showLoanDialog(loan, logic);
			if (loan == null)
				return;
			if (logic.isAvailable(loan.getBook(), loan.getStartOfLoan(),
					loan.getEndOfLoan())) {
				if (!logic.updateLoan(loan))
					JOptionPane
							.showMessageDialog(null,
									"Beim Bearbeiten des Ausleihvorganges ist ein Fehler aufgetreten");
				gui.showDataLoan(logic.getLoans());
			} else
				JOptionPane.showMessageDialog(null,
						"Das Buch wird an diesen Tagen schon ausgeliehen");
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben keinen Ausleihvorgang ausgewählt");

	}

	private void displayLoanForBook() {
		Book book = gui.getSelectetedBook();
		if (book != null) {
			gui.showDataLoan(logic.getLoansByBook(book));
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben kein Buch ausgewählt");
	}

	private void displayLoanForCustomer() {
		Customer customer = gui.getSelectedCustomer();
		gui.showDataLoan(logic.getLoansByCostumer(customer));
	}

	private void deleteBook() {
		Book book = gui.getSelectetedBook();
		if (book != null) {
			if (!logic.deleteBook(book))
				JOptionPane
						.showMessageDialog(
								null,
								"Beim Löschen des Buches ist ein Fehler aufgetreten,\nevtl.ist es noch ausgeliehen");
			gui.showDataBooks(logic.getBooks());
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben kein Buch Ausgewählt!");
	}

	private void updateBook() {
		Book book = gui.getSelectetedBook();
		if (book != null) {
			book = Dialog.showBookDialog(book);
			if (book == null)
				return;
			if (!logic.updateBook(book))
				JOptionPane
						.showMessageDialog(null,
								"Beim Bearbeiten des Buches ist ein Fehler aufgetreten");
			gui.showDataBooks(logic.getBooks());
		} else
			JOptionPane.showMessageDialog(null,
					"Sie haben kein Buch Ausgewählt!");
	}

	private void createBook() {
		Book book = Dialog.showBookDialog();
		if (book == null)
			return;
		if (!logic.saveBook(book))
			JOptionPane.showMessageDialog(null,
					"Beim erstellen des Buches ist ein Fehler aufgetreten");
		gui.showDataBooks(logic.getBooks());

	}

	private void deleteCustomer() {
		Customer costumer = gui.getSelectedCustomer();
		if (costumer != null) {
			if (!logic.deleteCustomer(costumer))
				JOptionPane
						.showMessageDialog(
								null,
								"Beim Löschen des Kunden ist ein Fehler aufgetreten, \nevtl. hat er noch ein Buch ausgeliehen.");
			gui.showDataCustomer(logic.getCustomers());
		} else {
			JOptionPane.showMessageDialog(null,
					"Sie habe keinen Kunden Ausgewählt!");
		}

	}

	private void updateCustomer() {
		Customer customer = gui.getSelectedCustomer();
		if (customer != null) {
			customer = Dialog.showCustomerDialog(customer);
			if (customer == null)
				return;
			if (!logic.updateCustomer(customer))
				JOptionPane
						.showMessageDialog(null,
								"Beim Bearbeiten des Kunden ist ein Fehler aufgetreten");
			gui.showDataCustomer(logic.getCustomers());
		} else
			JOptionPane.showMessageDialog(null,
					"Sie habe keinen Kunden Ausgewählt!");
	}

	private void createCustomer() {
		Customer customer = Dialog.showCustomerDialog();
		if (customer == null)
			return;
		if (!logic.saveCustomer(customer))
			JOptionPane.showMessageDialog(null,
					"Beim erstellen des Kunden ist ein Fehler aufgetreten");
		gui.showDataCustomer(logic.getCustomers());
	}

	private void closeAplication() {
		logic.finish();
		System.exit(0);
	}

	private void changePersistence() {
		if (!logic.changePersistence()) {
			JOptionPane
					.showMessageDialog(null,
							"Beim Wechseln der Datenhaltung ist ein Fehler Aufgetreten.");
			return;
		} else {
			gui.refreshTableToDefault();
		}
	}

	private void searchCustomer() {
		if (gui.getTxtFieldSearch().getText().equals(""))
			gui.showDataCustomer(logic.getCustomers());
		else
			gui.showDataCustomer(logic.searchCustomer(gui.getTxtFieldSearch()
					.getText()));
	}

	private void searchBooks() {
		if (gui.getTxtFieldSearch().getText().equals(""))
			gui.showDataBooks(logic.getBooks());
		else
			gui.showDataBooks(logic.searchBook(gui.getTxtFieldSearch()
					.getText()));
	}

}
