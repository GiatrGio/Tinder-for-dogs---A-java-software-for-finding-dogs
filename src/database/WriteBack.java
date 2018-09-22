package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javax.imageio.ImageIO;
import model.*;

/**
 * Adds writeback functionality to the db.
 * 
 * @author Team D
 * @see LogOut, which uses the methods defined here.
 */
public final class WriteBack {
	static Connection con;
	// Singleton pattern needed for WriteBack class
	private static WriteBack INSTANCE = null;// new DataRetrieval();

	// this is our getInstance method (singleton)
	public static WriteBack getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new WriteBack();
		}
		return INSTANCE;
	}

	// addDog writes the dog owner, name, breed, colour, gender, size and
	// dateofbirth to the database, without a picture
	public void addDog(Dog doggie) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC dbo.addDog ?,?,?,?,?,?,?";
		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, doggie.getOwner().getUsername());
			ps.setString(2, doggie.getName());
			ps.setString(3, doggie.getBreed());
			ps.setString(4, doggie.getColour());
			ps.setString(5, doggie.getGender());
			ps.setString(6, doggie.getSize());
			ps.setDate(7, new Date(doggie.getBirthDate().getTimeInMillis()));
			rs = ps.executeQuery();
			while (rs.next()) {
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// The result did not work
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

	// to Add a dog with a picture. addDogWpic writes the dog owner, name, breed,
	// colour, gender, size and dateofbirth to the database, with a picture
	public void addDogWPic(Dog doggie) {
		con = OpenCloseConnection.getInstance().connect(con);
		String SPsql = "EXEC dbo.addDogWPic ?,?,?,?,?,?,?,?";
		ResultSet rs = null;

		PreparedStatement ps = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(doggie.getDogimage(), "jpg", baos);
		} catch (IOException e1) {
			e1.printStackTrace();
			// failure of adding dog picture
		}
		InputStream is = new ByteArrayInputStream(baos.toByteArray());

		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, doggie.getOwner().getUsername());
			ps.setString(2, doggie.getName());
			ps.setString(3, doggie.getBreed());
			ps.setBinaryStream(4, is);
			ps.setString(5, doggie.getColour());
			ps.setString(6, doggie.getGender());
			ps.setString(7, doggie.getSize());
			ps.setDate(8, (Date) new Date(doggie.getBirthDate().getTimeInMillis()));
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {

			e.printStackTrace();
			// result = "Did not work";
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

	// This is used to edit a dog. It will return the characteristics of the dog
	public void editDogWPic(Dog doggie) {
		con = OpenCloseConnection.getInstance().connect(con);
		String SPsql = "EXEC dbo.EditDogWPic ?, ?, ?, ?, ?, ?, ?, ?";
		ResultSet rs = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(doggie.getDogimage(), "jpg", baos);
		} catch (IOException e1) {
			e1.printStackTrace();

		}
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, doggie.getDogTag());
			ps.setString(2, doggie.getName());
			ps.setString(3, doggie.getBreed());
			ps.setString(4, doggie.getColour());
			ps.setString(5, doggie.getGender());

			ps.setString(6, doggie.getSize());
			ps.setDate(7, new Date(doggie.getBirthDate().getTimeInMillis()));
			ps.setBinaryStream(8, is);
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// the result did not work

		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}
		// create method that edits the dog in the DB
	}

	// edit Dog to return the edited characteristics of the dog to the database
	public void editDog(Dog doggie) {
		con = OpenCloseConnection.getInstance().connect(con);
		String SPsql = "EXEC dbo.EditDog ?, ?, ?, ?, ?, ?, ?";
		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, doggie.getDogTag());
			ps.setString(2, doggie.getName());
			ps.setString(3, doggie.getBreed());
			ps.setString(4, doggie.getColour());
			ps.setString(5, doggie.getGender());
			ps.setString(6, doggie.getSize());
			ps.setDate(7, new Date(doggie.getBirthDate().getTimeInMillis()));
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Edit did not work

		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}
	}

	// method that deletes dog in database
	public void deleteDog(Dog doggie) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC dbo.DeleteDog ?";
		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, doggie.getDogTag());
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// did not work
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

	public void addUser(User user) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC dbo.addUser ?,?,?,?,?,?,?"; // for stored proc taking 7 params

		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getUserBio());
			ps.setString(5, user.getPassword());
			ps.setBoolean(6, user.getIsModerator());
			ps.setString(7, user.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// did not work

		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}
	}

	/**
	 * Created method that deletes user in database
	 * 
	 * @param user
	 */
	public void deleteUser(User user) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC dbo.DeleteUserById ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			// did not work
		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

	/**
	 * editUser to edit the user in the database
	 * 
	 * @param user
	 *            which is being edited
	 */
	public void editUser(User user) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC [dbo].[EditUser] ?,?,?,?,?,?,?,?"; // for stored proc taking 8 params

		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getLastname());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getUserBio());
			ps.setString(6, user.getPassword());
			ps.setBoolean(7, user.getIsModerator());
			ps.setString(8, user.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

	/**
	 * write back the new dog likes to the database
	 * 
	 * @param user,
	 *            which user
	 * @param dog,
	 *            which dog
	 */
	public void commitDogLikes(User user, Dog dog) {
		con = OpenCloseConnection.getInstance().connect(con);

		String SPsql = "EXEC [dbo].[AddDogLikes] ?,?"; // for stored proc taking 8 params
		ResultSet rs = null;

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SPsql);
			ps.setQueryTimeout(100);
			ps.setString(1, user.getUsername());
			ps.setInt(2, dog.getDogTag());
			rs = ps.executeQuery();
			while (rs.next()) {
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			OpenCloseConnection.getInstance().close(rs, ps, con);
		}

	}

}
