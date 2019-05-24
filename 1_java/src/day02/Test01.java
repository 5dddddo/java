package day02;

import java.util.Date;

public class Test01 {

	public static void main(String[] args) {

		char i = 'a';
		System.out.println(i);

		Date d = new Date();
		System.out.println(d.toLocaleString());

		Date d2 = d;
		d = null;

		String ss = new Date().toString();
		System.out.println(ss);

		double pi = Math.PI;
		int r = (int) (Math.random() * 45 + 1);
		int r1 = (int) Math.random();
		
		System.out.println(pi + " " + r + " " + r1); //string 객체 생성 많아져서 성능 저하 됨.
		System.out.printf("pi = %f, r =  %d, r1 = %d\n", pi, r, r1);
		System.out.printf("pi = %.2f, r =  %d, r1 = %d\n", pi, r, r1);
		
		
		String s1 = "hello";
		String s2 = new String("hello");

	}

}
