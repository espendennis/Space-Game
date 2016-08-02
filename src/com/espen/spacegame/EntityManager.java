package com.espen.spacegame;

import java.util.LinkedList;

/**
 * this class is used to hold every Entity in the game.
 * 
 * @author Dennis
 *
 */
public class EntityManager {

	Game game;

	Player player;
	private LinkedList<Projectile> projectiles;
	private LinkedList<Enemy> enemies;
	private LinkedList<Animation> animations;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            a reference to game to get access to the other subsystems
	 */
	public EntityManager(Game game) {
		this.game = game;
		projectiles = new LinkedList<Projectile>();
		enemies = new LinkedList<Enemy>();
		animations = new LinkedList<Animation>();
	}

	/**
	 * returns a reference to the LinkedList holding all projectiles
	 * 
	 * @return LinkedList holding all projectiles
	 */
	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * returns a reference to the LinkedList holding all enemies
	 * 
	 * @return LinkedList holding all enemies
	 */
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * returns a reference to the LinkedList holding all animations
	 * 
	 * @return LinkedList holding all animations
	 */
	public LinkedList<Animation> getAnimations() {
		return animations;
	}

	/**
	 * returns a reference to the player
	 * 
	 * @return reference to the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * set the player
	 * 
	 * @param player
	 *            reference to the player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

}
