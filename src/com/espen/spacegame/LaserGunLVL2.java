package com.espen.spacegame;

import java.awt.image.BufferedImage;

/**
 * Player Lasergun lvl2
 * 
 * @author Dennis
 *
 */
public class LaserGunLVL2 extends Weapon {

	public LaserGunLVL2(BufferedImage[] sprite, Actor parent, Game game) {
		super(sprite, parent, game);
		this.fireRate = Blackboard.PLAYERLASERLVL2RECOVERDURATION;
		this.reloadTime = Blackboard.PLAYERLASERLVL2RELOADDURATION;
		this.shotsPerBurst = Blackboard.PLAYERLASERLVL2SHOTSPERBURST;
		this.xOffset = Blackboard.PLAYERLASERLVL2XOFFSET;
		this.yOffset = Blackboard.PLAYERLASERLVL2YOFFSET;

	}

	@Override

	protected void spawnProjectile() {
		game.getSpawnSystem().spawnPlayerLaserLVL2(x + xOffset, y + yOffset);
	}

}
