package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.util.Date;
import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

/**
 * 
 * @author Tim M�ller
 * @version 1.2
 * 
 */
public class Logic implements IBusinessLogic {

	private IDataStorage dataStorage;

	public Logic(IDataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	@Override
	public boolean saveBook(Book book) {
		//  Book-Objekt der Datenhaltung �bergeben und den erhaltenen
		//  R�ckgabewert zur�ckgeben
		if (checkBook(book)) {
			return dataStorage.createBook(book) ; 
		}
		return false ;
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		// Customer-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if(checkCustomer(customer)) {
			return dataStorage.createCustomer(customer);
		}
		return false ;
	}

	@Override
	public boolean saveLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if (checkLoan(loan)) {
			return dataStorage.createLoan(loan);
		}
		return false ;
	}

	@Override
	public boolean updateBook(Book book) {
		// Book-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if (checkBook(book)) {
			return dataStorage.updateBook(book) ; 
		}
		return false ;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// Customer-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if(checkCustomer(customer)) {
			return dataStorage.updateCustomer(customer);
		}
		return false ;
	}

	@Override
	public boolean updateLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if (checkLoan(loan)) {
			return dataStorage.updateLoan(loan);
		}
		return false ;
	}

	@Override
	public boolean deleteBook(Book book) {
		// Pr�fen, ob es sich um ein g�ltiges Objekt handelt
		if (checkBook(book)) {
			// Pr�fen, ob das Buch laufende gerade ausgeliehen ist
			// wenn nicht, versuchen das Buch zu l�schen
			List<Loan> loanList = dataStorage.getLoansByBook(book) ;			
			if( loanList == null || loanList.size() == 0) {			
				return dataStorage.deleteBook(book);
			}
		}
		return false ;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {		
		// Pr�fen, ob es sich um ein g�ltiges Objekt handelt
		if(checkCustomer(customer)) {
			// Pr�fen, ob der Kunde laufende Ausleihen hat
			// wenn nicht, versuchen den Kunden zu l�schen 
			List<Loan> loanList = dataStorage.getLoansByCustomer(customer) ;			
			if( loanList == null || loanList.size() == 0) {			
				return dataStorage.deleteCustomer(customer);
			}
		}
		return false ;
	}

	@Override
	public boolean deleteLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung �bergeben und den erhaltenen
		// R�ckgabewert zur�ckgeben
		if (checkLoan(loan)) {
			return dataStorage.deleteLoan(loan);
		}
		return false ;
	}

	@Override
	public Book readBook(String isbn) {
		Book newBook = dataStorage.readBook(isbn); 
		if (checkBook(newBook)) {
			return newBook ;
		}
		return null ;
	}

	@Override
	public Customer readCustomer(int customerID) {
		Customer newCustomer = dataStorage.readCustomer(customerID);
		if(checkCustomer(newCustomer)) {
			return newCustomer ;
		}
		return null ;
	}

	@Override
	public Loan readLoan(String isbn, int customerID) {
		Loan newLoan = dataStorage.readLoan(isbn, customerID) ;
		if(checkLoan(newLoan)) {
			return newLoan ;
		}
		return null;
	}

	@Override
	public List<Loan> getLoansByCostumer(Customer customer) {
		return dataStorage.getLoansByCustomer(customer);
	}

	@Override
	public List<Loan> getLoansByBook(Book book) {
		return dataStorage.getLoansByBook(book);
	}

	/**
	 * @return -1 falls ein Fehler aufgetreten ist </br> 
	 * 			0 falls kein Buch vorhanden ist </br> 
	 * 			ansonsten die Anzahl der B�cher 
	 */
	@Override
	public int getBookCount() {
		return dataStorage.getBookCount() ;
	}

	
	/**
	 * @return -1 falls ein Fehler aufgetreten ist </br> 
	 * 			0 falls kein Kunde vorhanden ist </br> 
	 * 			ansonsten die Anzahl der Kunden 
	 */
	@Override
	public int getCustomerCount() {
		return dataStorage.getCustomerCount();
	}

	/**
	 * @return -1 falls ein Fehler aufgetreten ist </br> 
	 * 			0 falls keine Leihe vorhanden ist </br> 
	 * 			ansonsten die Anzahl der Leihen 
	 */
	@Override
	public int getLoanCount() {
		return dataStorage.getLoanCount();
	}

	/**
	 * dient dem Schlie�en der Datenhaltung
	 */
	@Override
	public void finish() {
		dataStorage.closeDataStorage();
	}

	private boolean checkBook(Book book) {
		if ( book == null) {
			return false ;
		} 
		if (book.getIsbn().length() < 1) {
			return false;
		}
		if (book.getTitle().length() < 1) {
			return false;
		}
		if (book.getAuthor().length() < 1) {
			return false;
		}
		if (!(Double.valueOf(book.getPrice()) instanceof Double)) {
			return false;
		}
		return true;
	}
	
	private boolean checkCustomer(Customer customer) {
		if ( customer == null) {
			return false ;
		}
		if(customer.getName().length() < 1) {
			return false ;
		}
		if(customer.getSurname().length() < 1) {
			return false ;
		}
		if(customer.getAddress().length() < 1) {
			return false ;
		}
		return true ;
	}
		
	public boolean isDate(String date) {		
		return date.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{2,4}$") ;
	}
	
	private boolean checkLoan(Loan loan) {
		if ( loan == null) {
			return false ;
		}
		if (! checkBook(loan.getBook())) {
			return false ;
		}
		if (! checkCustomer(loan.getCostumer())) {
			return false ;
		}
		if (! isDate(loan.getStartOfLoan())) {
			return false ;
		}
		if (! isDate(loan.getEndOfLoan())) {
			return false ;
		}
		return true ;
	}
	
	@Override
	public boolean changePersistence(IDataStorage dataStorage) {
		// Falls das Schlie�en der alten Datenhaltung nicht korrekt
		// abl�uft, false zur�ckgeben
		if (!this.dataStorage.closeDataStorage()) {
			return false;
		}
		// ansonsten die neue Datenhaltung zuweisen
		// und true zur�ckgeben
		this.dataStorage = dataStorage ;
		return true ;
	}
	
	@Override
	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan) {
		
	}
}
