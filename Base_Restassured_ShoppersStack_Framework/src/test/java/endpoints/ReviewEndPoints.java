package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestPayload.ReviewPayload;

public class ReviewEndPoints {
	
	
	public static Response addReview(String token,int productId,ReviewPayload payload)
	{
		return RestAssured.given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).queryParam("productId",productId).body(payload).log().all()
		.when().post(Routes.addReviewUrl);		
	}
	
	public static Response deleteReview(String token,int productId,int reviewId)
	{
		return RestAssured.given().contentType(ContentType.JSON).header("Authorization","Bearer "+token).queryParam("productId",productId).pathParam("reviewId",reviewId).log().all()
		.when().delete(Routes.deleteReviewUrl);		
	}

}
