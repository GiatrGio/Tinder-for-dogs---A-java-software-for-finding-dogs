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
 * @description Shows all liked dogs in the same way as ShowMyDogsPanel
 * @see ListModelPanel, which is the JList interface this Panel uses.
 *      ShowMyDogsPanel, which is almost identical in setup.
 *      ShowLikedDogInfoPanel, which shows detailed info when you press the
 *      button.
 */
public class ShowLikedDogsPanel extends JPanel implements ListModelPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Dog> likeddogs;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private DogBook dogbook = MainFrame.getMainFrame().getDogBook();
	private JList listforJ;

	/**
	 * Create the panel. in the moderator panel the moderator can see all dogs
	 */
	public ShowLikedDogsPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JButton btnEditDog = new JButton("Show Dog");
		btnEditDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listforJ.isSelectionEmpty()) {
					DialogMessage meh = new DialogMessage("EmptySelection");
					meh.setModal(true);
					meh.setVisible(true);
				} else {
					tryToViewDog();
				}
			}
		});
		btnEditDog.setBounds(334, 112, 120, 40);
		add(btnEditDog);

		JButton btnBackToMain = new JButton("Back to previous screen");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
			}
		});
		btnBackToMain.setBounds(38, 218, 284, 40);
		add(btnBackToMain);

		JLabel lblTheseAreYour = new JLabel("These are the dogs you liked. Select them to find contact info!");
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

	/**
	 * fill the list with liked dogs
	 */
	public void fillJList() {
		likeddogs = dogbook.getLikedDogs();
		listModel.clear();
		for (Dog dog : likeddogs) {
			listModel.addElement(dog.getName());
		}
		listforJ.setModel(listModel);
	}

	public void updateLikedDogs() {
		likeddogs = dogbook.getLikedDogs();
	}

	public void tryToViewDog() {
		((ShowLikedDogInfoPanel) MainFrame.getMainFrame().getShowLikedDogInfoPanel()).setTexts();
		MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowLikedDogInfoPanel());
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList getListForJ() {
		return listforJ;
	}

}
