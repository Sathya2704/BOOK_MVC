package com.botree.business;

import java.util.List;

import com.botree.bean.Book;
import com.botree.dao.BookDao;
import com.botree.exception.DuplicateBookException;
import com.botree.exception.IsbnNotFoundException;

public class BookBo {

	BookDao bDao = new BookDao();
	
	public boolean addBook(Book b) throws DuplicateBookException{
		
		return bDao.addBook(b);

	}
	
public Book findBook(int isbn) throws IsbnNotFoundException{
		
		return bDao.findBook(isbn);

	}

public boolean deleteBook(int isbn) throws IsbnNotFoundException {
	
	return bDao.deleteBook(isbn);
	
}
	
public List<Book> showAllBook() {

	return bDao.showAllbook();

}

}
