package day01;

import java.util.StringTokenizer;

public class Sample {

	public static void main(String[] args) {

		
		String s = "a/b/c/d/e/f/g";
		StringTokenizer st = new StringTokenizer(s,"/");
		
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		
		
		
	}

}
