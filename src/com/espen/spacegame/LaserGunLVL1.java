package com.espen.spacegame;

import java.awt.image.BufferedImage;

/**
 * Player Lasergun lvl1
 * 
 * @author Dennis
 *
 */
public class LaserGunLVL1 extends Weapon {

	public LaserGunLVL1(BufferedImage[] sprite, Actor parent, Game game) {
		super(sprite, parent, game);
		this.fireRate = Blackboard.PLAYERLASERLVL1RECOVERDURATION;
		this.reloadTime = Blackboard.PLAYERLASERLVL1RELOADDURATION;
		this.shotsPerBurst = Blackboard.PLAYERLASERLVL1SHOTSPERBURST;
		this.xOffset = Blackboard.PLAYERLASERLVL1XOFFSET;
	}

	protected void spawnProjectile() {
		game.getSpawnSystem().spawnPlayerLaserLVL1(x + xOffset, y + yOffset);
	}

}
