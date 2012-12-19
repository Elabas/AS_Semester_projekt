package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.io.Console;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;


/**
 * 
 * @author Tobias Hunziger
 * @version 1.0
 * 
 */
public class TUI  implements IUserInterface{
	
	public TUI(){
		Console con = System.console();
		if(con == null)
			System.exit(1);
		PrintWriter out = con.writer();
		this.out = out;
	}
	
	private final String SEPERATOR = "______________________________";
	private IBusinessLogic logic;
	private PrintWriter out ;
	
	private void drawMenu(){
		
		out.println("BIBILIOTEK-B�CHER-KUNDEN-VERWALTUNG");
		this.drawSeperator();
		while(true){
			out.println("Anzeigen (a)");
			out.println("Bearbeiten (b)");
			out.println("L�schen (c)");
			out.println("Erstellen (d)");
			out.println("Datenhaltung wechseln (e)");
			out.println("");
			out.println("Ihre Eingabe: ");
//			String in = System.console().readLine();
			String in = System.console().readLine() ;
		
			if(in.equals("a")){
				out.println("Kunden Anzeigen	(a)");
				out.println("B�cher Anzeigen	(b)");
				out.println("Ausleihen  Anzeigen	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					showDataCustomer(logic.getCustomers());
				}else if(in.equals("b")){
					showDataBook(logic.getBooks());
				}else if(in.equals("c")){
					showDataLoan(logic.getLoans());
				}
			}else if(in.equals("b")){
				out.println("Kunden Bearbeiten	(a)");
				out.println("B�cher Bearbeiten	(b)");
				out.println("Ausleihen  Bearbeiten	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					showDataCustomer(logic.getCustomers());
					out.println("ID Ausw�hlen");
					in = System.console().readLine();
					Customer customer = logic.readCustomer(Integer.parseInt(in));
					if(customer == null){
						drawError("Kunde Exestiert Nicht");
						continue;
					}
					out.println("Name: ");
					String name = System.console().readLine();
					out.println("Vorname: ");
					String vorname  = System.console().readLine();
					out.println("Adresse: ");
					String adresse  = System.console().readLine();
					customer.setAddress(adresse);
					customer.setName(vorname);
					customer.setSurname(name);
					if(!logic.updateCustomer(customer))
						drawError("Beim Bearbeiten des Kunden ist ein Fehler Aufgetreten");
				}else if(in.equals("b")){
					showDataBook(logic.getBooks());
					out.println("ISBN Ausw�hlen");
					in = System.console().readLine();
					Book book = logic.readBook(in);
					if(book == null){
						drawError("Buch Exestiert Nicht");
						continue;
					}
					out.println("ISBN: ");
					book.setIsbn(System.console().readLine());
					out.println("Titel: ");
					book.setTitle(System.console().readLine());
					out.println("Author: ");
					book.setAuthor(System.console().readLine());
					out.println("Preis: ");
					book.setPrice(Double.parseDouble(System.console().readLine()));
					if(!logic.updateBook(book))
						drawError("Beim Bearbeiten des Buches ist ein Fehler Aufgetreten");
				}else if(in.equals("c")){
					showDataLoan(logic.getLoans());
					out.println("ID Ausw�hlen");
					in = System.console().readLine();
					Loan loan = logic.readLoan(Integer.parseInt(in));
					if(loan == null){
						drawError("Ausleihvorgang Exestiert Nicht");
						continue;
					}
					out.println("B�cher: ");
					showDataBook(logic.getBooks());
					out.println("Buch ISBN Ausw�hlen: ");
					Book book = logic.readBook(System.console().readLine());
					out.println("Kunde: ");
					showDataCustomer(logic.getCustomers());
					out.println("Kunden ID Ausw�hlen: ");
					Customer customer = logic.readCustomer(Integer.parseInt(System.console().readLine()));
						out.println("Ausleih Datum eingeben (dd:mm:yy):");
						String startDate = System.console().readLine();
						out.println("End Datum eingeben (dd:mm:yy):");
						String endDate = System.console().readLine();
						
						loan.setBook(book);
						loan.setCostumer(customer);
						loan.setStartOfLoan(startDate);
						loan.setEndOfLoan(endDate);
						
						if(!logic.updateLoan(loan))
							drawError("Beim Bearbeiten des Ausleihvorganges ist ein Fehler Aufgetreten");
					
				}
			}else if(in.equals("c")){
				out.println("Kunden L�schen	(a)");
				out.println("B�cher L�schen	(b)");
				out.println("Ausleihen  L�schen	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					showDataCustomer(logic.getCustomers());
					out.println("ID Ausw�hlen");
					in = System.console().readLine();
					Customer customer = logic.readCustomer(Integer.parseInt(in));
					if(customer == null){
						drawError("Kunde Exestiert Nicht");
						continue;
					}
					if(!logic.deleteCustomer(customer))
						drawError("Beim L�schen des Kunden ist ein Fehler Aufgetreten");
				}else if(in.equals("b")){
					showDataBook(logic.getBooks());
					out.println("ISBN Ausw�hlen");
					in = System.console().readLine();
					Book book = logic.readBook(in);
					if(book == null){
						drawError("Buch Exestiert Nicht");
						continue;
					}
					if(!logic.deleteBook(book))
						drawError("Beim L�schen des Buches ist ein Fehler Aufgetreten");
				}else if(in.equals("c")){
					showDataLoan(logic.getLoans());
					out.println("ID Ausw�hlen");
					in = System.console().readLine();
					Loan loan = logic.readLoan(Integer.parseInt(in));
					if(loan == null){
						drawError("Ausleihvorgang Exestiert Nicht");
						continue;
					}
					if(!logic.deleteLoan(loan))
						drawError("Beim L�schen des Ausleihvorganges ist ein Fehler Aufgetreten");
				}
			}else if(in.equals("d")){
				out.println("Kunden Erstellen	(a)");
				out.println("B�cher Erstellen	(b)");
				out.println("Ausleihen  Erstellen	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					out.println("Name: ");
					String name = System.console().readLine();
					out.println("Vorname: ");
					String vorname  = System.console().readLine();
					out.println("Adresse: ");
					String adresse  = System.console().readLine();
					Customer customer = new Customer(vorname,name,adresse);
					if(!logic.saveCustomer(customer))
						drawError("Beim Erstellen des Kunden ist ein Fehler Aufgetreten");
				}else if(in.equals("b")){
					out.println("ISBN: ");
					String isbn = System.console().readLine();
					out.println("Titel: ");
					String titel = System.console().readLine();
					out.println("Autor: ");
					String autor = System.console().readLine();
					out.println("Preis: ");
					Double price = Double.parseDouble(System.console().readLine());
					Book book = new Book(isbn, titel, autor, price);
					if(!logic.saveBook(book))
						drawError("Beim Erstellen des Buches ist ein Fehler Aufgetreten");
				}else if(in.equals("c")){
					out.println("B�cher: ");
					showDataBook(logic.getBooks());
					out.println("Buch ISBN Ausw�hlen: ");
					Book book = logic.readBook(System.console().readLine());
					out.println("Kunde: ");
					showDataCustomer(logic.getCustomers());
					out.println("Kunden ID Ausw�hlen: ");
					Customer customer = logic.readCustomer(Integer.parseInt(System.console().readLine()));
					out.println("Ausleih Datum eingeben (dd:mm:yy):");
					String startDate = System.console().readLine();
					out.println("End Datum eingeben (dd:mm:yy):");
					String endDate = System.console().readLine();
				
					if(!logic.saveLoan(new Loan(book, customer, startDate, endDate)))
							drawError("Beim Erstellen des Ausleihvorganges ist ein Fehler Aufgetreten");
				}
			}else if(in.equals("e")){
				if(!logic.changePersistence())
					drawError("Beim Wechseln der Datenhaltung ist ein Fehler Aufgetreten");
			}
			
			out.println();
			out.println("Bitte W�hlen Sie einen Men�punkt: ");
		}
	}
	
