# Java Thread 6 - 동기화

> 전체 소스코드 [Ex04_ThreadSync](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming%20-%20Thread/Ex04_ThreadSync.java) 참고

<br>

- 하나의 자원을 여러 Thread가 사용하려 할 때

  한 시점에 하나의 Thread만이 사용할 수 있도록 하는 것

  어떤 Thread가 공유 자원을 점유하면 다른 Thread들은 block 상태

- 동기화 처리가가 되면 내부적으로 순차 처리가 이루어짐
- 공유되는 자원을 보호하는 역할을 함

- java는 언어적 차원에서 Thread를 지원하며

  keyword를 이용한 동기화를 지원

- Multi Thread 프로그램을 비교적 쉽게 작성 가능

<br>

### Monitor

- 공용 instance에 접근하기 위한 논리적 객체인 Monitor를 제공함

- 모든 공용 instance는 각각의 Monitor를 가짐

- 일단 Monitor를 한 Thread가 가지고 있으면

  다른 Thread는 해당 instance의 동기화 method (동기화 block)을 실행시킬 수 없음

- Monitor를 획득하려면 synchronized keyword 이용

<br>

#### 2개의 Thread를 파생시켜서 공용 객체를 이용하도록 하는 예제

 * Thread가 공용 객체를 동기화해서 사용하는 경우와 그렇지 않은 경우를 비교해보자
 * 먼저, 공용 객체를 만들기 위한 **SharedObject class**를 정의

``` java
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
```

- Thread 1이 assignNumber()를 수행하다가 sleep() 상태가 되면

  Thread 2가 assignNumber()를 호출하고 number를 200으로 바꿈

``` java
/* 출력결과
    현재 공용 객체의 number : 200
    현재 공용 객체의 number : 200 
*/
```

<BR>

### 동기화 Method

- Thread 2개가 실행되면서 assignNumber()를 번갈아 가면서 호출하게 됨

  number 값을 독점할 수 없음

  - synchronized keyword를 이용해서 sleep() 상태임에도 monitor를 획득하도록 보장함

- Thread에 의해서 사용이 되는 business method (logic)

  synchronized keyword로 동기화 method로 만들 수 있음

- 순차처리 됨

``` java
public synchronized void assignNumber(int number) {
    ...
}
```

```java
/* 출력결과
    현재 공용 객체의 number : 100
    현재 공용 객체의 number : 200 
*/
```

<br>

### 동기화 block

- 동기화는 method는 효율이 좋지 않음

  순차 처리되는 Critical 영역(임계 영역)이 넓어짐

- 동기화 block을 이용해서 동기화 처리가 필요한 부분만 동기화

- block을 벗어날 때까지 스스로 lock을 벗어나지 못함

``` java
synchronized (this){
    ...
}
```

<bR>

- Runnable interface를 구현한 **MyRunnable** class

  : Thread 생성자의 인자로 이용하기 위한 Class

``` java
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
```

<br>

- main() 

``` java
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
```

