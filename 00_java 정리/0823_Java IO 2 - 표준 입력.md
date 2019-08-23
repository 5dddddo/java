# Java IO 2 - FileStream

> 전체 소스코드 Ex02_NotePad 참고

<br>

#### TextArea의 내용을 파일로 저장하는 예제

- 파일 선택

``` java
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
```

<br>

- 파일 저장

``` java
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
}
```

