package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import database.*;
import model.*;

/**
* @author Team D
* @description Initializes panels and frames and controls their visibility throughout the program. Contains Main method to run the program. 
*/

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// DATA RETRIEVAL
	private UserBook userbook = DataRetrieval.getInstance().filluserbook();
	private DogBook dogbook = DataRetrieval.getInstance().fillDogBook(userbook);

	// Singleton pattern start
	private static MainFrame frame = null; // our instance is called frame
	
	//fields used in class
	private JPanel CreateUserPanel;
	private JPanel LoggedInMainPanel;
	private JPanel mainPanel;
	private JPanel ShowDogsPanel;
	private JPanel viewMyAccountPanel;
	private JPanel moderatorPanel;
	private JPanel EditAccountPanel;
	private JPanel ShowMyDogsPanel;
	private JPanel AddDogPanel;
	private JPanel EditDogPanel;
	private JPanel ModeratorDeleteDogPanel;
	private JPanel ShowAllUsersPanel;
	private JPanel ShowLikedDogsPanel;
	private JPanel ShowLikedDogInfoPanel;

	/**
	 * Protected constructor for the MainFrame
	 */
	protected MainFrame() {  //this generator is protected so nobody can erroneously create a second instance from another file
		setTitle("TinderForDogs (c)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 500, 500);
	} 

	/**
	 * It returns the frame MainFrame
	 * @return MainFrame
	 */
	public static MainFrame getMainFrame() {  //this is our name for getInstance 
		if (frame == null) {
			frame = new MainFrame();
		}
		return frame;
	}
	//end singleton pattern definition

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					getMainFrame().initializePanels();
					getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method for initializing all panels
	 */
	public void initializePanels() {
		frame.setMainPanel(new MainPanel());
		frame.setLoggedInMainPanel(new LoggedInMainPanel());
		frame.setCreateUserPanel(new CreateUserPanel());
		frame.setShowDogsPanel(new ShowDogsPanel());
		frame.setShowMyDogsPanel(new ShowMyDogsPanel());
		frame.setViewMyAccountPanel(new ViewMyAccountPanel());
		frame.setModeratorPanel(new ModeratorPanel());
		frame.setEditAccountPanel(new EditAccountPanel());
		frame.setAddDogPanel(new AddDogPanel());
		frame.setEditDogPanel(new EditDogPanel());
		frame.setContentPane(frame.getMainPanel());
		frame.setShowAllUsersPanel(new ShowAllUsersPanel());
		frame.setShowLikedDogsPanel(new ShowLikedDogsPanel());
		frame.setShowLikedDogInfoPanel(new ShowLikedDogInfoPanel());
	}

	/**
	 * Method for set panels
	 * @param panel
	 */
	public void setPanel(JPanel panel) {
		frame.setContentPane(panel);
		panel.setVisible(true);
		panel.setSize(500, 500);
	}

	/**
	 * It returns the UserBook object which contains all users
	 * @return	userbook UserBook
	 */
	public UserBook getUserBook() {
		return userbook;
	}

	/**
	 * It returns the UserBook object which contains all users
	 * @return	dogbook DogBook
	 */
	public DogBook getDogBook() {
		return dogbook;
	}

	public MainPanel getMainPanel() {
		return (MainPanel) mainPanel;
	}

	private void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public JPanel getShowMyDogsPanel() {
		return ShowMyDogsPanel;
	}

	public JPanel getCreateUserPanel() {
		return CreateUserPanel;
	}

	public void setCreateUserPanel(JPanel createUserPanel) {
		CreateUserPanel = createUserPanel;
	}

	public JPanel getLoggedInMainPanel() {
		return LoggedInMainPanel;
	}

	public void setLoggedInMainPanel(JPanel loggedInMainPanel) {
		LoggedInMainPanel = loggedInMainPanel;
	}

	public JPanel getShowDogsPanel() {
		return ShowDogsPanel;
	}

	public void setShowDogsPanel(JPanel showDogsPanel) {
		ShowDogsPanel = showDogsPanel;
	}

	public JPanel getViewMyAccountPanel() {
		return viewMyAccountPanel;
	}

	public void setViewMyAccountPanel(JPanel viewMyAccountPanel) {
		this.viewMyAccountPanel = viewMyAccountPanel;
	}

	public JPanel getModeratorPanel() {
		return moderatorPanel;
	}

	public void setModeratorPanel(JPanel moderatorPanel) {
		this.moderatorPanel = moderatorPanel;
	}

	public JPanel getEditAccountPanel() {
		return EditAccountPanel;
	}

	public void setEditAccountPanel(JPanel editAccountPanel) {
		EditAccountPanel = editAccountPanel;
	}

	public void setShowMyDogsPanel(JPanel showMyDogsPanel) {
		ShowMyDogsPanel = showMyDogsPanel;
	}

	public JPanel getAddDogPanel() {
		return AddDogPanel;
	}

	public void setAddDogPanel(JPanel addDogPanel) {
		AddDogPanel = addDogPanel;
	}

	public JPanel getEditDogPanel() {
		return EditDogPanel;
	}

	public void setEditDogPanel(JPanel editDogPanel) {
		EditDogPanel = editDogPanel;
	}

	public JPanel getModeratorDeleteDogPanel() {
		return ModeratorDeleteDogPanel;
	}

	public void setModeratorDeleteDogPanel(JPanel moderatorDeleteDogPanel) {
		ModeratorDeleteDogPanel = moderatorDeleteDogPanel;
	}

	public JPanel getShowAllUsersPanel() {
		return ShowAllUsersPanel;
	}

	public void setShowAllUsersPanel(JPanel showAllUsersPanel) {
		ShowAllUsersPanel = showAllUsersPanel;
	}
	
	public void setShowLikedDogsPanel(JPanel showLikedDogsPanel) {
		ShowLikedDogsPanel = showLikedDogsPanel;
	}
	
	public JPanel getShowLikedDogsPanel() {
		return ShowLikedDogsPanel;
	}

	public JPanel getShowLikedDogInfoPanel() {
		return ShowLikedDogInfoPanel;
	}

	public void setShowLikedDogInfoPanel(JPanel showLikedDogInfoPanel) {
		ShowLikedDogInfoPanel = showLikedDogInfoPanel;
	}

}
