package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestPayload.LoginPayload;

public class LoginEndPoints {

	public static Response shopperLogin(LoginPayload payload)
	{
		return RestAssured.given().log().all().contentType(ContentType.JSON).body(payload)
		.when().post(Routes.loginUrl);
	}
}
