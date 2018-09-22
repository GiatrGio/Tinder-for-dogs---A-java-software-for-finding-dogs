package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.*;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

/**
 * @author Team D
 * @description Allows the the user to create a new user account by filling in
 *              the fields. Some fields (e.g. username, password) must meet
 *              stringent specifications.
 * @see MainPanel, which hosts the Create Account button that opens this panel.
 */
public class CreateUserPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField userName;
	private JTextField userBio;
	private JTextField passWord;
	private JTextField eMail;
	private JCheckBox chckbxAgree;
	private JButton btnViewUserAgreement;
	private JButton btnCancel;
	private UserBook currentusers = MainFrame.getMainFrame().getUserBook();

	/**
	 * Create the panel.
	 */
	public CreateUserPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 500));

		JLabel lblPleaseFillIn = new JLabel("Please fill in the following fields and agree to the user agreement:");
		lblPleaseFillIn.setBounds(51, 61, 314, 14);
		add(lblPleaseFillIn);

		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setBounds(51, 108, 100, 14);
		add(lblNewLabel);

		firstName = new JTextField();
		firstName.setBounds(173, 105, 194, 20);
		add(firstName);
		firstName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setBounds(51, 133, 100, 14);
		add(lblNewLabel_1);

		lastName = new JTextField();
		lastName.setBounds(173, 130, 194, 20);
		add(lastName);
		lastName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(51, 158, 100, 14);
		add(lblNewLabel_2);

		userName = new JTextField();
		userName.setBounds(173, 155, 194, 20);
		add(userName);
		userName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("User bio");
		lblNewLabel_3.setBounds(51, 183, 100, 14);
		add(lblNewLabel_3);

		userBio = new JTextField();
		userBio.setBounds(173, 180, 194, 20);
		add(userBio);
		userBio.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(51, 208, 100, 14);
		add(lblNewLabel_4);

		passWord = new JTextField();
		passWord.setBounds(173, 205, 194, 20);
		add(passWord);
		passWord.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(51, 233, 100, 14);
		add(lblNewLabel_5);

		eMail = new JTextField();
		eMail.setBounds(173, 230, 194, 20);
		add(eMail);
		eMail.setColumns(10);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryToCreateAccount();
			}
		});
		btnCreate.setBounds(173, 435, 97, 23);
		add(btnCreate);

		chckbxAgree = new JCheckBox("I agree");
		chckbxAgree.setBounds(51, 261, 97, 23);
		add(chckbxAgree);

		btnViewUserAgreement = new JButton("View user agreement");
		btnViewUserAgreement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAgreement useragreement = new UserAgreement();
				useragreement.setModal(true);
				useragreement.setVisible(true);
			}
		});
		btnViewUserAgreement.setBounds(173, 261, 194, 23);
		add(btnViewUserAgreement);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getMainPanel());
				firstName.setText(null);
				lastName.setText(null);
				userName.setText(null);
				userBio.setText(null);
				passWord.setText(null);
				eMail.setText(null);
				chckbxAgree.setSelected(false);
			}
		});
		btnCancel.setBounds(280, 435, 85, 23);
		add(btnCancel);

		JTextArea textArea = new JTextArea("the password should: \n-be between 8-20 characters long\n-"
				+ "contain a special character \n-should not contain any white "
				+ "spaces\nYour username should:\n-be between 8-20 characters long\n-contain at least 1 number");
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(51, 304, 298, 135);
		add(textArea);

	}

	public void tryToCreateAccount() {
		String firstname = firstName.getText();
		String lastname = lastName.getText();
		String username = userName.getText();
		String userbio = userBio.getText();
		String password = passWord.getText();
		String email = eMail.getText();

		boolean ProperUsername = (new AcceptableUser()).isAcceptableUserName(username);
		boolean ProperPassword = (new AcceptableUser()).isAcceptablePassword(password);
		boolean agreed = chckbxAgree.isSelected();

		if (agreed && ProperUsername && ProperPassword) {
			User newuser = new User(username, password, firstname, email, lastname, userbio);
			currentusers.createUser(newuser);
			DialogMessage yay = new DialogMessage("Success");
			yay.setModal(true);
			yay.setVisible(true);
			MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getMainPanel());
		}

		else {
			String errorCode;
			// errors should be checked for username validity, password validity, and user
			// agreement being checkboxed, and display error message in that order
			if (!ProperUsername) {
				errorCode = "BadUsername";
			} else if (!ProperPassword) {
				errorCode = "BadPassword";
			} else if (!agreed) {
				errorCode = "UnacceptedAgreement";
			} else {
				errorCode = "Unknown";
			}

			DialogMessage meh = new DialogMessage(errorCode);
			meh.setModal(true);
			meh.setVisible(true);
		}
	}
}
