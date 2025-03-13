package creativeTeaching;

/**
 * abstract class; parent class
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

public abstract class Shape {
	int x;
	
	Shape (int new_x){
		x = new_x;
	}
	
	void print() {
		System.out.println("This shape has the side/radius of length " + x);
	}
	
	/**
	 * calculate the area of the shape
	 * @param length given length of the side or radius of a shape
	 */
	abstract double area (int length);
	
	public static void main(String[] args) {
		Square square = new Square();
		square.print();
		System.out.println("This shape has the area " + square.area(5));
		Circle circle = new Circle();
		circle.print();
		System.out.println("This shape has the area " + circle.area(5));
	}
}

