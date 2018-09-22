package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import model.Dog;
import model.User;
/*
 * DogTest to test the characteristics as given in Dog. 
 */
public class DogTest extends TestCase {

	public void testdogExists() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("bla", "bla", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		assertNotNull(henk);
	}
	
	public void testdogExists1() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog("bla", "bla", Harry,"blue","F","L", cal);
		assertNotNull(henk);
	}

	public void testdogExists2() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog(1234, "bla", "bla", Harry,"orange", "F", "Small",
				Calendar.getInstance(), 12);
		assertNotNull(henk);
	}
	
	public void testdogExists3() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(123, "bla", "bla", Harry,"pink","fluh","fluh", cal, 0);
		assertNotNull(henk);
	}

	public void testSetGetBreed() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		henk.setBreed("pitbull-mix");
		assertEquals("pitbull-mix", henk.getBreed());
	}
	
	public void testGetDogTag() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		Integer a = 4321;
		assertEquals(a, henk.getDogTag());
	}

	public void testGetOwner() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		assertEquals(Harry, henk.getOwner());
	}
	
	public void testSetOwner() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		User Sjaak = new User("Sjaak", "1234", null, null, null, null);
		henk.setOwner(Sjaak);
		assertEquals(Sjaak, henk.getOwner());
	}
	
	public void testGetName() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		assertEquals("karel", henk.getName());
	}
	
	public void testSetName() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		henk.setName("Floempie");
		assertEquals("Floempie", henk.getName());
	}
	
	public void testGetColour() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		assertEquals("pink", henk.getColour());
	}
	
	public void testSetColour() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"Green","fluh","fluh", cal,0);
		henk.setColour("Green");
		assertEquals("Green", henk.getColour());
	}

	public void testGetGender() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		assertEquals("fluh", henk.getGender());
	}
	
	public void testSetGender() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		henk.setGender("Male");
		assertEquals("Male", henk.getGender());
	}
	
	public void testGetSize() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		assertEquals("fluh", henk.getSize());
	}
	
	public void testSetSize() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		henk.setSize("Enormous");
		assertEquals("Enormous", henk.getSize());
	}
	
	public void testGetAge() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		assertEquals("fluh", henk.getSize());
	}
	
	public void testLike() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Calendar cal = new GregorianCalendar(2018,5,4);
		Dog henk = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,0);
		henk.setIsLikedandIncrement();
		assertTrue(henk.getIsLiked());
		assertEquals(1,henk.getLikes());
		Dog bert = new Dog(4321, "bla", "bla", Harry,"pink","fluh","fluh", cal,4);
		bert.setIsLikedandIncrement();
		assertEquals(5,bert. getLikes());
	}
	

	public void testCreateFlag() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		assertFalse(henk.getEditFlag());
	}

	public void testGetSetFlag() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		henk.setBreed("sdsfds");
	}
	
	public void testGetBirthDate() {
		User Harry = new User("Harry", "1234", null, null, null, null);
		Dog henk = new Dog("karel", "pitbull", Harry,"orange", "F", "Small",
				Calendar.getInstance());
		Calendar cal = new GregorianCalendar(2018,5,4);
		henk.setBirthDate(cal);
		Calendar cal2 = henk.getBirthDate();
		assertEquals(cal, cal2);
	}
	
	

}
