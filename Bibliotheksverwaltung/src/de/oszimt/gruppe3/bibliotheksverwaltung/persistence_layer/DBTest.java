package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IDataStorage data = new DB() ;
		Customer customer = new Customer("Horst", "Schlämmer", "Horstweg 1, Grevenbroich") ;
		System.out.println(data.createCustomer(customer)) ;
		Book book = new Book("978-3826659638", "SCJP", "Terence Gronowski", 49.95) ;
		data.createBook(book) ;
		data.closeDataStorage() ;
	}

}
