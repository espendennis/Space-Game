package com.espen.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Game game;

	public KeyInput(Game game) {
		this.game = game;

	}

	public void keyPressed(KeyEvent e) {
		game.getController().keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		game.getController().keyReleased(e);
	}
}