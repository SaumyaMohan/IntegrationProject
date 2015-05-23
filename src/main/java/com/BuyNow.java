package main.java.com;

import java.util.Set;

import spark.Request;
import spark.Response;
import spark.Route;

public class BuyNow implements Route{
	private static final double ORANGES_PRICE = 5.00;
	private static final double APPLES_PRICE = 6.00;
	private static final double BANANA_PRICE = 4.00;

	public Object handle(Request request, Response response) throws Exception {
		Set<String> params = request.queryParams();
		
		
		/*
		 *  RESTORE THIS
		int numOranges = Integer.parseInt(request.params("orange_quantity"));
		int numApples = Integer.parseInt(request.params("apple_quantity"));
		int numBananas = Integer.parseInt(request.params("bananas_quantity"));
		*/
		
		//TEMP
		int numOranges = 1;
		int numApples = 1;
		int numBananas = 1;
		
		//Create a new shopping cart
		ShoppingCart stuffToBuy = new ShoppingCart();
		
		//Create and add the items to the cart
		if (numOranges >=1){
			ShoppingCartItem oranges = new ShoppingCartItem("Oranges", ORANGES_PRICE);
			stuffToBuy.addItemToShoppingCart(oranges, numOranges);
		}
		
		if (numApples>=1){
			ShoppingCartItem apples = new ShoppingCartItem("Apples", APPLES_PRICE);
			stuffToBuy.addItemToShoppingCart(apples, numApples);
		}
		
		if (numBananas >=1){
			ShoppingCartItem bananas = new ShoppingCartItem("Bananas", BANANA_PRICE);
			stuffToBuy.addItemToShoppingCart(bananas, numBananas);
		}
		
		return stuffToBuy.toString();
		
		//return "Parameters: " + params;
	}

}
