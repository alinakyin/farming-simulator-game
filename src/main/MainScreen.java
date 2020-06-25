package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * The main screen of the game, where all available actions can be seen.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class MainScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public MainScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeMainScreen(this);
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
		
		JLabel moneyIcon = new JLabel("");
		moneyIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.gif"))
				.getImage().getScaledInstance(25, 22, Image.SCALE_DEFAULT)));
		moneyIcon.setBounds(540, 11, 23, 21);
		frame.getContentPane().add(moneyIcon);
		
		JTextPane moneyTextPane = new JTextPane();
		moneyTextPane.setEditable(false);
		moneyTextPane.setBounds(566, 11, 73, 20);
		frame.getContentPane().add(moneyTextPane);
		moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney())); 

		JButton viewStoreBtn = new JButton("Visit county store");
		viewStoreBtn.setBackground(SystemColor.inactiveCaptionBorder);
		viewStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				manager.launchCountyStoreScreen();
			}
		});
		viewStoreBtn.setBounds(232, 99, 179, 30);
		frame.getContentPane().add(viewStoreBtn);	
		
		JButton farmStatusBtn = new JButton("View farm status");
		farmStatusBtn.setBackground(SystemColor.inactiveCaptionBorder);
		farmStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Crops",
	                    "Animals"};
				int choice = JOptionPane.showOptionDialog(frame,
				    "Do you want to see the status of your crops or animals?",
				    "View farm status",
				    JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    options,
				    options[0]);
				if (choice == 0) {
					manager.launchCropStatusScreen();
				} else if (choice == 1) {
					manager.launchAnimalStatusScreen();
				}
			}
		});
		farmStatusBtn.setBounds(232, 185, 179, 30);
		frame.getContentPane().add(farmStatusBtn);
		
		JLabel currentDayLabel = new JLabel("");
		currentDayLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		currentDayLabel.setBounds(301, 11, 60, 21);
		frame.getContentPane().add(currentDayLabel);
		currentDayLabel.setText("Day " + GameEnvironment.getCurrentDay()); 
		
		JButton newDayBtn = new JButton("Move to the next day!");
		newDayBtn.setBackground(SystemColor.inactiveCaptionBorder);
		newDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int endDay = JOptionPane.showConfirmDialog(frame, "Are you sure you want to progress to the next day?", "Move to the next day",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
				if (endDay == JOptionPane.YES_OPTION) {
					String message = GameEnvironment.moveDayForward(manager.getFarmer());
					if (message == "Finished") {
						moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
						String closingMessage = GameEnvironment.finishGame(manager.getFarmer());
						JOptionPane.showMessageDialog(frame, closingMessage, "End of game",
							    JOptionPane.PLAIN_MESSAGE);
						finishedWindow();
					} else {
						currentDayLabel.setText("Day " + GameEnvironment.getCurrentDay());
						moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
						JOptionPane.showMessageDialog(frame, message, "Success!",
							    JOptionPane.PLAIN_MESSAGE);
						
						String randomEvent = GameEnvironment.generateRandomEvent(manager.getFarmer());
						if (randomEvent != null) {
							char c = randomEvent.charAt(0);
							if (c == 'Y') {
								moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
								ImageIcon icon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/crown.png"))
										.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
								JOptionPane.showMessageDialog(frame, randomEvent, "REMIX",
									    JOptionPane.PLAIN_MESSAGE, icon);
							} else if (c == 'U') {
								ImageIcon icon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/brokenheart.png"))
										.getImage().getScaledInstance(50, 45, Image.SCALE_DEFAULT));
								JOptionPane.showMessageDialog(frame, randomEvent, "REMIX",
									    JOptionPane.PLAIN_MESSAGE, icon);
							} else if (c == 'A') {
								ImageIcon icon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fire.png"))
										.getImage().getScaledInstance(55, 45, Image.SCALE_DEFAULT));
								JOptionPane.showMessageDialog(frame, randomEvent, "REMIX",
									    JOptionPane.PLAIN_MESSAGE, icon);
							}
						}
					}
				}
			}
		});
		newDayBtn.setBounds(232, 282, 179, 30);
		frame.getContentPane().add(newDayBtn);
		
		JButton tendCropsBtn = new JButton("Tend to crops");
		tendCropsBtn.setBackground(SystemColor.inactiveCaptionBorder);
		tendCropsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Crop> crops = manager.getFarmer().getFarm().getCrops();
				if (crops.size() == 0) {
					JOptionPane.showMessageDialog(frame, "You don't have any crops to tend to!", "Visit the store!",
						    JOptionPane.PLAIN_MESSAGE);
				} else {
					if (manager.getFarmer().getActionCount() < 2) {
						finishedWindow();
						manager.launchTendCropsScreen();
					} else {
						String message = GameEnvironment.noActionsLeft(manager.getFarmer());
						JOptionPane.showMessageDialog(frame, message, "Not allowed",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		tendCropsBtn.setBounds(471, 142, 141, 30);
		frame.getContentPane().add(tendCropsBtn);
		
		JButton tendLandBtn = new JButton("Tend to the land");
		tendLandBtn.setBackground(SystemColor.inactiveCaptionBorder);
		tendLandBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getFarmer().getActionCount() < 2) {
					int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to tend to your land?", "Tend to the land",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
					if (choice == JOptionPane.YES_OPTION) {
						ArrayList<Animal> animals = manager.getFarmer().getFarm().getAnimals();
						String message = GameEnvironment.tendToLand(manager.getFarmer(), animals);
						JOptionPane.showMessageDialog(frame, message, "Success!",
							    JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					String message = GameEnvironment.noActionsLeft(manager.getFarmer());
					JOptionPane.showMessageDialog(frame, message, "Not allowed",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		tendLandBtn.setBounds(471, 224, 141, 30);
		frame.getContentPane().add(tendLandBtn);
		
		JButton feedAnimalBtn = new JButton("Feed animals");
		feedAnimalBtn.setBackground(SystemColor.inactiveCaptionBorder);
		feedAnimalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Animal> animals = manager.getFarmer().getFarm().getAnimals();
				if (animals.size() == 0) {
					JOptionPane.showMessageDialog(frame, "You don't have any animals to feed!", "Visit the store!",
						    JOptionPane.PLAIN_MESSAGE);
				} else {
					if (manager.getFarmer().getActionCount() < 2) {
						finishedWindow();
						manager.launchFeedAnimalsScreen();
					} else {
						String message = GameEnvironment.noActionsLeft(manager.getFarmer());
						JOptionPane.showMessageDialog(frame, message, "Not allowed",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		feedAnimalBtn.setBounds(36, 224, 141, 30);
		frame.getContentPane().add(feedAnimalBtn);
		
		JButton playAnimalBtn = new JButton("Play with animals");
		playAnimalBtn.setBackground(SystemColor.inactiveCaptionBorder);
		playAnimalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Animal> animals = manager.getFarmer().getFarm().getAnimals();
				if (animals.size() == 0) {
					JOptionPane.showMessageDialog(frame, "You don't have any animals to play with!", "Visit the store!",
						    JOptionPane.PLAIN_MESSAGE);
				} else {
					if (manager.getFarmer().getActionCount() < 2) {
						finishedWindow();
						manager.launchPlayAnimalsScreen();
					} else {
						String message = GameEnvironment.noActionsLeft(manager.getFarmer());
						JOptionPane.showMessageDialog(frame, message, "Not allowed",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		playAnimalBtn.setBounds(36, 185, 141, 30);
		frame.getContentPane().add(playAnimalBtn);
		
		JButton harvestCropBtn = new JButton("Harvest crops");
		harvestCropBtn.setBackground(SystemColor.inactiveCaptionBorder);
		harvestCropBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Crop> ready = manager.getFarmer().getFarm().findReadyToHarvest();
				if (ready.size() == 0) {
					JOptionPane.showMessageDialog(frame, "You don't have any crops ready to harvest!", "Try a crop item!",
						    JOptionPane.PLAIN_MESSAGE);
				} else {
					if (manager.getFarmer().getActionCount() < 2) {
						finishedWindow();
						manager.launchHarvestCropsScreen();
					} else {
						String message = GameEnvironment.noActionsLeft(manager.getFarmer());
						JOptionPane.showMessageDialog(frame, message, "Not allowed",
							    JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		harvestCropBtn.setBounds(471, 183, 141, 30);
		frame.getContentPane().add(harvestCropBtn);
		
		JLabel farmNameLabel = new JLabel("");
		farmNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmNameLabel.setText(manager.getFarmName());
		farmNameLabel.setBounds(496, 36, 141, 21);
		frame.getContentPane().add(farmNameLabel);
		
		JLabel logIcon = new JLabel("");
		logIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/log.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		logIcon.setBounds(503, 265, 60, 55);
		frame.getContentPane().add(logIcon);
		
		JLabel appleIcon = new JLabel("");
		appleIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple.png"))
				.getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT)));
		appleIcon.setBounds(79, 280, 40, 35);
		frame.getContentPane().add(appleIcon);
		
		JLabel wheatIcon = new JLabel("");
		wheatIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wheat.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		wheatIcon.setBounds(512, 78, 60, 55);
		frame.getContentPane().add(wheatIcon);
		
		JLabel carrotIcon = new JLabel("");
		carrotIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrot.png"))
				.getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT)));
		carrotIcon.setBounds(288, 133, 55, 50);
		frame.getContentPane().add(carrotIcon);
		
		JLabel chickenIcon = new JLabel("");
		chickenIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chicken.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		chickenIcon.setBounds(69, 121, 60, 55);
		frame.getContentPane().add(chickenIcon);
		
		JLabel cowIcon = new JLabel("");
		cowIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cow.apng.png"))
				.getImage().getScaledInstance(70, 75, Image.SCALE_DEFAULT)));
		cowIcon.setBounds(285, 210, 70, 75);
		frame.getContentPane().add(cowIcon);
		
		JLabel storeIcon = new JLabel("");
		storeIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))
				.getImage().getScaledInstance(60, 45, Image.SCALE_DEFAULT)));
		storeIcon.setBounds(294, 43, 60, 45);
		frame.getContentPane().add(storeIcon);
		
	}
}
