package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The UserInterface of our Farm Simulator. 
 * It contains all the methods necessary for interacting with the user via the command line.
 *
 * @author Alina Phang
 * @version 1.0, May 2020.
 */
public class UserInterface {
	
	/**
	 * Asks the player to choose the game's duration
	 * @return The number of days the game will last
	 */
	public static int getNumDays() {
		Scanner scanner = new Scanner(System.in);  
	    System.out.println("How many days would you like this game to last?");

	    int numDays = scanner.nextInt();

	    while (numDays < 5 || numDays > 10) {
	    	System.out.println("Please enter a number between 5 and 10.");
	    	numDays = scanner.nextInt();  
	    }
	    
	    return numDays;
	}
	
	/**
	 * Asks the player to choose a name for their farmer
	 * @return The name of the farmer
	 */
	public static String getFarmerName() {
		Scanner scanner = new Scanner(System.in);  
	    System.out.println("What would you like to name your farmer?");

	    String farmerName = scanner.nextLine();
	    int length = farmerName.length();
	    while (length < 3 || length > 15 || !(farmerName.matches("[a-zA-Z]+"))) {
	    	System.out.println("Please enter a name between 3 and 15 characters long, alphabet only.");
	    	farmerName = scanner.nextLine();
	    	length = farmerName.length();
	    }
	    
	    return farmerName;
	}
	
	/**
	 * Asks the player to choose the age of their farmer
	 * @return The age of the farmer
	 */
	public static int getFarmerAge() {
		Scanner scanner = new Scanner(System.in);  
	    System.out.println("How old is your farmer?");

	    int farmerAge = scanner.nextInt();
	    
	    return farmerAge;
	}
	
	/**
	 * Gets the chosen type of farm
	 * @return The integer associated with the chosen type of farm
	 */
	public static int getFarmType() {
	    System.out.println("Choose a type of farm to own...\n"
	    		+ "1 : Dairy Farm\n"
	    		+ "2 : Factory Farm\n"
	    		+ "3 : Outback Ranch Farm\n"
	    		+ "4 : Plantation Farm");
	    
	    int farmType = getChoice(1, 4);
	    
	    return farmType;
	}
	
	/**
	 * Asks the player to name their farm
	 * @return The name of the farm
	 */
	public static String getFarmName() {
		Scanner scanner = new Scanner(System.in);  
	    System.out.println("What would you like to name your farm?");

	    String farmName = scanner.nextLine();
	    
	    return farmName;
	}
	
	/**
	 * Wrapper method that gets input from the player, and calls the GameEnvironment to create a Farmer
	 * @return The farmer the player has made
	 */
	public static Farmer initialiseGame() {
		int gameDuration = getNumDays();
	    String farmerName = getFarmerName();
	    int farmerAge = getFarmerAge();
	    int farmType = getFarmType();
	    String farmName = getFarmName();
	    
	    String farmString;
	    if (farmType == 1) {
	    	farmString = "Dairy Farm";
	    } else if (farmType == 2) {
	    	farmString = "Factory Farm";
	    } else if (farmType == 3) {
	    	farmString = "Outback Ranch Farm";
	    } else {
	    	farmString = "Plantation Farm";
	    }
	    
	    Farmer farmer = GameEnvironment.createFarmer(gameDuration, farmerName, farmerAge, farmString, farmName);
	    Farm farm = farmer.getFarm();
	    System.out.println("Welcome, " + farmerName + "! You are now the proud owner of a " 
	    				+ farmString + ". \n" + farm.getBonus() + "\n");
	    
	    return farmer;
	}
	
	/**
	 * A reusable method to take in the number range of available options and return once a valid number has been chosen
	 * @param begin The starting number
	 * @param numChoices The total number of available options
	 * @return The first valid number chosen
	 */		
	public static int getChoice(int begin, int numChoices) {
		Scanner scanner = new Scanner(System.in);  
		int choice = scanner.nextInt();
	    ArrayList<Integer> nums = new ArrayList<Integer>(numChoices);
	    
	    for (int i=begin; i<=numChoices; i++) { 
	    	nums.add(i);
	    }

	    while (!(nums.contains(choice)) ) {
	    	System.out.println("Please enter a valid number.");
	    	choice = scanner.nextInt();
	    }
	    
	    return choice;
	}
	
