package database;

import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import gui.MainFrame;
import model.*;

/**
 * Gets data from database
 * 
 * @author Team D
 * @param getsconnection
 *            from OpenCloseConnection
 */
public final class DataRetrieval {
	static Connection con;
	// singleton pattern
	private static DataRetrieval INSTANCE = null;// new DataRetrieval();

	public static DataRetrieval getInstance() { // this is our name for getInstance
		if (INSTANCE == null) {
			INSTANCE = new DataRetrieval();
		}
		return INSTANCE;
	}

	// filluserbook to get users from database
	public UserBook filluserbook() {
		con = OpenCloseConnection.getInstance().connect(con);
		String SPsql = "EXEC dbo.getUserInfo ?"; // for stored proc taking 2
													// parameters
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<User> databaseusers = new ArrayList<User>();
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, "All");
			rs = ps.executeQuery();
			while (rs.next()) {
				User myUser = new User(rs.getString("UserName"), rs.getString("Password"),
						rs.getString("UserFirstName"), rs.getString("EmailAddress"), rs.getString("UserLastName"),
						rs.getString("UserBio"), rs.getBoolean("IsMod"), rs.getInt("UserId"));
				databaseusers.add(myUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);

		}

		UserBook userbook = new UserBook(databaseusers);
		return userbook;
	}

	// fillDogBook to fill the DogBook
	public DogBook fillDogBook(UserBook userbook) {
		con = OpenCloseConnection.getInstance().connect(con);

		// String SPsql = "select DogName, DogLocation from dbo.Dogs";
		String SPsql = "EXEC dbo.FillDogBook ?";

		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<Dog> databasedogs = new ArrayList<Dog>();
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, "All");
			rs = ps.executeQuery();
			while (rs.next()) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(rs.getDate("Birthdate"));
				// Dog dogey = new Dog(rs.getInt("DogTag"),
				// rs.getString("DogName"), rs.getString("DogBreed"),
				// rs.getString("DogPicture"),
				// userbook.getUserByUsername(rs.getString("UserName"))); //
				// added DogPicture, new column needed in database.
				Dog dogey = new Dog(rs.getInt("DogTag"), rs.getString("DogName"), rs.getString("DogBreed"),
						userbook.getUserByUsername(rs.getString("UserName")), rs.getString("DogColour"),
						rs.getString("DogGender"), rs.getString("DogSize"), calendar, rs.getInt("NoOfLikes"));
				databasedogs.add(dogey);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}
		DogBook dogBook = new DogBook(databasedogs);
		return dogBook;

	}

	// findDogPic to get the DogPicture of a dog
	public BufferedImage findDogPic(Dog dog) {
		con = OpenCloseConnection.getInstance().connect(con);

		// String SPsql = "select DogName, DogLocation from dbo.Dogs";
		String SPsql = "EXEC dbo.FindDogPic ?";
		InputStream in = null;
		ResultSet rs = null;
		BufferedImage bufi = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, dog.getDogTag());
			rs = ps.executeQuery();
			while (rs.next()) {
				try {
					if (rs.getBytes("DogPicture") != null) {
						in = new ByteArrayInputStream(rs.getBytes("DogPicture"));
					}
					BufferedImage dogimage = null;
					try {
						dogimage = ImageIO.read(in);
					} catch (IOException | java.lang.IllegalArgumentException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
					if (dogimage != null) {
						bufi = dogimage;

					}

				} catch (Exception e) {
					// e.printStackTrace();

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

		finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
			return bufi;
		}
	}

	// setIsLiked to look if the current user has liked dogs, which will not be
	// displayed in ShowDogs
	public void setIsLiked(DogBook dogbook, User user) {
		con = OpenCloseConnection.getInstance().connect(con);

		// String SPsql = "select DogName, DogLocation from dbo.Dogs";
		String SPsql = "EXEC dbo.getDogLikes ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				dogbook.getDogbyTag(rs.getInt("LikedDogId")).setIsLiked();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}
	}
}