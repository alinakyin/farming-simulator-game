package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

/**
 * The screen for the county store.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class CountyStoreScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public CountyStoreScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow(boolean openMainScreen) {
		manager.closeCountyStoreScreen(this, openMainScreen);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("County Store");
		frame.setBounds(100, 100, 665, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel countyStoreLabel = new JLabel("Welcome to the county store!");
		countyStoreLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		countyStoreLabel.setBounds(220, 0, 266, 42);
		frame.getContentPane().add(countyStoreLabel);
		
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
		
		JLabel farmNameLabel = new JLabel("");
		farmNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmNameLabel.setText(manager.getFarmName());
		farmNameLabel.setBounds(496, 36, 141, 21);
		frame.getContentPane().add(farmNameLabel);
		
		JButton inventoryBtn = new JButton("View my inventory");
		inventoryBtn.setBackground(SystemColor.inactiveCaptionBorder);
		inventoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchInventoryScreen();
			}
		});
		inventoryBtn.setBounds(243, 108, 157, 33);
		frame.getContentPane().add(inventoryBtn);
		
		JButton cropsBtn = new JButton("Crops");
		cropsBtn.setBackground(SystemColor.inactiveCaptionBorder);
		cropsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(false);
				manager.launchCropsScreen();
			}
		});
		cropsBtn.setBounds(73, 260, 127, 33);
		frame.getContentPane().add(cropsBtn);
		
		JLabel shopLabel = new JLabel("Or shop...");
		shopLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		shopLabel.setBounds(290, 155, 76, 21);
		frame.getContentPane().add(shopLabel);
		
		JButton animalsBtn = new JButton("Animals");
		animalsBtn.setBackground(SystemColor.inactiveCaptionBorder);
		animalsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(false);
				manager.launchAnimalsScreen();
			}
		});
		animalsBtn.setBounds(259, 260, 127, 33);
		frame.getContentPane().add(animalsBtn);
		
		JButton itemsBtn = new JButton("Items");
		itemsBtn.setBackground(SystemColor.inactiveCaptionBorder);
		itemsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(false);
				manager.launchItemsScreen();
			}
		});
		itemsBtn.setBounds(442, 260, 127, 33);
		frame.getContentPane().add(itemsBtn);
		
		JButton leaveBtn = new JButton("Leave");
		leaveBtn.setBackground(SystemColor.inactiveCaptionBorder);
		leaveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(true);
			}
		});
		leaveBtn.setBounds(534, 331, 105, 28);
		frame.getContentPane().add(leaveBtn);
		
		JLabel storeIcon = new JLabel("");
		storeIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))
				.getImage().getScaledInstance(60, 45, Image.SCALE_DEFAULT)));
		storeIcon.setBounds(294, 43, 60, 45);
		frame.getContentPane().add(storeIcon);
		
		JLabel carrotIcon = new JLabel("");
		carrotIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrot.png"))
				.getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT)));
		carrotIcon.setBounds(106, 178, 100, 100);
		frame.getContentPane().add(carrotIcon);
		
		JLabel pigIcon = new JLabel("");
		pigIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
				.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
		pigIcon.setBounds(283, 200, 80, 75);
		frame.getContentPane().add(pigIcon);
		
		JLabel appleIcon = new JLabel("");
		appleIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple.png"))
				.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT)));
		appleIcon.setBounds(486, 215, 35, 30);
		frame.getContentPane().add(appleIcon);
	}

}
