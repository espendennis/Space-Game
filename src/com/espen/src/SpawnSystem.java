package com.espen.src;

/**
 * Class responsible for spawning entities into the game
 * 
 * @author Dennis
 *
 */
public class SpawnSystem {

	Game game;
	Player player;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            reference to game to access the other subsystems
	 */
	public SpawnSystem(Game game) {
		this.game = game;
	}

	/**
	 * Spawn the player
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnPlayer(double x, double y) {
		game.getEntityManager().setPlayer(new Player(game, 200, 200));
	}

	/**
	 * spawn a projectile for a player laser LVL1
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnPlayerLaserLVL1(double x, double y) {
		// Instantiate a temporary Projectile
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x, y);
		temp.setDamage(Blackboard.DAMAGELASSERLVL1);// set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL1);// set the speed
		// spawn the projectile
		game.getEntityManager().getProjectiles().add(temp);
		// spawn a muzzleflash
		game.getSpawnSystem().spawnBlueMuzzleFlash(x, y - 15);

	}

	/**
	 * spawn a projectile for a player laser LVL2
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnPlayerLaserLVL2(double x, double y) {
		// Instantiate a temporary Projectile
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x, y);
		temp.setDamage(Blackboard.DAMAGELASERLVL2); // set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL2); // set the speed
		// spawn the projectile
		game.getEntityManager().getProjectiles().add(temp);
		// spawn a muzzleflash
		game.getSpawnSystem().spawnBlueMuzzleFlash(x, y - 15);
	}

	/**
	 * spawn a projectile for a player laser LVL3
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnPlayerLaserLVL3(double x, double y) {
		// Instantiate a temporary Projectile
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x, y + 15);
		temp.setDamage(Blackboard.DAMAGELASERLVL3);// set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL3);// set the speed
		// spawn the projectile
		game.getEntityManager().getProjectiles().add(temp);
		// spawn a muzzleflash
		game.getSpawnSystem().spawnBlueMuzzleFlash(x, y);
	}

	/**
	 * spawn a projectile for an enemy laser LVL1
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnEnemyLaserLVL1(double x, double y) {
		game.getEntityManager().getProjectiles()
				// spawn projectile
				.add(new EnemyLaser(game.getTextures().getEnemyLaser(), game, x, y + 20));
		game.getSpawnSystem().spawnRedMuzzleFlash(x, y);// spawn a muzzleflash
	}

	/**
	 * spawn an explosion01 animation
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnExplosion01(double x, double y) {
		// spawn animation
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getExplosion01(), game, x, y));
	}

	/**
	 * spawn an blue muzzleflash animation
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnBlueMuzzleFlash(double x, double y) {
		// spawn animation
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getBlueMuzzleFlash(), game, x, y));
	}

	/**
	 * spawn an red muzzleflash animation
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnRedMuzzleFlash(double x, double y) {
		// spawn animation
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getRedMuzzleFlash(), game, x, y));
	}

	/**
	 * spawn an blue laserimpact animation
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnBlueLaserImpact(double x, double y) {
		// spawn animation
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getBlueLaserImpact(), game, x, y));
	}

	/**
	 * spawn an red laserimpact animation
	 * 
	 * @param x
	 *            desired x-position
	 * @param y
	 *            desired y-position
	 */
	public void spawnRedLaserImpact(double x, double y) {
		// spawn animation
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getRedLaserImpact(), game, x, y));
	}

	/**
	 * spawn an enemy wave
	 */
	public void spawnWave() {
		for (int x = 0; x < (Blackboard.GAMEWIDTH); x += 64) {
			Enemy temp = new Enemy(game.getTextures().getEnemy(), game, x, 0);
			temp.addWeapon(new EnemyLaserGunLVL1(game.getTextures().getEnemyLaserLVL1(), temp, game));
			// spawn enemy
			game.getEntityManager().getEnemies().add(temp);
		}
	}

}
