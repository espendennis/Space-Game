package com.espen.src;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * utility-class for loading images and spritesheets
 * 
 * @author Dennis
 *
 */
public class BufferedImageLoader {

	private BufferedImage image;
	private BufferedImage[] sprite;
	private int count = 0;

	/**
	 * Loading an image from the hard-drive into the game
	 * 
	 * @param path
	 *            Path where the image is located
	 * @return returns a BufferedImage object with the loaded image
	 * @throws IOException
	 */
	public BufferedImage loadImage(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}

	/**
	 * Loading an image from the hard-drive into the game and splitting it into
	 * subimages. Those are then put into BufferedImage-Arrays
	 * 
	 * @param path
	 *            Path where the image is located
	 * @param cols
	 *            Columns of images in the spritesheet
	 * @param rows
	 *            Rows of images in the spritesheet
	 * @return BufferedImage[] holding all the subimages of the spritesheet
	 * @throws IOException
	 */
	public BufferedImage[] loadSprite(String path, int cols, int rows, int subImageWidth, int subImageHeight)
			throws IOException {
		sprite = new BufferedImage[cols * rows];
		image = ImageIO.read(getClass().getResource(path));
		// Fill the BufferedImage Array for the animation with subimages from
		// the spritesheet
		for (int i = 1; i <= rows; i++)
			for (int j = 1; j <= cols; j++) {
				sprite[count] = image.getSubimage((j * subImageWidth) - subImageWidth,
						(i * subImageHeight) - subImageHeight, subImageWidth, subImageHeight);
				count++;
			}
		count = 0;
		return sprite;
	}

}
