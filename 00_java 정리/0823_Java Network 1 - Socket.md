# Java Network 1 - Socket

- IP address : 컴퓨터를 구분하기 위한 논리적 주소

- MAC address : 네트워크 통신을 통해 실제 데이터를 주고 받기 위한 물리적 주소

- 네트워크 통신을 하려면

  IP <-> MAC 사이의 address convert 과정이 필요

- DNS : Domain Name system

  ​		   Domain Name Server

- Protocol : 통신 규약

- Socket 

  ![1566541916657](https://user-images.githubusercontent.com/50972986/63658457-93a4cc80-c7e5-11e9-8599-ba4eddad2117.png)

  <br>

  - 하위의 복잡한 프로토콜과 상관없이 더 쉽고 유연하게 네트워크 프로그램 작성 가능
  - TCP/IP 프로토콜을 사용하는 프로그램을 쉽게 작성하기 위한 프로그래밍 API
  - data를 보내거나 받는 창구 역할
  - Port를 이용한 data의 전송을 추상화 시킴
  - 일단 Socket 객체만 생성되면 file system이나 다른 I/O처럼 사용이 가능

  - Socket 프로그래밍을 하기 위해서는 작성하는 프로세스가 Server 역할을 담당할지 Client 역할을 담당할지 결정

    - Server :  Client가 접속을 요청하면 이에 응답하는 프로세스

      ​				Client가 접속할 때 까지 대기함 

      ​				=> ServerSocket class의 accept() : Blocking method

    - Client : Socket을 통해 server에 접속

      ​			  server 접속에 성공하면 Socket 객체가 반환

  - 접속을 종료할 때는 모든 resource를 해제해야 함

  - 네트워크 연결이 되면 client와 server에 각각 Socket 객체가 생성됨

  <br>

  ![1566536878343](https://user-images.githubusercontent.com/50972986/63658466-a7e8c980-c7e5-11e9-9deb-7533a2f0ea4a.png)

  

  - Stream을 통해 데이터 주고받음

  - Server의 OutputStream은 Client의 InputStream과 연결

    Server의 InputStream은 Client의 OutputStream과 연결
  
    

<br>

<br>

#### 접속한 Client에 날짜를 전송하는 Server Program 예제

- Server

> 전체 소스코드 [Ex01_DateServer](https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20Network/Ex01_DateServer.java) 참고

<br>

``` java
// 서버쪽 프로그램은 클라이언트의 소켓 접속을 기다림
// ServerSocket class를 이용해서 기능을 구현
ServerSocket server = null;

// 클라이언트와 접속된 후 socket 객체가 있어야지
// 클라이언트와 데이터 통신이 가능
Socket socket = null;

// port 번호를 가지고 ServerSocket 객체를 생성
// port 번호는 0~65535 사용 가능
// 0~1023까지는 예약되어 있음
try {
    server = new ServerSocket(5554);
    System.out.println("클라이언트 접속 대기");

    // block : 클라이언트의 접속을 기다림
    // 만약 클라이언트가 접속해 오면 Socket객체를 하나 리턴함
    socket = server.accept();

    PrintWriter out = new PrintWriter(socket.getOutputStream());
    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    out.println(format.format(new Date()));

    // 일반적으로 Reader와 Writer는 내부 buffer를 가지고 있음
    // 명시적으로 내부 buffer를 비우고 데이터를 전달하는 명령
    out.flush();

    // resource 해제
    out.close();
    socket.close();
    server.close();
} catch (IOException e) {
    e.printStackTrace();
}
```

<br>

- Client

> 전체 소스코드 [Ex01_DateClient](https://github.com/5dddddo/java/blob/master/0823_Java_SE_programming%20-%20Network/Ex01_DateClient.java) 참고

<br>

``` java
// 클라이언트는 버튼을 누르면 서버쪽에 Socket 접속을 시도
// Socket에 URL과 port 번호를 넘겨줘야 함

// 만약에 접속에 성공하면 Socket 객체 반환
Socket socket = new Socket("127.0.0.1", 5554);

// Stream 생성
InputStreamReader isr = new InputStreamReader(socket.getInputStream());
BufferedReader br = new BufferedReader(isr);

// Server가 보낸 날짜 읽어옴
String msg = br.readLine();
// TextArea에 출력
printMsg(msg);

// resource 해제
br.close();
isr.close();
socket.close();
```

<br>

- 실행 결과

![1566542976353](https://user-images.githubusercontent.com/50972986/63658470-b931d600-c7e5-11e9-9f9c-c7b34d33b071.png)
