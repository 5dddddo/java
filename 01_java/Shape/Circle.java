package Shape;

public class Circle extends Shape {

	private double radius;

	public Circle() {
	}

	public Circle(String name, double radius) {
		super(name);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void calculationArea() {
		area = Math.PI * radius * radius;
	}

	public void print() {
		System.out.printf("원의 면적은 %.13f\n", area);
	}

}
