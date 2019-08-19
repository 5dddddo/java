package day07;

public class Test04_fish {
	public static void main(String[] args) {

		Fish f = new Fish("구피");
		Dog d = new Dog("허스키", "허숙희");
	
		
		Animal a = null;
		a = d;
		a.breath();
		a.print();
		a = f;
		a.breath();
		a.print();
		
	}
}
