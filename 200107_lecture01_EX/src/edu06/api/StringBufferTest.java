package edu06.api;

public class StringBufferTest {

	public static void main(String[] args) {
//		StringBuffer buffer = "hello";	// 컴파일 오류 StringBuffer = String => 타입이 다름
		StringBuffer buffer = new StringBuffer();
		System.out.println(buffer.capacity());

		StringBuffer buffer2 = new StringBuffer(100);
		System.out.println(buffer2.capacity());
		
		StringBuffer buffer3 = new StringBuffer(2);
		System.out.println(buffer3.capacity());
		
		buffer.append("우리");
		buffer.append("나라");
		buffer.append("만세");
		
		buffer2.append("대한").append("민국").append("만세");
		
		System.out.println(buffer);
		System.out.println(buffer2);
		
	}

}
