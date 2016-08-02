package com.espen.src;

import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import com.espen.src.menus.MainMenu;
import com.espen.src.menus.OptionsMenu;

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
	private MainMenu mainMenu;
	private OptionsMenu optionsMenu;

	private enum GameStates {
		MAINMENU, OPTIONSMENU, INGAME, GAMEOVER;
	};

	private GameStates gameState;

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
		player.addWeapon(new LaserGunLVL1(game.getTextures().getPlayerLaserLVL1(), player, game));
		gameState = GameStates.INGAME;
		mainMenu = new MainMenu(this);
		optionsMenu = new OptionsMenu(this);
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

		if (gameState == GameStates.INGAME) {// if the game is not in
												// gameOver-state.

			// call the tick-method in the Player
			player.tick();

			// call the tick-methods in all the projectiles
			for (int i = 0; i < projectiles.size(); i++) {
				tempProjectile = projectiles.get(i);
				tempProjectile.tick();
			}

			// call the tick-methods in all the enemies
			for (int i = 0; i < enemies.size(); i++) {
				tempEnemy = enemies.get(i);
				tempEnemy.tick();
			}
			if (enemies.size() == 0)// if there are no enemies left...
				game.getSpawnSystem().spawnWave();// ...spawn a new wave

			// if the score reaches 100 points...
			if (gameScore >= 100 & player.getLevel() < 2) {
				player.setLevel(2);// ...set the player's level to 2
				player.getWeapons().clear();
				LaserGunLVL2 temp = new LaserGunLVL2(game.getTextures().getPlayerLaserLVL1(), player, game);
				player.getWeapons().add(temp);
			}
			// if the score reaches 200 points...
			if (gameScore >= 200 & player.getLevel() < 3) {
				player.setLevel(3);// ...set the player's level to 3
				player.getWeapons().clear();
				LaserGunLVL3 temp = new LaserGunLVL3(game.getTextures().getPlayerLaserLVL3(), player, game);
				player.getWeapons().add(temp);
			}
			if (gameScore >= 300 & player.getLevel() < 4) {
				player.setLevel(4);
				LaserGunLVL2 temp = new LaserGunLVL2(game.getTextures().getPlayerLaserLVL3(), player, game);
				LaserGunLVL3 temp2 = new LaserGunLVL3(game.getTextures().getPlayerLaserLVL3(), player, game);
				player.getWeapons().add(temp);
				player.getWeapons().add(temp2);
			}
		}

	}

	/**
	 * the rendermethod handles the rendering of all the objects in the game
	 */
	public void render(Graphics g) {

		// render the animations
		for (int i = 0; i < animations.size(); i++) {
			tempAnimation = animations.get(i);
			tempAnimation.render(g);
		}

		if (gameState == GameStates.INGAME) {// if the is game is not in
												// gameover-state

			if (player == null) {// if player is null...
				// ...Set the player. This prevents randomly happening
				// null-pointer-exceptions
				player = game.getEntityManager().getPlayer();
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
		} else if (gameState == GameStates.MAINMENU) {
			mainMenu.render(g);
		} else if (gameState == GameStates.OPTIONSMENU) {
			optionsMenu.render(g);
		} else if (gameState == GameStates.GAMEOVER) {
			if (!playerDestroyed) {// if the player is not destroyed
				// spawn an explosion01 on the player's position
				game.getSpawnSystem().spawnExplosion01(player.getX(), player.getY());
				playerDestroyed = !playerDestroyed;// swap playerDestroyed
			}
			Font standartFont = g.getFont();
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("GAMEOVER!", 220, 200);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Points gained: " + gameScore, 240, 250);
			g.drawString("Level reached: " + player.getLevel(), 240, 300);
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
		gameState = GameStates.GAMEOVER;
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
