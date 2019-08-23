package javaThread;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Ex06_ThreadInterrupt extends Application {

	private void printMsg(String msg) {
		// textarea에 문자열 출력하는 method
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}

	TextArea textarea;
	// Thread 시작, 종료 버튼
	Button startBtn, stopBtn;
	Thread counterThread;

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

		startBtn = new Button("Thread 시작 버튼");
		startBtn.setPrefSize(250, 50);
		startBtn.setOnAction(t -> {
			counterThread = new Thread(() -> {
				try {
					for (int i = 0; i < 5; i++) {
						Thread.sleep(1000);
						printMsg(i + " : " + Thread.currentThread().getName());
					}
				} catch (Exception e) {
					// 만약 interrupt()가 걸려있는 상태에서 block 상태로 진입하면
					// exception을 내면서 catch문으로 이동
					printMsg("Thread가 종료됨");
				}
			});
			counterThread.start();

		});

		stopBtn = new Button("Thread 중지 버튼");
		stopBtn.setPrefSize(250, 50);
		stopBtn.setOnAction(t -> {
			// interrupt method가 실행된다고 Thread가 바로 종료되지 않음
			// interrupt()가 호출된 thread는
			// sleep()과 같이 block 상태에 들어가야지 interrupt 됨
			counterThread.interrupt();

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
		primaryStage.setTitle("Thread Interrupt 예제입니다");
		// Window에 띄우기
		primaryStage.show();

	}

	public static void main(String[] args) {
		// launch() : start()를 실행시키는 함수
		launch();
	}

}
