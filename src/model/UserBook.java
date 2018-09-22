package model;

import java.util.ArrayList;

/**
 * Class UserBook. It stores all users in an ArrayList. When an user is deleted
 * it is moved to another ArrayList which contains deleted users.
 * 
 * @author everyone
 *
 */

public class UserBook {
	private ArrayList<User> users;
	private ArrayList<User> deletedUsers; // List of Users that are deleted

	/**
	 * A constructor for UserBook which requires an ArrayList of users
	 * 
	 * @param myusers
	 */
	// CONSTRUCTOR
	public UserBook(ArrayList<User> myusers) {
		this.users = myusers;
		this.deletedUsers = new ArrayList<User>();
	}

	/**
	 * A constructor for UserBook which does not require any parameter
	 */
	public UserBook() {
		this.users = new ArrayList<User>();
		this.deletedUsers = new ArrayList<User>();
	}

	// GET METHODS
	/**
	 * Return the ArrayList that contains all users
	 * 
	 * @return the ArrayList with all users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Return one specific user
	 * 
	 * @param i
	 * @return the user stored in the position i in the ArrayList with all users
	 */
	public User getUserByIndex(int i) {
		return users.get(i);
	}

	/**
	 * Return the user using a username as filter. This is a method for finding a
	 * user using the username by iterating over the User objects in the arraylist.
	 * Note: if there are multiple users that have the same username (there
	 * shouldn't be), then it returns the last match. Note: if it doesn't find a
	 * user with that username, returns -1
	 * 
	 * @param username
	 * @return the user whose has an specific username as attribute
	 */
	public User getUserByUsername(String username) {

		User foundUser = null;
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				foundUser = u;
			}
		}
		return foundUser;
	}

	// GET NEW USERS
	public ArrayList<User> getNewUsers() {
		ArrayList<User> newUsers = new ArrayList<User>();
		for (User u : users) {
			if (u.getUserId() < 0) {
				newUsers.add(u);
			}
		}
		return newUsers;
	}

	// Get all users except mods
	public ArrayList<User> getUsersExceptMods() {
		ArrayList<User> normalUsers = new ArrayList<User>();
		for (User u : users) {
			if (!u.getIsModerator()) {
				normalUsers.add(u);
			}
		}
		return normalUsers;
	}

	// GET DELETED USERS
	public ArrayList<User> getDeletedUsers() {
		return deletedUsers;
	}

	// GET EDITED USERS
	public ArrayList<User> getEditedUsers() {
		ArrayList<User> editedusers = new ArrayList<User>();
		for (User u : users) {
			if (u.getEditFlag()) {
				editedusers.add(u);
			}
		}
		return editedusers;
	}

	// SET METHODS
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	// Method to create a new user and store it in allUser ArrayList
	public void createUser(User user) {
		users.add(user);
	}

	public void deleteUser(User user) {
		if (user.getUserId() > 0) {
			deletedUsers.add(user);
		}
		users.remove(user);
	}

	// Method that checks if one username exist
	public boolean checkUserName(String username) {
		boolean checkuser = false;
		for (int i = 0; i < users.size(); i++) {
			User myUser = users.get(i);
			String myUserName = myUser.getUsername();
			if (myUserName.equals(username)) {
				checkuser = true;
			}
		}
		return checkuser;
	}

	/**
	 * Login method to check if a combination of username and password is correct
	 * 
	 * @param username
	 * @param password
	 * @return boolean true if the combination is correct and false if it is not
	 */
	public boolean logIn(String username, String password) {
		boolean result = false;
		for (int i = 0; i < users.size(); i++) {
			User myUser = users.get(i);
			String myUserName = myUser.getUsername();
			String myPassword = myUser.getPassword();
			if (myUserName.equals(username) && myPassword.equals(password)) {
				result = true;
			}
		}
		return result;
	}

}