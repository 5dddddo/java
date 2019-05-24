package day10_collection;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Test04_Set {

	public static void main(String[] args) {

		//주머니 생김
		Set<Book> set = new TreeSet<Book>();
		
		set.add(new Book("ccc", 9000));
		set.add(new Book("ddd", 5000));
		set.add(new Book("aaa", 6000));
		set.add(new Book("bbb", 8000));
		set.add(new Book("bbb", 8000));
		System.out.println(set);

//		for(Book data : set) {
//			System.out.println(data);
//		}
//		
//		
		Iterator<Book> it = set.iterator();
		while (it.hasNext()) {
			Book book = (Book) it.next();
			// System.out.println(book);
			if (book.getTitle().equalsIgnoreCase("bbb")) {
				it.remove();
			}

		}

		System.out.println(set);
		
		
		
	}

}
