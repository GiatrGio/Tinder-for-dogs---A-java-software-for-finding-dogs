package model;

/*
 * Session keeps track of who's logged in. This is now done by storing the username in the User loggedin. 
 * In addition, when called upon, SessionLogin returns the loggedInUser (getLoggedinUser)
 */

public class Session {

	// start singleton pattern
	private static Session session = null;

	// fields
	private User loggedInUser;

	// CONSTRUCTOR (empty, only exists as protected to prevent instantiation in
	// other classes, that don't use getInstance() )
	protected Session() {
	}

	public static Session getInstance() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}
	// end singleton pattern

	// GET METHODS
	public User getLoggedinUser() {
		return loggedInUser;
	}

	// SET METHODS
	public void setLoggedinUser(User loggedin) {
		this.loggedInUser = loggedin;
	}

}
