package Person;

public class Test_Person {

	public static void main(String[] args) {
		Student s = new Student("홍길동", 20, 200201);
		Teacher t = new Teacher("이순신", 30, "JAVA");
		Employee e = new Employee("유관순", 40, "교무과");

		Person p = s;
		p.print();
		p = t;
		p.print();
		p = e;
		p.print();
	}

}
