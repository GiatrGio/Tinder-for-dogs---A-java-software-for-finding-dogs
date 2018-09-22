package test;

import junit.framework.TestCase;
import model.AcceptableUser;
/*
 * AcceptableUserTest.java tests AcceptableUser.java 			MH 21:00 12/04
 * https://howtoprogramwithjava.com/5-tips-to-being-a-better-programmer-unit-testing/
run as: JUNITtest!
 */

public class AcceptableUserTest extends TestCase {
	
	// PassWord Between 8 and 20, has a special character (good), no
	// whitespace
	public void testisAcceptablePasswordCorrect() {
		AcceptableUser henk = new AcceptableUser();
		String password = "Uienzooitje%";
		assertTrue(henk.isAcceptablePassword(password));
	}

	// Password not between 8 and 20 characters (negative result)
	public void testisAcceptablePasswordLength() {
		AcceptableUser henk = new AcceptableUser();
		String password = "pass$1";
		assertFalse(henk.isAcceptablePassword(password));
	}

	// Password should be between 8 and 20, but has no character (negative
	// result)
	public void testisAccaptablePasswordCharacter() {
		AcceptableUser henk = new AcceptableUser();
		String password = "pass231231";
		assertFalse(henk.isAcceptablePassword(password));
	}

	// Password should be between 8 and 20, but has whitespace (negative result)
	public void testisAccaptablePasswordSpace() {
		AcceptableUser henk = new AcceptableUser();
		String password = "pass 231231";
		assertFalse(henk.isAcceptablePassword(password));
	}

	// Username not between 8 and 20 characters (negative result)
	public void testisAccaptableUserNameLength() {
		AcceptableUser henk = new AcceptableUser();
		String username = ("user$1");
		assertFalse(henk.isAcceptableUserName(username));
	}

	// Username Between 8 and 20, has a number (good), no whitespace
	public void testisAccaptableUserNameCorrect() {
		AcceptableUser henk = new AcceptableUser();
		String username = ("Rocki$toozo12");
		assertTrue(henk.isAcceptableUserName(username));
	}

	// Username not between 8 and 20 characters (negative result) no number
	public void testisAccaptableUserNameNumber() {
		AcceptableUser henk = new AcceptableUser();
		String username = ("user$");
		assertFalse(henk.isAcceptableUserName(username));
	}

	// Username should be between 8 and 20, but has whitespace (negative result)
	public void testisAccaptableUserNameSpace() {
		AcceptableUser henk = new AcceptableUser();
		String username = ("user 231231");
		assertFalse(henk.isAcceptableUserName(username));
	}
}