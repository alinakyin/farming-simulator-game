package main;

/**
 * The superclass Produce of our Farm Simulator. 
 * It has two descendants: Crop and Animal. Produce are purchased to generate revenue for the farm.
 *
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class Produce {
	
	/**
	 * The type of produce
	 */
	private String name;
	
	/**
	 * The purchase price of the produce
	 */
	private int purchasePrice;
	
	/**
	 * Constructs an instance of Produce
	 * @param produceType The name/type of the produce
	 * @param price The purchase price of the produce
	 */
	public Produce(String produceType, int price) {
		name = produceType;
		purchasePrice = price;
	}
	
	/**
	 * @return The type of produce
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The purchase price of the produce
	 */
	public int getPrice() {
		return purchasePrice;
	}
}


