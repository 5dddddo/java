package day08;

public interface Movable {
	public abstract void move();
}

interface Drawable {
	void draw();
}


interface MovableAndDrawable extends Movable,Drawable{
	
}
class Circle implements MovableAndDrawable {

	@Override
	public void move() {
		System.out.println("Circle move");
	}

	@Override
	public void draw() {
		System.out.println("Circle draw");
	}

}

class Rectangle implements MovableAndDrawable {

	@Override
	public void move() {
		System.out.println("Rectangle move");
	}

	@Override
	public void draw() {
		System.out.println("Rectangle draw");
	}

}


