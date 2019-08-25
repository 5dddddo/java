# Java Thread 9 - Daemon

> 전체 소스 코드 [Ex07_ThreadDaemon](https://github.com/5dddddo/java/blob/master/0822_Java_SE_programming%20-%20Thread/Ex07_ThreadDaemon.java) 참고

<br>

- 자식 Thread가 된다고 생각하면 됨

- 부모 Thread( JavaFX Thread )가 중지되면 자동적으로 자식 Thread도 죽음

``` java
btn.setOnAction(t -> {
    // 버튼에서 Action이 발생(클릭)했을 때 호출!
    // Thread 생성 ( for문으로 1초마다 sleep했다가 깼다가 10번 반복 )
    // 이 Thread가 dead 상태로 가기 위해서는 10초가 걸림
    Thread thread = new Thread(() -> {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e); 
        }
    });
    // setDaemon() : 해당 Thread를 daemon thread로 설정
    // 자식 Thread가 된다고 생각하면 됨
    // 부모 Thread(JavaFX Thread)가 중지되면 자동적으로 자식 Thread도 죽음
    thread.setDaemon(true);
    thread.start();
});
```

<BR>

- JavaFX thread ( Window 창 ) 를 종료하면 Daemon thread로 지정한 thread도

  종료되기 때문에 프로세스가 종료 됨
