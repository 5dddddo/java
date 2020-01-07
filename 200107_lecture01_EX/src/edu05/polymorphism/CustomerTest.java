package edu05.polymorphism;

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
	
	/** 동적 자료구조 : Collection */
	public static void main(String[] args) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(new GeneralCustomer("user01", "pass01", "길동1", "010-1234-1000", "user01@work.com", "G"));
		customers.add(new GeneralCustomer("user02", "pass02", "길동2", "010-1234-2000", "user02@work.com", "G", 1000));
		customers.add(new SpecialCustomer("happy01", "pass11", "하린1", "010-3333-1000", "happy01@work.com", "S", "송중기"));
		customers.add(new SpecialCustomer("happy02", "pass12", "하린2", "010-3333-2000", "happy02@work.com", "S", "아이유"));	
	
		System.out.println();
		System.out.println(">>> 일반회원 정보 <<<");
		for (Customer customer: customers) {
			if (customer instanceof GeneralCustomer) {
				System.out.println(customer);
			}
		}
		
		System.out.println();
		System.out.println(">>> 우수회원 정보 <<<");
		for (Customer customer: customers) {
			if (customer instanceof SpecialCustomer) {
				System.out.println(customer);
			}
		}
		
		System.out.println();
		System.out.println(">>> 전체회원 정보 <<<");
		for (Customer customer: customers) {
			System.out.println(customer);
		}
	
	}
	
	/** 정적 자료구조 : Array */
	public static void main2(String[] args) {
		Customer[] customers = {
			new GeneralCustomer("user01", "pass01", "길동1", "010-1234-1000", "user01@work.com", "G"),
			new GeneralCustomer("user02", "pass02", "길동2", "010-1234-2000", "user02@work.com", "G", 1000),
			new SpecialCustomer("happy01", "pass11", "하린1", "010-3333-1000", "happy01@work.com", "S", "송중기"),
			new SpecialCustomer("happy02", "pass12", "하린2", "010-3333-2000", "happy02@work.com", "S", "아이유")
		};
		
		System.out.println();
		System.out.println(">>> 일반회원 정보 <<<");
		for (Customer customer: customers) {
			if (customer instanceof GeneralCustomer) {
				System.out.println(customer);
			}
		}
		
		System.out.println();
		System.out.println(">>> 우수회원 정보 <<<");
		for (Customer customer: customers) {
			if (customer instanceof SpecialCustomer) {
				System.out.println(customer);
			}
		}
		
		System.out.println();
		System.out.println(">>> 전체회원 정보 <<<");
		for (Customer customer: customers) {
			System.out.println(customer);
		}
		
	}
	
	/** Object Variable */
	public static void main1(String[] args) {
		// 일반회원
		GeneralCustomer g1 = new GeneralCustomer("user01", "pass01", "길동1", "010-1234-1000", "user01@work.com", "G");
		GeneralCustomer g2 = new GeneralCustomer("user02", "pass02", "길동2", "010-1234-2000", "user02@work.com", "G", 1000);
	
		// 우수회원
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
