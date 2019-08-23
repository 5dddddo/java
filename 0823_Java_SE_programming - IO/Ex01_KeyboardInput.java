package javaIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex01_KeyboardInput {

	public static void main(String[] args) {
		System.out.println("키보드로 한 줄을 입력해요! ");

		// 표준 입력 ( Keyboard )으로부터 입력을 받기 위해
		// Keyboard와 연결된 Stream 객체 필요

		// java는 표준입력에 대한 Stream을 기본적으로 제공
		// => System.in : byteStream
		// Stream은 이렇게 InputStream과 OutputStream으로 구분하기도 하고
		// byteStream과 reader, writer 계열의 stream으로도 구분

		// byteStream : 문자열이 아닌 기본 데이터형(int,boolean,...)의 입력을 받을 때
		// reader, writer 계열 : 문자열 입력을 받을 때 효율적으로 처리 가능

		// 일반 스트림을 Reader로 만들어주는 역할
		// 한 줄을 읽어오지 못하기 때문에 반복문 처리 필요 -> 비효율적
		InputStreamReader isr = new InputStreamReader(System.in);
		// 사용하기 편리한 BufferedReader로 업그레이드
		BufferedReader br = new BufferedReader(isr);

		try {
			// readLine() : block method
			// 				데이터가 들어올 때까지 기다림
			String input = br.readLine();
			System.out.println("입력받은 데이터  : " + input);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
