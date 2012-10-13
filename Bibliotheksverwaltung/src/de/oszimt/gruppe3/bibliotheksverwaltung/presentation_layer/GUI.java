package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.IBusinessLogic;

/**
 * 
 * @author Tobias Hunziger
 * @version 1.0
 * 
 */
public class GUI   {
	
	private IBusinessLogic logic ;

	public GUI(IBusinessLogic logic) {
		this.logic = logic ;
	}
}
