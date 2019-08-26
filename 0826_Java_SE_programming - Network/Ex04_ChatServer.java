package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class SharedThread {
	// 공용 객체가 가지는 field
	private List<ChatRunnable> list = new ArrayList<ChatRunnable>();

	private String msg = "";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SharedThread() {
		super();
	}

	public List<ChatRunnable> getList() {
		return list;
	}

	public void setList(List<ChatRunnable> list) {
		this.list = list;
	}

	public void addThread(ChatRunnable t) {
		list.add(t);
	}

	public void sendMsg() {
		for (ChatRunnable r : list) {
			r.sendMsg(msg);
		}

	}
}

class ChatRunnable implements Runnable {
	// 가지고 있어야 하는 field

	// 클라이언트와 연결된 소켓
	Socket socket;
	// 소켓과 연결된 입력을 위한 inputStream, 출력을 위한 outputStream
	BufferedReader br;
	PrintWriter out;
	SharedThread st;
	

	public ChatRunnable(Socket socket, SharedThread st) {
		super();
		this.socket = socket;
		this.st = st;
		try {
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 클라이언트와 echo 처리 구현
		// 클라이언트가 문자열을 보내면
		// 해당 문자열을 받아서 다시 클라이언트에게 전달
		// 한 번 하고 종료하는 것이 아니라
		// 클라이언트가 "/EXIT" 라는 문자열을 보낼 때까지 지속
		String line = "";
		try {
			// line == null : client가 socket을 종료한 경우
			while ((line = br.readLine()) != null) {
//				if (Thread.currentThread().isInterrupted()) {
//					System.out.println(Thread.currentThread().getName() + " 종료");
//					break;
//				}
				if (line.equals("/EXIT/")) {
					// 가장 근접한 loop를 탈출
					break;
				} else {
					st.setMsg(line);
					st.sendMsg();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String msg) {
		out.println(msg);
		out.flush();
	}
}

public class Ex04_ChatServer extends Application {

	// 외부 method나 람다식에서 사용할 수 있도록 하기 위해
	// 필드로 선언
	static TextArea textarea;
	Button startBtn, stopBtn;
	SharedThread st = new SharedThread();

	// Thread pool 생성
	ExecutorService executorService = Executors.newCachedThreadPool();

	// client의 접속을 받아들이는 서버 소켓
	// port 충돌 방지를 위한 try/catch가 필요하므로
	// 필드에서 생성하지 않고 선언만
	ServerSocket server;

	private static void printMsg(String msg) {
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

		startBtn = new Button("Echo Server 시작 (Socket 시작)");
		startBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		startBtn.setOnAction(t -> {
			// Client의 접속을 기다림
			// 접속이 되면 thread를 하나 생성
			// thread를 시작해서 Client와 thread가 통신하도록 만듦
			// Server는 다시 Client의 접속을 기다림
			Runnable runnable = () -> {
				try {
					server = new ServerSocket(8001);
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
						// Thread 실행
						st.addThread(c);
						executorService.execute(c);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			executorService.execute(runnable);
		});

		stopBtn = new Button("Echo Server 종료 ");
		stopBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		stopBtn.setOnAction(t -> {
			try {
				server.close();
				executorService.shutdown();
				printMsg("Echo Server 종료");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		// FlowPane : 오른쪽으로 붙이는 layout
		// 안스의 Linear Layout
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// FlowPane에 버튼 올리기
		flowpane.getChildren().add(startBtn);
		flowpane.getChildren().add(stopBtn);
		root.setBottom(flowpane);

		// 실제 Window에 띄우기 위해 Scene 객체 필요
		Scene scene = new Scene(root);
		// Stage primaryStage : 실제 Window 객체
		primaryStage.setScene(scene);
		primaryStage.setTitle("Multi Client Echo Server");
		// Window에 띄우기
		primaryStage.show();

	}

	public static void main(String[] args) {
		// launch() : start()를 실행시키는 함수
		launch();
	}

}
