# Java Thread 8 - Interrupt

> 전체 소스코드 [Ex06_ThreadInterrupt](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming%20-%20Thread/Ex06_ThreadInterrupt.java) 참고

<br>

#### run() method가 완료되기 전에 Thread를 강제 종료하는 예제

``` java
startBtn = new Button("Thread 시작 버튼");
startBtn.setPrefSize(250, 50);
startBtn.setOnAction(t -> {
    counterThread = new Thread(() -> {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                printMsg(i + " : " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            // 만약 interrupt()가 걸려있는 상태에서 sleep()과 같은
            // block 상태로 진입하면 exception을 내면서 catch문으로 이동
            System.out.println("Thread가 종료됨");
        }
    });
    counterThread.start();
});
```

<br>

- Thread에 interrupt 지시하는 버튼

``` java
stopBtn = new Button("Thread 중지 버튼");
stopBtn.setPrefSize(250, 50);
stopBtn.setOnAction(t -> {
    // interrupt method가 실행된다고 Thread가 바로 종료되지 않음
    // interrupt()가 호출된 thread는
    // sleep()과 같이 block 상태에 들어가야지
    // exception을 발생하며 interrupt 됨
    counterThread.interrupt();
});
```

