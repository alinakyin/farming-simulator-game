package main;

import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The screen for tending to crops on the farm.
 * 
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class TendCropsScreen {

	private JFrame frame;
	private FarmManager manager;
	private ArrayList<Crop> crops;
	private ArrayList<String> cropNames;
	private ArrayList<CropItem> cropItems; 
	private ArrayList<String> cropItemNames;
	private JTextPane cropStatus;
	private JComboBox<String> cropItemComboBox;
	private int selectedIndex;
	private int itemSelectedIndex;
	
	public TendCropsScreen(FarmManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeTendCropsScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setTitle("Tend to crops");
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
		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(80, 60, 219, 260);
		frame.getContentPane().add(scrollPane);
		JPanel view = new JPanel();
		view.setBackground(SystemColor.inactiveCaptionBorder);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(view);
		
		JLabel chooseCropLabel = new JLabel("Select one of your crops to tend to...");
		chooseCropLabel.setBounds(73, 27, 250, 23);
		frame.getContentPane().add(chooseCropLabel);
		
		cropItems = manager.getFarmer().getCropItems();
		
		JButton selectBtn = new JButton("Use item");
		selectBtn.setBackground(SystemColor.inactiveCaptionBorder);
		selectBtn.setEnabled(false);
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crop c = crops.get(selectedIndex);
				CropItem item = cropItems.get(itemSelectedIndex);
				String message = GameEnvironment.tendToCrop(manager.getFarmer(), c, item);
				JOptionPane.showMessageDialog(frame, message, "Crop tended to!",
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
		
		cropItemComboBox = new JComboBox<String>();
		cropItemComboBox.setBackground(SystemColor.inactiveCaptionBorder);
		cropItemComboBox.setBounds(466, 223, 131, 22);
		frame.getContentPane().add(cropItemComboBox);
		
		if (cropItems.size() > 0) {
			chooseItemLabel.setText("...or choose a crop item:");
			selectBtn.setEnabled(true);
			cropItemNames = new ArrayList<String>();
			for (CropItem c: cropItems) {
				cropItemNames.add(c.getName());
			}
			
			itemSelectedIndex = 0;
			cropItemComboBox.setModel(new DefaultComboBoxModel<String>(cropItemNames.toArray(new String[0])));
			cropItemComboBox.setSelectedIndex(0);
			itemProperties.setText(cropItems.get(0).basicDetails());
			cropItemComboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					itemSelectedIndex = cropItemComboBox.getSelectedIndex();
					itemProperties.setText(cropItems.get(itemSelectedIndex).basicDetails());
				}
			});
			
		} else {
			itemProperties.setText("You have no crop items... visit the store to change that!");
		}
		
		JButton waterBtn = new JButton("Water");
		waterBtn.setBackground(SystemColor.inactiveCaptionBorder);
		waterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crop c = crops.get(selectedIndex);
				String message = GameEnvironment.waterCrop(manager.getFarmer(), c);
				JOptionPane.showMessageDialog(frame, message, "Crop watered!",
					    JOptionPane.PLAIN_MESSAGE);
				finishedWindow();
			}
		});
		waterBtn.setBounds(339, 223, 89, 23);
		frame.getContentPane().add(waterBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBackground(SystemColor.menu);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		cancelBtn.setBounds(451, 336, 89, 23);
		frame.getContentPane().add(cancelBtn);
		
		cropNames = new ArrayList<String>();
		crops = manager.getFarmer().getFarm().getCrops();
		cropStatus.setText(crops.get(0).toDetailedString());
		
		JLabel waterIcon = new JLabel("");
		waterIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/water.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		waterIcon.setBounds(359, 169, 40, 40);
		frame.getContentPane().add(waterIcon);
		
		JLabel sproutIcon = new JLabel("");
		sproutIcon.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sprout.png"))
				.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		sproutIcon.setBounds(557, 146, 50, 50);
		frame.getContentPane().add(sproutIcon);
		
		for (Crop c: crops) {
			cropNames.add(c.getName());
		}
		
		ButtonGroup group = new ButtonGroup();
		
		int y = 20;
		int index = 0;
		for (String name: cropNames) {
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
		
	}
	
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
    		selectedIndex = Integer.parseInt(btn.getActionCommand());
    		crops = manager.getFarmer().getFarm().getCrops();
            cropStatus.setText(crops.get(selectedIndex).toDetailedString());
        }
    };
}
