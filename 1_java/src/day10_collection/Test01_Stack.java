package day10_collection;

import java.util.Stack;

public class Test01_Stack {

	public static void main(String[] args) {

		Stack<String> stack = new Stack<String>();
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.empty());

	}

}
