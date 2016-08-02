package com.espen.spacegame;

/**
 * This Class holds all the important perimeters of the game. In a later Version
 * those will be read from a .cfg-file
 * 
 * @author Dennis
 *
 */
public class Blackboard {

	// GameSpecs
	// -----------------------------------------------------------

	// Width of the game's window
	public static final int GAMEWIDTH = 640;
	// Height of the game's window
	public static final int GAMEHEIGHT = GAMEWIDTH / 12 * 9;
	// The game's title
	public static final String TITLE = "2D Space Game";
	// The game's Tickrate.
	public static final int TICKRATE = 60;

	// Playerstats
	// -----------------------------------------------------------

	// The Player's movement-speed
	public static final double PLAYERSPEED = 5;

	// EnemiesStats
	// -----------------------------------------------------------

	// The movement-speed of an LVL1 enemy
	public static final double ENEMYSPEEDLVL1 = 1;
	// How many points the player will get for killing a LVL1 enemy
	public static final int ENEMYPOINTSFORKILLLVL1 = 10;
	// The firerate of a LVL1 enemy
	public static final int ENEMYFIRERATELVL1 = 1;
	// The speed of the projectiles shot by a LVL1 enemy
	public static final int SPEEDENEMYLASERLVL1 = 2;
	// The damage the projectiles, shot by a LVL1 enemy deal
	public static final int DAMAGEENEMYLASERLVL1 = 10;

	// PlayerProjectiles
	// -----------------------------------------------------------

	// Speed of the player's LVL1 Laser projectiles
	public static final double SPEEDLASERLVL1 = 5;
	// Speed of the player's LVL2 Laser projectiles
	public static final double SPEEDLASERLVL2 = 5;
	// Speed of the player's LVL3 Laser projectiles
	public static final double SPEEDLASERLVL3 = 10;
	// Damage a player's LVL1Laser deals
	public static final int DAMAGELASSERLVL1 = 10;
	// Damage a player's LVL2 Laser deals
	public static final int DAMAGELASERLVL2 = 20;
	// Damage a player's LVL3 Laser deals
	public static final int DAMAGELASERLVL3 = 20;

	// Player Weapons
	// -----------------------------------------------------------

	// The time the player lvl1 laser needs to recover after every shot
	public static final int PLAYERLASERLVL1RECOVERDURATION = 5;
	// The time the player lvl1 laser needs to recover after every burst
	public static final int PLAYERLASERLVL1RELOADDURATION = 20;
	// The shots fired per burst for the player lvl1 laser
	public static final int PLAYERLASERLVL1SHOTSPERBURST = 3;
	// The x-offset for the player lvl1 laser
	public static final int PLAYERLASERLVL1XOFFSET = 0;
	// The y-offset for the player lvl1 laser
	public static final int PLAYERLASERLVL1YOFFSET = 0;

	// The time the player lvl2 laser needs to recover after every shot
	public static final int PLAYERLASERLVL2RECOVERDURATION = 5;
	// The time the player lvl2 laser needs to recover after every burst
	public static final int PLAYERLASERLVL2RELOADDURATION = 10;
	// The shots fired per burst for the player lvl2 laser
	public static final int PLAYERLASERLVL2SHOTSPERBURST = 1;
	// The x-offset for the player lvl2 laser
	public static final int PLAYERLASERLVL2XOFFSET = 0;
	// The y-offset for the player lvl2 laser
	public static final int PLAYERLASERLVL2YOFFSET = 0;

	// The time the player lvl3 laser needs to recover after every shot
	public static final int PLAYERLASERLVL3RECOVERDURATION = 5;
	// The time the player lvl3 laser needs to recover after every burst
	public static final int PLAYERLASERLVL3RELOADDURATION = 10;
	// The shots fired per burst for the player lvl3 laser
	public static final int PLAYERLASERLVL3SHOTSPERBURST = 1;
	// The x-offset for the player lvl3 laser
	public static final int PLAYERLASERLVL3XOFFSET = 10;
	// The y-offset for the player lvl3 laser
	public static final int PLAYERLASERLVL3YOFFSET = 0;

	// Enemy Weapons
	// -----------------------------------------------------------

	// The time the enemy lvl1 laser needs to recover after every shot
	public static final int ENEMYLASERLVL1RECOVERDURATION = 5;
	// The time the enemy lvl1 laser needs to recover after every burst
	public static final int ENEMYLASERLVL1RELOADDURATION = 100;
	// The shots fired per burst for the enemy lvl1 laser
	public static final int ENEMYLASERLVL1SHOTSPERBURST = 1;
	// The x-offset for the enemy lvl1 laser
	public static final int ENEMYLASERLVL1XOFFSET = 0;
	// The y-offset for the enemy lvl1 laser
	public static final int ENEMYLASERLVL1YOFFSET = 0;

	// Animations

	// standart animation speed
	public static final int STANDARTANIMATIONSPEED = 5;

}
