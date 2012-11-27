package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.GuiDbFactory;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.Library;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Library lib = new GuiDbFactory() ;
		lib.createFactory() ;
	}

}
