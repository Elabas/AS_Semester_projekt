package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;

/**
 * 
 * @author Tobias Hunziger
 * @version 1.0
 * 
 */
public class TUI  {

	private IBusinessLogic logic ;
	private final String SEPERATOR = "-----------------------------------------------------------------------------------";

	public TUI(IBusinessLogic logic) {
		this.logic = logic ;
		this.drawMenu();
	}
	
	private void drawMenu(){
		System.out.println("BIBILIOTEK-BÜCHER-KUNDEN-VERWALTUNG");
		this.drawSeperator();
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Kunden suchen					(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		System.out.println("Neuen Kunden Anlegen			(a)");
		
		System.out.println();
		System.out.print("Bitte Wählen Sie einen Menüpunkt: ");
		
	}
	
	private void drawSeperator(){
		System.out.println(SEPERATOR);
		System.out.println();
	}
}
