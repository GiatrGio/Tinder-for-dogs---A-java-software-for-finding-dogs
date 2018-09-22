package gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Team D
 * @description Allows user to edit their account info (including a very unsafe
 *              implementation of edit password).
 * @see ViewMyAccountPanel, which hosts the edit account button that directs to
 *      here.
 */
public class EditAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField userBio;
	private JTextField passWord;
	private JTextField eMail;
	private User currentingelogd;

	public EditAccountPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("First name");
		lblNewLabel.setBounds(70, 108, 80, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Last name");
		lblNewLabel_1.setBounds(70, 133, 80, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("User bio");
		lblNewLabel_3.setBounds(70, 183, 80, 14);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(70, 208, 80, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(70, 233, 80, 14);
		add(lblNewLabel_5);

		JLabel lblPleaseEditThe = new JLabel("Edit your profile:");
		lblPleaseEditThe.setBounds(175, 36, 284, 14);
		add(lblPleaseEditThe);

		JButton btnOk = new JButton("Save changes");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editUserAttempt();
			}
		});
		btnOk.setBounds(175, 305, 209, 31);
		add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getViewMyAccountPanel());
			}
		});
		btnCancel.setBounds(175, 339, 209, 31);
		add(btnCancel);

	}

	/**
	 * to edit the user, the attributes are gotten and applied to currentingelogd
	 */
	public void editUserAttempt() {
		String firstname = firstName.getText();
		String lastname = lastName.getText();
		String userbio = userBio.getText();
		String password = passWord.getText();
		String email = eMail.getText();

		currentingelogd.setAttributes(password, firstname, email, lastname, userbio);

		((ViewMyAccountPanel) MainFrame.getMainFrame().getViewMyAccountPanel()).updateTexts();
		MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getViewMyAccountPanel());
		DialogMessage yay = new DialogMessage("Success");
		yay.setModal(true);
		yay.setVisible(true);
	}

	/**
	 * the current user's information is gotten and placed in the text fields
	 */
	public void setTexts() {
		currentingelogd = Session.getInstance().getLoggedinUser();

		firstName = new JTextField(currentingelogd.getFirstname());
		firstName.setBounds(175, 105, 209, 20);
		add(firstName);
		firstName.setColumns(10);

		lastName = new JTextField(currentingelogd.getLastname());
		lastName.setBounds(175, 130, 209, 20);
		add(lastName);
		lastName.setColumns(10);

		userBio = new JTextField(currentingelogd.getUserBio());
		userBio.setBounds(175, 180, 209, 20);
		add(userBio);
		userBio.setColumns(10);

		passWord = new JTextField(currentingelogd.getPassword());
		passWord.setBounds(175, 205, 209, 20);
		add(passWord);
		passWord.setColumns(10);

		eMail = new JTextField(currentingelogd.getEmail());
		eMail.setBounds(175, 230, 209, 20);
		add(eMail);
		eMail.setColumns(10);
	}

}
