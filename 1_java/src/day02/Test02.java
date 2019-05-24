package day02;


public class Test02 {

	public static void main(String[] args) {
		double j = Integer.parseInt("90");
		double d = Double.parseDouble("89.9");
		System.out.printf("j = %5.2f,  y= %5.2f\n", j, d);

		double tmp;
		tmp = j;
		j = d;
		d = tmp;
		System.out.printf("j = %5.2f,  y= %5.2f", j, d);

	}

}
