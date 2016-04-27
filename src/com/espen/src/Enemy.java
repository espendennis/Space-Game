package com.espen.src;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Actor {
	private Textures textures;
	private Player player = null;
	private int count = 100;
	private Random random;

	public Enemy(double x, double y, Game game, Textures textures) {
		this.x = x;
		this.y = y;
		this.textures = textures;
		this.health = 50;
		this.game = game;
		velY = Blackboard.ENEMYSPEED;
		random = new Random();
	}

	public void tick() {
		y += velY;
		if (y > Blackboard.GAMEHEIGHT / 2)
			velY = -velY;
		if (y <= 0)
			velY = -velY;
		if (health <= 0)
			this.destroy();
		shooting();
	}

	public void render(Graphics g) {
		g.drawImage(textures.enemy, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void destroy() {
		game.getController().score(Blackboard.ENEMYPOINTSFORKILL);
		game.getController().spawnExplosion01(x, y);
		game.getController().removeEnemy(this);
	}

	public void shooting() {
		if (player == null) {
			player = game.getController().getPlayer();
		}
		count++;
		if (count > (Blackboard.TICKRATE / Blackboard.ENEMYFIRERATELVL1)) {
			if (150 > (player.getX() - this.x) & (player.getX() - this.x) > -150) {
				if (random.nextInt(2) == 1) {
					game.getController().spawnEnemyLaserLVL1(x, y);
				}
				count = 0;
			}
		}

	}

}
