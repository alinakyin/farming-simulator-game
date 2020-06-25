package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;

/**
 * The screen for buying crops in the county store.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class CropsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Crop> availableCrops; 
	private ArrayList<String> cropNames;
	private int purchaseIndex;
	private int currentAmount;
	
	public CropsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeCropsScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Buy crops");
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
		
		JTextPane cropProperties = new JTextPane();
		cropProperties.setEditable(false);
		cropProperties.setBackground(SystemColor.inactiveCaptionBorder);
		cropProperties.setBounds(400, 152, 188, 136);
		frame.getContentPane().add(cropProperties);
		
		availableCrops = manager.getFarmer().getFarm().getAvailableCrops();
		cropNames = new ArrayList<String>();
		for (Crop c: availableCrops) {
			cropNames.add(c.getName());
		}
		
		JComboBox<String> cropComboBox = new JComboBox<String>();
		cropComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		cropComboBox.setModel(new DefaultComboBoxModel<String>(cropNames.toArray(new String[0])));
		cropComboBox.setSelectedIndex(0);
		cropProperties.setText(availableCrops.get(0).toString());
		cropComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				purchaseIndex = cropComboBox.getSelectedIndex();
				cropProperties.setText(availableCrops.get(purchaseIndex).toString());
			}
		});
		cropComboBox.setBounds(68, 85, 216, 22);
		frame.getContentPane().add(cropComboBox);
		
		JLabel cropComboBoxLabel = new JLabel("Select a crop to buy...");
		cropComboBoxLabel.setBounds(70, 53, 172, 32);
		frame.getContentPane().add(cropComboBoxLabel);
		
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
		
		JLabel sproutIcon = new JLabel("");
		sproutIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		sproutIcon.setBounds(74, 202, 50, 50);
		frame.getContentPane().add(sproutIcon);
		
		JLabel sproutIcon1 = new JLabel("");
		sproutIcon1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		sproutIcon1.setBounds(155, 202, 50, 50);
		frame.getContentPane().add(sproutIcon1);
		
		JLabel sproutIcon2 = new JLabel("");
		sproutIcon2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		sproutIcon2.setBounds(234, 202, 50, 50);
		frame.getContentPane().add(sproutIcon2);
		
		JButton buyButton = new JButton("Buy");
		buyButton.setBackground(SystemColor.inactiveCaptionBorder);
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message;
				for (int i=0; i<currentAmount; i++) {
					message = GameEnvironment.buyCrop(manager.getFarmer().getFarm(), 
							availableCrops.get(purchaseIndex));
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
}
