package Problem;

import java.util.Arrays;

public class Prob4_selectionSort {

	public static void main(String[] args) {
		int[] num = new int[6];
		for (int i = 0; i < num.length; i++) {
			num[i] = (int) (Math.random() * 45) + 1;
			for (int j = i - 1; j >= 0; j--) {
				if (num[j] == num[i]) {
					i--;
					break;
				}
			}

		}
		System.out.println(Arrays.toString(num));

		for (int i = 0; i < num.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < num.length; j++) {
				if (num[min] > num[j])
					min = j;
			}
			if (i != min) {
				int tmp = num[min];
				num[min] = num[i];
				num[i] = tmp;
			}

		}
		System.out.println(Arrays.toString(num));
	}

}