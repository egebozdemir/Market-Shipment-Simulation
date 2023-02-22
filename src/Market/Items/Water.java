package Market.Items;

import Market.*;
import Market.Countability.Countable;

public class Water extends Item implements Countable {
	
	private final int UNIT_COST = 1; // TL per liter
	private final int UNIT_PRICE = 3; // TL per liter
	
	public Water() {
		super();
	}
	public Water(double volume, String itemSerialNumber) {
		super(ItemCode.W1, volume, itemSerialNumber);
	}
	@Override
	protected void setCost() {
		this.cost = UNIT_COST * this.volume;
		
	}
	@Override
	protected void setPrice() {
		this.price = UNIT_PRICE * this.volume;
	}
}