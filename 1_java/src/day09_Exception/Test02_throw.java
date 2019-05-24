package day09_Exception;

import java.io.IOException;

public class Test02_throw {

	public static void main(String[] args) {
		System.out.println("start");
		try {
			throw new Exception("예외발생"); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		 System.out.println("정상 종료");
	}

}
