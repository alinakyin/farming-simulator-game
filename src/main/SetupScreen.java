package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

/**
 * The screen for getting the game duration, farm type and farm name.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class SetupScreen {

	private JFrame frame;
	private JTextField farmNameText;
	private ArrayList<String> farmTypes;
	private JSlider gameDurationSlider;
	private int duration = 7; 
	private String farmType = "Dairy Farm"; // Defaults
	
	private FarmManager manager;
	
	public SetupScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeSetupScreen(this);
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
		
		farmNameText = new JTextField();
		farmNameText.setColumns(10);
		farmNameText.setBounds(365, 229, 200, 28);
		frame.getContentPane().add(farmNameText);
		
		gameDurationSlider = new JSlider();
		gameDurationSlider.setBackground(SystemColor.inactiveCaptionBorder);
		gameDurationSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {                  
			    duration = gameDurationSlider.getValue();
			}
		});
		gameDurationSlider.setPaintLabels(true);
		gameDurationSlider.setSnapToTicks(true);
		gameDurationSlider.setPaintTicks(true);
		gameDurationSlider.setMajorTickSpacing(1);
		gameDurationSlider.setValue(7);
		gameDurationSlider.setMinimum(5);
		gameDurationSlider.setMaximum(10);
		gameDurationSlider.setBounds(201, 121, 239, 37);
		frame.getContentPane().add(gameDurationSlider);
		
		farmTypes = new ArrayList<String>();
		farmTypes.add("Dairy Farm");
		farmTypes.add("Factory Farm");
		farmTypes.add("Outback Ranch Farm");
		farmTypes.add("Plantation Farm");
		JComboBox<String> farmTypeComboBox = new JComboBox<String>();
		farmTypeComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		farmTypeComboBox.setModel(new DefaultComboBoxModel<String>(farmTypes.toArray(new String[0])));
		farmTypeComboBox.setSelectedItem("Dairy Farm");
		farmTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {    
		    	farmType = (String)farmTypeComboBox.getSelectedItem();
			}
		});
		farmTypeComboBox.setBounds(82, 229, 200, 28);
		frame.getContentPane().add(farmTypeComboBox);

		JButton btnStart = new JButton("Start farming!");
		btnStart.setBackground(SystemColor.inactiveCaptionBorder);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String farmName = farmNameText.getText();
				boolean isValid = true;
				if (farmName.length() == 0) {
					isValid = false;
					JOptionPane.showMessageDialog(frame,
						    "Please give your farm a name!",
						    "Invalid input",
						    JOptionPane.WARNING_MESSAGE);
				} else {
					manager.setFarmName(farmName);
					manager.setFarmString(farmType);
					manager.setGameDuration(duration);
				}
				
				if (isValid) {
					manager.initialiseGame();
					finishedWindow();	
				}
			}
		});
		btnStart.setBounds(262, 306,  129, 28);
		frame.getContentPane().add(btnStart);
				
		JLabel farmTypeLabel = new JLabel("Choose a type of farm...");
		farmTypeLabel.setBounds(83, 201, 180, 28);
		frame.getContentPane().add(farmTypeLabel);
		
		JLabel farmNameLabel = new JLabel("Name your farm!");
		farmNameLabel.setBounds(365, 203, 113, 24);
		frame.getContentPane().add(farmNameLabel);
		
		JLabel gameDurationLabel = new JLabel("Select how many days you want this game to last:");
		gameDurationLabel.setBounds(195, 82, 291, 28);
		frame.getContentPane().add(gameDurationLabel);
		
		JLabel startingLabel = new JLabel("Getting started...");
		startingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		startingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startingLabel.setBounds(169, 21, 300, 37);
		frame.getContentPane().add(startingLabel);
		
		JLabel cloudsIcon = new JLabel("");
		cloudsIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clouds.gif"))
				.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
		cloudsIcon.setBounds(435, 5, 80, 75);
		frame.getContentPane().add(cloudsIcon);

		
	}
}
