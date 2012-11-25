package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;

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
