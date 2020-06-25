package main;

/**
 * Extends the Produce class to implement Animals, which are raised on the farm.
 * It includes happiness and healthiness attributes, as well as dailyIncome, which is the amount of money earned 
 * from tending to the animal each day.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class Animal extends Produce {
	
	/**
	 * The happiness of the animal 
	 */
	private int happiness;

	/**
	 * The healthiness of the animal 
	 */
	private int health;
	
	/**
	 * The starting healthiness of the animal (used for calculating the total score)
	 */
	private int initialHealth;


	/**
	 * The money earned from tending to the animal each day
	 */
	private int income;
	
	/** Constructs an instance of Animal
	 * @param name The type of animal
	 * @param purchasePrice The cost to buy this animal
	 * @param animalHappiness The happiness of the animal (out of 10)
	 * @param animalHealth The health of the animal (out of 120)
	 * @param revenue The daily income received from tending to the animal
	 */
	public Animal(String name, int purchasePrice, int animalHappiness, int animalHealth, int revenue) {
		super(name, purchasePrice);
		happiness = animalHappiness;
		health = animalHealth;
		income = revenue;
		initialHealth = animalHealth;
	}
	
	/**
	 * @return The happiness of the animal
	 */
	public int getHappiness() {
		return happiness;
	}
	
	/**
	 * @return The health of the animal
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @return The initial health of the animal
	 */
	public int getInitialHealth() {
		return initialHealth;
	}
	
	
	/**
	 * @return The daily money bonus based on the animal's happiness and healthiness levels
	 */
	public int getBonus() { 
		return (happiness + (health / 2));
	}
	
	/**
	 * @return The daily income provided by the animal based on happiness and healthiness
	 */
	public int getDailyIncome() {
		int i = income;
		return i + this.getBonus();
	}
	
	/**
	 * @return The income provided by the animal 
	 */
	public int getIncome() {
		return income;
	}
	
	/**
	 * Gets only the name and daily income of the animal (for inventory)
	 * @return A string containing the animal's name and daily income provided
	 */
	public String basicDetails() {
		return ("Animal name: " + this.getName() + 
				"\nDaily income: $" + this.getDailyIncome());
	}
	
	/**
	 * Gets the important information of the animal (for store)
	 * @return A string containing the animal's name, purchase price and daily income provided
	 */
	public String toString() {
		return ("Animal name: " + this.getName() + 
				"\nPurchase price: $" + this.getPrice() + 
				"\nDaily income: $" + this.getDailyIncome());
	}
	
	/**
	 * Gets the details of the animal including happiness and healthiness (for farm status)
	 * @return A string containing the animal's name, purchase price, daily income provided, healthiness and happiness
	 */
	public String toDetailedString() {
		return ("Animal name: " + this.getName() + 
				"\nDaily income: $" + this.getDailyIncome() + 
				"\nHealthiness: " + this.getHealth() + " out of 120" +
				"\nHappiness: " + this.getHappiness() + " out of 10\n");
	}
	
	/**
	 * Increases the animal's healthiness by the amount given by the food item, up to a maximum of 120
	 * @param f The food item being used
	 */
	public void feed(FoodItem f) {
		if ((health + f.getHealthGiven()) <= 120) {
			health += f.getHealthGiven();
		} else {
			health = 120;
		}
	}
	
	/**
	 * Decreases the animal's health by 12 points, down to a minimum of 0 (used when moving to the next day)
	 */
	public void loseHealth() {
		if ((health - 12) >= 0) {
			health -= 12;
		} else {
			health = 0;
		}
	}
	
	/**
	 * Increases the animal's happiness by 3 points, up to a maximum of 10
	 */
	public void play() {
		if ((happiness + 3) <= 10) {
			happiness += 3;
		} else {
			happiness = 10;
		}
	}
	
	/**
	 * Increases the animal's happiness by 1 point, up to a maximum of 10
	 */
	public void happinessBonus() {
		if ((happiness + 1) <= 10) {
			happiness += 1;
		} else {
			happiness = 10;
		}
	}
	
	/**
	 * Decreases the animal's happiness by 1 point, down to a minimum of 0 (used when moving to the next day)
	 */
	public void loseHappiness() {
		if ((happiness - 1) >= 0) {
			happiness -= 1;
		} else {
			happiness = 0;
		}
	}
	
	/**
	 * Decreases the animal's happiness by 3 points, down to a minimum of 0 (used in the broken fence random event)
	 */
	
	public void getDepression() {
		if ((happiness - 3) >= 0) {
			happiness -= 3;
		} else {
			happiness = 0;
		}
	}
	
	/**
	 * Two instances of Animal are the same type if they have the same name, price and daily income
	 * @param o An object to compare
	 * @return true if the animals are the same type, false otherwise
	 */
	public boolean equals(Object o) { 
		Animal s;
        if(!(o instanceof Animal)) { 
           return false; 
       } else { 
           s = (Animal)o; 
           if (this.getName().equals(s.getName()) && this.getPrice() == s.getPrice() && this.getIncome() == s.getIncome()) { 
               return true; 
           } 
       } 
       return false; 
	 } 
}


	
	
	
	
	

