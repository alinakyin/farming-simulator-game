package main;

/**
 * Extends the Item class to FoodItems, which are used for feeding animals on the farm.
 * It includes a healthGiven attribute, which is used to add to an animal's healthiness by the specified amount.
 *
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class FoodItem extends Item {
	
	/**
	 * The amount of health given to the Animal the item is used on
	 */
	private int healthGiven;
	
	/**
	 * Constructs an instance of FoodItem
	 * @param name The name of the item
	 * @param price The price of the item
	 * @param bonus The health bonus of the item
	 */
	public FoodItem(String name, int price, int bonus) {
		super(name, price);
		healthGiven = bonus;
	}
	
	/**
	 * @return The health bonus of the item
	 */
	public int getHealthGiven() {
		return healthGiven;
	}
	
	/**
	 * Gets the details of the item (for store)
	 * @return A string containing the type, name, price and health bonus of the item
	 */
	public String toDetailedString() {
		return ("Type: Food item\nItem name: " + this.getName() +
                "\nItem price: $"+ this.getPrice() +
                "\nHealth given: "+ this.getHealthGiven() + " points");  
	}
	
	/**
	 * Gets the important information of the item 
	 * @return A string containing the name, price and health bonus of the item
	 */
	public String toString() {
		return ("Item name: " + this.getName() +
                "\nItem price: $"+ this.getPrice() +
                "\nHealth given: "+ this.getHealthGiven() + " points");  
	}
	
	/**
	 * Gets only the name and bonus of the item (for inventory)
	 * @return The name and health bonus of the item
	 */
	public String basicDetails() {
		return ("Item name: " + this.getName() +
                "\nHealth given: "+ this.getHealthGiven() + " points");  
	}
	
}
