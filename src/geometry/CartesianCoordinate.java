/** Exam No: Y3843340 **/
package geometry;

public class CartesianCoordinate {
	private double xPosition;
	private double yPosition;
	
	public CartesianCoordinate(double x, double y) {
		xPosition = x;
		yPosition = y;
	}
		
	public double getX() {
		return xPosition;
	}
	
	public double getY() {
		return yPosition;
	}
	
	public void setX(double x) {
		this.xPosition = x;
	}
	
	public void setY(double y) {
		this.yPosition = y;
	}
	
	public String toString() {
		return "(" + xPosition + ", " + yPosition + ")";
	}
	
}
