package com.espen.src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = null;
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage explosion01 = null;
	private BufferedImage blueMuzzleFlash = null;
	private BufferedImage redMuzzleFlash = null;
	private Controller controller;
	private Textures textures;

	public void init() {
		image = new BufferedImage(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT, BufferedImage.TYPE_INT_RGB);
		setSize(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT);
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
			explosion01 = loader.loadImage("/explosion01.png");
			blueMuzzleFlash = loader.loadImage("/blue_muzzle_flash.png");
			redMuzzleFlash = loader.loadImage("/red_muzzle_flash.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		textures = new Textures(this);
		controller = new Controller(this, textures);
		addKeyListener(new KeyInput(this));

	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

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

	public void run() {

		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = Blackboard.TICKRATE;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		// Gameloop
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, " + frames + " Frames");
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

	private void tick() {
		controller.tick();
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(150, 150, 150));
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0, this);
		controller.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));
		game.setMaximumSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));
		game.setMinimumSize(new Dimension(Blackboard.GAMEWIDTH, Blackboard.GAMEHEIGHT));

		JFrame frame = new JFrame(Blackboard.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public Controller getController() {
		return controller;
	}

	public BufferedImage getExplosion01() {
		return explosion01;
	}

	public BufferedImage getBlueMuzzleFlash() {
		return blueMuzzleFlash;
	}

	public BufferedImage getRedMuzzleFlash() {
		return redMuzzleFlash;
	}

}
