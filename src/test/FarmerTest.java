package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Animal;
import main.Crop;
import main.CropItem;
import main.Farm;
import main.Farmer;
import main.FoodItem;
import main.PlantationFarm;

import org.junit.jupiter.api.BeforeEach;

class FarmerTest {
	Farm farm;
	Farmer farmer;
	
	@BeforeEach
    void init() {
		farm = new PlantationFarm("Test farm", "Alina");
		farmer = new Farmer("Alina", 20, farm);
    }
	
	@Test
	void initialiseTest() {
		assertEquals(farmer.getName(), "Alina");
		assertEquals(farmer.getAge(), 20);
		assertEquals(farmer.getFarm().getName(), "Test farm");
	}

	@Test
	void actionCountTest() {
		farmer.increaseActionCount();
		farmer.increaseActionCount();
		farmer.resetActionCount();
		assertEquals(0, farmer.getActionCount());
	}
	
	@Test
	void getCropsItemsSetTest() {
		CropItem pesticides = new CropItem("Pesticides", 60, 3);
		CropItem fertilizer = new CropItem("Fertilizer", 40, 2);
		CropItem love = new CropItem("Love", 100, 4);
		farmer.getCropItems().add(pesticides);
		farmer.getCropItems().add(fertilizer);
		farmer.getCropItems().add(love);
		farmer.getCropItems().add(love);
		farmer.getCropItems().add(love);
		
		ArrayList<CropItem> cropItemsSet = farmer.getCropItemsSet();
		assertEquals(3, cropItemsSet.size());
	}
	
	@Test
	void getFoodItemsSetTest() {
		FoodItem seeds = new FoodItem("Seeds", 60, 25); 
		farmer.getFoodItems().add(seeds);
		farmer.getFoodItems().add(seeds);
		farmer.getFoodItems().add(seeds);
	
		ArrayList<FoodItem> foodItemsSet = farmer.getFoodItemsSet();
		assertEquals(1, foodItemsSet.size());
	}
	
	@Test
	void giveToTest() {
		Crop wheat = new Crop("Wheat", 5, 30, 2);
		CropItem pesticides = new CropItem("Pesticides", 60, 2);
		farmer.getCropItems().add(pesticides);
		int numCropItems = farmer.getCropItems().size();
		
		farmer.giveTo(wheat, pesticides);
		
		assertEquals(numCropItems-1, farmer.getCropItems().size());
		assertEquals(0, wheat.getTimeTillHarvest());
	}
	
	@Test
	void giveWaterToTest() {
		Crop saffron = new Crop("Saffron", 100, 350, 5);
		farmer.giveWaterTo(saffron);
		assertEquals(4, saffron.getTimeTillHarvest());
	}
	
	@Test
	void feedToTest() {
		Animal pigs = new Animal("Pigs", 50, 8, 80, 11);
		FoodItem apples = new FoodItem("Apples", 60, 25); 
		farmer.getFoodItems().add(apples);
		int numFoodItems = farmer.getFoodItems().size();
		
		farmer.feedTo(pigs, apples);
		
		assertEquals(numFoodItems-1, farmer.getFoodItems().size());
		assertEquals(105, pigs.getHealth());
	}
	
	@Test
	void playWithTest() {
		Animal chickens = new Animal("Chickens", 30, 8, 10, 6);	
		farmer.playWith(chickens);
		
		assertEquals(10, chickens.getHappiness());
	}
}
