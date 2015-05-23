package main.java.com;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

	private double totalCost;
	private Map<ShoppingCartItem, Integer> items;

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	public void clearShoppingCart(){
		this.items = null;
	}
	
	public void addItemToShoppingCart(ShoppingCartItem item, int quantity)
	{
		if (item == null || quantity==0){
			return;
		}
		
		if (this.items == null){
			this.items = new HashMap<ShoppingCartItem, Integer>();
		}

		//Add them to the actual cart
		this.items.put(item, quantity);
		
		//Recompute the total
		this.totalCost += (item.getPrice() * quantity);
	}

	@Override
	public String toString(){
		StringBuilder desc = new StringBuilder();
		
		for (ShoppingCartItem item: this.items.keySet()){
			Integer quantity = items.get(item);
			desc.append(item.toString() + " * " + quantity.toString() + " = " + quantity*item.getPrice() + "\n");
		}
		desc.append("\n Total price = "+ this.totalCost);
		return desc.toString();
	}
}