	/**
	 * A reusable method that waits for the player to enter 0 to go back to the previous state, used for read-only states
	 */
	public static void goBack() {
		Scanner scanner = new Scanner(System.in);  
		int choice = scanner.nextInt();
	    while (choice != 0) {
	    	choice = scanner.nextInt();
	    }
	}
	
	/**
	 * Asks the player whether they want to view the farm's crops or animals
	 * @param farmer The current farmer
	 */
	public static void viewFarmStatus(Farmer farmer) {
		System.out.println("0 : Go back\n"
				+ "1 : View crops\n"
	    		+ "2 : View animals");
		
		Farm farm = farmer.getFarm();
		int choice = getChoice(0, 2);
		
		if (choice == 0) {
			mainMenu(farmer);
		} else if (choice == 1) {
			System.out.println("\n0 : Go back\n");
			ArrayList<Crop> crops = farm.getCrops();
			viewCrops(crops, false, true);
			goBack();
			viewFarmStatus(farmer);
		} else {
			System.out.println("\n0 : Go back\n");
			ArrayList<Animal> animals = farm.getAnimals();
			viewAnimals(animals, false, true);
			goBack();
			viewFarmStatus(farmer);
		}
	}
	
	/**
	 * Asks the player whether they want to purchase anything from the store or view their current inventory
	 * @param farmer The current farmer
	 */
	public static void visitCountyStore(Farmer farmer) {
		System.out.println("0 : Go back\n"
				+ "1 : Go shopping\n"
	    		+ "2 : View inventory");
		
		int choice = getChoice(0, 2);
		
		if (choice == 0) {
			mainMenu(farmer);
		} else if (choice == 1) {
			goShopping(farmer);
		} else {
			viewFarmInventory(farmer);
		}
	}
	
	/**
	 * Allows the player to choose a section of the store and purchase multiple items at a time 
	 * @param farmer The current farmer
	 */
	public static void goShopping(Farmer farmer) {
		System.out.println("\n0 : Go back\n");
		System.out.println("Shop...\n" 
				+ "1 : Animals\n"
	    		+ "2 : Crops\n"
	    		+ "3 : Items");
		
		Farm farm = farmer.getFarm();
		int storeSection = getChoice(0, 3);
		if (storeSection == 0) {
			visitCountyStore(farmer);
		} else if (storeSection == 1) {
			System.out.println("\n0 : Go back\n");
			
			ArrayList<Animal> animals = farm.getAvailableAnimals(); 
			viewAvailableAnimals(animals); // Prints them 
			int purchase = getChoice(0, 3); 
			while (purchase != 0) {
				System.out.println(GameEnvironment.buyAnimal(farm, animals.get(purchase-1)));
				purchase = getChoice(0, 3); 
			}
			goShopping(farmer); // 0 was entered, go back to where you were
			
		} else if (storeSection == 2) {
			System.out.println("\n0 : Go back\n");
			
			ArrayList<Crop> crops = farm.getAvailableCrops();
			viewAvailableCrops(crops);	
			int purchase = getChoice(0, 6);
			while (purchase != 0) {
				System.out.println(GameEnvironment.buyCrop(farm, crops.get(purchase-1)));
				purchase = getChoice(0, 6); 
			}
			goShopping(farmer); 
			
		} else {
			System.out.println("\n0 : Go back\n");
			
			ArrayList<CropItem> cropItems = farm.getAvailableCropItems();
			ArrayList<FoodItem> foodItems = farm.getAvailableFoodItems();
			viewAvailableCropItems(cropItems);
			viewAvailableFoodItems(foodItems);
			int purchase = getChoice(0, 6);
			while (purchase != 0) {
				if (purchase < 4) {
					System.out.println(GameEnvironment.buyCropItem(farmer, cropItems.get(purchase-1)));
				} else {
					System.out.println(GameEnvironment.buyFoodItem(farmer, foodItems.get(purchase-4)));
				}
				purchase = getChoice(0, 6); 
			}
			goShopping(farmer); 
		}
	}
	
	/**
	 * Shows the player's current inventory
	 * @param farmer The current farmer
	 */
	public static void viewFarmInventory(Farmer farmer) {
		System.out.println("\n0: Go back\n");
		viewInventory(farmer);
		goBack();
		visitCountyStore(farmer);
	}

