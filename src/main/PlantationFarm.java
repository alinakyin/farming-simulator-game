package main;

/**
 * Extends the Farm class to a PlantationFarm, which has its own set of food items, crop items, crops and animals
 * with custom attributes.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class PlantationFarm extends Farm {
	
	/**
	 * Constructs an instance of PlantationFarm
	 * @param name The name of the farm
	 * @param farmer The name of the farmer who owns the farm
	 */
	public PlantationFarm(String name, String farmer) {
		super(name, farmer);
		super.setMoney(250); 
		super.setMaxNumCrops(5);
		super.setBonus("Plantations are water based farms specialising in crop cultivation. As such, all your crops "
				+ "will grow a little faster, and earn a little more, than other types of farms!"); 
		
		FoodItem grains = new FoodItem("Grains", 60, 25); 
		FoodItem carrots = new FoodItem("Carrots", 40, 15); 
		FoodItem seeds = new FoodItem("Seeds", 100, 50); 
		CropItem fertilizer = new CropItem("Fertilizer", 20, 2);
		CropItem pesticides = new CropItem("Pesticides", 60, 3);
		CropItem love = new CropItem("Love", 100, 4);
		availableFoodItems.add(grains);
		availableFoodItems.add(carrots);
		availableFoodItems.add(seeds);
		availableCropItems.add(fertilizer);
		availableCropItems.add(pesticides);
		availableCropItems.add(love);
		
		Animal cows = new Animal("Cows", 100, 7, 75, 18); 
		Animal goats = new Animal("Goats", 50, 7, 30, 6);
		Animal sheep = new Animal("Sheep", 30, 7, 80, 11);

		availableAnimals.add(cows);
		availableAnimals.add(goats);
		availableAnimals.add(sheep);
		
		Crop sugarcane = new Crop("Sugarcane", 5, 30, 2);
		Crop rice = new Crop("Rice", 3, 31, 3);
		Crop cocoa = new Crop("Cocoa", 12, 50, 2);
		Crop pineapples = new Crop("Pineapples", 8, 60, 4);
		Crop saffron = new Crop("Saffron", 100, 350, 5);
		Crop coffee = new Crop("Coffee", 7, 21, 1);
		availableCrops.add(sugarcane);
		availableCrops.add(rice);
		availableCrops.add(cocoa);
		availableCrops.add(pineapples);
		availableCrops.add(saffron);
		availableCrops.add(coffee);
	}
}
