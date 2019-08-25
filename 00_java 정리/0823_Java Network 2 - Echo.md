# Java Network 2 - Echo

![1566536878343](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1566536878343.png)

<br>

#### Echo program 예제

- Client 프로그램으로부터 네트워크를 통해 문자열을 전달 받아서

  다시 Client에게 전달하는 echo Server 프로그램

<br>

### Server

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

### Client

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
				socket = new Socket("127.0.0.1", 5557);

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









- 현재는 단 한번만 echo가 동작하는데 클라이언트가 접속을 종료할때까지
  echo 작업이 지속적으로 동작하도록 프로그램을 수정
  서버는 클라이언트가 종료되면 같이 종료되도록 수정

- 채팅 프로그램

  -> server를 고쳐야함

  

client 

``` java
package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Ex02_Echoclient extends Application {

	// 외부 method나 람다식에서 사용할 수 있도록 하기 위해
	// 필드로 선언
	Socket socket;
	BufferedReader br;
	PrintWriter out;

	TextField tf;
	TextArea textarea;
	Button startBtn, stopBtn;

	private void printMsg(String msg) {
		textarea.appendText(msg + "\n");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// 화면 구성해서 window에 띄우는 코드
		// BorderPane : 화면 기본 layout을 설정 => 화면을 동/서/남/북/중앙 (5개의 영역)으로 분리
		BorderPane root = new BorderPane();
		// BorderPane의 크기를 설정 => 화면에 띄우는 window의 크기 설정
		root.setPrefSize(700, 500);

		// Component를 생성해서 BorderPane에 부착
		// 글 상자 생성
		textarea = new TextArea();
		// 화면의 가운데에 글 상자 위치
		// default size : 전체
		root.setCenter(textarea);

		startBtn = new Button("EchoServer 접속 (Socket 접속)");
		startBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		startBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			try {
				// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
				// Socket에 URL과 port 번호를 넘겨줘야 함
				socket = new Socket("127.0.0.1", 5557);

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
		});

		stopBtn = new Button("EchoServer 접속 종료 ");
		stopBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		stopBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			try {
				// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
				// Socket에 URL과 port 번호를 넘겨줘야 함
				out.close();
				br.close();
				socket.close();
				printMsg("Echo 서버 접속 종료");
			} catch (Exception e) {
				System.out.println(e);
			}
		});
		// FlowPane : 오른쪽으로 붙이는 layout
		// 안스의 Linear Layout
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// FlowPane에 버튼 올리기
		flowpane.getChildren().add(startBtn);
		flowpane.getChildren().add(stopBtn);
		flowpane.getChildren().add(tf);
		root.setBottom(flowpane);

		// 실제 Window에 띄우기 위해 Scene 객체 필요
		Scene scene = new Scene(root);
		// Stage primaryStage : 실제 Window 객체
		primaryStage.setScene(scene);
		primaryStage.setTitle("Echo 예제입니다");
		// Window에 띄우기
		primaryStage.show();

	}

	public static void main(String[] args) {
		// launch() : start()를 실행시키는 함수
		launch();
	}

}

```





server

``` java
package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Echo program 작성
 * 클라이언트 프로그램으로부터 문자열을 네트워크를 통해 전달 받아서
 * 다시 클라이언트에게 전달하는 echo 서버 프로그램
 * 
 * 현재는 단 한번만 echo가 동작하는데 클라이언트가 접속을 종료할때까지
 * echo 작업이 지속적으로 동작하도록 프로그램을 수정
 * 서버는 클라이언트가 종료되면 같이 종료되도록 수정
 * 
 * 지금 프로그램은 서버가 클라이언트 1명만 서비스 할 수 있음
 * 다수의 클라이언트를 서비스 하려면 어떻게 히야 할까?
 * 	Thread를 이용해서 이 문제를 해결
 * 
 * 
 */
public class Ex02_Echoserver {

	public static void main(String[] args) {

		ServerSocket server = null;
		Socket socket = null;

		try {
			System.out.println("서버 프로그램 기동 : 5557");
			server = new ServerSocket(5557);
			System.out.println("클라이언트 접속 대기");
			socket = server.accept();

			// Stream 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());

			// br로부터 데이터를 읽어서 out을 통해 다시 전달
			String msg = "";
			while (!((msg = br.readLine()).equals("/@EXIT"))) {
				out.println(msg);
				out.flush();
			}
			// 사용된 resource 해제
			out.close();
			br.close();
			socket.close();
			server.close();

			System.out.println("서버 프로그램 종료");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

```

