package day08;

public abstract class Animal extends Object {
	// abstract 선언으로 overriding에 강제성 생김

	String kind = "동물의 종류";

	public Animal() {
	}

	public Animal(String kind) {
		this.kind = kind;
	}

	public abstract void breath();

	public void print() {

	}

}
