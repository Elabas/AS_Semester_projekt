package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;
import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.ILibrary;
import de.oszimt.gruppe3.bibliotheksverwaltung.factories.TuiDbFactory;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ILibrary lib = new TuiDbFactory();
		try {
			IUserInterface userInterface = lib.createUserInterface();
			IBusinessLogic logic = new Logic();
			IDataStorage dataStorage = lib.createDataStorage();
			logic.setDataStorage(dataStorage);
			userInterface.setLogic(logic);
			userInterface.start();
		} catch (NullPointerException npe) {
			System.out.println("Fehler bei der Fabrikerzeugung");
			npe.printStackTrace();
		}
	}
}
