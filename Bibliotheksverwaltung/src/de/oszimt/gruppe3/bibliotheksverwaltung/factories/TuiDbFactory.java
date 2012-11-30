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
 * Datei: TuiDbFactory.java Aufgabe: Fabrik zur Erzeugung einer
 * Konsolenanwendung mit Datenbank
 * 
 * @author Tim Müller
 * @version 1.0
 */
public class TuiDbFactory implements ILibrary {

	/**
	 * @since 1.0
	 * @return eine neue Konsolenanwendung
	 */
	@Override
	public IUserInterface createUserInterface() {
		return new TUI();
	}

	/**
	 * @since 1.0
	 * @return eine neue Datenbankverbindung
	 */
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
