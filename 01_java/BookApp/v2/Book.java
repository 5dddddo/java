package BookApp.v2;

import java.io.Serializable;

public class Book implements Serializable{
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
		return "Book [title=" + title + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
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
