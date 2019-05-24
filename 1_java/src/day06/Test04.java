package day06;

public class Test04 {

	public static void main(String[] args) {
		BlockTest b1;
		System.out.println("start");
		b1 = new BlockTest();
		// BlockTest c = new BlockTest();
		System.out.println("end");

	}

}

class BlockTest {

	static {
		System.out.println("초기화 static { }");
	}

	{
		System.out.println("초기화 { }");

	}

	public BlockTest() {
		System.out.println("생성자");
	}
}
