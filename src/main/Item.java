package main;

/**
 * The superclass Item of our Farm Simulator. 
 * It has two descendants: CropItem and FoodItem. Items can be bought by the farmer
 * for day-to-day use on the farm.
 *
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class Item {
	
	/**
	 * The name of the item
	 */
	private String name;
	
	/**
	 * The price of the item
	 */
	private int price;
	
	/**
	 * Constructs an instance of an Item
	 * @param itemName The name of the item
	 * @param itemPrice The price of the item
	 */
	public Item(String itemName, int itemPrice) {
		name = itemName;
		price = itemPrice;
	}
	
	/**
	 * @return The name of the item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The price of the item
	 */
	public int getPrice() {
		return price;
	}
}