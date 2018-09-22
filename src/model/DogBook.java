package model;

import java.util.ArrayList;

public class DogBook {
	private ArrayList<Dog> dogs; // List of dog per User
	private ArrayList<Dog> deletedDogs; // List of dogs that are deleted

	// CONSTRUCTOR
	public DogBook(ArrayList<Dog> dogeys) {
		this.setDogs(dogeys);
		this.deletedDogs = new ArrayList<Dog>();
	}

	// GET METHODS
	public ArrayList<Dog> getDogs() {
		return dogs;
	}

	// GET EDITED DOGS
	public ArrayList<Dog> getEditedDogs() {
		ArrayList<Dog> editeddogs = new ArrayList<Dog>();
		for (Dog d : dogs) {
			if (d.getEditFlag()) {
				editeddogs.add(d);
			}
		}
		return editeddogs;
	}

	// GET NEW DOGS
	public ArrayList<Dog> getNewDogs() {
		ArrayList<Dog> newdogs = new ArrayList<Dog>();
		for (Dog d : dogs) {
			if (d.getDogTag() < 0) {
				newdogs.add(d);
			}
		}
		return newdogs;
	}

	// GET DELETED DOGS
	public ArrayList<Dog> getDeletedDogs() {
		return deletedDogs;
	}

	// GET DOG BY INDEX METHOD
	public Dog getDog(int i) {
		return dogs.get(i);
	}

	// GET DOGS OF SPECIFIC OWNER METHOD
	public ArrayList<Dog> getDogsOfUser(User user) {
		ArrayList<Dog> foundDogs = new ArrayList<Dog>();
		for (Dog d : dogs) {
			if (d.getOwner().equals(user)) {
				foundDogs.add(d);
			}
		}
		return foundDogs;
	}

	// GET DOGS OF SPECIFIC OWNER METHOD
	public ArrayList<Dog> getDogsExceptOfUser(User user) {
		ArrayList<Dog> foundDogs = new ArrayList<Dog>();
		for (Dog d : dogs) {
			if (!d.getOwner().equals(user)) {
				foundDogs.add(d);
			}
		}
		return foundDogs;
	}
	

	// GET LIKED DOGS
		public ArrayList<Dog> getLikedDogs() {
			ArrayList<Dog> foundDogs = new ArrayList<Dog>();
			for (Dog d : dogs) {
				if (d.getIsLiked()) {
					foundDogs.add(d);
				}
			}
			return foundDogs;
		}

	// GET NUMBER OF LIKED DOGS
	public int getNoOfLikedDogs() {
		int counter = 0;
		for (Dog d : dogs) {
			if (d.getIsLiked()) {
				counter++;
			}
		}
		return counter;
	}

	// DOGTAG of dog
	public Dog getDogbyTag(Integer tag) {
		for (Dog d : dogs) {
			if (d.getDogTag().equals(tag)) {
				return d;
			}
		}
		return null;

	}

	// SET METHODS
	public void setDogs(ArrayList<Dog> dogs) {
		this.dogs = dogs;
	}

	// ADD DOG METHOD
	public void addDog(Dog doggie) {
		dogs.add(doggie);
	}

	// DELETE DOG METHOD
	public void deleteDog(Dog doggie) {
		if (doggie.getDogTag() > 0) {
			deletedDogs.add(doggie);
		}
		dogs.remove(doggie);
	}

	public void deleteDogWithTag(Integer tag) {
		Dog doggie = getDogbyTag(tag);
		if (doggie.getDogTag() > 0) {
			deletedDogs.add(doggie);
		}
		dogs.remove(doggie);
	}

}