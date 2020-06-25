package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;
import main.Crop;
import main.CropItem;

class CropTest {
	Crop potatoes;
	Crop carrots;
	
	@BeforeEach
    void init() {
		potatoes = new Crop("Potatoes", 8, 60, 4);
		carrots = new Crop("Carrots", 7, 21, 1);
    }

	@Test
	void isHarvestableTest() {
		Crop saffron = new Crop("Saffron", 100, 350, 2);
		saffron.updateGrowingTime();
		saffron.updateGrowingTime();
		assertTrue(saffron.isHarvestable());
	}
	
	@Test
	void waterTest() {
		potatoes.water();
		assertEquals(3, potatoes.getTimeTillHarvest());
	}
	
	@Test
	void giveTest() {
		CropItem magicalWater = new CropItem("Magical water", 100, 3);
		carrots.give(magicalWater);
		assertEquals(-2, carrots.getTimeTillHarvest());
	}
	
	@Test
	void equalsTest() {
		Crop saffron1 = new Crop("Saffron", 100, 365, 2);
		Crop saffron2 = new Crop("Saffron", 100, 350, 2);
		Animal pigs = new Animal("Pigs", 40, 8, 64, 20);
		
		assertTrue(saffron1.equals(saffron2));
		assertFalse(pigs.equals(saffron1));
	}
	
}
