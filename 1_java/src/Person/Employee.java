package Person;

public class Employee extends Person {
	private String dept;

	public String getDept() {
		return dept;
	}

	public Employee() {
		super();
	}

	public Employee(String name, int age, String dept) {
		super(name, age);
		this.dept = dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public void print() {
		System.out.printf("이   름 : %s    나 이 : %d     부    서 : %s\n", name, age, dept);
	}
}
