package day04;

import java.util.Arrays;

public class Test01_array {

	public static void main(String[] args) {
		int[] score; // 배열 선언
		score = new int[5]; // 배열 생성
		double m = 0.0;

		score[0] = 90;
		score[1] = 95;
		score[2] = 60;
		score[3] = 70;
		score[4] = 88;
		// System.out.println(Arrays.toString(score));

		String[] names = new String[5];
		names[0] = "홍길동";
		names[1] = "김길동";
		names[2] = "최길동";
		System.out.println(Arrays.toString(names));

		int sum = 0;
		for (int i = 0; i < score.length; i++)
			sum += score[i];
		m = sum / 5.;

		for (int i = 0; i < score.length; i++) {
			if (names[i] != null)
				System.out.printf("%s** :  %3d\n", names[i].charAt(0), score[i]);
			else
				System.out.printf("이름없음 :  %3d\n", score[i]);
		}

		System.out.printf("학생 평균 %.2f\n", m);

	}

}
