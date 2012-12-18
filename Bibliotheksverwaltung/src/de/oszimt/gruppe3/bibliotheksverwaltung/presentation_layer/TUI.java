package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import java.io.Console;
import java.io.PrintWriter;
import java.util.List;

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
		
		out.println("BIBILIOTEK-BÜCHER-KUNDEN-VERWALTUNG");
		this.drawSeperator();
		while(true){
			out.println("Anzeigen (a)");
			out.println("Bearbeiten (b)");
			out.println("Löschen (c)");
			out.println("Erstellen (d)");
			out.println("");
			out.print("Ihre Eingabe: ");
			String in = System.console().readLine();
		
			if(in.equals("a")){
				out.println("Kunden Anzeigen	(a)");
				out.println("Bücher Anzeigen	(b)");
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
				out.println("Bücher Bearbeiten	(b)");
				out.println("Ausleihen  Bearbeiten	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					showDataCustomer(logic.getCustomers());
					out.println("ID Auswählen");
					in = System.console().readLine();
					Customer customer = logic.readCustomer(Integer.parseInt(in));
					out.print("Name: ");
					String name = System.console().readLine();
					out.print("Vorname: ");
					String vorname  = System.console().readLine();
					out.print("Adresse: ");
					String adresse  = System.console().readLine();
					customer.setAddress(adresse);
					customer.setName(vorname);
					customer.setSurname(name);
					logic.updateCustomer(customer);
				}else if(in.equals("b")){
					showDataBook(logic.getBooks());
					out.println("ISBN Auswählen");
					in = System.console().readLine();
					Book book = logic.readBook(in);
					out.print("ISBN: ");
					book.setIsbn(System.console().readLine());
					out.print("Titel: ");
					book.setTitle(System.console().readLine());
					out.print("Author: ");
					book.setAuthor(System.console().readLine());
					out.print("Preis: ");
					book.setPrice(Double.parseDouble(System.console().readLine()));
					logic.updateBook(book);
				}else if(in.equals("c")){
					showDataLoan(logic.getLoans());
					out.println("ID Auswählen");
					in = System.console().readLine();
					Loan loan = logic.readLoan(Integer.parseInt(in));
					
					out.println("Bücher: ");
					showDataBook(logic.getBooks());
					out.println("Buch ISBN Auswählen: ");
					Book book = logic.readBook(System.console().readLine());
					out.println("Kunde: ");
					showDataCustomer(logic.getCustomers());
					out.println("Kunden ID Auswählen: ");
					Customer customer = logic.readCustomer(Integer.parseInt(System.console().readLine()));
						out.println("Ausleih Datum eingeben (dd:mm:yy):");
						String startDate = System.console().readLine();
						out.println("End Datum eingeben (dd:mm:yy):");
						String endDate = System.console().readLine();
						
						loan.setBook(book);
						loan.setCostumer(customer);
						loan.setStartOfLoan(startDate);
						loan.setEndOfLoan(endDate);
						
						logic.updateLoan(loan);
					
				}
			}else if(in.equals("c")){
				out.println("Kunden Löschen	(a)");
				out.println("Bücher Löschen	(b)");
				out.println("Ausleihen  Löschen	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					showDataCustomer(logic.getCustomers());
					out.println("ID Auswählen");
					in = System.console().readLine();
					Customer customer = logic.readCustomer(Integer.parseInt(in));
					logic.deleteCustomer(customer);
				}else if(in.equals("b")){
					showDataBook(logic.getBooks());
					out.println("ISBN Auswählen");
					in = System.console().readLine();
					Book book = logic.readBook(in);
					logic.deleteBook(book);
				}else if(in.equals("c")){
					showDataLoan(logic.getLoans());
					out.println("ID Auswählen");
					in = System.console().readLine();
					Loan loan = logic.readLoan(Integer.parseInt(in));
					logic.deleteLoan(loan);
				}
			}else if(in.equals("d")){
				out.println("Kunden Erstellen	(a)");
				out.println("Bücher Erstellen	(b)");
				out.println("Ausleihen  Erstellen	(c)");
				in = System.console().readLine();
				
				if(in.equals("a")){
					out.print("Name: ");
					String name = System.console().readLine();
					out.print("Vorname: ");
					String vorname  = System.console().readLine();
					out.print("Adresse: ");
					String adresse  = System.console().readLine();
					Customer customer = new Customer(vorname,name,adresse);
					logic.saveCustomer(customer);
				}else if(in.equals("b")){
					out.print("ISBN: ");
					String isbn = System.console().readLine();
					out.print("Titel: ");
					String titel = System.console().readLine();
					out.print("Autor: ");
					String autor = System.console().readLine();
					out.print("Preis: ");
					double price = Double.parseDouble(System.console().readLine());
					Book book = new Book(isbn,titel,autor,price) ;
					logic.saveBook(book) ;
				}else if(in.equals("c")){
					out.println("Bücher: ");
					showDataBook(logic.getBooks());
					out.println("Buch ISBN Auswählen: ");
					Book book = logic.readBook(System.console().readLine());
					out.println("Kunde: ");
					showDataCustomer(logic.getCustomers());
					out.println("Kunden ID Auswählen: ");
					Customer customer = logic.readCustomer(Integer.parseInt(System.console().readLine()));
					out.println("Ausleih Datum eingeben (dd:mm:yy):");
					String startDate = System.console().readLine();
					out.println("End Datum eingeben (dd:mm:yy):");
					String endDate = System.console().readLine();
				
					logic.saveLoan(new Loan(book, customer, startDate, endDate));
				}
			}
			
			out.println();
			out.print("Bitte Wählen Sie einen Menüpunkt: ");
		}
	}
	
	private void drawSeperator(){
		out.println(this.SEPERATOR);
		out.println();
	}
	
	public void showDataCustomer(List<Customer> customers){
		out.println("Kunden Nr.	|	Vorname		|	Nachname	|	Adresse");
		for (Customer customer : customers) {
			out.println(customer.getCustomerID()+"		|	"+customer.getName()+"		|	"+customer.getSurname()+"		|	"+customer.getAddress());
		}
	}
	
	public void showDataBook(List<Book> books){
		out.println("ISBN		|	Titel		|	Author	|	Preis");
		for (Book book : books) {
			out.println(book.getIsbn()+"		|	"+book.getTitle()+"		|	"+book.getAuthor()+"		|	"+book.getPrice());
		}
		}
	
	public void showDataLoan(List<Loan> loans){
		out.println("Ausleih ID	|	Buch Titel		|	ISBN	|	Kunden Vorname	|	Kunden Nachname	|	Ausgeliehen an	|	Ausgeiehen bis");
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
		out.print("\n");
		out.print("|  ! "+msg+" ! |\n");
		for (int i = 0; i < length+8; i++) {
			out.print("-");
		}
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
