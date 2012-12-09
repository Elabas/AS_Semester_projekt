package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

public class GUIActionListener implements ActionListener {
	 private GUI gui;
	private IBusinessLogic logic;
	private static GUIActionListener instance;
	
	 private  GUIActionListener(GUI gui, IBusinessLogic logic){
		 this.logic = logic;
		 this.gui = gui;
	 }
	 
	 public static GUIActionListener getInstance(GUI gui, IBusinessLogic logic){
		 if(instance == null)
			 instance = new GUIActionListener(gui,logic);
		 return instance;
	 }
	 
	 @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gui.getBtnBuecherSuche()))
				searchBooks();
		else if(e.getSource().equals(gui.getBtnKundenSuche()))
				searchCustomer();
		else if(e.getSource().equals(gui.getMenuItemXML()))
				changeToXML();
		else if(e.getSource().equals(gui.getMenuItemDatenbank()))
				changeToDatabase();
		else if(e.getSource().equals(gui.getMenuItemBeenden()))
				closeAplication();
		else if(e.getSource().equals(gui.getMenuItemKundeErstellen()))
				createCustomer();
		else if(e.getSource().equals(gui.getMenuItemKundeBearbeiten()))
				updateCustomer();
		else if(e.getSource().equals(gui.getMenuItemKundeLueschen()))
				deleteCustomer();
		else if(e.getSource().equals(gui.getMenuItemBuchErstellen()))
				createBook();
		else if(e.getSource().equals(gui.getMenuItemBuchBearbeiten()))
				updateBook();
		else if(e.getSource().equals(gui.getMenuItemBuchLueschen()))
				deleteBook();
		else if(e.getSource().equals(gui.getMenuItemAusleihFuerKunde()))
				displayLoanForCustomer();
		else if(e.getSource().equals(gui.getMenuItemAusleihFuerBuch()))
				displayLoanForBook();
		else if(e.getSource().equals(gui.getMenuItemLoanBearbeiten()))
				updateLoan();
		else if(e.getSource().equals(gui.getMenuItemKundeFuerLoan()))
				displayCustomerForLoan();
		else if(e.getSource().equals(gui.getMenuItemBuchFuerLoan()))
				displayBookForLoan();

	}

	 
	 
	private void displayBookForLoan() {
		Loan loan = gui.getSelectetLoan();		
		// TODO 		
	}

	private void displayCustomerForLoan() {
		Loan loan = gui.getSelectetLoan();
		//TODO
	}

	private void updateLoan() {
		// TODO Auto-generated method stub
		
	}

	private void displayLoanForBook() {
		Book book = gui.getSelectetBook();
		gui.showDataLoan(logic.getLoansByBook(book));
	}

	private void displayLoanForCustomer() {
		Customer customer = gui.getSelectetCustomer();
		gui.showDataLoan(logic.getLoansByCostumer(customer));
	}

	private void deleteBook() {
		Book book = gui.getSelectetBook();
		if(book != null){
			if(!logic.deleteBook(book))
				new JOptionPane("Beim Löschen des Buches ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
			gui.showDataBooks(logic.getBooks());
		}else
			new JOptionPane("Beim Löschen des Buches ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
	}

	private void updateBook() {
		Book book = gui.getSelectetBook();
		book = Dialog.showBookDialog(book);
		if(book != null){
			if(!logic.updateBook(book))
				new JOptionPane("Beim Bearbeiten des Buches ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
			gui.showDataBooks(logic.getBooks());
		}else
			new JOptionPane("Beim Bearbeiten des Buches ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
	}

	private void createBook() {
		Book book = Dialog.showBookDialog();
		if(!logic.saveBook(book))
			new JOptionPane("Beim erstellen des Buches ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
		gui.showDataBooks(logic.getBooks());
		
	}

	private void deleteCustomer() {
		Customer costumer = gui.getSelectetCustomer();
		if(costumer != null){
			if(!logic.deleteCustomer(costumer))
				new JOptionPane("Beim Löschen des Kunden ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
			gui.showDataCustomer(logic.getCustomers());
		}else{
			new JOptionPane("Beim Löschen des Kunden ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void updateCustomer() {
		Customer customer = gui.getSelectetCustomer();
		if(customer != null){
			customer = Dialog.showCustomerDialog(customer);
			if(!logic.updateCustomer(customer)) 
				new JOptionPane("Beim Bearbeiten des Kunden ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
			gui.showDataCustomer(logic.getCustomers());
		}
		else
			new JOptionPane("Beim Bearbeiten des Kunden ist ein Fehler aufgetreten",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
	}

	private void createCustomer() {
		Customer customer  = Dialog.showCustomerDialog();
		if(!logic.saveCustomer(customer))
			new JOptionPane("Beim erstellen des Kunden ist ein Fehler aufgetreten", JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
		gui.showDataCustomer(logic.getCustomers());
	}

	
	private void closeAplication() {
		logic.finish();
		System.exit(0);
	}

	private void changeToDatabase() {
		if(!logic.changePersistence()){
			new JOptionPane("Beim Wechseln der Datenhaltung ist ein Fehler Aufgetreten.", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
			return;
		}else{
			gui.refreshTableToDefault();
		}
	}

	private void changeToXML() {
		if(!logic.changePersistence()){
			new JOptionPane("Beim Wechseln der Datenhaltung ist ein Fehler Aufgetreten.", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
			return;
		}else{
			gui.refreshTableToDefault();
		}
		
	}

	private void searchCustomer() {
		gui.showDataCustomer(logic.searchCustomer(gui.getTextFeldSuche().getText()));
	}

	private void searchBooks() {
		gui.showDataBooks(logic.searchBook(gui.getTextFeldSuche().getText()));
	}

}
