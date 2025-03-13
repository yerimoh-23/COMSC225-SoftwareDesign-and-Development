package creativeTeaching;

/**
 * concrete class
 *
 * @author Yerim Oh
 * @date March 22, 2023
 */

class PersonWalking implements Walk {

	/**
	 * calculate the speed
	 * @param distance the distance the person walked in kilometers
	 * @param time the amount of time the person walked in hours
	 */
	@Override
	public float speed (int distance, int time) {
		float speed = (float)distance/time;
		return speed;
	}
}

