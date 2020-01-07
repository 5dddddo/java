package edu06.api;

public class StringTest {

	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		String s3 = new String("hello");
		String s4 = new String("hello");
		
		// 문자열 비교 시에는 반드시 equals() 사용해야함
		System.out.println(s1 == s2);	// true
		System.out.println(s1 == s3);	// false
		System.out.println(s1 == s4);	// false
		System.out.println();
		
		System.out.println(s1.equals(s2));	// true
		System.out.println(s1.equals(s3));	// true
		System.out.println(s1.equals(s4));	// true
	}

}

class Member {
	String id = "guest";	// 명시적초기화
	String name;
	int age;
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
