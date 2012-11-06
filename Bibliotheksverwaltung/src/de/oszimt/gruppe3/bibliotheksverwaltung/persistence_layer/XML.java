package de.oszimt.gruppe3.bibliotheksverwaltung.persistence_layer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.oszimt.gruppe3.bibliotheksverwaltung.model.Book;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Customer;
import de.oszimt.gruppe3.bibliotheksverwaltung.model.Loan;

public class XML implements IDataStorage {

	private Document doc;
	private File f;
	
	public XML(File f) throws JDOMException, IOException{
		this.doc = this.openFile(f);
		this.f = f;
	}
	
	private Document openFile(File f) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(f);
		return doc;
	}
	

	private boolean saveToFile(){
		
		XMLOutputter xmlop = new XMLOutputter (Format.getPrettyFormat());
        try {
			xmlop.output(doc, new FileOutputStream(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
		
	}
	
	private void closeFile(BufferedWriter w){
		try {
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean createBook(Book book) {
		if(doc == null)
			return false;
		Element currRoot = doc.getRootElement();
		Element parentBooks = currRoot.getChild("books");
		Element newBook = new Element("book");
		
		newBook.addContent(new Element("isbn").setText(book.getIsbn()));
		newBook.addContent(new Element("title").setText(book.getTitle()));
		newBook.addContent(new Element("author").setText(book.getAuthor()));
		newBook.addContent(new Element("price").setText(book.getPrice()+""));
		
		parentBooks.addContent(newBook);
		
		return this.saveToFile();
	}
	

	@Override
	public boolean createLoan(Loan loan) {
		if(doc == null)
			return false;
		Element currRoot = doc.getRootElement();
		Element parrentLoan = currRoot.getChild("loanBooks");
		Element newLoan = new Element("loanBook");
		
		newLoan.addContent(new Element("loanID").setText( loan.getLoanID()+""));
		newLoan.addContent(new Element("isbn").setText(loan.getBook().getIsbn()));
		newLoan.addContent(new Element("CustomerID").setText(loan.getCostumer().getCustomerID()+""));
		newLoan.addContent(new Element("startOfLoan").setText(loan.getStartOfLoan().toString()));
		newLoan.addContent(new Element("endOfLoan").setText(loan.getEndOfLoan().toString()));
		
		parrentLoan.addContent(newLoan);
		
		return this.saveToFile();
	}
	
	@Override
	public boolean createCustomer(Customer customer) {
		if(doc == null)
			return false;
		Element currRoot = doc.getRootElement();
		Element parrentCusto = currRoot.getChild("customers");
		Element newCusto = new Element("customer");
		
		newCusto.addContent(new Element("customerID").setText(customer.getCustomerID()+""));
		newCusto.addContent(new Element("name").setText(customer.getName()));
		newCusto.addContent(new Element("surename").setText(customer.getSurname()));
		newCusto.addContent(new Element("address").setText(customer.getAddress()));
		
		parrentCusto.addContent(newCusto);
		
		
		return this.saveToFile();
	}
	
	@Override
	public boolean updateBook(Book book) {
		if(doc == null)
			return false;
		Element currRoot = doc.getRootElement();
		Element parentBooks = currRoot.getChild("books");
		
		for (Element currBook : parentBooks.getChildren()) {
			String isbn = currBook.getChild("isbn").getText();
			if(book.getIsbn().equals(isbn)){
				currBook.getChild("author").setText(book.getAuthor());
				currBook.getChild("title").setText(book.getTitle());
				currBook.getChild("price").setText(book.getPrice()+"");
				return true;
			}
		}		
		
		return false;
	}

	@Override
	public boolean updateLoan(Loan loan) {
		Element currRoot = doc.getRootElement();
		Element loans = currRoot.getChild("loans");
		List<Element> listLoans = loans.getChildren();
		
		for (Element currLoan : listLoans) {
			int loanID = Integer.parseInt(currLoan.getChild("loanID").getText());
			if(loan.getLoanID() == loanID){
				currLoan.getChild("isbn").setText(loan.getBook().getIsbn());
				currLoan.getChild("CustomerID").setText(loan.getCostumer().getCustomerID()+"");
				currLoan.getChild("startOfLoan").setText(loan.getStartOfLoan());
				currLoan.getChild("endOfLoan").setText(loan.getEndOfLoan());
					return true;						
			}
		}
		return false;
	}
	

	@Override
	public boolean updateCustomer(Customer customer) {
		if(doc == null)
			return false;
		
		Element currRoot = doc.getRootElement();
		Element parrentCusto = currRoot.getChild("customers");
		List<Element> listCusto = parrentCusto.getChildren();
		
		for (Element currCusto : listCusto) {
			int custoID = Integer.parseInt(currCusto.getChild("customerID").getText());
			if(customer.getCustomerID() == custoID){
				currCusto.getChild("name").setText(customer.getName());
				currCusto.getChild("surename").setText(customer.getSurname());
				currCusto.getChild("address").setText(customer.getAddress());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		if(doc == null)
			return false;
		
		Element currRoot = doc.getRootElement();
		Element books = currRoot.getChild("books");
		List<Element> listBooks = books.getChildren();
		if( book.getLoanList()!= null)
		for (Loan item : book.getLoanList()) {
			if(!deleteLoan(item)){
				return false;
			}
		}
		
		for (Element currBook : listBooks) {
			String isbn = currBook.getChild("isbn").getText();
			if(book.getIsbn().equals(isbn)){
				doc.removeContent(currBook);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteLoan(Loan loan) {
		if(doc == null)
			return false;
		
		Element currRoot = doc.getRootElement();
		Element loans = currRoot.getChild("loans");
		List<Element> listLoans = loans.getChildren();
		
		for (Element currLoan : listLoans) {
			int loanID = Integer.parseInt(currLoan.getChild("loanID").getText());
			if(loan.getLoanID() == loanID){
				doc.removeContent(currLoan);
				return true;
			}
		}
		
		
		return false;
	}
	
	@Override
	public boolean deleteCustomer(Customer customer) {
		if(doc == null)
			return false;
		
		Element currRoot = doc.getRootElement();
		Element parrentCusto = currRoot.getChild("customers");
		List<Element> listCusto = parrentCusto.getChildren();
		if( customer.getLoanList()!= null)
		for (Loan item : customer.getLoanList()) {
			if(!deleteLoan(item)){
				return false;
			}
		}
		
		for (Element currCusto : listCusto) {
			int custoID = Integer.parseInt(currCusto.getChild("customerID").getText());
			if(customer.getCustomerID() == custoID){
				doc.removeContent(currCusto);
				return true;
			}
		}
		return false;
	}

	@Override
	public Book readBook(String isbn) {
		if(doc == null)
			return null;
		
		Element currRoot = doc.getRootElement();
		Element parrentBook = currRoot.getChild("books");
		List<Element> listBooks = parrentBook.getChildren();
		
		for (Element book : listBooks) {
			String bookIsbn = book.getChild("isbn").getText();
			if(isbn.equals(bookIsbn)){
				String title = book.getChild("title").getText();
				String author = book.getChild("author").getText();
				double price = Double.parseDouble(book.getChild("price").getText());
	
				return new Book(bookIsbn, title, author, price);
			}
		}
		return null;
	}

	@Override
	public Loan readLoan(String isbn, int costumerID) {
		if(doc == null)
			return null;
		
		Element currRoot = doc.getRootElement();
		Element parrentLoan = currRoot.getChild("loanBooks");
		List<Element> listLoan = parrentLoan.getChildren();
		
		for (Element loan : listLoan) {
			String bookIsbn = loan.getChild("isbn").getText();
			int custoID = Integer.parseInt(loan.getChild("CustomerID").getText());
			if(custoID == costumerID && bookIsbn.equals(isbn)){
				String startOfLoan = null;
				String endOfLoan = null;
				int newLoanID = Integer.parseInt(loan.getChild("loanID").getText());
					startOfLoan = loan.getChild("startOfLoan").getText();
					endOfLoan = loan.getChild("startOfLoan").getText();
				return new Loan(newLoanID,this.readBook(bookIsbn), this.readCustomer(costumerID), startOfLoan, endOfLoan);
			
			}
		}	
		return null;
	}
	

	@Override
	public Loan readLoan(int loanID) {
		if(doc == null)
			return null;
		
		Element currRoot = doc.getRootElement();
		Element parrentLoan = currRoot.getChild("loanBooks");
		List<Element> listLoan = parrentLoan.getChildren();
		
		for (Element loan : listLoan) {
			int newLoanID = Integer.parseInt(loan.getChild("loanID").getText());
			if(newLoanID == loanID){
				String startOfLoan = null;
				String endOfLoan = null; 
				String bookIsbn = loan.getChild("isbn").getText();
				int custoID = Integer.parseInt(loan.getChild("CustomerID").getText());
					startOfLoan = loan.getChild("startOfLoan").getText();
					endOfLoan = loan.getChild("startOfLoan").getText();
				
				return new Loan(newLoanID,this.readBook(bookIsbn), this.readCustomer(custoID), startOfLoan, endOfLoan);
			}
			
		}
		return null;
	}
	
	@Override
	public Customer readCustomer(int customerID) {
		if(doc == null)
			return null;
		
		Element currRoot = doc.getRootElement();
		Element parrentCusto = currRoot.getChild("customers");
		List<Element> listCustos = parrentCusto.getChildren();
		
		for (Element customer : listCustos) {
			int newCustomerID = Integer.parseInt(customer.getChild("customerID").getText());
			if(newCustomerID == customerID){
				String name = customer.getChild("name").getText();
				String surname = customer.getChild("surname").getText();
				String address = customer.getChild("name").getText();
				return new Customer(name, surname, newCustomerID, address);
			}
		}
		return null;
	}

	@Override
	public List<Loan> getLoansByBook(Book book) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Loan item : book.getLoanList()) {
			loans.add(readLoan(item.getLoanID()));
		}
		return loans;
	}
	
	@Override
	public List<Loan> getLoansByCustomer(Customer customer) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Loan item : customer.getLoanList()) {
			loans.add(readLoan(item.getLoanID()));
		}
		return loans;
	}

	@Override
	public int getBookCount() {
		Element currRoot = doc.getRootElement();
		List<Element> bookList = currRoot.getChild("books").getChildren();
		return bookList.size();
	}

	@Override
	public int getLoanCount() {
		Element currRoot = doc.getRootElement();
		List<Element> loanList = currRoot.getChild("loanBooks").getChildren();
		return loanList.size();
	}
	

	@Override
	public int getCustomerCount() {
		Element currRoot = doc.getRootElement();
		List<Element> custoList = currRoot.getChild("customers").getChildren();
		return custoList.size();
	}
	
	@Override
	public void openDataStorage() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not Implemented Method!");
	}

	@Override
	public boolean closeDataStorage() {
		return this.saveToFile();
	}


}
