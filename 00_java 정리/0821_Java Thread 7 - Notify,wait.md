# Java Thread 7 - Notify/wait

> 전체 소스코드 [Ex05_ThreadWaitNotify](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming/Ex05_ThreadWaitNotify.java) 참고

<br>

## Java Thread States

![image](https://user-images.githubusercontent.com/50972986/63483753-6059f900-c4d8-11e9-92f2-3453ec5e16e5.png)

<Br>

#### Thread가 번갈아 가면서 호출하도록 하는 예제

<br>

- 공용 객체를 생성하기 위한 **MyShared** class 정의

``` java
class MyShared {
	public void printNum() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " : " + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```

<br>

- Runnable interface를 구현한 **Ex05_Runnable** class

  Thread 생성자의 인자로 이용하기 위한 Class

``` java
class Ex05_Runnable implements Runnable {
	MyShared obj;

	Ex05_Runnable(MyShared obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		obj.printNum();
	}

}
```

<br>

- 어떤 Thread가 Scheduler에 의해 먼저 선택될 지 알 수 없음

``` java
출력 결과
Thread-0 : 0
Thread-1 : 0
Thread-1 : 1
Thread-0 : 1
Thread-0 : 2
Thread-1 : 2
Thread-1 : 3
Thread-0 : 3
Thread-1 : 4
Thread-0 : 4
...
```

<br>

- #### method 호출할 때 Thread가 번갈아 가면서 호출하도록 만들고 싶음

``` java
// synchronized method 
// 하나의 Thread가 monitor를 점유하게 하고
// 다른 Thread를 block 시킴
public synchronized void printNum() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " : " + i);
				// notify() : 현재 wait() 상태에 있는 Thread를 깨워서
				// 			  runnable 상태로 전환 시킴
                // wait() 된 Thread가 없으면 무시하고 지나감
				notify();
				
				// wait() : 자기가 가지고 있는 monitor 객체를 스스로 놓고
				// 			wait block으로 들어감
                //	        wait() 되는 동시에 synchronized에 의해
                //			block되어 있던 Thread가 즉시 Running 상태가 되며 실행됨
                // monitor 객체를 가지고 있어야하므로 synchronized와 set
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
```

``` java
출력 결과
Thread-0 : 0
Thread-1 : 0
Thread-0 : 1
Thread-1 : 1
Thread-0 : 2
Thread-1 : 2
Thread-0 : 3
Thread-1 : 3
...
```

- Thread1이 wait()에 의해 가지고 있던 monitor를 놓아서

  Synchronized에 의해 블락 중이던  Thread2가 monitor를 획득하고

  runnable 상태가 되는 것이 아니라 바로 running 상태가 되면서 printNum() 즉시 수행

<br>

- wait() 된 Thread는 반드시 notify()에 의해 깨워줘야 함

  