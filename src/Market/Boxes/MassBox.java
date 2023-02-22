package Market.Boxes;

import java.util.HashMap;
import java.util.Map;
import Exceptions.*;
import Market.*;
import Market.Countability.*;

public class MassBox extends Box {
	private final int UNIT_COST = 3;
	private Map<String, Item> massBox;
	private int maxMass;
	private int currentMass;
	private double cost;
	public MassBox(int maxMass, double volume, String boxSerialNumber) {
		super(BoxCode.B2, volume, boxSerialNumber);
		this.massBox = new HashMap<>();
		this.maxMass = maxMass;
		this.currentMass = 0;
		this.cost = volume*UNIT_COST;
	}

	public int getMaxMass() {
		return maxMass;
	}

	public int getCurrentMass() {
		return currentMass;
	}

	@Override
	public boolean insert(Item item) {
		try {
			if (item == null) {
				return false;
			} else if (item instanceof Countable) {
				throw new WrongBoxException();
			} else if (loadedToContainer) {
				throw new BoxLoadedToContainerException();
			} else if (item instanceof Uncountable) {
				if (item.isItemBoxed()) {
					throw new ItemBoxedException();
				} else if (massBox.containsKey(item.getSerialNumber())) {
					throw new SerialNumberExistenceException();
				} else if ((currentMass + ((Uncountable) item).getMass()) > maxMass) {
					throw new MassOverflowException();
				} else if ((currentVolume + item.getVolume()) > maxVolume) {
					throw new VolumeOverflowException();
				} else {
					massBox.put(item.getSerialNumber(), item);
					item.itemBoxed();
					currentMass += ((Uncountable) item).getMass();
					currentVolume += item.getVolume();
					System.out.println(String.format("Item %s has been placed to the box %s" , item.getSerialNumber(), boxSerialNumber));
					return true;
				}
			}
		}catch (ItemBoxedException e){
			nonAddedItems.add(item);
			System.out.println("Item with serial number " + item.getSerialNumber() + " has already boxed (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}catch (SerialNumberExistenceException e) {
			nonAddedItems.add(item);
			System.out.println("Item with serial number " + item.getSerialNumber() + " has already exist (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch (WrongBoxException e) {
			nonAddedItems.add(item);
			System.out.println("Item " + item.getSerialNumber() + " can't be added due wrong type of box(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch(BoxLoadedToContainerException e) {
			nonAddedItems.add(item);
			System.out.println("Item " + item.getSerialNumber() + " can't be added because box is loaded to container(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch (MassOverflowException e) {
			nonAddedItems.add(item);
			System.out.println("Item " + item.getSerialNumber() + " can't be added due mass overflow(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		} catch (VolumeOverflowException e) {
			nonAddedItems.add(item);
			System.out.println("Item " + item.getSerialNumber() + " can't be added due volume overflow(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
		}
		return false;
	}

	public double getCost() {
		return cost;
	}

	public double calcPrice(){
		double price = 0;
		for (Item item: massBox.values()){
			price += item.getPrice();
		}
		return price;
	}

	public double calcUnshippedRevenue(){
		double unshippedRevenue = 0;
		for (Item item: massBox.values()) {
			unshippedRevenue += item.getRevenue();
		}
		return unshippedRevenue;
	}

	@Override
	public String toString() {
		return massBox.toString();
	}
}
