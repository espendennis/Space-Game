package com.espen.spacegame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * This is the main method of the game. It handles the gameloop the mainthread
 * and calls the tick- and render-methods in the subsystems. It also
 * instantiates those subsystems.
 * 
 * @author Dennis
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public final String TITLE = "2D Space Game";

	private boolean running = false; /*
										 * this variable is used to check if the
										 * game is running
										 */
	private Thread thread; /* the games' main thread */

	private Controller controller; /* The gamecontroller */
	private Textures textures; /* The textures-subsystem */
	private EntityManager entityManager; /*
											 * The subsystem that holds all the
											 * objects in the game
											 */
	private SpawnSystem spawnSystem; /*
										 * The subsystem responsible for
										 * spawning objects into the game
										 */
	private PlayerController playerController;

	/**
	 * This method initializes the games' subsystems.
	 */
	public void init() {

		setSize(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT);
		requestFocus();
		textures = new Textures(this);
		entityManager = new EntityManager(this);
		spawnSystem = new SpawnSystem(this);
		controller = new Controller(this);
		playerController = new PlayerController(this);
		playerController.possess(entityManager.getPlayer());
		addKeyListener(new KeyInput(this));

	}

	/**
	 * This method starts the games' mainthread.
	 */
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * This method stops the gamethread and exits the game.
	 */
	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	/**
	 * The run-method for the gamethread
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		init(); /*
				 * calls the init-method of this class to initialize the game
				 * the subsystems
				 */
		long lastTime = System.nanoTime(); /* get the current time */
		double nanos = 1000000000
				/ Blackboard.TICKRATE; /*
										 * the nanoseconds for the duration of
										 * one tick
										 */
		double delta = 0;
		int updates = 0; /* counts how often tick() is called */
		int frames = 0; /* counts how often render() is called */
		long timer = System
				.currentTimeMillis(); /*
										 * get the current time in milliseconds
										 * for the tick- and frame-counter.
										 * Milliseconds are sufficient for this
										 * one.
										 */

		// Gameloop
		while (running) { /* while the game is running */
			long now = System.nanoTime(); /* get the current time */
			delta += (now - lastTime)
					/ nanos; /*
								 * the time that has past since the last tick is
								 * divided by the length of a tick so a new tick
								 * can be triggered when delta reaches 1.
								 */
			lastTime = now;
			if (delta >= 1) { /* if delta reaches 1 call tick(). */
				tick();
				updates++;
				delta--;
			}
			render(); /*
						 * the render-method is called everytime this loop runs.
						 * The game can run as many frames as the system is able
						 * to render independent of the tickrate.
						 */
			frames++;

			if (System.currentTimeMillis()
					- timer > 1000) { /* if 1 second has passed */
				timer += 1000; /* add 1 second */
				System.out.println(updates + " Ticks, " + frames
						+ " Frames"); /*
										 * and print the counts of the calls of
										 * tick() and render() to the console
										 */
				updates = 0; /* reset updates */
				frames = 0; /* reset frames */
			}

		}
		stop(); /* if the loopcondition (running) returns false stop the game */
	}

	/**
	 * this is the main tick-method. It calls the tick-method in the controller
	 * which calls all other tick-methods in the game.
	 */
	private void tick() {
		controller.tick();

	}

	/**
	 * this the main render-method. It prints the whole game to the screen and
	 * calls the render-method in the controller which calls all other
	 * render-methods in the game.
	 */
	private void render() {

		BufferStrategy bs = this
				.getBufferStrategy(); /*
										 * get the reference to JFrames'
										 * BufferStrategy
										 */

		if (bs == null) {
			createBufferStrategy(
					3); /*
						 * if bs = null create a new Bufferstrategy. The 3 in
						 * the call tells the method to create a doublebuffer
						 */
			return;
		}
		Graphics g = bs
				.getDrawGraphics();/*
									 * get the Graphics-Object from the
									 * Bufferstrategy to draw into the Buffer
									 */
		g.setColor(new Color(150, 150, 150));
		g.drawImage(textures.getBackground(), 0, 0,
				this); /* paint the Background image */

		controller.render(g); /* call the render-method in the controller */

		g.dispose(); /* dispose the Graphics-Object */
		bs.show(); /*
					 * render everything that was painted into the buffer on the
					 * screen
					 */
	}

	/**
	 * the main method
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Game game = new Game(); /* Instantiate the game */

		game.setPreferredSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));
		game.setMaximumSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));
		game.setMinimumSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));

		JFrame frame = new JFrame(Blackboard.TITLE);
		frame.add(game); /* add the game to the frame */
		frame.pack(); /* pack the frame */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	/**
	 * get a reference to the controller subsystem
	 * 
	 * @return reference to the controller subsystem
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * get a reference to the textures subsystem
	 * 
	 * @return reference to the textures subsystem
	 */
	public Textures getTextures() {
		return textures;
	}

	/**
	 * get a reference to the entitymanager subsystem
	 * 
	 * @return reference to the entitymanager subsystem
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * get a reference to the spawn subsystem
	 * 
	 * @return reference to the spawn subsystem
	 */
	public SpawnSystem getSpawnSystem() {
		return spawnSystem;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

}