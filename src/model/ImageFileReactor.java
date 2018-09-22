package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFileReactor {
	private BufferedImage dpic = null;
	/*
	 * ImageFileReactor is tested by ImageFileReactorTest.java. contains
	 * doesFileExist method, which checks if the fil already is in the pictures
	 * repository on SVN.
	 */

	// check if file exists using f.exists() and f.isDirectory().
	public boolean doesFileExist(String name) {
		boolean filebool = false;
		File f = new File("pictures/" + name + ".jpg"); // try to make file

		if (f.exists() && !f.isDirectory()) {
			filebool = true;
		}
		return filebool;
	}

	/**
	 * image displayer for use in ShowDogs, MyDog etc. Input is the number attached
	 * to an image, checks if file does exist.
	 * 
	 * @param name:
	 *            imagename of the image to display
	 * @return dpic which is displayed
	 */
	public BufferedImage readImageDisplayer(String name) {
		if (doesFileExist(name) == true) {
			try {
				dpic = ImageIO.read(new File("pictures/" + name + ".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			dpic = null;
		}

		return dpic;
	}
}
