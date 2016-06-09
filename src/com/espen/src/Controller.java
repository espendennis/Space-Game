package com.espen.src;

import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * This method controlls the whole game. It calls the tick- and render-methods
 * for every object in the game. It also controls the player's score, the
 * keyinputs. Additional it handles a GameOver.
 * 
 * @author Dennis
 *
 */
public class Controller {

	private LinkedList<Projectile> projectiles;
	private LinkedList<Enemy> enemies;
	private LinkedList<Animation> animations;

	private Projectile tempProjectile;
	private Enemy tempEnemy;
	private Animation tempAnimation;

	private Game game;
	private Player player;

	private boolean playerDestroyed = false;

	private boolean gameOver = false;
	private int gameScore = 0;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            a reference to game to access the other subsystems
	 */
	public Controller(Game game) {
		this.game = game;
		game.getSpawnSystem().spawnPlayer(200, 200);// spawn the player
		game.getSpawnSystem().spawnWave();// spawn an initial wave of enemies
		this.player = game.getEntityManager().getPlayer();
		projectiles = game.getEntityManager().getProjectiles();
		enemies = game.getEntityManager().getEnemies();
		animations = game.getEntityManager().getAnimations();
	}

	/**
	 * the tickmethod handles all the updates in the game
	 */
	public void tick() {

		// call the tick-methods in all the animations
		for (int i = 0; i < animations.size(); i++) {
			tempAnimation = animations.get(i);
			tempAnimation.tick();
		}

		if (!gameOver) {// if the game is not in gameOver-state.

			// call the tick-method in the Player
			player.tick();

			// call the tick-methods in all the projectiles
			for (int i = 0; i < projectiles.size(); i++) {
				tempProjectile = projectiles.get(i);

				// if (tempProjectile.getY() < 0)
				// game.getEntityManager().getProjectiles().remove(tempProjectile);

				tempProjectile.tick();
			}

			// call the tick-methods in all the enemies
			for (int i = 0; i < enemies.size(); i++) {
				tempEnemy = enemies.get(i);
				tempEnemy.tick();
			}
			if (enemies.size() == 0)// if there are no enemies left...
				game.getSpawnSystem().spawnWave();// ...spawn a new wave

			if (gameScore >= 100)// if the score reaches 100 points...
				player.setLevel(2);// ...set the player's to 2
			if (gameScore >= 200)// if the score reaches 200 points...
				player.setLevel(3);// ...set the player's to 3
		}

	}

	/**
	 * the rendermethod handles the rendering of all the objects in the game
	 */
	public void render(Graphics g) {

		if (!gameOver) {// if the is game is not in gameover-state

			if (player == null) {// if player is null...
				player = game.getEntityManager().getPlayer();// ...Set the
																// player. This
																// prevents
																// randomly
																// happening
																// null-pointer-exceptions
			}
			// render the player
			player.render(g);

			// render the projectiles
			for (int i = 0; i < projectiles.size(); i++) {
				tempProjectile = projectiles.get(i);
				tempProjectile.render(g);
			}

			// render the enemies
			for (int i = 0; i < enemies.size(); i++) {
				tempEnemy = enemies.get(i);
				tempEnemy.render(g);
			}

			// render the HUD
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.drawString("Score: " + gameScore, 20, 20);
			g.drawString("LVL: " + player.getLevel(), 120, 20);
			g.drawString("HEALTH: " + player.getHealth(), 20, 450);
		}

		// render the animations
		for (int i = 0; i < animations.size(); i++) {
			tempAnimation = animations.get(i);
			tempAnimation.render(g);
		}

		if (gameOver) {// if the game is in gameover state
			if (!playerDestroyed) {// if the player is not destroyed
				game.getSpawnSystem().spawnExplosion01(player.getX(), player.getY());// spawn
																						// an
																						// explosion01
																						// on
																						// the
																						// player's
																						// position
				playerDestroyed = !playerDestroyed;// swap playerDestroyed
			}

			Font standartFont = g.getFont();
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("GAMEOVER!", 220, 250);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Points gained: " + gameScore, 240, 300);
			g.drawString("Level reached: " + player.getLevel(), 240, 350);
			g.setFont(standartFont);

		}

	}

	/**
	 * add score
	 * 
	 * @param points
	 *            how much points should be added to the score
	 */
	public void score(int points) {
		gameScore += points;
	}

	/**
	 * get the score
	 * 
	 * @return score
	 */
	public int getGameScore() {
		return gameScore;
	}

	/**
	 * set the game to gameover state
	 */
	public void gameOver() {
		gameOver = true;
		enemies.clear();// remove all enemies
	}

	/**
	 * check if the game is in gameover state
	 * 
	 * @return if the game is in gameover state
	 */
	public boolean isGameOver() {
		return gameOver;
	}

}
