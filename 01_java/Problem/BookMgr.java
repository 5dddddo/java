package Problem;

public class BookMgr {
	private Book[] booklist;
	static int cnt = 0;

	public BookMgr() {
		booklist = new Book[3];
	}

	public void addBook(Book book) {
		if (cnt == booklist.length) {
			Book[] tmp = new Book[booklist.length * 2];
			System.arraycopy(booklist, 0, tmp, 0, booklist.length);
			booklist = tmp;
		}
		booklist[cnt++] = book;

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
			System.out.printf("책 이름 : %s 가격 : %d\n" ,booklist[i].getTitle(),booklist[i].getPrice());
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
