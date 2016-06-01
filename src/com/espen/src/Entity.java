package com.espen.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Class for every object in the game. This abstract class implements the basic
 * fields and methods for every object. Some of the methods need more specific
 * implementations depending on the role in the game. Due to this the class is
 * abstract.
 *
 * @author Dennis
 *
 */
public abstract class Entity {

	protected double x;
	protected double y;
	protected double speed;
	protected double animationSpeed;
	protected double velX;
	protected double velY;
	protected double imageSize;
	protected int tickcount = 0;
	protected int animationIndex = 0;
	protected Game game;
	protected Controller controller = null;

	protected Textures textures;
	protected BufferedImage[] sprite;
	protected boolean loopingAnimation = true;
	private int count = 0;
	private int count2 = 0;

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
	public Entity(BufferedImage image, Game game, int cols, int rows, int animationSpeed, double x, double y) {
		this.animationSpeed = animationSpeed;
		this.count = cols * rows;
		this.game = game;
		sprite = new BufferedImage[count];
		this.x = x;
		this.y = y;
		// Fill the BufferedImage Array for the animation with subimages from
		// the spritesheet
		for (int i = 1; i <= rows; i++)
			for (int j = 1; j <= cols; j++) {
				sprite[count2] = image.getSubimage((j * 32) - 32, (i * 32) - 32, 32, 32);
				count2++;
			}

	}

	/**
	 * returns the x-position
	 * 
	 * @return x-position
	 */
	public double getX() {
		return x;
	}

	/**
	 * returns the y-position
	 * 
	 * @return y-position
	 */
	public double getY() {
		return y;
	}

	/**
	 * sets the x-velocity
	 * 
	 * @param velX
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * sets the y-velocity
	 * 
	 * @param velX
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

	/**
	 * the tick-method handles all updates for the entity
	 */
	public void tick() {
		x += velX;	//update the x-position with the x-velocity
		y += velY;	//update the y-position with the y-velocity
		tickcount++; // increment tickcount
		if (tickcount >= animationSpeed) { // if tickcount reaches the desired
											// of ticks between
											// animationupdates...
			animationIndex++; // ...increment the animation index
			tickcount = 0; // reset tickcount
		}
		if (animationIndex >= sprite.length) {// if the animationIndex exceeds
												// the size of the BufferedImage
												// Array holding the sprites...
			if (loopingAnimation) { // ...and if the animation i set to loop...
				animationIndex = 0; // ...reset the index
				// else-case is used for the animation-class
			} else { // if the animation is not set to loop
				destroy(); // destroy the entity
			}

		}
	}

	/**
	 * the render method draws the entity into the buffer
	 * 
	 * @param g
	 *            Graphics object used to draw the entity
	 */
	public void render(Graphics g) {

		g.drawImage(sprite[animationIndex], (int) x, (int) y, null); // draw the
																		// current
																		// sprite

	}

	/**
	 * abstract mathod to destroy the entity
	 */
	public abstract void destroy();

}
