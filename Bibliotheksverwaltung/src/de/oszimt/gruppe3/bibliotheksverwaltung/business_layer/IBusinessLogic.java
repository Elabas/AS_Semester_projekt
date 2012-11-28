package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

/**
 * Datei: IBusinessLogic.java Aufgabe: Interface für das Fachkonzept
 * 
 * @author Tim Müller
 * @version 1.6 vom 28.11.2012
 * 
 */
public interface IBusinessLogic {

	public boolean saveBook(Book book);

	public boolean saveCustomer(Customer customer);

	public boolean saveLoan(Loan loan);

	public boolean updateBook(Book book);

	public boolean updateCustomer(Customer customer);

	public boolean updateLoan(Loan loan);

	public boolean deleteBook(Book book);

	public boolean deleteCustomer(Customer costumer);

	public boolean deleteLoan(Loan loan);

	public Book readBook(String isbn);

	public Customer readCustomer(int customerID);

	public Loan readLoan(String isbn, int customerID);

	public Loan readLoan(int loanID);

	public List<Loan> getLoansByCostumer(Customer customer);

	public List<Loan> getLoansByBook(Book book);

	public List<Book> getBooks();

	public List<Customer> getCustomers();

	public List<Loan> getLoans();

	public int getBookCount();

	public int getCustomerCount();

	public int getLoanCount();

	public void finish();

	public boolean changePersistence();

	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan);

	public List<Customer> searchCustomer(String term);

	public List<Book> searchBook(String term);

	public void setDataStorage(IDataStorage dataStorage);
}
