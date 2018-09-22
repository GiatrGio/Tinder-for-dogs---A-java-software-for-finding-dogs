package test;

import junit.framework.TestCase;
import model.Dog;
import model.User;

public class UserTest extends TestCase {

	public void testUserExists6() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		assertNotNull(Abel);
	}

	public void testUserExists7() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel", true, 123);
		assertNotNull(Abel);
	}

	public void testGetFirstName() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		String name = Abel.getFirstname();
		assertEquals("Abel", name);
	}

	public void testGetEmail() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		String x = Abel.getEmail();
		assertEquals("Abel.Abel@Abel.nl", x);
	}

	public void testGetPassword() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		String x = Abel.getPassword();
		assertEquals("1234", x);
	}
	
	public void testGetIsModerating(){
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		assertFalse(Abel.getIsModerating());
	}

	public void testGetLastName() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		String x = Abel.getLastname();
		assertEquals("Anel", x);
	}

	public void testGetUserBio() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		String x = Abel.getUserBio();
		assertEquals("Anel", x);
	}

	public void testGetIsModerator() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel", true, 123);
		boolean x = Abel.getIsModerator();
		assertTrue(x);
	}

	public void testGetUserID() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel", true, 123);
		Integer b = 123;
		assertEquals(b, Abel.getUserId());
	}
	public void testSetIsModerating(){
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setIsModerating(true);
		assertTrue(Abel.getIsModerating());
	}

	public void testSetGetUserName() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setUsername("xXx_Abel1_XxX");
		String username = Abel.getUsername();
		assertEquals("xXx_Abel1_XxX", username);
	}

	public void testSetFirstName() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setFirstname("bcd");
		String name = Abel.getFirstname();
		assertEquals("bcd", name);
	}

	public void testSetEmail() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setEmail("123");
		String x = Abel.getEmail();
		assertEquals("123", x);
	}

	public void testSetPassword() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setPassword("abc");
		String x = Abel.getPassword();
		assertEquals("abc", x);
	}

	public void testSetLastName() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setLastname("Dirk");
		String x = Abel.getLastname();
		assertEquals("Dirk", x);
	}

	public void testSetUserBio() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Abel.setUserBio("blabla long walks along the beach");
		String x = Abel.getUserBio();
		assertEquals("blabla long walks along the beach", x);
	}

	public void testSetIsModerator() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel", true, 123);
		Abel.setIsModerator(false);
		boolean x = Abel.getIsModerator();
		assertFalse(x);
	}

	public void testCreateFlag() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		assertFalse(Harry.getEditFlag());
	}

	public void testGetSetEditFlag() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Harry.setEmail("blabla");
		assertFalse(Harry.getEditFlag());
	}

	public void testSetAttributes() {
		User Abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel", true, 123);
		Abel.setAttributes("1234", "Gerrie", "Gerrie@wur.nl", "Dirks", "beach");
		assertEquals("1234", Abel.getPassword());
		assertEquals("Gerrie", Abel.getFirstname());
		assertEquals("Gerrie@wur.nl", Abel.getEmail());
		assertEquals("Dirks", Abel.getLastname());
		assertEquals("beach", Abel.getUserBio());
	}
}
