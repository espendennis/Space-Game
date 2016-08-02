package com.espen.spacegame;

import java.awt.image.BufferedImage;

/**
 * Player Lasergun lvl1
 * 
 * @author Dennis
 *
 */
public class LaserGunLVL3 extends Weapon {

	public LaserGunLVL3(BufferedImage[] sprite, Actor parent, Game game) {
		super(sprite, parent, game);
		this.fireRate = Blackboard.PLAYERLASERLVL3RECOVERDURATION;
		this.reloadTime = Blackboard.PLAYERLASERLVL3RELOADDURATION;
		this.shotsPerBurst = Blackboard.PLAYERLASERLVL3SHOTSPERBURST;
		this.xOffset = Blackboard.PLAYERLASERLVL3XOFFSET;
		this.yOffset = Blackboard.PLAYERLASERLVL3YOFFSET;

	}

	protected void spawnProjectile() {
		game.getSpawnSystem().spawnPlayerLaserLVL3(x + xOffset, y + yOffset);
	}

}
