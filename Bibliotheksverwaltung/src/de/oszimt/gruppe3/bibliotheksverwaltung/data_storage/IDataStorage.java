package de.oszimt.gruppe3.bibliotheksverwaltung.data_storage;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Costumer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 *
 */
public interface IDataStorage {

	public boolean createBook(Book book) ;
	public boolean createCostumer(Costumer costumer) ;
	public boolean createLoan(Loan loan) ;
	public boolean updateBook(Book book) ;
	public boolean updateCostumer(Costumer costumer) ;
	public boolean updateLoan(Loan loan) ;
	public boolean deleteBook(Book book) ;
	public boolean deleteCostumer(Costumer costumer) ;
	public boolean deleteLoan(Loan loan) ;
	public Book readBook(String isbn) ;
	public Costumer readCostumer(int costumerID) ;
	public Loan readLoan(String isbn, int costumerID) ;
	public List<Loan> getLoansByCostumer(Costumer costumer) ;
	public List<Loan> getLoansByBook(Book book) ;
	
}
