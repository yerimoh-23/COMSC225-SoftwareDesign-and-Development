package creativeTeaching;

/**
 * child class
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

public class Circle extends Shape {
	
	Circle () {
		super(5);
	}
	
	/**
	 * calculate the area of the circle
	 * @param length given radius of a shape
	 * @return return the area of the circle
	 */
	@Override
	double area (int length) {
		double area = length*length*Math.PI;
		return area;
	}
}

