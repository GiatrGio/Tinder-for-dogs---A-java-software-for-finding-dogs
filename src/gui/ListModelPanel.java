package gui;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
* @author Team D
* @description Provides an interface for ShowMyDogsPanel, ShowLikedDogsPanel, and ShowAllUsersPanel (Moderator functionality).
* @see ShowMyDogsPanel, ShowLikedDogsPanel, and ShowAllUsersPanel, which implement this interface.
*/
public interface ListModelPanel {

	void fillJList();

	DefaultListModel<String> getListModel();
	JList getListForJ();

}