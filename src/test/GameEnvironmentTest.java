package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Farmer;
import main.Farm;
import main.FoodItem;
import main.Animal;
import main.Crop;
import main.CropItem;
import main.GameEnvironment;

/**
 * Tests for the GameEnvironment class. Mainly tests that the logic of the game is working, for example, that the farmer's 
 * action count is increasing at the right times. The actions themselves have been tested in the class they belong to, 
 * for example, feeding animals is in the FarmerTest class.
 *
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
class GameEnvironmentTest {
	Farm farm;
	Farmer farmer;
	CropItem fertilizer;
	CropItem pesticides;
	FoodItem apples;
	FoodItem worms;
	Crop wheat;
	Crop sugarcane;
	Crop corn;
	Animal cows;
	Animal pigs;
	Animal chickens;
	
	@BeforeEach
	void  init() {
		farmer = GameEnvironment.createFarmer(5, "TestDummy", 20, "Plantation Farm", "Test farm");
		farm = farmer.getFarm();
	}

	@Test
	void emptyInventoryTest() {
		assertEquals(0, farmer.getFoodItems().size());
		assertEquals(0, farmer.getCropItems().size());
	}
	
	@Test
	void getFinalScoreTest() {
		// With starting money of $250
		assertEquals(625, GameEnvironment.getFinalScore(farmer, 5)); 
	}
	
	@Test
	void getFinalScore1Test() {
		pigs = new Animal("Pigs", 50, 8, 80, 11);
		int bonus = pigs.getDailyIncome();
		farm.addAnimal(pigs);
		double score = (250 + (bonus * 2)) * 2.5; // Add bonus for happiness >= 8 and 
		
		assertEquals((int)score, GameEnvironment.getFinalScore(farmer, 5)); 
	}
	
	@Test
	void getFinalScore2Test() {
		wheat = new Crop("Wheat", 5, 30, 3);
		cows = new Animal("Cows", 100, 3, 75, 32); 
		pesticides = new CropItem("Pesticides", 20, 2);
		apples = new FoodItem("Apples", 60, 25);
		farm.addCrop(wheat);
		farm.addAnimal(cows);
		farmer.getCropItems().add(pesticides);
		farmer.getFoodItems().add(apples);
		int bonus = cows.getDailyIncome();
		double score = (250 + bonus + (30 / 6) + (20 / 2) + (60 / 4)) * 4.5;
		
		assertEquals((int)score, GameEnvironment.getFinalScore(farmer, 9)); 
	}
	
	@Test
	void buyCropItemTest() {
		fertilizer = new CropItem("Fertilizer", 20, 2);
		String s = GameEnvironment.buyCropItem(farmer, fertilizer);
		assertEquals(1, farmer.getCropItems().size());
		
		// Should be too expensive
		pesticides = new CropItem("Pesticides", 300, 3);
		String s1 = GameEnvironment.buyCropItem(farmer, pesticides);
		assertEquals(1, farmer.getCropItems().size());
	}
	
	@Test
	void buyFoodItemTest() {
		apples = new FoodItem("Apples", 60, 25);
		String s = GameEnvironment.buyFoodItem(farmer, apples);
		assertEquals(1, farmer.getFoodItems().size());
		
		// Should be too expensive
		worms = new FoodItem("Worms", 300, 35);
		String s1 = GameEnvironment.buyFoodItem(farmer, worms);
		assertEquals(1, farmer.getFoodItems().size());
	}

	@Test
	void buyCropTest() {
		wheat = new Crop("Wheat", 5, 30, 4);	
		String s = GameEnvironment.buyCrop(farm, wheat);
		assertEquals(1, farm.getCrops().size());
		
		// Should be too expensive
		sugarcane = new Crop("Sugarcane", 300, 450, 6);
		String s1 = GameEnvironment.buyCrop(farm, sugarcane);
		assertEquals(1, farm.getCrops().size());
	}
	
	@Test
	void buyAnimalTest() {
		cows = new Animal("Cows", 100, 3, 75, 32); 
		String s = GameEnvironment.buyAnimal(farm, cows);
		assertEquals(1, farm.getAnimals().size());
		
		// Should be too expensive
		pigs = new Animal("Pigs", 250, 460, 80, 20);
		String s1 = GameEnvironment.buyAnimal(farm, pigs);
		assertEquals(1, farm.getAnimals().size());
	}
	
	@Test
	void moveDayForwardTest() {
		cows = new Animal("Cows", 100, 3, 75, 32); 
		farm.addAnimal(cows);
		int initialDay = GameEnvironment.getCurrentDay();
		int initialMoney = farm.getMoney();
		int bonus = cows.getDailyIncome();
		
		String s = GameEnvironment.moveDayForward(farmer);

		// Should increase currentDay, deposit money, and reset actionCount
		assertEquals(initialDay+1, GameEnvironment.getCurrentDay());
		assertEquals(0, farmer.getActionCount());
		assertEquals(initialMoney+bonus, farm.getMoney());
	}
	
	@Test
	void waterCropTest() {
		wheat = new Crop("Wheat", 5, 30, 4);
		int initialActionCount = farmer.getActionCount();
		String s = GameEnvironment.waterCrop(farmer, wheat);

		// Should increase actionCount 
		assertEquals(initialActionCount+1, farmer.getActionCount());
	}
	
	@Test
	void tendToCropTest() {
		wheat = new Crop("Wheat", 5, 30, 4);
		pesticides = new CropItem("Pesticides", 20, 2);
		int initialActionCount = farmer.getActionCount();
		String s = GameEnvironment.tendToCrop(farmer, wheat, pesticides);

		// Should increase actionCount 
		assertEquals(initialActionCount+1, farmer.getActionCount());
	}
	
	@Test
	void feedAnimalTest() {
		int initialActionCount = farmer.getActionCount();
		cows = new Animal("Dairy cows", 120, 6, 100, 45);
		apples = new FoodItem("Apples", 60, 25);
		String s = GameEnvironment.feedAnimal(farmer, cows, apples);
		
		// Should increase actionCount
		assertEquals(initialActionCount+1, farmer.getActionCount());
	}
	
	@Test
	void playWithAnimalTest() {
		int initialActionCount = farmer.getActionCount();
		cows = new Animal("Dairy cows", 120, 6, 100, 45);
		String s = GameEnvironment.playWithAnimal(farmer, cows);
		
		// Should increase actionCount 
		assertEquals(initialActionCount+1, farmer.getActionCount());
	}
	
	@Test
	void harvestCropTest() {
		wheat = new Crop("Wheat", 5, 30, 4);
		int initialActionCount = farmer.getActionCount();
		String s = GameEnvironment.harvestCrop(farmer, wheat);

		// Should increase actionCount 
		assertEquals(initialActionCount+1, farmer.getActionCount());
	}
	
	@Test
	void tendToLandTest() {
		int initialActionCount = farmer.getActionCount();
		int initialMaxNumCrops = farm.getMaxNumCrops();
		cows = new Animal("Dairy cows", 120, 6, 100, 45);
		farm.getAnimals().add(cows);

		String s = GameEnvironment.tendToLand(farmer, farm.getAnimals());
		
		// Should increase actionCount, maxNumCrops 
		assertEquals(initialActionCount+1, farmer.getActionCount());
		assertEquals(initialMaxNumCrops+1, farm.getMaxNumCrops());
	}
	
	@Test
	void brokenFenceTest() {
		pigs = new Animal("Pigs", 50, 6, 80, 45);
		chickens = new Animal("Chickens", 30, 6, 60, 18);
		farm.addAnimal(pigs); 
		farm.addAnimal(chickens);
		GameEnvironment.brokenFence(farmer, farm.getAnimals());
		
		// Should have lost at least one animal
		assertNotEquals(2, farm.getAnimals().size());
	}
	
	@Test
	void brokenFence1Test() {
		pigs = new Animal("Pigs", 50, 6, 80, 45);
		farm.addAnimal(pigs); 
		GameEnvironment.brokenFence(farmer, farm.getAnimals());
		
		// Should have lost your only animal
		assertEquals(0, farm.getAnimals().size());
	}
	
	@Test
	void droughtTest() {
		sugarcane = new Crop("Sugarcane", 120, 210, 6);
		wheat = new Crop("Wheat", 5, 30, 4);
		
		farm.addCrop(sugarcane);
		farm.addCrop(wheat);
		farm.addCrop(sugarcane);
		farm.addCrop(sugarcane);
		farm.addCrop(wheat);
		farm.addCrop(sugarcane);

		GameEnvironment.drought(farmer, farm.getCrops());
		
		// Should lose 3 crops
		assertEquals(3, farm.getCrops().size());	
	}
	
	@Test
	void drought1Test() {
		corn = new Crop("Corn", 3, 25, 2);
		wheat = new Crop("Wheat", 5, 30, 4);
		
		farm.addCrop(corn);
		farm.addCrop(corn);
		farm.addCrop(wheat);

		GameEnvironment.drought(farmer, farm.getCrops());
		
		// Should lose 1 crop
		assertEquals(2, farm.getCrops().size());	
	}
	
	@Test
	void countyFairTest() { 
		farm.setMoney(0);
		
		// No crops or animals test, should win $75
		GameEnvironment.countyFair(farmer, farm.getCrops(), farm.getAnimals());
		assertEquals(75, farm.getMoney());
	}
	
	@Test
	void countyFair1Test() {  
		pigs = new Animal("Pigs", 50, 6, 80, 45);
		
		farm.setMoney(0);
		farm.addAnimal(pigs);
		
		GameEnvironment.countyFair(farmer, farm.getCrops(), farm.getAnimals());
		assertEquals(125, farm.getMoney());
	}
	
	@Test
	void countyFair2Test() {
		pigs = new Animal("Pigs", 50, 6, 80, 45);
		
		farm.setMoney(0);
		farm.addAnimal(pigs);
		farm.addAnimal(pigs);
		farm.addAnimal(pigs);
		
		GameEnvironment.countyFair(farmer, farm.getCrops(), farm.getAnimals());
		assertEquals(225, farm.getMoney());
	}
	
	@Test
	void countyFair3Test() {
		pigs = new Animal("Pigs", 50, 6, 80, 45);
		cows = new Animal("Dairy cows", 120, 6, 100, 45);
		corn = new Crop("Corn", 3, 25, 2);
		wheat = new Crop("Wheat", 5, 30, 4);
		
		farm.setMoney(0);
		farm.addAnimal(pigs);
		farm.addAnimal(pigs);
		farm.addAnimal(cows);
		farm.addCrop(corn);
		farm.addCrop(wheat);
		
		GameEnvironment.countyFair(farmer, farm.getCrops(), farm.getAnimals());
		assertEquals(275, farm.getMoney());
	}

	@Test
	void generateRandomEventTest() {
		// Statistically a random event should occur within 20 times
		String s;
		boolean occurred = false;
		for (int i=0; i<20; i++) {
			s = GameEnvironment.generateRandomEvent(farmer);
			if (s != null) {
				occurred = true;
				break;
			}
		}
		assertEquals(true, occurred);
	}
}
