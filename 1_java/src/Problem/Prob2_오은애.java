package Problem;

import java.util.Scanner;

public class Prob2_오은애 {
	public static void main(String[] args) {

		int x = 2;
		int y = 5;
		char c = 'A'; // 'A' 65
		System.out.println(y >= 5 || x < 0 && x > 2); // true
		System.out.println(!('A' <= c && c <= 'Z')); // false
		System.out.println('C' - c); // 2
		System.out.println('5' - '0'); // 5
		System.out.println(c + 1); // 66
		System.out.println(++c); // B
		System.out.println(c++); // B
		System.out.println(c); // C

		/*
		 * 변수 num의 값에따라 양수 음수 0을 출력하는 코드를 완성하세요. 힌트: 삼항연산자.
		 */
		int num = -90;
		System.out.println("양수 음수  0 판별후 출력");
		System.out.println(num >= 0 ? (num == 0 ? "0" : "양수") : "음수");

		/*
		 * 다음은 대문자를 소문자로 변경하는 코드입니다. 변수 ch에 저장된 문자가 대문자 인 경우에만 소문자로 변경하는 코드를 완성 합니다.
		 */
		char ch = 'P';
		char lowerCase = (char) (ch >= 'A' && ch <= 'Z' ? ch + 32 : ch);
		System.out.print("ch:" + ch);
		System.out.println(" to lowerCase :" + lowerCase);

		/* 년도를 입력받아 윤년인지 판별하여 출력해 보세요 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("연도를 입력하세요 : ");
		int year = keyboard.nextInt();

		System.out.printf("%d년은 %s\n", year, year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? "윤년" : "평년");

		keyboard.close();
		keyboard = null;
	}
}
