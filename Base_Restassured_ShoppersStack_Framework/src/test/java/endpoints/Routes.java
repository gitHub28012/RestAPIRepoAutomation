package endpoints;

public class Routes {
	
	
	// declare the base url!!
	
	public static String baseUrl="https://www.shoppersstack.com/shopping";
	
	//login
	public static String loginUrl=baseUrl+"/users/login";
	
	// view products
	public static String viewProductsUrl=baseUrl+"/products/alpha";
	
	//cart
	public static String addToCartUrl=baseUrl+"/shoppers/{shopperId}/carts";
	public static String updateCartUrl=baseUrl+"/shoppers/{shopperId}/carts/{itemId}";
	
	//address
	public static String addAddressUrl=baseUrl+"/shoppers/{shopperId}/address";
	public static String deleteAddressUrl=baseUrl+"/shoppers/{shopperId}/address/{addressId}";
	
	//order
	public static String placeOrderUrl=baseUrl+"/shoppers/{shopperId}/orders";
	public static String updateOrderStatusUrl=baseUrl+"/shoppers/{shopperId}/orders/{orderId}";
	
	//review
	public static String addReviewUrl=baseUrl+"/reviews";
	public static String deleteReviewUrl=baseUrl+"/reviews/{reviewId}";
	
	

}
