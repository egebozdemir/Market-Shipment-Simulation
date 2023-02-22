package Market.Items;

import Market.*;
import Market.Countability.Countable;

public class Oil extends Item implements Countable {

	private final int UNIT_COST = 20; // TL per liter
	private final int UNIT_PRICE = 45; // TL per liter

	public Oil() {
		super();
	}
	public Oil(double volume, String itemSerialNumber) {
		super(ItemCode.O1, volume, itemSerialNumber);
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