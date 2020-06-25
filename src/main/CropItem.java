package main;

/**
 * Extends the Item class to CropItems, which are used for tending to crops on the farm.
 * It includes a harvestTimeBonus attribute, which is used to cut down on crops' harvest times by the specified amount.
 *
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class CropItem extends Item {
	
	/**
	 * The amount of time the item cuts a Crop's harvesting time down by
	 */
	private int harvestTimeBonus;
	
	/**
	 * Constructs an instance of CropItem
	 * @param name The name of the item
	 * @param price The price of the item
	 * @param bonus The harvest time bonus of the item
	 */
	public CropItem(String name, int price, int bonus) {
		super(name, price);
		harvestTimeBonus = bonus;
	}
	
	/**
	 * @return The harvest time bonus of the item
	 */
	public int getHarvestTimeBonus() {
		return harvestTimeBonus;
	}
	
	/**
	 * Gets the important information of the item
	 * @return A string containing the name, price and harvest time bonus of the item
	 */
	public String toString() {
		return ("Item name: " + this.getName() +
                "\nItem price: $"+ this.getPrice() +
                "\nHarvest time bonus: "+ this.getHarvestTimeBonus() + " day(s)"); 
	}
	
	/**
	 * Gets the details of the item (for store)
	 * @return A string containing the type, name, price and harvest time bonus of the item
	 */
	public String toDetailedString() {
		return ("Type: Crop item\nItem name: " + this.getName() +
                "\nItem price: $"+ this.getPrice() +
                "\nHarvest time bonus: "+ this.getHarvestTimeBonus() + " day(s)"); 
	}
	
	/**
	 * Gets only the name and bonus the item (for inventory)
	 * @return A string containing the name and harvest time bonus of the item
	 */
	public String basicDetails() {
		return ("Item name: " + this.getName() +
                "\nHarvest time bonus: "+ this.getHarvestTimeBonus() + " day(s)"); 
	}
}
