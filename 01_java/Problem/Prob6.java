package Problem;

import java.util.Scanner;

public class Prob6 {

	public static void main(String[] args) {
		BookMgr bm = new BookMgr();
		bm.addBook(new Book("Java Program", 15500));
		bm.addBook(new Book("JSP Program", 16000));
		bm.addBook(new Book("SQL Fundamentals", 18000));
		bm.addBook(new Book("JDBC Program", 20000));
		bm.addBook(new Book("EJB Program", 30000));
		bm.addBook(new Book("자바", 3300));

		bm.printBookList();
		System.out.println();
		bm.printTotalPrice();

	}

}
