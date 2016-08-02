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
	protected int currentFrameOfAnimation = 0;
	protected Game game;
	protected Controller controller = null;

	protected Textures textures;
	protected BufferedImage[] sprite;
	protected boolean loopingAnimation = true;

	/**
	 * Constructor
	 * 
	 * @param sprite
	 *            Spritesheet for this entity
	 * @param game
	 *            A reference to a game-object to get access to the other
	 *            subsystems
	 * @param x
	 *            x-coordinate of desired spawn-point
	 * @param y
	 *            y-coordinate of desired spawn-point
	 */
	public Entity(BufferedImage[] sprite, Game game, double x, double y) {
		this.animationSpeed = Blackboard.STANDARTANIMATIONSPEED;
		this.game = game;
		this.sprite = sprite;
		this.x = x;
		this.y = y;

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
		x += velX; // update the x-position with the x-velocity
		y += velY; // update the y-position with the y-velocity
		tickcount++; // increment tickcount
		// if tickcount reaches the desired amount of ticks between
		// animationupdates...
		if (tickcount >= animationSpeed) {
			currentFrameOfAnimation++; // ...switch to the next frame
			tickcount = 0; // reset tickcount
		}
		if (currentFrameOfAnimation >= sprite.length) {// if the frameindex
														// exceeds
			// the size of the BufferedImage
			// Array holding the sprites...
			if (loopingAnimation) { // ...and if the animation i set to loop...
				currentFrameOfAnimation = 0; // ...reset the index
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
		// draw the current sprite
		g.drawImage(sprite[currentFrameOfAnimation], (int) x, (int) y, null);

	}

	/**
	 * abstract method to destroy the entity
	 */
	public abstract void destroy();
}
