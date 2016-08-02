package com.espen.spacegame;

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
	 * @param sprite
	 *            Spritesheet for this character
	 * @param game
	 *            A reference to a game-object to get access to the other
	 *            subsystems
	 * @param x
	 *            x-coordinate of desired spawn-point
	 * @param y
	 *            y-coordinate of desired spawn-point
	 */
	public EnemyLaser(BufferedImage[] sprite, Game game, double x, double y) {
		super(sprite, game, x, y);
		speed = -Blackboard.SPEEDENEMYLASERLVL1; // set speed
		damage = Blackboard.DAMAGEENEMYLASERLVL1; // set damage

	}

	/**
	 * check collision with the player
	 */
	public void checkCollision() {

		if (player == null) { // check if player is null
			// set the player. This prevents randomly happening
			// null-pointer-exceptions
			player = game.getEntityManager().getPlayer();
		}
		// check if the bounds intersect with the player's bounds
		collision = this.getBounds().intersects(player.getBounds());
		if (collision == true) { // if they intersect...
			player.takeDamage(damage); // ...deal damage to the player...
			game.getSpawnSystem().spawnRedLaserImpact(x, y);// ...and spawn an
															// impact animation
			this.destroy();// destroy this projectile by removing it from the
							// Entity-Manager
		}
	}
}
