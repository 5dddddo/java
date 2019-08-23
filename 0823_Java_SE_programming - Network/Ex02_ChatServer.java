package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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

public class Ex02_ChatServer extends Application {
	TextArea textarea;
	// initBtn : Thread Pool을 생성
	// startBtn : Thread Pool을 이용해서 Thread를 실행
	// stopBtn : 프로그램이 종료될 때 Thread Pool을 종료
	Button initBtn, stopBtn, startBtn;

	// ExecutorService : Thread Pool
	ExecutorService executorService;
	ExecutorCompletionService<Integer> executorCompletionService;
	private int total = 0;

	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ExecutorService executorService;

		ExecutorCompletionService<HashMap<String, PrintWriter>> executorCompletionService;
		private int total = 0;

		ServerSocket server = null;
		Socket socket = null;

		try {
			executorService = Executors.newFixedThreadPool(5);

			// 기존 Thread Pool에서 확장된 기능이 있는 Completion Pool 생성
			executorCompletionService = new ExecutorCompletionService<HashMap<String, PrintWriter>>(executorService);

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

		initBtn = new Button("Thread Pool 생성");
		initBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		initBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			// Executors.newCachedThreadPool() : Thread pool 생성
			// 처음에 만들어지는 Thread pool 안에는 Thread가 없음
			// 만약 필요하면 내부적으로 Thread를 생성

			// newCachedThreadPool()
			// resource가 허용하는 한 만드는 Thread의 수는 제한이 없음
			// 60초 동안 Thread가 사용되지 않으면 자동적으로 삭제
			executorService = Executors.newCachedThreadPool();

			// 기존 Thread Pool에서 확장된 기능이 있는 Completion Pool 생성
			executorCompletionService = new ExecutorCompletionService<Integer>(executorService);

			// newFixedThreadPool()
			// 인자로 들어온 int 값을 넘는 Thread를 생성할 수 없음
			// Thread가 사용되지 않더라도 만들어진 Thread 계속 유지
			// executorService = Executors.newFixedThreadPool(5);

			// executorService가 상위 객체이기 때문에 down casting
			// getPoolSize() : 현재 pool 안에 몇 개의 Thread가 존재하는지 확인
			int threadNum = ((ThreadPoolExecutor) executorService).getPoolSize();
			printMsg("현재 Thread Pool 안의 Thread 개수 : " + threadNum);

		});

		stopBtn = new Button("Thread Pool 종료");
		stopBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		stopBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			// shutdown() : Thread Pool 내의 Thread들을 interrupt 걸어서 종료시킨 후
			// Thread Pool도 종료시킴
			executorService.shutdown();

		});

		startBtn = new Button("Thread 실행");
		startBtn.setPrefSize(250, 50);

		// setOnAction(특정 interface를 구현한 객체이고 추상 method를 overriding 하는 코드)
		startBtn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			for (int i = 1; i < 101; i += 10) {
				// 람다 내에서 지역변수 쓸 수 없음 -> final 임시 변수 선언
				final int j = i;

				// Callable 객체 생성
				Callable<Integer> callable = new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						IntStream intStream = IntStream.rangeClosed(j, j + 9);
						int sum = intStream.sum();
						return sum;
					}
				};

				// 확장된 Thread pool을 이용해서 10개의 Thread를 실행
				executorCompletionService.submit(callable);
			}
			// 10개의 Thread에서 리턴된 결과값을 취합하는 Thread
			Runnable runnable = new Runnable() {
				public void run() {
					for (int i = 0; i < 10; i++) {
						// executorCompletionService가 공용으로 사용되고 있기 때문에
						// take()가 기다리고 있다가
						// submit()을 통해 수행되는 Thread 중 끝난 Thread가 발생하면
						// 그 결과값을 받아옴
						try {
							Future<Integer> future = executorCompletionService.take();
							total += future.get();
						} catch (InterruptedException e) {
							// executorCompletionService.take()에서 발생하는 Exception 처리
							e.printStackTrace();
						} catch (ExecutionException e) {
							// future.get()에서 발생하는 Exception 처리
							e.printStackTrace();
						}
					}
					printMsg("최종 결과값은 " + total);
				}
			};
			executorService.execute(runnable);
		});
		// FlowPane : 오른쪽으로 붙이는 layout
		// 안스의 Linear Layout
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// FlowPane에 버튼 올리기
		flowpane.getChildren().add(initBtn);
		flowpane.getChildren().add(stopBtn);
		flowpane.getChildren().add(startBtn);
		root.setBottom(flowpane);

		// 실제 Window에 띄우기 위해 Scene 객체 필요
		Scene scene = new Scene(root);
		// Stage primaryStage : 실제 Window 객체
		primaryStage.setScene(scene);
		primaryStage.setTitle("Thread Pool 예제입니다");
		// Window에 띄우기
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();

	}

}
