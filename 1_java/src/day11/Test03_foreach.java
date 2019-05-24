package day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Test03_foreach {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<Integer>();
		list.add(98);
		list.add(78);
		list.add(25);
		list.add(31);
		list.add(86);
		list.add(43);
		for (int data : list) {
			System.out.print(data + " ");
		}

		System.out.println();
		System.out.println("-------------------------");

		// Collection.forEach = Collection의 모든 원소를 순회하면서 자동으로 수행되는 call-back method로서
		// Consumer 객체의 accept method 자동 수행 됨.
		// Consumer 익명 객체 생성
		list.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.print(t + " ");
			}
		});
		System.out.println();
		list.forEach((Integer t) -> System.out.print(t + " "));
		
		System.out.println();
		list.forEach(t -> System.out.print(t + " "));
	
		System.out.println();
		list.removeIf(i -> i % 2 == 0); // 조건에 만족하는 모든 원소 삭제
		list.forEach(t -> System.out.print(t + " "));

		System.out.println();
		list.replaceAll(i -> i * 10);
		list.forEach(t -> System.out.print(t + " "));

	}

}
