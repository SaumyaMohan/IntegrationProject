package main.java.PayPal;

import main.java.com.ShoppingCart;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;

public class PaypalIntegration {

	public static final String API_FAILURE = "API Failure";
	public static final String SANDBOX_SET_EXPRESS_CHECKOUT_REDIRECT_URL = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
	
	public static String performSetExpressCheckout(ShoppingCart cart){
		SetExpressCheckout setExpressCheckoutObject = new SetExpressCheckout();
		SetExpressCheckoutReq setExpressCheckoutReq = PayPalAPIMapper.createSetExpressCheckoutRequest(cart);
		
		//Call the actual service
		SetExpressCheckoutResponseType setExpressCheckoutResponse = setExpressCheckoutObject.setExpressCheckout(setExpressCheckoutReq) ;
		
		String responseToken = PayPalAPIMapper.getTokenFromSetExpressCheckoutResponse(setExpressCheckoutResponse);
		
		if (!responseToken.equalsIgnoreCase(API_FAILURE)){
			//Construct redirect URL
			return SANDBOX_SET_EXPRESS_CHECKOUT_REDIRECT_URL + responseToken;
		}
		
		return responseToken;
	}
}
