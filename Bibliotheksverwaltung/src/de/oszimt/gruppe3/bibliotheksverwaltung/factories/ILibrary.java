package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.IUserInterface;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 */
public interface ILibrary {

	public IUserInterface createUserInterface();

	public IDataStorage createDataStorage();

}
