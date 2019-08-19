package day08;

public class Test02 {

	public static void main(String[] args) {

		// abstract class는 객체 생성 불가능
		// Animal a = new Animal() ;

		// Type 선언은 가능
		Animal[] animals = { new Fish("쿠피"), new Dog("시베리아", "캐리") };

		for (Animal data : animals) {
			exec(data);
		}

	}

	public static void exec(Animal f) {
		f.breath();
		f.print();

	}
}
