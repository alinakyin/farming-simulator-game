package main;

/**
 * Extends the Produce class to implement Crops, which are grown on the farm.
 * It includes a timeTillHarvest attribute, which is the number of days it takes to be fully grown and ready to harvest,
 * as well as timeGrowing, which is the number of days since it began growing. When timeTillHarvest is greater than
 * timeGrowing, the Crop can be harvested for an amount of money specified by the salePrice attribute.
 *
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class Crop extends Produce {

	/**
	 * The selling price of the crop
	 */
	private int salePrice;
	
	/**
	 * The time it takes for the crop to be fully grown (modified by tending to the Crop)
	 */
	private int timeTillHarvest;

	/**
	 * The amount of time the crop has been growing
	 */
	private int timeGrowing; 
	
	/**
	 * A boolean which marks whether the crop is ready to harvest or not
	 */
	private boolean harvestable;

	/**
	 * Constructs an instance of Crop
	 * @param name The type of crop
	 * @param purchasePrice The cost of purchasing the crop
	 * @param value The selling price of the crop
	 * @param time The amount of time it takes for the crop to be fully grown
	 */
	public Crop(String name, int purchasePrice, int value, int time) {
		super(name, purchasePrice);
		salePrice = value;
		timeTillHarvest = time;
		timeGrowing = 0;
		harvestable = false;
	}

	/**
	 * @return The time it takes for the crop to be fully grown
	 */
	public int getTimeTillHarvest() {
		return timeTillHarvest;
	}
	
	/**
	 * @return The time the crop has been growing
	 */
	public int getTimeGrowing() {
		return timeGrowing;
	}
	
	/**
	 * Increases the time the crop has been growing (used when moving to the next day)
	 */
	public void updateGrowingTime() {
		timeGrowing++;
	}
	
	/**
	 * @return The selling price of the crop
	 */
	public int getSalePrice() {
		return salePrice;
	}
	
	/**
	 * @return true if the crop is ready to harvest, false otherwise
	 */
	public boolean isHarvestable() {
		if (timeGrowing >= timeTillHarvest) {
			harvestable = true;
		}
		
		return harvestable;
	}
	
	/**
	 * Gets only the name, selling price and time till harvest of the crop (for inventory)
	 * @return A string containing the crop name, selling price and time it takes to be fully grown
	 */
	public String basicDetails() {
		return ("Crop name: " + this.getName() + 
				"\nSelling price: $" + this.getSalePrice() +
				"\nTime until fully grown: " + this.getTimeTillHarvest() + " day(s)");
	}
	
	/**
	 * Gets the important information of the crop (for store)
	 * @return A string containing the crop name, purchase price, selling price and time it takes to be fully grown
	 */
	public String toString() {
		return ("Crop name: " + this.getName() + 
				"\nPurchase price: $" + this.getPrice() + 
				"\nSelling price: $" + this.getSalePrice() +
				"\nTime until fully grown: " + this.getTimeTillHarvest() + " day(s)");
	}
	
	/**
	 * Gets the details of the crop including time growing (for farm status)
	 * @return A string containing the crop name, selling price, time it takes to be fully grown and time growing
	 */
	public String toDetailedString() {
		return ("Crop name: " + this.getName() + 
				"\nSelling price: $" + this.getSalePrice() +
				"\nTime until fully grown: " + this.getTimeTillHarvest() + " day(s)" +
				"\nTime growing: " + this.getTimeGrowing() + " day(s)\n");
	}
	
	/**
	 * Decreases the time the crop needs to be fully grown by 1 day
	 */
	public void water() {
		timeTillHarvest--;
	}
	
	/**
	 * Decreases the time the crop needs to be fully grown by the bonus given by the crop item
	 * @param item The crop item being used
	 */
	public void give(CropItem item) {
		timeTillHarvest -= item.getHarvestTimeBonus();
	}
	
	/**
	 * Two instances of Crop are the same type if they have the same name, price and time till harvest
	 * @param o An object to compare
	 * @return true if the crops are the same type, false otherwise
	 */
	public boolean equals(Object o) { 
		Crop s;
        if(!(o instanceof Crop)) { 
           return false; 
       } else { 
           s = (Crop)o; 
           if (this.getName().equals(s.getName()) && this.getPrice() == s.getPrice() 
        		   && this.timeTillHarvest == s.getTimeTillHarvest()) { 
               return true; 
           } 
       } 
       return false; 
	 } 
}


