package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;
import main.Crop;
import main.CropItem;
import main.Farm;
import main.FoodItem;
import main.PlantationFarm;

class FarmTest {
	Farm farm;
	
	@BeforeEach
    void init() {
		farm = new PlantationFarm("Test farm", "Alina");
    }
	
	@Test
	void initialiseTest() {
		assertEquals("Test farm", farm.getName());
		assertEquals("Alina", farm.getFarmer());
	}
	
	@Test
	void addCropTest() {
		farm.setMaxNumCrops(1);
		Crop rice = new Crop("Rice", 3, 31, 3);
		farm.addCrop(rice);
		farm.addCrop(rice);
		farm.addCrop(rice);
		
		// Should only be able to buy 1 crop
		assertEquals(1, farm.getCrops().size());
	}
	
	@Test
	void addAnimalTest() {
		Animal chickens = new Animal("Chickens", 30, 8, 10, 6);
		farm.addAnimal(chickens);
		farm.addAnimal(chickens);
		
		assertEquals(2, farm.getAnimals().size());
	}
	
	@Test
	void getAvailableCropItemsTest() {
		ArrayList<CropItem> availableCropItems = farm.getAvailableCropItems();
		
		assertEquals(3, availableCropItems.size());
	}
	
	@Test
	void getAvailableFoodItemsTest() {
		ArrayList<FoodItem> availableFoodItems = farm.getAvailableFoodItems();
		
		assertEquals(3, availableFoodItems.size());
	}
	
	@Test
	void getAvailableCropsTest() {
		ArrayList<Crop> availableCrops= farm.getAvailableCrops();
		
		assertEquals(6, availableCrops.size());
	}
	
	@Test
	void getAvailableAnimalsTest() {
		ArrayList<Animal> availableAnimals = farm.getAvailableAnimals();
		
		assertEquals(3, availableAnimals.size());
	}
	
	@Test
	void getCropsSetTest() {
		Crop barley = new Crop("Barley", 12, 50, 2);
		Crop potatoes = new Crop("Potatoes", 3, 31, 3);
		Crop rice = new Crop("Rice", 3, 31, 3);
		farm.getCrops().add(barley);
		farm.getCrops().add(barley);
		farm.getCrops().add(rice);
		farm.getCrops().add(rice);
		farm.getCrops().add(rice);
		farm.getCrops().add(potatoes);
		
		ArrayList<Crop> cropsSet = farm.getCropsSet();
		
		assertEquals(3, cropsSet.size());
	}
	
	@Test
	void getAnimalsSetTest() {
		Animal chickens = new Animal("Chickens", 30, 8, 10, 6);
		farm.getAnimals().add(chickens);
		farm.getAnimals().add(chickens);
		farm.getAnimals().add(chickens);
		farm.getAnimals().add(chickens);
		
		ArrayList<Animal> animalsSet = farm.getAnimalsSet();
		
		assertEquals(1, animalsSet.size());
	}
	
	@Test
	void findReadyToHarvestTest() {
		Crop corn = new Crop("Corn", 12, 50, 2);
		Crop rice = new Crop("Rice", 3, 31, 3);
		Crop wheat = new Crop("Wheat", 3, 31, 3);
		corn.updateGrowingTime();
		corn.updateGrowingTime();
		farm.getCrops().add(rice);
		farm.getCrops().add(corn);
		farm.getCrops().add(wheat);
		
 		ArrayList<Crop> ready = farm.findReadyToHarvest();
 		
 		rice.updateGrowingTime();
 		rice.updateGrowingTime();
 		rice.updateGrowingTime();
 		
 		ArrayList<Crop> ready1 = farm.findReadyToHarvest();
 		
		assertEquals(1, ready.size());
		assertEquals(2, ready1.size());
	}
	
	@Test
	void harvestTest() {
		Crop corn = new Crop("Corn", 12, 50, 2);
		farm.addCrop(corn);
		farm.harvest(corn);
		
		assertEquals(300, farm.getMoney());
		assertEquals(0, farm.getCrops().size());
	}
	
	@Test
	void loseCropTest() {
		Crop corn = new Crop("Corn", 12, 50, 2);
		farm.addCrop(corn);
		farm.loseCrop(0);
		
		assertEquals(0, farm.getCrops().size());
	}
	
	@Test
	void loseAnimalTest() {
		Animal chickens = new Animal("Chickens", 30, 8, 10, 6);
		Animal pigs = new Animal("Pigs", 50, 8, 80, 11);
		farm.addAnimal(chickens);
		farm.addAnimal(pigs);
		farm.addAnimal(chickens);
		farm.loseAnimal(2);
		
		assertEquals(2, farm.getAnimals().size());
	}
}
