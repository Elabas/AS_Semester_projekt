package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.IUserInterface;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.TUI;

/**
 * Datei: TuiXmlFactory.java
 * Aufgabe: Fabrik zur Erzeugung einer Konsolenanwendung mit XML-Datei
 * 
 * @author Tim Müller
 * @version 1.0
 */
public class TuiXmlFactory implements ILibrary {

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
	 * @return eine neue XML-Schnittstelle
	 */
	@Override
	public IDataStorage createDataStorage() {
		try {
			return new XML();
		} catch (JDOMException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

}
