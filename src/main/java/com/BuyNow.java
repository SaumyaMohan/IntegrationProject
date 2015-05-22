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
		int numOranges = Integer.parseInt(request.params("orange_quantity"));
		int numApples = Integer.parseInt(request.params("apple_quantity"));
		int numBananas = Integer.parseInt(request.params("bananas_quantity"));		
		return "Parameters: " + params;
	}

}