	/**
	 * Asks the player to confirm if they want to move to the next day and performs the action
	 * @param farmer The current farmer
	 */
	public static void moveToNextDay(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		System.out.println("Are you sure you want to move to the next day?\n"
				+ "1 : Yes\n"
				+ "2 : No");
		
		int choice = getChoice(0, 2);
		if (choice == 0 || choice == 2) {
			mainMenu(farmer);
			
		} else {
			String message = GameEnvironment.moveDayForward(farmer);
			if (message == "Finished") {
				System.out.println(GameEnvironment.finishGame(farmer));
				
			} else {
				System.out.println("\n0 : Main menu\n");
				System.out.println(message);
				
				String randomEvent = GameEnvironment.generateRandomEvent(farmer);
				System.out.println(randomEvent);

				goBack();
				mainMenu(farmer);
			}
		}
	}
	
	/**
	 * Asks the player to choose one of their crops to tend to
	 * @param farmer The current farmer
	 */
	public static void chooseCrop(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		System.out.println("Choose a crop to tend to...\n");
		ArrayList<Crop> crops = farmer.getFarm().getCrops(); 
		int numCrops = crops.size();
		viewCrops(crops, true, true);
		
		int choice = getChoice(0, numCrops);
		if (choice == 0) {
			mainMenu(farmer);
			
		} else {
			Crop crop = crops.get(choice-1);
			chooseCropItem(farmer, crop);
		}
	}
	
	/**
	 * Asks the player to choose water or a CropItem to tend to their chosen crop and performs the action
	 * @param farmer The current farmer
	 * @param crop The crop to be tended to
	 */
	public static void chooseCropItem(Farmer farmer, Crop crop) {
		System.out.println("\n0 : Go back\n");
		System.out.println("Tend to " + crop.getName() + " with...\n"
				+ "1 : Water\n" 
				+ "2 : Crop Item");
		
		int tendingMethod= getChoice(0, 2);
		if (tendingMethod == 0) {
			chooseCrop(farmer);
			
		} else if (tendingMethod == 1) {
			System.out.println("\n0 : Main menu\n");
			System.out.println(GameEnvironment.waterCrop(farmer, crop));
			goBack();
			mainMenu(farmer);
			
		} else {
			System.out.println("\n0 : Go back\n");
			ArrayList<CropItem> cropItems = farmer.getCropItems();
			ArrayList<CropItem> cropItemsSet = farmer.getCropItemsSet(); // List with no duplicates
			viewCropItems(cropItems, cropItemsSet, true); // Prints the items out 
			int numCropItems = cropItemsSet.size(); // Number of unique items

			int itemChoice = getChoice(0, numCropItems);
			if (itemChoice == 0) {
				chooseCropItem(farmer, crop);
				
			} else {
				CropItem cropItem = cropItemsSet.get(itemChoice-1);
				System.out.println("\n0 : Main menu\n");
				System.out.println(GameEnvironment.tendToCrop(farmer, crop, cropItem));
				goBack();
				mainMenu(farmer);
			}
		}
	}
	
	/**
	 * Asks the player to choose one of their animals to feed
	 * @param farmer The current farmer
	 */
	public static void chooseAnimal(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		System.out.println("Choose an animal to feed...\n");
		ArrayList<Animal> animals = farmer.getFarm().getAnimals(); 
		int numAnimals = animals.size();
		viewAnimals(animals, true, true);
		
		int choice = getChoice(0, numAnimals);
		if (choice == 0) {
			mainMenu(farmer);
			
		} else {
			Animal animal = animals.get(choice-1);
			chooseFoodItem(farmer, animal);
		}
	}
	
	/**
	 * Asks the player to choose a FoodItem to feed their chosen animal with and performs the action
	 * @param farmer The current farmer
	 * @param animal The animal to be fed
	 */
	public static void chooseFoodItem(Farmer farmer, Animal animal) {
		System.out.println("\n0 : Go back\n");
		System.out.println("Feed " + animal.getName() + " with...\n");
		ArrayList<FoodItem> foodItems = farmer.getFoodItems();
		ArrayList<FoodItem> foodItemsSet = farmer.getFoodItemsSet(); // List with no duplicates
		viewFoodItems(foodItems, foodItemsSet, true);
		int numFoodItems = foodItemsSet.size(); // Number of unique items

		int itemChoice = getChoice(0, numFoodItems);
		if (itemChoice == 0) {
			chooseAnimal(farmer);
			
		} else {
			FoodItem foodItem = foodItemsSet.get(itemChoice-1);
			System.out.println("\n0 : Main menu\n");
			System.out.println(GameEnvironment.feedAnimal(farmer, animal, foodItem));
			goBack();
			mainMenu(farmer);
		}
	}
	
