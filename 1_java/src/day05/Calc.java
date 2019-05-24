package day05;

public class Calc {

	public static int add(int... a) { // 가변 인자는 맨 뒤에 위치
		int sum = 0;
		for (int tmp : a)
			sum += tmp;
		return sum;

	}

	public static double add(double x, double y) {
		return x + y;
	}

	public static int sub(int x, int y) {
		return x - y;
	}

	public static int multi(int x, int y) {
		return x * y;
	}

	public static int div(int x, int y) {
		return x / y;
	}

}
