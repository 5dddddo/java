# Java IO 1 - 표준 입력

> 전체 소스코드 Ex01_KeyboardInput(https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20IO/Ex01_KeyboardInput.java) 참고

<br>

- Stream이란 Java에서 특정 장치로부터 Data를 읽거나 기록할 때 사용하는 중간매체
- Stream은 단방향이고 양방향으로 사용될 수 없음
  - InputStream
  - OutputStream
- Stream은 FIFO 구조를 가짐, Random access 불가

- Stream 생성시 Stream의 종류가 구별됨

- 표준 입력 ( Keyboard )으로부터 입력을 받기 위해
  		Keyboard와 연결된 Stream 객체 필요

-  java는 표준입력에 대한 Stream을 기본적으로 제공

   => System.in : byteStream

-  Stream은 InputStream과 OutputStream으로 구분하기도 하고

  byteStream과 reader, writer 계열의 stream으로도 구분
  - byteStream : 문자열이 아닌 기본 데이터형(int,boolean,...)의 입력을 받을 때
  - reader, writer 계열 : 문자열 입력을 받을 때 효율적으로 처리 가능

<br>

#### 표준 입력(키보드)으로부터 1줄을 읽어서 출력하는 예제

- BufferedReader 사용

``` java
// 일반 스트림을 Reader로 만들어주는 역할
// 한 줄을 읽어오지 못하기 때문에 반복문 처리 필요 -> 비효율적
InputStreamReader isr = new InputStreamReader(System.in);
// 사용하기 편리한 BufferedReader로 업그레이드
BufferedReader br = new BufferedReader(isr);
```

<br>

- readLine() : block method
   					데이터가 들어올 때까지 기다림

``` java
String input = br.readLine();
```

