package model;
/*
 * SW MH - Uploadimage is used to upload the images given by the user. May be used in future to upload to database, now stores in pictures folder on eclipse.
 *	Uses ImageResizer.java to resize the image to the desired size.
 */

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import gui.ImageFilteren;

public class UploadImage {
	private BufferedImage ori = null;

	// uploadProcedure is called upon in order to upload dog pictures to the
	// database, and for use in the program.
	public BufferedImage uploadProcedure(String fileName) {
		try {
			BufferedImage ori = fileInPut(fileName);
			setOri(ori); // setOri to put the original image in

		} catch (IOException e) {
			e.printStackTrace();
		}

		// resize the image ori
		ori = imageResizerRunner(ori);

		// imageFilterRunner to run the "Instagram" Filter application
		imageFilterRunner(ori);
		return ori;

	}

	// BufferedImage from filename File
	public BufferedImage fileInPut(String fileName) throws IOException {
		BufferedImage ori = ImageIO.read(new File(fileName));
		return ori;
	}

	/**
	 * BufferedImage into imageResizer.cropIfNeededAndResize method, which makes the
	 * picture 150 x 150 pixels, and crops if needed
	 * 
	 * @param originalImage
	 * @return resized image
	 */
	public BufferedImage imageResizerRunner(BufferedImage originalImage) {
		ImageResizer imageres = new ImageResizer();
		BufferedImage resized = imageres.cropIfNeededAndResize(originalImage);
		return resized;
	}

	/*
	 * ImageFilterRunner: the user can adjust the colour gamma of the picture.
	 * Starts the ImageFilteren panel
	 */
	public void imageFilterRunner(BufferedImage fil) {
		ImageFilteren imgf = new ImageFilteren();
		imgf.imageFilteraar(fil);
	}

	public void setOri(BufferedImage originalImage) {
		this.ori = originalImage;
	}
}