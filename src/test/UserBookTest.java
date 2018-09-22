package test;

import java.util.ArrayList;

import database.DataRetrieval;
import junit.framework.TestCase;
import model.*;

public class UserBookTest extends TestCase {
	/**
	 * UserBookTest tests whether UserBook corresponds to the databae, whether
	 * UserBook exists, whether an index can be added to the UserBook.
	 * testGetUserByUsername tests whether a user can be recalled by the UserBook.
	 * testDBFetch tests whether the database can be called and gives back users.
	 */
	// Test if UserBook exists
	public void testUserBookExists() {
		ArrayList<User> users = new ArrayList<User>();
		UserBook userbook = new UserBook(users);
		assertNotNull(userbook);
	}

	public void testUserBookExists2() {
		UserBook userbook = new UserBook();
		assertNotNull(userbook);
	}

	// Test get method
	public void testgetUsers() {
		ArrayList<User> users = new ArrayList<User>();
		UserBook userbook = new UserBook(users);
		assertEquals(users, userbook.getUsers());
	}

	// Test getUserByIndex method
	public void testaddAndIndexUserBook() {
		User abel1 = new User("Abeltje1", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		User abel2 = new User("Abeltje2", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		User abel3 = new User("Abeltje3", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		ArrayList<User> users = new ArrayList<User>();
		UserBook userbook = new UserBook(users);
		userbook.createUser(abel1);
		userbook.createUser(abel2);
		userbook.createUser(abel3);
		assertEquals(abel2, userbook.getUserByIndex(1));
	}

	// Test get method for username
	public void testGetUserByUsername() {
		User abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		ArrayList<User> users = new ArrayList<User>();
		UserBook userbook = new UserBook(users);
		userbook.createUser(abel);
		assertEquals(abel, userbook.getUserByUsername("Abeltje"));
	}

	// Test depends on user in DB
//	public void testdbFetch() {
//
//		UserBook allusers = new UserBook();
//		DataRetrieval data = new DataRetrieval();
//		data.filluserbook();
//		Integer fsa = 47;
//		assertEquals(allusers.getUserByUsername("admin").getUserId(), fsa);
//	}

}
