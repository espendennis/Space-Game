package com.espen.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * pretty simple utility class to handle keyinputs
 * 
 * @author Dennis
 *
 */
public class KeyInput extends KeyAdapter {

	private Game game;

	/**
	 * Constructor
	 * 
	 * @param game
	 *            reference to game
	 */
	public KeyInput(Game game) {
		this.game = game;

	}

	/**
	 * Forward the handling for the input to the controller
	 */
	public void keyPressed(KeyEvent e) {
		game.getController().keyPressed(e);
	}

	/**
	 * Forward the handling for the input to the controller
	 */
	public void keyReleased(KeyEvent e) {
		game.getController().keyReleased(e);
	}
}