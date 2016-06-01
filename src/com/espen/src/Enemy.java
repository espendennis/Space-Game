package com.espen.src;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Inherits from Actor and is basically used to create enemies
 * 
 * @author Dennis
 *
 */
public class Enemy extends Actor {

	private Player player = null;
	private int count = 100;
	private Random random;

	/**
	 * Constructor
	 * 
	 * @param image
	 *            Spritesheet for this character
	 * @param game
	 *            A reference to a game-object to get access to the other
	 *            subsystems
	 * @param cols
	 *            number of columns the animation takes in the spritesheet
	 * @param rows
	 *            number of rows the animation takes in the spritesheet
	 * @param animationSpeed
	 *            how many ticks between the animation jumps to the next
	 *            subimage
	 * @param x
	 *            x-coordinate of desired spawn-point
	 * @param y
	 *            y-coordinate of desired spawn-point
	 */
	public Enemy(BufferedImage image, Game game, int cols, int rows, int animationSpeed, double x, double y) {
		super(image, game, cols, rows, animationSpeed, x, y);
		velY = Blackboard.ENEMYSPEEDLVL1; // Player moves downwards after beeing
											// spawned
		random = new Random(); // Random-Class for randomizing shooting in the
								// shooting method
	}

	/**
	 * the tickmethod handles every update for this character
	 */
	public void tick() {
		super.tick(); // calls the superclass's tickmethod. this is implemented
						// in the Entity-class and inherited by this classes'
						// superclass Actor
		if (y > Blackboard.GAMEHEIGHT / 2)// if the enemy reaches the horizontal
											// middle of the screen...
			velY = -velY; // ... the enemy changes direction vertically
		if (y <= 0) // if the enemy reaches the top of the screen...
			velY = -velY; // ... the enemy changes direction vertically
		if (health <= 0) // if the health drops to 0 or lower...
			this.destroy(); // ...the enemy is destroyed
		shooting(); // call the shooting-method

	}

	/**
	 * returns a Rectangle which represent the enemy's boundaries for
	 * collision-controll
	 * 
	 * @return Rectangle representing the enemy boundaries
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * destroys the enemy by removing it from the Entity-Controller. Spawns an
	 * explosion-animation at the enemy's location. Adds points for the kill
	 * 
	 */
	public void destroy() {
		game.getController().score(Blackboard.ENEMYPOINTSFORKILLLVL1);// add
																		// score
		game.getSpawnSystem().spawnExplosion01(x, y); // spawn explosion
		game.getEntityManager().getEnemies().remove(this); // remove from
															// Entity-Manager
	}

	/*
	 * this method is called every tick. It checks if the player's position in
	 * x-direction is close to the enemy. If it is close a random-generator
	 * decides if a shot is triggered or not. In 50% of the cases the enemy
	 * shoots
	 */
	private void shooting() {
		if (player == null) { // check if player is null
			player = game.getEntityManager().getPlayer(); // set the player. This
														// prevents randomly
														// happening
														// null-pointer-exceptions
		}
		count++; // increment null every tick
		if (count > (Blackboard.TICKRATE / Blackboard.ENEMYFIRERATELVL1)) {// if
																			// count
																			// reaches
																			// number
																			// of
																			// ticks
																			// between
																			// two
																			// possible
																			// shots...
			if (150 > (player.getX() - this.x) & (player.getX() - this.x) > -150) {// ...check
																					// if
																					// the
																					// players
																					// x-position
																					// is
																					// close
																					// to
																					// the
																					// enemy
				if (random.nextInt(2) == 1) { // randomly in 50% of the cases...
					game.getSpawnSystem().spawnEnemyLaserLVL1(x, y); // ...spawn
																		// a
																		// shot
				}
				count = 0; // reset count
			}
		}

	}

}
