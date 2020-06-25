package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * The screen for buying animals in the county store.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class AnimalsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Animal> availableAnimals; 
	private ArrayList<String> animalNames;
	private int purchaseIndex;
	private int currentAmount;
	private JLabel animalIcon;
	
	public AnimalsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeAnimalsScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Buy animals");
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
		
		JLabel farmNameLabel = new JLabel("");
		farmNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmNameLabel.setText(manager.getFarmName());
		farmNameLabel.setBounds(496, 36, 141, 21);
		frame.getContentPane().add(farmNameLabel);
		
		JTextPane animalProperties = new JTextPane();
		animalProperties.setEditable(false);
		animalProperties.setBackground(SystemColor.inactiveCaptionBorder);
		animalProperties.setBounds(400, 152, 188, 136);
		frame.getContentPane().add(animalProperties);
		
		availableAnimals = manager.getFarmer().getFarm().getAvailableAnimals();
		animalNames = new ArrayList<String>();
		for (Animal a: availableAnimals) {
			animalNames.add(a.getName());
		}
		
		animalIcon = new JLabel("");
		setAnimal(0);
		animalIcon.setBounds(125, 190, 130, 85);
		frame.getContentPane().add(animalIcon);
		
		JComboBox<String> animalComboBox = new JComboBox<String>();
		animalComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		animalComboBox.setModel(new DefaultComboBoxModel<String>(animalNames.toArray(new String[0])));
		animalComboBox.setSelectedIndex(0);
		animalProperties.setText(availableAnimals.get(0).toString());
		animalComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				purchaseIndex = animalComboBox.getSelectedIndex();
				setAnimal(purchaseIndex);
				animalProperties.setText(availableAnimals.get(purchaseIndex).toString());
			}
		});
		animalComboBox.setBounds(68, 85, 216, 22);
		frame.getContentPane().add(animalComboBox);
		
		JLabel animalComboBoxLabel = new JLabel("Select an animal to buy...");
		animalComboBoxLabel.setBounds(70, 53, 172, 32);
		frame.getContentPane().add(animalComboBoxLabel);
		
		currentAmount = 1;
		JTextField amount = new JTextField();
		amount.setBackground(SystemColor.inactiveCaptionBorder);
		amount.setEditable(false);
		amount.setText("1");
		amount.setBounds(436, 86, 47, 20);
		frame.getContentPane().add(amount);
		amount.setColumns(10);
		
		JButton decreaseBtn = new JButton("-");
		decreaseBtn.setBackground(SystemColor.inactiveCaptionBorder);
		decreaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentAmount = Integer.parseInt(amount.getText());
				if ((currentAmount - 1) > 0) {
					currentAmount--;
					amount.setText(Integer.toString(currentAmount));
				}
			}
		});
		decreaseBtn.setBounds(379, 85, 47, 23);
		frame.getContentPane().add(decreaseBtn);
		
		JButton increaseBtn = new JButton("+");
		increaseBtn.setBackground(SystemColor.inactiveCaptionBorder);
		increaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentAmount = Integer.parseInt(amount.getText());
				currentAmount++;
				amount.setText(Integer.toString(currentAmount));
			}
		});
		increaseBtn.setBounds(493, 85, 47, 23);
		frame.getContentPane().add(increaseBtn);
		
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setBounds(436, 53, 89, 32);
		frame.getContentPane().add(amountLabel);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setBackground(SystemColor.inactiveCaptionBorder);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message;
				for (int i=0; i<currentAmount; i++) {
					message = GameEnvironment.buyAnimal(manager.getFarmer().getFarm(), 
							availableAnimals.get(purchaseIndex));
					moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
					JOptionPane.showMessageDialog(frame, message, "Receipt",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		buyButton.setBounds(280, 296, 105, 28);
		frame.getContentPane().add(buyButton);

		JButton doneBtn = new JButton("Done");
		doneBtn.setBackground(SystemColor.inactiveCaptionBorder);
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				manager.launchCountyStoreScreen();
			}
		});
		doneBtn.setBounds(534, 331, 105, 28);
		frame.getContentPane().add(doneBtn);
	
	}
	
	/**
	 * Sets the animal icon based on which type of animal is selected
	 */
	private void setAnimal(int purchaseIndex) {
		if (availableAnimals.get(purchaseIndex).getName() == "Chickens") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chicken.png"))
					.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(125, 190, 130, 85);
		} else if (availableAnimals.get(purchaseIndex).getName() == "Pigs") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(125, 205, 130, 85);
		} else if (availableAnimals.get(purchaseIndex).getName() == "Horses") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/horse1.gif"))
					.getImage().getScaledInstance(85, 80, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(125, 190, 130, 85);
		} else if (availableAnimals.get(purchaseIndex).getName() == "Goats") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goat.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(125, 190, 130, 85);
		} else if (availableAnimals.get(purchaseIndex).getName() == "Sheep") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lamb.gif"))
					.getImage().getScaledInstance(170, 50, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(78, 190, 130, 85);
		} else {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cow.apng.png"))
					.getImage().getScaledInstance(75, 80, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(120, 180, 130, 85);
		}
	}
}
