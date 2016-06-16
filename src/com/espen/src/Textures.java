package com.espen.src;

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

	/**
	 * Constructor
	 * 
	 * @param game
	 *            a reference to game to access other subsystems
	 */
	public Textures(Game game) {

		BufferedImageLoader loader = new BufferedImageLoader(); // instantiate
																// the
																// utility-class
																// for loading
																// images
		try {
			image = new BufferedImage(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT, BufferedImage.TYPE_INT_RGB); // image
																												// to
																												// clear
																												// the
																												// screen
																												// before
																												// painting
																												// the
																												// buffer
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
}
