package day04;

import java.util.Arrays;

public class Test05 {

	public static void main(String[] args) {

		/*
		 * System.out.println(args); System.out.println(Arrays.deepToString(args));
		 * System.out.println(args.length); String [] s = {};
		 */

		if (args.length == 0) {
			System.out.println("사용 방법");
			System.out.println("java day04.Test05 홍길동/90/70/88");
			System.out.println("성적 데이터를 입력해주세요");
			return;
		}

		System.out.println("성적 처리");
		String[] data = args[0].split("/");
		System.out.println(Arrays.toString(data));

		int sum = 0;
		for (int i = 1; i < data.length; i++) {
			sum += Integer.parseInt(data[i]);

		}
		System.out.printf("학생 이름 : %s, 평균 : %.2f \n", data[0], sum / 3.);
	}

}
