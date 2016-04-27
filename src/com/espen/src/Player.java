package com.espen.src;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Actor {

	private BufferedImage player;
	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	private int count = 100;

	public Player(double x, double y, Game game, Textures textures) {
		this.x = x;
		this.y = y;
		this.game = game;
		player = textures.player;
		this.setLevel(3);
		health = 100;
		controller = game.getController();
	}

	public void tick() {
		x += velX;
		y += velY;
		if (x <= 0)
			x = 0;
		if (x >= game.getWidth() - player.getWidth())
			x = game.getWidth() - player.getWidth();
		if (y <= 0)
			y = 0;
		if (y >= game.getHeight() - player.getHeight())
			y = game.getHeight() - player.getHeight();
		if (isShooting) {
			count++;
			if (level == 1) {
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL1) {
					this.fireBulletLVL1();
					count = 0;
				}
			}
			if (level == 2) {
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL2) {
					this.fireBulletLVL2();
					count = 0;
				}
			}
			if (level == 3) {
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL3) {
					this.fireBulletLVL3();
					count = 0;
				}
			}
		}
		if (!isShooting) {
			count = 100;
		}
		if (health <= 0) {
			controller.gameOver();
		}
	}

	public void render(Graphics g) {
		if (controller == null)
			controller = game.getController();
		if (!controller.isGameOver())
			g.drawImage(player, (int) x, (int) y, null);

	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public double getSpeed() {
		return speed;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void fireBulletLVL1() {
		controller.spawnBulletLVL1(x, y);
	}

	public void fireBulletLVL2() {
		controller.spawnBulletLVL2(x, y);
	}

	public void fireBulletLVL3() {
		controller.spawnBulletLVL3(x, y);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void destroy() {

	}
}
