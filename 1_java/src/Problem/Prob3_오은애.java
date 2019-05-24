package Problem;

public class Prob3_오은애 {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String sourceString = "everyday we have is one more than we deserve";
		String encodedString = "";

		// 프로그램을 구현부 시작.
		// 참고 : 문자 'a'의 정수값은 97이며, 'z'는 122입니다.
		for (int i = 0; i < sourceString.length(); i++) {
			if (sourceString.charAt(i) == ' ') {
				sb.append(sourceString.charAt(i));
				continue;
			}
			if ((int) (sourceString.charAt(i) + 3) >= 123)
				sb.append((char) ((sourceString.charAt(i) + 3) % 123 + 'a'));
			else
				sb.append((char) (sourceString.charAt(i) + 3));
			//c = c>='a' && c<='z'?(char)('a'+((c-'a')+3)%26):c;
		}
		encodedString = sb.toString();
		// 프로그램 구현부 끝.

		System.out.println("암호화할 문자열 : " + sourceString);
		System.out.println("암호화된 문자열 : " + encodedString);
	}

}
