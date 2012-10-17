package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public interface IBusinessLogic {

	public boolean saveBook(Book book) ;
	public boolean saveCustomer(Customer customer) ;
	public boolean saveLoan(Loan loan) ;
	public boolean updateBook(Book book) ;
	public boolean updateCustomer(Customer customer) ;
	public boolean updateLoan(Loan loan) ;
	public boolean deleteBook(Book book) ;
	public boolean deleteCustomer(Customer costumer) ;
	public boolean deleteLoan(Loan loan) ;
	public Book readBook(String isbn) ;
	public Customer readCustomer(int customerID) ;
	public Loan readLoan(String isbn, int customerID) ;
	public List<Loan> getLoansByCostumer(Customer customer) ;
	public List<Loan> getLoansByBook(Book book) ;
	public int getBookCount() ;
	public int getCustomerCount() ;
	public int getLoanCount() ;
	public void finish() ;
}
