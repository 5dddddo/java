package day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test04 {

	public static void main(String[] args) {

		String[] names = { "hong", "lee abcde", "ohabcde", "kim" };

		List<String> nn = Arrays.asList(names);
		nn.forEach(i -> System.out.println(i.toUpperCase()));
		System.out.println();

		nn.
		stream()
		.filter(i -> i.length() > 5)
		.forEach(i -> System.out.println(i));
		System.out.println();

		Stream
		.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
		.filter(i -> i % 2 == 0)
		.forEach(i -> System.out.println(i));
		
		
		

	}

}
