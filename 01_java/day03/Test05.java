package day03;

public class Test05 {

	public static void main(String[] args) {

		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 0)
				sum += i;
		}
		System.out.println(sum);
		System.out.println("-------------------");
		
		System.out.println("**** 구구단  ****");
	
		for (int i = 1; i <= 9; i++) {
			for (int j = 2; j <= 9; j++)
				System.out.printf("%d*%d=%2d |", j,i,i*j);
			System.out.println();
		}
	}

}
