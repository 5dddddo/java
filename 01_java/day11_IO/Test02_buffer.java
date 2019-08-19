package day11_IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test02_buffer {

	public static void main(String[] args) {
		FileInputStream fi = null;
		FileOutputStream fo = null;

		String fileName = "C:\\lib\\Ben.mp3";

		int cnt = 0; // r/w 횟수
		int read = 0; // 읽어온 문자
		try {
			fi = new FileInputStream(fileName);
			fo = new FileOutputStream("c:\\lib\\abc.mp3");

			System.out.println("***** 복사 시작 *****");

			// 메모리 낭비쓰~
			byte[] buffer = new byte[fi.available()]; // b의 크기만큼 읽음 -> buffer
			while (fi.read(buffer) != -1) {

				fo.write(buffer);
				cnt++;
			}

			System.out.println("***** 복사 완료 *****");
			System.out.println("IO cnt : " + cnt);

		} catch (FileNotFoundException e) {
			System.out.println("file을 준비해 주세요.");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			try {
				if (fi != null)
					fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fo != null)
					fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main End");
	}

}
