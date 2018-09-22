package test;

/*
 * ImageResizerTest tests whether ImageResizer works, as in, takes the image and modifies the dimensions. 
 * To see if cropping worked, the image needs to be looked at.
 */
// 
import junit.framework.TestCase;
import model.ImageResizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizerTest extends TestCase {
	private ImageResizer imageResi = new ImageResizer();

	public void testResize() {

		// grab an image and converts into BufferedImage
		// apply the resizemethod, control if pixel size is 150 x 150
		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(new File("pictures/saddog.jpg")); // test image saddog.jpg
			assertNotNull(originalImage);

			BufferedImage resizedImage = imageResi.cropIfNeededAndResize(originalImage);

			int x = resizedImage.getHeight();
			int y = resizedImage.getWidth();
			assertEquals(150, x);
			assertEquals(150, y);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
