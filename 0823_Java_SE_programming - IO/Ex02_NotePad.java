package javaIO;

/*
 * JavaFX를 이용해서 GUI 프로그램을 만들려고 해요!
 * 화면에 창 띄울려면 Application이라는 class의 instance를 생성.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Ex02_NotePad extends Application {

	TextArea textarea;
	Button openBtn, saveBtn;
	File file;

	private void printMsg(String msg) {
		Platform.runLater(() -> {
			textarea.appendText(msg + "\n");
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(700, 500);

		textarea = new TextArea();
		root.setCenter(textarea);

		openBtn = new Button();
		openBtn.setPrefSize(150, 50);
		openBtn.setText("파일 선택");
		openBtn.setOnAction(t -> {
			// textarea 안의 내용을 다 지움
			textarea.clear();
			// 파일 선택창
			FileChooser chooser = new FileChooser();
			chooser.setTitle("오픈할 파일을 선택하세요.");
			// 파일 chooser로부터 오픈할 파일에 대한 reference를 획득
			file = chooser.showOpenDialog(primaryStage);
			// file 객체로부터 inputStream을 열기
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				while ((line = br.readLine()) != null) {
					printMsg(line);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		saveBtn = new Button();
		saveBtn.setPrefSize(150, 50);
		saveBtn.setText("파일 저장");
		saveBtn.setOnAction(t -> {
			String content = textarea.getText();
			try {
				FileWriter fw = new FileWriter(file);
				// 파일 덮어쓰기
				fw.write(content);
				// 반드시 Stream의 close 처리를 해줘야 함
				fw.close();
				// 화면에 정보창 띄움
				// 확인 버튼을 누를 때까지 hold
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("파일 저장");
				alert.setHeaderText("File Save!!");
				alert.setContentText("파일에 내용이 저장됨");
				alert.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		FlowPane flowPane = new FlowPane();
		flowPane.setPrefSize(700, 50);
		flowPane.getChildren().add(openBtn);
		flowPane.getChildren().add(saveBtn);
		root.setBottom(flowPane);

		// 화면 primaryStage를 띄워주는 Scene객체 생성
		Scene scene = new Scene(root);
		// 실제 화면 세팅
		primaryStage.setScene(scene);
		primaryStage.setTitle("파일 저장 예제입니다");
		// Window에 띄우기
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
