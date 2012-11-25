package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

/**
 * 
 * @author Tim Müller
 * @version 1.4
 * 
 */
public class Logic implements IBusinessLogic {

	private IDataStorage dataStorage;

	public Logic(IDataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	// TODO Kommentierung
	@Override	
	public boolean saveBook(Book book) {
		//  Book-Objekt der Datenhaltung Übergeben und den erhaltenen
		//  Rückgabewert zurückgeben
		if (checkBook(book)) {
			return dataStorage.createBook(book) ; 
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean saveCustomer(Customer customer) {
		// Customer-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if(checkCustomer(customer)) {
			return dataStorage.createCustomer(customer);
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean saveLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.createLoan(loan);
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean updateBook(Book book) {
		// Book-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if (checkBook(book)) {
			return dataStorage.updateBook(book) ; 
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean updateCustomer(Customer customer) {
		// Customer-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if(checkCustomer(customer)) {
			return dataStorage.updateCustomer(customer);
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean updateLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.updateLoan(loan);
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean deleteBook(Book book) {
		// Prüfen, ob es sich um ein gültiges Objekt handelt
		if (checkBook(book)) {
			// Prüfen, ob das Buch laufende gerade ausgeliehen ist
			// wenn nicht, versuchen das Buch zu löschen
			List<Loan> loanList = dataStorage.getLoansByBook(book) ;			
			if( loanList == null || loanList.size() == 0) {			
				return dataStorage.deleteBook(book);
			}
		}
		return false ;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {		
		// Prüfen, ob es sich um ein gültiges Objekt handelt
		if(checkCustomer(customer)) {
			// Prüfen, ob der Kunde laufende Ausleihen hat
			// wenn nicht, versuchen den Kunden zu löschen 
			List<Loan> loanList = dataStorage.getLoansByCustomer(customer) ;			
			if( loanList == null || loanList.size() == 0) {			
				return dataStorage.deleteCustomer(customer);
			}
		}
		return false ;
	}

	// TODO Kommentierung
	@Override
	public boolean deleteLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.deleteLoan(loan);
		}
		return false ;
	}

	@Override
	public Book readBook(String isbn) {
		Book newBook = dataStorage.readBook(isbn); 
		List<Loan> loanList = dataStorage.getLoansByBook(newBook) ;
		newBook.setLoanList(loanList) ;
		if (checkBook(newBook)) {
			return newBook ;
		}
		return null ;
	}

	@Override
	public Customer readCustomer(int customerID) {
		Customer newCustomer = dataStorage.readCustomer(customerID);
		List<Loan> loanList = dataStorage.getLoansByCustomer(newCustomer) ;
		newCustomer.setLoanList(loanList) ;
		if(checkCustomer(newCustomer)) {
			return newCustomer ;
		}
		return null ;
	}

	// TODO Kommentierung
	@Override
	public Loan readLoan(String isbn, int customerID) {
		Loan newLoan = dataStorage.readLoan(isbn, customerID) ;
		if(checkLoan(newLoan)) {
			return newLoan ;
		}
		return null;
	}

	// TODO Kommentierung
	@Override
	public List<Loan> getLoansByCostumer(Customer customer) {
		return dataStorage.getLoansByCustomer(customer);
	}

	// TODO Kommentierung
	@Override
	public List<Loan> getLoansByBook(Book book) {
		return dataStorage.getLoansByBook(book);
	}

	/**
	 * @return -1 falls ein Fehler aufgetreten ist </br> 
	 * 			0 falls kein Buch vorhanden ist </br> 
	 * 			ansonsten die Anzahl der Bücher 
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
	 * dient dem Schließen der Datenhaltung
	 */
	@Override
	public void finish() {
		dataStorage.closeDataStorage();
	}

	// TODO Kommentierung
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
	
	// TODO Kommentierung
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
		
	// TODO Kommentierung
	public boolean isDate(String date) {		
		return date.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{2,4}$") ;
	}
	
	// TODO Kommentierung
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
		if (!isDate(loan.getStartOfLoan()) || !isDate(loan.getEndOfLoan())) {
			return false ;
		}
		if (! checkDateDifference(loan.getStartOfLoan(), loan.getEndOfLoan())) {
			return false ;
		}
		return true ;
	}
	
	// TODO Kommentierung
	@Override
	public boolean changePersistence(IDataStorage dataStorage) {
		// Falls das Schließen der alten Datenhaltung nicht korrekt
		// abläuft, false zurückgeben
		if (!this.dataStorage.closeDataStorage()) {
			return false;
		}
		// ansonsten die neue Datenhaltung zuweisen
		// und true zurückgeben
		this.dataStorage = dataStorage ;
		return true ;
	}
	
	// TODO Kommentierung
	private boolean checkDateDifference(String startOfLoan, String endOfLoan) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy") ;
		Date sol = null, eol = null;
		long diff = -1 ;
		try {
			sol = sdf.parse(startOfLoan);
			eol = sdf.parse(endOfLoan) ;
		} catch (ParseException e) {
			return false ;
		}	
		diff = eol.getTime() - sol.getTime() ;
		if ( diff < 0 ) {
			return false ;
		}
		return true ;
	}
	
	// TODO Kommentierung
	@Override
	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan) {		
		if (!isDate(startOfLoan) || !isDate(endOfLoan)) {
			return false ;
		}
		if (! checkDateDifference(startOfLoan, endOfLoan)) {
			return false ;
		}
		if (checkBook(book)) {
			return dataStorage.isAvailable(book, startOfLoan, endOfLoan) ;
		}
		return false ;
	}

	@Override
	public List<Customer> searchCustomer(String term) {
		List<Customer> results = dataStorage.searchCustomer(term) ;
		if (results != null) {
			for (Customer customer : results) {
				customer.setLoanList(dataStorage.getLoansByCustomer(customer)) ;
			}
			return results ;
		}
		return null ;
	}

	@Override
	public List<Book> searchBook(String term) {
		List<Book> results = dataStorage.searchBook(term) ;
		if (results != null) {
			for (Book book :results) {
				book.setLoanList(dataStorage.getLoansByBook(book)) ;
			}
			return results ;
		}
		return null ;
	}
}
