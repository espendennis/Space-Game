package com.espen.src;

import java.awt.event.KeyEvent;
import java.util.Hashtable;

/**
 * The PlayerController class is used to control an Entity in the game by
 * keyInputs. After Instantiating the Controller has to possess an actor by
 * calling the possess()-method; There will be an optionsmenu to change the
 * mapping of the keys.
 * 
 * @author Dennis
 *
 */
public class PlayerController {

	private Game game;
	private Actor target;
	private Hashtable<String, Integer> keyTable;
	private String up = "up";
	private String down = "down";
	private String left = "left";
	private String right = "right";
	private String shoot = "shoot";
	private boolean pressingUp = false;
	private boolean pressingDown = false;
	private boolean pressingLeft = false;
	private boolean pressingRight = false;

	/**
	 * Constructor Only needs a reference to game to get access to other
	 * subsystems
	 * 
	 * @param game
	 */
	public PlayerController(Game game) {
		this.game = game;
		target = game.getEntityManager().getPlayer();
		keyTable = new Hashtable<String, Integer>();
		keyTable.put(up, new Integer(KeyEvent.VK_UP));
		keyTable.put(left, new Integer(KeyEvent.VK_LEFT));
		keyTable.put(right, new Integer(KeyEvent.VK_RIGHT));
		keyTable.put(down, new Integer(KeyEvent.VK_DOWN));
		keyTable.put(shoot, new Integer(KeyEvent.VK_SPACE));

	}

	/*
	 * InputHandling
	 * 
	 * Note: If the player for example moves to the right and wants to change
	 * direction to move to the left and he presses the left key shortly before
	 * he releases the right key the player's ship would stop moving. To prevent
	 * this from happening several booleans are set and checked during input
	 * handling.
	 */

	/**
	 * handles the keyPressed events
	 * 
	 * @param e
	 *            KeyEvent
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == keyTable.get(right)) {
			target.setVelX(Blackboard.PLAYERSPEED);
			pressingRight = true;

		} else if (key == keyTable.get(left)) {
			target.setVelX(-Blackboard.PLAYERSPEED);
			pressingLeft = true;

		} else if (key == keyTable.get(down)) {
			target.setVelY(Blackboard.PLAYERSPEED);
			pressingDown = true;

		} else if (key == keyTable.get(up)) {
			target.setVelY(-Blackboard.PLAYERSPEED);
			pressingUp = true;

		} else if (key == keyTable.get(shoot)) {
			target.setShooting(true);
			;
		}
	}

	/**
	 * handles the keyReleased events
	 * 
	 * @param e
	 *            KeyEvent
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == keyTable.get(right)) {
			if (pressingLeft) {// if player is already pressing left...
				target.setVelX(-Blackboard.PLAYERSPEED);// ...move left
			} else {
				target.setVelX(0);
			}
			pressingRight = false;

		} else if (key == keyTable.get(left)) {
			if (pressingRight) {// if player is already pressing right...
				target.setVelX(Blackboard.PLAYERSPEED);// ...move right
			} else {
				target.setVelX(0);
			}
			pressingLeft = false;

		} else if (key == keyTable.get(down)) {
			if (pressingUp) {// if player is already pressing up...
				target.setVelY(-Blackboard.PLAYERSPEED);// ...move up
			} else {
				target.setVelY(0);
			}
			pressingDown = false;
			;

		} else if (key == keyTable.get(up)) {
			if (pressingDown) {// if player is already pressing down...
				target.setVelY(Blackboard.PLAYERSPEED);// ...move down
			} else {
				target.setVelY(0);
			}
			pressingUp = false;
			;

		} else if (key == keyTable.get(shoot)) {
			target.setShooting(false);
		}

	}

	public void possess(Actor target) {
		this.target = target;
	}

}
