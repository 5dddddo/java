package Person;

public class Test_Person2 {

	public static void main(String[] args) {
		Student s = new Student("홍길동", 20, 200201);
		Teacher t = new Teacher("이순신", 30, "JAVA");
		Employee e = new Employee("유관순", 40, "교무과");

		Test_Person2.personPrint(e);
		Test_Person2.personPrint(s);
		Test_Person2.personPrint(t);

	}

	// 매개변수로 넘어온 class에 print()가 없으면 Error
	public static void personPrint(Person e) {
		e.print();
	}

}
