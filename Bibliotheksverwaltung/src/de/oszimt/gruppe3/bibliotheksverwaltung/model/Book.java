package de.oszimt.gruppe3.bibliotheksverwaltung.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Tim M�ller
 * @version 1.0
 *
 */
public class Book implements Serializable {

	private String isbn;
	private String title;
	private String author;
	private double price;
	private List<Loan> loanList ;

	public Book(String isbn, String title, String author, double price) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.loanList = new ArrayList<Loan>() ;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<Loan> getLoanList() {
		return loanList;
	}
	
	public void setLoanList(List<Loan> loanList) {
		this.loanList = loanList;
	}
	
	public boolean addLoan(Loan loan) {
		return loanList.add(loan) ;
	}
	
	public boolean removeLoan(Loan loan) {
		return loanList.remove(loan) ;
	}
	
	@Override
	public String toString() {
		return isbn + "\n" + title + "\n" + author + "\n" + price ;
	}

}
