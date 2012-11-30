package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.IUserInterface;

/**
 * Datei: ILibrary.java
 * Aufgabe: Interface f�r die Fabriken
 * 
 * @author Tim M�ller
 * @version 1.0 vom 28.11.2012
 */
public interface ILibrary {

	public IUserInterface createUserInterface();

	public IDataStorage createDataStorage();

}
