package day10_collection;

public class Employee<T, S> {
//public class Employee<T extends Number> {
	private S name;
	private T number;

	public Employee() {
		super();
	}

	public Employee(S name, T number) {
		super();
		this.name = name;
		this.number = number;
	}

	public S getName() {
		return name;
	}

	public void setName(S name) {
		this.name = name;
	}

	public T getNumber() {
		return number;
	}

	public void setNumber(T number) {
		this.number = number;
	}

}
