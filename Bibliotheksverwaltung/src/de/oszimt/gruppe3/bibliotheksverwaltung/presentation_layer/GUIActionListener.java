package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

public class GUIActionListener implements ActionListener {
	 private GUI gui;
	private static GUIActionListener instance;
	private Logic logic;
	
	 private  GUIActionListener(GUI gui, Logic logic){
		 this.gui = gui;
		 this.logic = logic;
	 }
	 
	 public static GUIActionListener getInstance(GUI gui, Logic logic){
		 if(instance == null)
			 instance = new GUIActionListener(gui,logic);
		 return instance;
	 }
	 
	 @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(gui.getBtnBücherSuche()))
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

	}

	private void createCustomer() {
		
		
		Customer customer = new Customer(name, surname, address);
		logic.saveCustomer(customer);
	}

	private void closeAplication() {
		logic.finish();
		System.exit(0);
	}

	private void changeToDatabase() {
		if(!	logic.changePersistence(){
			new JOptionPane("Beim Wechseln der Datenhaltung ist ein Fehler Aufgetreten.", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
			return;
		}else{
			gui.refreshTableToDefault();
		}
	}

	private void changeToXML() {
		if(!	logic.changePersistence(){
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
