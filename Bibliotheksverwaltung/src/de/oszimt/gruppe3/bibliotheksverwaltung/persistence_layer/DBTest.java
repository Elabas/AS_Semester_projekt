package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		// Customer customer = new Customer("Horst", "Schlämmer",
		// "Horstweg 1, Grevenbroich") ;
		// System.out.println(data.createCustomer(customer)) ;
		// Book book = new Book("978-3826659638", "SCJP", "Terence Gronowski",
		// 49.95) ;
		// data.createBook(book) ;
		Book book = data.readBook("978-3826659638");
		Customer customer = data.readCustomer(0);
//		System.out.println(data.createLoan(new Loan(book, customer, d1, d2)));
		data.closeDataStorage();
	}

}
