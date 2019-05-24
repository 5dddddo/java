package day10_collection;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class Test03_BookList {

	public static void main(String[] args) {
		// List<Book> list = new ArrayList<Book>();
		List<Book> list = new Vector<Book>();
		
		list.add(new Book("ccc", 9000));
		list.add(new Book("ddd", 5000));
		list.add(new Book("aaa", 6000));
		list.add(new Book("bbb", 8000));
		list.add(new Book("bbb", 8000));

		System.out.println(list);
		System.err.println();
//		Collections.sort(list);
		// list.remove(new Book("bbb", 8000));
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {

			System.out.println(list.get(i));
		}

	}

}

class Book implements Serializable, Comparable<Book> {
	private String title;
	private int price;

	public Book() {
		this(" ", 0);
	}

	public Book(String t, int p) {
		title = t;
		price = p;
	}

	@Override
	public String toString() {
		return " [title=" + title + ", price=" + price + "]";
	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Book) {
			if (((Book) obj).title.equals(this.title) && ((Book) obj).price == this.price)
				return true;

		}
		return false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title.length() > 0)
			this.title = title;
	}

	public int getPrice() {

		return price;
	}

	public void setPrice(int price) {
		if (price > 0)
			this.price = price;
	}

	public void print() {

		System.out.printf("%s %d\n", title, price);
	}

	@Override
	public int compareTo(Book o) {
//		return (price - o.price);

		return title.compareTo(o.title);
	}

}
