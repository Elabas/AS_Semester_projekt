package de.oszimt.gruppe3.bibliotheksverwaltung.model;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 *
 */
public class Costumer {

	private String name;
	private String surname;
	private int customerID;
	private String address;

	public Costumer(String name, String surname, int customerID, String address) {
		this.name = name;
		this.surname = surname;
		this.customerID = customerID;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
