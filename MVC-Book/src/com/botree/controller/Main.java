package com.botree.controller;

import java.util.List;
import java.util.Scanner;

import com.botree.bean.Book;
import com.botree.bean.User;
import com.botree.business.BookBo;
import com.botree.business.LoginBo;
import com.botree.exception.DuplicateBookException;
import com.botree.exception.InvalidUserException;
import com.botree.exception.IsbnNotFoundException;

public class Main {

	public static void main(String[] args) {
				
		boolean flag = true;
		
		do {
			var user = login();
			
			var loginBo = new LoginBo();
			
			try {
				flag = !loginBo.validateUser(user);
			}catch(InvalidUserException e) {
				System.out.println(e.getMessage());
				flag = true;
			}
		}while(flag);
	
	do {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select option");
		
		System.out.println("""
				1. Add Book \n
				2. View Book \n
				3. Delete Book \n
				4. Show All Book
				
				""");
		int option=sc.nextInt();
		
		switch(option) {
		case 1 -> add();
		
		case 2 -> find();
		
		case 3 -> delete();
		
		case 4 -> showAll();
		
		case 5 -> System.exit(0);
		
		default -> System.out.println("Wrong choice");
		
	}
  }while(true);
	}
	
	public static void add() {

		System.err.println("Add Page");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter isbn : ");
		int isbn = sc.nextInt();
				
		System.out.println("Enter Book name : ");
		String book_name = sc.next();
		
		System.out.println("Enter the Quantity : ");
		int quantity = sc.nextInt();
		
		System.out.println("Enter the Book Category : ");
		String book_category = sc.next();
		
		System.out.println("Enter the Price : ");
		int price = sc.nextInt();
		
		var b = new Book(isbn, book_name, quantity, book_category, price);

		var bBo = new BookBo();

		try {
			bBo.addBook(b);
			System.out.println(isbn + "registered successfully");

		} catch (DuplicateBookException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void find() {
        System.err.println("Find Page");
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter isbn : ");
		int isbn = sc.nextInt();
		
		var bBo = new BookBo();
		try {
		System.out.println(bBo.findBook(isbn));
		}catch(IsbnNotFoundException e) {
			System.out.println(e.getMessage());
		}
	   }
	
	public static void delete() {
		System.err.println("Delete Page");
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter isbn : ");
		int isbn = sc.nextInt();
			
		var bBo = new BookBo();
		
		try {
		bBo.deleteBook(isbn);
		System.out.println(isbn+"deleted successfully");
		
		}catch(IsbnNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void showAll() {
        System.err.println("Display Page");

        var bBo = new BookBo();
        List<Book> book = bBo.showAllBook();

        if (book.isEmpty()) {
            System.out.println("No student found.");
        } else {
            System.out.println("Student List:");
            for (Book books : book) {
                System.out.println(books);
            }
        }
    }
	
public static User login() {
	
	System.err.println("Login Page");
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter user name : ");
	String name = sc.next();
	
	System.out.println("Enter Password : ");
	String password = sc.next();
	
	return new User(name, password);
	
}


}
