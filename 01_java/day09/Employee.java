package day09;

public class Employee {

	private String name;
	private String dept;

	public Employee() {
		super();
	}

	public Employee(String name, String dept) {
		super();
		this.name = name;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	// Object의 toString
	@Override
	public String toString() {
		return "Employee [name=" + name + ", dept=" + dept + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj != null && obj instanceof Employee) {
			Employee tmp = (Employee) obj;
			if (name.equals(tmp.name) && dept.equals(tmp.dept))
				flag = true;
		}
		return flag;
	}

}
