package day08;

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

	@Override
	public void breath() {
		System.out.println("폐");
	}
}