package day05;

public class TV {

	String color;
	int size;
	boolean power;
	int channel;

	public void channelUp() {
		this.channel++;
	}

	public void channelDown() {
		this.channel--;
	}

	public void power() {
		this.power = !power;
	}

	public void print() {

		System.out.printf("TV [색상 : %s, 전원 상태 : %b, 현재 체널 : %d ]\n"
				, this.color, this.power, this.channel);
	}

}
