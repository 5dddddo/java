# Java Thread 4 (08/21)

> 전체 소스코드 Ex02_ThreadRace 참고

<br>

![1566373059175](https://user-images.githubusercontent.com/50972986/63419946-ac595f00-c440-11e9-8a03-6efa87441299.png)

<br>

## Layout 구성 순서

1. BorderPane

``` java
@Override
public void start(Stage primaryStage) throws Exception {
    // 화면을 구성해서 window 띄우는 코드
    // 화면의 기본 layout을 설정
    // 화면을 동서남북중앙(5개의 영역)으로 분리
    BorderPane root = new BorderPane();
    // BorderPane의 크기를 설정
    // 화면에 띄우는 window의 크기 설정
    root.setPrefSize(700, 500);
    ...
}
```

<Br>

2. FlowPane 부분은 아래에서 자세히 다룸

<Br>

3. TilePane : 4행 1열짜리 TilePane 생성

``` java
// center 부분을 차지할 TilePane을 생성
TilePane center = new TilePane();
// 1열만 존재하는 TilePane
center.setPrefColumns(1);

// 4행이 존재하는 TilePane
center.setPrefRows(4);
```

<Br>

4. TextArea : TilePane 아래에 붙임

```java
// 메시지가 출력될 textarea 생성 및 크기 결정
textarea = new TextArea();
textarea.setPrefSize(600, 100);
```

<Br>

5. Button : FlowPane을 생성하고 이어 붙임

``` java
FlowPane flowpane = new FlowPane();
flowpane.setPrefSize(700, 50);
// flowpane에 버튼을 붙임
flowpane.getChildren().add(btn);
// Window의 아래 부분에 flowpane 지정
root.setBottom(flowpane);
```

<bR>

## FlowPane 부분 : Multi Thread

- UserPanel class 정의 : FlowPane class를 상속 받음 
- 몜버 변수
  - TextField
  - ProgressBar
  - ProgressIndicator

``` java
class UserPanel extends FlowPane {
    private TextField nameField = new TextField();
    // ProgressBar(진행률 초기값);
	private ProgressBar progressbar = new ProgressBar(0.0);
	// ProgressIndicator(진행률 초기값);
    private ProgressIndicator progressIndicator =
        new ProgressIndicator(0.0);

    public UserPanel(String name) {
        // 전체 패널 사이즈 지정
        this.setPrefSize(700, 50);

        // TextField의 사이즈 지정
        nameField.setPrefSize(100, 50);
        nameField.setText(name);

        // ProgressBar의 사이즈 지정
        progressbar.setPrefSize(500, 50);

        // ProgressIndicator 사이즈 지정
        progressIndicator.setPrefSize(50, 50);

        // FlowPane에 붙이기
        getChildren().add(nameField);
        getChildren().add(progressbar);
        getChildren().add(progressIndicator);

    }
}
```

<br>

- Ex02_ThreadRace class

``` java
public class Ex02_ThreadRace extends Application {
	private List<String> names =
        Arrays.asList("홍길동", "이순신", "강감찬");
    
	// FlowPane 1개당
    // progressBar를 제어할 Thread가 1개씩 존재해야 함
    
	// Thread 객체가 나중에 실행시킬
    // Runnable 객체들을 담을 리스트 객체 생성 
	private List<ProgressRunnable> uRunnable =
        new ArrayList<ProgressRunnable>();
```

<br>

- Thread를 생성할 때 넘겨줄

  Runnable interface를 구현한

  ProgressRunnable Class

``` java
class ProgressRunnable implements Runnable {
	private ProgressBar progressbar;
	private ProgressIndicator progressInedicator;
	private String name;
	private TextArea textarea;

	public ProgressRunnable(ProgressBar progressbar,
                            ProgressIndicator progressInedicator,
                            String name,
                            TextArea textarea) {
		super();
		this.progressbar = progressbar;
		this.progressInedicator = progressInedicator;
		this.name = name;
		this.textarea = textarea;
	}

	@Override
	public void run() {
		// Thread가 동작해서 progressBar를 제어
		Random random = new Random();
		// progressbar의 현재 진행률
		double k = 0;

		// progressbar.getProgress() : 진행률 0~1 사이의 값으로 알려줌
		while (progressbar.getProgress() < 1.0) {
			try {
				// 1초동안 현재 Thread를 sleep
				Thread.sleep(1000);
				k += (random.nextDouble() * 0.1);

				// 람다식 내에서 지역 변수르 사용할 수 없음
                // final 임시 변수 선언
				final double tt = k;
                
				// javaFX Thread에 UI 처리 부탁
				// k값이 지속적으로 증가
                // progressbar와 progressInedicator의 진행률
                // 증가 시킴
				Platform.runLater(() -> {
					progressbar.setProgress(tt);
					progressInedicator.setProgress(tt);
				});

                // Thread 수행 완료
				if (k > 1.0)
					break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
```

<br>

- 이제 만들어진 TilePane에 3개의 FlowPane과 textArea 부착

``` java
@Override
public void start(Stage primaryStage) throws Exception {
    
    ...
    for (String name : names) {
        // FlowPane 생성
        UserPanel panel = new UserPanel(name);
        center.getChildren().add(panel);
        // List에 Runnable interface의 추상 메소드 run()을
        // 구현한 ProgressRunnable 객체 3개 저장
        uRunnable.add(new ProgressRunnable(
            panel.getProgressbar(),
            panel.getProgressIndicator(),
            panel.getNameField().getText(),
            textarea));
    }
    center.getChildren().add(textarea);
    root.setCenter(center);

    ...
}
```

<br>

- 버튼 클릭시 새로운 Thread를 생성하고

  List에 저장해 놓은 ProgressRunnable 객체를 인자로 넘겨주어

  Thread 시작

``` java
btn = new Button("버튼 클릭 !!");
btn.setPrefSize(250, 50);
btn.setOnAction(t -> {
    // 버튼에서 Action이 발생(클릭)했을 때 호출!
    // uRunnable (Arraylist)를 돌면서
    // Thread를 생성하고 start() 호출
    for (ProgressRunnable runnable : uRunnable) {
        new Thread(runnable).start();
    }
});
```

<br>

- 3개의 Thread가 수행됨

![image](https://user-images.githubusercontent.com/50972986/63431747-83de5e80-c45a-11e9-87bf-29ad8177a826.png)

![image](https://user-images.githubusercontent.com/50972986/63431796-a5d7e100-c45a-11e9-8e12-c5a9ff3c2507.png)