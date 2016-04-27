package com.espen.src;

public class LaserLVL3 extends Laser implements Projectile {

	public LaserLVL3(double x, double y, Game game, Textures textures) {
		super(x, y, game, textures);
		damage = Blackboard.DAMAGELASERLVL3;
		speed = Blackboard.SPEEDLASERLVL3;
		game.getController().spawnBlueMuzzleFlash(x, y);
	}
}
