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
	private Date startOfLoan;
	private Date endOfLoan;

	public Loan(Book book, Customer costumer, Date startOfLoan, Date endOfLoan) {
		this.book = book;
		this.costumer = costumer;
		this.startOfLoan = startOfLoan;
		this.endOfLoan = endOfLoan;
	}
	
	public Loan(int loanID,Book book, Customer costumer, Date startOfLoan, Date endOfLoan) {
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

	public Date getStartOfLoan() {
		return startOfLoan;
	}

	public void setStartOfLoan(Date startOfLoan) {
		this.startOfLoan = startOfLoan;
	}

	public Date getEndOfLoan() {
		return endOfLoan;
	}

	public void setEndOfLoan(Date endOfLoan) {
		this.endOfLoan = endOfLoan;
	}
	
	public int getLoanID() {
		return loanID;
	}
	
	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

}
