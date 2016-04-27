package com.espen.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	private double x;
	private double y;
	private int maxCols;
	private int actualCol = 1;
	private int actualRow = 1;
	private int count;
	private int size;
	private int speed;
	private int tickcount = 0;
	private int animationIndex = 0;
	private SpriteSheet spriteSheet = null;
	private BufferedImage[] sprite = null;
	private Game game;

	public Animation(BufferedImage image, Game game, int cols, int rows, int speed, double x, double y) {
		this.maxCols = cols;
		this.speed = speed;
		this.count = cols * rows;
		this.game = game;
		size = image.getWidth() / maxCols;
		spriteSheet = new SpriteSheet(image);
		sprite = new BufferedImage[count];
		this.x = x;
		this.y = y;

		for (int i = 0; i < count; i++) {
			sprite[i] = spriteSheet.grabImage(actualCol, actualRow, size, size);
			actualCol++;
			if (actualCol > maxCols) {
				actualCol = 1;
				actualRow++;
			}
		}

	}

	public void tick() {

		tickcount++;
		if (tickcount >= speed) {
			animationIndex++;
			tickcount = 0;
		}
		if (animationIndex >= sprite.length) {
			destroy();
		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite[animationIndex], (int) x, (int) y, null);
	}

	public void destroy() {
		game.getController().removeAnimation(this);
	}

}
