package com.espen.src;

public class LaserLVL1 extends Laser implements Projectile {

	public LaserLVL1(double x, double y, Game game, Textures textures) {
		super(x, y, game, textures);
		damage = Blackboard.DAMAGELASSERLVL1;
		speed = Blackboard.SPEEDLASERLVL1;
		game.getController().spawnBlueMuzzleFlash(x, y - 15);
	}
}
