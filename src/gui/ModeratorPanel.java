package gui;

import javax.swing.JPanel;

import model.Session;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

/**
* @author Team D
* @description Allows moderators to remove or edit offensive dogs or remove belligerent users. 
* @see ShowAllUsersPanel and ShowMyDogsPanel, which the moderator has special access to in order to moderate.
*/
public class ModeratorPanel extends JPanel {

	private JButton btnEditUsersProfile;
	private JButton btnEditDogInfo;
	private JButton btnNewButton;
	private static final long serialVersionUID = 1L;

	public ModeratorPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		// moderators can delete users who misbehave or post inappropriate content
		btnEditUsersProfile = new JButton("delete a user");
		btnEditUsersProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ListModelPanel) MainFrame.getMainFrame().getShowAllUsersPanel()).fillJList();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowAllUsersPanel());
			}
		});

		btnEditUsersProfile.setBounds(71, 115, 304, 23);
		add(btnEditUsersProfile);

		// moderators can edit or delete dogs as well
		btnEditDogInfo = new JButton("edit or delete dogs");
		btnEditDogInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ShowMyDogsPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).fillJList();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
				((ShowMyDogsPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).moderatingFunction();
			}
		});
		btnEditDogInfo.setBounds(71, 80, 304, 23);
		add(btnEditDogInfo);

		btnNewButton = new JButton("Back to main screen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).fillJList();
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
				Session.getInstance().getLoggedinUser().setIsModerating(false);

			}
		});
		btnNewButton.setBounds(71, 150, 302, 23);
		add(btnNewButton);

	}
}
