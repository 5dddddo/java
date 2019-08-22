package javaThread;
/*
 * 2개의 Thread를 파생시켜서 공용 객체를 이용하도록 하는 예제
 * 
 * Thread가 공용 객체를 동기화해서 사용하는 경우와
 * 그렇지 않은 경우를 비교해보자
 * 
 * 먼저, 공용 객체를 만들기 위한 class를 정의
 */

class SharedObject {
	// 공용 객체가 가지는 field
	private int number;

	// getter & setter : 외부 Thread에 의해서 사용 됨
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// Thread에 의해서 사용이 되는 business method (logic)
	// synchronized keyword로 동기화 할 수 있음
	// method 동기화는 효율이 좋지 않음
	// Critical 영역(임계 영역)이 넓기 때문
	// 동기화 block을 이용해서 동기화 처리가 필요한 부분만 동기화
	public void assignNumber(int number) {
		this.number = number;
		try {
			Thread.sleep(3000);
			System.out.println("현재 공용 객체의 number : " + this.number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// Runnable interface를 구현한 class
// Thread 생성자의 인자로 이용하기 위한 Class
class MyRunnable implements Runnable {
	SharedObject shared;
	int input;

	MyRunnable(SharedObject shared, int input) {
		this.shared = shared;
		this.input = input;
	}

	@Override
	public void run() {
		shared.assignNumber(input);
	}
}

public class Ex04_ThreadSync {

	public static void main(String[] args) {
		// 공용 객체 생성
		SharedObject so = new SharedObject();

		// Thread 2개 생성
		// 공용 객체를 가지고 있는 Runnable 객체를 이용해 Thread 생성
		Thread t1 = new Thread(new MyRunnable(so, 100));
		Thread t2 = new Thread(new MyRunnable(so, 200));

		// Thread 실행 ( runnable 상태로 전환 )
		t1.start();
		t2.start();

	}

}
