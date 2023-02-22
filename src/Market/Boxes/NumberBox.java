package Market.Boxes;

import java.util.HashMap;
import java.util.Map;
import Exceptions.*;
import Market.*;
import Market.Countability.*;

public class NumberBox extends Box {
    private final int UNIT_COST = 2;
    private Map<String, Item> numberBox;
    private int maxNumberOfItems;
    private int NumberOfItems;
    private double cost;

    public NumberBox(int maxNumberOfItems, double volume, String boxSerialNumber) {
        super(BoxCode.B1, volume, boxSerialNumber);
        this.numberBox = new HashMap<>();
        this.maxNumberOfItems = maxNumberOfItems;
        this.NumberOfItems = 0;
        this.cost = volume * UNIT_COST;
    }

    public int getmaxNumberOfItems() {
        return maxNumberOfItems;
    }

    public int getCurrentNumberOfItems() {
        return NumberOfItems;
    }

    @Override
    public boolean insert(Item item) {
        try {
            if (item == null) {
                return false;
            }
            else if (item instanceof Uncountable) {
                throw new WrongBoxException();
            }
            else if (loadedToContainer) {
                throw new BoxLoadedToContainerException();
            }
            else if (item instanceof Countable) {
                if (item.isItemBoxed()) {
                    throw new ItemBoxedException();
                } else if (numberBox.containsKey(item.getSerialNumber())) {
                    throw new SerialNumberExistenceException();
                }
                else if ((NumberOfItems + 1) > maxNumberOfItems) {
                    throw new NumberOfItemsOverflowException();
                }
                else if ((currentVolume + item.getVolume()) > maxVolume) {
                    throw new VolumeOverflowException();
                }
                else {
                    numberBox.put(item.getSerialNumber(), item);
                    item.itemBoxed();
                    NumberOfItems += 1;
                    currentVolume += item.getVolume();
                    System.out.println(String.format("Item %s has been placed to the box %s" , item.getSerialNumber(), boxSerialNumber));
                    return true;
                }
            }
        } catch (ItemBoxedException e){
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
        } catch (NumberOfItemsOverflowException e) {
            nonAddedItems.add(item);
            System.out.println("Item " + item.getSerialNumber() + " can't be added due number of item overflow(EX: " + e.exceptionCode + " " + e.getMessage() + ")");
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
        for (Item item: numberBox.values()){
            price += item.getPrice();
        }
        return price;
    }

    public double calcUnshippedRevenue(){
        double unshippedRevenue = 0;
        for (Item item: numberBox.values()) {
            unshippedRevenue += item.getRevenue();
        }
        return unshippedRevenue;
    }

    @Override
    public String toString() {
        return numberBox.toString();
    }
}
