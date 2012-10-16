package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TUI tui = new TUI(new Logic());
	}

}
