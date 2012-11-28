package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

/**
 * Datei: DB.java Aufgabe: Schnittstelle zur Datenbank
 * 
 * @author Tim M�ller
 * @version 1.6 vom 28.11.2012
 * 
 */
public class DB implements IDataStorage {

	private static Connection connection;
	private PreparedStatement sqlInterface;
	private String file = "db/BibliothekDB";
	private String user = "user";
	private String pass = "pass";
	private String statementsFile = "resources/statements.xml";
	private Map<String, String> statements;

	public DB() throws ClassNotFoundException, SQLException, JDOMException,
			IOException {
		openDataStorage();
		importStatements();
	}

	/**
	 * Methode zum �ffnen der Datenbankverbindung
	 * 
	 * @since 1.0
	 */
	@Override
	public void openDataStorage() throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:file:" + file
				+ ";shutdown=true", user, pass);
		sqlInterface = null;
	}

	/**
	 * Methode zum Schlie�en der Datenbankverbindung
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean closeDataStorage() {
		try {
			if (sqlInterface != null) {
				sqlInterface.close();
			}
			connection.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Methode zum Einlesen der SQL-Statements aus der entsprechenden Datei
	 * 
	 * @since 1.5
	 */
	private void importStatements() throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(statementsFile);
		statements = new HashMap<String, String>();
		Element root = doc.getRootElement();
		List<Element> elements = root.getChildren("statement");
		for (Element element : elements) {
			String id = element.getAttribute("id").getValue();
			String statement = element.getValue();
			statements.put(id, statement);
		}
	}

	/**
	 * Methode um ein Buch in der Datenbank zu speichern
	 * 
	 * @since 1.0
	 * @param book
	 *            - das zu speichernde Buch
	 * @return <b>true</b> - wenn das Buch erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean createBook(Book book) {
		String sqlStatement = statements.get("createBook");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn());
			sqlInterface.setString(2, book.getTitle());
			sqlInterface.setString(3, book.getAuthor());
			sqlInterface.setDouble(4, book.getPrice());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um einen Kunden in der Datenbank zu speichern
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der zu speichernde Kunde
	 * @return <b>true</b> - wenn der Kunde erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean createCustomer(Customer customer) {
		String sqlStatement = statements.get("createCustomer");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, customer.getName());
			sqlInterface.setString(2, customer.getSurname());
			sqlInterface.setString(3, customer.getAddress());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um eine Leihe in der Datenbank zu speichern
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die zu speichernde Leihe
	 * @return <b>true</b> - wenn die Leihe erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean createLoan(Loan loan) {
		String sqlStatement = statements.get("createLoan");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, loan.getBook().getIsbn());
			sqlInterface.setInt(2, loan.getCostumer().getCustomerID());
			sqlInterface.setString(3, parseToDBDate(loan.getStartOfLoan()));
			sqlInterface.setString(4, parseToDBDate(loan.getEndOfLoan()));
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um ein Buch in der Datenbank zu bearbeiten
	 * 
	 * @since 1.0
	 * @param book
	 *            - das bearbeitete Buch
	 * @return <b>true</b> - wenn das Buch erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean updateBook(Book book) {
		String sqlStatement = statements.get("updateBook");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn());
			sqlInterface.setString(2, book.getTitle());
			sqlInterface.setString(3, book.getAuthor());
			sqlInterface.setDouble(4, book.getPrice());
			sqlInterface.setString(5, book.getIsbn());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um einen Kunden in der Datenbank zu bearbeiten
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der bearbeitete Kunde
	 * @return <b>true</b> - wenn der Kunde erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean updateCustomer(Customer customer) {
		String sqlStatement = statements.get("updateCustomer");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, customer.getName());
			sqlInterface.setString(2, customer.getSurname());
			sqlInterface.setString(3, customer.getAddress());
			sqlInterface.setInt(4, customer.getCustomerID());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um eine Leihe in der Datenbank zu bearbeiten
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die bearbeitete Leihe
	 * @return <b>true</b> - wenn die Leihe erfolgreich in der Datenbank
	 *         gespeichert wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean updateLoan(Loan loan) {
		String sqlStatement = statements.get("updateLoan");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, loan.getBook().getIsbn());
			sqlInterface.setInt(2, loan.getCostumer().getCustomerID());
			sqlInterface.setString(3, parseToDBDate(loan.getStartOfLoan()));
			sqlInterface.setString(4, parseToDBDate(loan.getEndOfLoan()));
			sqlInterface.setInt(5, loan.getLoanID());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um ein Buch aus der Datenbank zu l�schen
	 * 
	 * @since 1.0
	 * @param book
	 *            - das zu l�schende Buch
	 * @return <b>true</b> - wenn das Buch erfolgreich aus der Datenbank
	 *         gel�scht wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean deleteBook(Book book) {
		String sqlStatement = statements.get("deleteBook");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um einen Kunden aus der Datenbank zu l�schen
	 * 
	 * @since 1.0
	 * @param customer
	 *            - der zu l�schende Kunde
	 * @return <b>true</b> - wenn der Kunde erfolgreich aus der Datenbank
	 *         gel�scht wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean deleteCustomer(Customer customer) {
		String sqlStatement = statements.get("deleteCustomer");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setInt(1, customer.getCustomerID());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um eine Leihe aus der Datenbank zu l�schen
	 * 
	 * @since 1.0
	 * @param loan
	 *            - die zu l�schende Leihe
	 * @return <b>true</b> - wenn die Leihe erfolgreich aus der Datenbank
	 *         gel�scht wurde<br>
	 *         <b>false</b> - wenn ein Fehler auftrat
	 */
	@Override
	public boolean deleteLoan(Loan loan) {
		String sqlStatement = statements.get("deleteLoan");
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setInt(1, loan.getLoanID());
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Methode um ein Buch aus der Datenbank auszulesen
	 * 
	 * @since 1.0
	 * @param isbn
	 *            - die ISBN des gew�nschten Buches
	 * @return <b>newBook</b> - ein Objekt des gew�nschten Buches<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder das Buch nicht
	 *         vorhanden ist
	 */
	@Override
	public Book readBook(String isbn) {
		// String sqlStatement = "SELECT * FROM T_Books WHERE p_isbn = ?";
		String sqlStatement = statements.get("readBook");
		Book newBook = null;
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, isbn);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				newBook = new Book(result.getString("p_isbn"),
						result.getString("title"), result.getString("author"),
						result.getDouble("price"));
			}
		} catch (SQLException e) {
			return null;
		}
		return newBook;
	}

	/**
	 * Methode um einen Kunden aus der Datenbank auszulesen
	 * 
	 * @since 1.0
	 * @param customerID
	 *            - die ID des gew�nschten Kunden
	 * @return <b>newCustomer</b> - ein Objekt des gew�nschten Kunden<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder der Kunde nicht
	 *         vorhanden ist
	 */
	@Override
	public Customer readCustomer(int customerID) {
		String sqlStatement = statements.get("readCustomer");
		Customer newCustomer = null;
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setInt(1, customerID);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				newCustomer = new Customer(result.getString("name"),
						result.getString("surname"),
						result.getInt("p_customer_id"),
						result.getString("address"));
			}
		} catch (SQLException e) {
			return null;
		}
		return newCustomer;
	}

	/**
	 * Methode um eine Leihe aus der Datenbank auszulesen
	 * 
	 * @since 1.0
	 * @param isbn
	 *            - die ISBN des geliehenen Buches
	 * @param customerID
	 *            - die ID des Kunden, der das Buch geliehen hat
	 * @return <b>newLoan</b> - ein Objekt der gew�nschten Leihe<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder das Buch,der Kunde
	 *         oder die Leihe nicht vorhanden sind
	 */
	@Override
	public Loan readLoan(String isbn, int customerID) {
		Book newBook = readBook(isbn);
		Customer newCustomer = readCustomer(customerID);
		String sqlStatement = statements.get("readLoan2");
		ResultSet result = null;
		Loan newLoan = null;
		if (newBook == null || newCustomer == null) {
			return null;
		}
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, isbn);
			sqlInterface.setInt(2, customerID);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				newLoan = new Loan(result.getInt("p_loan_id"), newBook,
						newCustomer,
						parseToLocalDate(result.getString("startOfLoan")),
						parseToLocalDate(result.getString("endOfLoan")));
			}
		} catch (SQLException e) {
			return null;
		}
		return newLoan;
	}

	/**
	 * Methode um eine Leihe aus der Datenbank auszulesen
	 * 
	 * @since 1.0
	 * @param loanID
	 *            - die ID der gew�nschten Leihe
	 * @return <b>newLoan</b> - ein Objekt der gew�nschten Leihe<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder die Leihe nicht
	 *         vorhanden ist
	 */
	@Override
	public Loan readLoan(int loanID) {
		String sqlStatement = statements.get("readLoan");
		ResultSet result = null;
		Loan newLoan = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setInt(1, loanID);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				newLoan = new Loan(loanID,
						readBook(result.getString("f_isbn")),
						readCustomer(result.getInt("f_customer_id")),
						parseToLocalDate(result.getString("startOfLoan")),
						parseToLocalDate(result.getString("endOfLoan")));
			}
		} catch (SQLException e) {
			return null;
		}
		return newLoan;
	}

	/**
	 * Methode um alle Leihen eines Kunden aus der Datenbank auszulesen
	 * 
	 * @since 1.1
	 * @param customer
	 *            - ein Objekt des Kunden
	 * @return <b>loanList</b> - eine Liste mit Leih-Objekten des Kunden<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder der Kunden keine
	 *         Leihen hat
	 */
	@Override
	public List<Loan> getLoansByCustomer(Customer customer) {
		String sqlStatement = statements.get("getLoansByCustomer");
		ResultSet result = null;
		List<Loan> loanList = new ArrayList<Loan>();
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setInt(1, customer.getCustomerID());
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Loan tmpLoan = new Loan(result.getInt("p_loan_id"),
						readBook(result.getString("f_isbn")), customer,
						parseToLocalDate(result.getString("startOfLoan")),
						parseToLocalDate(result.getString("endOfLoan")));
				loanList.add(tmpLoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return loanList;
	}

	/**
	 * Methode um alle Leihen die zu einem Buch geh�ren aus der Datenbank
	 * auszulesen
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
		String sqlStatement = statements.get("getLoansByBook");
		ResultSet result = null;
		List<Loan> loanList = new ArrayList<Loan>();
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn());
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Loan tmpLoan = new Loan(result.getInt("p_loan_id"), book,
						readCustomer(result.getInt("f_customer_id")),
						parseToLocalDate(result.getString("startOfLoan")),
						parseToLocalDate(result.getString("endOfLoan")));
				loanList.add(tmpLoan);
			}
		} catch (SQLException e) {
			return null;
		}
		return loanList;
	}

	/**
	 * Methode um die Anzahl der B�cher zu ermitteln
	 * 
	 * @since 1.2
	 * @return <b>bookCount</b> - die Anzahl der vorhandenen B�cher
	 */
	@Override
	public int getBookCount() {
		int bookCount = 0;
		String sqlStatement = statements.get("getBookCount");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				bookCount = result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}
		return bookCount;
	}

	/**
	 * Methode um die Anzahl der Kunden zu ermitteln
	 * 
	 * @since 1.2
	 * @return <b>customerCount</b> - die Anzahl der vorhandenen Kunden
	 */
	@Override
	public int getCustomerCount() {
		int customerCount = 0;
		String sqlStatement = statements.get("getCustomerCount");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				customerCount = result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}
		return customerCount;
	}

	/**
	 * Methode um die Anzahl der Leihen zu ermitteln
	 * 
	 * @since 1.2
	 * @return <b>loanCount</b> - die Anzahl der vorhandenen Leihen
	 */
	@Override
	public int getLoanCount() {
		int loanCount = 0;
		String sqlStatement = statements.get("getLoanCount");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				loanCount = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return loanCount;
	}

	/**
	 * Methode um ein Datum in das Format der Datenbank zu formatieren
	 * 
	 * @since 1.3
	 * @param date
	 *            - das zu parsende Datum
	 * @return <b>dbDate</b> - das Datum im zur Datenbank kompatiblen Format
	 */
	private String parseToDBDate(String date) {
		String[] dateArray = date.split("\\.");
		if (dateArray.length != 3) {
			return null;
		}
		StringBuilder dbDate = new StringBuilder();
		dbDate.append(dateArray[2]);
		dbDate.append("-");
		dbDate.append(dateArray[1]);
		dbDate.append("-");
		dbDate.append(dateArray[0]);
		return dbDate.toString();
	}

	/**
	 * Methode um ein Datum aus der Datenbank in das deutsche Format zu
	 * formatieren
	 * 
	 * @since 1.3
	 * @param date
	 *            - das zu parsende Datum aus der Datenbank
	 * @return <b>localDate</b> - das Datum im deutschen Format
	 */
	private String parseToLocalDate(String date) {
		String[] dateArray = date.split("-");
		if (dateArray.length != 3) {
			return null;
		}
		StringBuilder localDate = new StringBuilder();
		localDate.append(dateArray[2]);
		localDate.append(".");
		localDate.append(dateArray[1]);
		localDate.append(".");
		localDate.append(dateArray[0]);
		return localDate.toString();
	}

	/**
	 * Methode zur �berpr�fung ob ein Buch in einem Zeitraum verf�gbar ist
	 * 
	 * @since 1.4
	 * @param book
	 *            - das zu leihende Buch
	 * @param startOfLoan
	 *            - das Startdatum der Leihe
	 * @param endOfLoan
	 *            - das Enddatum der Leihe
	 * @return <b>true</b> - wenn das Buch im angegebenen Zeitraum verf�gbar ist<br>
	 *         <b>false</b> - wenn ein Fehler auftrat oder das Buch nicht
	 *         verf�gbar ist
	 */
	@Override
	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan) {
		String sqlStatement = statements.get("isAvailable");
		ResultSet result = null;
		int counter = -1;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn());
			sqlInterface.setString(2, parseToDBDate(endOfLoan));
			sqlInterface.setString(3, parseToDBDate(startOfLoan));
			result = sqlInterface.executeQuery();
			while (result.next()) {
				counter++;
			}
		} catch (SQLException e) {
			return false;
		}
		if (counter < 0) {
			return false;
		}
		return true;
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
		List<Customer> results = new ArrayList<Customer>();
		term = "%" + term + "%";
		String sqlStatement = statements.get("searchCustomer");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, term);
			sqlInterface.setString(2, term);
			sqlInterface.setString(3, term);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Customer newCustomer = new Customer(result.getString("name"),
						result.getString("surname"),
						result.getInt("p_customer_id"),
						result.getString("address"));
				results.add(newCustomer);
			}
		} catch (SQLException e) {
			return null;
		}
		return results;
	}

	/**
	 * Methode zur Suche nach B�cher
	 * 
	 * @since 1.5
	 * @param term
	 *            - der gesuchte Begriff
	 * @return <b>results</b> - eine Liste der gefundenen B�cher<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder nichts gefunden wurde
	 */
	@Override
	public List<Book> searchBook(String term) {
		List<Book> results = new ArrayList<Book>();
		term = "%" + term + "%";
		String sqlStatement = statements.get("searchBook");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, term);
			sqlInterface.setString(2, term);
			sqlInterface.setString(3, term);
			sqlInterface.setString(4, term);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Book newBook = new Book(result.getString("p_isbn"),
						result.getString("title"), result.getString("author"),
						result.getDouble("price"));
				results.add(newBook);
			}
		} catch (SQLException e) {
			return null;
		}
		return results;
	}

	/**
	 * Methode die alle B�cher zur�ckliefert
	 * 
	 * @since 1.6
	 * @return <b>books</b> - eine Liste aller B�cher<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine B�cher vorhanden
	 *         sind
	 */
	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		String sqlStatement = statements.get("getBooks");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Book newBook = new Book(result.getString("p_isbn"),
						result.getString("title"), result.getString("author"),
						result.getDouble("price"));
				books.add(newBook);
			}
		} catch (SQLException e) {
			return null;
		}
		return books;
	}

	/**
	 * Methode die alle Kunden zur�ckliefert
	 * 
	 * @since 1.6
	 * @return <b>customers</b> - eine Liste aller Kundenr<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine Kunden vorhanden
	 *         sind
	 */
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		String sqlStatement = statements.get("getCustomers");
		ResultSet result = null;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Customer newCustomer = new Customer(result.getString("name"),
						result.getString("surname"),
						result.getInt("p_customer_id"),
						result.getString("address"));
				;
				customers.add(newCustomer);
			}
		} catch (SQLException e) {
			return null;
		}
		return customers;
	}

	/**
	 * Methode die alle Leihen zur�ckliefert
	 * 
	 * @since 1.6
	 * @return <b>loans</b> - eine Liste aller Leihen<br>
	 *         <b>null</b> - wenn ein Fehler auftrat oder keine Leihen vorhanden
	 *         sind
	 */
	@Override
	public List<Loan> getLoans() {
		List<Loan> loans = new ArrayList<Loan>();
		String sqlStatement = statements.get("getLoans");
		ResultSet result = null;
		;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			result = sqlInterface.executeQuery();
			while (result.next()) {
				Loan newLoan = new Loan(result.getInt("p_loan_id"),
						readBook(result.getString("f_isbn")),
						readCustomer(result.getInt("f_customer_id")),
						parseToLocalDate(result.getString("startOfLoan")),
						parseToLocalDate(result.getString("endOfLoan")));
				loans.add(newLoan);
			}
		} catch (SQLException e) {
			return null;
		}
		return loans;
	}

}
