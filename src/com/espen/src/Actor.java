package com.espen.src;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Actor {

	protected double x;
	protected double y;
	protected double speed;
	protected double velX;
	protected double velY;
	protected int level;
	protected Game game;
	protected Controller controller = null;
	protected boolean isShooting = false;
	protected int health;
	protected Textures textures;

	public Actor() {
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public int getHealth() {
		return health;
	}

	public void takeDamage(int damage) {
		health -= damage;
	}

	public abstract Rectangle getBounds();

	public abstract void destroy();

	public abstract void tick();

	public abstract void render(Graphics g);

}
