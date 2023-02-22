package Market.Items;

import Market.*;
import Market.Countability.Uncountable;

public class Flour extends Item implements Uncountable {

	private final int UNIT_COST = 5; // TL per kg
	private final int UNIT_PRICE = 12; // TL per liter

	public Flour() {
		super();
	}
	public Flour(int mass, double volume, String itemSerialNumber) {
		super(ItemCode.F1, mass, volume, itemSerialNumber);
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