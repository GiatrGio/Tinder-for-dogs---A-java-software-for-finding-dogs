package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import model.ImageFileReactor;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.UploadImage;

import model.*;

/**
 * @author Team D
 * @description Allows user to edit their dogs information
 * @see ShowMyAccountPanel, which hosts the edit dog button that directs to
 *      here.
 */
public class EditDogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DogBook dogbook = MainFrame.getMainFrame().getDogBook();
	private ArrayList<Dog> ownersdogs;
	private JTextField actualName;
	private JTextField actualBreed;
	private JTextField actualColour;
	private JTextField actualGender;
	private JTextField dogFilePath;
	private JTextField actualSize;

	private JLabel lblPicture = new JLabel("Picture goes here");
	private BufferedImage image;
	private ImageFileReactor imgfr = new ImageFileReactor();
	private BufferedImage bufi = imgfr.readImageDisplayer("DogPetite");
	private JDateChooser dateChooser_1;
	private Dog selectedDog;

	/**
	 * Create the panel.
	 */
	public EditDogPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(50, 108, 80, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Breed");
		lblNewLabel_1.setBounds(50, 133, 80, 14);
		add(lblNewLabel_1);

		JLabel DogPicture = new JLabel("Colour");
		DogPicture.setBounds(50, 158, 80, 14);
		add(DogPicture);

		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(50, 183, 80, 14);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Size");
		lblNewLabel_4.setBounds(50, 208, 80, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Date of birth");
		lblNewLabel_5.setBounds(50, 233, 80, 14);
		add(lblNewLabel_5);

		JLabel lblPictureText = new JLabel("Image");
		lblPictureText.setBounds(50, 258, 80, 14);
		add(lblPictureText);

		lblPicture.setBackground(new Color(51, 153, 0));
		lblPicture.setBounds(306, 108, 150, 150);
		add(lblPicture);

		JLabel lblPleaseEditThe = new JLabel("Edit your dog:");
		lblPleaseEditThe.setBounds(50, 68, 122, 14);
		add(lblPleaseEditThe);

		JButton btnOk = new JButton("Save changes");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editDogAttempt();
			}
		});
		btnOk.setBounds(140, 341, 145, 34);
		add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
			}
		});
		btnCancel.setBounds(140, 377, 145, 34);
		add(btnCancel);

		JButton btnChangeImage = new JButton("Upload new picture");
		btnChangeImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser();
			}
		});
		btnChangeImage.setBounds(140, 278, 145, 23);
		add(btnChangeImage);

		actualName = new JTextField();
		actualName.setBounds(140, 105, 145, 20);
		add(actualName);
		actualName.setColumns(10);

		actualBreed = new JTextField();
		actualBreed.setBounds(140, 130, 145, 20);
		add(actualBreed);
		actualBreed.setColumns(10);

		actualColour = new JTextField();
		actualColour.setBounds(140, 155, 145, 20);
		add(actualColour);
		actualColour.setColumns(10);

		actualGender = new JTextField();
		actualGender.setBounds(140, 180, 145, 20);
		add(actualGender);
		actualGender.setColumns(10);

		actualSize = new JTextField();
		actualSize.setBounds(140, 205, 145, 20);
		add(actualSize);
		actualSize.setColumns(10);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(140, 230, 145, 20);
		add(dateChooser_1);

		dogFilePath = new JTextField();
		dogFilePath.setBounds(142, 254, 145, 22);
		add(dogFilePath);
		dogFilePath.setColumns(10);
	}

	/**
	 * get the Dog attributes from the text fields, try to set the attributes to the
	 * selectedDog.
	 */
	public void editDogAttempt() {
		selectedDog = ownersdogs
				.get(((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).getListForJ().getSelectedIndex());

		selectedDog.setName(actualName.getText());
		selectedDog.setBreed(actualBreed.getText());
		selectedDog.setColour(actualColour.getText());
		selectedDog.setGender(actualGender.getText());
		selectedDog.setSize(actualSize.getText());
		selectedDog.setBirthDate(dateChooser_1.getCalendar());
		selectedDog.setDogimage(image);

		if (Session.getInstance().getLoggedinUser().getIsModerator()) {
			((ShowMyDogsPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).fillJList();
			MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
		} else {
			((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).fillJList();
			MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowMyDogsPanel());
		}
	}

	/**
	 * Set the actual dog attributes in the textfields, to be able to see those
	 * before editing.
	 */
	public void setTexts() {
		if (Session.getInstance().getLoggedinUser().getIsModerator()) {
			ownersdogs = dogbook.getDogs();
		} else {
			ownersdogs = dogbook.getDogsOfUser(Session.getInstance().getLoggedinUser());
		}
		selectedDog = ownersdogs
				.get(((ListModelPanel) MainFrame.getMainFrame().getShowMyDogsPanel()).getListForJ().getSelectedIndex());
		actualName.setText(selectedDog.getName());
		actualBreed.setText(selectedDog.getBreed());
		actualColour.setText(selectedDog.getColour());
		actualGender.setText(selectedDog.getGender());
		actualSize.setText(selectedDog.getSize());
		dateChooser_1.setCalendar(selectedDog.getBirthDate());
		if (selectedDog.getNewDogimage() == null) {
			lblPicture.setIcon(new ImageIcon(bufi));
		} else {
			lblPicture.setIcon(new ImageIcon(selectedDog.getDogimage()));
		}
	}
	
	/*
	 * filechooser which opens to edit the dog picture
	 */
	protected void fileChooser() {
		// Create file chooser object
		JFileChooser fc = new JFileChooser() {
			private static final long serialVersionUID = 1L;

			@Override
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
		
		// only images.
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

	public void uploadImage() {
		// gets fileName from dogFilePath
		String fileName = dogFilePath.getText();
		// upload image using UploadImage.saveAsPicture method, uses
		// ImageResizer, ImageFileReactor and ImageFilter
		UploadImage upi = new UploadImage();
		setOri(upi.uploadProcedure(fileName));
	}

	public void setOri(BufferedImage ori) {
		this.image = ori;
		changeImage();
	}
	
	// changes the image in the EditDogPanel
	public void changeImage() {
		lblPicture.setIcon(new ImageIcon(image));
		add(lblPicture);
		revalidate();
		updateUI();
	}
}
