package Market;

public abstract class Item {

	protected int mass;
	protected double volume;
	protected double cost;
	protected double price;
	protected double revenue = price - cost;
	protected boolean itemBoxed;
	protected ItemCode itemCode;
	protected String itemSerialNumber;

	// Constructors
	public Item() {
		this(null, 0, "nullSerialNumber");
	}

	public Item(ItemCode itemCode, double volume, String itemSerialNumber) {
		this.itemCode = itemCode;
		this.volume = volume;
		this.itemSerialNumber = itemSerialNumber;
		this.itemBoxed = false;
		setCost();
		setPrice();
		setRevenue();
	}

	public Item(ItemCode itemCode, int mass, double volume, String itemSerialNumber) {
		this.itemCode = itemCode;
		this.mass = mass;
		this.volume = volume;
		this.itemSerialNumber = itemSerialNumber;
		setCost();
		setPrice();
		setRevenue();
	}

	// Getters
	public double getVolume() {
		return volume;
	}
	public double getCost() {
		return cost;
	}
	public double getPrice() {
		return price;
	}
	public double getRevenue() {
		return revenue;
	}
	public boolean isItemBoxed() {
		return itemBoxed;
	}
	public String getSerialNumber() {
		return itemSerialNumber;
	}
	public ItemCode getItemCode() {
		return itemCode;
	}

	// Methods
	// Calculates cost of the item by using unit cost
	protected abstract void setCost();

	// Calculates price of the item by using unit price
	protected abstract void setPrice();

	// Calculates revenue of the item
	protected void setRevenue() {
		this.revenue = this.price - this.cost;
	}

	// When item boxed changes variable itemBoxed to true
	public void itemBoxed(){
		this.itemBoxed = true;
	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", volume=" + volume + ", itemSerialNumber=" + itemSerialNumber
				+ ", cost=" + cost + ", price=" + price + ", revenue=" + String.format("%.2f", revenue) + "]";
	}	
}