package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* @author Team D
* @description Shows the user agreement which may sound a bit creepy but in reality is no more worrying than those of, for example, Facebook and Apple.
* @see CreateUserPanel, which hosts the 'View user agreement' button.
*/
public class UserAgreement extends JDialog {
	private final JPanel contentPanel = new JPanel();

	public UserAgreement() {
		setBounds(100, 100, 488, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JTextArea textArea = new JTextArea("By creating an account for TinderForDogs, you agree \n"
					+ "to grant Us a non transferable option to claim, for \n"
					+ "now and for ever more, your immortal soul. Should We \n"
					+ "wish to exercise this option, you agree to surrender your \n"
					+ "immortal soul, and any claim you may have on it, within \n"
					+ "5 (five) working days of receiving written notification \n"
					+ "from TinderForDogs or one of its duly authorised minions.");
			textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 0;
			contentPanel.add(textArea, gbc_textArea);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				JButton closeButton = new JButton("Close");
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();

					}
				});
				closeButton.setActionCommand("Cancel");
				buttonPane.add(closeButton);
			}
		}
	}

}
