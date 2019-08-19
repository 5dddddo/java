package Person;

public class Test_Person3 {

	public static void main(String[] args) {
		Person[] p = { new Student("홍길동", 20, 200201),
				new Student("김길동", 20, 200201), 
				new Student("오길동", 20, 200201),
				new Teacher("이선생", 30, "JAVA"),
				new Teacher("박선생", 30, "C"), 
				new Teacher("고선생", 30, "SQL"),
				new Employee("유관순", 40, "교무과")
				};

		for (Person data : p) 
			personPrint(data);
	
		for (Person data : p) {
			if (data instanceof Student) {
				System.out.print(data.getName().charAt(0) + "** "); 
				System.out.println(((Student)data).getId()); 
				//id 변수는 overriding 된 자원이 X
				//부모 클래스에서 자식  클래스만 가지고 있는 자원에 access 하려면 down casting 필수
			}
		}
	}

	public static void personPrint(Person e) {
		e.print();
	}
}
