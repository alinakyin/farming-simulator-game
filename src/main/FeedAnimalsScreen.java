package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * The screen for feeding animals on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class FeedAnimalsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Animal> animals;
	private ArrayList<String> animalNames;
	private ArrayList<FoodItem> foodItems; 
	private ArrayList<String> foodItemNames;
	private JTextPane animalStatus;
	private JComboBox<String> foodItemComboBox;
	private int selectedIndex;
	private int itemSelectedIndex;
	private JLabel healthIcon;
	private JLabel animalIcon;
	
	public FeedAnimalsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeFeedAnimalsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Feed animals");
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
		
		animalStatus = new JTextPane();
		animalStatus.setEditable(false);
		animalStatus.setBackground(SystemColor.inactiveCaptionBorder);
		animalStatus.setBounds(325, 70, 167, 97);
		frame.getContentPane().add(animalStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(80, 60, 219, 260);
		frame.getContentPane().add(scrollPane);
		JPanel view = new JPanel();
		view.setBackground(SystemColor.inactiveCaptionBorder);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(view);
		
		JLabel chooseAnimalLabel = new JLabel("Select one of your animals to feed...");
		chooseAnimalLabel.setBounds(65, 27, 250, 23);
		frame.getContentPane().add(chooseAnimalLabel);
		
		foodItems = manager.getFarmer().getFoodItems();
		
		JButton selectBtn = new JButton("Give item");
		selectBtn.setBackground(SystemColor.inactiveCaptionBorder);
		selectBtn.setEnabled(false);
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal a = animals.get(selectedIndex);
				FoodItem item = foodItems.get(itemSelectedIndex);
				String message = GameEnvironment.feedAnimal(manager.getFarmer(), a, item);
				JOptionPane.showMessageDialog(frame, message, "Animal fed!",
					    JOptionPane.PLAIN_MESSAGE);
				finishedWindow();
			}
		});
		selectBtn.setBounds(550, 336, 89, 23);
		frame.getContentPane().add(selectBtn);
		
		JTextPane itemProperties = new JTextPane();
		itemProperties.setEditable(false);
		itemProperties.setBackground(SystemColor.inactiveCaptionBorder);
		itemProperties.setBounds(464, 251, 139, 75);
		frame.getContentPane().add(itemProperties);

		JLabel chooseItemLabel = new JLabel("");
		chooseItemLabel.setBounds(466, 189, 151, 23);
		frame.getContentPane().add(chooseItemLabel);
		
		foodItemComboBox = new JComboBox<String>();
		foodItemComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		foodItemComboBox.setBounds(466, 223, 131, 22);
		frame.getContentPane().add(foodItemComboBox);
		
		if (foodItems.size() > 0) {
			chooseItemLabel.setText("Choose a food item:");
			selectBtn.setEnabled(true);
			foodItemNames = new ArrayList<String>();
			for (FoodItem f: foodItems) {
				foodItemNames.add(f.getName());
			}
			
			itemSelectedIndex = 0;
			foodItemComboBox.setModel(new DefaultComboBoxModel<String>(foodItemNames.toArray(new String[0])));
			foodItemComboBox.setSelectedIndex(0);
			itemProperties.setText(foodItems.get(0).basicDetails());
			foodItemComboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					itemSelectedIndex = foodItemComboBox.getSelectedIndex();
					itemProperties.setText(foodItems.get(itemSelectedIndex).basicDetails());
				}
			});
			
		} else {
			itemProperties.setText("You have no food items... visit the store to change that!");
		}
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(SystemColor.menu);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelBtn.setBounds(451, 336, 89, 23);
		frame.getContentPane().add(cancelBtn);
		
		animalNames = new ArrayList<String>();
		animals = manager.getFarmer().getFarm().getAnimals();
		animalStatus.setText(animals.get(0).toDetailedString());
		
		String animalType = animals.get(0).getName();
		animalIcon = new JLabel("");
		setAnimal(animalType);
		frame.getContentPane().add(animalIcon);
		
		healthIcon = new JLabel("");
		setHealthBar(0);
		frame.getContentPane().add(healthIcon);
		
		for (Animal a: animals) {
			animalNames.add(a.getName());
		}
		
		ButtonGroup group = new ButtonGroup();
		
		int y = 20;
		int index = 0;
		for (String name: animalNames) {
			JRadioButton radioBtn = new JRadioButton(name);
			radioBtn.setOpaque(false);
			radioBtn.setBounds(20, y, 133, 26);
			radioBtn.setActionCommand(Integer.toString(index));
			if (index == 0) {
				radioBtn.setSelected(true);
			}
			group.add(radioBtn);
			view.add(radioBtn);
			radioBtn.addActionListener(listener);
			y += 40;
			index++;
		}
	}
	
	/**
	 * Sets the animal icon based on which type of animal is selected
	 */
	private void setAnimal(String animal) {
		if (animal == "Chickens") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chicken.png"))
					.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(355, 189, 130, 85);
		} else if (animal == "Pigs") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(355, 199, 130, 85);
		} else if (animal == "Horses") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/horse.gif"))
					.getImage().getScaledInstance(90, 85, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(355, 189, 130, 85);
		} else if (animal == "Goats") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goat.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(355, 189, 130, 85);
		} else if (animal == "Sheep") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lamb.gif"))
					.getImage().getScaledInstance(170, 50, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(303, 189, 130, 85);
		} else {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cow.apng.png"))
					.getImage().getScaledInstance(75, 80, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(345, 179, 130, 85);
		}
	}
	
	/**
	 * Sets the health bar based on healthiness levels
	 */
	private void setHealthBar(int selectedIndex) {
		if (animals.get(selectedIndex).getHealth() == 0) {
        	healthIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/empty.png"))
    				.getImage().getScaledInstance(83, 62, Image.SCALE_DEFAULT)));
        	healthIcon.setBounds(504, 76, 80, 80);
        } else if (animals.get(selectedIndex).getHealth() < 60) {
    		healthIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almostempty.png"))
    				.getImage().getScaledInstance(92, 82, Image.SCALE_DEFAULT)));
    		healthIcon.setBounds(500, 86, 80, 80);
        } else if (animals.get(selectedIndex).getHealth() < 120) {
    		healthIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almostfull.png"))
    				.getImage().getScaledInstance(85, 75, Image.SCALE_DEFAULT)));
    		healthIcon.setBounds(502, 83, 80, 80);
        } else if (animals.get(selectedIndex).getHealth() == 120) {
        	healthIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/full.png"))
    				.getImage().getScaledInstance(80, 47, Image.SCALE_DEFAULT)));
        	healthIcon.setBounds(502, 77, 80, 80);
        }
	}
	
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
    		selectedIndex = Integer.parseInt(btn.getActionCommand());
    		animals = manager.getFarmer().getFarm().getAnimals();
    		setAnimal(animals.get(selectedIndex).getName());
            animalStatus.setText(animals.get(selectedIndex).toDetailedString());
            setHealthBar(selectedIndex);
        }
    };
}