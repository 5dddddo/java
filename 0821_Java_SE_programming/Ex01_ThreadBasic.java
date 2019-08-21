package javaThread;

/*
 * 모든 java Application은 Main Thread가 main() method를 호출해서 실행됨
 * 
 * 프로그램은 main() method가 종료될 때 종료되는 것이 아니라
 * 프로그램 내에서 파생된 모든 Thread가 종료될 때 종료됨
 * 
 * Thread의 생성
 * 1. Thread class를 직접 상속받아서 사용자 class를 정의하고 객체를 생성해서 사용
 * 2. Runnable interface를 구현한 class를 정의하고 객체를 생성해서
 * 	  Thread 생성자의 인자로 넣어서 Thread 생성
 * 
 * 현재 사용되는 Thread의 이름을 출력해보자
 * 
 * 실제 Thread의 생성 (new) -> start()
 * Thread를 실행하는 것이 아니라 runnable 상태로 전환
 * -> JVM 안예 잇는 Thread schedule에 의해
 *	    하나의 Thread가 선택되어 running 상태로 전환 ( CPU Core가 붙음 : 선택된 Thread 코드 수행)
 * -> 어느 시점이 되면 Thread scheduler에 의해서 runnable 상태로 전환 ( Starvation 방지 )
 * -> runnable인 Thread(직전에 선택되었던 Thread까지 포함) 중 하나를 선택해서 running 상태로 전환
 * 
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Ex01_ThreadBasic extends Application {

	TextArea textarea;
	Button btn;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// start()를 호출하는 thread 출력
		System.out.println(Thread.currentThread().getName());
		// 출력 결과 : JavaFX Application Thread
		// JavaFX는 내부적으로 화면을 제어하는 Thread를 생성해서 사용함

		// 화면을 구성해서 window 띄우는 코드
		// 화면의 기본 layout을 설정 => 화면을 동서남북중앙(5개의 영역)으로 분리
		BorderPane root = new BorderPane();
		// BorderPane의 크기를 설정 => 화면에 띄우는 window의 크기 설정
		root.setPrefSize(700, 500);

		// Component 생성해서 BorderPane에 부착
		textarea = new TextArea();
		root.setCenter(textarea);

		btn = new Button("버튼 클릭 !!");
		btn.setPrefSize(250, 50);
		btn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			// 버튼을 클릭하면 Thread 생성
			// Runnable 객체의 run을 override하는 실행 코드를
			// 람다식으로 표현
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName());
				// 화면 제어는 새로 생성한 Thread가 처리하지 않고
				// JavaFX Application Thread가 담당하도록 함
				// textarea에 출력하기 위해서
				// 새 Thread를 파생시켜 JavaFX Application Thread한테 부탁

				Platform.runLater(() -> {
					textarea.appendText("소리없는 아우성\n");
				});
			}).start();
		});
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// flowpane에 버튼을 올려요!
		flowpane.getChildren().add(btn);
		root.setBottom(flowpane);
		
		// Scene 객체가 필요해요.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Thread 예제입니다.");
		primaryStage.show();
	}

	public static void main(String[] args) {
		// 현재 main method를 호출한 Thread의 이름을 출력
		// Thread.currentThread() : 지금 이 코드를 수행하는 thread가 어떤 thread인지 가져옴
		System.out.println(Thread.currentThread().getName());

		// launch() : start()를 실행시키는 함수
		launch();
	}

}
