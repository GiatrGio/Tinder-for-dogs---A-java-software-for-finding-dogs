package model;

/**
 * Class User holds everything a user can do.
 * 
 * @author Team D
 */

public class User extends DatabaseObject {
	private Integer userid;
	private String username;
	private String password;
	private String firstname;
	private String email;
	private String lastname;
	private String userbio;
	private boolean isModerator = false;
	private Integer internalid = -1;
	private boolean isModerating = false;

	// Constructor with 6 parameters
	/**
	 * User constructor for creating normal users through the GUI. (moderator status
	 * defaults to false)
	 * 
	 * @param username
	 * @param password
	 * @param firstname
	 * @param email
	 * @param lastname
	 * @param userbio
	 */
	public User(String username, String password, String firstname, String email, String lastname, String userbio) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.email = email;
		this.lastname = lastname;
		this.userbio = userbio;
		this.isModerator = false;
		this.userid = internalid;
		internalid--;
		editflag = false;
		this.isModerating = false;
	}

	/**
	 * 7 parameter User constructor for creating normal users and moderator by
	 * extracting information from the database
	 * 
	 * @param username
	 * @param password
	 * @param firstname
	 * @param email
	 * @param lastname
	 * @param userbio
	 */
	public User(String username, String password, String firstname, String email, String lastname, String userbio,
			boolean isModerator, Integer userid) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.email = email;
		this.lastname = lastname;
		this.userbio = userbio;
		this.isModerator = isModerator;
		this.userid = userid;
		editflag = false;
		this.isModerating = false;
	}

	// ALL THE GET METHODS

	// method returns the User name
	public String getUsername() {
		return username;
	}

	// method returns the User email
	public String getEmail() {
		return email;
	}

	// method returns the User firstname
	public String getFirstname() {
		return firstname;
	}

	// method that returns the User password
	public String getPassword() {
		return password;
	}

	// method that returns the User lastname
	public String getLastname() {
		return lastname;
	}

	// method that returns the User userbio
	public String getUserBio() {
		return userbio;
	}

	public boolean getIsModerator() {
		return isModerator;
	}

	public Integer getUserId() {
		return userid;
	}

	public boolean getIsModerating() {
		return isModerating;
	}

	// ALL THE SET METHODS

	// method changes the User name
	public void setUsername(String newUsername) {
		this.username = newUsername;
		setEditFlag();
	}

	// method changes the User email
	public void setEmail(String newEmail) {
		this.email = newEmail;
		setEditFlag();
	}

	// method changes the User firstname
	public void setFirstname(String newFirstName) {
		this.firstname = newFirstName;
		setEditFlag();
	}

	// method changes the User password
	public void setPassword(String newPassword) {
		this.password = newPassword;
		setEditFlag();
	}

	// method changes the User lastname
	public void setLastname(String newLastName) {
		this.lastname = newLastName;
		setEditFlag();
	}

	// method changes the User userbio
	public void setUserBio(String newBio) {
		this.userbio = newBio;
		setEditFlag();
	}

	// method sets moderator status:
	public void setIsModerator(boolean ismod) {
		this.isModerator = ismod;
		setEditFlag();
	}

	public void setEditFlag() {
		// Set edit flag to true if userID already existed within the DB.
		editflag = (this.userid > 0);
	}

	
	/**
	 * Set the attributes for an user
	 * 
	 * @param password
	 * @param firstname
	 * @param email
	 * @param lastname
	 * @param userbio
	 */
	public void setAttributes(String password, String firstname, String email, String lastname, String userbio) {
		this.password = password;
		this.firstname = firstname;
		this.email = email;
		this.lastname = lastname;
		this.userbio = userbio;
		setEditFlag();
	}

	/**
	 * Set if an user is moderator or not
	 * 
	 * @param b
	 */
	public void setIsModerating(boolean b) {
		isModerating = b;

	}
}
