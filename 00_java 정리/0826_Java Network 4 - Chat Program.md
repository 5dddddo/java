# Java Network 4 - Chat Server

### 전체 구조

![image](https://user-images.githubusercontent.com/50972986/63687281-1c9c2200-c83f-11e9-80d6-fb7740069163.png)

<br>

### 방 1개짜리 다중 채팅 구현 예제

- 서버가 클라이언트(Thread)의 존재를 알고 있어야 함
- 클라이언트와 통신하는 서버쪽 Thread는 클라이언트로부터 받은 msg를 broadcasting 할 수 있어야 함

<br>

1. 공유 객체를 생성하기 위한 class를 정의
   - 공유 객체는 Thread가 아닌 사용자 정의 Class

   - 이 공유 객체 안에는 ArrayList가 하나 존재하는데

     이 안에는 클라이언트와 통신을 담당하는 Thread들이 저장됨

   - 공유 객체 안에 method를 하나 정의함

     - sendMsg() : ArrayList 안의 각 Thread에게 클라이언트가 보내준 msg를 전달하는 역할

2.  클라이언트가 msg를 전달하면 Thread가 받아서 공유 객체의 method를 이용하여 모든 Thread에게 데이터를 전달하는 형식으로 데이터를 Broadcasting 시킴

<br>

## Server

> 전체 코드 [Ex04_ChatServer](https://github.com/5dddddo/java/blob/master/0826_Java_SE_programming%20-%20Network/Ex04_ChatServer.java) 참고

<br>

- **SharedThread** class : 공유 객체 

  ​									  sendMsg()를 통해 Client와 연결된 thread에 **Broadcasting**

``` java
class SharedThread {
   	// Client와의 통신을 담당하는 ChatRunnable 객체들을 저장하는 리스트
	private List<ChatRunnable> list = new ArrayList<ChatRunnable>();
	private String msg = "";

    // Getter & Setter

	public void addThread(ChatRunnable t) {
		list.add(t);
	}

	public void sendMsg() {
           // Broadcasting 코드
		for (ChatRunnable r : list) {
            // ChatRunnable의 멤버 함수 : sendMsg() 호출
			r.sendMsg(msg);
		}
	}
}
```

<br>

- ChatRunnable class : 실제로 Client와 직접적으로 데이터를 송수신하는 부분

``` java
class ChatRunnable implements Runnable {
	...
	@Override
	public void run() {
		... 
		String line = "";
		try {
			// line == null : client가 socket을 종료한 경우
			while ((line = br.readLine()) != null) {
				...
				if (line.equals("/EXIT/")) {
					// 가장 근접한 loop를 탈출
					break;
				} else {
                    // Broadcast 할 msg를 공유 객체의 msg에 담고
                    // 실행중인 client에 Broadcast 함
					st.setMsg(line);
					st.sendMsg();
				}
			}
	...
}
```

<br>

- **Ex04_ChatServer**

  - Thread Pool (ExecutorService)을 생성하여 Thread 관리

  - ChatRunnable의 생성자에 공유 객체를 넘겨주어 Broadcating 할 수 있도록 함

  - 현재 접속되어 있는 Client의  List에 새로 생성한 ChatRunnable 객체를 추가함

``` java
public class Ex04_ChatServer extends Application {
	...
    // 공유 객체 생성 
	SharedThread st = new SharedThread();
	// Thread pool 생성
	ExecutorService executorService = Executors.newCachedThreadPool();
	...

	@Override
	public void start(Stage primaryStage) throws Exception {
		...
		startBtn.setOnAction(t -> 
			Runnable runnable = () -> {
				try {
					server = new ServerSocket(PORT_NUMBER);
					printMsg("Echo 서버 기동");
					while (true) {
						printMsg("클라이언트 접속 대기");
						// block method
						// 버튼을 누르자마자 클라이언트가 접속될 때까지 window(JavaFX)가 멈춤
						// 별도의 Thread로 생성해 클라이언트 접속을 기다려야 함
						Socket s = server.accept();
						printMsg("클라이언트 접속 성공");
						// 클라이언트가 접속했으니 Thread를 만들고 시작함
						ChatRunnable c = new ChatRunnable(s, st);
						// Thread를 공유 객체에 추가
						st.addThread(c);
						executorService.execute(c);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			executorService.execute(runnable);
		});
	...
	}
}
```

<br>

## Client

> 전체 코드 [Ex04_ChatClient](https://github.com/5dddddo/java/blob/master/0826_Java_SE_programming%20-%20Network/Ex04_ChatClient.java) 참고

<br>

- Broadcast 된 문자열을 읽어오기 위해 별도의 Thread 생성

``` java
public class Ex04_ChatClient extends Application {
	... 
    // Thread pool 생성
	ExecutorService executorService = Executors.newCachedThreadPool();
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		...
		startBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출
			try {
				// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
				socket = new Socket("IP주소", PORT_NUMBER);
				// Stream 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				printMsg("Echo 서버 접속 성공");
			} catch (Exception e) {
				System.out.println(e);
			}
            // BufferedReader를 통해 문자열을 받아오는 별도의 Thread를 생성 
			Runnable runnable = () -> {
				while (true) {
					try {
						String result = br.readLine();
						if (result != null)
							printMsg(result);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			};
			executorService.execute(runnable);
		});
        ...
}
```

