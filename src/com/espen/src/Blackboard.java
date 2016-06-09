package com.espen.src;

/**
 * This Class holds all the important perimeters of the game. In a later Version
 * those will be read from a .cfg-file
 * 
 * @author Dennis
 *
 */
public class Blackboard {

	// GameSpecs
	public static final int GAMEWIDTH = 640; // Width of the game's window
	public static final int GAMEHEIGHT = GAMEWIDTH / 12 * 9; // Height of the
																// game's window
	public static final String TITLE = "2D Space Game"; // The game's title
	public static final int TICKRATE = 60; // The game's Tickrate.

	// Playerstats
	public static final double PLAYERSPEED = 5; // The Player's movement-speed

	// EnemiesStats
	public static final double ENEMYSPEEDLVL1 = 1; // The movement-speed of an
													// LVL1 enemy
	public static final int ENEMYPOINTSFORKILLLVL1 = 10; // How many points the
															// player will get
															// for killing a
															// LVL1 enemy
	public static final int ENEMYFIRERATELVL1 = 1; // The firerate of a LVL1
													// enemy
	public static final int SPEEDENEMYLASERLVL1 = 2; // The speed of the
														// projectiles shot by a
														// LVL1 enemy
	public static final int DAMAGEENEMYLASERLVL1 = 10; // The damage the
														// projectiles, shot by
														// a LVL1 enemy deal

	// PlayerWeapons
	public static final double SPEEDLASERLVL1 = 5; // Speed of the player's LVL1
													// Laser projectiles
	public static final double SPEEDLASERLVL2 = 5; // Speed of the player's LVL2
													// Laser projectiles
	public static final double SPEEDLASERLVL3 = 10; // Speed of the player's
													// LVL3 Laser projectiles
	public static final int FIRERATELASERLVL1 = 2; // Firerate of the player's
													// LVL1 Laser
	public static final int FIRERATELASERLVL2 = 4; // Firerate of the player's
													// LVL2 Laser
	public static final int FIRERATELASERLVL3 = 10; // Firerate of the player's
													// LVL3 Laser
	public static final int DAMAGELASSERLVL1 = 10; // Damage a player's LVL1
													// Laser deals
	public static final int DAMAGELASERLVL2 = 20; // Damage a player's LVL2
													// Laser deals
	public static final int DAMAGELASERLVL3 = 20; // Damage a player's LVL3
													// Laser deals
	public static final int XOFFSETLASERLVL1 = 0; // X-Offset for spawning a
													// player's LVL1 laser
	public static final int XOFFSETLASERLVL2 = 0; // X-Offset for spawning a
													// player's LVL2 laser
	public static final int XOFFSETLASERLVL3 = 10; // X-Offset for spawning a
													// player's LVL3 laser

	// Animations
	public static final int EXPLOSION01SPEED = 5; // Animationspeed for the
													// explosion01 animation
	public static final int BLUEMUZZLEFLASHSPEED = 5; // Animationspeed for the
														// blue muzzleflash
														// animation
	public static final int REDMUZZLEFLASHSPEED = 5; // Animationspeed for the
														// red muzzleflash
														// animation
	public static final int BLUELASERIMPACTSPEED = 5; // Animationspeed for the
														// blue laser impact
														// animation
	public static final int REDLASERIMPACTSPEED = 5; // Animationspeed for the
														// red laser impact
														// animation
	
}
