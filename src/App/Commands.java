package App;

import Market.*;
import Market.ContainerCode;
import Market.Items.*;
import Market.Boxes.*;
import Exceptions.ContainerShippedException;
import java.util.StringTokenizer;

public class Commands {

	private Company company;

	// Constructor
	public Commands(Company c) {
		this.company = c;
	}

	// Command switch
	public void runCommand(StringTokenizer tokenizer){
		switch(Integer.parseInt(tokenizer.nextToken())) {
		case 1: //Produce Something.
			String code = tokenizer.nextToken();
			// Trying code for items
			try {
				switch (ItemCode.valueOf(code)) {
				case M1:
					Milk milk = new Milk(Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(milk)) {
						company.addCost(milk.getCost());
						System.out.println(String.format("%.2f liter of box of milk has been produced with the serial number %s Revenue: %.2f₺", milk.getVolume() , milk.getSerialNumber(),company.getRevenue()));
					}
					break;
				case W1:
					Water water = new Water(Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(water)) {
						company.addCost(water.getCost());
						System.out.println(String.format("%.2f liter of box of water has been produced with the serial number %s Revenue: %.2f₺", water.getVolume() , water.getSerialNumber(),company.getRevenue()));
					}
					break;
				case O1:
					Oil oil = new Oil(Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(oil)) {
						company.addCost(oil.getCost());
						System.out.println(String.format("%.2f liter of box of oil has been produced with the serial number %s Revenue: %.2f₺", oil.getVolume() , oil.getSerialNumber(),company.getRevenue()));
					}
					break;
				case S1:
					Sugar sugar = new Sugar(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(sugar)) {
						company.addCost(sugar.getCost());
						System.out.println(String.format("%d kg sugar has been produced with the serial number %s Revenue: %.2f₺", sugar.getMass() , sugar.getSerialNumber(),company.getRevenue()));
					}
					break;
				case F1:
					Flour flour = new Flour(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(flour)) {
						company.addCost(flour.getCost());
						System.out.println(String.format("%d kg flour has been produced with the serial number %s Revenue: %.2f₺", flour.getMass() , flour.getSerialNumber(),company.getRevenue()));
					}
					break;
				case P1:
					Pasta pasta = new Pasta(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(pasta)) {
						company.addCost(pasta.getCost());
						System.out.println(String.format("%d kg pasta has been produced with the serial number %s Revenue: %.2f₺", pasta.getMass() , pasta.getSerialNumber(),company.getRevenue()));
					}
					break;
				case R1:
					Rice rice = new Rice(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(rice)) {
						company.addCost(rice.getCost());
						System.out.println(String.format("%d kg rice has been produced with the serial number %s Revenue: %.2f₺", rice.getMass() , rice.getSerialNumber(),company.getRevenue()));
					}
					break;
				}
			} catch (Exception e) {
				// Nothing to do
			}
			// If not match with items then trying with boxes
			try {
				switch (BoxCode.valueOf(code)){
				case B1:
					NumberBox numberBox = new NumberBox(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(numberBox)) {
						company.addCost(numberBox.getCost());
						System.out.println(String.format("%.2f liters of number box has been produced with capacity of %d with the serial number %s Revenue: %.2f₺", numberBox.getMaxVolume(), numberBox.getmaxNumberOfItems(), numberBox.getBoxSerialNumber(), company.getRevenue()));
					}
					break;
				case B2:
					MassBox massBox = new MassBox(Integer.parseInt(tokenizer.nextToken()),Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
					if (company.add(massBox)) {
						company.addCost(massBox.getCost());
						System.out.println(String.format("%.2f liters of mass box has been produced with capacity of %d kg with the serial number %s Revenue: %.2f₺", massBox.getMaxVolume(), massBox.getMaxMass(), massBox.getBoxSerialNumber(), company.getRevenue()));
					}
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// Nothing to do
			}
			// If not box then trying with conainers
			try {
				switch (ContainerCode.valueOf(code)){
				case C1:
					Container container = new Container(Double.parseDouble(tokenizer.nextToken()),tokenizer.nextToken());
					if (company.add(container)) {
						company.addCost(container.getCost());
						System.out.println(String.format("%.2f liters of container has been produced with the serial number %s Revenue: %.2f₺", container.getMaxVolume(), container.getSerialNumber(), company.getRevenue()));
					}
					break;
				default:
				}
			} catch (Exception e) {
				// Nothing to do
			}
			break;
		case 2: // Load something
			String serialNumberOfWillBeAdded = tokenizer.nextToken(); 
			String serialNumberOfAddedTo = tokenizer.nextToken();
			// To add items to boxes
			try {
				Item item = company.getItem(serialNumberOfWillBeAdded);
				Box box = company.getBox(serialNumberOfAddedTo);
				if(item != null && box !=null) {
					box.insert(item);
				}
				else {
					throw new Exception();
				}
			} catch (Exception e) {
				//Nothing to do
			}
			// To add boxes to containers
			try {
				Box box = company.getBox(serialNumberOfWillBeAdded);
				Container container = company.getContainer(serialNumberOfAddedTo);
				if(box != null && container != null){
					container.place(box);

				}
				else{
					throw new Exception();
				}
			} catch (Exception e) {
				//Nothing to do
			}
			/* To add items to containers. But it will throw exception eventually in container class
                because we can't add items to containers */
			try {
				Item item = company.getItem(serialNumberOfWillBeAdded);
				Container container = company.getContainer(serialNumberOfAddedTo);
				if(item != null && container != null){
					container.place(item);
				}
				else{
					throw new Exception();
				}
			} catch (Exception e) {
				//Nothing to do
			}
			break;
		case 3: // To shipping
			String serialNumberOfContainer = tokenizer.nextToken();
			// Ships container with given serial number
			try {
				Container container = company.getContainer(serialNumberOfContainer);
				if(container != null){
					if (container.isShipped() == true){
						throw new ContainerShippedException();
					}else{
						company.addPrice(container.calcPrice());
						container.ship();
						System.out.println(String.format("Container %s has been shipped  Revenue: %.2f₺", container.getSerialNumber(), company.getRevenue()));
					}
				}
				else {
					throw new Exception();
				}
			} catch (ContainerShippedException e) {
				System.out.println("Container with serial number " + serialNumberOfContainer + " has already shipped. (" + "EX: " + e.exceptionCode + " " + e.getMessage() + ")");
			} catch (Exception e) {
				//Nothing to do
			}
			break;
		case 4: // Show...
			switch (Integer.parseInt(tokenizer.nextToken())){
			case 1: // ...total unearned revenue
				System.out.println(String.format("Unearned revenue: %.2f₺", company.calcUnshippedRevenue()));
				break;
			case 2: // ...total revenue
				System.out.println(String.format("Total revenue: %.2f₺", company.getRevenue()));
				break;
			}
			break;
		}
	}
}