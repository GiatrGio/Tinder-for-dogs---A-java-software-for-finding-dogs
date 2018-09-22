package gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
 * @description Shows all dogs you own and allows you to add new ones, edit
 *              existing dogs, or delete dogs.
 * @see ListModelPanel, which is the JList interface this Panel uses.
 *      ShowLikedDogsPanel, which is almost identical in setup. AddDogPanel,
 *      which can be accessed with the add dog button and adds dogs to this
 *      list. EditDogPanel (Edit Dog button).
 */
public class ShowMyDogsPanel extends JPanel implements ListModelPanel, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private ArrayList<Dog> ownersdogs;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private DogBook dogbook = MainFrame.getMainFrame().getDogBook();
	private JList listforJ;
	private JButton btnAddDog;
	private boolean moderatorworking; // boolean to know if this class is
										// working under moderator access or not
	private JLabel lblNumberOfLikes;

	/**
	 * Create the panel.
	 */
	public ShowMyDogsPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		btnAddDog = new JButton("Add dog");
		btnAddDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getAddDogPanel());
			}
		});
		btnAddDog.setBounds(334, 61, 120, 40);
		add(btnAddDog);

		JButton btnEditDog = new JButton("Edit dog");
		btnEditDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listforJ.isSelectionEmpty()) {
					DialogMessage meh = new DialogMessage("EmptySelection");
					meh.setModal(true);
					meh.setVisible(true);
				} else {
					tryToEditDog();
				}
			}
		});
		btnEditDog.setBounds(334, 112, 120, 40);
		add(btnEditDog);

		JButton btnDeleteDog = new JButton("Delete dog");
		btnDeleteDog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listforJ.isSelectionEmpty()) {
					DialogMessage meh = new DialogMessage("EmptySelection");
					meh.setModal(true);
					meh.setVisible(true);
				} else {
					tryToDeleteDog();
				}
			}
		});

		btnDeleteDog.setBounds(334, 162, 120, 40);
		add(btnDeleteDog);

		JButton btnBackToMain = new JButton("Back to previous screen");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (moderatorworking) {
					MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getModeratorPanel());
				} else {
					MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
				}
			}
		});
		btnBackToMain.setBounds(38, 218, 284, 40);
		add(btnBackToMain);

		JLabel lblTheseAreYour = new JLabel("These are your dogs, you can select them in the menu below.");
		lblTheseAreYour.setBounds(38, 31, 416, 14);
		add(lblTheseAreYour);

		listforJ = new JList();
		listforJ.setVisibleRowCount(1);
		listforJ.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listforJ.setLayoutOrientation(JList.VERTICAL);
		listforJ.setBounds(38, 62, 284, 143);
		add(listforJ);

		ListSelectionListener sl = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					try {
						lblNumberOfLikes.setText("Number of likes: "
								+ Integer.toString((ownersdogs.get(listforJ.getSelectedIndex()).getLikes())));
					} catch (java.lang.ArrayIndexOutOfBoundsException f) {
						lblNumberOfLikes.setText("Number of likes: ");
					}
				}
			}
		};

		listforJ.addListSelectionListener(sl);

		JScrollPane scrollPane = new JScrollPane(listforJ);
		scrollPane.setBounds(38, 62, 284, 143);
		add(scrollPane);

		lblNumberOfLikes = new JLabel("Number of likes: ");
		lblNumberOfLikes.setBounds(334, 231, 120, 14);
		add(lblNumberOfLikes);

	}

	public void moderatingFunction() {
		if (Session.getInstance().getLoggedinUser().getIsModerating() == true) {
			btnAddDog.setEnabled(false);
		} else {
			btnAddDog.setEnabled(true);
		}
	}

	// fillJList working under moderator access
	public void fillJList() {
		
		if (Session.getInstance().getLoggedinUser().getIsModerating()) {
			ownersdogs = dogbook.getDogs();
			moderatorworking = true;
		} else {
			ownersdogs = dogbook.getDogsOfUser(Session.getInstance().getLoggedinUser());
		}
		listModel.clear();
		for (Dog dog : ownersdogs) {
			listModel.addElement(dog.getName());
		}
		listforJ.setModel(listModel);

	}

	public void updateOwnersDogs() {
		ownersdogs = dogbook.getDogsOfUser(Session.getInstance().getLoggedinUser());
	}

	public void tryToEditDog() {
		((EditDogPanel) MainFrame.getMainFrame().getEditDogPanel()).setTexts();
		MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getEditDogPanel());
	}

	public void tryToDeleteDog() {
		dogbook.deleteDog(ownersdogs.get(listforJ.getSelectedIndex()));
		listModel.remove(listforJ.getSelectedIndex());
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList getListForJ() {
		return listforJ;
	}

	public boolean getModeratorWorking() {
		return moderatorworking;
	}

	public void valueChanged(ListSelectionEvent e) {
		// needs to be here because of errors but is also already up top.
	}

}
