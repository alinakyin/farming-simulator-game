package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Animal;
import main.Crop;
import main.FoodItem;

class AnimalTest {
	Animal cows;
	Animal chickens;
	Animal pigs;
	
	@BeforeEach
    void init() {
		cows = new Animal("Cows", 100, 9, 75, 18); 
		chickens = new Animal("Chickens", 30, 8, 10, 6);
		pigs = new Animal("Pigs", 50, 8, 80, 11);
    }

	@Test
	void getBonusTest() {
		int pigBonus = pigs.getBonus();
		int chickenBonus = chickens.getBonus();
		
		assertEquals(48, pigBonus);
		assertEquals(13, chickenBonus);
	}

	@Test
	void getDailyIncomeTest() {
		int pigsIncome = pigs.getDailyIncome();
		int cowsIncome = cows.getDailyIncome();
		
		assertEquals(59, pigsIncome);
		assertEquals(64, cowsIncome);
	}
	
	@Test
	void feedTest() {
		FoodItem hay = new FoodItem("Hay", 100, 50);
		FoodItem apples = new FoodItem("Apples", 40, 20);
		cows.feed(hay);
		chickens.feed(apples);
		
		assertEquals(120, cows.getHealth());
		assertEquals(30, chickens.getHealth());
	}
	
	@Test
	void loseHealthTest() {
		chickens.loseHealth();
		pigs.loseHealth();
		assertEquals(0, chickens.getHealth());
		assertEquals(68, pigs.getHealth());
	}
	
	@Test
	void playTest() {
		chickens.play();
		
		assertEquals(10, chickens.getHappiness());
	}
	
	@Test
	void happinessBonusTest() {
		cows.happinessBonus();
		cows.happinessBonus();
		
		assertEquals(10, cows.getHappiness());
	}
	
	@Test
	void loseHappinessTest() {
		chickens = new Animal("Chickens", 30, 0, 10, 6);
		pigs.loseHappiness();
		chickens.loseHappiness();
		
		assertEquals(7, pigs.getHappiness());
		assertEquals(0, chickens.getHappiness());
	}
	
	@Test
	void getDepressionTest() {
		cows = new Animal("Cows", 100, 2, 75, 18); 
		chickens.getDepression();
		cows.getDepression();
		
		assertEquals(5, chickens.getHappiness());
		assertEquals(0, cows.getHappiness());
	}
	
	@Test
	void equalsTest() {
		Animal cows1 = new Animal("Cows", 100, 2, 85, 18); 
		Animal cows2 = new Animal("Cows", 100, 2, 90, 18); 
		Crop potatoes = new Crop("Potatoes", 7, 21, 3);
		
		assertTrue(cows1.equals(cows2));
		assertFalse(potatoes.equals(cows1));
	}

}
