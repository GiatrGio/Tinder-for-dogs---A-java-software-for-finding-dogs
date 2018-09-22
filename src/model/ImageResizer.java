package model;
/** MH image resizer from http://www.mkyong.com/java/how-to-resize-an-image-in-java/
 *  also contains ImageCrop to crop the image to have the same width as height works with image given by user HowToResizeBufferedImage can read the width and height of the image. The
 * image dimensions are alterded, if needed cropped (width should be equal to height). cropping occurs in ImageCrop method, see below
 * @original author mkyong[]
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageResizer {

	// give standard image output width and height, tailor-fit to the window in
	// GUI to show the image.
	private int IMG_WIDTH = 150;
	private int IMG_HEIGHT = 150;

	// cropIfNeededAndResize builds the final image resi (resized) which is cropped
	// and resized (150x150 pixels)
	public BufferedImage cropIfNeededAndResize(BufferedImage originalImage) { // refactor
		int width = originalImage.getWidth(); // reads the Width and height
		int height = originalImage.getHeight();
		int type = originalImage.getType();
		// BufferedImage reImage = originalImage;

		// if height is the same as width, resizing can be done
		if (height == width) {
			BufferedImage reImage = resizeImage(originalImage, type);
			return reImage;
		} else {
			BufferedImage croppedImage = imageCrop(originalImage, type);
			BufferedImage resi = resizeImage(croppedImage, type);
			return resi;
		}
		// return originalImage;

	}

	// resizeImage to resize the image
	public BufferedImage resizeImage(BufferedImage originalImage, int type) {

		// int type1 = originalImage.getType();
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);

		Graphics2D g = resizedImage.createGraphics();
		// originalimage.getwidth;
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		return resizedImage;
	}

	// ImageCrop is ccalled when height of image is not the same as the length.
	// ImageCrop looks at the height and width of the image, and crops according
	// to the difference between the IMG_WIDTH which is given agove
	// (set default to 150 x 150)
	public BufferedImage imageCrop(BufferedImage originalImage, int type) {

		int width = originalImage.getWidth(); // reads the Width and height
		int height = originalImage.getHeight();

		int heightnew = height;
		int widthnew = width;

		// when width is more than the weight, ImageCrop makes it less wide.
		if (width > height) {
			int diff = width - height;
			widthnew = width - diff; // new width will be used in getSubimage
										// beneath

		} else {
			// (width < height)
			int diff = height - width;
			heightnew = height - diff; // new height will be used in getSubimage
		}

		int x = (width / 2) - (widthnew / 2); // coordinates from which taken
												// the cropped slice
		int y = (height / 2) - (heightnew / 2);

		// getSubimage will now create the subImage which has width and height
		BufferedImage subImage = originalImage.getSubimage(x, y, widthnew, heightnew);
		return subImage;
	}

}