package main;

/**
 * Extends the Farm class to a DairyFarm, which has its own set of food items, crop items, crops and animals
 * with custom attributes.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class DairyFarm extends Farm {
	
	/**
	 * Constructs an instance of DairyFarm
	 * @param name The name of the farm
	 * @param farmer The name of the farmer who owns the farm
	 */
	public DairyFarm(String name, String farmer) {
		super(name, farmer);
		super.setMoney(250); 
		super.setMaxNumCrops(5);
		super.setBonus("Dairy farms offer a unique animal: Dairy cows! These literal cash cows will generate a daily bonus greater "
				+ "than that of any other animal, compared to other types of farms."); 

		FoodItem apples = new FoodItem("Apples", 60, 25); 
		FoodItem seeds = new FoodItem("Seeds", 40, 15); 
		FoodItem vitamins = new FoodItem("Vitamins", 100, 50); 
		CropItem fertilizer = new CropItem("Fertilizer", 20, 2);
		CropItem pesticides = new CropItem("Pesticides", 60, 3);
		CropItem love = new CropItem("Love", 150, 4);
		availableFoodItems.add(apples);
		availableFoodItems.add(seeds);
		availableFoodItems.add(vitamins);
		availableCropItems.add(fertilizer);
		availableCropItems.add(pesticides);
		availableCropItems.add(love);
		
		Animal cows = new Animal("Dairy cows", 120, 6, 100, 40); 
		Animal chickens = new Animal("Chickens", 30, 6, 20, 8);
		Animal sheep = new Animal("Sheep", 50, 6, 80, 15);

		availableAnimals.add(cows);
		availableAnimals.add(chickens);
		availableAnimals.add(sheep);
		
		Crop wheat = new Crop("Wheat", 5, 30, 4);
		Crop rice = new Crop("Rice", 7, 35, 4);
		Crop corn = new Crop("Corn", 12, 50, 3);
		Crop barley = new Crop("Barley", 8, 60, 5);
		Crop kumara = new Crop("Kumara", 120, 350, 6);
		Crop carrots = new Crop("Carrots", 7, 21, 2);
		availableCrops.add(wheat);
		availableCrops.add(rice);
		availableCrops.add(corn);
		availableCrops.add(barley);
		availableCrops.add(kumara);
		availableCrops.add(carrots);
	}

}
