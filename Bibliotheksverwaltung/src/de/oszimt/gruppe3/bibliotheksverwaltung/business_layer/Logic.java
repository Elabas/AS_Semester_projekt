package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

/**
*
* @author Tim Müller
* @version 1.1
*
*/
public class Logic implements IBusinessLogic {

	private IDataStorage dataStorage ;
	
	public Logic(IDataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	@Override
	public boolean saveBook(Book book) {
		//  Book-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.createBook(book) ;
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		//  Customer-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.createCustomer(customer);
	}

	@Override
	public boolean saveLoan(Loan loan) {
		//  Loan-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.createLoan(loan);
	}

	@Override
	public boolean updateBook(Book book) {
		//  Book-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.updateBook(book);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		//  Customer-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.updateCustomer(customer);
	}

	@Override
	public boolean updateLoan(Loan loan) {
		//  Loan-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.updateLoan(loan);
	}

	@Override
	public boolean deleteBook(Book book) {
		//  Book-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.deleteBook(book);
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		//  Customer-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.deleteCustomer(customer);
	}

	@Override
	public boolean deleteLoan(Loan loan) {
		//  Loan-Objekt der Datenhaltung übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		return dataStorage.deleteLoan(loan);
	}

	@Override
	public Book readBook(String isbn) {
		return  dataStorage.readBook(isbn) ;		
	}

	@Override
	public Customer readCustomer(int customerID) {		
		return dataStorage.readCustomer(customerID) ;
	}

	@Override
	public Loan readLoan(String isbn, int customerID) {
		
		return null;
	}

	@Override
	public List<Loan> getLoansByCostumer(Customer customer) {
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
	public int getCustomerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLoanCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
