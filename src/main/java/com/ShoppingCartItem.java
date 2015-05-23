package main.java.com;

public class ShoppingCartItem {

	private String description;
	private double price;

	public ShoppingCartItem(String description, double price){
		this.description = description;
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString(){
		return description + " @ "  + price;
	}
}
