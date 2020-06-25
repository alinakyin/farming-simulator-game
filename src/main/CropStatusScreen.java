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
 * The screen for viewing the status of all the crops growing on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class CropStatusScreen {

	private JFrame frame;
	private FarmManager manager;
	
	public CropStatusScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeCropStatusScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Crop Status");
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
		
		String cropStatus = "";
		Farmer farmer = manager.getFarmer();
		Farm farm = farmer.getFarm();
		
		boolean isEmpty = true;
		ArrayList<Crop> crops = farm.getCrops();
		
		if (crops.size() > 0) {
			isEmpty = false;
			for (Crop c: crops) {
				cropStatus += c.toDetailedString() + "\n\n";
			}
		}
		
		if (isEmpty) {
			cropStatus = "You don't have any crops.";
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(138, 50, 230, 260);
		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		frame.getContentPane().add(scrollPane);
		
		JTextPane cropStatusTextPane = new JTextPane();
		cropStatusTextPane.setEditable(false);
		scrollPane.setViewportView(cropStatusTextPane);
		cropStatusTextPane.setText(cropStatus);
		cropStatusTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		
		JLabel cropStatusLabel = new JLabel("Crop Status");
		cropStatusLabel.setBounds(217, 18, 132, 21);
		frame.getContentPane().add(cropStatusLabel);
		
		JButton doneBtn = new JButton("Done");
		doneBtn.setBackground(SystemColor.inactiveCaptionBorder);
		doneBtn.setBounds(550, 336, 89, 23);
		doneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		frame.getContentPane().add(doneBtn);
		
		JLabel maxCropsLabel = new JLabel("");
		maxCropsLabel.setText("Maximum number of crops: " + manager.getFarmer().getFarm().getMaxNumCrops());
		maxCropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxCropsLabel.setBounds(128, 321, 183, 32);
		frame.getContentPane().add(maxCropsLabel);
		
		JLabel wheatIcon = new JLabel("");
		wheatIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wheat.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		wheatIcon.setBounds(442, 142, 60, 55);
		frame.getContentPane().add(wheatIcon);
		
		JLabel carrotIcon = new JLabel("");
		carrotIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrot.png"))
				.getImage().getScaledInstance(55, 50, Image.SCALE_DEFAULT)));
		carrotIcon.setBounds(450, 60, 55, 50);
		frame.getContentPane().add(carrotIcon);
		
		JLabel cornIcon = new JLabel("");
		cornIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/corn.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		cornIcon.setBounds(446, 239, 60, 55);
		frame.getContentPane().add(cornIcon);
		
	}
}