	/**
	 * Asks the player to choose an animal to play with and performs the action
	 * @param farmer The current farmer
	 */
	public static void chooseAnimalToPlayWith(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		System.out.println("Choose an animal to play with...\n");
		ArrayList<Animal> animals = farmer.getFarm().getAnimals(); 
		int numAnimals = animals.size();
		viewAnimals(animals, true, true);
		
		int choice = getChoice(0, numAnimals);
		if (choice == 0) {
			mainMenu(farmer);
			
		} else {
			Animal animal = animals.get(choice-1);
			System.out.println("\n0 : Main menu\n");
			System.out.println(GameEnvironment.playWithAnimal(farmer, animal));
			goBack();
			mainMenu(farmer);
		}
	}
	
	/**
	 * Shows all the crops that are ready to harvest and asks the player to choose one to harvest
	 * @param farmer The current farmer
	 */
	public static void chooseCropsToHarvest(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		ArrayList<Crop> ready = farmer.getFarm().findReadyToHarvest();
		if (ready.size() == 0) {
			System.out.println("You don't have any crops ready to harvest!\n");
			goBack();
			mainMenu(farmer);
			
		} else {
			viewCropsToHarvest(ready);
			int numCropsReady = ready.size();
			int choice = getChoice(0, numCropsReady);
			if (choice == 0) {
				mainMenu(farmer);
				
			} else {
				Crop cropToHarvest = ready.get(choice-1);
				System.out.println("\n0 : Main menu\n");
				System.out.println(GameEnvironment.harvestCrop(farmer, cropToHarvest));
				goBack();
				mainMenu(farmer);
			}
		}
	}
	
	/**
	 * Asks the player to confirm if they want to tend to the land and performs the action
	 * @param farmer The current farmer
	 */
	public static void confirmTendToLand(Farmer farmer) {
		System.out.println("\n0 : Main menu\n");
		System.out.println("Are you sure you want to tend to your land?\n"
				+ "1 : Yes\n"
				+ "2 : No");
		
		int choice = getChoice(0, 2);
		if (choice == 0 || choice == 2) {
			mainMenu(farmer);
			
		} else {
			ArrayList<Animal> animals = farmer.getFarm().getAnimals();
			System.out.println("\n0 : Main menu\n");
			System.out.println(GameEnvironment.tendToLand(farmer, animals));
			goBack();
			mainMenu(farmer);
		}
	}
	
	/**
	 * Prints all of the player's options
	 * @param farmer The current farmer
	 */
	public static void mainMenu(Farmer farmer) {
		System.out.println("1 : View farm status\n"
				+ "2 : View money\n"
	    		+ "3 : Visit the county store\n"
	    		+ "4 : Move on to next day\n"
	    		+ "5 : Tend to crops\n"
	    		+ "6 : Feed animals\n"
	    		+ "7 : Play with animals\n"
	    		+ "8 : Harvest crops\n"
	    		+ "9 : Tend to the land");
		
		int choice = getChoice(1, 9);
		
		if (choice == 1) {
			viewFarmStatus(farmer);
		} else if (choice == 2) {
			System.out.println("\n0 : Go back\n");
			System.out.println(farmer.viewMoney());
			goBack();
			mainMenu(farmer);
		} else if (choice == 3) {
			visitCountyStore(farmer);
		} else if (choice == 4) {
			moveToNextDay(farmer);
		} else if (choice == 5) {
			if (farmer.getActionCount() < 2) {
				chooseCrop(farmer);
			} else {
				System.out.println("\n0 : Go back\n");
				System.out.println(GameEnvironment.noActionsLeft(farmer));
				goBack();
				mainMenu(farmer);
			}
		} else if (choice == 6) {
			if (farmer.getActionCount() < 2) {
				chooseAnimal(farmer);
			} else {
				System.out.println("\n0 : Go back\n");
				System.out.println(GameEnvironment.noActionsLeft(farmer));
				goBack();
				mainMenu(farmer);
			}
		} else if (choice == 7) {
			if (farmer.getActionCount() < 2) {
				chooseAnimalToPlayWith(farmer);
			} else {
				System.out.println("\n0 : Go back\n");
				System.out.println(GameEnvironment.noActionsLeft(farmer));
				goBack();
				mainMenu(farmer);
			}
		} else if (choice == 8) {
			if (farmer.getActionCount() < 2) {
				chooseCropsToHarvest(farmer);
			} else {
				System.out.println("\n0 : Go back\n");
				System.out.println(GameEnvironment.noActionsLeft(farmer));
				goBack();
				mainMenu(farmer);
			}
		} else if (choice == 9) {
			if (farmer.getActionCount() < 2) {
				confirmTendToLand(farmer);
			} else {
				System.out.println("\n0 : Go back\n");
				System.out.println(GameEnvironment.noActionsLeft(farmer));
				goBack();
				mainMenu(farmer);
			}
		}
	}
	
