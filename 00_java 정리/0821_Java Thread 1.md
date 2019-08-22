# Java Thread 1 (08/21)

1. Multi Processing 

   - Process : OS에서 실행중인 하나의 앱

   - 시간적으로 동시간에 다른 일을 같이 처리할 수 있는 경우

   - CPU 냬의 Core가 2개 이상일 경우에만 의미를 가지는 용어

2. Multi Tasking

   - 여러 개의 프로그램을 시분할 방식을 이용해서

     하나의 Core가 마치 시간적으로 동시에 수행되는 것처럼 보이는 것

   - 실제로는 시간적으로 하나의 일만 수행 됨

   - Core의 개수와는 상관 없음

3. Multi Threading

   - Thread : 프로세스 내에서 각 일을 처리하는 module

     ​			   새로운 흐름

   - 하나의 프로그램은 내부적으로 여러 개의 작업(Thread)이

     동시에 진행 되어야 할 경우가 있음

   - Core의 개수와는 상관 없음
   
   - Concurrency (병행성)         vs       Parallelism (병렬성)
   
     프로그램적 특성을 지칭				기계적 특성을 지칭
   
   - Thread는 Concurrency을 지향

<br>

# Java Thread

- 모든 java Application은 Main Thread가 main() method를 호출해서 실행됨

- 프로세스의 종료는 main() method가 종료될 때 종료되는 것이 아니라

  프로세스 내에서 파생된 모든 Thread가 종료될 때 종료됨

- Thread의 생성 방법

  1. Thread class를 직접 상속받아서 사용자 class를 정의하고 객체를 생성해서 사용

  2. Runnable interface를 구현한 class를 정의하고 객체를 생성해서

     Thread 생성자의 인자로 넣어서 Thread 생성

<br>

Java Thread States

![1566377459460](https://user-images.githubusercontent.com/50972986/63418638-67ccc400-c43e-11e9-8d2d-687d08a7b2f9.png)

1. 실제 Thread의 생성 ( new Thread() ) -> start() method 실행

2. Thread가 실행되는 것이 아니라 runnable 상태로 전환

![1566377533106](https://user-images.githubusercontent.com/50972986/63418677-774c0d00-c43e-11e9-9db1-aa63ce4584ef.png)

3. JVM 안에 있는 Thread schedule에 의해

   하나의 Thread가 선택되어 Running 상태로 전환 

   ( CPU Core가 붙음 / CPU 점유 : 선택된 Thread 코드 수행 )

![1566377627129](https://user-images.githubusercontent.com/50972986/63418739-8fbc2780-c43e-11e9-93a5-acde40bb81f7.png)

4. 어느 시점이 되면 Thread scheduler에 의해서 runnable 상태로 전환

   ( Starvation 방지 )

   - Schedulering
     - 일반적으로 우선순위에 따라 선택 됨
     - Round Robin 방법 : 순차적 처리

5. runnable인 Thread (직전에 선택되었던 Thread까지 포함) 중

   하나를 선택해서 running 상태로 전환

6. 위의 3~5 과정 반복

7. Dead 상태 : run()이 완료되면 Dead 상태가 되어

   ​					 Thread가 완전히 종료되어 다시 수행될 수 없고

   ​					 Garbage colletor에 의해 수거됨

   

----------------------------

## 전체 Java Thread States

> [0821_Java Thread 7 - Notify,wait.md](https://github.com/5dddddo/java/blob/master/00_java%20%EC%A0%95%EB%A6%AC/0821_Java%20Thread%207%20-%20Notify%2Cwait.md) 참고

<br>

![image](https://user-images.githubusercontent.com/50972986/63483753-6059f900-c4d8-11e9-92f2-3453ec5e16e5.png)