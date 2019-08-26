# Java Network 2 - Echo

![1566536878343](https://user-images.githubusercontent.com/50972986/63658385-1711ee00-c7e5-11e9-98b1-351eee07a654.png)

<br>

#### Echo program 예제

- Client 프로그램으로부터 네트워크를 통해 문자열을 전달 받아서

  다시 Client에게 전달하는 echo Server 프로그램

<br>

## Server

> 전체 소스코드 [Ex02_Echoserver](https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20Network/Ex02_Echoserver.java) 참고

<br>

- Client가 보낸 문자열을 BufferedReader로 받고

  PrintWriter를 이용해 다시 Client에 보냄 

- 한 번의 echo 동작을 수행하고 서버 종료

``` java
ServerSocket server = null;
Socket socket = null;

try {
    // Port : 5557
    server = new ServerSocket(5557);
    System.out.println("서버 프로그램 기동 : 5557");
    socket = server.accept();
	System.out.println("클라이언트 접속 대기");
    
    // Stream 생성
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    PrintWriter out = new PrintWriter(socket.getOutputStream());

    // br로부터 데이터를 읽어서
    String msg = br.readLine();
	// out을 통해 다시 전달
    out.println(msg);
    out.flush();

    // 사용된 resource 해제
    out.close();
    br.close();
    socket.close();
    server.close();
    System.out.println("서버 프로그램 종료");
} catch (IOException e) {
    e.printStackTrace();
}
```

<br>

## Client

> 전체 소스코드 [Ex02_Echoclient](https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20Network/Ex02_Echoclient.java) 참고

<br>

``` java
public class Ex02_Echoclient extends Application {
	// 외부 method나 람다식에서 사용할 수 있도록 하기 위해
	// 필드로 선언
	Socket socket;
	BufferedReader br;
	PrintWriter out;
    
    ...
        
	@Override
	public void start(Stage primaryStage) throws Exception {
	
        ...
            
        btn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			try {
				// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
				// Socket에 URL과 port 번호를 넘겨줘야 함
				socket = new Socket("127.0.0.1", PORT_NUMBER);

				// Stream 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());

				printMsg("Echo 서버 접속 성공");
			} catch (Exception e) {
				System.out.println(e);
			}
		});

		tf = new TextField();
		tf.setPrefSize(200, 40);
		tf.setOnAction(t -> {
			// 입력상자 (Textfield)에서 enter key가 입력되면 호출
			String msg = tf.getText();

			// server로 문자열 전송
			out.println(msg);
			out.flush();

			// server가 전송한 문자열 받기
			try {
				String result = br.readLine();
				printMsg(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
            out.close();
            br.close();
            socket.close();
            printMsg("Echo 서버 접속 종료");
		});
        
		...
	}
}
```

<br>

- 현재는 단 한번만 echo가 동작하는데 클라이언트가 접속을 종료할 때까지
  
  echo 작업이 지속적으로 동작하도록 프로그램을 수정

  서버는 클라이언트가 종료되면 같이 종료되도록 수정
  
  
