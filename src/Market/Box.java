package Market;

import java.util.ArrayList;

public abstract class Box {

	protected BoxCode boxCode;
	protected double maxVolume;
	protected double currentVolume;
	protected String boxSerialNumber;
	protected ArrayList<Item> nonAddedItems;
	protected boolean loadedToContainer;

	public Box() {
		this(null, 0, "nullSerialNumber");
	}

	public Box(BoxCode boxCode, double maxVolume, String boxSerialNumber) {
		this.boxCode = boxCode;
		this.maxVolume = maxVolume;
		this.boxSerialNumber = boxSerialNumber;
		this.currentVolume = 0;
		this.nonAddedItems = new ArrayList<>();
		this.loadedToContainer = false;
	}

	// Getters
	public BoxCode getBoxCode() {
		return boxCode;
	}

	public double getMaxVolume() {
		return maxVolume;
	}

	public double getCurrentVolume() {
		return currentVolume;
	}

	public String getBoxSerialNumber() {
		return boxSerialNumber;
	}

	public boolean isLoadedToContainer() {
		return loadedToContainer;
	}

	public void loadToContainer(){
		this.loadedToContainer = true;
	}

	public ArrayList<Item> getNonAddedItems() {
		return nonAddedItems;
	}

	// Abstract methods
	// Inserts item to box
	public abstract boolean insert(Item item);

	// Calculates prices of items inside box
	public abstract double calcPrice();

	// Calculates prices of items inside unshipped box
	public abstract double calcUnshippedRevenue();
}