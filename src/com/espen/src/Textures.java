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
	private BufferedImage image;
	private BufferedImage background;
	private BufferedImage explosion01;
	private BufferedImage blueMuzzleFlash;
	private BufferedImage redMuzzleFlash;
	private BufferedImage player;
	private BufferedImage enemy;
	private BufferedImage laser;
	private BufferedImage enemyLaser;
	private BufferedImage blueLaserImpact;
	private BufferedImage redLaserImpact;

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
			explosion01 = loader.loadImage("/explosion01.png");
			blueMuzzleFlash = loader.loadImage("/blue_muzzle_flash.png");
			redMuzzleFlash = loader.loadImage("/red_muzzle_flash.png");
			player = loader.loadImage("/player.png");
			enemy = loader.loadImage("/enemy.png");
			laser = loader.loadImage("/laser.png");
			enemyLaser = loader.loadImage("/enemyLaser.png");
			blueLaserImpact = loader.loadImage("/blue_laser_impact.png");
			redLaserImpact = loader.loadImage("/red_laser_impact.png");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters for all the images

	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public BufferedImage getExplosion01() {
		return explosion01;
	}

	public BufferedImage getBlueMuzzleFlash() {
		return blueMuzzleFlash;
	}

	public BufferedImage getRedMuzzleFlash() {
		return redMuzzleFlash;
	}

	public BufferedImage getPlayer() {
		return player;
	}

	public BufferedImage getEnemy() {
		return enemy;
	}

	public BufferedImage getLaser() {
		return laser;
	}

	public BufferedImage getEnemyLaser() {
		return enemyLaser;
	}

	public BufferedImage getBlueLaserImpact() {
		return blueLaserImpact;
	}

	public BufferedImage getRedLaserImpact() {
		return redLaserImpact;
	}
}
