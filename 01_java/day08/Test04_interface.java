package day08;

public class Test04_interface {

	public static void main(String[] args) {

		Circle c = new Circle();
		Rectangle r = new Rectangle();

		Drawable d = c;
		d.draw();

		Movable m = r;
		r.move();

		MovableAndDrawable[] md = { c, r };
		md[0].move();
		md[0].draw();
		
		
		
		
	}

}
