package com.espen.src;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * utility-class for loading images
 * 
 * @author Dennis
 *
 */
public class BufferedImageLoader {

	private BufferedImage image;

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

}
