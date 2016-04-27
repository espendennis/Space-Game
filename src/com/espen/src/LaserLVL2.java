package com.espen.src;

public class LaserLVL2 extends Laser implements Projectile {

	public LaserLVL2(double x, double y, Game game, Textures textures) {
		super(x, y, game, textures);
		damage = Blackboard.DAMAGELASERLVL2;
		speed = Blackboard.SPEEDLASERLVL2;
		game.getController().spawnBlueMuzzleFlash(x, y - 15);
	}
}
