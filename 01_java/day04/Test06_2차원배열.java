package day04;

import java.util.Arrays;

public class Test06_2차원배열 {

	public static void main(String[] args) {

		int[][] a = { { 3, 2, 1 }, { 3, 8, 2, 1 }, { 4, 1, 4, 5, 7 } };

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}

		for (int i = 0; i < a.length; i++)
			System.out.println(Arrays.toString(a[i]));

	}
}
