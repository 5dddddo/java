package day09_Exception;

import javax.swing.JOptionPane;

public class Test01_Exception {

	public static void main(String[] args) {

		System.out.println("start");
		String num = JOptionPane.showInputDialog("숫자를 입력하세요");

		try {
			System.out.println(1);
			System.out.println(4. / Integer.parseInt(num)); // Run-time exception
			System.out.println(2);
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
			System.out.println("0으로 나누면 X");
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.out.println("숫자만 입력");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			System.out.println(9);
		}

		System.out.println("end");
	}

}
