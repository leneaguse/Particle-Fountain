//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Fountain
// Files:           P2 Fountain Particle.jar
// Course:          CS 300, Spring, 2019
//
// Author:          Lenea Guse
// Email:           laguse@wisc.edu 
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __ Write-up states that pair programming is allowed for this assignment.
//   __ We have both read and understand the course Pair Programming Policy.
//   __ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         N/A
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
public class Fountain {
	private static Random randGen = new Random();
	private static Particle[] particles;
	private static int positionX; // middle of the screen (left to right): 400
	private static int positionY; // middle of the screen (top to bottom): 300
	private static int startColor; // blue: Utility.color(23,141,235)
	private static int endColor; // lighter blue: Utility.color(23,200,255)
	private static float partSize;
	private static int transparentColor;
	

/**
 * Creates a single particle at position (3,3) with velocity (-1,-2). Then checks whether calling
 * updateParticle() on this particle's index correctly results in changing its position to
 * (2,1.3).
 * 
 * @return true when no defect is found, and false otherwise
 */
private static boolean testUpdateParticle() {
	boolean testUpdate = false;
	//creates a new particles
	particles[0] = new Particle(3, 3, Fountain.partSize, Utility.lerpColor(startColor, endColor, .5f));
	//sets the velocities
	particles[0].setVelocityX(-1);
	particles[0].setVelocityY(-2);
	
	updateParticle(0);
	
	//gets the positions 
	float changedxpos = particles[0].getPositionX();
	float changedypos = particles[0].getPositionY();
	
	float expectedX = 2.0f;
	float expectedY = 1.3f;
	
	//compares if the expected positions are the actual
	if (expectedX != changedxpos || expectedY != changedypos) {
		testUpdate = true;
	}
  return testUpdate;
}
 
/**
 * Calls removeOldParticles(6) on an array with three particles (two of which have ages over six
 * and another that does not). Then checks whether the old particles were removed and the young
 * particle was left alone.
 * 
 * @return true when no defect is found, and false otherwise
 */
private static boolean testRemoveOldParticles() {
	boolean testRemove = false;
	//creates and sets the age of particle 1
	particles[0] = new Particle(3, 3, Fountain.partSize, Utility.lerpColor(startColor, endColor, .5f));
	particles[0].setAge(9);
	//creates and sets the age of particle 2
	particles[1] = new Particle(3, 3, Fountain.partSize, Utility.lerpColor(startColor, endColor, .5f));
	particles[1].setAge(8);
	//creates particle 3 with a 0 age
	particles[2] = new Particle(3, 3, Fountain.partSize, Utility.lerpColor(startColor, endColor, .5f));
	
	removeOldParticles(6);
	
	//checks if the spots were null and the particles were removed
	if (particles[0] != null || particles[1] != null) {
		testRemove = true;
	}
	
  return testRemove; 
}

/**
 * An additional test method that checks if the correct number of particles were made
 * Calls createNewParticles to make 3 new particles
 * then checks if all three were made and no more than three
 * 
 * @return no return
 */

private static void testCreateNewParticles() {
	boolean testPassed = true;
	createNewParticles(3);
	
	//checks if three particles were made
	if (particles[0] == null || particles[1] == null || particles[2] == null) {
		testPassed = false;
	}
	//checks if only three particles were made
	if (particles[3] != null) {
		testPassed = false;
	}
	 System.out.println(testPassed);
}
	
	public static void main(String[] args) {
		Utility.runApplication();
	}
	
	public static void setup() {
		//creates an array of 800 new particles
		particles = new Particle[800];
		
		//calls the test methods to ensure it's working out properly
		boolean testUpdate = testUpdateParticle();
		boolean testRemove = testRemoveOldParticles();
		//prints out "FAILED" if anything has gone wrong with the program
		if (testUpdate == true || testRemove == true) {
			System.out.print("FAILED");
		}
		//resets the spaces where particles were made
		particles[0] = null;
		particles[1] = null;
		particles[2] = null;
			
		//defines the start and end colors
		startColor = Utility.color(23, 141, 235);
		endColor = Utility.color(23, 300, 255);
		
		//sets the initial position
		positionX = 400;
		positionY = 300;
		
		//sets the initial velocity
		float velx = -6;
		float vely = -6;
		
	}
	
