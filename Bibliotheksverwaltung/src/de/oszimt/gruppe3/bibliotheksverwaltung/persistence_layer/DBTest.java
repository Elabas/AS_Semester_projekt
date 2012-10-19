package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.SQLException;
import java.util.Date;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IDataStorage data = null;
		try {
			data = new DB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer customer = new Customer("Horst", "Schlämmer", "Horstweg 1, Grevenbroich") ;
//		System.out.println(data.createCustomer(customer)) ;
		Book book = new Book("978-3826659638", "SCJP", "Terence Gronowski", 49.95) ;
//		data.createBook(book) ;
		System.out.println(data.readBook("978-3826659638").toString());
//		System.out.println(data.createLoan(new Loan(book,customer,new Date("19.10.2012"),new Date("26.10.2012"))));
		data.closeDataStorage() ;
	}

}
