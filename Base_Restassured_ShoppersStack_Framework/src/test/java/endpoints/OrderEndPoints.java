package endpoints;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestPayload.OrderPayload;

public class OrderEndPoints {

	public static Response placeOrder(String token, int shopperId, OrderPayload orderPayload) {
		return RestAssured.given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token)
				.pathParam("shopperId", shopperId).body(orderPayload).log().body().when().post(Routes.placeOrderUrl);
	}

	public static Response updateOrder(String token, int shopperId, int orderId,String status) {
		return RestAssured.given().contentType(ContentType.JSON).header("Authorization", "Bearer " + token)
				.pathParam("shopperId", shopperId).pathParam("orderId", orderId).queryParam("status",status).log().body().when()
				.patch(Routes.updateOrderStatusUrl);
	}

}
