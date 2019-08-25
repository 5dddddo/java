

# Java Thread 12 - ExecutorCompletionService

> 전체 소스코드 [Ex10_ThreadCompleteService](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming%20-%20Thread/Ex10_ThreadCompleteService.java) 참고

<br>

- Callable interface를 이용할 때 

  return 값을 담는 Future 객체의 block method get()에 의해 순차처리가 되는 문제점을 해결해보자!

<br>

#### 1~100까지 숫자의 합을 구하는 예제

- 1~10까지 1개의 Thread가 합을 계산해서 결과 return

  11~20까지 1개의 Thread가 합을 계산해서 결과 return
  ...

  91~100까지 1개의 Thread가 합을 계산해서 결과 return

- Thread Pool을 이용해야 하고 Callable을 이용해서 리턴값을 받아야 함

- 10개의 Thread로부터 각각을 수행하고

  10개의 결과값을 받아 취합하는 별도의 Thread를 하나 만들어 최종 합을 구함

<br>

#### Completion Pool 생성

``` java
public class Ex10_ThreadCompleteService extends Application {
    // ExecutorService : Thread Pool
	ExecutorService executorService;
	// ExecutorCompletionService : 기존 Thread Pool에서 확장된 기능이 있는 Completion Pool 생성
	ExecutorCompletionService<Integer> executorCompletionService;
	...
        
    @Override
	public void start(Stage primaryStage) throws Exception {
        ...
    	executorService = Executors.newCachedThreadPool();

        // generic type : Thread를 실행하고 return 받는 결과값의 type
        executorCompletionService = 
            new ExecutorCompletionService<Integer>(executorService);
		...
}
```

<br>

#### 10개씩 더하는 Callable 객체 생성

``` java
for (int i = 1; i < 101; i += 10) {
    // 람다 내에서 지역변수 쓸 수 없음 -> final 임시 변수 선언
    final int j = i;

    // Callable 객체 생성
    Callable<Integer> callable = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            // 10개씩 합 구하기
            IntStream intStream = IntStream.rangeClosed(j, j + 9);
            int sum = intStream.sum();
            return sum;
        }
    };
    // 확장된 Thread pool을 이용해서 10개의 Thread를 실행
    executorCompletionService.submit(callable);
}
```

#### <br>10개의 Thread에서 리턴된 결과값을 취합하는 Runnable 객체 생성

- 결과값을 return 받지 않고 출력하고 종료하는 Thread를 만들기 위해

  Runnable interface 구현

- take() 함수가 기다리고 있다가

  executorCompletionService.submit(callable)을 통해 수행되는 Thread 중

  먼저 끝난 Thread가 발생하면 그 결과값을 받아옴

- 순차적으로 처리되지 않고 끝난 Thread 먼저 처리함

``` java
Runnable runnable = new Runnable() {
    public void run() {
        for (int i = 0; i < 10; i++) {
            // executorCompletionService가 공용으로 사용되고 있기 때문에
            // take()가 기다리고 있다가
            // submit()을 통해 수행되는 Thread 중 끝난 Thread가 발생하면
            // 그 결과값을 받아옴
            try {
                Future<Integer> future = executorCompletionService.take();
                // 종료된 Thread의 결과값 누적
                total += future.get();
            } catch (InterruptedException e) {
                // executorCompletionService.take()에서 발생하는 Exception 처리
                e.printStackTrace();
            } catch (ExecutionException e) {
                // future.get()에서 발생하는 Exception 처리
                e.printStackTrace();
            }
        }
        printMsg("최종 결과값은 " + total);
    }
};
executorService.execute(runnable);
```

<br>

- 실행 결과

![image](https://user-images.githubusercontent.com/50972986/63498736-78923e00-c501-11e9-9208-5bdbb83397fb.png)

