package com.espen.src;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Projectile {

	public void tick();

	public void render(Graphics g);

	public double getY();

	public int getDamage();

	public Rectangle getBounds();

	public void destroy();
}
