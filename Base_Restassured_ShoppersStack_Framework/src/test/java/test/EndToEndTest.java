package test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.AddToCartEndPoints;
import endpoints.AddressEndPoints;
import endpoints.LoginEndPoints;
import endpoints.OrderEndPoints;
import endpoints.ReviewEndPoints;
import endpoints.ViewProductsEndPoints;
import io.restassured.response.Response;
import requestPayload.AddressPayload;
import requestPayload.CartPayload;
import requestPayload.LoginPayload;
import requestPayload.OrderAddressPayload;
import requestPayload.OrderPayload;
import requestPayload.ReviewPayload;

public class EndToEndTest {

	static int shopperId;
	static String token;
	static int productId;
	static int itemId;
	static int addressId;
	static int orderId;
	static int reviewId;

	@Test(priority = 1)
	void testShopperLogin() {

		LoginPayload loginPayload = new LoginPayload();
		loginPayload.setEmail("jadhavt630@gmail.com");
		loginPayload.setPassword("Tanmay@123");
		loginPayload.setRole("SHOPPER");

		Response responseBody = LoginEndPoints.shopperLogin(loginPayload);

		responseBody.then().assertThat().statusCode(200).log().body();
		shopperId = responseBody.jsonPath().get("data.userId");
		token = responseBody.jsonPath().get("data.jwtToken");

	}

	@Test(priority = 2)
	void testViewProducts()
	{
		
		Response responseBody = ViewProductsEndPoints.viewProducts();
		productId=responseBody.jsonPath().get("data[0].productId");
		System.out.println(productId);
		Assert.assertEquals(responseBody.getStatusCode(),200);
		
	}
	
	@Test(priority = 3)
	void testAddtoCartProducts()
	{
		CartPayload cartpayload = new CartPayload();
		cartpayload.setProductId(productId);
		cartpayload.setQuantity(1);
		
		Response responseBoay = AddToCartEndPoints.addToCart(token,shopperId,cartpayload);
		responseBoay.then().log().all();
		itemId=responseBoay.jsonPath().get("data.itemId");
		
		Assert.assertEquals(responseBoay.getStatusCode(),201);
	}
	
	@Test(priority = 4)
	void testUpdateCartProducts()
	{
		CartPayload cartpayload = new CartPayload();
		cartpayload.setProductId(productId);
		cartpayload.setQuantity(3);
		
		Response responseBody = AddToCartEndPoints.updateFromCart(token,shopperId, itemId,cartpayload);
		Assert.assertEquals(responseBody.getStatusCode(),200);
	}
	
	@Test(priority = 5)
	void testAddAddress()
	{
		AddressPayload addressPayload = new AddressPayload();
		addressPayload.setAddressId(0);
		addressPayload.setBuildingInfo("Gopalan coworks");
		addressPayload.setCity("Banglore");
		addressPayload.setCountry("India");
		addressPayload.setLandmark("Opposite Iplante");
		addressPayload.setName("Tanmay Jadhav");
		addressPayload.setPhone("987456123");
		addressPayload.setPincode("560010");
		addressPayload.setState("Karnataka");
		addressPayload.setStreetInfo("Katriguupe main road");
		addressPayload.setType("Office");
		
		Response responseBody = AddressEndPoints.addAddress(token, shopperId,addressPayload);
		addressId=responseBody.jsonPath().get("data.addressId");
		Assert.assertEquals(responseBody.getStatusCode(),201);		
	}
	
	
	@Test(priority = 6)
	void testPlaceAnOrder()
	{
        OrderAddressPayload orderAddressPayload = new OrderAddressPayload();
        orderAddressPayload.setAddressId(addressId);
        
        OrderPayload orderPayload = new OrderPayload();
        orderPayload.setOap(orderAddressPayload);
        orderPayload.setPaymentMode("COD");
     
	    Response responseBody = OrderEndPoints.placeOrder(token, shopperId, orderPayload);
		responseBody.then().log().all();
		orderId=responseBody.jsonPath().get("data.orderId");
		Assert.assertEquals(responseBody.getStatusCode(),201);
	}
	

  @Test(priority = 7) void testUpdateAnOrder() { Response responseBody =
  OrderEndPoints.updateOrder(token, shopperId, orderId,"DELIVERED");
  Assert.assertEquals(responseBody.getStatusCode(),200);
  
  }
  
  @Test(priority = 8) void testAddReview()
  {
  ReviewPayload reviewPayload = new ReviewPayload();
  
  reviewPayload.setDescription("One of the best display");
  reviewPayload.setHeading("Nice Product!!"); reviewPayload.setRating(4.2);
  reviewPayload.setShopperId(shopperId);
  reviewPayload.setShopperName("Tanmay Jadhav");
  
  Response responseBody = ReviewEndPoints.addReview(token, productId,reviewPayload); 
 
  reviewId=responseBody.jsonPath().get("data.reviewId");
  
  Assert.assertEquals(responseBody.getStatusCode(),200);
  
  }
  
  
  @Test(priority = 9) void deleteAddress() 
  { 
	  
  Response responseBody =
  AddressEndPoints.deleteAddress(token, shopperId, addressId);
  Assert.assertEquals(responseBody.getStatusCode(),204);
  
  }
 
  @Test(priority = 10) void testDeleteReview() { Response responseBody =
  ReviewEndPoints.deleteReview(token, productId, reviewId);
  
  Assert.assertEquals(responseBody.getStatusCode(),200); }
 }
