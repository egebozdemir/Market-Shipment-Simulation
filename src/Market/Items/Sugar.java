package Market.Items;

import Market.*;
import Market.Countability.Uncountable;

public class Sugar extends Item implements Uncountable {

	private final int UNIT_COST = 13; // TL per kilogram
	private final int UNIT_PRICE = 25; // TL per liter

	public Sugar() {
		super();
	}
	public Sugar(int mass, double volume, String itemSerialNumber) {
		super(ItemCode.S1, mass, volume, itemSerialNumber);
	}

	@Override
	public int getMass() {
		return mass;
	}

	@Override
	protected void setCost() {
		this.cost = UNIT_COST * this.mass;

	}
	@Override
	protected void setPrice() {
		this.price = UNIT_PRICE * this.volume;

	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", mass=" + mass + ", volume=" + volume + ", itemSerialNumber=" + itemSerialNumber
				+ ", cost=" + cost + ", price=" + price + ", revenue=" + String.format("%.2f", revenue) + "]";
	}
}