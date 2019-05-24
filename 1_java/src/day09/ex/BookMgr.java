package day09.ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Problem.Book;

public class BookMgr {
	private Book[] booklist;
	static int cnt = 0;

	public BookMgr() {
		booklist = new Book[10];
		try (Scanner sc = new Scanner(new File("C:\\\\workspace_4_23\\\\Sample_01\\\\Bookdata.txt"))) {
			while (sc.hasNextLine()) {
				String[] tmp = sc.nextLine().split("/");
				Book b = new Book(tmp[0], Integer.parseInt(tmp[1]));
				try {
					if (!checkDup(b))
						addBook(b);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("공간이 부족합니다.");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Bookdata.txt 파일이 존재하지 않음");
			e.printStackTrace();
		}
	}

	public BookMgr(Book[] booklist) {
		super();
		this.booklist = booklist;
		cnt = booklist.length;
	}

	public BookMgr(String t, int p) {
		booklist = new Book[10];
		if (cnt < booklist.length - 1) {
			Book tmp = new Book(t, p);
			if (!checkDup(tmp)) {
				booklist[cnt++] = tmp;
			}
		} else
			System.out.println("공간이 부족합니다.");

	}

	public void addBook(Book book) {
		if (cnt == booklist.length) {
			Book[] tmp = new Book[booklist.length * 2];
			System.arraycopy(booklist, 0, tmp, 0, booklist.length);
			booklist = tmp;
		}
		
		if (!checkDup(book))
			booklist[cnt++] = book;
	}

	public boolean checkDup(Book book) {
		for (int i = cnt - 1; i >= 0; i--) {
			if (book.equals(booklist[i])) {
				System.out.printf("%s는 이미 등록된 책입니다.\n", book.getTitle());
				return true;
			}
		}
		return false;

	}

	public Book[] getBooklist() {
		return booklist;
	}

	public void setBooklist(Book[] booklist) {
		this.booklist = booklist;
	}

	public int getCnt() {
		return cnt;
	}

	public void printBookList() {
		System.out.println("=== 책 목록 ===");

		for (int i = 0; i < cnt; i++) {
			System.out.printf("책 이름 : %s 가격 : %d\n", booklist[i].getTitle(), booklist[i].getPrice());
		}
	}

	public void printTotalPrice() {
		int sum = 0;
		for (int i = 0; i < cnt; i++)
			sum += booklist[i].getPrice();

		System.out.println("=== 책 가격의 총합 ===");
		System.out.printf("전체 책 가격의 합 : %d\n", sum);

	}

}
