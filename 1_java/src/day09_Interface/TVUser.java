package day09_Interface;

public class TVUser {

	public static void main(String[] args) {
		TV u1 = new STV();
		TV u2 = new LTV();
		
		tv(u1);
		tv(u2);
		
		
		
 	}
	public static void tv(TV tv) {
		tv.powerOn();
		
	}
	

}
