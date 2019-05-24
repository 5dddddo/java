package day03;

public class Test04_loop {

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++)
			System.out.println("for loop");

		int i = 10;
		while (i > 5) {
			System.out.println("while loop");
			i--;
		}

		do {
			System.out.println("do while");
		} while (i > 5);

		boolean flag = false;
		while (!flag) {
			i--;
			System.out.println("loop");
			if (i == 0)	flag = !flag;
			
		}

	}

}
