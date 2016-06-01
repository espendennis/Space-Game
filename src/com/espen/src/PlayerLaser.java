package com.espen.src;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * Class for laserprojectiles shot by the player
 * 
 * @author Dennis
 *
 */
public class PlayerLaser extends Entity implements Projectile {

	protected double speed;
	protected int damage;
	protected boolean collision = false;
	protected Enemy enemy;

	protected BufferedImage image;

	protected LinkedList<Enemy> enemies = null;

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
	public PlayerLaser(BufferedImage image, Game game, int cols, int rows, int animationSpeed, double x, double y) {
		super(image, game, cols, rows, animationSpeed, x, y);
		damage = Blackboard.DAMAGELASSERLVL1; // set damage
		speed = Blackboard.SPEEDLASERLVL1; // set speed
	}

	public void tick() {
		y -= speed; // update y-position by speed
		checkCollision(); // call collisioncheck
		if (this.getY() > (Blackboard.GAMEHEIGHT + 32) || this.getY() < -32) // if
																				// a
																				// projectile
																				// exits
																				// the
																				// screen...
			this.destroy(); // ...destroy it

	}

	/**
	 * get the current y-position
	 * 
	 * @return current y-position
	 */
	public double getY() {
		return y;
	}

	/**
	 * returns the damage dealt by this projectile on impact
	 * 
	 * @returns damage dealt by this projectile on impact
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * returns boundaries for this projectile
	 * 
	 * @return rectangle representing boundaries for this projectile
	 */
	public Rectangle getBounds() {
		return new Rectangle(((int) x) + 16, ((int) y) + 6, 1, 8);
	}

	/**
	 * destroy this projectile by removing it from the Entity-Manager
	 */
	public void destroy() {
		game.getEntityManager().getProjectiles().remove(this);
	}

	/**
	 * check collision
	 */
	public void checkCollision() {
		if (game == null) {// if game is null...
			System.out.println("Game null");// ...set game. This prevents random
											// nullpointer-exceptions
		}
		enemies = game.getEntityManager().getEnemies(); // get the LinkedList
														// with all enemies from
														// the Entity-Manager
		for (int i = 0; i < enemies.size(); i++) { // for every enemy in the
													// list
			enemy = enemies.get(i); // get enemy
			collision = this.getBounds().intersects(enemy.getBounds());// check
																		// if
																		// bounds
																		// intersect
			if (collision == true) {
				game.getSpawnSystem().spawnBlueLaserImpact(x, y);// spawn
																	// impact-animation
				enemy.takeDamage(damage);// deal damage
				this.destroy();// destroy projectile
			}
		}
	}

	/**
	 * set the projectiles' speed
	 * 
	 * @param speed
	 *            the projectiles' speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * set the damage dealt by this projectile on impact
	 * 
	 * @param damage
	 *            damage dealt by this projectile on impact
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
