package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import java.io.Console;
import java.io.PrintWriter;

/**
 * 
 * @author Tobias Hunziger
 * @version 1.0
 * 
 */
public class TUI {
	
	public TUI(){
		Console con = System.console();
		if(con == null)
			System.exit(1);
		PrintWriter out = con.writer();
		this.drawMenu(out);
	}
	
	private final String SEPERATOR = "______________________________";
	
	private void drawMenu(PrintWriter out){
		
		out.println("BIBILIOTEK-BÜCHER-KUNDEN-VERWALTUNG");
		this.drawSeperator(out);
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
	
	private void drawSeperator(PrintWriter out){
		out.println(this.SEPERATOR);
		out.println();
	}

}
