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
import model.ImageFileReactor;
import model.Session;
import model.SoundControl;
import model.SoundEffects;

import javax.swing.JCheckBox;

/**
* @author Team D
* @description The Logged In Main Panel is the main navigation panel for the user. 
* 				From here, the user may find new dogs to like ('Dog Sniffing'), view their personal info ('View my account')
* 				, View the dogs they own ('View my Dogs') , Moderate (Moderator Menu, greyed out when the user is no moderator) , 
* 				, view the dogs they liked ('Show Liked Dogs'), mute the program's horrible sounds ('Sound' checkbox), and Log Out.
* @see MainPanel, which directs the user to this panel when the login is correct.
*/
public class LoggedInMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnShowDogs;
	private JButton btnModeratorPanel;
	private JButton btnViewMyDogs;
	private JButton btnViewMyAccount;
	private JButton btnLogOut;
	private JButton btnViewLikedDogs;
	private JLabel lblNewLabel_1;
	private ImageFileReactor imgfr = new ImageFileReactor();
	private SoundEffects sound = new SoundEffects();

	public LoggedInMainPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JLabel lblWelcomeToTinderfordogs = new JLabel("Welcome to TinderDogs");
		lblWelcomeToTinderfordogs.setBounds(183, 150, 200, 30);
		add(lblWelcomeToTinderfordogs);

		btnShowDogs = new JButton("Dog Sniffing");
		btnShowDogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setShowDogsPanel(new ShowDogsPanel());
				MainFrame.getMainFrame().getMainPanel().initializePanels();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowDogsPanel());
				((ShowDogsPanel) MainFrame.getMainFrame().getShowDogsPanel()).dogShower();
				sound.playBark();
			}
		});
		btnShowDogs.setBounds(183, 200, 150, 30);
		add(btnShowDogs);

		btnViewMyDogs = new JButton("View my Dogs");
		btnViewMyDogs.setBounds(183, 282, 150, 30);
		btnViewMyDogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setShowMyDogsPanel(new ShowMyDogsPanel());
				MainFrame.getMainFrame().getMainPanel().initializePanels();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
				sound.playWoof();
			}
		});
		add(btnViewMyDogs);

		btnViewMyAccount = new JButton("View my account");
		btnViewMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getViewMyAccountPanel());
				sound.playBark();
			}
		});
		btnViewMyAccount.setBounds(183, 241, 150, 30);
		add(btnViewMyAccount);

		btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AreYouSure dialog = new AreYouSure();
				dialog.setModal(true);
				dialog.setVisible(true);
				sound.playHoundDog();
			}
		});
		btnLogOut.setBounds(183, 405, 150, 30);
		add(btnLogOut);

		btnViewLikedDogs = new JButton("Show Liked Dogs");
		btnViewLikedDogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setShowLikedDogsPanel(new ShowLikedDogsPanel());
				MainFrame.getMainFrame().getMainPanel().initializePanels();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowLikedDogsPanel());
				sound.playBark();
			}
		});
		btnViewLikedDogs.setBounds(183, 364, 150, 30);
		add(btnViewLikedDogs);

		btnModeratorPanel = new JButton("Moderator menu");
		btnModeratorPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getModeratorPanel());
				Session.getInstance().getLoggedinUser().setIsModerating(true);
			}
		});
		btnModeratorPanel.setBounds(183, 323, 150, 30);
		add(btnModeratorPanel);

		JCheckBox chckbxSoundOff = new JCheckBox("Sound off");
		chckbxSoundOff.setBounds(327, 36, 128, 23);
		add(chckbxSoundOff);
		chckbxSoundOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundControl.getInstance().dogBarkControl();
			}
		});

		lblNewLabel_1 = new JLabel("Dog Picture Failed");
		BufferedImage bufimg = imgfr.readImageDisplayer("DogPetite");
		if (bufimg != null) {
			lblNewLabel_1.setIcon(new ImageIcon(bufimg));
			lblNewLabel_1.setBackground(new Color(51, 153, 0));
			lblNewLabel_1.setBounds(21, 27, 150, 150);
			add(lblNewLabel_1);
		}

	}// end of constructor

	public void enableButton() {
		if (Session.getInstance().getLoggedinUser().getIsModerator() == false) {
			btnModeratorPanel.setEnabled(false);
		}
	}

	public void setModStatusText() {
		String userOrModString = "You are logged in as a user";
		if (Session.getInstance().getLoggedinUser().getIsModerator()) {
			userOrModString = "You are logged in as a moderator";
		}
		JLabel lblUserOrModStatus = new JLabel(userOrModString);
		lblUserOrModStatus.setBounds(183, 170, 200, 30);
		add(lblUserOrModStatus);

	}

}
