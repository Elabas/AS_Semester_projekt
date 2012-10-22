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

	private int loanID ;
	private Book book;
	private Customer costumer;
	private String startOfLoan;
	private String endOfLoan;

	public Loan(Book book, Customer costumer, String startOfLoan, String endOfLoan) {
		this.book = book;
		this.costumer = costumer;
		this.startOfLoan = startOfLoan;
		this.endOfLoan = endOfLoan;
	}
	
	public Loan(int loanID,Book book, Customer costumer, String startOfLoan, String endOfLoan) {
		this(book,costumer,startOfLoan,endOfLoan) ;
		this.loanID = loanID ;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCostumer() {
		return costumer;
	}

	public void setCostumer(Customer costumer) {
		this.costumer = costumer;
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

}
