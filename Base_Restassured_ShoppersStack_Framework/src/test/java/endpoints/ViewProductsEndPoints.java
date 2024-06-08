package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ViewProductsEndPoints {
	
	
	public static Response viewProducts()
	{
		return RestAssured.given().contentType(ContentType.JSON)
		.when().get(Routes.viewProductsUrl);
	}

}
