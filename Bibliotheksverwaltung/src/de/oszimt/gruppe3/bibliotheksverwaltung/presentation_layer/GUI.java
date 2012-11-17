package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;

/**
 * 
 * @author Tobias Hunziger
 * @version 1.0
 * 
 */
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8657580223689145365L;
	private JMenuBar menu;
		private JMenu file;
			private JMenuItem useDatabase;
			private JMenuItem useXML;
			private JMenuItem close;
		private JMenu customer;
			private JMenuItem createCusto;
			private JMenuItem updateCusto;
			private JMenuItem deleteCusto;
			private JMenuItem showBooksForCusto;		
		private JMenu books;
			private JMenuItem createBook;
			private JMenuItem updateBook;
			private JMenuItem deleteBook;
			private JMenuItem showCustoForBook;
			
		private JPanel mainPanel;
		private JTable mainTable;
	
	public GUI(){
		init();
		this.setTitle("Bibilioteks Verwaltung");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init(){
		menu = new JMenuBar();
		file = new JMenu("Datei");
		{
			useDatabase = new JMenuItem("Datenbank Benutzen");
			useXML = new JMenuItem("XML Benutzen");
			close = new JMenuItem("Schließen");
			
			file.add(useDatabase);
			file.add(useXML);
			file.add(new JSeparator());
			file.add(close);
			
		}
		customer = new JMenu("Kunden");
		{
			createCusto = new JMenuItem("Neuen Kunden Anlegen");
			updateCusto = new JMenuItem("Kunden Bearbeiten");
			deleteCusto = new JMenuItem("Kunden Löschen");
			showBooksForCusto = new JMenuItem("Ausgeliehende Bücher Anzeigen");
			
			customer.add(createCusto);
			customer.add(new JSeparator());
			customer.add(updateCusto);
			customer.add(deleteCusto);
			customer.add(new JSeparator());
			customer.add(showBooksForCusto);
		}
		books = new JMenu("Bücher");
		{
			createBook = new JMenuItem("Neues Buch Anlegen");
			updateBook = new JMenuItem("Buch Bearbeiten");
			deleteBook = new JMenuItem("Buch Löschen");
			showCustoForBook = new JMenuItem("Ausleihenden Kunden Anzeigen");
			
			books.add(createBook);
			books.add(new JSeparator());
			books.add(updateBook);
			books.add(deleteBook);
			books.add(new JSeparator());
			books.add(showCustoForBook);
		}
		
		menu.add(file);
		menu.add(customer);
		menu.add(books);
		
		mainPanel = new JPanel();
		mainTable = new JTable();
		
		mainPanel.add(mainTable,BorderLayout.CENTER);
		
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(menu, BorderLayout.NORTH);
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}

}
