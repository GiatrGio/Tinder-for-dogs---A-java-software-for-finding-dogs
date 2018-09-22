package database;

import model.Dog;
import model.DogBook;
import model.Session;
import model.User;
import model.UserBook;

import java.util.ArrayList;

/**
* Prepares logout by committing all changes to the database
* @author Team D
* @see WriteBack, which is extensively used.
*/
public class LogOut {
	
	// Adding a user to the database upon logging out
	public void addUserDB(UserBook userbook) {
		ArrayList<User> list = userbook.getNewUsers();
		for (User u : list) {
			WriteBack.getInstance().addUser(u);
		}
	}

	// editing a user in the database upon logging out
	public void editUserDB(UserBook userbook) {
		ArrayList<User> list = userbook.getEditedUsers();
		for (User u : list) {
			WriteBack.getInstance().editUser(u);
		}
	}
	
	// deleting a user in the database upon logging out
	public void deleteUsersDB(UserBook userbook) {
		ArrayList<User> list = userbook.getDeletedUsers();
		for (User u : list) {
			WriteBack.getInstance().deleteUser(u);
		}
	}

	// adding Dogs in the database upon logging out
	public void addDogsDB(DogBook dogbook) {
		ArrayList<Dog> list = dogbook.getNewDogs();
		for (Dog d : list) {
			if (d.getDogimage() == null) {
				WriteBack.getInstance().addDog(d);
			} 
			else {WriteBack.getInstance().addDogWPic(d);
			}
		}
	}
	
	// editing Dogs in the database upon logging out 
	public void editDogsDB(DogBook dogbook) {
		ArrayList<Dog> list = dogbook.getEditedDogs();
		for (Dog d : list) {
			if(d.getImageEditflag() && d.getDogimage() != null) {
				WriteBack.getInstance().editDogWPic(d);
			}
			else {
				WriteBack.getInstance().editDog(d);
			}
		}
	}

	// deleting Dogs in the database upon logging out
	public void deleteDogsDB(DogBook dogbook) {
		ArrayList<Dog> list = dogbook.getDeletedDogs();
		for (Dog d : list) {
			WriteBack.getInstance().deleteDog(d);
		}
	}
	
	// commiting liked dogs to the database upon logging out
	public void commitLikedDogsDB(DogBook dogbook) {
		ArrayList<Dog> list = dogbook.getLikedDogs();
		for (Dog d : list) {
			if(d.getIsNewlyLiked()){ 
				WriteBack.getInstance().commitDogLikes(Session.getInstance().getLoggedinUser(), d);
			}
		}
	}
	
	// calling all methods above upon logout.
	public void finalLogOut(DogBook dogbook, UserBook userbook) {
		addUserDB(userbook);
		editUserDB(userbook);
		deleteUsersDB(userbook);
		addDogsDB(dogbook);
		editDogsDB(dogbook);
		deleteDogsDB(dogbook);
		commitLikedDogsDB(dogbook);
		
	}
	
}
