package edu06.api;

import java.util.Date;

public class StringFormatTest {

	public static void main(String[] args) {
		Date today = new Date();
		System.out.printf("현재날짜:%s %n", today);
		
		// 현재날짜정보 : 년도4자리.월2자리.일2자리 
		String currentDate = String.format("현재날짜: %tY.%<tm.%<td %n", today); 
		System.out.println(currentDate);
		
		currentDate = String.format("현재날짜: %tY.%tm.%td %n", today, today, today); 
		System.out.println(currentDate);
	}
	
	public static void main1(String[] args) {
		// 형식 
		// %s %S  문자열, 대문자변환문자열   => %양수s  %-음수s 
		// %c %C  단일문자, 단일문자대문자변환문자열 
		// %b %B  boolean, 대문자변환 boolean
		// %d	   정수형숫자
		// %f     실수형숫자
		// %n	  라인이동
		
		// System.out.printf("");
		
		String str = "Hello world";
		System.out.printf("%20s,%-20S", str, str);
		System.out.printf("%s", "2020년 파이팅 하세요!!!");
		System.out.printf("%n\n");
		
		int no1 = 12345;
		System.out.printf("%d, %10d, %010d %n", no1, no1, no1);
		System.out.printf("%d, %<10d, %<010d %n", no1);
		
		double num1 = 12.345;
		System.out.printf("%f, %<10.2f, %<.3f, %<-10.2f %n", num1);
		
		// 실습 : 출력형식으로 출력되게 해보세요
		// 10 * 25.32356 = 253.236 => 실제 계산결과 253.235600 
		int val1 = 10;
		double val2 = 25.32356;
		System.out.printf("%d * %.5f = %.3f %n", val1, val2, (val1 * val2));
		
	}

}
