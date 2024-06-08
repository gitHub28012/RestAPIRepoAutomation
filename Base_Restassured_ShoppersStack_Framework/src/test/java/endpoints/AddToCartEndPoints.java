package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestPayload.CartPayload;

public class AddToCartEndPoints {
	
	
	public static Response addToCart(String token,int shopperId,CartPayload payload)
	{
	
		return RestAssured.given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).pathParam("shopperId",shopperId).body(payload)
		.when().post(Routes.addToCartUrl);
	}
	
	public static Response updateFromCart(String token,int shopperId,int itemId,CartPayload payload)
	{
		return RestAssured.given().log().all().contentType(ContentType.JSON).header("Authorization", "Bearer " + token).pathParam("shopperId",shopperId).pathParam("itemId",itemId).body(payload)
	    .when().put(Routes.updateCartUrl);			
	}

}
