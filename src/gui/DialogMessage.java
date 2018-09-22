// GUI part of the error message


package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
* @author Team D
* @description Hosts various error messages that may occur throughout the program, usually when the user input is wrong.
* @see The whole program, error messages are created throughout.
*/
public class DialogMessage extends JDialog {

	private static final long serialVersionUID = 1L;

	public DialogMessage(String errorCode) {		
		getContentPane().setLayout(null);
		setBounds(100, 100, 430, 200);
		String displayedString = "";

	    switch(errorCode) {
	    	case "BadPassword" :
	    		displayedString = "Your password does not meet the requirements";
	    		break;
	    	case "BadUsername" :
	    		displayedString = "Your username does not meet the requirements";
	    		break;
	    	case "UnacceptedAgreement" :
	    		displayedString = "You have to accept the user agreement, silly";
	    		break;
	    	case "AllDogsAreLiked" :
	    		displayedString = "<html> You have liked all the available dogs! <br> I guess it is time for you to look for a new hobby</html>";
	    		break;
	    	case "Success" :
	    		displayedString = "You have succesfully created an account!";
	    		break;
	    	case "Failure" :
	    		displayedString = "Login failed";
	    		break;
	    	case "EmptySelection" :
	    		displayedString = "You did not select anything";
	    		break;
	    	case "loginWrong" :
	    		displayedString = "<html>Wrong password and/or username,<br>please try again or create a new account.</html>";
	    		break;
	    	case "Unknown" :
	    		displayedString = "Unknown error";
	    		break;
	    }
	    	    
	    // display the error
		JLabel lblErrorDescription = new JLabel(displayedString);
		lblErrorDescription.setBounds(51, 13, 314, 53);
		getContentPane().add(lblErrorDescription);
				
		// Button that will close the error message 
		JButton btnClose = new JButton("Close Message");
		btnClose.setBounds(51, 110, 314, 20);
		getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
