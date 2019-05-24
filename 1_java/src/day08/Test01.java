package day08;

import static java.lang.Math.PI;
import static java.lang.Math.*;

public class Test01 {

	public static void main(String[] args) {
		System.out.println(Math.PI);
		System.out.println(PI);
		System.out.println(random());

		Fish f = new Fish("구피");
		Dog d = new Dog("허스키", "허숙희");

		Animal a = f;
		a.breath();
		a.print();

		exec(d);
		exec(f);
		
	}

	public static void exec(Animal f) {
		f.breath();
		f.print();

	}
}
