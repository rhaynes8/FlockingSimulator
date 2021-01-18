/** Exam No: Y3843340 **/
package geometry;

public class LineSegment {
	/* Declare fields of type CartesianCoordinate for start and end points */
	public CartesianCoordinate startPoint;
	public CartesianCoordinate endPoint;
	
	/* Define constructor that takes in two CartesianCoordinate type values*/
	public LineSegment(CartesianCoordinate coord1, CartesianCoordinate coord2) {
		/* Equate start to first coordinate */
		startPoint = coord1;
		/* Equate end to second coordinate */
		endPoint = coord2;
	}
	
	
	/**
	 * Method to get and return x and y coordinates of the start point of line
	 */
	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}
	
	/**
	 * Method to get and return x and y coordinates of the end point of line
	 */
	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}
	
	/**
	 * Show the start and end coordinates as a type String that can be printed to the console
	 */
	public String toString() {
		return "Start Point = " + startPoint + ", End Point = " + endPoint + "";
	}
	
	/**
	 * Calculates the length of the line using Pythagoras' Theorem and then returns it.
	 */
	public double length() {

		return Math.sqrt(Math.pow((endPoint.getX() - startPoint.getX()), 2) + Math.pow((endPoint.getY() - startPoint.getY()), 2));
		
	}
	
	
}