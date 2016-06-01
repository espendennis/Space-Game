package com.espen.src;

import java.awt.image.BufferedImage;

/**
 * extends Laser and is used for enemy laser-projectiles
 * 
 * @author Dennis
 *
 */
public class EnemyLaser extends PlayerLaser {

	private Player player = null;

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
	public EnemyLaser(BufferedImage image, Game game, int cols, int rows, int animationSpeed, double x, double y) {
		super(image, game, cols, rows, animationSpeed, x, y);
		speed = -Blackboard.SPEEDENEMYLASERLVL1; // set speed
		damage = Blackboard.DAMAGEENEMYLASERLVL1; // set damage

	}

	/**
	 * check collision with the player
	 */
	public void checkCollision() {

		if (player == null) { // check if player is null
			player = game.getEntityManager().getPlayer();// set the player. This
														// prevents randomly
														// happening
														// null-pointer-exceptions
		}
		collision = this.getBounds().intersects(player.getBounds()); // check if
																		// the
																		// bounds
																		// intersect
																		// with
																		// the
																		// player's
																		// bounds
		if (collision == true) { // if they intersect...
			player.takeDamage(damage); // ...deal damage to the player...
			game.getSpawnSystem().spawnRedLaserImpact(x, y);// ...and spawn an
															// impact animation
			this.destroy();// destroy this projectile by removing it from the
							// Entity-Manager
		}
	}
}
