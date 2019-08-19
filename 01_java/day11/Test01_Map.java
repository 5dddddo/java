package day11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test01_Map {

	public static void main(String[] args) {

		Map<String, String> m = new HashMap<String, String>();
		m.put("java1", "1234");
		m.put("java2", "9874");
		m.put("java3", "1457");
		m.put("java4", "7521");
		m.put("java5", "2568");
		m.put("java6", "9571");
		m.put("java6", "888");
		
		m.forEach((i,j)->System.out.println(i +" "+ j));
		
		
		
//		System.out.println(m); // toString overriding
//		Set<String> key = m.keySet();
//
//		Iterator<String> it = key.iterator();
//		while (it.hasNext()) {
//			String keyName = (String) it.next();
//			System.out.println(keyName + " " + m.get(keyName));
//		}

//		Scanner sc = new Scanner(System.in);
//		while (true) {
//			System.out.println("ID와 PW를 입력하세요");
//			System.out.println("ID : _");
//			String id = sc.nextLine().trim();
//			System.out.println("PW : _");
//			String pw = sc.nextLine().trim();
//
//			if (!m.containsKey(id)) {
//				System.out.println("입력하신 ID는 존재하지 않습니다.");
//				continue;
//			} else {
//				if (!m.get(id).equals(pw))
//					System.out.println("PW가 일치하지 않습니다.");
//				else {
//					System.out.println("로그인 성공");
//				}
//
//			}
//
//		}

	}
}