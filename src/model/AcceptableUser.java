package model;

// SW MH AcceptableUser checks (1) password, (2) username, (3) user agreement if an account is created in "CreateUserPanel.java"
public class AcceptableUser {
	private int MIN_PASSWORD_LENGTH = 8;
	private int MAX_PASSWORD_LENGTH = 20;
	private int MIN_USERNAME_LENGTH = 8;
	private int MAX_USERNAME_LENGTH = 20;
	private String spcharacters = "!\"#$%&'()*+,-.:;<=>?@[]^_`{|}~"; // special characters
	private String numberz = "1234567890"; // numbers

	/**
	 * isAcceptablePassword checks if (1) the password is between 8-20 characters
	 * long (2) the password contains a special character (3) the password does not
	 * contain any white spaces
	 * @param password
	 * @return: boolean if the password is acceptable
	 */
	public boolean isAcceptablePassword(String password) {
		int len = password.length();

		int lench = spcharacters.length();
		boolean result = false;

		// Check if password is between [MIN_PASSWORD_LENGTH] and
		// [MAX_PASSWORD_LENGTH]
		if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
			result = false;
		}

		// Check if the password contains a special character
		else {
			for (int x = 0; x < lench; x++) {
				char ch = spcharacters.charAt(x);

				for (int i = 0; i < len; i++) {
					char c = password.charAt(i);

					// check if the password contains white space
					if (Character.isWhitespace(c) == true) {
						result = false;
					}

					// if character is in valid characters (of which one needs
					// to be included
					else {
						if (c == ch) {
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * isAcceptableUsername checks if: (1) the username is between 8-20 characters
	 * long (2) the username does not contain any white spaces (3) the username
	 * contains a number
	 * 
	 * @param username
	 *            gotten from the text field
	 * @return
	 */

	public boolean isAcceptableUserName(String username) {
		int len = username.length();
		int lench = numberz.length();
		boolean result = false;

		// Check if username is between [MIN_USERNAME_LENGTH] and
		// [MAX_USERNAME_LENGTH]
		if (len < MIN_USERNAME_LENGTH || len > MAX_USERNAME_LENGTH) {
			return false;
		}

		// numbers -> goes through the "numberz" string of which at least one
		// needs to be included
		for (int x = 0; x < lench; x++) {
			char nu = numberz.charAt(x);

			// For loop goes through the username characters with a for loop
			for (int i = 0; i < len; i++) {
				char c = username.charAt(i);

				// Look for white space
				if (Character.isWhitespace(c)) {
					result = false;
				}

				// Look for a number
				else if (c == nu) {
					result = true;
				}
			}
		}
		return result;
	}
}
