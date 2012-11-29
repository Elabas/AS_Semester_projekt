package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.JDOMException;

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
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Customer customer = new Customer("Horst", "Schlämmer",
		// "Horstweg 1, Grevenbroich") ;
		// System.out.println(data.createCustomer(customer)) ;
		// Book book = new Book("978-3826659638", "SCJP", "Terence Gronowski",
		// 49.95) ;
		// data.createBook(book) ;
		Logic logic = new Logic(data);
		Book book = logic.readBook("978-3826659638");
		System.out.println(book);
		List<Book> bookList = logic.searchBook("SCJP");
		if (bookList != null) {
			System.out.println(bookList.get(0).getLoanList().get(0).getCostumer());
		}
		List<Customer> customerList = logic.searchCustomer("Schlämmer");
		if (customerList != null) {
			System.out.println(customerList.get(0).getLoanList().get(0).getBook());
		}
		// Customer customer = logic.readCustomer(2);
		// System.out.println(customer);
		// Loan loan = new Loan(book, customer, "17.11.2012", "23.11.2012") ;
		// Loan loan1 = new Loan(book, customer, "11.11.2012", "16.11.2012") ;
		// Loan loan2 = new Loan(book, customer, "24.11.2012", "27.11.2012") ;
		// Loan loan3 = new Loan(book, customer, "17.11.2012", "13.11.2012") ;
		// Loan loan4 = new Loan(book, customer, "15.11.2012", "21.11.2012") ;
		// Loan loan5 = new Loan(book, customer, "19.11.2012", "21.11.2012") ;
		// Loan loan6 = new Loan(book, customer, "19.11.2012", "27.11.2012") ;
//		 System.out.println("loan  :" + logic.isAvailable(book, "17.11.2012",
//		 "23.11.2012")); // false
//		 System.out.println("loan1 :" + logic.isAvailable(book, "11.11.2012",
//		 "16.11.2012")); // true
//		 System.out.println("loan2 :" + logic.isAvailable(book, "24.11.2012",
//		 "27.11.2012")); // true
//		 System.out.println("loan3 :" + logic.isAvailable(book, "17.11.2012",
//		 "13.11.2012")); // false
//		 System.out.println("loan4 :" + logic.isAvailable(book, "15.11.2012",
//		 "21.11.2012")); // false
//		 System.out.println("loan5 :" + logic.isAvailable(book, "19.11.2012",
//		 "21.11.2012")); // false
//		 System.out.println("loan6 :" + logic.isAvailable(book, "19.11.2012",
//		 "27.11.2012")); // false

		// Loan loan = data.readLoan(2) ;
		// System.out.println(loan);
		// System.out.println(data.getBookCount());
		// System.out.println(data.getCustomerCount());
		// System.out.println(data.getLoanCount());

		// System.out.println(logic.saveLoan(loan));
		// Book book = data.readBook("978-3826659638");
		// System.out.println(book);
		// book.setPrice(49.95) ;
		// System.out.println(logic.updateBook(book));
		// System.out.println(data.readBook("978-3826659638"));
		// System.out.println(logic.isDate("26.10.1987"));
		// System.out.println(logic.isDate("1.1.87"));
		// System.out.println(logic.isDate("xx.10.1987"));
		// System.out.println(logic.isDate("26.10.1987xx"));
		// System.out.println(logic.isDate("26.xx.1987"));
		// System.out.println(logic.deleteLoan(loan));
		// System.out.println(logic.deleteBook(book));
		// System.out.println(logic.deleteCustomer(customer));
		// data.closeDataStorage();
		logic.finish();
	}

}
