package main;

/**
 * The central class of the GUI, maintaining game state and managing the launching and 
 * closing of screens.
 * 
 * @author Alina Phang, Jesper Slager
 * @version 1.0, May 2020.
 */
public class FarmManager {
	
	/**
	 * The name of the farmer
	 */
	private String farmerName;
	
	/**
	 * The age of the farmer
	 */
	private int farmerAge;
	
	/**
	 * A string representing the type of farm
	 */
	private String farmString;
	
	/**
	 * The name of the farm
	 */
	private String farmName;
	
	/**
	 * The duration of the game
	 */
	private int gameDuration;
	
	/**
	 * The instance of Farmer to be initialised before starting the game
	 */
	private Farmer farmer;
	
	/**
	 * Sets the farmer's name
	 * @param name The farmer's name
	 */
	public void setFarmerName(String name) {
		farmerName = name;
	}
	
	/**
	 * Sets the farmer's age
	 * @param age The farmer's age
	 */
	public void setFarmerAge(int age) {
		farmerAge = age;
	}
	
	/**
	 * Sets the farm type
	 * @param string The type of farm as a string
	 */
	public void setFarmString(String string) {
		farmString = string;
	}
	
	/**
	 * Sets the farm's name
	 * @param name The farm's name
	 */
	public void setFarmName(String name) {
		farmName = name;
	}
	
	/**
	 * Sets the duration of the game
	 * @param duration The duration of the game in days
	 */
	public void setGameDuration(int duration) {
		gameDuration = duration;
	}
	
	/**
	 * Sets the instance of Farmer that was initialised based on player input
	 * @param incomingFarmer the Farmer the player has made
	 */
	public void setFarmer(Farmer incomingFarmer) {
		farmer = incomingFarmer;
	}
	
	/**
	 * @return The farmer's name
	 */
	public String getFarmerName() {
		return farmerName;
	}
	
	/**
	 * @return The farmer's age
	 */
	public int getFarmerAge() {
		return farmerAge;
	}
	
	/**
	 * @return The farm's name
	 */
	public String getFarmName() {
		return farmName;
	}
	
	/**
	 * @return The current Farmer
	 */
	public Farmer getFarmer() {
		return farmer;
	}
	
	/**
	 * @return The farm type as a string
	 */
	public String getFarmString() {
		return farmString;
	}
	
	/**
	 * Initialises and sets the instance of Farmer that will be playing the game
	 */
	public void initialiseGame() {
		Farmer farmer = GameEnvironment.createFarmer(gameDuration, farmerName, farmerAge, farmString, farmName);
		this.setFarmer(farmer);
	}
	
	public void launchStartingScreen() {
		StartingScreen startingWindow = new StartingScreen(this);
	}
	
	public void closeStartingScreen(StartingScreen startingWindow) {
		startingWindow.closeWindow();
		launchSetupScreen();
	}
	
	public void launchSetupScreen() {
		SetupScreen setupWindow = new SetupScreen(this);
	}
	
	public void closeSetupScreen(SetupScreen setupWindow) {
		setupWindow.closeWindow();
		launchWelcomeScreen();
	}
	
	public void launchWelcomeScreen() {
		WelcomeScreen welcomeWindow = new WelcomeScreen(this);
	}
	
	public void closeWelcomeScreen(WelcomeScreen welcomeWindow) {
		welcomeWindow.closeWindow();
		launchMainScreen();
	}

	public void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}
	
	public void closeMainScreen(MainScreen mainWindow) {
		mainWindow.closeWindow();
	}
	
	public void launchCountyStoreScreen() {
		CountyStoreScreen countyStoreWindow = new CountyStoreScreen(this);
	}
	
	/**
	 * Closes the county store screen and sometimes opens the main screen
	 * @param countyStoreWindow The window showing the county store
	 * @param openMainScreen true when closing the county store screen alone, 
	 * 						 false when closing it to open crops/animals/items screens
	 */
	public void closeCountyStoreScreen(CountyStoreScreen countyStoreWindow, boolean openMainScreen) {
		countyStoreWindow.closeWindow();
		if (openMainScreen) {
			launchMainScreen();
		}
	}
	
	public void launchCropsScreen() {
		CropsScreen cropsWindow = new CropsScreen(this);
	}
	
	public void closeCropsScreen(CropsScreen cropsWindow) {
		cropsWindow.closeWindow();
	}
	
	public void launchItemsScreen() {
		ItemsScreen itemsWindow = new ItemsScreen(this);
	}
	
	public void closeItemsScreen(ItemsScreen itemsWindow) {
		itemsWindow.closeWindow();
	}
	
	public void launchAnimalsScreen() {
		AnimalsScreen animalsWindow = new AnimalsScreen(this);
	}
	
	public void closeAnimalsScreen(AnimalsScreen animalsWindow) {
		animalsWindow.closeWindow();
	}
	
	public void launchInventoryScreen() {
		InventoryScreen inventoryWindow = new InventoryScreen(this);
	}
	
	public void closeInventoryScreen(InventoryScreen inventoryWindow) {
		inventoryWindow.closeWindow();
	}
	
	public void launchCropStatusScreen() {
		CropStatusScreen cropStatusWindow = new CropStatusScreen(this);
	}
	
	public void closeCropStatusScreen(CropStatusScreen cropStatusWindow) {
		cropStatusWindow.closeWindow();
	}
	
	public void launchAnimalStatusScreen() {
		AnimalStatusScreen animalStatusWindow = new AnimalStatusScreen(this);
	}
	
	public void closeAnimalStatusScreen(AnimalStatusScreen animalStatusWindow) {
		animalStatusWindow.closeWindow();
	}
	
	public void launchTendCropsScreen() {
		TendCropsScreen tendCropsWindow = new TendCropsScreen(this);
	}
	
	public void closeTendCropsScreen(TendCropsScreen tendCropsWindow) {
		tendCropsWindow.closeWindow();
		launchMainScreen();
	}
	
	public void launchFeedAnimalsScreen() {
		FeedAnimalsScreen feedAnimalsWindow = new FeedAnimalsScreen(this);
	}
	
	public void closeFeedAnimalsScreen(FeedAnimalsScreen feedAnimalsWindow) {
		feedAnimalsWindow.closeWindow();
		launchMainScreen();
	}
	
	public void launchPlayAnimalsScreen() {
		PlayAnimalsScreen playAnimalsWindow = new PlayAnimalsScreen(this);
	}
	
	public void closePlayAnimalsScreen(PlayAnimalsScreen playAnimalsWindow) {
		playAnimalsWindow.closeWindow();
		launchMainScreen();
	}
	
	public void launchHarvestCropsScreen() {
		HarvestCropsScreen harvestCropsWindow = new HarvestCropsScreen(this);
	}
	
	public void closeHarvestCropsScreen(HarvestCropsScreen harvestCropsWindow) {
		harvestCropsWindow.closeWindow();
		launchMainScreen();
	}
	
	public static void main(String[] args) {
		FarmManager manager = new FarmManager();
		manager.launchStartingScreen();
	}
}
