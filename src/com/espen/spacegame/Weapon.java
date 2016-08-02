package com.espen.spacegame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Weapon {

	protected Actor parent;
	protected Game game;
	protected double x;
	protected double y;
	protected int fireRate = 5;
	protected int firecount = 0;
	protected int reloadTime = 20;
	protected int reloadCount = 0;
	protected int shotsPerBurst = 3;
	protected int shotsCount = 0;
	protected int animationTickCount = 0;
	protected int xOffset = 0;
	protected int yOffset = 0;
	protected double animationSpeed = 10;
	protected boolean shootingAnimation = false;
	protected boolean shooting = false;
	protected boolean recovering = false;
	protected boolean reloading = false;
	protected BufferedImage[] sprite;
	protected int currentFrameOfAnimation = 0;

	public Weapon(BufferedImage[] sprite, Actor parent, Game game) {
		this.parent = parent;
		this.game = game;
		this.sprite = sprite;
		this.fireRate = Blackboard.PLAYERLASERLVL1RECOVERDURATION;
		this.reloadTime = Blackboard.PLAYERLASERLVL1RELOADDURATION;
		this.shotsPerBurst = Blackboard.PLAYERLASERLVL1SHOTSPERBURST;
	}


	public void tick() {
		this.x = parent.x;
		this.y = parent.y;
		if(parent.isShooting)
			shooting = true;

		if (recovering)
			firecount++;
		if (firecount > fireRate) {
			recovering = false;
			firecount = 0;
		}

		if (reloading)
			this.reloadCount++;
		if (this.reloadCount >= this.reloadTime){
			this.reloading = false;
			reloadCount = 0;
			shooting = false;
		}
		if (shooting & !recovering & !reloading) {
			spawnProjectile();
			shootingAnimation = true;
			shotsCount++;
			xOffset = -xOffset;
			recovering = true;
			if (shotsCount >= shotsPerBurst) {
				reloading = true;
				shooting = false;
				shotsCount = 0;
			}
		}

		if (shootingAnimation) {
			animationTickCount++;
			if (animationTickCount >= animationSpeed) {
				currentFrameOfAnimation++;
				if (currentFrameOfAnimation >= sprite.length) {
					currentFrameOfAnimation = 0;
					shootingAnimation = false;
				}
			}

		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite[currentFrameOfAnimation], (int) x, (int) y, null);

	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public void setShotsPerBurst(int shotsPerBurst) {
		this.shotsPerBurst = shotsPerBurst;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	protected abstract void spawnProjectile();

}
