package day10_collection;

public class EmployeeTest {

	public static void main(String[] args) {
//
//		Employee<String> e = new Employee<String>("홍길동", "201912");
//		System.out.println(e.number.substring(0, 4));
//
//		Employee<Integer> e1 = new Employee<Integer>("오길동", 201903);
//		System.out.println(e1);
//
//		Employee e2 = new Employee("민길동", "201904");
//		System.out.println(e2.number instanceof String ?
//				((String) e2.number).substring(0, 4) : e2.number);

		Employee<String, String> e = new Employee<String, String>("홍길동", "201912");
		System.out.println(e);

		Employee e1 = new Employee();
		System.out.println(e);

	}

}
