package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class DB implements IDataStorage {

	private static Connection connection;
	private Statement sqlInterface;
//	private String file =  "F:/Tim/Tim/EclipseWorkspace/AS_Semester_projekt/db/BibliothekDB";
	private String file =  "db/BibliothekDB";
	private String user = "user";
	private String pass = "pass";

	public DB() {
		openDataStorage();
	}

	@Override
	public void openDataStorage() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:file:" + file
					+ ";shutdown=true", user, pass);
			sqlInterface = connection.createStatement();
		} catch (SQLException sqle) {
			System.out.println("SQL Fehler");
			sqle.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("kein Treiber");
			cnfe.printStackTrace();
		}
	}

	@Override
	public void closeDataStorage() {
		try {
			sqlInterface.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer readCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan readLoan(String isbn, int costumerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansByCustomer(Customer customer) {
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
