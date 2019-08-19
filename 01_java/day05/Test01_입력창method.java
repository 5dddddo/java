package day05;

import javax.swing.JOptionPane;

public class Test01_입력창method {
	public static void main(String[] args) {
		String data = JOptionPane.showInputDialog("이름을 입력하세요");
		System.out.println(data);

		String scoreStr = JOptionPane.showInputDialog("국어/영어/수학 점수를 입력해 주세요");
		int sum = 0;
		String[] score = scoreStr.split("/");
		for (int i = 0; i < score.length; i++)
			sum += Integer.parseInt(score[i]);

		System.out.println(sum);

	}
}
