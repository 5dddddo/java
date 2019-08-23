package javaIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Ex03_ObjectStream {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("1", "홍길동");
		map.put("2", "오길동");
		map.put("3", "김길동");
		map.put("4", "이길동");

		// 객체가 저장될 파일에 대한 File 객체 생성
		// 해당 파일의 존재 여부는 상관 없음
		File file = new File("asset/objectStream.txt");
		// 객체가 저장될 File의 OutputStream부터 열기
		try {
			// 파일이 존재하면 해당 파일의 통로만 열고
			// 파일이 없으면 파일을 생성하고 통로를 열어줌
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(map);
			
			// 1. 진짜 객체를 저장할 수 없기 때문에
			// 내보내려고 하는 map 객체를 마샬링 작업을 통해서 형태를 변환
			// 마샬링 : 객체를 문자열로 표현하기 위해서 하는 변화 작업

			// 저장하는 코드는 close를 제대로 해줘야 함
			// 역순으로 close
			oos.close();
			fos.close();

			// 객체가 저장된 파일을 open해서 해당 객체를 프로그램으로 읽어들임
			// 파일에서 데이터를 읽기 위해 InputStream이 필요
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			// 객체 읽어들임
			Object obj = ois.readObject();
			
			// 문자열로 표현된 객체를 읽어들여서 원래 객체로 복원
			
			HashMap<String, String> result = null;

			// generic type은 상관하지 않고 Map의 객체인지 확인
			if (obj instanceof Map<?, ?>) {
				result = (HashMap<String, String>) obj;
			}
			// key값이 3인 요소 출력
			System.out.println(result.get("3"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
