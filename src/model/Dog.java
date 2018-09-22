package model;

import java.util.Calendar;
import database.DataRetrieval;
import java.awt.image.BufferedImage;

/* 
 * Dog holds the major characteristics of a dog, as indicated by the User. Dogs get a dogtag, and can be liked.
 * Dog can retrieve data of a dog using DataRetrieval which is imported here. 
 */
public class Dog extends DatabaseObject {

	private String name;
	private String breed;
	private User owner; // owner means username of the owner
	private String dogpic; // dogpic added
	private BufferedImage dogimage;
	private String colour;
	private String gender;
	private String size;
	private Calendar birthdate;
	private int numberOfLikes;
	private boolean isLikedByCurrentUser;
	private boolean isOwnedByCurrentUser;
	private boolean isNewlyLiked;
	private boolean imageeditflag;
	private Integer dogtag;
	private Integer internalid = -1; // new dogs get a negative dogtag.

	/**
	 * This constructor is used when you create a new dog
	 * 
	 * @param name
	 * @param breed
	 * @param owner
	 * @param colour
	 * @param gender
	 * @param size
	 * @param birthdate
	 */
	// CONSTRUCTOR
	public Dog(String name, String breed, User owner, String colour, String gender, String size, Calendar birthdate) {
		this.name = name;
		this.breed = breed;
		this.owner = owner;
		this.colour = colour;
		this.gender = gender;
		this.size = size;
		this.birthdate = birthdate;
		this.numberOfLikes = 0;
		isLikedByCurrentUser = false;
		editflag = false;
		this.isNewlyLiked = false;
		this.dogtag = internalid;
		internalid--;
	}

	/**
	 * This is when you get the dog from the database and except from all the other
	 * attributes you also want to take the likes ...
	 * 
	 * @param dogtag
	 * @param name
	 * @param breed
	 * @param owner
	 * @param colour
	 * @param gender
	 * @param size
	 * @param birthdate
	 * @param likes
	 */
	// CONSTRUCTOR used when retrieving data from the database
	public Dog(Integer dogtag, String name, String breed, User owner, String colour, String gender, String size,
			Calendar birthdate, int likes) {
		this.dogtag = dogtag;
		this.name = name;
		this.breed = breed;
		this.owner = owner;
		this.colour = colour;
		this.gender = gender;
		this.size = size;
		this.birthdate = birthdate;
		this.numberOfLikes = likes;
		editflag = false;
		this.isNewlyLiked = false;
		isLikedByCurrentUser = false;
	}

	// ALL THE GET METHODS
	public Integer getDogTag() {
		return dogtag;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public User getOwner() {
		return owner;
	}

	public String getColour() {
		return colour;
	}

	public String getGender() {
		return gender;
	}

	public String getSize() {
		return size;
	}

	public boolean getIsLiked() { // is it liked by the current user
		return isLikedByCurrentUser;
	}

	public boolean getIsOwner() {
		return isOwnedByCurrentUser;
	}

	public int getLikes() {
		return numberOfLikes;
	}

	public Calendar getBirthDate() {
		if (birthdate == null) {
			return Calendar.getInstance();
		} else {
			return birthdate;
		}
	}

	public int getAge() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(Calendar.YEAR) - this.getBirthDate().get(Calendar.YEAR));
	}

	public String getDogPic() {
		return dogpic;
	}

	// ALL THE SET METHODS

	public void setName(String name) {
		this.name = name;
		setEditFlag();
	}

	public void setBreed(String breed) {
		this.breed = breed;
		setEditFlag();
	}

	public void setOwner(User ownername) {
		this.owner = ownername;
		setEditFlag();
	}

	public void setColour(String colour) {
		this.colour = colour;
		setEditFlag();
	}

	public void setGender(String gender) {
		this.gender = gender;
		setEditFlag();
	}

	public void setSize(String size) {
		this.size = size;
		setEditFlag();
	}

	public void setIsLiked() {
		isLikedByCurrentUser = true;
	}

	public void setIsLikedandIncrement() {
		if (!isLikedByCurrentUser) {
			isLikedByCurrentUser = true;
			setNewlyLiked();
			numberOfLikes++;
		}
	}

	public void setBirthDate(Calendar birthdate) {
		this.birthdate = birthdate;
		setEditFlag();
	}

	public void setDogPic(String dogpic) { // dogpic added
		this.dogpic = dogpic;
		setEditFlag();
	}

	public void setEditFlag() {
		// Set edit flag to true if the dog already existed within the DB.
		editflag = (this.dogtag > 0);
	}

	public BufferedImage getDogimage() {
		return dogimage;
	}

	public void setDogimage(BufferedImage dogimage) {
		this.dogimage = dogimage;
		setEditFlag();
		setImageEditflag();

	}

	public Object getNewDogimage() {
		if (this.dogimage == null) {
			this.dogimage = DataRetrieval.getInstance().findDogPic(this);
		}
		return dogimage;
	}

	public boolean getImageEditflag() {
		return imageeditflag;
	}

	public void setImageEditflag() {
		this.imageeditflag = (this.dogtag > 0);
	}

	public boolean getIsNewlyLiked() {
		return isNewlyLiked;
	}

	public void setNewlyLiked() {
		isNewlyLiked = true;
	}

}