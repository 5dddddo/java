package day08;

import Person.Person;
import Person.Student;

public class Test03 {

	public static void main(String[] args) {

		Object[] obj = { new Fish("쿠피"), new Dog("시베리아", "캐리"), new Student("", 22, 1234), "hello java" };

		for (Object data : obj)
			exec(data);

	}

	// object 타입 사용시 down casting 필수
	public static void exec(Object obj) {
		if (obj instanceof Person)
			((Person) obj).print();
		if (obj instanceof Animal)
			((Animal) obj).print();
	}
}
