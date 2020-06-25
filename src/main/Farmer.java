package main;

import java.util.ArrayList;

/**
 * The Farmer of our Farm Simulator. 
 * The Farmer owns a Farm and is able to buy an assortment of items to use on their crops and animals. 
 * They can also perform many actions, including harvesting crops or playing with animals.
 *
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class Farmer {
	
	/**
	 * The name of the farmer
	 */
	private String name; 
	
	/**
	 * The age of the farmer
	 */
	private int age;
	
	/**
	 * The number of actions the farmer has performed today
	 */
	private int actionCount;
	
	/**
	 * The list of items the farmer currently has for tending to crops
	 */
	private ArrayList<CropItem> cropItems;
	
	/**
	 * The list of items the farmer currently has for feeding animals
	 */
	private ArrayList<FoodItem> foodItems; 
	
	/**
	 * The farm that this farmer owns
	 */
	private Farm farm; 
	
	/**
	 * Constructs an instance of a Farmer
	 * @param farmerName The name of the farmer
	 * @param farmerAge The age of the farmer
	 * @param newFarm An instance of a Farm 
	 */
	public Farmer(String farmerName, int farmerAge, Farm newFarm) {
		name = farmerName;
		age = farmerAge;
		farm = newFarm;
		cropItems = new ArrayList<CropItem>();
		foodItems = new ArrayList<FoodItem>();
		actionCount = 0;
	}

	/**
	 * @return The name of the farmer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The age of the farmer
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * @return The current action count of the farmer
	 */
	public int getActionCount() {
		return actionCount;
	}
	
	/**
	 * Increases the farmer's action count
	 */
	public void increaseActionCount() {
		actionCount++;
	}
	
	/**
	 * Resets the farmer's action count (used when moving to a new day)
	 */
	public void resetActionCount() {
		actionCount = 0;
	}
	
	/**
	 * @return The farm this farmer owns
	 */
	public Farm getFarm() {
		return farm;
	}
	
	/**
	 * @return The crop items currently owned by the farmer
	 */
	public ArrayList<CropItem> getCropItems() {
		return cropItems;
	}
	
	/**
	 * @return The food items currently owned by the farmer
	 */
	public ArrayList<FoodItem> getFoodItems() {
		return foodItems;
	}
	
	/**
	 * @return A string showing the farmer's current money available
	 */
	public String viewMoney() {
		return ("Current money available: $" + farm.getMoney() + "\n");
	}
	
	/**
	 * @return An ArrayList of CropItems without duplicates to allow unique access for indexing
	 */
	public ArrayList<CropItem> getCropItemsSet() {
		ArrayList<CropItem> set = new ArrayList<CropItem>();
		for (CropItem c: cropItems) {
			if (!(set.contains(c))) {
				set.add(c);
			}
		}
		return set;
	}	

	/**
	 * @return An ArrayList of FoodItems without duplicates to allow unique access for indexing
	 */
	public ArrayList<FoodItem> getFoodItemsSet() {
		ArrayList<FoodItem> set = new ArrayList<FoodItem>();
		for (FoodItem f: foodItems) {
			if (!(set.contains(f))) {
				set.add(f);
			}
		}
		return set;
	}	
	
	/**
	 * Gives the crop the item and removes the item from the farmer's inventory
	 * @param crop The recipient crop
	 * @param item The item being used
	 */
	public void giveTo(Crop crop, CropItem item) {
		crop.give(item);
		cropItems.remove(item);
	}

	/**
	 * Wrapper method to allow the farmer to water the crop
	 * @param crop The crop receiving water
	 */
	public void giveWaterTo(Crop crop) {
		crop.water();
	}

	/**
	 * Feeds the animal the item and removes the item from the farmer's inventory
	 * @param animal The animal being fed 
	 * @param item The item being used
	 */
	public void feedTo(Animal animal, FoodItem item) {
		animal.feed(item);
		foodItems.remove(item);
	}
	
	/**
	 * Wrapper method to allow the farmer to play with the animal
	 * @param animal The animal being played with
	 */
	public void playWith(Animal animal) {
		animal.play();

	}
}
