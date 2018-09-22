package test;

import model.Dog;
import model.DogBook;
import model.User;
import model.UserBook;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import database.DataRetrieval;
import junit.framework.TestCase;

public class DogBookTest extends TestCase {
	/*
	 * DogBookTest to test dogbook. Includes test whether dogBookExists, whether we
	 * can add a dog to the index (testAddAndIndexDogBook)
	 */
	public void testdogBookExists() {
		DogBook mydogs = new DogBook(null);
		assertNotNull(mydogs);
	}

	public void testgetDogBook() {
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		DogBook mydogs = new DogBook(dogs);
		assertEquals(dogs, mydogs.getDogs());
	}

	public void testaddAndIndexDogBook() {
		User abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		Dog barry = new Dog(0, "Barry", "pitbull", abel,"green", "M", "Large",
				Calendar.getInstance(), 3);
		Dog henkie = new Dog(1, "Barry", "pitbull", abel,"orange", "F", "Small",
				Calendar.getInstance(), 5);
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		DogBook dogsbook = new DogBook(dogs);
		dogsbook.addDog(barry);
		dogsbook.addDog(henkie);

		assertEquals(barry, dogsbook.getDog(0));
		assertEquals(henkie, dogsbook.getDog(1));
	}

	public void testaddDogAndGetDogsFromUser() {
		User abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		User henk = new User("Henk", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		Dog barry = new Dog(345, "Barry", "pitbull", abel,"purple", "F", "Small",
				Calendar.getInstance(), 12);
		Dog vlekje = new Dog(346, "vlekje", "pitbull", henk,"lila", "F", "Medium",
				Calendar.getInstance(), 0);
		Dog karel = new Dog(347, "karel", "pitbull", henk,"yellow", "M", "XXL",
				Calendar.getInstance(), 21);

		DogBook dogsbook = new DogBook(dogs);
		dogsbook.addDog(barry);
		dogsbook.addDog(vlekje);
		dogsbook.addDog(karel);

		ArrayList<Dog> testdogs = new ArrayList<Dog>();
		testdogs.add(barry);

		assertEquals(testdogs, dogsbook.getDogsOfUser(abel));
	}

	public void testdeleteDog() {
		User abel = new User("Abeltje", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		User henk = new User("Henk", "1234", "Abel", "Abel.Abel@Abel.nl", "Anel", "Anel");
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		Dog barry = new Dog(345, "Barry", "pitbull", abel,"blue", "M", "Medium",
				Calendar.getInstance(), 3);
		Dog vlekje = new Dog(346, "vlekje", "pitbull", henk,"red", "M", "Tiny",
				Calendar.getInstance(), 3);
		Dog karel = new Dog(347, "karel", "pitbull", henk,"vantablack", "F", "Large",
				Calendar.getInstance(), 3);

		DogBook dogsbook = new DogBook(dogs);
		dogsbook.addDog(barry);
		dogsbook.addDog(vlekje);
		dogsbook.addDog(karel);

		dogsbook.deleteDog(barry);
		ArrayList<Dog> testlist = new ArrayList<Dog>();
		testlist.add(vlekje);
		testlist.add(karel);
		assertEquals(testlist, dogsbook.getDogs());

		ArrayList<Dog> testlistdel = new ArrayList<Dog>();
		testlistdel.add(barry);
		assertEquals(testlistdel, dogsbook.getDeletedDogs());
	}

	public void testdbFetch() {
		UserBook AllUserBook = DataRetrieval.getInstance().filluserbook();
		DogBook AllDogBook = DataRetrieval.getInstance().fillDogBook(AllUserBook);
		assertEquals(AllDogBook.getDogbyTag(2).getOwner(), AllUserBook.getUserByUsername("DobbyHasNoMaster"));
	}

}
