package day03;

public class Test08_StringBuilder {

	public static void main(String[] args) {
		String msg = "hello java test";
		String msg2 = "";
		StringBuilder sb = new StringBuilder();

		System.out.println(msg);
		for (int i = 0; i < msg.length(); i++) {
			// msg2+=(char)(msg.charAt(i)-32); //성능 저하 , 쓰지 말 것
			sb.append((char) (msg.charAt(i) - 32));
		}
		msg2 = sb.toString();
		System.out.println(msg);
		System.out.println(msg2);

		/*
		 * String name = "오은애"; System.out.println(name);
		 * System.out.println(name.length()); System.out.println("**" +
		 * name.charAt(name.length() - 1)); System.out.println(name.substring(0, 2));
		 */

	}

}
