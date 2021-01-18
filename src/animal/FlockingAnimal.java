/** Exam No: Y3843340 **/
package animal;
import java.util.ArrayList;
import drawing.Canvas;
import main.Utils;

public class FlockingAnimal extends DynamicAnimal {
	// Declare a boolean variable 
	protected boolean random;
	
	// Define contructor that takes in a type canvas as well as a starting x and y coordinate as its arguments
	public FlockingAnimal(Canvas canvas, double xPosition, double yPosition) {
		
		// Call DynamicAnimal constructor
		super(canvas, xPosition, yPosition);
		// Initialise random to false
		random = false;

	}
	
	// Define contructor that takes in a type canvas
	public FlockingAnimal(Canvas canvas) {
		
		// Call DynamicAnimal constructor
		super(canvas);
		// Initialise random to false
		random = false;
	}
	
	// Define contructor that takes in a type canvas, a starting x and y coordinate,
	// as well as a boolean variable 'rand' as its arguments
	// If a FlockingAnimal is created with rand as true, it will be a RandomAnimal that flocks
	public FlockingAnimal(Canvas canvas, double xPosition, double yPosition, boolean rand) {
		
		// Call DynamicAnimal constructor
		super(canvas, xPosition, yPosition);
		// Initialise random to the value of argument 'rand'
		random = rand;
	}
	
	// Define contructor that takes in a type canvas as well as a boolean variable 'rand' as its arguments
	// If a FlockingAnimal is created with rand as true, it will be a RandomAnimal that flocks
	public FlockingAnimal(Canvas canvas, boolean rand) {
		
		// Call DynamicAnimal constructor
		super(canvas);
		// Initialise random to the value of argument 'rand' 
		random = rand;
	}

	/**
	 * Update the position of the animal so that it can be seen moving on the screen at a particular speed
	 * @param time The time at which each update occurs
	 */
	public void update(int time) {
			
			// If true is declared in the constructor
			if (random == true) {
				// Create a random angle between -20 and 20
				double angle = Utils.randomInt(40)-20;
				// Turn the animal by this random angle
				this.turn((int)angle);
			}
			// Distance moved = speed * time elapsed
			int distance = (int)(this.getSpeed() * ((double)time/1000));
			// Move this distance
			this.move(distance);	
		
	}
	
	/**
	 * Method to implement a cohesion behaviour in a group (flock) of animals
	 * @param animals An array of type FlockingAnimal
	 */
	public static void Cohesion(ArrayList<FlockingAnimal> animals) {
		
		// for all animals in the array
		for (int j = 0; j < animals.size(); j++) {
			
			// Declare and initialise variables to 0
			double mean_x = 0;
			double mean_y = 0;
			double x_sum = 0;
			double y_sum = 0;
			int amount = 0;
			double CohesionAngle = 0;
			
			// for all animals in the array
			for (int i = 0; i < animals.size(); i++) {
				// if an agent compares itself then terminate the loop
				if(i==j) continue;

				// Find the x and y distances that an animal is away from the 'j' agent
				double x = animals.get(j).position.getX() - animals.get(i).position.getX();
				double y = animals.get(j).position.getY() - animals.get(i).position.getY();
				
				// Use coordinates to find length between agent and animal.get(i)
				double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));


				// For all the animals within the radius of a particular agent, add up their respective x and y coordinates
				if (length <= animals.get(j).radius) {
					
					// Add all of the x and y coordinate together
					x_sum += animals.get(i).position.getX();
					y_sum += animals.get(i).position.getY();
					
					// Keep track of how many animals there are in the radius
					amount = amount + 1;
				}
			}
		
				// Get the average x and y coordinates for all animals in the radius if there is a animal in the radius
				// Centre of mass
				if (amount > 0) {
					mean_x = x_sum/amount;
					mean_y = y_sum/amount;
				}	
		
				// Difference between animal in radius's position and centre of mass
				double xDiff = mean_x - animals.get(j).position.getX();
				double yDiff = mean_y - animals.get(j).position.getY();
				
				// Find the angle that an animal needs to turn so that it moves towards the centre of mass
				double angleInRadians = Math.atan2(yDiff, xDiff);
				double angleInDegrees = Math.toDegrees(angleInRadians);
				
				//Keep angle within 0-360 degrees
				if (angleInDegrees < 0) {
					angleInDegrees = 360 - (-angleInDegrees);
				}
				
