package com.espen.src;

public class EnemyLaserLVL1 extends Laser implements Projectile {

	private Player player = null;

	public EnemyLaserLVL1(double x, double y, Game game, Textures textures) {
		super(x, y, game, textures);
		image = textures.enemyLaser;
		speed = -Blackboard.SPEEDENEMYLASERLVL1;
		damage = Blackboard.DAMAGEENEMYLASERLVL1;
		game.getController().spawnRedMuzzleFlash(x - 0, y);
	}

	public void checkCollision() {

		if (player == null) {
			player = game.getController().getPlayer();
		}
		collision = this.getBounds().intersects(player.getBounds());
		if (collision == true) {
			player.takeDamage(damage);
			this.destroy();
		}
	}
}
