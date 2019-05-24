package day05;

public class Test03_Time {

	public static void main(String[] args) {
		Time t1 = new Time();
		t1.setHour(14);
		t1.setSecond(20);
		t1.print();

	}

}

class Time {
	private int hour, second;
	private boolean am;

	public boolean isAm() {
		return hour < 12;
	}

	public void setAm(boolean am) {
		this.am = am;
	}

	public void print() {
		System.out.printf("%2d시 %2d분\n", hour, second);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {

		if (hour >= 0 && hour <= 23)
			this.hour = hour;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}