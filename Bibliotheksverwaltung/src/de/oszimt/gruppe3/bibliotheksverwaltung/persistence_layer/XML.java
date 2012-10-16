package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Costumer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class XML implements IDataStorage {

	public XML(File f) throws JDOMException, IOException{
		this.openFile(f);
	}
	
	private Document openFile(File f) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(f);
		return doc;
	}
	
	@Override
	public boolean createBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createCostumer(Costumer costumer) {
		// TODO Auto-generated method stub
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
	public boolean updateCostumer(Costumer costumer) {
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
	public boolean deleteCostumer(Costumer costumer) {
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
	public Costumer readCostumer(int costumerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan readLoan(String isbn, int costumerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Loan> getLoansByCostumer(Costumer costumer) {
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
	public int getCostumerCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLoanCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
