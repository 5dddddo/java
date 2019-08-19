package util;

public class MyStack {

	private int[] data;
	private int size;

	public MyStack() {
		super();
		data = new int[10];
	}

	public MyStack(int s) {
		super();
		if (s > 0)
			data = new int[s];
		else
			data = new int[10];

		// data = new int[s>0?size:10];
	}

	public MyStack(int[] data) {
		super();
		this.data = data;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public void push(int tmp) {
		if (!isFull()) {
			data[size++] = tmp;

		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == data.length;
	}

	public int top() {
		if (!isEmpty())
			return data[size - 1];
		else
			return -1;
	}

	public int pop() {
		if (!isEmpty()) {
			int value = data[size - 1];
			size--;
			return value;
		} else
			return -1;
	}

}
