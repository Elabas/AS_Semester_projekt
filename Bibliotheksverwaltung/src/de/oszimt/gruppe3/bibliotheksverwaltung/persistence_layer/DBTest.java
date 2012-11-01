package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.oszimt.gruppe3.bibliotheksverwaltung.business_layer.Logic;
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
//		 Customer customer = new Customer("Horst", "Schlämmer",
//		 "Horstweg 1, Grevenbroich") ;
//		 System.out.println(data.createCustomer(customer)) ;
//		Book book = new Book("978-3826659638", "SCJP", "Terence Gronowski",
//		 49.95) ;
//		 data.createBook(book) ;
//		Book book = data.readBook("978-3826659638");
//		Customer customer = data.readCustomer(1);
//		Loan loan = data.readLoan(2) ;
//		System.out.println(loan);
//		System.out.println(data.getBookCount());
//		System.out.println(data.getCustomerCount());
//		System.out.println(data.getLoanCount());
		Logic logic = new Logic(data) ; 
//		Book book = data.readBook("978-3826659638");
//		System.out.println(book);
//		book.setPrice(49.95) ;
//		System.out.println(logic.updateBook(book));
//		System.out.println(data.readBook("978-3826659638"));
//		System.out.println(logic.isDate("26.10.1987"));
//		System.out.println(logic.isDate("1.1.87"));
//		System.out.println(logic.isDate("xx.10.1987"));
//		System.out.println(logic.isDate("26.10.1987xx"));
//		System.out.println(logic.isDate("26.xx.1987"));
//		System.out.println(logic.deleteLoan(loan));
//		System.out.println(logic.deleteBook(book));
//		System.out.println(logic.deleteCustomer(customer));
//		data.closeDataStorage();
		logic.finish() ;
	}

}
