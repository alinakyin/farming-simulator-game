package main;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;

/**
 * The screen for getting the farmer's name and age.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class StartingScreen {

	private JFrame frame;
	private JTextField farmerNameText;
	private JTextField farmerAgeText;
	
	private FarmManager manager;
	private JLabel gameTitleText;
	
	public StartingScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeStartingScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Farm Simulator");
		frame.setBounds(100, 100, 665, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		farmerNameText = new JTextField();
		farmerNameText.setBounds(274, 143, 167, 28);
		frame.getContentPane().add(farmerNameText);
		farmerNameText.setColumns(10);
		
		farmerAgeText = new JTextField();
		farmerAgeText.setColumns(10);
		farmerAgeText.setBounds(274, 185, 71, 28);
		frame.getContentPane().add(farmerAgeText);
		
		gameTitleText = new JLabel("Farming Simulator");
		gameTitleText.setFont(new Font("Tahoma", Font.BOLD, 20));
		gameTitleText.setBounds(218, 18, 282, 59);
		frame.getContentPane().add(gameTitleText);
		
		JTextArea welcomeTxt = new JTextArea();
		welcomeTxt.setEditable(false);
		welcomeTxt.setLineWrap(true);
		welcomeTxt.setFont(new Font("Tahoma", Font.BOLD, 13));
		welcomeTxt.setText("Hello! To get started on your adventure, first enter your farmer's name and age...\r\n");
		welcomeTxt.setBackground(SystemColor.inactiveCaptionBorder);
		welcomeTxt.setBounds(43, 81, 614, 41);
		frame.getContentPane().add(welcomeTxt);
		
		JLabel farmerNameLabel = new JLabel("Farmer's name:");
		farmerNameLabel.setBounds(167, 144, 90, 27);
		frame.getContentPane().add(farmerNameLabel);
		
		JLabel farmerAgeLabel = new JLabel("Farmer's age:");
		farmerAgeLabel.setBounds(178, 186, 79, 27);
		frame.getContentPane().add(farmerAgeLabel);
		
		JButton continueBtn = new JButton("Continue");
		continueBtn.setBackground(SystemColor.inactiveCaptionBorder);
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isValid = true;
				
				try {
					String farmerName = farmerNameText.getText();
					int length = farmerName.length();
					if (length < 3 || length > 15 || !(farmerName.matches("[a-zA-Z]+"))) {
						isValid = false;
						JOptionPane.showMessageDialog(frame,
							    "Please enter a name between 3 and 15 characters long, alphabet only.",
							    "Invalid input",
							    JOptionPane.WARNING_MESSAGE);
					} else {
						manager.setFarmerName(farmerName);
					}
					
			        int age = Integer.parseInt(farmerAgeText.getText());
			        if (!(age > 5 && age < 110)) {
			        	isValid = false;
						JOptionPane.showMessageDialog(frame,
							    "Please enter an age between 5 and 110!",
							    "Invalid input",
							    JOptionPane.WARNING_MESSAGE);
			        } else {
			        	manager.setFarmerAge(age);
			        }
				}
				
		        catch(NumberFormatException error) {
		        	isValid = false;
		            JOptionPane.showMessageDialog(frame, "Age must be a number!", "Invalid input", JOptionPane.WARNING_MESSAGE);
		        }
				
				if (isValid) {
					finishedWindow();
				}
			}
		});
			       
		continueBtn.setBounds(502, 306, 105, 28);
		frame.getContentPane().add(continueBtn);
		
		JLabel sproutIcon = new JLabel("");
		sproutIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		sproutIcon.setBounds(63, 279, 30, 30);
		frame.getContentPane().add(sproutIcon);
		
		JLabel sproutIcon1 = new JLabel("");
		sproutIcon1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		sproutIcon1.setBounds(33, 249, 30, 30);
		frame.getContentPane().add(sproutIcon1);
		
		JLabel sproutIcon2 = new JLabel("");
		sproutIcon2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		sproutIcon2.setBounds(25, 295, 30, 30);
		frame.getContentPane().add(sproutIcon2);
		
		JLabel appleIcon = new JLabel("");
		appleIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple.png"))
				.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT)));
		appleIcon.setBounds(330, 289, 35, 30);
		frame.getContentPane().add(appleIcon);
		
		JLabel chickenIcon = new JLabel("");
		chickenIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chicken.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		chickenIcon.setBounds(502, 167, 60, 55);
		frame.getContentPane().add(chickenIcon);
		
		JLabel eggIcon = new JLabel("");
		eggIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/egg.png"))
				.getImage().getScaledInstance(50, 31, Image.SCALE_DEFAULT)));
		eggIcon.setBounds(546, 194, 51, 32);
		frame.getContentPane().add(eggIcon);
		
		JLabel pigIcon = new JLabel("");
		pigIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
				.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
		pigIcon.setBounds(386, 245, 80, 75);
		frame.getContentPane().add(pigIcon);

	}
}