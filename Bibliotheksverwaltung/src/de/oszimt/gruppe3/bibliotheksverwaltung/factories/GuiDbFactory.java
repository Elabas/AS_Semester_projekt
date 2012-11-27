package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import java.io.IOException;
import java.sql.SQLException;

import org.jdom2.JDOMException;

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
		try {
			new GUI(new Logic(new DB()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
