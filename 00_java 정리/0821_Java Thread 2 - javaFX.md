# Java Thread 2 (08/21)

> 전체 소스코드 [Ex00_ThreadBasic](https://github.com/5dddddo/java/blob/master/0821_Java_SE_programming%20-%20Thread/Ex00_ThreadBasic.java) 참고

## JavaFX란?

- JavaFX 응용 프로그램에 포함된

  모든 Java 라이브러리를 사용할 수 있도록 하여

  Java의 강력한 기능을 확장,

  프레젠테이션 기술을 활용할 수 있게 해주는

  응용 프로그램

<br>

## JavaFX library 추가

1. https://gluonhq.com/products/javafx/
2. JavaFX Windows SDK Download
3. java project - [Build path] - [Configure Build path...] - [Add External Jars] 
   - javafx-sdk-11.0.2\lib 내의 .jar 파일들 추가

4. class 정의시 extends Application

<br>

#### 현재 사용되는 Thread의 이름을 출력하는 예제

<br>

```java
public class Ex00_ThreadBasic extends Application {

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

		btn = new Button("버튼 클릭 !!");
		btn.setPrefSize(250, 50);
		btn.setOnAction(t -> {
			// 버튼에서 Action이 발생(클릭)했을 때 호출!
			// 버튼을 클릭하면 Thread 생성
			// Runnable 객체의 run을 override하는 실행 코드를
			// 람다식으로 표현
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName());
			}).start();
		});
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// flowpane에 버튼을 올려요!
		flowpane.getChildren().add(btn);
		root.setBottom(flowpane);
		
		// Window 띄우기 위해서
		// Scene 객체가 필요
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Thread 예제입니다.");
		primaryStage.show();
	}

	public static void main(String[] args) {
		// 현재 main method를 호출한 Thread의 이름을 출력
		// Thread.currentThread()
        // : 지금 이 코드를 수행하는 thread가 어떤 thread인지 가져옴
		System.out.println(Thread.currentThread().getName());

		// launch() : start()를 실행시키는 함수
		launch();
	}
}

```

<br>

![1566378250252](https://user-images.githubusercontent.com/50972986/63418789-a4002480-c43e-11e9-912c-96b987d40396.png)

<br>

```java
/* 출력결과
    main
    JavaFX Application Thread
    Thread-4 // 버튼 클릭
    Thread-5 // 버튼 클릭
    Thread-6 // 버튼 클릭
    ...
*/
```

