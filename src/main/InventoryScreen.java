package main;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/**
 * The screen for viewing the player's inventory.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class InventoryScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public InventoryScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeInventoryScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Inventory");
		frame.setBounds(100, 100, 665, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel moneyIcon = new JLabel("");
		moneyIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.gif"))
				.getImage().getScaledInstance(25, 22, Image.SCALE_DEFAULT)));
		moneyIcon.setBounds(540, 11, 23, 21);
		frame.getContentPane().add(moneyIcon);
		
		JTextPane moneyTextPane = new JTextPane();
		moneyTextPane.setBounds(566, 11, 73, 20);
		moneyTextPane.setEditable(false);
		frame.getContentPane().add(moneyTextPane);
		moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
		
		JLabel farmNameLabel = new JLabel("");
		farmNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmNameLabel.setText(manager.getFarmName());
		farmNameLabel.setBounds(496, 36, 141, 21);
		frame.getContentPane().add(farmNameLabel);
		
		Farmer farmer = manager.getFarmer();
		Farm farm = farmer.getFarm();
		
		ArrayList<CropItem> cropItems = farmer.getCropItems();
		ArrayList<FoodItem> foodItems = farmer.getFoodItems();
		ArrayList<CropItem> cropItemsSet = farmer.getCropItemsSet();
		ArrayList<FoodItem> foodItemsSet = farmer.getFoodItemsSet();
		ArrayList<Crop> crops = farm.getCrops();
		ArrayList<Animal> animals = farm.getAnimals();
		ArrayList<Crop> cropsSet = farm.getCropsSet();
		ArrayList<Animal> animalsSet = farm.getAnimalsSet();
		
		String inventory = "";
		boolean isEmpty = true;
		if (cropItems.size() > 0) {
			isEmpty = false;
			for (CropItem c: cropItemsSet) {
				inventory += c.basicDetails() + "\n";
				inventory += "Amount: " + Collections.frequency(cropItems, c) + "\n\n";
			}
		}
				
		if (foodItems.size() > 0) {
			isEmpty = false;
			for (FoodItem f: foodItemsSet) {
				inventory += f.basicDetails() + "\n";
				inventory += "Amount: " + Collections.frequency(foodItems, f) + "\n\n";
			}
		}
		
		if (crops.size() > 0) {
			isEmpty = false;
			for (Crop c: cropsSet) {
				inventory += c.basicDetails() + "\n";
				inventory += "Amount: " + Collections.frequency(crops, c) + "\n\n";
			}
		}
		
		if (animals.size() > 0) {
			isEmpty = false;
			for (Animal a: animalsSet) {
				inventory += a.basicDetails() + "\n";
				inventory += "Amount: " + Collections.frequency(animals, a) + "\n\n";
			}
		}
		
		if (isEmpty) {
			inventory = "Your inventory is empty!";
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(138, 50, 230, 260);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		JTextPane inventoryTextPane = new JTextPane();
		inventoryTextPane.setEditable(false);
		scrollPane.setViewportView(inventoryTextPane);
		inventoryTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		inventoryTextPane.setText(inventory);
		
		JLabel inventoryLabel = new JLabel("Inventory");
		inventoryLabel.setBounds(222, 18, 132, 21);
		frame.getContentPane().add(inventoryLabel);
		
		JButton doneBtn = new JButton("Done");
		doneBtn.setBackground(SystemColor.inactiveCaptionBorder);
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		doneBtn.setBounds(550, 336, 89, 23);
		frame.getContentPane().add(doneBtn);
		
		JLabel cowIcon = new JLabel("");
		cowIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cow.apng.png"))
				.getImage().getScaledInstance(75, 80, Image.SCALE_DEFAULT)));
		cowIcon.setBounds(444, 120, 75, 80);
		frame.getContentPane().add(cowIcon);
				
		JLabel carrotIcon = new JLabel("");
		carrotIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrot.png"))
				.getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT)));
		carrotIcon.setBounds(450, 60, 55, 50);
		frame.getContentPane().add(carrotIcon);
		
		JLabel cornIcon = new JLabel("");
		cornIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/corn.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		cornIcon.setBounds(450, 220, 60, 55);
		frame.getContentPane().add(cornIcon);
		
	}
}
