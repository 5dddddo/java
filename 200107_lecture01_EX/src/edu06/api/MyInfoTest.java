package edu06.api;

import java.util.Properties;

public class MyInfoTest {

	public static void main(String[] args) {
		System.out.println("<< main() 실행시 아규먼트 전달하기 >>");
//		System.out.println(args.length);
		Properties properties = System.getProperties();
		String grade = properties.getProperty("grade");
		System.out.println("grade : " + grade);
		
		if (args.length != 5) {
			System.err.println("Error : 프로그램 실행시 아규먼트로 올바르게 입력하세요");
			System.err.println("Usage : java MyInfortest 아이디 이름 수량 가격 주소");
			System.exit(0);
		}
		
		for (String str : args) {
			System.out.println(str);
		}
	}

}

/*
	1. 실행 : java edu02.MyInfoTest 공백구분자 데이터1 데이터2 데이터x
	
		dos> java edu02.MyInfoTest
		dos> java edu02.MyInfoTest hello world 100 200 "hello world"
	
*/