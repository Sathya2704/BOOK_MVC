package com.botree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.botree.bean.Book;
import com.botree.constants.QueryConstants;
import com.botree.exception.DuplicateBookException;
import com.botree.exception.IsbnNotFoundException;
import com.botree.util.BUtil;

public class BookDao {

public boolean addBook(Book b) throws DuplicateBookException{
		
		Connection conn = BUtil.getConnection();
		PreparedStatement pstmt = null;
		
		try {
		      pstmt=conn.prepareStatement(QueryConstants.INSERT_SQL);
		      pstmt.setInt(1, b.isbn());
		      pstmt.setString(2, b.book_name());
		      pstmt.setInt(3, b.quantity());
		      pstmt.setString(4, b.book_category());
		      pstmt.setInt(5, b.price());

		      pstmt.execute();
		      
		      return true;
		      
		}catch(Exception e) {
			e.printStackTrace();
			throw new DuplicateBookException(b.isbn()+"already exist");
		}
	}

public Book findBook(int isbn) throws IsbnNotFoundException{
	Connection conn = BUtil.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    
    try {
        pstmt = conn.prepareStatement(QueryConstants.SEARCH_SQL);
        pstmt.setInt(1, isbn);
        
        rs = pstmt.executeQuery();
        
        if (rs.next()) 
        	
            return new Book(rs.getInt("isbn"), rs.getString("book_name"), rs.getInt("quantity"), rs.getString("book_category"), rs.getInt("price"));
            
    } catch (Exception e) {
        e.printStackTrace();
    } 
    throw new IsbnNotFoundException(isbn+" not found");

}
	
public boolean deleteBook(int isbn) throws IsbnNotFoundException {

	Connection conn = BUtil.getConnection();
	PreparedStatement pstmt = null;

	try {
		pstmt = conn.prepareStatement(QueryConstants.DELETE_SQL);
		pstmt.setInt(1, isbn);
		
		pstmt.execute();

		return true;

	} catch (Exception e) {
		e.printStackTrace();
		throw new IsbnNotFoundException(isbn + " not found");
	}
}

public List<Book> showAllbook()  {
	List<Book> bList = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    conn = BUtil.getConnection();
    try {
    pstmt = conn.prepareStatement(QueryConstants.SHOWSQL);
    rs = pstmt.executeQuery();

    while (rs.next()) {
        Book book = new Book(rs.getInt("isbn"), rs.getString("book_name"), rs.getInt("quantity"), rs.getString("book_category"), rs.getInt("price"));
        bList.add(book);
    }
    }catch(Exception e) {
    	e.printStackTrace();
    }

    return bList;
}
	
}
