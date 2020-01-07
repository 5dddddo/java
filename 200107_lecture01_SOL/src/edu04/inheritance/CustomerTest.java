package edu04.inheritance;

import java.util.ArrayList;

/**
 * <pre>
 * TODO : 회원관리에 대한 테스트 클래스
 * 
 * # 자료 저장구조
 * - 정적 자료구조 : Array
 * - 동적 자료구조 : Collection (API: java.util.*)
 * </pre>
 * @author Administrator
 *
 */
public class CustomerTest {
	
	public static void main(String[] args) {

		// 다중 저장구조 : Array
		GeneralCustomer[] general = new GeneralCustomer[2];
		general[0] = new GeneralCustomer("user01", "pass01", "길동1", "010-1234-1000", "user01@work.com", "G");
		general[1] = new GeneralCustomer("user02", "pass02", "길동2", "010-1234-2000", "user02@work.com", "G", 1000);
		
		SpecialCustomer[] special = new SpecialCustomer[2];
		special[0] = new SpecialCustomer("happy01", "pass11", "하린1", "010-3333-1000", "happy01@work.com", "S", "송중기");
		special[1] = new SpecialCustomer("happy02", "pass12", "하린2", "010-3333-2000", "happy02@work.com", "S", "아이유");
		
		System.out.println();
		System.out.println(">>> 일반회원 정보 <<<");
		for (int index = 0; index < general.length; index++) {
			System.out.println(general[index]);
		}
		
		System.out.println();
		System.out.println(">>> 우수회원 정보 <<<");
		for (int index = 0; index < special.length; index++) {
			System.out.println(special[index]);
		}
		
		System.out.println();
		System.out.println(">>> 전체회원 정보 <<<");
		for (GeneralCustomer customer: general) {
			System.out.println(customer);
		}
		for (SpecialCustomer customer: special) {
			System.out.println(customer);
		}
		
	}

	public static void main1(String[] args) {
		// 일반회원 : 단일 저장구조 => 다중 저장구조
		GeneralCustomer g1 = new GeneralCustomer("user01", "pass01", "길동1", "010-1234-1000", "user01@work.com", "G");
		GeneralCustomer g2 = new GeneralCustomer("user02", "pass02", "길동2", "010-1234-2000", "user02@work.com", "G", 1000);
	
		// 우수회원 : 단일 저장구조 => 다중 저장구조
		SpecialCustomer s1 = new SpecialCustomer("happy01", "pass11", "하린1", "010-3333-1000", "happy01@work.com", "S", "송중기");
		SpecialCustomer s2 = new SpecialCustomer("happy02", "pass12", "하린2", "010-3333-2000", "happy02@work.com", "S", "아이유");
	
		System.out.println();
		System.out.println(">>> 일반회원 정보 <<<");
		System.out.println(g1);
		System.out.println(g1);
		
		System.out.println();
		System.out.println(">>> 우수회원 정보 <<<");
		System.out.println(s1);
		System.out.println(s1);
		
		System.out.println();
		System.out.println(">>> 전체회원 정보 <<<");
		System.out.println(g1);
		System.out.println(g1);
		System.out.println(s1);
		System.out.println(s1);
	}
	
}
