package com.espen.spacegame;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class that handles all textures
 * 
 * @author Dennis
 *
 */
public class Textures {
	private BufferedImage background;
	private BufferedImage image;
	private BufferedImage[] explosion01;
	private BufferedImage[] blueMuzzleFlash;
	private BufferedImage[] redMuzzleFlash;
	private BufferedImage[] player;
	private BufferedImage[] enemy;
	private BufferedImage[] laser;
	private BufferedImage[] enemyLaser;
	private BufferedImage[] blueLaserImpact;
	private BufferedImage[] redLaserImpact;
	private BufferedImage[] playerLaserLVL1;
	private BufferedImage[] playerLaserLVL3;
	private BufferedImage[] enemyLaserLVL1;
	
	/**
	 * Constructor
	 * 
	 * @param game
	 *            a reference to game to access other subsystems
	 */
	public Textures(Game game) {

		// instantiate the utility-class for loading images
		BufferedImageLoader loader = new BufferedImageLoader(); 
		try {
			// image to clear the screen before painting the buffer
			image = new BufferedImage(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT, BufferedImage.TYPE_INT_RGB); 
			background = loader.loadImage("/background.png"); // background
																// image
			explosion01 = loader.loadSprite("/explosion01.png", 4, 4, 32, 32);
			blueMuzzleFlash = loader.loadSprite("/blue_muzzle_flash.png", 4, 1, 32, 32);
			redMuzzleFlash = loader.loadSprite("/red_muzzle_flash.png", 4, 1, 32, 32);
			player = loader.loadSprite("/player.png", 2, 1, 32, 32);
			enemy = loader.loadSprite("/enemy.png", 2, 1, 32, 32);
			laser = loader.loadSprite("/laser.png", 1, 1, 32, 32);
			enemyLaser = loader.loadSprite("/enemyLaser.png", 1, 1, 32, 32);
			blueLaserImpact = loader.loadSprite("/blue_laser_impact.png", 2, 2, 32, 32);
			redLaserImpact = loader.loadSprite("/red_laser_impact.png", 2, 2, 32, 32);
			playerLaserLVL1 = loader.loadSprite("/player_laserLVL1.png", 2, 1, 32, 32);
			playerLaserLVL3 = loader.loadSprite("/player_laserLVL3.png", 4, 1, 32, 32);
			enemyLaserLVL1 = loader.loadSprite("/enemy_laserLVL1.png", 2, 1, 32, 32);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters for all the images and sprites

	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public BufferedImage[] getExplosion01() {
		return explosion01;
	}

	public BufferedImage[] getBlueMuzzleFlash() {
		return blueMuzzleFlash;
	}

	public BufferedImage[] getRedMuzzleFlash() {
		return redMuzzleFlash;
	}

	public BufferedImage[] getPlayer() {
		return player;
	}

	public BufferedImage[] getEnemy() {
		return enemy;
	}

	public BufferedImage[] getLaser() {
		return laser;
	}

	public BufferedImage[] getEnemyLaser() {
		return enemyLaser;
	}

	public BufferedImage[] getBlueLaserImpact() {
		return blueLaserImpact;
	}

	public BufferedImage[] getRedLaserImpact() {
		return redLaserImpact;
	}

	public BufferedImage[] getPlayerLaserLVL1() {
		return playerLaserLVL1;
	}

	public BufferedImage[] getPlayerLaserLVL3() {
		return playerLaserLVL3;
	}

	public BufferedImage[] getEnemyLaserLVL1() {
		return enemyLaserLVL1;
	}
}
