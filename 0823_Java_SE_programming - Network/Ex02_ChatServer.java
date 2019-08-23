package javaNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

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
class ClientRunnable implements Runnable {
	BufferedReader br;
	PrintWriter out;
	Socket socket;

	public ClientThread(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// Stream 생성
			br = new BufferedReader(new InputStreamReader(fsocket.getInputStream()));
			out = new PrintWriter(fsocket.getOutputStream());

			// br로부터 데이터를 읽어서 out을 통해 다시 전달
			String msg = "";

			while (!((msg = br.readLine()).equals("/@EXIT"))) {
				out.println(msg);
				out.flush();
				out.close();
				br.close();
				fsocket.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class Ex02_ChatServer {

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;

		try {

			ExecutorService executorService = Executors.newFixedThreadPool(5);
			int threadNum = ((ThreadPoolExecutor) executorService).getPoolSize();
			System.out.println(threadNum);
			System.out.println("서버 프로그램 기동 : 5556");
			server = new ServerSocket(5556);
			System.out.println("클라이언트 접속 대기");

			socket = server.accept();
			// thread runnable 객체 생성

			ClientRunnable runnable = new ClientRunnable(socket);

			executorService.execute(runnable);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
