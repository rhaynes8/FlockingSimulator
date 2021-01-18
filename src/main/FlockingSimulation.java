/** Exam No: Y3843340 **/

package main;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import main.Utils;
import drawing.Canvas;
import animal.FlockingAnimal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlockingSimulation {

	// Declare buttons to use in the program
	private JButton addAnimalButton;
	private JButton addRandimalButton;
	private JButton removeAnimalButton;
	private JButton fishButton;
	private JButton birdButton;
	private JButton dolphinButton;
	private JButton smokeParticleButton;
	private JButton clearSimulationButton;
	// Declare an array list of type FlockingAnimal
	private ArrayList<FlockingAnimal> animals;
	// Declare a boolean variable that shows whether the game loop is running
	private boolean continueRunning = true;

	/* Constructor */
	public FlockingSimulation() {
		
		// Instantiating a new array list of type FlockingAnimal
		animals = new ArrayList<FlockingAnimal>();
		
		
		// Set up a new JFrame to contain GUI Components
		JFrame frame = new JFrame();
		Canvas canvas = new Canvas();
		frame.setTitle("Flocking Simulator");
		frame.setSize(1500, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Create a grid layout for the buttons to be placed in
		GridLayout rPanel = new GridLayout(0,2);
		
		// Instantiate panels
		JPanel rightPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		// Instantiate sliders
		JSlider animalSpeed = new JSlider(0, 1000, 100);
		JSlider kCohesion = new JSlider(0, 100, 50);
		JSlider kSeparation = new JSlider(0, 100, 50);
		JSlider kAlignment = new JSlider(0, 100, 50);
		JSlider radius = new JSlider(0, 1000, 500);
		
		// Instantiate buttons
		addAnimalButton = new JButton("Add Animal");
		addRandimalButton = new JButton("Add Random Animal");
		removeAnimalButton = new JButton("Remove Animal");
		fishButton = new JButton("Fish Simulation");
		birdButton = new JButton("Bird Simulation");
		dolphinButton = new JButton("Dolphin Simulation");
		smokeParticleButton = new JButton("Smoke Particle Simulation");
		clearSimulationButton = new JButton("Clear Simulation");
				
		// Add panels to the frame
		frame.add(lowerPanel, BorderLayout.SOUTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(canvas, BorderLayout.CENTER);
		
		// Add buttons and sliders to the panels so they are visible in the GUI
		lowerPanel.add(addAnimalButton);
		lowerPanel.add(addRandimalButton);
		lowerPanel.add(removeAnimalButton);
		lowerPanel.add(animalSpeed);
		lowerPanel.add(kCohesion);
		lowerPanel.add(kSeparation);
		lowerPanel.add(kAlignment);
		lowerPanel.add(radius);
		rightPanel.add(clearSimulationButton);
		rightPanel.setLayout(rPanel);
		rightPanel.add(fishButton);
		rightPanel.add(birdButton);
		rightPanel.add(dolphinButton);
		rightPanel.add(smokeParticleButton);
		
		// Set the amount of columns in the grid to be 1
		rPanel.setColumns(1);
		
		// Show labels and ticks for each slider as well the title of the slider
		animalSpeed.setPaintTicks(true);
		animalSpeed.setPaintLabels(true);
		animalSpeed.setMinorTickSpacing(100);
		animalSpeed.setMajorTickSpacing(500);
		animalSpeed.setBorder(BorderFactory.createTitledBorder("Speed"));
		
		kCohesion.setPaintTicks(true);
		kCohesion.setPaintLabels(true);
		kCohesion.setMinorTickSpacing(10);
		kCohesion.setMajorTickSpacing(50);
		kCohesion.setBorder(BorderFactory.createTitledBorder("Cohesion (%)"));
		
		kSeparation.setPaintTicks(true);
		kSeparation.setPaintLabels(true);
		kSeparation.setMinorTickSpacing(10);
		kSeparation.setMajorTickSpacing(50);
		kSeparation.setBorder(BorderFactory.createTitledBorder("Separation (%)"));
		
		kAlignment.setPaintTicks(true);
		kAlignment.setPaintLabels(true);
		kAlignment.setMinorTickSpacing(10);
		kAlignment.setMajorTickSpacing(50);
		kAlignment.setBorder(BorderFactory.createTitledBorder("Alignment (%)"));
		
		radius.setPaintTicks(true);
		radius.setPaintLabels(true);
		radius.setMinorTickSpacing(100);
		radius.setMajorTickSpacing(500);
		radius.setBorder(BorderFactory.createTitledBorder("Radius"));

		// The number of animals to use in the simulation
		int number = 29;
		
		// Fill the screen with 'number' random animals
		for (int i = 0; i <= number; i++) {
			// Place all animals at a random position within the canvas
			animals.add(new FlockingAnimal(canvas, Utils.randomInt(canvas.getWidth()), Utils.randomInt(canvas.getHeight()), false));
		}		
		
		// A button that undraws and removes all animals in the array
		clearSimulationButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Set the background to light gray
				 canvas.setBackground(Color.LIGHT_GRAY);
				 
				 // If the array is not empty
				 if (!animals.isEmpty()) {
					 
					 for (int i = animals.size() - 1; i >= 0; i--) {
						 // Undraw all turtles in the array
						 animals.get(animals.size() - 1).undraw(); 
						 // Remove the turtles from memory
						 animals.remove(animals.size() - 1); 
					 }

				 }
				 			 
			}
		});
		
		// A button that sets up a smoke particle simulation
		smokeParticleButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Set the background to light gray
				 canvas.setBackground(Color.lightGray);
				 
				// If the array is not empty
				 if (!animals.isEmpty()) {
					 
					 for (int i = animals.size() - 1; i >= 0; i--) {
						 // Undraw all turtles in the array
						 animals.get(animals.size() - 1).undraw(); 
						 // Remove the turtles from memory
						 animals.remove(animals.size() - 1); 
					 }

				 }	 
				 
				 for (int i = 0; i <= number; i++) {
						// Add animals of type FlockingAnimal equal to the value of 'number'
						// Add them all at the same coordinate, and make them random
						animals.add(new FlockingAnimal(canvas, canvas.getWidth()/2, canvas.getHeight()/2, true));
						// Set their flocking behaviours
						animals.get(i).setkc(0);
						animals.get(i).setks(80);
						animals.get(i).setka(0);
						// Set their speed
						animals.get(i).setSpeed(35);
						// Set separation radius
						animals.get(i).sepRadius = 10;
					 }
				 
				  // Set the values of the sliders to their corresponding values					 
				  animalSpeed.setValue(1000);
				  kCohesion.setValue(0);
				  kSeparation.setValue(100);
				  kAlignment.setValue(0);
					
				  // Wait for 300ms while particles separate
				  Utils.pause(300);				
					
				  // Set all animals to smoke particle behaviour
				  for (int i = 0; i <= number; i++) {
						
				  	  animals.get(i).setka(0);
					  kAlignment.setValue(0);
						
					  animals.get(0).setSpeed(50);
					  animals.get(i).setSpeed(50);
					  animalSpeed.setValue(50);
				  }
			 }
		});
		
		// A button that makes a fish flocking simulation
		fishButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Set the background to blue for ocean setting
				 canvas.setBackground(Color.blue);
				 
				// If the array is not empty
				 if (!animals.isEmpty()) {
					 
					 for (int i = animals.size() - 1; i >= 0; i--) {
						 // Undraw all turtles in the array
						 animals.get(animals.size() - 1).undraw(); 
						 // Remove the turtles from memory
						 animals.remove(animals.size() - 1); 
					 }
					 
				 }
						 
				for (int i = 0; i <= number; i++) {
					// Add animals of type FlockingAnimal equal to the value of 'number'
					// Add them all at the same coordinate, and don't make them random
					animals.add(new FlockingAnimal(canvas, canvas.getWidth()/2, canvas.getHeight()/2, false)); 
				}
				 
				for (int i = 0; i <= number; i++) {
					// Set the separation radius to max
					animals.get(i).sepRadius = 2000;
					// Set the behaviours of the flock
					animals.get(i).setkc(100);
					animals.get(i).setks(51);
					animals.get(i).setka(0);
					// Set the speed of each animal
					animals.get(i).setSpeed(60);
					// pause for 20ms
					Utils.pause(20);

				}
				// Set the sliders to their corresponding values
				animalSpeed.setValue(60);
				kCohesion.setValue(100);
				kSeparation.setValue(51);
				kAlignment.setValue(0);
				
			 }
		});
		
		// A button that creates a bird flocking simulation
		birdButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Set background to cyan for sky setting
				 canvas.setBackground(Color.CYAN);
				 
				// If the array is not empty
				 if (!animals.isEmpty()) {
					 
					 for (int i = animals.size() - 1; i >= 0; i--) {
						 // Undraw all turtles in the array
						 animals.get(animals.size() - 1).undraw(); 
						 // Remove the turtles from memory
						 animals.remove(animals.size() - 1); 
					 }
				 }
					 
				for (int i = 0; i <= number; i++) {
					// Add animals of type FlockingAnimal equal to the value of 'number'
					// Add them all at the same coordinate, and don't make them random
					animals.add(new FlockingAnimal(canvas, canvas.getWidth()/2, canvas.getHeight()/2, false)); 
				}
				 
				for (int i = 0; i <= number; i++) {
					// Set their radii to max
					animals.get(i).radius = 1000;
					animals.get(i).sepRadius = 35;
					// Set their flocking behaviours
					animals.get(i).setkc(0);
					animals.get(i).setks(100);
					animals.get(i).setka(0);
					// Set the speed
					animals.get(i).setSpeed(1000);
					// Pause for 20ms
					Utils.pause(20);
				}
	
				// Set sliders to their corresponding values
				animalSpeed.setValue(1000);
				radius.setValue(2000);
				kCohesion.setValue(0);
				kSeparation.setValue(100);
				kAlignment.setValue(5);
				// Wait while birds separate
				Utils.pause(100);
				
				for (int i = 0; i <= number; i++) {
					animals.get(i).setSpeed(175);
					animals.get(i).setks(20);
					animals.get(i).setkc(15);
				}
				animalSpeed.setValue(175);
				kSeparation.setValue(20);
				kCohesion.setValue(15);
			 }
		});
		
		// A button that creates a pod of dolphin simulation
		dolphinButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Set background to blue for ocean setting
				 canvas.setBackground(Color.BLUE);
				 
				// If the array is not empty
				 if (!animals.isEmpty()) {
					 
					 for (int i = animals.size() - 1; i >= 0; i--) {
						 // Undraw all turtles in the array
						 animals.get(animals.size() - 1).undraw(); 
						 // Remove the turtles from memory
						 animals.remove(animals.size() - 1); 
					 }
				 }				 
				 
				for (int i = 0; i <= number; i++) {
					// Add animals of type FlockingAnimal equal to the value of 'number'
					// Add them all at the same coordinate, and don't make them random
					animals.add(new FlockingAnimal(canvas, canvas.getWidth()/2, canvas.getHeight()/2, false)); 
				}
				 
				for (int i = 0; i <= number; i++) {
					// Set behaviours so that they separate very quickly from the coordinate that they were all placed on
					animals.get(i).radius = 1000;
					animals.get(i).sepRadius = 50;
					animals.get(i).setSpeed(1000);
					animals.get(i).setkc(0);
					animals.get(i).setks(100);
					animals.get(i).setka(0);
					// Pause for 20ms
					Utils.pause(20);
				}
				// Set sliders to corresponding values
				animalSpeed.setValue(1000);
				kCohesion.setValue(0);
				kSeparation.setValue(100);
				kAlignment.setValue(0);
				// Pause whilst dolphins separate
				Utils.pause(120);				
				
				for (int i = 0; i <= number; i++) {
					// Set alignment value
					animals.get(i).setka(50);
					// Change slider to corresponding value
					kAlignment.setValue(50);
					// Set speed to 100
					animals.get(0).setSpeed(100);
					animals.get(i).setSpeed(100);
					// Change slider to corresponding value
					animalSpeed.setValue(100);
				}
			 }
		});
		
	
		// Button that adds a new animal to the canvas
		addAnimalButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 // Add a new, non-random animal to a random point within the canvas boundaries
				 animals.add(new FlockingAnimal(canvas, Utils.randomInt(canvas.getWidth()), Utils.randomInt(canvas.getHeight()), false));
				 // Set this new animal's behaviours, speed and radius
				 animals.get(animals.size() - 1).setSpeed(animalSpeed.getValue());
				 animals.get(animals.size() - 1).setkc(kCohesion.getValue());
				 animals.get(animals.size() - 1).setks(kSeparation.getValue());
				 animals.get(animals.size() - 1).setka(kAlignment.getValue());
				 animals.get(animals.size() - 1).radius = radius.getValue();
				 
		
			}

		});
		
		// Button that add a new random animal to the canvas
		addRandimalButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 
				// Add a new, random animal to a random point within the canvas boundaries
				 animals.add(new FlockingAnimal(canvas, Utils.randomInt(canvas.getWidth()), Utils.randomInt(canvas.getHeight()), true));
				// Set this new animal's behaviours, speed and radius
				 animals.get(animals.size() - 1).setSpeed(animalSpeed.getValue());
				 animals.get(animals.size() - 1).setkc(kCohesion.getValue());
				 animals.get(animals.size() - 1).setks(kSeparation.getValue());
				 animals.get(animals.size() - 1).setka(kAlignment.getValue());
				 animals.get(animals.size() - 1).radius = radius.getValue();
				
			 }
		});
		
		// Button that removes the last animal from the array
		removeAnimalButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent ae) {
				 
				 // If the array isn't empty
				 if (!animals.isEmpty()) {
					 // Undraw the last animal in the array
					 animals.get(animals.size() - 1).undraw();
					 // Remove it from the memory
					 animals.remove(animals.size() - 1);
				 }
				
			 }
		});
		// Slider that controls the speed of the animals
		animalSpeed.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0; i < animals.size(); i++) {
					// Change all animals' speeds to the value of the speed slider
					animals.get(i).setSpeed(animalSpeed.getValue()-1);
				}
			
			}
			
		});
		
		// Slider that controls the cohesion behaviour of the animals
		kCohesion.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0; i < animals.size(); i++) {
					// Change all animals' cohesion to the value of the cohesion slider
					animals.get(i).setkc(kCohesion.getValue()-1);
				}			
			}			
		});
		
		// Slider that controls the separation behaviour of the animals
		kSeparation.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0; i < animals.size(); i++) {
					// Change all animals' separation to the value of the separation slider
					animals.get(i).setks(kSeparation.getValue()-1);
				}			
			}	
		});
		
		// Slider that controls the alignment behaviour of the animals
		kAlignment.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0; i < animals.size(); i++) {
					// Change all animals' alignment to the value of the alignment slider
					animals.get(i).setka(kAlignment.getValue()-1);
				}
			}
			
		});
		
		// Slider that controls the radius of the animals
		radius.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				
				for (int i = 0; i < animals.size(); i++) {
					// Change all animals' radius to the value of the radius slider
					animals.get(i).setRadius(radius.getValue()-1);
				}
			
			}
			
		});

		// Call the game loop from within the constructor
		gameLoop(canvas);
	
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		
		System.out.println("Running Flocking Simulation...");
		
		new FlockingSimulation();
	}
	
	/**
	 * Game Loop
	 * @param canvas The canvas with which to draw on
	 */
	public void gameLoop(Canvas canvas) {
		// Declare a time variable to update with
		int deltaTime = 20;				
	
		// While the program is running
		while (continueRunning) {
			// undraw all animals
			for (FlockingAnimal animal : animals) {
				animal.undraw();
			}
			// update all animals
			for (FlockingAnimal animal : animals) {
				animal.update(deltaTime);
			}
			// draw all animals
			for (FlockingAnimal animal : animals) {
				animal.draw();
			}
			// wrap the position of all animals so that they cannot leave the boundaries of the canvas
			for (FlockingAnimal animal : animals) {
				animal.wrapPosition(canvas.getWidth(), canvas.getHeight() - 50);
			}
			// Call the flocking behaviour methods
			FlockingAnimal.Cohesion(animals);
			FlockingAnimal.Separation(animals);
			FlockingAnimal.Alignment(animals);
			// Pause, so that the gameLoop updates every 20ms
			Utils.pause(deltaTime);
		}
	
	}
	
}
