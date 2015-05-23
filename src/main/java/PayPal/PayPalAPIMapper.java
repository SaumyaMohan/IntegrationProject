package main.java.PayPal;

import java.util.ArrayList;
import java.util.List;

import main.java.com.ShoppingCart;
import main.java.com.ShoppingCartItem;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.CountryCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.ErrorType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SellerDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

public class PayPalAPIMapper {

	public static SetExpressCheckoutReq createSetExpressCheckoutRequest(ShoppingCart cart)
	{			
		// ## SetExpressCheckoutReq
		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();

		// Completion URL
		setExpressCheckoutRequestDetails
				.setReturnURL("http://localhost/return");

		// Cancel URL
		setExpressCheckoutRequestDetails
				.setCancelURL("http://localhost/cancel");

		// list of information about the payment
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();

		//Iterate through the cart and add the items
		for (ShoppingCartItem cartItem: cart.getShoppingCartItems().keySet())
		{
			int itemQuantity = cart.getShoppingCartItems().get(cartItem);
		
			PaymentDetailsType paymentDetails = new PaymentDetailsType();

			//Set item total
			BasicAmountType orderTotal1 = new BasicAmountType(CurrencyCodeType.USD,
					String.valueOf(cartItem.getPrice() * itemQuantity));
			paymentDetails.setOrderTotal(orderTotal1);
			paymentDetails.setPaymentAction(PaymentActionCodeType.SALE);
			
			addSellerInfoToPaymentDetails(paymentDetails);
			addShippingAddressInfoToPaymentDetails(paymentDetails);
			paymentDetails.setPaymentRequestID(cartItem.getDescription());
			paymentDetailsList.add(paymentDetails);
		}
		
		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);

		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(
				setExpressCheckoutRequestDetails);

		setExpressCheckoutReq
				.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

		return setExpressCheckoutReq;
	}
	
	public static String getTokenFromSetExpressCheckoutResponse(SetExpressCheckoutResponseType setExpressCheckoutResponse)
	{
		String token = null;
		
		if (setExpressCheckoutResponse.getAck().getValue()
				.equalsIgnoreCase("success")) {
			token = setExpressCheckoutResponse.getToken();
		}
		else {
			token = PaypalIntegration.API_FAILURE;
		}
		
		return token;
	}
	
	private static void addSellerInfoToPaymentDetails(PaymentDetailsType paymentDetails){
		// Seller info
		SellerDetailsType sellerDetails1 = new SellerDetailsType();
		sellerDetails1.setPayPalAccountID("saumyamohan.20-facilitator@gmail.com");
		paymentDetails.setSellerDetails(sellerDetails1);
		paymentDetails.setPaymentRequestID("PaymentRequest1");
	}
	
	private static void addShippingAddressInfoToPaymentDetails(PaymentDetailsType paymentDetails){
		// `Address` 
		AddressType shipToAddress1 = new AddressType();
		shipToAddress1.setStreet1("Ape Way");
		shipToAddress1.setCityName("Austin");
		shipToAddress1.setStateOrProvince("TX");
		shipToAddress1.setCountry(CountryCodeType.US);
		shipToAddress1.setPostalCode("78750");
		paymentDetails.setShipToAddress(shipToAddress1);
	}
}
