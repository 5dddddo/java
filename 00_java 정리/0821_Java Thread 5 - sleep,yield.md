# Java Thread 5 - sleep,yield

> 전체 소스코드 Ex03_ThreadSleep 참고

<br>

## Thread.sleep()

![image](https://user-images.githubusercontent.com/50972986/63477933-870d3500-c4c2-11e9-8324-7c8433c33ee1.png)

<br>

- Running 상태 -> sleep 상태

  - Thread.sleep() 되어 일정 시간동안 Block이 된 후에 

    Running 상태로 돌아가는 것이 아니라

    다시 runnable 상태로 돌아가기 때문에 바로 실행 되지 않음

  - Thread.sleep(s)은 **최소 s초의 block 상태를 보장**
  - 다시 실행하려면 Thread Scheduler의 선택을 받아야 함

<bR>

- Dead 상태 : run()이 완료되어 Dead 상태가 되면

  ​					 Thread가 완전히 종료되어 다시 수행될 수 없고

  ​					 Garbage Collector에 의해 수거됨

<br>



``` java
// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
btn.setOnAction(t -> {
    // 버튼에서 Action이 발생(클릭)했을 때 호출

    IntStream intStream = IntStream.rangeClosed(1, 5);
    // consumer 사용
    intStream.forEach(value -> {
        // 1~5까지 5번 반복
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                // Thread block
                try {
                    Thread.sleep(3000);
                    printMsg(i + " : " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("ThreadNumber-" + value);
        thread.start();

    });
});
```

<br>

## Thread.yield() 

- 다른 Thread에게 Running 상태를 양보
- Runnable 상태로 돌아감