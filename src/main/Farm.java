package main;

import java.util.ArrayList;

/**
 * The Farm of our Farm Simulator. 
 * The farm is the space where crops are grown and animals are raised. 
 * It is owned by a farmer and contains a certain amount of money.
 *
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class Farm {

	/**
	 * The name of the farm
	 */
	private String name;
	
	/**
	 * The name of the Farmer that owns this farm
	 */
	private String farmer;
	
	/**
	 * The amount of money the farm has
	 */
	private int money;
	
	/**
	 * The maximum number of crops the farm can grow
	 */
	private int maxNumCrops;
	
	/**
	 * The bonuses the type of farm offers
	 */
	private String bonus;
	
	/**
	 * The list of crops currently growing on this farm
	 */
	private ArrayList<Crop> crops; 
	
	/**
	 * The list of animals currently living on this farm
	 */
	private ArrayList<Animal> animals; 

	/**
	 * The list of crop items available for this farm
	 */
	protected ArrayList<CropItem> availableCropItems; // Accessible to the subclasses 
	
	/**
	 * The list of food items available for this farm
	 */
	protected ArrayList<FoodItem> availableFoodItems;
	
	/**
	 * The list of crops available for this farm
	 */
	protected ArrayList<Crop> availableCrops; 
	
	/**
	 * The list of animals available for this farm
	 */
	protected ArrayList<Animal> availableAnimals; 
	
	/**
	 * Constructs an instance of a Farm
	 * @param farmName The name of the farm
	 * @param farmerName The name of the farmer
	 */
	public Farm(String farmName, String farmerName) {
		name = farmName;
		farmer = farmerName;
		money = 0;
		maxNumCrops = 0;
		crops = new ArrayList<Crop>();
		animals = new ArrayList<Animal>(); 
		availableCropItems = new ArrayList<CropItem>(); 
		availableFoodItems = new ArrayList<FoodItem>();
		availableCrops = new ArrayList<Crop>();
		availableAnimals = new ArrayList<Animal>();
	}
	
	/**
	 * Sets the money attribute of the farm
	 * @param initialMoney Amount of money the farm starts off with
	 */
	public void setMoney(int initialMoney) {
		money = initialMoney;
	}
	
	/**
	 * Sets the maximum number of crops the farm can grow
	 * @param initialNum Capacity for crops that the farm starts off with
	 */
	public void setMaxNumCrops(int initialNum) {
		maxNumCrops = initialNum;
	}
	
	/**
	 * Sets the bonus this type of farm offers
	 * @param farmBonus Bonuses the player starts off with as a result of their farm choice
	 */
	public void setBonus(String farmBonus) {
		bonus = farmBonus;
	}
	
	/**
	 * Deducts money from the farm
	 * @param amount Amount of money to withdraw
	 */
	public void withdrawMoney(int amount) {
		money -= amount;
	}
	
	/**
	 * Adds money to the farm
	 * @param amount Amount of money to deposit
	 */
	public void depositMoney(int amount) {
		money += amount;
	}
	
	/**
	 * Adds space for more crops to be grown on the farm
	 */
	public void increaseMaxNumCrops() {
		maxNumCrops++;
	}
	
	/**
	 * @return The bonus the type of farm offers
	 */
	public String getBonus() {
		return bonus;
	}
	
	/**
	 * @return The maximum number of crops the farm can grow
	 */
	public int getMaxNumCrops() {
		return maxNumCrops;
	}
	
	/**
	 * @return The name of the farm
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The name of the farmer that owns this farm
	 */
	public String getFarmer() {
		return farmer;
	}
	
	/**
	 * @return The farm's current money
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Gets the details of the farm
	 * @return The name of the farm, the name of the farmer and the farm's current money
	 */
	public String toString() {
		return ("Farm: " + this.getName() +
                "\nFarmer: "+ this.getFarmer() +
                "\nCurrent money: $"+ this.getMoney() + "\n"); 
	}
	
	/**
	 * @return The list of crops currently growing on the farm
	 */
	public ArrayList<Crop> getCrops() {
		return crops;
	}
	
	/**
	 * @return The list of animals currently living on the farm
	 */
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	/**
	 * Adds a Crop to the list of this farm's current crops
	 * @param c The Crop to be added
	 * @return A string telling the player whether the add was successful or not
	 */
	public String addCrop(Crop c) {
		if (crops.size() < maxNumCrops) {
			crops.add(new Crop(c.getName(), c.getPrice(), c.getSalePrice(), c.getTimeTillHarvest()));
			return ("You have bought " + c.getName() + "! Your new balance is $" + this.getMoney() + ".\n"); 
		} else {
			return ("Your farm does not have any space for more crops! Tend to your land to make room.\n");
		}
	}
	
	/**
	 * Adds an Animal to the list of this farm's current animals
	 * @param a The Animal to be added
	 */
	public void addAnimal(Animal a) {
		animals.add(new Animal(a.getName(), a.getPrice(), a.getHappiness(), a.getHealth(), a.getIncome()));
	}
	
	/**
	 * @return The list of crop items available to be bought
	 */
	public ArrayList<CropItem> getAvailableCropItems() {
		return availableCropItems;
	}
	
	/**
	 * @return The list of food items available to bought
	 */
	public ArrayList<FoodItem> getAvailableFoodItems() {
		return availableFoodItems;
	}
	
	/**
	 * @return The list of crops available to be bought
	 */
	public ArrayList<Crop> getAvailableCrops() {
		return availableCrops;
	}
	
	/**
	 * @return The list of animals available to be bought
	 */
	public ArrayList<Animal> getAvailableAnimals() {
		return availableAnimals;
	}
	
	/**
	 * @return An ArrayList of Crops without duplicates to allow each unique crop to be shown once in the inventory
	 */
	public ArrayList<Crop> getCropsSet() {
		ArrayList<Crop> set = new ArrayList<Crop>();
		for (Crop c: crops) {
			if (!(set.contains(c))) {
				set.add(c);
			}
		}
		return set;
	}	
	
	/**
	 * @return An ArrayList of Animals without duplicates to allow each unique animal to be shown once in the inventory
	 */
	public ArrayList<Animal> getAnimalsSet() {
		ArrayList<Animal> set = new ArrayList<Animal>();
		for (Animal a: animals) {
			if (!(set.contains(a))) {
				set.add(a);
			}
		}
		return set;
	}	
	
	/**
	 * @return The list of crops ready to be harvested
	 */
	public ArrayList<Crop> findReadyToHarvest() {
		ArrayList<Crop> ready = new ArrayList<Crop>();
		for (Crop c: crops) {
			if (c.isHarvestable()) {
				ready.add(c);
			}
		}
		return ready;
	}
	
	/**
	 * Deposits the money earned from harvesting the crop, removes the crop from the farm and resets its statuses
	 * @param crop The crop to be harvested
	 */
	public void harvest(Crop crop) {
		int profit = crop.getSalePrice();
		depositMoney(profit);
		crops.remove(crop);
	}
	
	/**
	 * Removes a crop from the list of current crops at a specified index
	 * @param index The index to lose the crop at
	 */
	public void loseCrop(int index) {
		crops.remove(index);
	}
	
	/**
	 * Removes an animal from the list of current animals at a specified index
	 * @param index The index to lose the animal at
	 */
	public void loseAnimal(int index) {
		animals.remove(index);
	}
}
