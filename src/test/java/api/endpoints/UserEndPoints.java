package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndPoints.java
// Create for perform Create, read,Update, Delete Request the user API.

public class UserEndPoints {

	public static Response CreateUser (User payload)
	{
		
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
	.when()
		.post(Routs.post_url);
	return response;
		 
	}
	public static Response readUser (String userName)
	{
		
	Response response=given()
			.pathParam("username",userName)
	 
		.when()
		.get(Routs.get_url);
	return response;
		 
	}

	public static Response updateUser (String userName,User payload)
	{
		
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username",userName)
			.body(payload)
		.when()
		.get(Routs.update_url);
	return response;
		 
	}
	
	public static Response deleteUser (String userName)
	{
		
	Response response=given()
			.pathParam("username",userName)
	 
		.when()
		.get(Routs.delete_url);
	return response;
		 
	}
	
	
	
}
