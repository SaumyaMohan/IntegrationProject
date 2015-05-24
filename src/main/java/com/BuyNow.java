package main.java.com;

import java.util.Set;

import org.eclipse.jetty.util.log.Slf4jLog;

import main.java.PayPal.PaypalIntegration;
import main.java.PayPal.SetExpressCheckout;
import spark.Request;
import spark.Response;
import spark.Route;

public class BuyNow implements Route{
	
	public Object handle(Request request, Response response) throws Exception {
		Set<String> params = request.queryParams();
		
		double orangePrice = Double.parseDouble(request.queryParams("orange_price"));
		double applePrice = Double.parseDouble(request.queryParams("apple_price"));
		double bananaPrice = Double.parseDouble(request.queryParams("banana_price"));
		
		int numOranges = params.contains("orange_check") ? Integer.parseInt(request.queryParams("orange_quantity")) : 0;
		int numApples = params.contains("apple_check") ? Integer.parseInt(request.queryParams("apple_quantity")) : 0;
		int numBananas = params.contains("banana_check") ? Integer.parseInt(request.queryParams("banana_quantity")) : 0;
		
		//Create a new shopping cart
		ShoppingCart stuffToBuy = new ShoppingCart();
		
		//Create and add the items to the cart
		if (numOranges > 0){
			ShoppingCartItem oranges = new ShoppingCartItem("Oranges", orangePrice);
			stuffToBuy.addItemToShoppingCart(oranges, numOranges);
		}
		
		if (numApples > 0){
			ShoppingCartItem apples = new ShoppingCartItem("Apples", applePrice);
			stuffToBuy.addItemToShoppingCart(apples, numApples);
		}
		
		if (numBananas > 0 ){
			ShoppingCartItem bananas = new ShoppingCartItem("Bananas", bananaPrice);
			stuffToBuy.addItemToShoppingCart(bananas, numBananas);
		}
		
		String redirectURL = PaypalIntegration.performSetExpressCheckout(stuffToBuy);
		
		if (!redirectURL.equalsIgnoreCase(PaypalIntegration.API_FAILURE)){
			response.redirect(redirectURL);
		}
		
		//Internal error
		response.status(500);
		
		return null;	
	}

}