				// Calculate the angle which the 'j' animal needs to turn to reach the centre of mass
				CohesionAngle = (animals.get(j).kc/100)*(angleInDegrees - Math.toDegrees(animals.get(j).angle));
				if (amount > 0) {
					// Turn this animal by the cohesion angle
					animals.get(j).turn((int)(CohesionAngle));
				}
	
			}

		}
	
	/**
	 * Method to implement a separation behaviour in a group (flock) of animals
	 * @param animals An array of type FlockingAnimal
	 */
	public static void Separation(ArrayList<FlockingAnimal> animals) {
		
		// for all animals in the array
		for (int j = 0; j < animals.size(); j++) {
			
			// Declare and initialise variables to 0
			double mean_x = 0;
			double mean_y = 0;
			double x_sum = 0;
			double y_sum = 0;
			int amount = 0;
			double SeparationAngle = 0;

			// for all animals in the array
			for (int i = 0; i < animals.size(); i++) {
				// if an agent compares itself then terminate the loop
				if(i==j) continue;
				
				// Find the x and y distances that an animal is away from the 'j' agent
				double x = animals.get(j).position.getX() - animals.get(i).position.getX();
				double y = animals.get(j).position.getY() - animals.get(i).position.getY();
				
				// Use coordinates to find length between agent and animal.get(i)
				double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				
				// For all the animals within the radius of a particular agent
				if (length <= animals.get(j).sepRadius) {
					
					// Add all of the x and y coordinate together
					x_sum += animals.get(i).position.getX();
					y_sum += animals.get(i).position.getY();
					
					// Keep track of how many animals there are in the radius
					amount = amount + 1;

				}
			}
			
			// Get the average x and y coordinates for all animals in the radius
			// Centre of mass
			if(amount > 0) {
				mean_x = x_sum/amount;
				mean_y = y_sum/amount;
			}
			
			// Difference between animal in radius's position and centre of mass
			double xDiff = mean_x - animals.get(j).position.getX();
			double yDiff = mean_y - animals.get(j).position.getY();
			
			// Find the angle that an animal needs to turn so that it moves towards the centre of mass
			double angleInRadians = Math.atan2(yDiff, xDiff);
			double angleInDegrees = Math.toDegrees(angleInRadians);
			
			//Keep angle within 0-360 degrees
			if (angleInDegrees < 0) {
				angleInDegrees = 360 - (-angleInDegrees);
			}
			
			// Calculate the angle which the 'j' animal needs to turn to move in the opposite direction to the centre of mass
			SeparationAngle = (angleInDegrees - Math.toDegrees(animals.get(j).angle))+180;
			SeparationAngle = (animals.get(j).ks/100)*(SeparationAngle);
			
			if(amount > 0) {
				// turn the animal by this angle
				animals.get(j).turn((int)(SeparationAngle));
			}

		}

	}
	
	/**
	 * Method to implement a alignment behaviour in a group (flock) of animals
	 * @param animals An array of type FlockingAnimal
	 */
	public static void Alignment(ArrayList<FlockingAnimal> animals) {
		
		// for all animals in the array
		for (int j = 0; j < animals.size(); j++) {
			// Declare and initialise variables to 0
			int amount = 0;
			int angle_sum = 0;
			int mean_angle = 0;
			double alignmentAngle = 0;
		
			// for all animals in the array
			for (int i = 0; i < animals.size(); i++) {
				// if an agent compares itself then terminate the loop
				if (i == j) continue;
				
				// Find the x and y distances that an animal is away from the 'j' agent
				double x = animals.get(j).position.getX() - animals.get(i).position.getX();
				double y = animals.get(j).position.getY() - animals.get(i).position.getY();
				
				// Use coordinates to find length between agent and animals.get(i)
				double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

				// For all the animals within the radius (100)
				if (length <= animals.get(j).radius) {
					
					// Add up all of their respective angles
					angle_sum += animals.get(i).getAngle();
					// Keep track of how many animals are in the radius
					amount++;

				}
				
				// If there is another animal in the radius
				if (amount > 0) {
					// Find the average angle
					mean_angle = angle_sum/amount;
				}
				
				// Don't let angle go below 0
				if (animals.get(j).ka < 0) {
					animals.get(j).ka = 0;
				}
				
				// Calculate the angle which the 'j' animal needs to turn to move in an aligned direction
				alignmentAngle = (animals.get(j).ka/100)*(mean_angle - Math.toDegrees(animals.get(j).angle));
				
				if (amount > 0) {
					// Turn the animal by this angle
					animals.get(j).turn((int)(alignmentAngle));
				}
				
				
			}
		
		}	
	
	}
	
	

}
