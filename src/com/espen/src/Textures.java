package com.espen.src;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage player, playerLaser, enemy, enemyLaser;

	private SpriteSheet spriteSheet = null;

	public Textures(Game game) {
		spriteSheet = new SpriteSheet(game.getSpriteSheet());

		getTextures();
	}

	private void getTextures() {
		player = spriteSheet.grabImage(1, 1, 32, 32);
		playerLaser = spriteSheet.grabImage(2, 1, 32, 32);
		enemy = spriteSheet.grabImage(3, 1, 32, 32);
		enemyLaser = spriteSheet.grabImage(4, 1, 32, 32);

	}

}
