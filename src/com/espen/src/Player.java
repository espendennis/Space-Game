package com.espen.src;

import java.awt.Rectangle;

/**
 * The game's player class
 * 
 * @author Dennis
 *
 */
public class Player extends Actor {

	private boolean up = false;
	private boolean down = false;
	private boolean right = false;
	private boolean left = false;
	private int count = 100;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            A reference to a game-object to get access to the other
	 *            subsystems
	 * @param cols
	 *            number of columns the animation takes in the spritesheet
	 * @param rows
	 *            number of rows the animation takes in the spritesheet
	 * @param animationSpeed
	 *            how many ticks between the animation jumps to the next
	 *            subimage
	 * @param x
	 *            x-coordinate of desired spawn-point
	 * @param y
	 *            y-coordinate of desired spawn-point
	 */
	public Player(Game game, int cols, int rows, int animationSpeed, double x, double y) {
		super(game.getTextures().getPlayer(), game, cols, rows, animationSpeed, x, y);
		this.setLevel(1); // set starting-level
		health = 100; // set hitpoints
		controller = game.getController(); // set controller
	}

	/**
	 * the tick-method handles all updates for the player
	 */
	public void tick() {
		super.tick();
		if (x <= 0) // if the player passes the left edge of the screen
			x = 0; // move the player back to the edge
		if (x >= game.getWidth() - 32)// if the player passes the right edge of
										// the screen
			x = game.getWidth() - 32;// move the player back to the edge
		if (y <= 0)// if the player passes the upper edge of the screen
			y = 0;// move the player back to the edge
		if (y >= game.getHeight() - 32)// if the player passes the lower edge of
										// the screen
			y = game.getHeight() - 32;// move the player back to the edge
		if (isShooting) { // if the player is shooting
			count++; // increment count
			if (level == 1) { // if the player is lvl1...
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL1) {// ...and
																					// the
																					// count
																					// exceeds
																					// the
																					// number
																					// of
																					// ticks
																					// between
																					// 2
																					// shots...
					this.fireLaserLVL1();// ...shoot
					count = 0;// reset count
				}
			}
			if (level == 2) {// if the player is lvl2...
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL2) {// ...and
																					// the
																					// count
																					// exceeds
																					// the
																					// number
																					// of
																					// ticks
																					// between
																					// 2
																					// shots...
					this.fireLaserLVL2();// ...shoot
					count = 0;// reset count
				}
			}
			if (level == 3) {// if the player is lvl3...
				if (count > Blackboard.TICKRATE / Blackboard.FIRERATELASERLVL3) {// ...and
																					// the
																					// count
																					// exceeds
																					// the
																					// number
																					// of
																					// ticks
																					// between
																					// 2
																					// shots...
					this.fireLaserLVL3();// ...shoot
					count = 0;// reset count
				}
			}
		}
		if (!isShooting) {// if the player is not shooting...
			count = 100; // ...set count to 100. Without these 2 lines the
							// player would have to wait several ticks for the
							// first shot everytime he starts shooting
		}
		if (health <= 0) {// if the health drops under 0
			if (controller == null) {// if controller is null...
				controller = game.getController();// ...set controller. This
													// prevents random
													// nullpointer-exceptions
			}
			controller.gameOver();// call gameOver() in the Controller.
		}

	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public boolean isUp() {
		return up;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public void setUp(boolean up) {
		this.up = up;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public boolean isDown() {
		return down;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public void setDown(boolean down) {
		this.down = down;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public boolean isRight() {
		return right;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public boolean isLeft() {
		return left;
	}

	/**
	 * used for keyinput-handling.
	 * 
	 * @see Controller
	 * @return
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * set count. Count is used to delay the spawning of shots according to the
	 * firerate.
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Spawn a LaserLVL1 projectile
	 */
	public void fireLaserLVL1() {

		game.getSpawnSystem().spawnPlayerLaserLVL1(x, y);
	}

	/**
	 * Spawn a LaserLVL2 projectile
	 */
	public void fireLaserLVL2() {

		game.getSpawnSystem().spawnPlayerLaserLVL2(x, y);
	}

	/**
	 * Spawn a LaserLVL3 projectile
	 */
	public void fireLaserLVL3() {
		game.getSpawnSystem().spawnPlayerLaserLVL3(x, y);
	}

	/**
	 * return the boundaries of the player for collision-control
	 * 
	 * @return Rectangle representing the boundaries of the player
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * this method must be implemented when extending actor. There is no use in the
	 * playerclass for this method because the destruction of the player is handled in the
	 * controller on GameOver. So the body of this method is empty.
	 */
	public void destroy() {

	}
}
