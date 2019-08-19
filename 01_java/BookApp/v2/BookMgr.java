package BookApp.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BookMgr {
	private List<Book> booklist = new ArrayList<Book>();

	public BookMgr() {
		try (Scanner sc = new Scanner(new File("C:\\\\workspace_4_23\\\\Sample_01\\\\Bookdata.txt"))) {
			while (sc.hasNextLine()) {
				String tmp = sc.nextLine();
				addBook(tmp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Bookdata.txt 파일이 존재하지 않음");
			e.printStackTrace();
		}
	}

	public BookMgr(List<Book> booklist) {
		super();
		this.booklist = booklist;
	}

	public void addBook(String book) {
		String[] tmp = book.trim().split("/");
		String t = tmp[0];
		int p = Integer.parseInt(tmp[1]);

		if (booklist.contains(new Book(t, p)))
			System.out.printf("%s는 이미 등록된 책 입니다.\n", t);
		else {
			System.out.println("새로운 책 " + t + " 을(를) 등록했습니다.");
			booklist.add(new Book(t, p));
		}
	}

	public void updateBook(String cmd) {
		String[] tmp = cmd.trim().split("/");
		String t = tmp[0];
		int p = Integer.parseInt(tmp[1]);
		Book b = new Book(t, p);

		if (booklist.contains(b)) {
			System.out.println("수정할 내용의 번호를 입력하세요. ex) 1.제목 or 2.가격 or 3.제목과 가격");
			int pos = booklist.indexOf(b);
			Scanner sc = new Scanner(System.in);
			int num = Integer.parseInt(sc.nextLine());
			switch (num) {
			case 1:
				System.out.println("수정할 책 제목을 입력하세요");
				cmd = sc.nextLine();
				System.out.printf("책 제목이 %s에서 %s로 변경되었습니다.\n", t, cmd);
				booklist.get(pos).setTitle(cmd);
				break;
			case 2:
				System.out.println("수정할 가격을 입력하세요");
				num = Integer.parseInt(sc.nextLine());
				System.out.printf("책 가격이 %d원에서 %d원으로 변경되었습니다.\n", p, num);
				booklist.get(pos).setPrice(num);
				break;
			case 3:
				System.out.println("수정할 책 제목과 가격을 입력하세요");
				tmp = sc.nextLine().split("/");
				System.out.printf("책 제목과 가격이 %s %d원으로 변경되었습니다.\n", tmp[0], Integer.parseInt(tmp[1]));
				booklist.get(pos).setTitle(tmp[0]);
				booklist.get(pos).setPrice(Integer.parseInt(tmp[1]));
	
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				break;
			}

		} else {
			System.out.println("등록되지 않은 책입니다.");
		}
	}

	public void deleteBook(String cmd) {
		String[] tmp = cmd.trim().split("/");
		String t = tmp[0];
		int p = Integer.parseInt(tmp[1]);

		Book b = new Book(t, p);
		if (booklist.contains(b)) {
			booklist.remove(b);
			System.out.println("책 " + t + " 을 목록에서 삭제했습니다.");
		} else {
			System.out.println("등록되지 않은 책입니다.");
		}
	}

	public void searchBook(String book) {
		boolean flag = false;
		Iterator<Book> it = booklist.iterator();
		int num = 0;
		while (it.hasNext()) {
			Book b = it.next();
			if (b.getTitle().equals(book)) {
				System.out.printf("%d번째 책 %s의 가격은 %d원입니다.\n", ++num, b.getTitle(), b.getPrice());
				flag = true;
			}
		}
		if (flag)
			return;
		else
			System.out.println("등록되지 않은 책입니다.");
	}

	public void printBookList() {
		System.out.println("=== 책 목록 ===");
		booklist.forEach(i -> System.out.printf("책 이름 : %s 가격 : %d\n", i.getTitle(), i.getPrice()));

	}

}
