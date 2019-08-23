package javaNetwork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Ex01_DateClient extends Application {

	TextArea textarea;
	Button btn;

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

		btn = new Button("DateServer 접속 (Socket 접속)");
		btn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		btn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			try {
				// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
				// Socket에 URL과 port 번호를 넘겨줘야 함
				Socket socket = new Socket("127.0.0.1", 5554);

				// 만약에 접속에 성공하면
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String msg = br.readLine();

				printMsg(msg);

				br.close();
				isr.close();
				socket.close();
				

			} catch (Exception e) {
				System.out.println(e);
			}
		});
		// FlowPane : 오른쪽으로 붙이는 layout
		// 안스의 Linear Layout
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// FlowPane에 버튼 올리기
		flowpane.getChildren().add(btn);
		root.setBottom(flowpane);

		// 실제 Window에 띄우기 위해 Scene 객체 필요
		Scene scene = new Scene(root);
		// Stage primaryStage : 실제 Window 객체
		primaryStage.setScene(scene);
		primaryStage.setTitle("Data Server 예제입니다");
		// Window에 띄우기
		primaryStage.show();

	}

	public static void main(String[] args) {
		// launch() : start()를 실행시키는 함수
		launch();
	}

}
