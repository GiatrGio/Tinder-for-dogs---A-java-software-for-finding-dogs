package gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import model.*;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * @author Team D
 * @description Also called 'dogsniffing', this is the actual tinder
 * functionality that allows you to scroll through and like dogs
 * that you don't own and haven't liked yet.
 * @see LoggedInMainPanel, which hosts the dogsniffing button
 */
public class ShowDogsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnLike;
	private JButton btnNext;
	private JButton btnBackToMain;
	private JLabel dogName;
	private JLabel dogBreed;
	private JLabel dogOwnerName;
	private JLabel dogColour;
	private JLabel dogGender;
	private JLabel dogSize;
	private JLabel dogAge;
	private JLabel likes;
	private JLabel lblPicture = new JLabel();
	private DogBook currentdogs = MainFrame.getMainFrame().getDogBook();
	private ArrayList<Dog> allotherdogs;
	private int pointer = 0;
	private JLabel lblNewLabel_2;
	private ImageFileReactor imgfr = new ImageFileReactor();
	private BufferedImage defaultDog = imgfr.readImageDisplayer("DogPetite");
	private SoundEffects sound = new SoundEffects();

	public ShowDogsPanel() {
		setPreferredSize(new Dimension(500, 500));
		setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 100, 46, 14);
		add(lblName);

		JLabel lblBreed = new JLabel("Breed");
		lblBreed.setBounds(29, 130, 46, 14);
		add(lblBreed);

		JLabel lblOwner = new JLabel("Owner");
		lblOwner.setBounds(29, 160, 46, 14);
		add(lblOwner);

		JLabel lblColour = new JLabel("Colour");
		lblColour.setBounds(29, 190, 56, 16);
		add(lblColour);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(29, 220, 56, 16);
		add(lblGender);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(29, 250, 56, 16);
		add(lblSize);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(29, 280, 56, 16);
		add(lblAge);

		JLabel lblLikes = new JLabel("Likes:"); // number of likes
		lblLikes.setBounds(52, 324, 61, 16);
		add(lblLikes);

		// if no picture assigned: standard picture in the svn folder
		lblPicture.setBackground(new Color(51, 153, 0));
		lblPicture.setBounds(230, 97, 150, 150);
		add(lblPicture);

		JLabel lblClickNextOr = new JLabel("Click next or previous to swipe dogs");
		lblClickNextOr.setBounds(29, 41, 258, 14);
		add(lblClickNextOr);

		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextDog();
			}
		});

		btnNext.setBounds(331, 410, 150, 23);
		add(btnNext);

		btnBackToMain = new JButton("Main screen");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
				sound.playBark();
			}
		});
		btnBackToMain.setBounds(19, 410, 150, 23);
		add(btnBackToMain);

		btnLike = new JButton("Like!");
		btnLike.setMnemonic(KeyEvent.VK_L);
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.playWoof();
				likeDog();
			}
		});
		btnLike.setBounds(359, 375, 89, 23);
		add(btnLike);

		lblNewLabel_2 = new JLabel("Dog Picture");
		BufferedImage bufimg = imgfr.readImageDisplayer("likedog");

		if (bufimg != null) {
			lblNewLabel_2.setIcon(new ImageIcon(bufimg));
			lblNewLabel_2.setBackground(new Color(51, 153, 0));
			lblNewLabel_2.setBounds(368, 340, 50, 50);
			add(lblNewLabel_2);
		}
	}

	public void setFields() {
		dogName = new JLabel();
		dogName.setBounds(87, 100, 258, 14);
		add(dogName);

		dogBreed = new JLabel();
		dogBreed.setBounds(87, 130, 258, 14);
		add(dogBreed);

		dogOwnerName = new JLabel();
		dogOwnerName.setBounds(87, 160, 258, 14);
		add(dogOwnerName);

		dogColour = new JLabel();
		dogColour.setBounds(87, 190, 258, 14);
		add(dogColour);

		dogGender = new JLabel();
		dogGender.setBounds(87, 220, 258, 14);
		add(dogGender);

		dogSize = new JLabel();
		dogSize.setBounds(87, 250, 258, 14);
		add(dogSize);

		dogAge = new JLabel();
		dogAge.setBounds(87, 280, 258, 14);
		add(dogAge);

		likes = new JLabel();
		likes.setBounds(95, 324, 61, 16);
		add(likes);

		if (allotherdogs.get(pointer).getDogimage() == null) {
			lblPicture.setIcon(new ImageIcon(defaultDog));
		} else {
			lblPicture.setIcon(new ImageIcon(allotherdogs.get(pointer).getDogimage()));
		}

	}

	/**
	 * dogShower shows the dog if it is not yet liked by the user.
	 */
	public void dogShower() {
		if (pointer == allotherdogs.size())
			pointer = 0;
		isLikedOrNot();
		dogName.setText(allotherdogs.get(pointer).getName());
		dogBreed.setText(allotherdogs.get(pointer).getBreed());
		dogOwnerName.setText(allotherdogs.get(pointer).getOwner().getUsername());
		likes.setText(String.valueOf(allotherdogs.get(pointer).getLikes()));
		dogColour.setText(allotherdogs.get(pointer).getColour());
		dogGender.setText(allotherdogs.get(pointer).getGender());
		dogSize.setText(allotherdogs.get(pointer).getSize());
		dogAge.setText(String.valueOf(allotherdogs.get(pointer).getAge()));

		if (!allotherdogs.get(pointer).getIsLiked()) {

			if (allotherdogs.get(pointer).getNewDogimage() == null) {
				lblPicture.setIcon(new ImageIcon(defaultDog));
			} else {
				lblPicture.setIcon(new ImageIcon(allotherdogs.get(pointer).getDogimage()));
			}

		}

	}

	public void makeDogList() {
		allotherdogs = currentdogs.getDogsExceptOfUser(Session.getInstance().getLoggedinUser());
	}

	/**
	 * isLikedOrNot sees if a dog is liked by the user already -> then next dog,
	 * does not show dogs liked by the user
	 */
	public void isLikedOrNot() {
		if (allotherdogs.get(pointer).getIsLiked()) {

			if (allotherdogs.size() == currentdogs.getNoOfLikedDogs()) {
				DialogMessage meh = new DialogMessage("AllDogsAreLiked");
				MainFrame.getMainFrame().setPanel(MainFrame.getMainFrame().getLoggedInMainPanel());
				meh.setModal(true);
				meh.setVisible(true);

			} else {
				nextDog();
			}
		}
	}

	// go to the next dog
	public void nextDog() {
		if (pointer != currentdogs.getDogs().size() - 1) {
			++pointer;
			dogShower();
		}
	}

	// likeDog to like dogs and
	public void likeDog() {
		allotherdogs.get(pointer).setIsLikedandIncrement();
		nextDog();
	}
}
