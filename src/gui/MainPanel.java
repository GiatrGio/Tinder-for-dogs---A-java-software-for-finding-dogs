package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DataRetrieval;
import model.*;
import javax.swing.JCheckBox;

/**
 * @author Team D Shows the interface that the user sees when opens the program
 *         The user can Create an account. The Create an account button leads to
 *         the menu for creating the account The user can enter her/his id and
 *         password and enter the LoggedInMainPanel The user can choose or not
 *         he wants his password letter to be revealed or not
 * @see CreateUserPanel and LoggedInMainPanel, which can be accessed by clicking
 *      the create user button and logging in successfully, respectively.
 */

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JButton btnLogIn;
	private JButton btnCreateAccount;
	private UserBook currentusers = MainFrame.getMainFrame().getUserBook();
	private ImageFileReactor imgfr = new ImageFileReactor();
	private SoundEffects sound = new SoundEffects();

	public MainPanel() {
		setPreferredSize(new Dimension(492, 457));
		setLayout(null);

		JLabel lblWelcomeToTinderfordogs = new JLabel("Welcome to TinderForDogs!");
		lblWelcomeToTinderfordogs.setBounds(168, 217, 228, 14);
		add(lblWelcomeToTinderfordogs);

		txtUsername = new JTextField();
		txtUsername.setBounds(168, 243, 106, 20);
		add(txtUsername);
		txtUsername.setColumns(10);

		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(168, 275, 106, 20); // radiobuton showpassword
		add(pwdPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(98, 246, 70, 14);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(98, 278, 70, 14);
		add(lblPassword);

		btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryToLogIn();
				
			}
		});
		btnLogIn.setBounds(214, 317, 89, 23);
		add(btnLogIn);

		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setBounds(192, 363, 157, 14);
		add(lblDontHaveAn);

		btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getCreateUserPanel());
				txtUsername.setText(null);
				pwdPassword.setText(null);
				sound.playWoof();
			}
		});
		btnCreateAccount.setBounds(160, 389, 188, 23);
		add(btnCreateAccount);

		// checkbox to show/hide password
		JCheckBox chckbxShowPassword = new JCheckBox("show password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					pwdPassword.setEchoChar((char) 0);

				} else {
					pwdPassword.setEchoChar('*');
				}
			}

		});
		chckbxShowPassword.setBounds(291, 273, 129, 23);
		add(chckbxShowPassword);

		// drawing of the dog in the mainpanel
		JLabel lblDogDrawing = new JLabel("DogDrawing");
		lblDogDrawing.setBounds(161, 23, 188, 188);
		add(lblDogDrawing);
		BufferedImage bufimg = imgfr.readImageDisplayer("dogdrawingdonotremove");
		if (bufimg != null) {
			lblDogDrawing.setIcon(new ImageIcon(bufimg));
			lblDogDrawing.setBackground(new Color(51, 153, 0));
			add(lblDogDrawing);
		}

	}

	public void addNotify() {
		super.addNotify();
		MainFrame.getMainFrame().getMainPanel().getRootPane().setDefaultButton(btnLogIn);
	}

	// gets the text from username and password, then login tried.
	public void tryToLogIn() {
		String username = txtUsername.getText();
		String password = String.valueOf(pwdPassword.getPassword());

		boolean result = currentusers.logIn(username, password);

		if (result) {
			Session.getInstance().setLoggedinUser(currentusers.getUserByUsername(username));
			initializePanels();
			MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
			((LoggedInMainPanel) MainFrame.getMainFrame().getLoggedInMainPanel()).enableButton();
			sound.playWoofGroove(); // plays the dog woof
		
		} else {
			DialogMessage boo = new DialogMessage("loginWrong");
			boo.setModal(true);
			boo.setVisible(true);
			sound.playHoundDog(); // plays the dog cry
		
		}
	}

	public void initializePanels() {
		((LoggedInMainPanel) MainFrame.getMainFrame().getLoggedInMainPanel()).setModStatusText();
		((ViewMyAccountPanel) MainFrame.getMainFrame().getViewMyAccountPanel()).setTexts();
		((EditAccountPanel) MainFrame.getMainFrame().getEditAccountPanel()).setTexts();
		((ListModelPanel) MainFrame.getMainFrame().getShowLikedDogsPanel()).fillJList();
		((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).fillJList();
		((ShowDogsPanel) MainFrame.getMainFrame().getShowDogsPanel()).makeDogList();
		((ShowDogsPanel) MainFrame.getMainFrame().getShowDogsPanel()).setFields();
		DataRetrieval.getInstance().setIsLiked(MainFrame.getMainFrame().getDogBook(),
				Session.getInstance().getLoggedinUser());

	}
}
