package day10_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test02_List {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();

		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("aaa");

		Object[] o = new Object[list.size()];
		o = list.toArray();
		String[] s = new String[list.size()];
		s = list.toArray(s);
		// 복잡쓰 -> stream으로 통합해서 사용할 수 있음

		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);

		System.out.println();
		System.out.println(Arrays.toString(s));
		Arrays.sort(s);
		System.out.println(Arrays.toString(s));

	}

}
