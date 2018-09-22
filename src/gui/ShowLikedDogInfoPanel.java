package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Dog;
import model.DogBook;
import model.ImageFileReactor;

/**
 * @author Team D
 * @description Shows information of a dog you liked and the owner's (contact)
 *              information.
 * @see ShowLikedDogsPanel, which hosts the button that directs here.
 */
public class ShowLikedDogInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DogBook dogbook = MainFrame.getMainFrame().getDogBook();
	private ArrayList<Dog> likeddogs;

	JLabel nameValue = new JLabel("Name");
	JLabel breedValue = new JLabel("Breed");
	JLabel colourValue = new JLabel("Colour");
	JLabel genderValue = new JLabel("Gender");
	JLabel sizeValue = new JLabel("Size");
	JLabel birthValue = new JLabel("Date of birth");
	JLabel likesValue = new JLabel("NoLikes");

	JLabel ownerNameValue = new JLabel("UserName");
	JLabel ownerRealNameValue = new JLabel("Real Name");
	JLabel ownerBioValue = new JLabel("Bio");
	JLabel ownerEmailValue = new JLabel("Email");

	private JLabel lblPicture = new JLabel("Picture goes here");
	ImageFileReactor imgfr = new ImageFileReactor();
	private BufferedImage bufi = imgfr.readImageDisplayer("DogPetite");
	private Dog selectedDog;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

	public ShowLikedDogInfoPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(50, 50, 80, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Breed");
		lblNewLabel_1.setBounds(50, 75, 80, 14);
		add(lblNewLabel_1);

		JLabel DogPicture = new JLabel("Colour");
		DogPicture.setBounds(50, 100, 80, 14);
		add(DogPicture);

		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(50, 125, 80, 14);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Size");
		lblNewLabel_4.setBounds(50, 150, 80, 14);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Date of birth");
		lblNewLabel_5.setBounds(50, 175, 80, 14);
		add(lblNewLabel_5);

		JLabel lblLikes = new JLabel("Belovedness");
		lblLikes.setBounds(50, 200, 80, 14);
		add(lblLikes);

		JLabel lblOwnerContact = new JLabel("Owner's info and Contact information");
		lblOwnerContact.setBounds(50, 232, 300, 14);
		add(lblOwnerContact);

		JLabel lblOwnerName = new JLabel("Username of Owner");
		lblOwnerName.setBounds(50, 262, 150, 14);
		add(lblOwnerName);

		JLabel lblOwnerRealName = new JLabel("Real name of Owner");
		lblOwnerRealName.setBounds(50, 287, 150, 14);
		add(lblOwnerRealName);

		JLabel lblOwnerBio = new JLabel("Owner's User Bio");
		lblOwnerBio.setBounds(50, 312, 150, 14);
		add(lblOwnerBio);

		JLabel lblOwnerEmail = new JLabel("Owner's Email Address");
		lblOwnerEmail.setBounds(50, 337, 150, 14);
		add(lblOwnerEmail);

		nameValue.setBounds(140, 50, 80, 14);
		add(nameValue);

		breedValue.setBounds(140, 75, 80, 14);
		add(breedValue);

		colourValue.setBounds(140, 100, 80, 14);
		add(colourValue);

		genderValue.setBounds(140, 125, 80, 14);
		add(genderValue);

		sizeValue.setBounds(140, 150, 80, 14);
		add(sizeValue);

		birthValue.setBounds(140, 175, 80, 14);
		add(birthValue);

		likesValue.setBounds(140, 200, 80, 14);
		add(likesValue);

		lblPicture.setBackground(new Color(51, 153, 0));
		lblPicture.setBounds(306, 50, 150, 150);
		add(lblPicture);

		ownerNameValue.setBounds(250, 262, 150, 14);
		add(ownerNameValue);
		ownerRealNameValue.setBounds(250, 287, 150, 14);
		add(ownerRealNameValue);
		ownerBioValue.setBounds(250, 312, 150, 14);
		add(ownerBioValue);
		ownerEmailValue.setBounds(250, 337, 150, 14);
		add(ownerEmailValue);

		JButton btnCancel = new JButton("Back to Dogs");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getShowLikedDogsPanel());
			}
		});
		btnCancel.setBounds(50, 370, 145, 34);
		add(btnCancel);
	}

	/**
	 * setTexts set all the text fields with the information about the liked dog
	 */
	public void setTexts() {
		likeddogs = dogbook.getLikedDogs();
		selectedDog = likeddogs.get(
				((ListModelPanel) MainFrame.getMainFrame().getShowLikedDogsPanel()).getListForJ().getSelectedIndex());
		nameValue.setText(selectedDog.getName());
		breedValue.setText(selectedDog.getBreed());
		colourValue.setText(selectedDog.getColour());
		genderValue.setText(selectedDog.getGender());
		sizeValue.setText(selectedDog.getSize());
		birthValue.setText(format1.format((selectedDog.getBirthDate().getTime())));
		likesValue.setText(String.valueOf(selectedDog.getLikes()));
		ownerNameValue.setText(selectedDog.getOwner().getUsername());
		ownerRealNameValue.setText(selectedDog.getOwner().getFirstname() + ' ' + selectedDog.getOwner().getLastname());
		ownerBioValue.setText(selectedDog.getOwner().getUserBio());
		ownerEmailValue.setText(selectedDog.getOwner().getEmail());

		if (selectedDog.getNewDogimage() == null) {
			lblPicture.setIcon(new ImageIcon(bufi));
		} else {
			lblPicture.setIcon(new ImageIcon(selectedDog.getDogimage()));
		}
		
	}
}
