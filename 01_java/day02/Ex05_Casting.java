package day02;

public class Test05 {

	public static void main(String[] args) {

		byte a = 10;
		byte b= 20;
	//	byte c = a+b;  a+b 수행 시 int + int 로 형변환 됨
	//	byte c = (byte)a+b;
		
		byte c = (byte)(a+b); 
		
		char c1 = 'a';
		char c2= (char)(c1+1); //c1 + 1은 int 연산 됨
		c2 = ++c1; // int 연산 없이 바로 b가 됨
		
		System.out.println(0.1d == 0.1f); //false : 부동소수점 변환이 달라서 float에서 double로 casting시 다른 값 가짐
		System.out.println(10.0d == 10.0f); //true
		
		
		
		
	}

}
