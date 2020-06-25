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
 * The screen for buying crop items and food items in the county store.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class ItemsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<CropItem> availableCropItems;
	private ArrayList<FoodItem> availableFoodItems;
	private ArrayList<String> itemNames;
	private int purchaseIndex;
	private int currentAmount;
	
	public ItemsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeItemsScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Buy items");
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
		
		JTextPane itemProperties = new JTextPane();
		itemProperties.setEditable(false);
		itemProperties.setBackground(SystemColor.inactiveCaptionBorder);
		itemProperties.setBounds(400, 152, 188, 136);
		frame.getContentPane().add(itemProperties);
		
		availableCropItems = manager.getFarmer().getFarm().getAvailableCropItems();
		availableFoodItems = manager.getFarmer().getFarm().getAvailableFoodItems();
		
		itemNames = new ArrayList<String>();
		for (CropItem c: availableCropItems) {
			itemNames.add(c.getName());
		}
		
		for (FoodItem f: availableFoodItems) {
			itemNames.add(f.getName());
		}
		
		JComboBox<String> itemComboBox = new JComboBox<String>();
		itemComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		itemComboBox.setModel(new DefaultComboBoxModel<String>(itemNames.toArray(new String[0])));
		itemComboBox.setSelectedIndex(0);
		itemProperties.setText(availableCropItems.get(0).toDetailedString());
		itemComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				purchaseIndex = itemComboBox.getSelectedIndex();
				if (purchaseIndex < 3) {
					itemProperties.setText(availableCropItems.get(purchaseIndex).toDetailedString());
				} else {
					itemProperties.setText(availableFoodItems.get(purchaseIndex-3).toDetailedString());
				}
			}
		});
		itemComboBox.setBounds(68, 85, 216, 22);
		frame.getContentPane().add(itemComboBox);
		
		JLabel itemComboBoxLabel = new JLabel("Select an item to buy...");
		itemComboBoxLabel.setBounds(70, 53, 172, 32);
		frame.getContentPane().add(itemComboBoxLabel);
		
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
					if (purchaseIndex < 3) {
						message = GameEnvironment.buyCropItem(manager.getFarmer(), 
								availableCropItems.get(purchaseIndex));
					} else {
						message = GameEnvironment.buyFoodItem(manager.getFarmer(), 
								availableFoodItems.get(purchaseIndex-3));
					}
					moneyTextPane.setText(Integer.toString(manager.getFarmer().getFarm().getMoney()));
					JOptionPane.showMessageDialog(frame, message, "Receipt",
						    JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		buyButton.setBounds(276, 302, 89, 32);
		frame.getContentPane().add(buyButton);

		JButton doneBtn = new JButton("Done");
		doneBtn.setBackground(SystemColor.inactiveCaptionBorder);
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
				manager.launchCountyStoreScreen();
			}
		});
		doneBtn.setBounds(550, 336, 89, 23);
		frame.getContentPane().add(doneBtn);
		
		JLabel appleIcon = new JLabel("");
		appleIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apple.png"))
				.getImage().getScaledInstance(50, 45, Image.SCALE_DEFAULT)));
		appleIcon.setBounds(74, 202, 50, 50);
		frame.getContentPane().add(appleIcon);
		
		JLabel pillIcon = new JLabel("");
		pillIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pill.png"))
				.getImage().getScaledInstance(46, 23, Image.SCALE_DEFAULT)));
		pillIcon.setBounds(228, 215, 46, 23);
		frame.getContentPane().add(pillIcon);
		
		JLabel loveIcon = new JLabel("");
		loveIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/love.gif"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		loveIcon.setBounds(155, 210, 50, 50);
		frame.getContentPane().add(loveIcon);
	}
}