	private void drawSeperator(){
		out.println(this.SEPERATOR);
		out.println();
	}
	
	public void showDataCustomer(List<Customer> customers){
		out.println("Kunden Nr.	|	Vorname		|	Nachname	|	Adresse");
		if(customers != null)
		for (Customer customer : customers) {
			out.println(customer.getCustomerID()+"		|	"+customer.getName()+"		|	"+customer.getSurname()+"		|	"+customer.getAddress());
		}
	}
	
	public void showDataBook(List<Book> books){
		out.println("ISBN		|	Titel		|	Author	|	Preis");
		if(books != null)
		for (Book book : books) {
			out.println(book.getIsbn()+"		|	"+book.getTitle()+"		|	"+book.getAuthor()+"		|	"+book.getPrice());
		}
		}
	
	public void showDataLoan(List<Loan> loans){
		out.println("Ausleih ID	|	Buch Titel		|	ISBN	|	Kunden Vorname	|	Kunden Nachname	|	Ausgeliehen an	|	Ausgeiehen bis");
		if(loans != null)
		for (Loan loan : loans) {
			out.println(loan.getLoanID()+"		|	"+loan.getBook().getTitle()+"		|	"+loan.getBook().getIsbn()+"		|	"+loan.getCostumer().getName()+"		|	"+loan.getCostumer().getSurname()+"		|	"+loan.getStartOfLoan()+"		|	"+loan.getEndOfLoan());
		}
	}
	
	public int readInputInt(){
		return 0;
	}
	
	public String readInputString(){
		return null;
	}

	public void drawError(String msg){
		int length = msg.length();
		
		for (int i = 0; i < length+8; i++) {
			out.print("-");
		}
		out.println("");
		out.println("|  ! "+msg+" ! |");
		for (int i = 0; i < length+8; i++) {
			out.print("-");
		}
		out.println("");
	}
	@Override
	public void setLogic(IBusinessLogic logic) {
		this.logic = logic;
	}

	@Override
	public void start() {
		drawMenu();
		showDataCustomer(logic.getCustomers());
	}

}
