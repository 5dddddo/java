package day09;

public class Test01 {

	public static void main(String[] args) {
		String msg1 = "hello java";
		String msg2 = "hello java";

		System.out.println(msg1);
		System.out.println(msg2.equals(msg1));

		Employee emp1 = new Employee("홍길동", "기술부");
		Employee emp2 = new Employee("홍길동", "기술부");

		System.out.println(emp1.toString());
		System.out.println(emp2);
		System.out.println(emp2.equals(emp1));

	}

}
