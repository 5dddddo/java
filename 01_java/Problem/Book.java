package Problem;

public class Book {
	private String title;
	private int price;

	public Book() {
		this(" ", 0);
	}

	public Book(String t, int p) {
		this.setTitle(t);
		this.setPrice(p);
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj != null && obj instanceof Book) {
			if (((Book) obj).title.equals(this.title) && ((Book) obj).price == this.price)
				flag = true;
		}
		return flag;
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

}
