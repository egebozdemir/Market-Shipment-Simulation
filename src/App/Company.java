package App;

import Market.*;
import java.util.HashMap;
import java.util.Map;
import Exceptions.SerialNumberExistenceException;

public class Company {
	private double revenue;
	private Map<String, Item> producedItems;
	private Map<String, Box> producedBoxes;
	private Map<String, Container> producedContainers;

	// Constructor
	public Company() {
		this.revenue = 0;
		this.producedItems = new HashMap<>();
		this.producedBoxes = new HashMap<>();
		this.producedContainers = new HashMap<>();
	}

	// Add methods items, boxes or containers to hashmap
	protected boolean add(Item item){
		try {
			if (item != null){
				if (producedItems.containsKey(item.getSerialNumber())) {
					throw new SerialNumberExistenceException();
				} else {
					producedItems.put(item.getSerialNumber(), item);
					return true;
				}
			}
		} catch (SerialNumberExistenceException e) {
			System.out.println("Item with serial number " + item.getSerialNumber() + " has already exist (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	protected boolean add(Box box){
		try {
			if (box != null){
				if (producedBoxes.containsKey(box.getBoxSerialNumber())) {
					throw new SerialNumberExistenceException();
				} else {
					producedBoxes.put(box.getBoxSerialNumber(), box);
					return true;
				}
			}
		} catch (SerialNumberExistenceException e) {
			System.out.println("Box with serial number " + box.getBoxSerialNumber() + " has already exist (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	protected boolean add(Container container){
		try {
			if (container != null){
				if (producedContainers.containsKey(container.getSerialNumber())) {
					throw new SerialNumberExistenceException();
				} else {
					producedContainers.put(container.getSerialNumber(), container);
					return true;
				}
			}
		} catch (SerialNumberExistenceException e) {
			System.out.println("Container with serial number " + container.getSerialNumber() + " has already exist (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	// To find items, boxes or containers from their serial numbers
	protected Item getItem(String serialNumber){
		if (serialNumber == null) {
			return null;
		} else {
			if (producedItems.containsKey(serialNumber)) {
				return producedItems.get(serialNumber);
			}
		}
		return null;
	}

	protected Box getBox(String serialNumber) {
		if (serialNumber == null) {
			return null;
		} else {
			if (producedBoxes.containsKey(serialNumber)) {
				return producedBoxes.get(serialNumber);
			}
		}
		return null;
	}

	protected Container getContainer( String serialNumber ){
		if (serialNumber == null) {
			return null;
		} else {
			if (producedContainers.containsKey(serialNumber)) {
				return producedContainers.get(serialNumber);
			}
		}
		return null;
	}

	// Method for calculating unshipped items revenue
	protected double calcUnshippedRevenue(){
		double unshippedRevenue = 0;
		for (Item item: producedItems.values()) {
			if(!item.isItemBoxed()) {
				unshippedRevenue += item.getRevenue();
			}
		}
		for (Box box : producedBoxes.values()){
			if(!box.isLoadedToContainer()){
				unshippedRevenue += box.calcUnshippedRevenue();
			}
		}
		for (Container container : producedContainers.values()){
			if(!container.isShipped()){
				unshippedRevenue += container.calcUnshippedRevenue();
			}
		}
		return unshippedRevenue;
	}

	// To subtract cost of item, box or container from our revenue
	public void addCost(double cost){
		this.revenue -= cost;
	}
	// To add price of item, box or container to our revenue
	public void addPrice(double price){
		this.revenue += price;
	}

	// Getters
	public double getRevenue() {
		return revenue;
	}
}