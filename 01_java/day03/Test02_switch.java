package day03;

import java.util.Scanner;

public class Test02_switch {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("성적처리 시작하려면 yes를 입력하세요");

		String msg = input.nextLine();
		if (!(msg != null && msg.equalsIgnoreCase("yes"))) {
			System.out.println("Application stop");
			if (input != null) {
				input.close();
				input = null;
			}
			return;
		}

		System.out.println("성적처리 Application start");
		System.out.println("성적을 입력하세요");
		System.out.println("국어 영어 수학 입력 예) 90 90 90 enter");

		int k1 = input.nextInt();
		int k2 = input.nextInt();
		int k3 = input.nextInt();
		input.nextLine();

		System.out.printf("국어 : %d 영어 : %d 수학 : %d\n", k1, k2, k3);
		double mean = (k1 + k2 + k3) / 3.;
		System.out.printf("평균 : %.2f\n", mean);

		char grade;
		switch ((int) mean / 10) {
		case 10:
		case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7:
			grade = 'C';
			break;
		case 6:
			grade = 'D';
			break;
		default:
			grade = 'F';
			break;
		}
		
		

		System.out.printf("성적 처리 결과 [%c] 등급\n", grade);

		if (input != null) {
			input.close();
			input = null;
		}
		System.out.println("Application stop");
	}

}
