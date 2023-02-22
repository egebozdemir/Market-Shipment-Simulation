package Market.Items;

import Market.*;
import Market.Countability.Countable;

public class Milk extends Item implements Countable {

	private final int UNIT_COST = 5; // TL per liter
	private final int UNIT_PRICE = 11; // TL per liter

	public Milk() {
		super();
	}
	public Milk(double volume, String itemSerialNumber) {
		super(ItemCode.M1, volume, itemSerialNumber);
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