	public static void update(){
		int number = randGen.nextInt();
		int maxAge = 80;
		
		//sets the background color
		Utility.background(Utility.color(235,  213,  186));
		
		//creates 10 new particles
		createNewParticles(10);
		
		//goes through the particles array
		for (int i = 0; i < particles.length; i++) {
			//if a particle is defined, calls updateParticle
			if (particles[i] != null) {
			updateParticle(i);
			}
		}
		//calls a method to remove particles that have reached a maxAge
		removeOldParticles(maxAge);
	}
	private static void updateParticle(int index) {		
		float velx = 0;
		float vely = 0;
		float posx = 0;
		float posy = 0;
		int particleAge = 0;
		
		//adds gravity effect
		vely = particles[index].getVelocityY() + 0.3f;
		particles[index].setVelocityY(vely);
		
		//gets the initial parameters of the particle
		velx = particles[index].getVelocityX();
		vely = particles[index].getVelocityY();
		posx = particles[index].getPositionX();
		posy = particles[index].getPositionY();
		
		//updates the position of the particle
		float newposx = velx + posx;
		float newposy = vely + posy;
		
		//sets the new positions of the particle
		particles[index].setPositionX(newposx);
		particles[index].setPositionY(newposy);
		
		//determines the look of the particle
		Utility.fill(particles[index].getColor(), particles[index].getTransparency());
		Utility.circle(newposx, newposy, particles[index].getSize());
		
		//increments the age of the particle
		particleAge = particles[index].getAge() + 1;
		particles[index].setAge(particleAge);
		
		
	}
	
	private static void createNewParticles(int newPart) {
		int partcount = 0;
		float newposx;
		float newposy;
		for (int i = 0; i < particles.length; i++) {
			if (particles[i] == null) {
				//x and y position
				newposx = ((5.99f * randGen.nextFloat()) - 3);
				newposy = ((5.99f * randGen.nextFloat()) - 3);
				newposx = newposx + positionX;
				newposy = newposy + positionY;
				
				//sets particle size
				Fountain.partSize = ((6.99f * randGen.nextFloat()) + 4);
				
				//creates a new particle at specified index
				particles[i] = new Particle(newposx, newposy, Fountain.partSize, Utility.lerpColor(startColor, endColor, .5f));
				
				//sets the velocities for the new particle
				particles[i].setVelocityX((1.99f * randGen.nextFloat() - 1));
				particles[i].setVelocityY((4.99f * randGen.nextFloat() - 10));
				
				//sets the age for the particle
				particles[i].setAge(randGen.nextInt(41));
				
				//gives particle specified look
				Fountain.transparentColor= (randGen.nextInt(95) + 32);
				particles[i].setTransparency(Fountain.transparentColor);
				
				//increases count until specified number of particles
				partcount++;
				if (partcount >= newPart) {
					break;
				}
			}
			
			
		}
	}
	
	private static void removeOldParticles(int maxAge) {
		for (int i = 0; i < particles.length; i++) {
			if (particles[i] != null) {
				//pulls the Age of the particle
				int currAge = particles[i].getAge();
				//checks if the age is greater than the max age
				if(currAge > maxAge) {
					//removes particle if true
					particles[i] = null;
				}
			}
		}
	}
	
	public static void mousePressed(int xPosition, int yPosition) {
		//pulls the location of the mouse clicked
		Fountain.positionX = xPosition;
		Fountain.positionY = yPosition;
	}
	
	public static void keyPressed(char key) {
		//checks if 'p' was pressed
		if (key == 'p') {
			//saves a screenshot
			Utility.save("Screenshot.png");
			System.out.println("Screenshot taken");
		}
	}
	

 }
