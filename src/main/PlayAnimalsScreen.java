package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * The screen for playing with animals on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class PlayAnimalsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Animal> animals;
	private ArrayList<String> animalNames;
	private JTextPane animalStatus;
	private int selectedIndex;
	private JLabel animalIcon;
	
	public PlayAnimalsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closePlayAnimalsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Play with animals");
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
		
		JLabel chooseAnimalLabel = new JLabel("Select one of your animals to play with...");
		chooseAnimalLabel.setBounds(60, 27, 250, 23);
		frame.getContentPane().add(chooseAnimalLabel);
		
		JButton selectBtn = new JButton("Play!");
		selectBtn.setBackground(SystemColor.inactiveCaptionBorder);
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Animal a = animals.get(selectedIndex);
				String message = GameEnvironment.playWithAnimal(manager.getFarmer(), a);
				JOptionPane.showMessageDialog(frame, message, "Animal played with!",
					    JOptionPane.PLAIN_MESSAGE);
				finishedWindow();
			}
		});
		selectBtn.setBounds(550, 336, 89, 23);
		frame.getContentPane().add(selectBtn);
		
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
		
		for (Animal a: animals) {
			animalNames.add(a.getName());
		}
		
		String animalType = animals.get(0).getName();
		animalIcon = new JLabel("");
		setAnimal(animalType);
		frame.getContentPane().add(animalIcon);
		
		JLabel butterflyIcon = new JLabel("");
		butterflyIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/butterfly.gif"))
				.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		butterflyIcon.setBounds(535, 154, 25, 25);
		frame.getContentPane().add(butterflyIcon);
		
		JLabel butterflyIcon1 = new JLabel("");
		butterflyIcon1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/butterfly.gif"))
				.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		butterflyIcon1.setBounds(505, 177, 25, 25);
		frame.getContentPane().add(butterflyIcon1);
				
		ButtonGroup group = new ButtonGroup();
		
		int y = 20;
		int index = 0;
		for (String name: animalNames) {
			JRadioButton radioBtn = new JRadioButton(name);
			radioBtn.setBounds(20, y, 133, 26);
			radioBtn.setActionCommand(Integer.toString(index));
			radioBtn.setOpaque(false);
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
			animalIcon.setBounds(375, 199, 130, 85);
		} else if (animal == "Pigs") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(375, 209, 130, 85);
		} else if (animal == "Horses") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/horse.gif"))
					.getImage().getScaledInstance(90, 85, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(375, 199, 130, 85);
		} else if (animal == "Goats") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goat.gif"))
					.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(375, 199, 130, 85);
		} else if (animal == "Sheep") {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lamb.gif"))
					.getImage().getScaledInstance(170, 50, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(323, 199, 130, 85);
		} else {
			animalIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cow.apng.png"))
					.getImage().getScaledInstance(75, 80, Image.SCALE_DEFAULT)));
			animalIcon.setBounds(365, 189, 130, 85);
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
        }
    };
}