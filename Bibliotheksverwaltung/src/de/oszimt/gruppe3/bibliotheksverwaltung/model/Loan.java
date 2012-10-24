package de.oszimt.gruppe3.bibliotheksverwaltung.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Tim Müller
 * @version 1.0
 * 
 */
public class Loan implements Serializable {

	private int loanID;
	private Book book;
	private Customer customer;
	private String startOfLoan;
	private String endOfLoan;

	public Loan(Book book, Customer customer, String startOfLoan,
			String endOfLoan) {
		this.book = book;
		this.customer = customer;
		this.startOfLoan = startOfLoan;
		this.endOfLoan = endOfLoan;
	}

	public Loan(int loanID, Book book, Customer customer, String startOfLoan,
			String endOfLoan) {
		this(book, customer, startOfLoan, endOfLoan);
		this.loanID = loanID;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCostumer() {
		return customer;
	}

	public void setCostumer(Customer customer) {
		this.customer = customer;
	}

	public String getStartOfLoan() {
		return startOfLoan;
	}

	public void setStartOfLoan(String startOfLoan) {
		this.startOfLoan = startOfLoan;
	}

	public String getEndOfLoan() {
		return endOfLoan;
	}

	public void setEndOfLoan(String endOfLoan) {
		this.endOfLoan = endOfLoan;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	@Override
	public String toString() {
		return book.getIsbn() + "\n" + customer.getCustomerID() + "\n"
				+ startOfLoan + "\n" + endOfLoan;
	}

}
