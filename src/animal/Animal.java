/** Exam No: Y3843340 **/
package animal;
import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;

public class Animal {
	
	// Declare fields for use in the class
	// Declare a canvas to draw on
	private Canvas myCanvas;	
	// Need to know position of Animal
	public CartesianCoordinate position;
	// Where pen is up or down 
	public boolean pen;
	// What angle the animal is facing
	public double angle;
	// Radius of the animals
	public int radius;
	// How close they can move together
	public int sepRadius;
	// Cohesion Scaling Factor
	public double kc;
	// Separation Scaling Factor
	public double ks;
	// Alignment Scaling Factor
	public double ka;
	
	// Animal Constructor
	public Animal(Canvas myCanvas) {

		// Initialise a canvas
		this.myCanvas = myCanvas;
		// Starting coordinates for the animal
		this.position = new CartesianCoordinate(0, 0);
		// Pen is not on paper to start with
		this.pen = false;
		// Angle is 0 to start with
		this.angle = 0;
		// Radius is 500 to start with 
		this.radius = 500;
		// One animal wants to move away from another at a distance of 50 or greater
		this.sepRadius = 50;
		// Cohesion begins at 50%
		this.kc = 50;
		// Separation begins at 50%
		this.ks = 50;
		// Alignment begins at 50%
		this.ka = 50;
		
	}

	/**
	 * The turtle is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(int i) {
	
		// Declare some variables to store numbers in
		double a;
		double b;
		// Instantiate a new Cartesian coordinate that will be the end point
		CartesianCoordinate endPoint;
		// Equate a to the current x value (getX()) added to the distance moved in the cos of the angle	
		a = this.position.getX() + (i*Math.cos(this.angle));
		// Equate b to the current y value (getY()) added to the distance moved in the sin of the angle
		b = this.position.getY() + (i*Math.sin(this.angle));
		// Equate previously instantiated coordinates to a and b
		endPoint = new CartesianCoordinate(a, b);
		
		// If pen is true (down), draw the line
		if (this.pen == true) {
			// Declare a line segment variable
			LineSegment Line;
			
			// Draw line between a start point and an end point
			Line = new LineSegment(this.position, endPoint);
			myCanvas.drawLineBetweenPoints(this.position, Line.endPoint);
		}
		
		
		// New position equal to the end point so the animal isn't reset every time
		// the function is called
		this.position = endPoint;
		
	}

	/**
	 * Rotates the turtle clockwise by the specified angle in degrees.
	 * 
	 * @param i The number of degrees to turn.
	 */
	public void turn(int i) {

		// Math functions require radians, so angle needs to be converted from degrees
		this.angle += Math.toRadians(i);
		
//		// Keep angle within 0-360 degrees if angle is less than zero
		if (this.angle < 0) {
			this.angle = 2*Math.PI - (-this.angle);
		}
		
		// Keep angle within 0-360 degrees if angle is more than zero
		if (this.angle > 2*Math.PI) {
			this.angle = this.angle - 2*Math.PI;
		}
	}
	
	/**
	 * Get the animal's angle and return it
	 */
	public double getAngle() {
		return this.angle;
	}
	
	/**
	 * Set the animal's angle 
	 * @param angle The turtle's new angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * Get the animal's cohesion percentage and return it
	 */
	public double getkc() {
		return this.kc;
	}
	
	/**
	 * Set the animal's cohesion percentage 
	 * @param kc The percentage of cohesion
	 */
	public void setkc(double kc) {
		this.kc = kc;
	}
	
	/**
	 * Get the animal's separation percentage and return it
	 */
	public double getks() {
		return this.ks;
	}
	
	/**
	 * Set the animal's separation percentage 
	 * @param ks The percentage of separation
	 */
	public void setks(double ks) {
		this.ks = ks;
	}
	
	/**
	 * Get the animal's alignment percentage and return it
	 */
	public double getka() {
		return this.ka;
	}
	
	/**
	 * Set the animal's alignment percentage 
	 * @param ks The percentage of alignment
	 */
	public void setka(double ka) {
		this.ka = ka;
	}
	
	/**
	 * Set the animal's radius 
	 * @param radius The size of radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	/**
	 * Moves the pen off the canvas so that the animal's route isn't drawn for any subsequent movements.
	 */
	public void putPenUp() {
		pen = false;
	}

	/**
	 * Lowers the pen onto the canvas so that the animal's route is drawn.
	 */
	public void putPenDown() {
		pen = true;
		
	}
	
	/**
	 * Draws an animal to the canvas in the shape of an equilateral triangle
	 */
	public void draw() {
		
		// Draw an equilateral triangle with side length 20
		
		//Don't draw until told otherwise
		this.putPenUp();
		// Rotate by 180 degrees as we start in the middle of the triangle
		this.turn(180);
		// Move pen position by 10 to get to edge of triangle
		this.move(10);
		// Start drawing until told otherwise
		this.putPenDown();
		// Rotate by 90 degrees to begin drawing one half of a side
		this.turn(90);
		// Draw a line with a length of 10
		this.move(10);
		// Rotate by 120 degrees
		this.turn(120);
		// Draw a whole triangle side
		this.move(20);
		// Rotate by 120 degrees
		this.turn(120);
		// Draw a whole triangle side
		this.move(20);
		// Rotate by 120 degrees
		this.turn(120);
		// Draw the unfinished half of the beginning side
		this.move(10);
		// Stop drawing until told otherwise
		this.putPenUp();
		// Return the pen to the centre of the animal
		this.turn(90);
		this.move(10);
	}
	
	/**
	 * Removes the last added animal from the canvas
	 */
	public void undraw() {
		// Remove the four lines used to draw each animal
		for (int i = 0; i <= 3; i++) {
		myCanvas.removeMostRecentLine();
		}
		// Repaint the canvas to account for new changes
		myCanvas.repaint();
	}
	
	/**
	 * Wraps edges of canvas so that animals can not leave bounds
	 * @param max_x The maximum width of the canvas
	 * @param max_y The maximum height of the canvas    
	 */
	public void wrapPosition(int max_x, int max_y) {
		// If the animal is at, or over the most maximum x_coordinate on the canvas
		if (position.getX() >= max_x) {
			// Draw them at the minimum x_coordinate of the canvas
			position.setX(1);
		}
		// If the animal is at or under the most minimum x_coordinate on the canvas
		if (position.getX() <= 0) {
			// Draw them at the most maximum x_coordinate on the canvas
			position.setX(max_x);
		}
		// If the animal is at, or over the most maximum y_coordinate on the canvas
		if (position.getY() >= max_y) {
			// Draw them at the minimum y_coordinate of the canvas
			position.setY(1);
		}
		// If the animal is at or under the most minimum y_coordinate on the canvas
		if (position.getY() <= 0) {
			// Draw them at the minimum x_coordinate of the canvas
			position.setY(max_y);
		}
	}
	

}
