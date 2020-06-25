package main;

/**
 * Extends the Farm class to an OutbackRanchFarm, which has its own set of food items, crop items, crops and animals
 * with custom attributes.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class OutbackRanchFarm extends Farm {
	
	/**
	 * Constructs an instance of OutbackRanchFarm
	 * @param name The name of the farm
	 * @param farmer The name of the farmer who owns the farm
	 */
	public OutbackRanchFarm(String name, String farmer) {
		super(name, farmer);
		super.setMoney(250); 
		super.setMaxNumCrops(7); 
		super.setBonus("Outback ranch farms specialise in vast, open land! As such, you will receive more space to grow crops, "
				+ "and your animals will have higher levels of happiness compared to other types of farms."); 
		
		FoodItem apples = new FoodItem("Apples", 60, 25); 
		FoodItem grains = new FoodItem("Grains", 40, 15); 
		FoodItem hay = new FoodItem("Hay", 100, 50); 
		CropItem fertilizer = new CropItem("Fertilizer", 20, 2);
		CropItem pesticides = new CropItem("Pesticides", 60, 3);
		CropItem love = new CropItem("Love", 100, 4);
		availableFoodItems.add(apples);
		availableFoodItems.add(grains);
		availableFoodItems.add(hay);
		availableCropItems.add(fertilizer);
		availableCropItems.add(pesticides);
		availableCropItems.add(love);

		Animal cows = new Animal("Cows", 100, 8, 75, 25); 
		Animal pigs = new Animal("Pigs", 50, 8, 80, 15);
		Animal horses = new Animal("Horses", 250, 8, 120, 60);

		availableAnimals.add(cows);
		availableAnimals.add(pigs);
		availableAnimals.add(horses);
		
		Crop wheat = new Crop("Wheat", 5, 30, 3);
		Crop rice = new Crop("Rice", 3, 20, 4);
		Crop corn = new Crop("Corn", 12, 50, 3);
		Crop peanuts = new Crop("Peanuts", 8, 60, 5);
		Crop sugarcane = new Crop("Sugarcane", 100, 350, 6);
		Crop beans = new Crop("Beans", 7, 21, 2);
		availableCrops.add(wheat);
		availableCrops.add(rice);
		availableCrops.add(corn);
		availableCrops.add(peanuts);
		availableCrops.add(sugarcane);
		availableCrops.add(beans);
	}
	
}
	

