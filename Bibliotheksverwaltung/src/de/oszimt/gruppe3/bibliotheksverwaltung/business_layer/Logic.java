package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Costumer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

public class Logic implements IBusinessLogic {

	private IDataStorage dataStorage ;

	@Override
	public boolean saveBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveCostumer(Costumer costumer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveLoan(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCostumer(Costumer costumer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateLoan(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCostumer(Costumer costumer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteLoan(Loan loan) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book readBook(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Costumer readCostumer(int costumerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan readLoan(String isbn, int costumerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansByCostumer(Costumer costumer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansByBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBookCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCostumerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLoanCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
