package com.espen.spacegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * pretty simple utility class to forward keyinputs to the playerController
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
		game.getPlayerController().keyPressed(e);
	}

	/**
	 * Forward the handling for the input to the controller
	 */
	public void keyReleased(KeyEvent e) {
		game.getPlayerController().keyReleased(e);
	}
}