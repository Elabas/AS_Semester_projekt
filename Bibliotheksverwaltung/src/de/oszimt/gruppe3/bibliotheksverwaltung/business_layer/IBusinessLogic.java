package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Costumer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public interface IBusinessLogic {

	public boolean saveBook(Book book) ;
	public boolean saveCostumer(Costumer costumer) ;
	public boolean saveLoan(Loan loan) ;
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
	public int getBookCount() ;
	public int getCostumerCount() ;
	public int getLoanCount() ;
}
