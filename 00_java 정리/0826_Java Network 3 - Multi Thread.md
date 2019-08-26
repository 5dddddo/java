# Java Network 3 - Multi Thread

#### Echo program 예제

- 클라이언트가 접속을 종료할 때까지

  echo 작업이 지속적으로 동작하도록 Echo 프로그램을 수정

- Multi Thread

<br>

## Server

> 전체 소스코드 Ex03_EchoServerMultiClient 참고

<br>

- EchoRunnable class
  - Client와 직접적으로 통신하는 Thread의 runnable 구현 class

``` java
class EchoRunnable implements Runnable {
	// 가지고 있어야 하는 field

	// 클라이언트와 연결된 소켓
	Socket socket;
	// 소켓과 연결된 입력을 위한 inputStream
    //			   출력을 위한 outputStream
	BufferedReader br;
	PrintWriter out;

	public EchoRunnable(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream());
        } ...
	}

	@Override
	public void run() {
		// 클라이언트와 echo 처리 구현
		// 클라이언트가 문자열을 보내면
		// 해당 문자열을 받아서 다시 클라이언트에게 전달
		String line = "";
		try {
			// line == null : client가 socket을 종료한 경우
            // 한 번 하고 종료하는 것이 아니라
			// 클라이언트가 "/EXIT" 라는 문자열을 보낼 때까지 지속
			while ((line = br.readLine()) != null) {
				if (line.equals("/EXIT/")) {
					// 가장 근접한 loop를 탈출
					break;
				} else {
                    // Client에 전송
					out.println(line);
					out.flush();
				}
			}
        } ...
	}
}
```

<br>

- JavaFX를 사용하기 위해 Application을 상속받은 Ex03_EchoServerMultiClient class

  

  - **문제** : Start 버튼을 눌렀을 때 server.accept()을 통해 Client의 접속을 기다리면

    ​		  Block method이기 때문에 클라이언트가 접속될 때까지 window(JavaFX)가 멈춤

    **해결** :  별도의 Thread를 생성하여 client의 접속을 기다려야 함

<br>

``` java
public class Ex03_EchoServerMultiClient extends Application {
	...
	// Thread pool 생성
	ExecutorService executorService = Executors.newCachedThreadPool();
	...
	@Override
	public void start(Stage primaryStage) throws Exception {
        ...
            
		startBtn = new Button("Echo Server 시작 (Socket 시작)");
		startBtn.setPrefSize(250, 50);
		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		startBtn.setOnAction(t -> {
			// Client의 접속을 기다림
			// 접속이 되면 thread를 하나 생성
			// thread를 시작해서 Client와 thread가 통신하도록 만듦
			// Server는 다시 Client의 접속을 기다림
			try {
				server = new ServerSocket(7777);
				printMsg("Echo 서버 기동");
				while (true) {
					printMsg("클라이언트 접속 대기");
                    
                    // 문제 ---------------------------------------------------------
					// block method
					// 버튼을 누르자마자 클라이언트가 접속될 때까지 window(JavaFX)가 멈춤
					Socket s = server.accept();
                    // -------------------------------------------------------------
					
                    printMsg("클라이언트 접속 성공");
					// 클라이언트가 접속했으니 Thread를 만들고 시작함
					EchoRunnable r = new EchoRunnable(s);
					// Thread 실행
					executorService.execute(r);
                }
			});
        }...
```

<br>

- 문제 해결 후 Ex03_EchoServerMultiClient class

```java
Runnable runnable = () -> {
				try {
					server = new ServerSocket(7777);
					printMsg("Echo 서버 기동");
					while (true) {

						printMsg("클라이언트 접속 대기");
						// block method
						// 버튼을 누르자마자 클라이언트가 접속될 때까지 window(JavaFX)가 멈춤
						Socket s = server.accept();
						printMsg("클라이언트 접속 성공");
						// 클라이언트가 접속했으니 Thread를 만들고 시작함
						EchoRunnable r = new EchoRunnable(s);
						// Thread 실행
						executorService.execute(r);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
// 별도의 Thread로 빼낸 부분을 실행시킴
executorService.execute(runnable);
```

<br>

## Client

> 전체 코드 Ex03_EchoClientMultiClient 참고

<br>

- client 부분은 [0823_Java Network 2 - Echo.md](https://github.com/5dddddo/java/blob/master/00_java%20%EC%A0%95%EB%A6%AC/0823_Java%20Network%202%20-%20Echo.md)와 동일

