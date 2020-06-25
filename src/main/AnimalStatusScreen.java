package main;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

/**
 * The screen for viewing the status of all animals on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class AnimalStatusScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public AnimalStatusScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeAnimalStatusScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Animal Status");
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
		
		String animalStatus = "";
		Farmer farmer = manager.getFarmer();
		Farm farm = farmer.getFarm();
		
		boolean isEmpty = true;
		ArrayList<Animal> animals = farm.getAnimals();
		if (animals.size() > 0) {
			isEmpty = false;
			for (Animal a: animals) {
				animalStatus += a.toDetailedString() + "\n\n";
			}
		}
		
		if (isEmpty) {
			animalStatus = "You don't have any animals.";
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(138, 50, 230, 260);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		JTextPane animalStatusTextPane = new JTextPane();
		animalStatusTextPane.setEditable(false);
		scrollPane.setViewportView(animalStatusTextPane);
		animalStatusTextPane.setText(animalStatus);
		animalStatusTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		
		JLabel animalStatusLabel = new JLabel("Animal Status");
		animalStatusLabel.setBounds(213, 18, 132, 21);
		frame.getContentPane().add(animalStatusLabel);
		
		JButton doneBtn = new JButton("Done");
		doneBtn.setBackground(SystemColor.inactiveCaptionBorder);
		doneBtn.setBounds(550, 336, 89, 23);
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(doneBtn);
		
		JLabel pigIcon = new JLabel("");
		pigIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pig.gif"))
				.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
		pigIcon.setBounds(450, 60, 80, 75);
		frame.getContentPane().add(pigIcon);
		
		JLabel chickenIcon = new JLabel("");
		chickenIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chicken.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		chickenIcon.setBounds(444, 140, 60, 55);
		frame.getContentPane().add(chickenIcon);
		
		JLabel goatIcon = new JLabel("");
		goatIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goat.gif"))
				.getImage().getScaledInstance(80, 75, Image.SCALE_DEFAULT)));
		goatIcon.setBounds(450, 220, 80, 75);
		frame.getContentPane().add(goatIcon);
		
	}
}