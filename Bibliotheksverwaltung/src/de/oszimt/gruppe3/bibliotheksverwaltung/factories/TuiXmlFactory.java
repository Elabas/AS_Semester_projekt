package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.TUI;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 */
public class TuiXmlFactory extends Library {

	@Override
	public void createFactory() {
		new TUI(new Logic(new XML()));
	}

}
