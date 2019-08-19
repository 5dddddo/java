package day09_Exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test04_IOException {

	public static void main(String[] args) {
		System.out.println("start");
		Scanner sc = null;
		try {
			sc = new Scanner(new File("C:\\workspace_4_23\\Sample_01\\a.txt"));
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				String[] bookdata = data.split("/");
				String title = bookdata[0];
				int price = Integer.parseInt(bookdata[1]);
				System.out.println(title + " " + price);
			}
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.out.println("존재하지 않는 파일입니다.");
		} catch (Exception e) {
			System.out.println("bookdata parsing Error");
			System.out.println(e.getMessage());
		}

		finally {
			if (sc != null) {
				sc.close();
				sc = null;
			}
		}

		System.out.println("end");

	}

}
