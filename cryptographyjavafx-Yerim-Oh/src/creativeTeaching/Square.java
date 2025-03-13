package creativeTeaching;

/**
 * child class
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

public class Square extends Shape {
	
	Square () {
		super(5);
	}
	
	/**
	 * calculate the area of the square
	 * @param length given side of a square
	 * @return return the area of the square
	 */
	@Override
	double area (int length) {
		double area = length*length;
		return area;
	}
}

