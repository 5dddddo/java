# Java Thread 11 - Callable

> 전체 소스코드 [Ex09_ThreadCallable](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming/Ex09_ThreadCallable.java) 참고

<br>

- Thread에 대한 return 값이 있는 경우 구현하는  interface
- return 값은 Future\<T> 객체로 받음

- Future 객체는 Pending 객체로 

  ``` java
  Future<String> future = executorService.submit(callable);
  ```

  위의 코드 실행 시 실제 결과값을 담은 Future 객체가 return 되는 것이 아니라

  결과를 담는 바구니 역할을 하는 빈 Future 객체가 바로 return 됨

  <br>

- 실제 결과값을 받아오기 위해서는 반드시 blocking method인 Future.get() 을 호출해야 함

- 호출되면 callable 객체의 call()을 실행하고

  Future 객체 안에 결과 값을 받아올 수 있을 때까지 block

  실제 결과 값이 나올 때까지 기다렸다가 (Block) 결과값을 받아옴

  <br>

- 결국 Multi thread임에도 불구하고 순차적으로 처리가 되는 문제가 발생

- 이를 해결할 방법

  > Ex10_ThreadCompleteService 참고

<br>

```java
startBtn.setOnAction(t -> {
    // 버튼에서 Action이 발생(클릭)했을 때 호출!
    for (int i = 0; i < 10; i++) {
        // 람다 내에서 지역변수 쓸 수 없음 -> final 임시 변수 선언
        final int j = i;

        // Callable 객체 생성
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.currentThread().setName("MyThread-" + j);
                String msg = Thread.currentThread().getName() + " Pool 안의 Thread 개수 : "
                    + ((ThreadPoolExecutor) executorService).getPoolSize();
                System.out.println(msg);
                // printMsg(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Thread.currentThread().getName() + " 종료";
            }
        };

        // Thread pool을 이용해서 Thread를 실행
        
        // Future객체는 Pending 객체
        // 실제 결과값을 담은 Future 객체가 return 되는 것이 아니라 결과를 담는 바구니만 만듦
        
        // Future.get()를 호출하면 그 때 callable을 수행한 후
        // 결과 값이 나올 때 까지 기다렸다가 결과가 나오면 받아옴
        Future<String> future = executorService.submit(callable);
        try {
            // get() method가 blocking method
            // callable 객체의 call()을 실행하고
            // Future 객체 안에 결과 값을 받아올 수 있을 때까지 block
            // => 순차 처리 됨
            String result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
});
```

<br>

- 실행 결과

```java
MyThread-0 Pool 안의 Thread 개수 : 1
MyThread-1 Pool 안의 Thread 개수 : 2
MyThread-2 Pool 안의 Thread 개수 : 2
MyThread-3 Pool 안의 Thread 개수 : 2
MyThread-4 Pool 안의 Thread 개수 : 2
MyThread-5 Pool 안의 Thread 개수 : 2
MyThread-6 Pool 안의 Thread 개수 : 2
MyThread-7 Pool 안의 Thread 개수 : 2
MyThread-8 Pool 안의 Thread 개수 : 2
MyThread-9 Pool 안의 Thread 개수 : 2
```

