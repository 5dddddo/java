package edu06.api;

import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		String str1 = "서울특별시,부산광역시,대구광역시,경기도,인천광역시";
		String str2 = "010.1234.2773";
		
		System.out.println("주소 토큰링");
		String[] tokens1 = str1.split(",");
		for (String str : tokens1) {
			System.out.println(str);
		}
		
		System.out.println();
		System.out.println("휴대폰 토큰링 : String#split()");
		String[] tokens2 = str2.split(".");
		for (String str : tokens2) {
			System.out.println(str);
		}
	
		System.out.println();
		System.out.println("휴대폰 토큰링 : StringTokenizer");
		StringTokenizer tokens3 = new StringTokenizer(str2, ".");
		while(tokens3.hasMoreTokens()) {
			System.out.println(tokens3.nextToken());
		}
		
		System.out.println();
		System.out.println("휴대폰 토큰링 : String#split()");
		String[] tokens4 = str2.split("\\.");
		for (String str : tokens4) {
			System.out.println(str);
		}
		
		String email = "admin@work.co.kr";
		String id = email.split("@")[0];
		String domain = email.split("@")[1];
		
		System.out.println();
		System.out.println("id: " + id);
		System.out.println("domain: " + domain);
		
		StringTokenizer email2 = new StringTokenizer(email, "@");
		id = email2.nextToken();
		domain = email2.nextToken();

		System.out.println();
		System.out.println("id: " + id);
		System.out.println("domain: " + domain);
		
		System.out.println();
		System.out.println("domain detail");
		for (String domainDetail : domain.split("\\.")) {
			System.out.println(domainDetail);
		}
		
	}

}
