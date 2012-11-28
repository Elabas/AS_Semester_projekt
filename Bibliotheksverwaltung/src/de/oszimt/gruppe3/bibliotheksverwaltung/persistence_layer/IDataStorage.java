package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

/**
 * Datei: IDataStorage.java Aufgabe: Interface für die Datenhaltung
 * 
 * @author Tim Müller
 * @version 1.6 vom 28.11.2012
 * 
 */
public interface IDataStorage {

	public boolean createBook(Book book);

	public boolean createCustomer(Customer customer);

	public boolean createLoan(Loan loan);

	public boolean updateBook(Book book);

	public boolean updateCustomer(Customer customer);

	public boolean updateLoan(Loan loan);

	public boolean deleteBook(Book book);

	public boolean deleteCustomer(Customer customer);

	public boolean deleteLoan(Loan loan);

	public Book readBook(String isbn);

	public Customer readCustomer(int customerID);

	public Loan readLoan(String isbn, int customerID);

	public Loan readLoan(int loanID);

	public List<Loan> getLoansByCustomer(Customer customer);

	public List<Loan> getLoansByBook(Book book);

	public List<Book> getBooks();

	public List<Customer> getCustomers();

	public List<Loan> getLoans();

	public int getBookCount();

	public int getCustomerCount();

	public int getLoanCount();

	public void openDataStorage() throws Exception;

	public boolean closeDataStorage();

	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan);

	public List<Customer> searchCustomer(String term);

	public List<Book> searchBook(String term);
}
