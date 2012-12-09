package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

import java.io.Console;
import java.io.PrintWriter;
import java.util.List;

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
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Kunden suchen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		out.println("Neuen Kunden Anlegen (a)");
		
		out.println();
		out.print("Bitte Wählen Sie einen Menüpunkt: ");
	}
	
	private void drawSeperator(){
		out.println(this.SEPERATOR);
		out.println();
	}
	
	public void showDataCustomer(List<Customer> customers){
		
	}
	
	public void showDataBookr(List<Book> books){
			
		}
	
	public void showDataLoan(List<Loan> loans){
		
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
	}

}
