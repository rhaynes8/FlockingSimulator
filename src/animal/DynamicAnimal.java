/** Exam No: Y3843340 **/
package animal;
import drawing.Canvas;


public class DynamicAnimal extends Animal {
	// Declare field for speed value
	protected int speed = 100;

	// Define constructor that takes in a type Canvas as its argument
	public DynamicAnimal(Canvas canvas) {
		// Call animal constructor 
		super(canvas);
		// Draw the animal
		drawAnimal();
	}
	
	// Define contructor that takes in a type canvas as well as a starting x and y coordinate as its arguments
	public DynamicAnimal(Canvas canvas, double xPosition, double yPosition) {
		// Call animal constructor
		super(canvas);
		// Move the turtle to the starting coordinates defined by the user
		move((int)xPosition);
		turn(90);
		move((int)yPosition);
		// Turn back to the original angle
		turn(-90);
		// Draw the animal now that we're in the right position
		drawAnimal();
	}

	/**
	 * Draw animal using draw function in Animal class
	 */
	private void drawAnimal() {
		this.draw();
	}
	/**
	 * Get the current speed of the DynamicAnimal and return it
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set the current speed of the turtle
	 * @param speed The speed at which the turtle will move
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Update the position of the turtle so that it can be seen moving on the screen at a particular speed
	 * @param time The time at which each update occurs
	 */
	public void update(int time) {
		// Distance moved = speed * time elapsed
		int distance = (int)(this.speed * ((double)time/1000));
		// Move this distance
		this.move(distance);
	}
}
