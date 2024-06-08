package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestPayload.AddressPayload;

public class AddressEndPoints {
	
	
	public static Response addAddress(String token,int shopperId,AddressPayload payload)
	{
		 return RestAssured.given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).pathParam("shopperId",shopperId).body(payload).log().all()
		.when().post(Routes.addAddressUrl);		
	}
	
	public static Response deleteAddress(String token,int shopperId,int addressId)
	{
		 return RestAssured.given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).pathParam("shopperId",shopperId).pathParam("addressId",addressId).log().all()
		 .when().delete(Routes.deleteAddressUrl);		 
	}

}
