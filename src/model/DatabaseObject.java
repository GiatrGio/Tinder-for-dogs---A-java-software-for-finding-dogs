package model;

public class DatabaseObject {
	/*
	 * Editflags from User and Dog when edited, are given here. This is for communication with the database.
	 * @ author: Team Initial D  
	 * @ version: 1.0
	 * @ methods: databaseObject(), getEditFlag()
	 * 
	 */
	protected boolean editflag;

	public DatabaseObject() {
		super();
	}

	public boolean getEditFlag() {
		return editflag;
	}

}