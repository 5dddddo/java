package day09_Exception;

public class Test03_InterruptException {

	public static void main(String[] args) {
		System.out.println("start");

		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초 간 block
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		System.out.println("end");
	}

}
