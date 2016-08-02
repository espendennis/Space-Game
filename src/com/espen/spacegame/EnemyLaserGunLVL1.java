package com.espen.spacegame;

import java.awt.image.BufferedImage;
/**
 * 
 * EnemyLaser Lvl1
 * 
 * @author Dennis
 *
 */
public class EnemyLaserGunLVL1 extends Weapon{

	public EnemyLaserGunLVL1(BufferedImage[] sprite, Actor parent, Game game) {
		super(sprite, parent, game);
		this.fireRate = Blackboard.ENEMYLASERLVL1RECOVERDURATION;
		this.reloadTime = Blackboard.ENEMYLASERLVL1RELOADDURATION;
		this.shotsPerBurst = Blackboard.ENEMYLASERLVL1SHOTSPERBURST;
		this.xOffset = Blackboard.ENEMYLASERLVL1XOFFSET;
		this.yOffset = Blackboard.ENEMYLASERLVL1YOFFSET;
	}

	@Override
	protected void spawnProjectile() {
		game.getSpawnSystem().spawnEnemyLaserLVL1(x + xOffset, y + yOffset);
	}

}
