package com.espen.src;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Controller {

	private LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private LinkedList<Animation> animations = new LinkedList<Animation>();

	private Projectile tempProjectile;
	private Enemy tempEnemy;
	private Animation tempAnimation;

	private Game game;

	private Player player;
	private boolean playerDestroyed = false;

	private Textures textures;

	private int xOffsetBulletLVL1 = Blackboard.XOFFSETLASERLVL1;
	private int xOffsetBulletLVL2 = Blackboard.XOFFSETLASERLVL2;
	private int xOffsetBulletLVL3 = Blackboard.XOFFSETLASERLVL3;

	private boolean gameOver = false;
	private int gameScore = 0;

	public Controller(Game game, Textures textures) {
		this.game = game;
		this.textures = textures;
		player = new Player(200, 200, game, textures);

		spawnWave();
	}

	public void tick() {

		// AnimationTick
		for (int i = 0; i < animations.size(); i++) {
			tempAnimation = animations.get(i);
			tempAnimation.tick();
		}

		if (!gameOver) {

			// PlayerTick
			player.tick();

			// ProjectileTick
			for (int i = 0; i < projectiles.size(); i++) {
				tempProjectile = projectiles.get(i);

				if (tempProjectile.getY() < 0)
					removeProjectile(tempProjectile);

				tempProjectile.tick();
			}

			// EnemyTick
			for (int i = 0; i < enemies.size(); i++) {
				tempEnemy = enemies.get(i);
				tempEnemy.tick();
			}
			if (enemies.size() == 0)
				spawnWave();
			
			//Player Level-Up
			if (gameScore >= 100)
				player.setLevel(2);
			if (gameScore >= 200)
				player.setLevel(3);
		}

	}

	public void render(Graphics g) {

		if (!gameOver) {

			// RenderPlayer
			player.render(g);

			// RenderProjectiles
			for (int i = 0; i < projectiles.size(); i++) {
				tempProjectile = projectiles.get(i);
				tempProjectile.render(g);
			}

			// RenderEnemies
			for (int i = 0; i < enemies.size(); i++) {
				tempEnemy = enemies.get(i);
				tempEnemy.render(g);
			}

			// Render HUD
			g.setFont(new Font("Dialog", Font.BOLD, 16));
			g.drawString("Score: " + gameScore, 20, 20);
			g.drawString("LVL: " + player.getLevel(), 120, 20);
			g.drawString("HEALTH: " + player.getHealth(), 20, 450);
		}

		// RenderAnimations
		for (int i = 0; i < animations.size(); i++) {
			tempAnimation = animations.get(i);
			tempAnimation.render(g);
		}

		if (gameOver) {
			if (!playerDestroyed) {
				game.getController().spawnExplosion01(player.getX(), player.getY());
				playerDestroyed = !playerDestroyed;
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

	public void score(int points) {
		gameScore += points;
	}

	public int getGameScore() {
		return gameScore;
	}

	public Player getPlayer() {
		return player;
	}

	public void spawnBulletLVL1(double x, double y) {
		xOffsetBulletLVL1 = -xOffsetBulletLVL1;
		this.addProjectile(new LaserLVL1(x + xOffsetBulletLVL1, y, game, textures));
	}

	public void spawnBulletLVL2(double x, double y) {
		xOffsetBulletLVL2 = -xOffsetBulletLVL2;
		this.addProjectile(new LaserLVL2(x + xOffsetBulletLVL2, y, game, textures));
	}

	public void spawnBulletLVL3(double x, double y) {
		xOffsetBulletLVL3 = -xOffsetBulletLVL3;
		this.addProjectile(new LaserLVL3(x + xOffsetBulletLVL3, y, game, textures));
	}

	public void spawnEnemyLaserLVL1(double x, double y) {
		this.addProjectile(new EnemyLaserLVL1(x, y + 20, game, textures));
	}

	public void addProjectile(Projectile block) {
		projectiles.add(block);
	}

	public void removeProjectile(Projectile block) {
		projectiles.remove(block);
	}

	public void addEnemy(Enemy block) {
		enemies.add(block);
	}

	public void removeEnemy(Enemy block) {
		enemies.remove(block);

	}

	public void addAnimation(Animation block) {
		animations.add(block);
	}

	public void removeAnimation(Animation block) {
		animations.remove(block);
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public void spawnWave() {
		for (int x = 0; x < (Game.WIDTH * Game.SCALE); x += 64) {
			addEnemy(new Enemy(x, 0, game, textures));
		}
	}

	public void gameOver() {
		gameOver = true;
		enemies.clear();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void spawnExplosion01(double x, double y) {
		this.addAnimation(new Animation(game.getExplosion01(), game, 4, 4, Blackboard.EXPLOSION01SPEED, x, y));
	}

	public void spawnBlueMuzzleFlash(double x, double y) {
		this.addAnimation(new Animation(game.getBlueMuzzleFlash(), game, 4, 1, Blackboard.BLUEMUZZLEFLASHSPEED, x, y));
	}

	public void spawnRedMuzzleFlash(double x, double y) {
		this.addAnimation(new Animation(game.getRedMuzzleFlash(), game, 4, 1, Blackboard.REDMUZZLEFLASHSPEED, x, y));
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(Blackboard.PLAYERSPEED);
			player.setRight(true);

		} else if (key == KeyEvent.VK_LEFT) {
			player.setVelX(-Blackboard.PLAYERSPEED);
			player.setLeft(true);

		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(Blackboard.PLAYERSPEED);
			player.setDown(true);

		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(-Blackboard.PLAYERSPEED);
			player.setUp(true);

		} else if (key == KeyEvent.VK_SPACE) {
			player.setShooting(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			if (player.isLeft()) {
				player.setVelX(-Blackboard.PLAYERSPEED);
			} else {
				player.setVelX(0);
			}
			player.setRight(false);

		} else if (key == KeyEvent.VK_LEFT) {
			if (player.isRight()) {
				player.setVelX(Blackboard.PLAYERSPEED);
			} else {
				player.setVelX(0);
			}
			player.setLeft(false);

		} else if (key == KeyEvent.VK_DOWN) {
			if (player.isUp()) {
				player.setVelY(-Blackboard.PLAYERSPEED);
			} else {
				player.setVelY(0);
			}
			player.setDown(false);

		} else if (key == KeyEvent.VK_UP) {
			if (player.isDown()) {
				player.setVelY(Blackboard.PLAYERSPEED);
			} else {
				player.setVelY(0);
			}
			player.setUp(false);

		} else if (key == KeyEvent.VK_SPACE) {
			player.setShooting(false);
			player.setCount(0);
		}

	}

}
