package com.espen.src;

public class Blackboard {

	// GameSpecs
	public static final int GAMEWIDTH = 640;
	public static final int GAMEHEIGHT = GAMEWIDTH / 12 * 9;
	public static final String TITLE = "2D Space Game";
	public static final int TICKRATE = 60;

	// Playerstats
	public static final double PLAYERSPEED = 5;

	// EnemiesStats
	public static final double ENEMYSPEED = 1;
	public static final int ENEMYPOINTSFORKILL = 10;
	public static final int ENEMYFIRERATELVL1 = 1;
	public static final int SPEEDENEMYLASERLVL1 = 2;
	public static final int DAMAGEENEMYLASERLVL1 = 10;

	// PlayerWeapons
	public static final double SPEEDLASERLVL1 = 5;
	public static final double SPEEDLASERLVL2 = 5;
	public static final double SPEEDLASERLVL3 = 10;
	public static final int FIRERATELASERLVL1 = 2;
	public static final int FIRERATELASERLVL2 = 4;
	public static final int FIRERATELASERLVL3 = 10;
	public static final int DAMAGELASSERLVL1 = 10;
	public static final int DAMAGELASERLVL2 = 20;
	public static final int DAMAGELASERLVL3 = 20;
	public static final int XOFFSETLASERLVL1 = 0;
	public static final int XOFFSETLASERLVL2 = 0;
	public static final int XOFFSETLASERLVL3 = 10;

	// Animations
	public static final int EXPLOSION01SPEED = 5;
	public static final int BLUEMUZZLEFLASHSPEED = 5;
	public static final int REDMUZZLEFLASHSPEED = 5;
}
