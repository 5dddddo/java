package day09.ex;

public class Prob6 {

	public static void main(String[] args) {
		BookMgr bm = new BookMgr();
	//	bm.addBook(new Book("Hadoop", 43000));
	//	bm.addBook(new Book("SQL", 22000));
		bm.printBookList();
		System.out.println();
		bm.printTotalPrice();

	}

}
