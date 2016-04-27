package com.espen.src;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Laser implements Projectile {

	protected double x;
	protected double y;
	protected double speed;
	protected Game game;
	protected int damage;
	protected boolean collision = false;
	protected Enemy enemy;

	protected BufferedImage image;

	protected LinkedList<Enemy> enemies = null;

	public Laser(double x, double y, Game game, Textures textures) {
		this.x = x;
		this.y = y;
		this.game = game;
		image = textures.playerLaser;
		damage = Blackboard.DAMAGELASSERLVL1;
		speed = Blackboard.SPEEDLASERLVL1;
	}

	public void tick() {
		y -= speed;
		checkCollision();
	}

	public void render(Graphics g) {
		g.drawImage(image, (int) x, (int) y, null);

	}

	public double getY() {
		return y;
	}

	public int getDamage() {
		return damage;
	}

	public Rectangle getBounds() {
		return new Rectangle(((int) x) + 16, ((int) y) + 6, 1, 8);
	}

	public void destroy() {
		game.getController().removeProjectile(this);
	}

	public void checkCollision() {
		enemies = game.getController().getEnemies();
		for (int i = 0; i < enemies.size(); i++) {
			enemy = enemies.get(i);
			collision = this.getBounds().intersects(enemy.getBounds());
			if (collision == true) {
				enemy.takeDamage(damage);
				this.destroy();
			}
		}
	}

}
