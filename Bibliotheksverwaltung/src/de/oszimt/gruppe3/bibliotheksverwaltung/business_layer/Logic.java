package de.oszimt.gruppe3.bibliotheksverwaltung.business_layer;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom2.JDOMException;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.DB;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.XML;

/**
 * Datei: Logic.java 
 * Aufgabe: Fachkonzept des Programms, regelt Anfragen an die
 * Datenhaltung und gibt ausgelesene Daten an die Oberfläche weiter
 * 
 * @author Tim Müller, FA03
 * @version 1.6 vom 28.11.2012
 * 
 */
public class Logic implements IBusinessLogic {

	private IDataStorage dataStorage;

	public Logic() {
	}

	/**
	 * Methode um ein Buch zu speichern
	 * 
	 * @since 1.0
	 * @param book
	 *            - das zu speichernde Buch
	 * @return <b>true</b> - wenn das Buch gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder das Buch nicht
	 *         gespeichert werden konnte
	 */
	@Override
	public boolean saveBook(Book book) {
		// Das Book-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkBook(book)) {
			return dataStorage.createBook(book);
		}
		return false;
	}

	/**
	 * Methode um einen Kunden zu speichern
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der zu speichernde Kunde
	 * @return <b>true</b> - wenn der Kunde gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder der Kunde nicht
	 *         gespeichert werden konnte
	 */
	@Override
	public boolean saveCustomer(Customer customer) {
		// Das Customer-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkCustomer(customer)) {
			return dataStorage.createCustomer(customer);
		}
		return false;
	}

	/**
	 * Methode um eine Leihe zu speichern
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die zu speichernde Leihe
	 * @return <b>true</b> - wenn die Leihe gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder die Leihe nicht
	 *         gespeichert werden konnte
	 */
	@Override
	public boolean saveLoan(Loan loan) {
		// Das Loan-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.createLoan(loan);
		}
		return false;
	}

	/**
	 * Methode um ein Buch zu bearbeiten
	 * 
	 * @since 1.0
	 * @param book
	 *            - das zu bearbeitende Buch
	 * @return <b>true</b> - wenn das Buch bearbeitet wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder das Buch nicht
	 *         bearbeitet werden konnte
	 */
	@Override
	public boolean updateBook(Book book) {
		// Das Book-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkBook(book)) {
			return dataStorage.updateBook(book);
		}
		return false;
	}

	/**
	 * Methode um einen Kunden zu bearbeiten
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der zu bearbeitende Kunde
	 * @return <b>true</b> - wenn der Kunde bearbeitet wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder der Kunde nicht
	 *         bearbeitet werden konnte
	 */
	@Override
	public boolean updateCustomer(Customer customer) {
		// Das Customer-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkCustomer(customer)) {
			return dataStorage.updateCustomer(customer);
		}
		return false;
	}

	/**
	 * Methode um eine Leihe zu bearbeiten
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die zu bearbeitende Leihe
	 * @return <b>true</b> - wenn die Leihe bearbeitet wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder die Leihe nicht
	 *         bearbeitet werden konnte
	 */
	@Override
	public boolean updateLoan(Loan loan) {
		// Das Loan-Objekt überprüfen und falls es korrekt ist
		// der Datenhaltung übergeben und den erhaltenen
		// Rückgabewert zurückgeben, ansonsten false zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.updateLoan(loan);
		}
		return false;
	}

	/**
	 * Methode um ein Buch zu löschen
	 * 
	 * @since 1.0
	 * @param book
	 *            - das zu löschende Buch
	 * @return <b>true</b> - wenn das Buch gelöscht wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder das Buch nicht
	 *         gelöscht werden konnte
	 */
	@Override
	public boolean deleteBook(Book book) {
		// Prüfen, ob es sich um ein gültiges Objekt handelt
		if (checkBook(book)) {
			// Prüfen, ob das Buch laufende gerade ausgeliehen ist
			// wenn nicht, versuchen das Buch zu löschen
			List<Loan> loanList = dataStorage.getLoansByBook(book);
			if (loanList == null || loanList.size() == 0) {
				return dataStorage.deleteBook(book);
			}
		}
		return false;
	}

	/**
	 * Methode um einen Kunden zu löschen
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der zu löschende Kunde
	 * @return <b>true</b> - wenn der Kunde gelöscht wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder der Kunde nicht
	 *         gelöscht werden konnte
	 */
	@Override
	public boolean deleteCustomer(Customer customer) {
		// Prüfen, ob es sich um ein gültiges Objekt handelt
		if (checkCustomer(customer)) {
			// Prüfen, ob der Kunde laufende Ausleihen hat
			// wenn nicht, versuchen den Kunden zu löschen
			List<Loan> loanList = dataStorage.getLoansByCustomer(customer);
			if (loanList == null || loanList.size() == 0) {
				return dataStorage.deleteCustomer(customer);
			}
		}
		return false;
	}

	/**
	 * Methode um eine Leihe zu löschen
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die zu löschende Leihe
	 * @return <b>true</b> - wenn die Leihe gelöscht wurde<br>
	 *         <b>false</b> - wenn ein Fehler autrat oder die Leihe nicht
	 *         gelöscht werden konnte
	 */
	@Override
	public boolean deleteLoan(Loan loan) {
		// Loan-Objekt der Datenhaltung Übergeben und den erhaltenen
		// Rückgabewert zurückgeben
		if (checkLoan(loan)) {
			return dataStorage.deleteLoan(loan);
		}
		return false;
	}

	/**
	 * Methode um ein Buch auszulesen
	 * 
	 * @since 1.0
	 * @param isbn
	 *            - die ISBN des gewünschten Buches
	 * @return <b>newBook</b> - ein Objekt des gewünschten Buches<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder das Buch nicht
	 *         vorhanden ist
	 */
	@Override
	public Book readBook(String isbn) {
		Book newBook = dataStorage.readBook(isbn);
		List<Loan> loanList = dataStorage.getLoansByBook(newBook);
		newBook.setLoanList(loanList);
		if (checkBook(newBook)) {
			return newBook;
		}
		return null;
	}

	/**
	 * Methode um einen Kunden auszulesen
	 * 
	 * @since 1.0
	 * @param customerID
	 *            - die ID des gewünschten Kunden
	 * @return <b>newCustomer</b> - ein Objekt des gewünschten Kunden<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder der Kunde nicht
	 *         vorhanden ist
	 */
	@Override
	public Customer readCustomer(int customerID) {
		Customer newCustomer = dataStorage.readCustomer(customerID);
		List<Loan> loanList = dataStorage.getLoansByCustomer(newCustomer);
		newCustomer.setLoanList(loanList);
		if (checkCustomer(newCustomer)) {
			return newCustomer;
		}
		return null;
	}

	/**
	 * Methode um eine Leihe auszulesen
	 * 
	 * @since 1.0
	 * @param isbn
	 *            - die ISBN des geliehenen Buches
	 * @param customerID
	 *            - die ID des Kunden, der das Buch geliehen hat
	 * @return <b>newLoan</b> - ein Objekt der gewünschten Leihe<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder das Buch,der Kunde
	 *         oder die Leihe nicht vorhanden sind
	 */
	@Override
	public Loan readLoan(String isbn, int customerID) {
		Loan newLoan = dataStorage.readLoan(isbn, customerID);
		if (checkLoan(newLoan)) {
			return newLoan;
		}
		return null;
	}

	/**
	 * Methode um eine Leihe auszulesen
	 * 
	 * @since 1.0
	 * @param loanID
	 *            - die ID der gewünschten Leihe
	 * @return <b>newLoan</b> - ein Objekt der gewünschten Leihe<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder die Leihe nicht
	 *         vorhanden ist
	 */
	@Override
	public Loan readLoan(int loanID) {
		Loan newLoan = dataStorage.readLoan(loanID);
		if (checkLoan(newLoan)) {
			return newLoan;
		}
		return null;
	}

	/**
	 * Methode um alle Leihen eines Kunden auszulesen
	 * 
	 * @since 1.1
	 * @param customer
	 *            - ein Objekt des Kunden
	 * @return <b>loanList</b> - eine Liste mit Leih-Objekten des Kunden<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder der Kunden keine
	 *         Leihen hat
	 */
	@Override
	public List<Loan> getLoansByCostumer(Customer customer) {
		return dataStorage.getLoansByCustomer(customer);
	}

	/**
	 * Methode um alle Leihen die zu einem Buch gehören auszulesen
	 * 
	 * @since 1.1
	 * @param book
	 *            - ein Objekt des Buches
	 * @return <b>loanList</b> - eine Liste mit Leih-Objekten des Buches<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder das Buch keine Leihen
	 *         hat
	 */
	@Override
	public List<Loan> getLoansByBook(Book book) {
		return dataStorage.getLoansByBook(book);
	}

	/**
	 * Methode um die Anzahl der Bücher zu ermitteln
	 * 
	 * @since 1.2
	 * @return -1 falls ein Fehler aufgetreten ist </br> 0 falls kein Buch
	 *         vorhanden ist </br> ansonsten die Anzahl der Bücher
	 */
	@Override
	public int getBookCount() {
		return dataStorage.getBookCount();
	}

	/**
	 * Methode um die Anzahl der Kunden zu ermitteln
	 * 
	 * @since 1.2
	 * @return -1 falls ein Fehler aufgetreten ist </br> 0 falls kein Kunde
	 *         vorhanden ist </br> ansonsten die Anzahl der Kunden
	 */
	@Override
	public int getCustomerCount() {
		return dataStorage.getCustomerCount();
	}

	/**
	 * Methode um die Anzahl der Leihen zu ermitteln
	 * 
	 * @since 1.2
	 * @return -1 falls ein Fehler aufgetreten ist </br> 0 falls keine Leihe
	 *         vorhanden ist </br> ansonsten die Anzahl der Leihen
	 */
	@Override
	public int getLoanCount() {
		return dataStorage.getLoanCount();
	}

	/**
	 * @since 1.0 dient dem Schließen der Datenhaltung
	 */
	@Override
	public void finish() {
		dataStorage.closeDataStorage();
	}

	/**
	 * Methode zur Überprüng eines Buches
	 * 
	 * @since 1.1
	 * @param book
	 *            - das zu prüfende Buch
	 * @return <b>true</b> - wenn das Buch korrekt ist<br>
	 *         <b>false</b> - wenn das Buch nicht korrekt ist
	 */
	private boolean checkBook(Book book) {
		if (book == null) {
			return false;
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

	/**
	 * Methode zur Überprüng eines Kunden
	 * 
	 * @since 1.1
	 * @param customer
	 *            - der zu prüfende Kunde
	 * @return <b>true</b> - wenn der Kunde korrekt ist<br>
	 *         <b>false</b> - wenn der Kunde nicht korrekt ist
	 */
	private boolean checkCustomer(Customer customer) {
		if (customer == null) {
			return false;
		}
		if (customer.getName().length() < 1) {
			return false;
		}
		if (customer.getSurname().length() < 1) {
			return false;
		}
		if (customer.getAddress().length() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * Methode zur Überprüfung eines Datums
	 * 
	 * @since 1.1
	 * @param date
	 * @return
	 */
	public boolean isDate(String date) {
		return date.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{2,4}$");
	}

	/**
	 * Methode zur Überprüng einer Leihe
	 * 
	 * @since 1.1
	 * @param loan
	 *            - die zu prüfende Leihe
	 * @return <b>true</b> - wenn die Leihe korrekt ist<br>
	 *         <b>false</b> - wenn die Leihe nicht korrekt ist
	 */
	private boolean checkLoan(Loan loan) {
		if (loan == null) {
			return false;
		}
		if (!checkBook(loan.getBook())) {
			return false;
		}
		if (!checkCustomer(loan.getCostumer())) {
			return false;
		}
		if (!isDate(loan.getStartOfLoan()) || !isDate(loan.getEndOfLoan())) {
			return false;
		}
		if (!checkDateDifference(loan.getStartOfLoan(), loan.getEndOfLoan())) {
			return false;
		}
		return true;
	}

	/**
	 * Methode zur Änderung der Datenhaltung während des Programmablaufs
	 * 
	 * @since 1.5
	 * @return <b>true</b> - wenn Änderung geklappt hat<br>
	 *         <b>false</b> - wenn Änderung nicht geklappt hat
	 */
	@Override
	public boolean changePersistence() {
		// Falls das Schließen der alten Datenhaltung nicht korrekt
		// abläuft, false zurückgeben
		if (!this.dataStorage.closeDataStorage()) {
			return false;
		}
		// ansonsten die neue Datenhaltung zuweisen
		// und true zurückgeben
		if (dataStorage instanceof DB) {
			try {
				dataStorage = new XML();
			} catch (JDOMException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		} else {
			try {
				dataStorage = new DB();
			} catch (ClassNotFoundException e) {
				return false;
			} catch (SQLException e) {
				return false;
			} catch (JDOMException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Methode zur Überprüfung der Differenz zwischen einem Start- und Enddatum
	 * 
	 * @since 1.4
	 * @param startOfLoan
	 *            - das Startdatum
	 * @param endOfLoan
	 *            - das Enddatum
	 * @return <b>true</b> - wenn das Startdatum vor dem Enddatum liegt<br>
	 *         <b>false</b> - wenn das Startdatum nicht vor dem Enddatum liegt
	 */
	private boolean checkDateDifference(String startOfLoan, String endOfLoan) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date sol = null, eol = null;
		long diff = -1;
		try {
			sol = sdf.parse(startOfLoan);
			eol = sdf.parse(endOfLoan);
		} catch (ParseException e) {
			return false;
		}
		diff = eol.getTime() - sol.getTime();
		if (diff < 0) {
			return false;
		}
		return true;
	}

	/**
	 * Methode zur Überprüfung ob ein Buch in einem Zeitraum verfügbar ist
	 * 
	 * @since 1.4
	 * @param book
	 *            - das zu leihende Buch
	 * @param startOfLoan
	 *            - das Startdatum der Leihe
	 * @param endOfLoan
	 *            - das Enddatum der Leihe
	 * @return <b>true</b> - wenn das Buch im angegebenen Zeitraum verfügbar ist<br>
	 *         <b>false</b> - wenn ein Fehler auftrat oder das Buch nicht
	 *         verfügbar ist
	 */
	@Override
	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan) {
		if (!isDate(startOfLoan) || !isDate(endOfLoan)) {
			return false;
		}
		if (!checkDateDifference(startOfLoan, endOfLoan)) {
			return false;
		}
		if (checkBook(book)) {
			return dataStorage.isAvailable(book, startOfLoan, endOfLoan);
		}
		return false;
	}

	/**
	 * Methode zur Suche nach Kunden
	 * 
	 * @since 1.5
	 * @param term
	 *            - der gesuchte Begriff
	 * @return <b>results</b> - eine Liste der gefundenen Kunden<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder nichts gefunden wurde
	 */
	@Override
	public List<Customer> searchCustomer(String term) {
		List<Customer> results = dataStorage.searchCustomer(term);
		if (results != null) {
			for (Customer customer : results) {
				customer.setLoanList(dataStorage.getLoansByCustomer(customer));
			}
			return results;
		}
		return null;
	}

	/**
	 * Methode zur Suche nach Bücher
	 * 
	 * @since 1.5
	 * @param term
	 *            - der gesuchte Begriff
	 * @return <b>results</b> - eine Liste der gefundenen Bücher<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder nichts gefunden wurde
	 */
	@Override
	public List<Book> searchBook(String term) {
		List<Book> results = dataStorage.searchBook(term);
		if (results != null) {
			for (Book book : results) {
				book.setLoanList(dataStorage.getLoansByBook(book));
			}
			return results;
		}
		return null;
	}

	/**
	 * Methode die alle Bücher zurückliefert
	 * 
	 * @since 1.6
	 * @return <b>books</b> - eine Liste aller Bücher<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine Bücher vorhanden
	 *         sind
	 */
	@Override
	public List<Book> getBooks() {
		List<Book> books = dataStorage.getBooks();
		if (books != null) {
			for (Book book : books) {
				book.setLoanList(dataStorage.getLoansByBook(book));
			}
			return books;
		}
		return null;
	}

	/**
	 * Methode die alle Kunden zurückliefert
	 * 
	 * @since 1.6
	 * @return <b>customers</b> - eine Liste aller Kundenr<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine Kunden vorhanden
	 *         sind
	 */
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = dataStorage.getCustomers();
		if (customers != null) {
			for (Customer customer : customers) {
				customer.setLoanList(dataStorage.getLoansByCustomer(customer));
			}
			return customers;
		}
		return null;
	}

	/**
	 * Methode die alle Leihen zurückliefert
	 * 
	 * @since 1.6
	 * @return <b>loans</b> - eine Liste aller Leihen<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine Leihen vorhanden
	 *         sind
	 */
	@Override
	public List<Loan> getLoans() {
		return dataStorage.getLoans();
	}

	@Override
	public void setDataStorage(IDataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}
}
