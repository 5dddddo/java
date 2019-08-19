package day07;

public class Animal extends Object {
	String kind = "동물의 종류";

	public Animal() {
	}

	public Animal(String kind) {
		this.kind = kind;
	}

	public void breath() {
		System.out.println("폐");
	}
	public void print() {
		
	}

}

class Dog extends Animal {
	String name;
	String kind;

	public Dog(String kind, String name) {
		super("강아지");
		this.name = name;
		this.kind = kind;
	}

	public Dog() {
		super("강아지");
	}

	public void print() {
		System.out.printf("동물 : %s (%s) 이름 : %s\n", super.kind, this.kind, this.name);
	}
}