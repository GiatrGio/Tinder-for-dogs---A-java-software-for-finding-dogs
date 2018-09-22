package gui;
/*
 * MH DR SW Add dog panel to get the name and breed from the user. User can add a picture to the dog, 
 * which can be altered by ImageFilter on the go. The picture is displayed in the picture window
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

/**
 * @author Team D
 * @description Shows your personal information and allows you to click buttons
 *              to edit or delete your account.
 * @see LoggedInMainPanel, which has the 'View my Account' button.
 *      EditAccountPanel, which can be accessed by the 'Edit Account' button.
 */
public class AddDogPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ImageFileReactor imgfr = new ImageFileReactor();
	private JTextField dogName;
	private JTextField dogBreed;
	private JTextField dogColour;
	private JTextField dogGender;
	private JTextField dogSize;
	private JDateChooser dateChooser;
	private BufferedImage bufi = imgfr.readImageDisplayer("DogPetite");
	private JLabel lblPicture = new JLabel("Picture goes here");
	private JTextField dogFilePath;
	private BufferedImage image;
	private SoundEffects sound = new SoundEffects();

	public AddDogPanel() {
		setLayout(null);

		JLabel lblPleaseEnterThe = new JLabel("Please enter the information below and click \"Add\"");
		lblPleaseEnterThe.setBounds(60, 53, 292, 14);
		add(lblPleaseEnterThe);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(40, 97, 46, 14);
		add(lblName);

		JLabel lblBreed = new JLabel("Breed");
		lblBreed.setBounds(40, 122, 46, 14);
		add(lblBreed);

		JLabel lblColour = new JLabel("Colour");
		lblColour.setBounds(40, 147, 46, 14);
		add(lblColour);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(40, 172, 46, 14);
		add(lblGender);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(40, 197, 46, 14);
		add(lblSize);

		JLabel lblBirthdate = new JLabel("Date of birth");
		lblBirthdate.setBounds(40, 222, 73, 14);
		add(lblBirthdate);

		dogName = new JTextField();
		dogName.setBounds(116, 94, 86, 20);
		add(dogName);
		dogName.setColumns(10);

		dogBreed = new JTextField();
		dogBreed.setBounds(116, 119, 86, 20);
		add(dogBreed);
		dogBreed.setColumns(10);

		dogColour = new JTextField();
		dogColour.setBounds(116, 144, 86, 20);
		add(dogColour);
		dogColour.setColumns(10);

		dogGender = new JTextField();
		dogGender.setBounds(116, 169, 86, 20);
		add(dogGender);
		dogGender.setColumns(10);

		dogSize = new JTextField();
		dogSize.setBounds(116, 194, 86, 20);
		add(dogSize);
		dogSize.setColumns(10);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(116, 219, 86, 20);
		add(dateChooser);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playWoof();
				tryToAddDog();
				dogName.setText(null);
				dogBreed.setText(null);
				dogColour.setText(null);
				dogGender.setText(null);
				dogSize.setText(null);
			}
		});
		btnAdd.setBounds(116, 274, 89, 23);
		add(btnAdd);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
				dogName.setText(null);
				dogBreed.setText(null);
				dogColour.setText(null);
				dogGender.setText(null);
				dogSize.setText(null);
			}
		});
		btnCancel.setBounds(212, 274, 89, 23);
		add(btnCancel);

		// standard picture in svn pictures folder
		lblPicture.setIcon(new ImageIcon(bufi));
		lblPicture.setBackground(new Color(51, 153, 0));
		lblPicture.setBounds(230, 80, 150, 150);
		add(lblPicture);

		JButton btnNewButton = new JButton("Add picture");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser();
			}
		});
		btnNewButton.setBounds(212, 240, 89, 23);
		add(btnNewButton);

		dogFilePath = new JTextField();
		dogFilePath.setBounds(116, 241, 86, 20);
		add(dogFilePath);
		dogFilePath.setColumns(10);

		JLabel lblPicture_1 = new JLabel("Picture");
		lblPicture_1.setBounds(40, 244, 46, 14);
		add(lblPicture_1);

	}

	/**
	 * fileChooser to get the image you want to add to the dog
	 */
	public void fileChooser() {
		JFileChooser fc = new JFileChooser() {
			private static final long serialVersionUID = 1L;

			protected JDialog createDialog(Component parent) throws HeadlessException {
				// intercept the dialog created by JFileChooser
				JDialog dialog = super.createDialog(getRootPane());
				dialog.setModal(true); // set modality (or setModalityType)
				return dialog;
			}
		};

		// Ensure file chooser can only accept image files (not directories),
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setAcceptAllFileFilterUsed(false);

		// only images can be chosen
		FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files",
				ImageIO.getReaderFileSuffixes());
		fc.setFileFilter(imageFilter);

		int returnValue = fc.showOpenDialog(null);

		// fileChooser option picker
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			// put the file path of the picture in the text field
			dogFilePath.setText(fc.getSelectedFile().getPath());
		}
		// Actually upload the image
		uploadImage();
	}

	/**
	 * uploadImage will start the procedure of uploading the image, as in
	 * UploadImage class
	 */
	public void uploadImage() {
		// gets fileName from dogFilePath
		String fileName = dogFilePath.getText();

		// upload image using UploadImage.saveAsPicture method, uses ImageResizer,
		// ImageFileReactor and ImageFilter
		UploadImage upi = new UploadImage();
		setOri(upi.uploadProcedure(fileName));
	}

	public void setOri(BufferedImage ori) {
		this.image = ori;
		changeImage();
	}

	/*
	 * Changes the Icon image in the AddDogPanel
	 */
	public void changeImage() {
		lblPicture.setIcon(new ImageIcon(image));
		lblPicture.setBackground(new Color(51, 153, 0));
		lblPicture.setBounds(230, 97, 150, 150);
		add(lblPicture);
		revalidate();
		updateUI();
	}

	/**
	 * try to add dog (user given characteristics) t
	 */
	public void tryToAddDog() {
		String name = dogName.getText();
		String breed = dogBreed.getText();
		String colour = dogColour.getText();
		String gender = dogGender.getText();
		String size = dogSize.getText();
		Calendar birthdate = dateChooser.getCalendar();

		Dog dogey = new Dog(name, breed, Session.getInstance().getLoggedinUser(), colour, gender, size, birthdate);
		dogey.setDogimage(image);

		MainFrame.getMainFrame().getDogBook().addDog(dogey);
		((ShowMyDogsPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).updateOwnersDogs();
		((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).getListModel().addElement(name);
		MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
	}
}
