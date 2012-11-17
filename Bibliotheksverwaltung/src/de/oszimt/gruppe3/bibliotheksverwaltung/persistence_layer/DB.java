package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

/**
 * 
 * @author Tim Müller
 * @version 1.3
 * 
 */
public class DB implements IDataStorage {

	private static Connection connection;
	private PreparedStatement sqlInterface;
	// private String file =
	// "F:/Tim/Tim/EclipseWorkspace/AS_Semester_projekt/db/BibliothekDB";
	private String file = "db/BibliothekDB";
	private String user = "user";
	private String pass = "pass";

	public DB() throws ClassNotFoundException, SQLException {
		openDataStorage();
	}

	@Override
	public void openDataStorage() throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		connection = DriverManager.getConnection("jdbc:hsqldb:file:" + file
				+ ";shutdown=true", user, pass);
		sqlInterface = null;
	}

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

	@Override
	public boolean createBook(Book book) {
		String sqlStatement = "INSERT INTO T_Books VALUES" + "(?,?,?,?)";
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

	@Override
	public boolean createCustomer(Customer customer) {
		String sqlStatement = "INSERT INTO T_Customers(name,surname,address) VALUES(?,?,?)";
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

	@Override
	public boolean createLoan(Loan loan) {
		String sqlStatement = "INSERT INTO T_Loans(f_isbn,f_customer_id,startOfLoan,endOfLoan) VALUES(?,?,?,?)";
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

	@Override
	public boolean updateBook(Book book) {
		String sqlStatement = "UPDATE T_Books SET p_isbn = ?, title = ?, author = ?, price = ? WHERE p_isbn = ?";
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

	@Override
	public boolean updateCustomer(Customer customer) {
		String sqlStatement = "UPDATE T_Customers SET name = ?, surname = ?, address = ? WHERE p_customer_id = ?";
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

	@Override
	public boolean updateLoan(Loan loan) {
		String sqlStatement = "UPDATE T_Loans SET f_isbn = ?, f_customer_id = ?, startOfLoan = ?, endOfLoan = ? WHERE p_loan_id = ?";
		int result = 0;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, loan.getBook().getIsbn());
			sqlInterface.setInt(2, loan.getCostumer().getCustomerID());
			sqlInterface.setString(3, parseToDBDate(loan.getStartOfLoan()));
			sqlInterface.setString(4, parseToDBDate(loan.getEndOfLoan()));
			sqlInterface.setInt(5,loan.getLoanID()) ;
			result = sqlInterface.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		String sqlStatement = "DELETE FROM T_Books WHERE p_isbn = ?";
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

	@Override
	public boolean deleteCustomer(Customer customer) {
		String sqlStatement = "DELETE FROM T_Customers WHERE p_customer_id = ?";
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

	@Override
	public boolean deleteLoan(Loan loan) {
		String sqlStatement = "DELETE FROM T_Loans WHERE p_loan_id = ?";
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

	@Override
	public Book readBook(String isbn) {
		String sqlStatement = "SELECT * FROM T_Books WHERE p_isbn = ?";
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

	@Override
	public Customer readCustomer(int customerID) {
		String sqlStatement = "SELECT * FROM T_Customers WHERE p_customer_id = ?";
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

	@Override
	public Loan readLoan(String isbn, int customerID) {
		Book newBook = readBook(isbn);
		Customer newCustomer = readCustomer(customerID);
		String sqlStatement = "SELECT p_loan_id,startOfLoan,endOfLoan FROM T_Loans WHERE f_isbn = ? AND f_customer_id = ?";
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

	@Override
	public Loan readLoan(int loanID) {
		String sqlStatement = "SELECT * FROM T_Loans WHERE p_loan_id = ?";
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

	@Override
	public List<Loan> getLoansByCustomer(Customer customer) {
		String sqlStatement = "SELECT p_loan_id,f_isbn,startOfLoan,endOfLoan FROM T_Loans WHERE f_customer_id = ? ORDER BY p_loan_id";
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

	@Override
	public List<Loan> getLoansByBook(Book book) {
		String sqlStatement = "SELECT p_loan_id,f_customer_id,startOfLoan,endOfLoan FROM T_Loans WHERE f_isbn= ? ORDER BY p_loan_id";
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

	@Override
	public int getBookCount() {
		int bookCount = 0;
		String sqlStatement = "SELECT count(p_isbn) FROM T_Books";
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

	@Override
	public int getCustomerCount() {
		int customerCount = 0;
		String sqlStatement = "SELECT count(p_customer_id) FROM T_Customers";
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

	@Override
	public int getLoanCount() {
		int loanCount = 0;
		String sqlStatement = "SELECT count(p_loan_id) FROM T_Loans";
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

	private String parseToDBDate(String date) {
		String[] dateArray = date.split("\\.");
		if (dateArray.length != 3) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dateArray[2]);
		sb.append("-");
		sb.append(dateArray[1]);
		sb.append("-");
		sb.append(dateArray[0]);
		return sb.toString();
	}

	private String parseToLocalDate(String date) {
		String[] dateArray = date.split("-");
		if (dateArray.length != 3) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dateArray[2]);
		sb.append(".");
		sb.append(dateArray[1]);
		sb.append(".");
		sb.append(dateArray[0]);
		return sb.toString();
	}
	
	@Override
	public boolean isAvailable(Book book, String startOfLoan, String endOfLoan) {
		String sqlStatement = "SELECT * FROM T_Loans WHERE f_isbn = ? AND (startOfLoan > ? OR endOfLoan < ?) ";
		ResultSet result = null;
		int counter = -1 ;
		try {
			sqlInterface = connection.prepareStatement(sqlStatement);
			sqlInterface.setString(1, book.getIsbn()) ;
			sqlInterface.setString(2, parseToDBDate(endOfLoan)) ;
			sqlInterface.setString(3, parseToDBDate(startOfLoan)) ;
			result = sqlInterface.executeQuery();
			while (result.next()) {				
				counter++ ;		
			}
		} catch (SQLException e) {
			return false ;
		}
		if (counter < 0) {
			return false ;
		}
		return true ;
	}

}
