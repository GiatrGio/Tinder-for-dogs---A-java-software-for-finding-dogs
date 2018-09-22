package testsThatAskForInput;

/*
 * ImageFilterenTest test ImageFilteren.java// (works but do not go to the imagefilter window itself), you should use console
*/
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import gui.ImageFilteren;

public class ImageFilterenTest {
	@Test
	public <assertNotNull> void testImageFilteren() {
		ImageFilteren imgfi = new ImageFilteren();
		Scanner keyboard = new Scanner(System.in); // Michel: /Users/michelhamakers/Downloads/emma2_2.jpg
		System.out.println("IMGFILTEREN. Give a directory to a file, like: /Users/michelhamakers/Downloads/emma2_2.jpg");
		String input = keyboard.nextLine();
		keyboard.close();

		// read directory to buffered Image
		BufferedImage bufi = null;
		try {
			bufi = ImageIO.read(new File(input));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// BufferedImage bufi into imagefilteraar
		imgfi.imageFilteraar(bufi);
		assertNotNull(bufi);
	}

}
