package day11;

import java.util.ArrayList;
import java.util.List;

import day09.ex.Book;

public class Test05_stream {
	public static void main(String[] args) {

		List<Book> books = new ArrayList<Book>();
		books.add(new Book("java", 500));
		books.add(new Book("sql", 5100));
		books.add(new Book("servlet&jsp", 9500));
		books.add(new Book("c", 1500));
		books.add(new Book("html", 13500));
		books.add(new Book("html", 13500));
		books.add(new Book("html", 13500));
		books.add(new Book("html", 13500));

		books.forEach(i -> System.out.println(i.getTitle().charAt(0) + "**"));

		System.out.println("----------------------------");
		books.forEach(i -> System.out.println(i));

		// book 객체  중복 제거
		System.out.println("----------------------------");
		books.stream().distinct().forEach(i -> System.out.println(i));

		// book 객체 중 가격이 5000원 이상인 책 갯수
		long num = books.stream().filter(i -> i.getPrice() > 5000).count();
		System.out.println(num);

		int sum = books.stream().mapToInt(i -> i.getPrice()).sum();
		System.out.println(sum);

	}

}
