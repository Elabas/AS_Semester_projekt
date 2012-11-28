package de.oszimt.gruppe3.bibliotheksverwaltung.factories;

import java.io.IOException;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.GUI;
import de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer.IUserInterface;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 */
public class GuiXmlFactory implements ILibrary {

	@Override
	public IUserInterface createUserInterface() {
		return new GUI();
	}

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
