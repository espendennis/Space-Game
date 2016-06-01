package com.espen.src;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * The game's baseclass for every "Character" in the game.
 * 
 * @author Dennis
 *
 */
public abstract class Actor extends Entity {

	protected int health = 100;
	protected int level = 1;
	protected boolean isShooting = false;

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
	public Actor(BufferedImage image, Game game, int cols, int rows, int animationSpeed, double x, double y) {
		super(image, game, cols, rows, animationSpeed, x, y);
	}

	/**
	 * returns the actor's Level
	 * 
	 * @return integer representing the actor's Level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * set the actor's Level
	 * 
	 * @param level
	 *            the level the actor should be set to.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * returns a boolean which represents if the actor is shooting
	 * 
	 * @return boolean if the actor is shooting
	 */
	public boolean isShooting() {
		return isShooting;
	}

	/**
	 * set if the actor is shooting
	 * 
	 * @param isShooting
	 *            boolean to decide if actor is shooting
	 */
	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	/**
	 * get the health-points of the actor.
	 * 
	 * @return health-points
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Let the actor take damage
	 * 
	 * @param damage
	 *            the value of the damage that should be dealt to the actor
	 */
	public void takeDamage(int damage) {
		health -= damage;
	}

	/**
	 * returns a Rectangle as Bounding-Box for Collision-Control.
	 * 
	 * @return Rectangel representing the Bounds
	 */
	public abstract Rectangle getBounds();

	/**
	 * Destroys the actor by removing it from the Entity-Manager
	 */
	public abstract void destroy();
	

}
