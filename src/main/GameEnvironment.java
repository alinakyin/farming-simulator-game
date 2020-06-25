package main;
import java.util.ArrayList;
import java.util.Random;

/**
 * The GameEnvironment of our Farm Simulator. 
 * It holds all of the logic necessary to play the game.
 *
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class GameEnvironment {
	
	/**
	 * The day the game is currently on
	 */
	private static int currentDay = 1;
	
	/**
	 * The chosen duration of the game
	 */
	private static int gameDuration;
	
	/**
	 * The total amount of money spent on purchases
	 */
	private static int totalExpenditure;
	
	/**
	 * @return The total amount of money spent, used for calculating profit upon completion of the game
	 */
	public static int getTotalExpenditure() {
		return totalExpenditure;
	}
	
	/**
	 * @return The day the game is currently on
	 */
	public static int getCurrentDay() {
		return currentDay;
	}
	
	/**
	 * @return The chosen duration of the game
	 */
	public static int getGameDuration() {
		return gameDuration;
	}
	
	/**
	 * Calculates the final score based on number and status of crops, animals, items and the farm's money
	 * @param farmer The current farmer
	 * @param gameDuration The chosen duration of the game
	 * @return The player's final score
	 */
	public static int getFinalScore(Farmer farmer, double gameDuration) {
		Farm farm = farmer.getFarm();
		ArrayList<Animal> animals = farm.getAnimals();
		ArrayList<Crop> crops = farm.getCrops();
		ArrayList<CropItem> cropItems = farmer.getCropItems();
		ArrayList<FoodItem> foodItems = farmer.getFoodItems();
		
		double score = farm.getMoney();
		
		for (Animal a: animals) {
			if (a.getHappiness() >= 8) {
				score += a.getDailyIncome();
			} 
			if (a.getHealth() >= (0.75 * a.getInitialHealth()))
				score += a.getDailyIncome();
		}
		
		for (Crop c: crops) {
			if (c.getTimeTillHarvest() < 2) {
				score += (c.getSalePrice() / 2);
			}
			else if (c.getTimeTillHarvest() < 3) {
				score += (c.getSalePrice() / 4);
			}
			else if (c.getTimeTillHarvest() < 4) {
				score += (c.getSalePrice() / 6);
			}
		}

		for (CropItem c: cropItems) {
			score += (c.getPrice() / 2);
		}
		
		for (FoodItem f: foodItems) {
			score += (f.getPrice() / 4);
		}
		
		score *= (gameDuration / 2);
		return (int)score;
	}
	
	/**
	 * Displays the farm’s name, the game’s duration, the profit made in this time, and a final score
	 * @param farmer The current farmer
	 * @return A message telling the player their final score
	 */
	public static String finishGame(Farmer farmer) {
		int score = getFinalScore(farmer, getGameDuration());
		
		return ("Congratulations on entering retirement! Sadly,\nyour days on this farm have come to an end.\n\nFarm: " + farmer.getFarm().getName() + 
				"\nGame duration: " + getGameDuration() + " days\n"
				+ "Profit made: $" + (farmer.getFarm().getMoney() - getTotalExpenditure() - 250) + "\nFinal score: " + score);
	}
	
	/**
	 * Notifies the player that they are out of actions for the day
	 * @param farmer The current farmer
	 * @return A message telling the player to move on to the next day
	 */
	public static String noActionsLeft(Farmer farmer) {
		return ("You have no more actions left for today! Move on to the next day.\n");
	}
	
	/**
	 * Takes input from the UI, constructs the appropriate type of farm and returns a Farmer
	 * @param chosenGameDuration The duration of the game 
	 * @param farmerName The name of the farmer
	 * @param farmerAge The age of the farmer
	 * @param farmString The type of farm
	 * @param farmName The name of the farm
	 * @return The farmer the player has made
	 */
	public static Farmer createFarmer(int chosenGameDuration, String farmerName, int farmerAge, String farmString, String farmName) {
		gameDuration = chosenGameDuration; // Setting the global variable
		Farm farm;
		
		if (farmString == "Dairy Farm") {
			farm = new DairyFarm(farmName, farmerName);
		} else if (farmString == "Factory Farm") {
			farm = new FactoryFarm(farmName, farmerName);
		} else if (farmString == "Outback Ranch Farm") {
			farm = new OutbackRanchFarm(farmName, farmerName);
		} else {
			farm = new PlantationFarm(farmName, farmerName);
		}
		
		Farmer farmer = new Farmer(farmerName, farmerAge, farm);
		
		return farmer;
	}
	
	/**
	 * Checks if the farmer can afford a CropItem, then adds it to cropItems 
	 * @param farmer The current farmer
	 * @param c The CropItem to be bought
	 * @return A message telling the player whether the purchase was successful and their new balance
	 */
	public static String buyCropItem(Farmer farmer, CropItem c) {
		ArrayList<CropItem> cropItems = farmer.getCropItems();
		Farm farm = farmer.getFarm();
		int balance = farm.getMoney() - c.getPrice();
		if (balance > 0) {
			farm.withdrawMoney(c.getPrice());
			totalExpenditure += c.getPrice();
			cropItems.add(c);
			return ("You have bought " + c.getName() + "! Your new balance is $" + farm.getMoney() + ".\n"); 
		} else {
			return ("You don't have enough money to buy " + c.getName() + "!\n");
		}
	}
	
	/**
	 * Checks if the farmer can afford a FoodItem, then adds it to foodItems 
	 * @param farmer The current farmer
	 * @param f The FoodItem to be bought
	 * @return A message telling the player whether the purchase was successful and their new balance
	 */
	public static String buyFoodItem(Farmer farmer, FoodItem f) {
		ArrayList<FoodItem> foodItems = farmer.getFoodItems();
		Farm farm = farmer.getFarm();
		int balance = farm.getMoney() - f.getPrice();
		if (balance > 0) {
			farm.withdrawMoney(f.getPrice());
			totalExpenditure += f.getPrice();
			foodItems.add(f);
			return ("You have bought " + f.getName() + "! Your new balance is $" + farm.getMoney() + ".\n"); 
		} else {
			return ("You don't have enough money to buy " + f.getName() + "!\n");
		}
	}
	
	/**
	 * Checks if the farmer can afford a Crop, then adds it to the farm's list of crops 
	 * @param farm The current farm
	 * @param c The Crop to be bought
	 * @return A message telling the player whether the purchase was successful or not
	 */
	public static String buyCrop(Farm farm, Crop c) {
		int balance = farm.getMoney() - c.getPrice();
		if (balance > 0) {
			farm.withdrawMoney(c.getPrice());
			totalExpenditure += c.getPrice();
			return (farm.addCrop(c));
		} else {
			return ("You don't have enough money to buy " + c.getName() + "!\n");
		}
	}
	
	/**
	 * Checks if the farmer can afford an Animal, then adds it to the farm's list of animals 
	 * @param farm The current farm
	 * @param a The Animal to be bought
	 * @return A message telling the player whether the purchase was successful or not 
	 */
	public static String buyAnimal(Farm farm, Animal a) {
		int balance = farm.getMoney() - a.getPrice();
		if (balance > 0) {
			farm.withdrawMoney(a.getPrice());
			totalExpenditure += a.getPrice();
			farm.addAnimal(a);
			return ("You have bought " + a.getName() + "! Your new balance is $" + farm.getMoney() + ".\n"); 
		} else {
			return ("You don't have enough money to buy " + a.getName() + "!\n");
		}
	}
	
	/**
	 * Gives the farmer their daily cash bonus, deducts happiness from animals and checks if the game is finished
	 * @param farmer The current farmer
	 * @return A message saying if the game has finished, or the current day and daily bonus received otherwise
	 */
	public static String moveDayForward(Farmer farmer) {
		currentDay++;
		farmer.resetActionCount();
		int dailyBonus = 0;
		ArrayList<Animal> animals = farmer.getFarm().getAnimals();
		for (Animal a: animals) {
			dailyBonus += a.getDailyIncome();
			a.loseHappiness(); 
			a.loseHealth();
		}
		farmer.getFarm().depositMoney(dailyBonus);
		
		ArrayList<Crop> crops = farmer.getFarm().getCrops();
		for (Crop c: crops) {
			c.updateGrowingTime();
		}
	
		if (currentDay == gameDuration) {
			return ("Finished");
			
		} else {
			return ("Congratulations! You are now on Day " + currentDay + ". You have received a daily "
					+ "bonus of $" + dailyBonus + ".\n");
		}
	}
	
	/**
	 * Calls the Farmer method to water a crop
	 * @param farmer The current farmer
	 * @param c The crop receiving water
	 * @return A message telling the player the crop's new time till harvest and how long it has been growing
	 */
	public static String waterCrop(Farmer farmer, Crop c) {
		farmer.increaseActionCount();
		farmer.giveWaterTo(c);

		if (c.getTimeTillHarvest() <= c.getTimeGrowing()) {
			return ("You have watered "+ c.getName() + ". It is now ready to harvest!\n");
		} else {
			return("You have watered "+ c.getName() + "! Its time"
					+ " till harvest is now " + c.getTimeTillHarvest() + " day(s). It has been growing for "
							+ c.getTimeGrowing() + " day(s).\n");
		}
	}
	
	/**
	 * Calls the Farmer method to give a crop an item
	 * @param farmer The current farmer
	 * @param crop The crop receiving the item
	 * @param item The item being used
	 * @return A message telling the player the crop's new time till harvest and how long it has been growing
	 */
	public static String tendToCrop(Farmer farmer, Crop crop, CropItem item) {
		farmer.increaseActionCount();
		farmer.giveTo(crop, item);
		
		if (crop.getTimeTillHarvest() <= crop.getTimeGrowing()) {
			return ("You have given " + item.getName() + " to "+ crop.getName() + ". It is now ready to harvest!\n");
		} else {
			return ("You have given "+ item.getName() + " to " + crop.getName() + "! Its time"
					+ " till harvest is now " + crop.getTimeTillHarvest() + " day(s). It has been growing for "
							+ crop.getTimeGrowing() + " day(s).\n");
		}
	}
	
	/**
	 * Calls the Farmer method to feed the animal
	 * @param farmer The current farmer
	 * @param animal The animal being fed
	 * @param item The food item being used
	 * @return A message telling the player the animal's new healthiness level
	 */
	public static String feedAnimal(Farmer farmer, Animal animal, FoodItem item) {
		farmer.increaseActionCount();
		farmer.feedTo(animal, item);
		return ("You have fed "+ animal.getName() + " with " + 
				item.getName() + "! Its healthiness level is now " 
				+ animal.getHealth() + " out of 120.\n");
	}
	
	/**
	 * Calls the Farmer method to play with the animal
	 * @param farmer The current farmer
	 * @param animal The animal being played with 
	 * @return A message telling the player the animal's new happiness level
	 */
	public static String playWithAnimal(Farmer farmer, Animal animal) {
		farmer.increaseActionCount();
		farmer.playWith(animal);
		return ("You played with " + animal.getName() + "! Its happiness level is now "
				+ animal.getHappiness() + " out of 10.\n");
	}
	
	/**
	 * Calls the Farm method to harvest the crop 
	 * @param farmer The current farmer
	 * @param cropToHarvest The crop to be harvested
	 * @return A message telling the player how much they earned from harvesting the crop
	 */
	public static String harvestCrop(Farmer farmer, Crop cropToHarvest) {
		farmer.increaseActionCount();
		farmer.getFarm().harvest(cropToHarvest);
		return ("You have earned $" + cropToHarvest.getSalePrice() + " from harvesting "
				+ cropToHarvest.getName() + "!\n");
	}
	
	/**
	 * Increases the maximum number of crops that can be grown on the farm and gives all animals a small happiness bonus
	 * @param farmer The current farmer
	 * @param animals The list of animals on the farm
	 * @return A message telling the player what they have received for tending to the land
	 */
	public static String tendToLand(Farmer farmer, ArrayList<Animal> animals) {
		farmer.increaseActionCount();
		farmer.getFarm().increaseMaxNumCrops();
		for (Animal a: animals) {
			a.happinessBonus();
		}
		return ("You have been granted more space to"
				+ " grow crops. All of the animals on\nyour farm also received a small happiness bonus!\n");
	}
	
	/**
	 * The player loses one or more of their animals and the remaining animals lose a substantial amount of happiness
	 * @param farmer The current farmer
	 * @param animals The list of animals on the farm
	 * @return A message telling the player how many animals were lost
	 */
	public static String brokenFence(Farmer farmer, ArrayList<Animal> animals) {
		int numAnimals = animals.size();
		int numToLose;
		String depression = "";
		
		if (numAnimals == 1) {
			numToLose = 1;
			farmer.getFarm().loseAnimal(0);
		} else {
			depression = "The remaining animals on your farm have suffered "
					+ "a bout of\ndepression for each lost friend...";
			numToLose = randomNumberInRange(1, numAnimals);
			ArrayList<Integer> indexesToLose = new ArrayList<Integer>();
			int range = numAnimals - 1;
			for (int i=0; i<numToLose; i++) {
				indexesToLose.add(randomNumberInRange(0, range));
				range--; // Because the available range shrinks as you lose animals
			}
			for (Integer i: indexesToLose) { 
				farmer.getFarm().loseAnimal(i);
			}
			
			for (Animal a: animals) {
				a.getDepression();
			}
		}
			
		return "Uh oh! Overnight, " + numToLose + " of your animals escaped through a broken fence.\n"
				+ depression;
	}
	
	/**
	 * The player loses half of their growing crops, the exact crops are determined randomly
	 * @param farmer The current farmer
	 * @param crops The list of crops growing on the farm
	 * @return A message telling the player how many crops were lost
	 */
	public static String drought(Farmer farmer, ArrayList<Crop> crops) {
		int numCrops = crops.size();
		int numToLose = numCrops / 2;
		ArrayList<Integer> indexesToLose = new ArrayList<Integer>();
		for (int i=0; i<numToLose; i++) {
			indexesToLose.add(randomNumberInRange(0, numCrops-1));
		}
		for (Integer i: indexesToLose) {
			farmer.getFarm().loseCrop(i);
		}
		
		return "A drought has hit! The wells have dried up, and as a result,\n"
				+ "you have lost " + numToLose + " of your growing crops to thirst.";
	}
	
	/**
	 * The player earns a cash prize, scaled to the number of crops and animals on the farm
	 * @param farmer The current farmer
	 * @param crops The list of crops growing on the farm
	 * @param animals The list of animals on the farm
	 * @return A message telling the player how much they earned
	 */
	public static String countyFair(Farmer farmer, ArrayList<Crop> crops,  ArrayList<Animal> animals) {
		int numCrops = crops.size();
		int numAnimals = animals.size();
		int cashPrize = 75 + (50 * numAnimals + 25 * numCrops);
		farmer.getFarm().depositMoney(cashPrize);
		return "You won first prize at the annual county fair! Your farm has earned a $" + cashPrize + " bonus!";
	}
	
	/**
	 * Returns a random int in a given range
	 * @param min The minimum int (inclusive)
	 * @param max The maximum int (exclusive)
	 * @return A random int
	 */
	public static int randomNumberInRange(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	/**
	 * Generates a random double and calls a random event if it is within a certain range
	 * @param farmer The current farmer
	 * @return The message passed from the specific random event method 
	 */
	public static String generateRandomEvent(Farmer farmer) {
		String message = null;
		ArrayList<Crop> crops = farmer.getFarm().getCrops(); 
		ArrayList<Animal> animals = farmer.getFarm().getAnimals(); 
		int numCrops = crops.size();
		int numAnimals = animals.size();
		
		double probability = Math.random();
		
		if (probability < 0.15 && numAnimals >= 1) { 
			message = brokenFence(farmer, animals);
		}

		if (probability > 0.15 && probability < 0.3 && numCrops > 1) {
			message = drought(farmer, crops);
		}
		
		if (probability > 0.3 && probability < 0.45) {
			message = countyFair(farmer, crops, animals);
		}
		
		return message;
	}
		
}


