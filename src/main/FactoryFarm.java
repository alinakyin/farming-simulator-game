package main;

/**
 * Extends the Farm class to a FactoryFarm, which has its own set of food items, crop items, crops and animals
 * with custom attributes.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class FactoryFarm extends Farm {

	/**
	 * Constructs an instance of FactoryFarm
	 * @param name The name of the farm
	 * @param farmer The name of the farmer who owns the farm
	 */
	public FactoryFarm(String name, String farmer) {
		super(name, farmer);
		super.setMoney(250); 
		super.setMaxNumCrops(3);
		super.setBonus("Factory farms are highly efficient in animal production! As such, you will receive a 25% boost in daily revenue "
				+ " from your animals, compared to other types of farms. However, their limited freedom means they will "
				+ "not be as happy, and the farm will have less space to grow crops.");
		
		FoodItem apples = new FoodItem("Apples", 60, 25); 
		FoodItem worms = new FoodItem("Worms", 40, 15); 
		FoodItem vitamins = new FoodItem("Vitamins", 100, 50); 
		CropItem fertilizer = new CropItem("Fertilizer", 20, 2);
		CropItem pesticides = new CropItem("Pesticides", 60, 3);
		CropItem love = new CropItem("Love", 100, 4);
		availableFoodItems.add(apples);
		availableFoodItems.add(worms);
		availableFoodItems.add(vitamins);
		availableCropItems.add(fertilizer);
		availableCropItems.add(pesticides);
		availableCropItems.add(love);
		
		Animal cows = new Animal("Cows", 100, 3, 75, 32); 
		Animal chickens = new Animal("Chickens", 30, 3, 20, 10);
		Animal pigs = new Animal("Pigs", 50, 3, 80, 20);
		availableAnimals.add(cows);
		availableAnimals.add(chickens);
		availableAnimals.add(pigs);

		Crop wheat = new Crop("Wheat", 6, 30, 3);
		Crop rice = new Crop("Rice", 5, 25, 4);
		Crop corn = new Crop("Corn", 12, 50, 3);
		Crop potatoes = new Crop("Potatoes", 12, 60, 5);
		Crop soybeans = new Crop("Soybeans", 120, 350, 6);
		Crop carrots = new Crop("Carrots", 7, 21, 2);
		availableCrops.add(wheat);
		availableCrops.add(rice);
		availableCrops.add(corn);
		availableCrops.add(potatoes);
		availableCrops.add(soybeans);
		availableCrops.add(carrots);
	}
}
