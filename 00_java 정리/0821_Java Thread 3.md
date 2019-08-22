# Java Thread 3 (08/21)

> 전체 소스코드 [Ex01_ThreadBasic](https://github.com/5dddddo/java/blob/master/0821_Java_SE_programming/Ex01_ThreadBasic.java) 참고

<br>

#### 버튼 클릭 시 Text 출력하는 예제

![image](https://user-images.githubusercontent.com/50972986/63426646-5095d280-c44e-11e9-8aa8-432832949333.png)

<br>

- BorderPane : 화면 기본 layout을 설정

  ​					    화면을 동/서/남/북/중앙 (5개의 영역)으로 분리

  - setPrefSize(w,h) : 사이즈 설정
  - setXXX() : 동/서/남/북/중앙에 위치할 component 지정

- Platform.runLater( )

  : 화면 제어는 새로 생성한 Thread가 처리하지 않고

    JavaFX Application Thread가 담당하도록 함

    textarea에 출력하기 위해서

   새 Thread를 파생시켜 JavaFX Application Thread한테 부탁

- FlowPane : LinearLayout
- Scene : 실제 Window 창을 띄우기 위해 필요한 class
- Stage : 실제 Window 객체

<br>

```java
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class javaFXUITemplate extends Application {
	TextArea textarea;
	Button btn;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 화면을 구성해서 window 띄우는 코드
		// 화면의 기본 layout을 설정
        // => 화면을 동서남북중앙(5개의 영역)으로 분리
		BorderPane root = new BorderPane();
		// BorderPane의 크기를 설정
        // => 화면에 띄우는 window의 크기 설정
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
				// 화면 제어는 새로 생성한 Thread가 처리하지 않고
				// JavaFX Application Thread가 담당하도록 함
				// textarea에 출력하기 위해서
				// 새 Thread를 파생시켜 JavaFX Application Thread한테 부탁
				Platform.runLater(() -> {
					textarea.appendText("소리없는 아우성");
				});
			}).start();
		});
        
        // FlowPane : Android의 LinearLayout과 같은 layout
        // 			  옆으로 붙음
		FlowPane flowpane = new FlowPane();
		flowpane.setPrefSize(700, 50);
		// flowpane에 버튼을 올림
		flowpane.getChildren().add(btn);
        // window 바닥에 flowpane 띄움
		root.setBottom(flowpane);
}
    public static void main(String[] args) {
        // launch() : start()를 실행시키는 함수
        launch();
    }

}
```

<br>

![image](https://user-images.githubusercontent.com/50972986/63426893-dd409080-c44e-11e9-9420-95febeef6c9d.png)