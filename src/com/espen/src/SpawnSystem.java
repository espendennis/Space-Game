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
	private int xOffsetLaserLVL1 = Blackboard.XOFFSETLASERLVL1;
	private int xOffsetLaserLVL2 = Blackboard.XOFFSETLASERLVL2;
	private int xOffsetLaserLVL3 = Blackboard.XOFFSETLASERLVL3;

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
		xOffsetLaserLVL1 = -xOffsetLaserLVL1; // swap x-Offset
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x + xOffsetLaserLVL1, y); // Instantiate
																											// a
																											// temporary
																											// Projectile
		temp.setDamage(Blackboard.DAMAGELASSERLVL1);// set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL1);// set the speed
		game.getEntityManager().getProjectiles().add(temp);// spawn the
															// projectile
		game.getSpawnSystem().spawnBlueMuzzleFlash(x + xOffsetLaserLVL1, y - 15);// spawn
																					// a
																					// muzzleflash

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
		xOffsetLaserLVL2 = -xOffsetLaserLVL2; // swap x-Offset
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x + xOffsetLaserLVL2, y); // Instantiate
																											// a
																											// temporary
																											// Projectile
		temp.setDamage(Blackboard.DAMAGELASERLVL2); // set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL2); // set the speed
		game.getEntityManager().getProjectiles().add(temp); // spawn the
															// projectile
		game.getSpawnSystem().spawnBlueMuzzleFlash(x + xOffsetLaserLVL2, y - 15); // spawn
																					// a
																					// muzzleflash
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
		xOffsetLaserLVL3 = -xOffsetLaserLVL3; // swap x-Offset
		PlayerLaser temp = new PlayerLaser(game.getTextures().getLaser(), game, x + xOffsetLaserLVL3, y + 15); // Instantiate
																												// a
																												// temporary
																												// Projectile
		temp.setDamage(Blackboard.DAMAGELASERLVL3);// set the damage
		temp.setSpeed(Blackboard.SPEEDLASERLVL3);// set the speed
		game.getEntityManager().getProjectiles().add(temp);// spawn the
															// projectile
		game.getSpawnSystem().spawnBlueMuzzleFlash(x + xOffsetLaserLVL3, y);// spawn
																			// a
																			// muzzleflash

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
				.add(new EnemyLaser(game.getTextures().getEnemyLaser(), game, x, y + 20)); // spawn
																							// projectile
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
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getExplosion01(), game, x, y)); // spawn
																														// animation
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
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getBlueMuzzleFlash(), game, x, y)); // spawn
																															// animation
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
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getRedMuzzleFlash(), game, x, y)); // spawn
																														// animation
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
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getBlueLaserImpact(), game, x, y)); // spawn
																															// animation
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
		game.getEntityManager().getAnimations().add(new Animation(game.getTextures().getRedLaserImpact(), game, x, y)); // spawn
																														// animation
	}

	/**
	 * spawn an enemy wave
	 */
	public void spawnWave() {
		for (int x = 0; x < (Blackboard.GAMEWIDTH); x += 64) {
			game.getEntityManager().getEnemies().add(new Enemy(game.getTextures().getEnemy(), game, x, 0)); // spawn
																											// enemy
		}
	}

}