	/**
	 * Prints all the crops that are ready to harvest
	 * @param ready The list of crops that are ready to harvest
	 */
	public static void viewCropsToHarvest(ArrayList<Crop> ready) {
		int index = 1;
		for (Crop c: ready) {
			System.out.println(index + " : " + c + "\nTime growing: "
					+ c.getTimeGrowing() + " day(s)\n");
			index++;
		}
	}

	/**
	 * Prints out all of the crop items available for the farm
	 * @param availableCropItems A list of the crop items available to be bought
	 */
	public static void viewAvailableCropItems(ArrayList<CropItem> availableCropItems) {
		int index = 1; 
		for (CropItem c: availableCropItems) {
				System.out.println(index + " : " + c + "\n");
				index++;
		}
	}
	
	/**
	 * Prints out all of the food items available for the farm
	 * @param availableFoodItems A list of the food items available to be bought
	 */
	public static void viewAvailableFoodItems(ArrayList<FoodItem> availableFoodItems) {
		int index = 4; // Because I put all items together in the store
		for (FoodItem f: availableFoodItems) {
				System.out.println(index + " : " + f + "\n");
				index++;
		}
	}
	
	/**
	 * Prints out all of the crops available for the farm
	 * @param availableCrops A list of the crops available to be bought
	 */
	public static void viewAvailableCrops(ArrayList<Crop> availableCrops) {
		int index = 1;
		for (Crop c: availableCrops) {
				System.out.println(index + " : " + c + "\n");
				index++;
		}
	}
	
	/**
	 * Prints out all of the animals available for the farm
	 * @param availableAnimals A list of the animals available to be bought
	 */
	public static void viewAvailableAnimals(ArrayList<Animal> availableAnimals) {
		int index = 1;
		for (Animal a: availableAnimals) {
				System.out.println(index + " : " + a + "\n");
				index++;
		}
	}
	
	/**
	 * Prints out all currently owned crops 
	 * @param crops The list of crops to be shown
	 * @param needsIndexes true if the crops need to be numbered (i.e. when the player needs to choose from them),
	 * 					   false if read-only
	 * @param detailed true if timeGrowing needs to be shown (i.e. in the farm status),
	 * 				   false when it is hidden (i.e. in the store/in the inventory)
	 */
	public static void viewCrops(ArrayList<Crop> crops, boolean needsIndexes, boolean detailed) {
		if (crops.size() == 0) {
			System.out.println("You don't have any crops.\n");
		} else {
			int index = 1;
			String numbering;
			String details;
			for (Crop c: crops) {
				if (needsIndexes) {
					numbering = index + " : ";
				} else {
					numbering = "";
				}
				
				if (detailed) {
					details = "\nTime growing: " + c.getTimeGrowing() + " day(s)\n";	
				} else {
					details = "\n";
				}
				System.out.println(numbering + c + details);
				index++;
			}
		}
	}
	
	/**
	 * Prints out all currently owned animals
	 * @param animals The list of animals to be shown
	 * @param needsIndexes true if the animals need to be numbered (i.e. when the player needs to choose from them),
	 * 					   false if read-only
	 * @param detailed true if healthiness and happiness need to be shown (i.e. in the farm status),
	 * 				   false when it is hidden (i.e. in the store/in the inventory)
	 */
	public static void viewAnimals(ArrayList<Animal> animals, boolean needsIndexes, boolean detailed) {
		if (animals.size() == 0) {
			System.out.println("You don't have any animals.\n");
		} else {
			int index = 1;
			String numbering;
			String details;
			for (Animal a: animals) {
				if (needsIndexes) {
					numbering = index + " : ";
				} else {
					numbering = "";
				}
				if (detailed) {
					details = "\nHealthiness: " + a.getHealth() + " out of 120" +
							  "\nHappiness: " + a.getHappiness() + " out of 10\n";
				} else {
					details = "\n";
				}
				System.out.println(numbering + a + details);
				index++;
			}
		}
	}
	
