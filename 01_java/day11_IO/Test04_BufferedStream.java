package day11_IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test04_BufferedStream {

	public static void main(String[] args) {
		FileInputStream fi = null;
		FileOutputStream fo = null;

		BufferedInputStream bi = null;
		BufferedOutputStream bo = null;

		String fileName = "c:\\lib\\dbinfo.txt";

		int cnt = 0; // r/w 횟수
		int read = 0; // 읽어온 문자
		try {

			fi = new FileInputStream(fileName);
			fo = new FileOutputStream("c:\\lib\\abc.txt");

			bi = new BufferedInputStream(fi);
			bo = new BufferedOutputStream(fo);

			while ((read = bi.read()) != -1) {
				bo.write(read);
				cnt++;
			}
			// buffer가 꽉 차면 자동으로 비워지는데 그렇지 않으면 자동으로 비워지지 않음
			// buffer가 채워져 있으면 close가 안 되기 때문에 비우기 필수!
			bo.flush();

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
				if (fo != null)
					fo.close();
				if (bi != null)
					bi.close();
				if (bo != null)
					bo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Main End");
	}

}
