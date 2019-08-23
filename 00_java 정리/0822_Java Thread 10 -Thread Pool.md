# Java Thread 10 -Thread Pool

> 전체 소스코드 [Ex08_ThreadPoolBasic](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming - Thread/Ex08_ThreadPoolBasic.java) 참고

<br>

- 자바 7 버전 이상부터 Thread를 효율적으로 사용할 수 있도록 해주는 package 제공

  - ExecutorService

- Thread Pool을 이용해 Thread를 만들어 사용하고 실행이 끝난 후

  Pool에 반납하고 필요할 때 다시 받아서 재사용

- overhead를 줄여줌

<br>

## Thread pool 생성

1. Executors.newCachedThreadPool() 
   - 처음에 만들어진 Thread pool 안에는 Thread가 없음
   - 만약 필요하면 내부적으로 Thread를 생성
   - resource가 허용하는 한 만드는 Thread의 수는 제한이 없음
   - 60초 동안 Thread가 사용되지 않으면 자동적으로 삭제

<br>

2. newFixedThreadPool()
   - 인자로 들어온 int 값을 넘는 Thread를 생성할 수 없음
   - Thread가 사용되지 않더라도 만들어진 Thread 계속 유지

<br>

-  getPoolSize() : 현재 pool 안에 몇 개의 Thread가 존재하는지 확인

  ```java
  // executorService가 상위 객체이기 때문에 ThreadPoolExecutor로 downcasting
  int threadNum = ((ThreadPoolExecutor) executorService).getPoolSize();
  printMsg("현재 Thread Pool 안의 Thread 개수 : " + threadNum);
  ```

<br>

## Thread 생성

- Thread Pool을 이용해서 Thread 생성

``` JAVA
for (int i = 0; i < 10; i++) {
    // 람다 내에서 지역변수 쓸 수 없음 -> final 임시 변수 선언
    final int j = i;
    Runnable runnable = () -> {
        Thread.currentThread().setName("MyThread-" + j);
        String msg = Thread.currentThread().getName() + " Pool 안의 Thread 개수 : "
            + ((ThreadPoolExecutor) executorService).getPoolSize();
        printMsg(msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    // Thread pool을 이용해서 Thread를 생성
    executorService.execute(runnable);
}
```

<br>

## Thread Pool 종료

- shutdown() 

  Thread Pool 내의 Thread들에 interrupt 걸어서 종료시킨 후

  Thread Pool도 종료시킴

```java
stopBtn.setOnAction(t -> {
    executorService.shutdown();
});
```

<BR>

- 실행 결과

![image](https://user-images.githubusercontent.com/50972986/63494998-ed617a00-c4f9-11e9-953e-6c2d8c4a79fc.png)