	/**
	 * Prints out all currently owned crops with amounts
	 * @param cropsList A list of the currently owned crops
	 * @param cropsSet A list of the currently owned crops without duplicates
	 */
	public static void viewCropsInventory(ArrayList<Crop> cropsList, ArrayList<Crop> cropsSet) {
		for (Crop c: cropsSet) {
			System.out.println(c);
			System.out.println("Amount: " + Collections.frequency(cropsList, c) + "\n");
		}
	}
	
	/**
	 * Prints out all currently owned animals with amounts
	 * @param animalsList A list of the currently owned animals
	 * @param animalsSet A list of the currently owned animals without duplicates
	 */
	public static void viewAnimalsInventory(ArrayList<Animal> animalsList, ArrayList<Animal> animalsSet) {
		for (Animal a: animalsSet) {
			System.out.println(a);
			System.out.println("Amount: " + Collections.frequency(animalsList, a) + "\n");
		}
	}
	
	/**
	 * Prints out all owned crop items 
	 * @param cropItems A list of the currently owned crop items
	 * @param cropItemsSet A list of the currently owned crop items without duplicates
	 * @param needsIndexes true if the items need to be numbered (i.e. when the player needs to choose from them),
	 * 					   false if read-only
	 */
	public static void viewCropItems(ArrayList<CropItem> cropItems, ArrayList<CropItem> cropItemsSet, boolean needsIndexes) {
		if (cropItems.size() == 0) {
			System.out.println("You don't have any crop items.\n");
		} else {
			int index = 1;
			String numbering;
			for (CropItem c: cropItemsSet) {
				if (needsIndexes) {
					numbering = index + " : ";
				} else {
					numbering = "";
				}
				System.out.println(numbering + c);
				index++;
				System.out.println("Amount: " + Collections.frequency(cropItems, c) + "\n");
			}
		}
	}
	
	/**
	 * Prints out all owned food items 
	 * @param foodItems A list of the currently owned food items
	 * @param foodItemsSet A list of the currently owned food items without duplicates
	 * @param needsIndexes true if the items need to be numbered (i.e. when the player needs to choose from them),
	 * 					   false if read-only
	 */
	public static void viewFoodItems(ArrayList<FoodItem> foodItems, ArrayList<FoodItem> foodItemsSet, boolean needsIndexes) {
		if (foodItems.size() == 0) {
			System.out.println("You don't have any food items.\n");
		} else {
			int index = 1;
			String numbering;
			for (FoodItem f: foodItemsSet) {
				if (needsIndexes) {
					numbering = index + " : ";
				} else {
					numbering = "";
				}
				System.out.println(numbering + f);
				index++;
				System.out.println("Amount: " + Collections.frequency(foodItems, f) + "\n");
			}
		}
	}

	/**
	 * Show what items the farmer currently owns, their amounts, and the amount of money the Farm has
	 * @param farmer The current farmer
	 */
	public static void viewInventory(Farmer farmer) {
		Farm farm = farmer.getFarm();
		ArrayList<CropItem> cropItems = farmer.getCropItems();
		ArrayList<FoodItem> foodItems = farmer.getFoodItems();
		ArrayList<Crop> crops = farm.getCrops();
		ArrayList<Animal> animals = farm.getAnimals();
		ArrayList<CropItem> cropItemsSet = farmer.getCropItemsSet();
		ArrayList<FoodItem> foodItemsSet = farmer.getFoodItemsSet();
		ArrayList<Crop> cropsSet = farm.getCropsSet();
		ArrayList<Animal> animalsSet = farm.getAnimalsSet();
		
		boolean isEmpty = true;
		
		if (crops.size() > 0) {
			isEmpty = false;
			viewCropsInventory(crops, cropsSet);
		}
		
		if (animals.size() > 0) {
			isEmpty = false;
			viewAnimalsInventory(animals, animalsSet);
		} 
		
		if (cropItems.size() > 0) {
			isEmpty = false;
			viewCropItems(cropItems, cropItemsSet, false);
		}
		
		if (foodItems.size() > 0) {
			isEmpty = false;
			viewFoodItems(foodItems, foodItemsSet, false);
		} 
		
		if (isEmpty) {
			System.out.println("Your inventory is empty!\n");
		}
		
		System.out.println("Available money: $" + farm.getMoney() + "\n");
	}

	public static void main(String[] args) { 
		Farmer farmer = initialiseGame();
	    mainMenu(farmer);
 
	}
}

