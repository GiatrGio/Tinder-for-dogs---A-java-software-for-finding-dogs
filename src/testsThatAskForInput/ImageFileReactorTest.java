package testsThatAskForInput;

import java.util.Scanner;

/*
 * MH FB ImageFileReactorTest tests ImageFileReactor 
 * testCharArrayIsDigit() tests if generated number by get
 * RandomNameGenerator only contains digits
 * testDoesItExist tests if doesFileExist works, by creating a testfile and checking if this file is 'seen' by doesFileExist method
 */

import org.junit.Test;
import junit.framework.TestCase;
import model.ImageFileReactor;

/* 
 * ImageFileReactorTest tests the doesFileExist method in ImageFileReactor.
 */
public class ImageFileReactorTest extends TestCase {
	private ImageFileReactor imgrea = new ImageFileReactor();

	@Test

	// Test testDoesItExist tests if doesFileExist works, by creating a testfile and
	// checking if this file is 'seen' by doesFileExist method
	public void testDoesItExist() {

		

		// checks if "pictures/newFileTester.jpg" exists, should not be the case
		// (false).
		assertEquals(imgrea.doesFileExist("newFileTester"), false);

	}
	
	// testDoesItExistYes checks if given input by user exists. Now it should be the case. Insert without extension.
	public void testDoesItExistYes() {
	
		ImageFileReactor imgrun = new ImageFileReactor();
		Scanner keyboard = new Scanner(System.in); 
		System.out.println("Please give name");
		String input = keyboard.nextLine(); // Michel: for example, "saddog"
		keyboard.close();
		assertEquals(imgrun.doesFileExist(input), true);
	}

}
