package main;

import java.awt.Image;
import java.awt.SystemColor;
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


/**
 * The screen for harvesting crops on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class HarvestCropsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Crop> ready;
	private ArrayList<String> readyNames;
	private JTextPane cropStatus;
	private int selectedIndex;
	
	public HarvestCropsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeHarvestCropsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Harvest crops");
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
		
		cropStatus = new JTextPane();
		cropStatus.setEditable(false);
		cropStatus.setBackground(SystemColor.inactiveCaptionBorder);
		cropStatus.setBounds(325, 70, 203, 97);
		frame.getContentPane().add(cropStatus);
		
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
		
		JLabel chooseCropLabel = new JLabel("Select one of your crops to harvest...");
		chooseCropLabel.setBounds(76, 27, 250, 23);
		frame.getContentPane().add(chooseCropLabel);
		
		JButton selectBtn = new JButton("Harvest!");
		selectBtn.setBackground(SystemColor.inactiveCaptionBorder);
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crop c = ready.get(selectedIndex);
				String message = GameEnvironment.harvestCrop(manager.getFarmer(), c);
				JOptionPane.showMessageDialog(frame, message, "Crop harvested!",
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
		
	    ready = manager.getFarmer().getFarm().findReadyToHarvest();
		readyNames = new ArrayList<String>();
		
		for (Crop c: ready) {
			readyNames.add(c.getName());
		}
		
		ButtonGroup group = new ButtonGroup();
		
		cropStatus.setText(ready.get(0).toDetailedString());
		int y = 20;
		int index = 0;
		for (String name: readyNames) {
			JRadioButton radioBtn = new JRadioButton(name);
			radioBtn.setBounds(20, y, 133, 26);
			radioBtn.setOpaque(false);
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
		
		JLabel riceFieldIcon = new JLabel("");
		riceFieldIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ricefield.png"))
				.getImage().getScaledInstance(160, 140, Image.SCALE_DEFAULT)));
		riceFieldIcon.setBounds(470, 165, 160, 140);
		frame.getContentPane().add(riceFieldIcon);
		
		JLabel wheatIcon = new JLabel("");
		wheatIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wheat.png"))
				.getImage().getScaledInstance(60, 55, Image.SCALE_DEFAULT)));
		wheatIcon.setBounds(360, 190, 60, 55);
		frame.getContentPane().add(wheatIcon);
		
	}
	
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
    		selectedIndex = Integer.parseInt(btn.getActionCommand());
    		ready = manager.getFarmer().getFarm().findReadyToHarvest();
            cropStatus.setText(ready.get(selectedIndex).toDetailedString());
        }
    };
}
