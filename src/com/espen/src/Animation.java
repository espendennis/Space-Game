package com.espen.src;

import java.awt.image.BufferedImage;

/**
 * This Class is a special case of the Entity-Class. It is used for Animations
 * which are spawned into the world, get drawn and are destroyed after one
 * animation-loop. At this state of the game this class is only used for
 * Particles.
 * 
 * @author Dennis
 *
 */
public class Animation extends Entity {
	/**
	 * Constructor
	 * 
	 * @param sprite
	 *            Spritesheet for this animation
	 * @param game
	 *            A reference to a game-object to get access to the other
	 *            subsystems
	 * @param x
	 *            x-coordinate of desired spawn-point
	 * @param y
	 *            y-coordinate of desired spawn-point
	 */
	public Animation(BufferedImage[] sprite, Game game, double x, double y) {
		super(sprite, game, x, y);
		this.loopingAnimation = false;
	}

	/**
	 * Destroys the animation by removing it from the Entity-Controller
	 */
	public void destroy() {
		game.getEntityManager().getAnimations().remove(this);
	}

}
