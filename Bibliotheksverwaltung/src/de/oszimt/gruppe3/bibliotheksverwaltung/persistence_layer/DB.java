package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;
import de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer.IDataStorage;

public class DB implements IDataStorage {

	private static Connection connection;
	private Statement sqlInterface;
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
		sqlInterface = connection.createStatement();
	}

	@Override
	public boolean closeDataStorage() {
		try {
			sqlInterface.close();
			connection.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean createBook(Book book) {
		String sqlStatement = "INSERT INTO T_Books VALUES" + "('"
				+ book.getIsbn() + "','" + book.getTitle() + "','"
				+ book.getAuthor() + "'," + book.getPrice() + ")";
		int result = 0;
		try {
			result = sqlInterface.executeUpdate(sqlStatement);
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
		String sqlStatement = "INSERT INTO T_Customers(name,prename,address) VALUES"
				+ "('"
				+ customer.getSurname()
				+ "','"
				+ customer.getName()
				+ "','" + customer.getAddress() + "')";
		int result = 0;

		try {
			result = sqlInterface.executeUpdate(sqlStatement);
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
		String sqlStatement = "INSERT INTO T_Loans(f_isbn,f_customer_id,startOfLoan,endOfLoan) VALUES('"
				+ loan.getBook().getIsbn()
				+ "',"
				+ loan.getCostumer().getCustomerID()
				+ ","
				+ loan.getStartOfLoan() + "," + loan.getEndOfLoan() + ")";
		int result = 0;
		try {
			result = sqlInterface.executeUpdate(sqlStatement);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
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
	public boolean deleteCustomer(Customer customer) {
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
		String sqlStatement = "SELECT * FROM T_Books WHERE p_isbn ='" + isbn
				+ "'";
		Book newBook = null;
		ResultSet result = null;
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			result.next() ;
			newBook = new Book(result.getString("p_isbn"),
					result.getString("title"), result.getString("author"),
					result.getDouble("price"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newBook;
	}

	@Override
	public Customer readCustomer(int customerID) {
		String sqlStatement = "SELECT * FROM T_Customers WHERE p_customer_id ="
				+ customerID;
		Customer newCustomer = null;
		ResultSet result = null;
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			result.next() ;
			newCustomer = new Customer(result.getString("name"),
					result.getString("surname"),
					result.getInt("p_customer_id"), result.getString("address"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newCustomer;
	}

	@Override
	public Loan readLoan(String isbn, int customerID) {
		Book newBook = readBook(isbn);
		Customer newCustomer = readCustomer(customerID);
		String sqlStatement = "SELECT p_loan_id,startOfLoan,endOfLoan FROM T_Loans WHERE f_isbn ='"
				+ isbn + "' AND f_customer_id =" + customerID;
		ResultSet result = null;
		Loan newLoan = null;
		if (newBook == null || newCustomer == null) {
			return null;
		}
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			result.next() ;
			newLoan = new Loan(result.getInt("p_loan_id"), newBook,
					newCustomer, result.getDate("startOfLoan"),
					result.getDate("endOfLoan"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newLoan;
	}

	@Override
	public Loan readLoan(int loanID) {
		String sqlStatement = "SELECT * FROM T_Loans WHERE p_loan_id ="
				+ loanID;
		ResultSet result = null;
		Loan newLoan = null;
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			newLoan = new Loan(loanID, readBook(result.getString("f_isbn")),
					readCustomer(result.getInt("f_customer_id")),
					result.getDate("startOfLoan"), result.getDate("endOfLoan"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return newLoan;
	}

	@Override
	public List<Loan> getLoansByCustomer(Customer customer) {
		String sqlStatement = "SELECT p_loan_id,f_isbn,startOfLoan,endOfLoan FROM T_Loans WHERE f_customer_id ="
				+ customer.getCustomerID() + " ORDER BY p_loan_id";
		ResultSet result = null;
		List<Loan> loanList = new ArrayList<Loan>();
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			while (result.next()) {
				Loan tmpLoan = new Loan(result.getInt("p_loan_id"),
						readBook(result.getString("f_isbn")), customer,
						result.getDate("startOfLoan"),
						result.getDate("endOfLoan"));
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
		String sqlStatement = "SELECT p_loan_id,f_customer_id,startOfLoan,endOfLoan FROM T_Loans WHERE f_isbn='"
				+ book.getIsbn() + "'";
		ResultSet result = null;
		List<Loan> loanList = new ArrayList<Loan>();
		try {
			result = sqlInterface.executeQuery(sqlStatement);
			while (result.next()) {
				Loan tmpLoan = new Loan(result.getInt("p_loan_id"), book,
						readCustomer(result.getInt("f_customer_id")),
						result.getDate("startOfLoan"),
						result.getDate("endOfLoan"));
				loanList.add(tmpLoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			result = sqlInterface.executeQuery(sqlStatement);
			bookCount = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
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
			result = sqlInterface.executeQuery(sqlStatement);
			customerCount = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
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
			result = sqlInterface.executeQuery(sqlStatement);
			loanCount = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return loanCount;
	}

}
