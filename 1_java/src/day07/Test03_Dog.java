package day07;

public class Test03_Dog {

	public static void main(String[] args) {
		Dog d = new Dog("허스키", "허숙희");
		d.print();

		System.out.println(d.kind);
		System.out.println(((Animal) d).kind); // up casting : 부모의 kind 변수 print
		System.out.println("----------------------");

		Object o = new Dog("허스키", "허숙희"); // down casting 필요

		if (o instanceof Animal)
			System.out.println(((Animal) o).kind);

		if (o instanceof Dog)
			System.out.println(((Dog) o).kind);

		System.out.println("----------------------");

		if (o instanceof String)
			System.out.println(((String) o).toString());

	}

}
