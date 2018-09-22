package test;

import junit.framework.TestCase;
import model.Session;
import model.User;

public class SessionTest extends TestCase {
	public void testSessionInstance() { // tests if a session is created the
										// first time and if the field
										// loggedInUser is accessible
		Session my_session = Session.getInstance();
		assertNotNull(my_session);
	}

	public void testSetAndGetIngelogdGetter() {
		Session my_session = Session.getInstance();
		User bob = new User("Bob", "1234", "Bob", "Bob.Bob@Bobby.nl", "Anel", "Anel");
		my_session.setLoggedinUser(bob); // emulating setting a user during
											// login
		assertNotNull(my_session.getLoggedinUser());
	}

	public void testIngelogdLoggedinUserGetterSetter() {
		User bob = new User("Bob", "1234", "Bob", "Bob.Bob@Bobby.nl", "Anel", "Anel");
		Session my_session = Session.getInstance();
		my_session.setLoggedinUser(bob);
		assertEquals(bob, my_session.getLoggedinUser());
	}

}
