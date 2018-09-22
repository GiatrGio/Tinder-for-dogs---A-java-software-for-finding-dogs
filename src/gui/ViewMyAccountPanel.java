package gui;

import javax.swing.JPanel;

import database.LogOut;
import model.*;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

/**
* @author Team D
* @description Shows your personal information and allows you to click buttons to edit or delete your account. 
* @see LoggedInMainPanel, which has the 'View my Account' button. EditAccountPanel, which can be accessed by the 'Edit Account' button.
*/
public class ViewMyAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblActualUsername;
	private JLabel lblActualFirstname;
	private JLabel lblActualLastName;
	private JLabel lblActualUserBio;
	private JLabel lblActualEmail;
	private JLabel lblActualPassword;
	private UserBook userbook; 
	private User currentingelogd;
	
	public ViewMyAccountPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500,500));
		
		JLabel lblThisIsYour = new JLabel("This is your account:");
		lblThisIsYour.setBounds(52, 33, 187, 14);
		add(lblThisIsYour);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(52, 86, 80, 14);
		add(lblUsername);
		
		JLabel lblFirstname = new JLabel("First name");
		lblFirstname.setBounds(52, 111, 80, 14);
		add(lblFirstname);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setBounds(52, 138, 80, 14);
		add(lblLastName);
		
		JLabel lblUserBio = new JLabel("User bio");
		lblUserBio.setBounds(52, 163, 80, 14);
		add(lblUserBio);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(52, 188, 80, 14);
		add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 213, 80, 14);
		add(lblPassword);
		
		JButton btnNewButton_5 = new JButton("Edit account");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getEditAccountPanel());
			}
		});
		btnNewButton_5.setBounds(52, 251, 156, 23);
		add(btnNewButton_5);
		
		JButton btnBackToMain = new JButton("Back to main screen");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
			}
		});
		btnBackToMain.setBounds(52, 323, 156, 23);
		add(btnBackToMain);
		
		JButton btnDeleteAccount = new JButton("Delete account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryToDeleteAccount();
			}
		});
		btnDeleteAccount.setBounds(52, 287, 156, 23);
		add(btnDeleteAccount);

	}
	
	/**
	 * Deletion of the account 
	 */
	public void tryToDeleteAccount() {
		userbook = MainFrame.getMainFrame().getUserBook();
		currentingelogd = Session.getInstance().getLoggedinUser();
		userbook.deleteUser(currentingelogd);
		
		LogOut newLogout = new LogOut();
		newLogout.finalLogOut(MainFrame.getMainFrame().getDogBook(), MainFrame.getMainFrame().getUserBook());
		
		MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getMainPanel());
	}
	
	public void setTexts(){
		currentingelogd = Session.getInstance().getLoggedinUser();
		lblActualUsername = new JLabel(currentingelogd.getUsername());
		lblActualUsername.setBounds(152, 86, 150, 14);
		add(lblActualUsername);
		
		lblActualFirstname = new JLabel(currentingelogd.getFirstname());
		lblActualFirstname.setBounds(152, 111, 150, 14);
		add(lblActualFirstname);
		
		lblActualLastName = new JLabel(currentingelogd.getLastname());
		lblActualLastName.setBounds(152, 138, 150, 14);
		add(lblActualLastName);
		
		lblActualUserBio = new JLabel(currentingelogd.getUserBio());
		lblActualUserBio.setBounds(152, 163, 150, 14);
		add(lblActualUserBio);
		
		lblActualEmail = new JLabel(currentingelogd.getEmail());
		lblActualEmail.setBounds(152, 188, 150, 14);
		add(lblActualEmail);
		
		lblActualPassword = new JLabel(currentingelogd.getPassword());
		lblActualPassword.setBounds(152, 213, 150, 14);
		add(lblActualPassword);

	}
	
	public void updateTexts() {
		lblActualFirstname.setText(currentingelogd.getFirstname());
		lblActualLastName.setText(currentingelogd.getLastname());
		lblActualUserBio.setText(currentingelogd.getUserBio());
		lblActualPassword.setText(currentingelogd.getPassword());
		lblActualEmail.setText(currentingelogd.getEmail());
	}

}
