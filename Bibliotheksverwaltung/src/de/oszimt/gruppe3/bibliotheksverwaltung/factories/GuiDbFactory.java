package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.GUI;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 */
public class GuiDbFactory extends Library {

	@Override
	public void createFactory() {
		new GUI(new Logic(new DB()));
	}

}
