package com.espen.spacegame;

import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * Interface for all projectiles in the game
 * @author Dennis
 *
 */
public interface Projectile {

	public void tick();

	public void render(Graphics g);

	public double getY();

	public int getDamage();

	public Rectangle getBounds();

	public void destroy();
}
