package day04;

public class Test04 {

	public static void main(String[] args) {
		String[] strData = { "Java Programming", "JDBC", "Oracle10g", "JSP/Servlet" };

		for (int i = 0; i < strData.length; i++) {
			for (int j = strData[i].length() - 1; j >= 0; j--) {
				System.out.print(strData[i].charAt(j));
			}
			System.out.println();
		}
		
		
		for (String str: strData) {
			for (int j = str.length() - 1; j >= 0; j--) {
				System.out.print(str.charAt(j));
			}
			System.out.println();
		}
		
	}

}
