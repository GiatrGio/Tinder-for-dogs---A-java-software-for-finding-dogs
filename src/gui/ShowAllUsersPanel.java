package gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import model.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;

/**
* @author Team D
* @description Moderator-only, lists all users and allows the moderator to remove 
* @see ShowAllUsersPanel and ShowMyDogsPanel, which the moderator has special access to in order to moderate.
*/
public class ShowAllUsersPanel extends JPanel implements ListModelPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> allusers;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private UserBook userbook = MainFrame.getMainFrame().getUserBook();
	private JList listforJ;

	public ShowAllUsersPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listforJ.isSelectionEmpty()) {
					DialogMessage meh = new DialogMessage("EmptySelection");
					meh.setModal(true);
					meh.setVisible(true);
				} else {
					tryToDeleteUser();
				}
			}
		});

		btnDeleteUser.setBounds(334, 162, 120, 40);
		add(btnDeleteUser);

		JButton btnBackToMain = new JButton("Back to moderator panel");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getModeratorPanel());
			}
		});
		btnBackToMain.setBounds(38, 218, 284, 40);
		add(btnBackToMain);

		JLabel lblTheseAreYour = new JLabel("These are all the users, you can select them in the menu below.");
		lblTheseAreYour.setBounds(38, 31, 416, 14);
		add(lblTheseAreYour);

		listforJ = new JList();
		listforJ.setVisibleRowCount(1);
		listforJ.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listforJ.setLayoutOrientation(JList.VERTICAL);
		listforJ.setBounds(38, 62, 284, 143);
		add(listforJ);

		JScrollPane scrollPane = new JScrollPane(listforJ);
		scrollPane.setBounds(38, 62, 284, 143);
		add(scrollPane);

	}

	// get all users except the moderators
	public void fillJList() {
		allusers = userbook.getUsersExceptMods();
		listModel.clear();
		for (User user : allusers) {
			listModel.addElement(user.getUsername());
		}
		listforJ.setModel(listModel);

	}

	public void updateAllusers() {
		allusers = userbook.getUsers();
	}


	public void tryToDeleteUser() {
		userbook.deleteUser(allusers.get(listforJ.getSelectedIndex()));
		listModel.remove(listforJ.getSelectedIndex());
	}

	/* (non-Javadoc)
	 * @see gui.ListModelPanel#getListModel()
	 */
	@Override
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	/* (non-Javadoc)
	 * @see gui.ListModelPanel#getListForJ()
	 */
	@Override
	public JList getListForJ() {
		return listforJ;
	}

}
