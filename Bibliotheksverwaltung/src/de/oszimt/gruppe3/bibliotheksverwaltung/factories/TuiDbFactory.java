package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import java.io.IOException;
import java.sql.SQLException;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.GUI;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.IUserInterface;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.TUI;

/**
 * 
 * @author Tim M�ller
 * @version 1.0
 */
public class TuiDbFactory implements ILibrary {

	@Override
	public IUserInterface createUserInterface() {
		return new TUI();
	}

	@Override
	public IDataStorage createDataStorage() {
		try {
			return new DB();
		} catch (ClassNotFoundException e) {
			return null;
		} catch (SQLException e) {
			return null;
		} catch (JDOMException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}
