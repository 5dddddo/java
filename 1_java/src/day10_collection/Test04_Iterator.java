package day10_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Test04_Iterator {

	public static void main(String[] args) {
		List<Book> list = new Vector<Book>();

		list.add(new Book("ccc", 9000));
		list.add(new Book("ddd", 5000));
		list.add(new Book("aaa", 6000));
		list.add(new Book("bbb", 8000));
		list.add(new Book("bbb", 8000));

		// Collection framework에서 Collection(List, Set)에 저장된 요소를 읽어 오는 표준 방법
		Iterator<Book> it = list.iterator();
		while (it.hasNext()) {
			Book book = (Book) it.next();
			// System.out.println(book);
			if (book.getTitle().equalsIgnoreCase("bbb")) {
				it.remove();
			}

		}

		System.out.println(list);
	}

}
