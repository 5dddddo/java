package util;

import java.util.Arrays;

public class Util {

	public static int [] selectSort(int[] n) {
		int []a = n.clone();
		for (int i = 0; i < a.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[min] > a[j])
					min = j;
			}
			if (i != min) {
				int tmp = a[min];
				a[min] = a[i];
				a[i] = tmp;
			}

		}
		System.out.println(Arrays.toString(a));
		return a;
	}
	
	
	
	
	
	
	
	
	
}
