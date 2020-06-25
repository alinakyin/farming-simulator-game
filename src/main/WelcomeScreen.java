package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextPane;
import java.awt.SystemColor;

/**
 * The screen for telling the player their farm's unique qualities.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class WelcomeScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public WelcomeScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeWelcomeScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Welcome!");
		frame.setBounds(100, 100, 665, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("");
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setText("<html>Welcome, " + manager.getFarmerName() + "! You are now the proud owner of a " + manager.getFarmString() + ".</html>");
		welcomeLabel.setBounds(90, 87, 467, 40);
		frame.getContentPane().add(welcomeLabel);
		
		JButton continueBtn = new JButton("Continue");
		continueBtn.setBackground(SystemColor.inactiveCaptionBorder);
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		continueBtn.setBounds(272, 306, 105, 28);
		frame.getContentPane().add(continueBtn);
		
		JLabel introLabel = new JLabel("Introduction");
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);
		introLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		introLabel.setBounds(231, 30, 201, 34);
		frame.getContentPane().add(introLabel);
		
		JTextPane farmBonusTxtPane = new JTextPane();
		farmBonusTxtPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		farmBonusTxtPane.setText(manager.getFarmer().getFarm().getBonus());
		farmBonusTxtPane.setEditable(false);
		farmBonusTxtPane.setBackground(SystemColor.inactiveCaptionBorder);
		farmBonusTxtPane.setBounds(219, 138, 351, 142);
		frame.getContentPane().add(farmBonusTxtPane);

		JLabel sunIcon = new JLabel("");
		sunIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sun.gif"))
				.getImage().getScaledInstance(100, 85, Image.SCALE_DEFAULT)));
		sunIcon.setBounds(90, 138, 100, 85);
		frame.getContentPane().add(sunIcon);
		
	}
}