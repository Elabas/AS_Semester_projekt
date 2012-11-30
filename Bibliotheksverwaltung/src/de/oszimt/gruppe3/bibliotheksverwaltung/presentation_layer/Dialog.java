package de.oszimt.gruppe3.bibliotheksverwaltung.presentation_layer;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;

/**
 * Kurzbeschreibung der Klasse
 * 
 * @version 1.0 vom 30.11.2012
 * @author Tobias Hunziger
 */
public class Dialog {

	public static Customer showCustomerDialog(Customer customer) {
		JTextField txtName = new JTextField(18);
		JLabel lbName = new JLabel("Vorname: ");
		JTextField txtSurename = new JTextField(18);
		JLabel lbSurename = new JLabel("Nochname: ");
		JTextField txtAddress = new JTextField(18);
		JLabel lbAddress = new JLabel("Adresse: ");
		JComponent[] inputs = new JComponent[] { lbName, txtName, lbSurename,
				txtSurename, lbAddress, txtAddress };

		if (customer != null) {
			txtName.setText(customer.getName());
			txtSurename.setText(customer.getSurname());
			txtAddress.setText(customer.getAddress());
		}

		JOptionPane.showOptionDialog(null, inputs, "Benutzer Anlegen",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new String[] { "Speichern", "Abbrechen" }, "Speichern");

		if (customer != null) {
			customer.setAddress(txtAddress.getText());
			customer.setName(txtName.getText());
			customer.setSurname(txtSurename.getText());
			return customer;
		}

		return new Customer(txtName.getText(), txtSurename.getText(),
				txtAddress.getText());
	}

	public static Customer showCustomerDialog() {
		return showCustomerDialog(null);
	}

}
