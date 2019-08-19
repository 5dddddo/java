package day07;

public class Fish extends Animal {
	String name;

	public Fish() {
		super("물고기");
	}

	public Fish(String name) {
		super("물고기");
		this.name = name;
	}

	public void print() {
		System.out.printf("동물 : %s 이름 : %s\n", super.kind, this.name);
	}

	
	@Override // 이거슨 override 메소드다~ 오타 방지
	public void breath() {
		System.out.println("아가미");
	}

}
