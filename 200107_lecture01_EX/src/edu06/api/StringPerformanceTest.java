package edu06.api;

public class StringPerformanceTest {

	public static void main(String[] args) {
		int length = 100000;
		long duration1 = doA(length);
		long duration2 = doB(length);
		long duration3 = doC(length);
		
		System.out.println("String + 연결 : " + duration1);
		System.out.println("StringBuffer#append() : " + duration2);
		System.out.println("StringBuilder#append() : " + duration3);
	}

	/** 
	 * 객체생성없이 사용하기 위한 메서드 구현해보세요
	 * + 연산자를 이용해서 아규먼트로 받은 크기만큼 * 문자를 결합수행
	 * + 수행전 시간 , 수행후 시간을 설정하고  수행시간을 결과로 반환
	 * + doA(int) : long 
	 */
	public static long doA(int length) {
		String str = "";
		long startTime = System.currentTimeMillis();
		for (int index=0; index < length; index++) {
			str += "*";
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
	
	/** 
	 * 객체생성없이 사용하기 위한 메서드 구현해보세요
	 * StringBuffer 를 이용해서 아규먼트로 받은 크기만큼 * 문자를 결합수행
	 *  수행전 시간 , 수행후 시간을 설정하고  수행시간을 결과로 반환
	 * + doB(int) : long 
	 */
	public static long doB(int length) {
		StringBuffer str = new StringBuffer();
		long startTime = System.currentTimeMillis();
		for (int index=0; index < length; index++) {
			str.append("*");
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	/** 
	 * 객체생성없이 사용하기 위한 메서드 구현해보세요
	 * StringBuilder 를 이용해서 아규먼트로 받은 크기만큼 * 문자를 결합수행
	 * + 수행전 시간 , 수행후 시간을 설정하고  수행시간을 결과로 반환
	 * + doC(int) : long 
	 */
	public static long doC(int length) {
		StringBuilder str = new StringBuilder();
		long startTime = System.currentTimeMillis();
		for (int index=0; index < length; index++) {
			str.append("*");
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

}
