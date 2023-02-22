package Market;

import Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Container {

	private final int UNIT_COST = 1;
	private double maxVolume;
	private double currentVolume;
	private double cost;
	private boolean isShipped;
	private String serialNumber;
	private ArrayList<Box> nonAddedBoxes;
	private ContainerCode containerCode;
	private Map<String, Box> container;

	// Constructor
	public Container(double maxVolume, String serialNumber) {
		this.containerCode = ContainerCode.C1;
		this.maxVolume = maxVolume;
		this.serialNumber = serialNumber;
		this.currentVolume=0;
		this.isShipped = false;
		this.nonAddedBoxes = new ArrayList<>();
		this.container = new HashMap<>();
		this.cost = maxVolume*UNIT_COST;
	}

	// Getters
	public double getCost() {
		return cost;
	}

	public ContainerCode getContainerCode() {
		return containerCode;
	}

	public double getMaxVolume() {
		return maxVolume;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public double getCurrentVolume() {
		return currentVolume;
	}

	public ArrayList<Box> getNonAddedBoxes() {
		return nonAddedBoxes;
	}

	public boolean isShipped() {
		return isShipped;
	}

	// Methods
	// Place item to container. Just for exception
	public boolean place(Item item){
		try{
			throw new ItemsPlacedToContainerException();
		} catch (ItemsPlacedToContainerException e) {
			System.out.println("Item can't be added to container.(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	// Place box to container
	public boolean place(Box box) {
		try {
			if (box == null) {
				return false;
			}
			else if (isShipped) {
				throw new ContainerShippedException();
			}
			else if (!box.loadedToContainer) {
				if (container.containsKey(box.getBoxSerialNumber())) {
					throw new SerialNumberExistenceException();
				} else if ((currentVolume + box.getCurrentVolume()) > maxVolume) {
					throw new VolumeOverflowException();
				} else {
					container.put(box.getBoxSerialNumber(), box);
					currentVolume += box.getCurrentVolume();
					box.loadToContainer();
					System.out.println(String.format("Box %s has been placed to the container %s" , box.getBoxSerialNumber(), containerCode));
					return true;
				}
			}
		} catch (SerialNumberExistenceException e) {
			nonAddedBoxes.add(box);
			System.out.println("Box with serial number " + box.getBoxSerialNumber() + " has already exist (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch (ContainerShippedException e) {
			nonAddedBoxes.add(box);
			System.out.println("Box can't be placed to container because box container is shipped.(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch (VolumeOverflowException e) {
			nonAddedBoxes.add(box);
			System.out.println("Box can't be added due volume overflow(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	// To calculate unshipped revenue
	public int calcUnshippedRevenue(){
		int unshippedRevenue = 0;
		for (Box box : container.values()) {
			unshippedRevenue += box.calcUnshippedRevenue();
		}
		return unshippedRevenue;
	}

	// Calculates boxes price. (Means items inside of boxes).
	public double calcPrice(){
		double price = 0;
		for (Box box: container.values()){
			price += box.calcPrice(); // <-- By this
		}
		return price;
	}

	// When this method is called isShipped variable changes to true
	public void ship(){
		this.isShipped = true;
	}

	@Override
	public String toString() {
		return container.toString();
	}
}