package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 */
public interface IUserInterface {

	public void setLogic(IBusinessLogic logic) ;
	public void start() ;
}
