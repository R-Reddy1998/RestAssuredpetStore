package api.endpoints;

import static io.restassured.RestAssured.*;
 
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndPoints.java
// Create for perform Create, read,Update, Delete Request the user API.

public class UserEndPoints2 {

	
	// method Created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routs =ResourceBundle.getBundle("routes");//Load propertirs file //name of the properties file
		return routs;
	}
	public static Response CreateUser (User payload)
	{
	String post_url=getURL().getString("post_url");
		
	Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
	.when()
		.post(post_url);
	return response;
		 
	}
	public static Response readUser (String userName)
	{
		String get_url=getURL().getString("get_url");
	Response response=given()
			.pathParam("username",userName)
	 
		.when()
		.get(get_url);
	return response;
		 
	}

	public static Response updateUser (String userName,User payload)
	{
		String update_url=getURL().getString("update_url");
		
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username",userName)
			.body(payload)
		.when()
		.get(update_url);
	return response;
		 
	}
	
	public static Response deleteUser (String userName)
	{
		String delete_url=getURL().getString("delete_url");
		
	Response response=given()
			.pathParam("username",userName)
	 
		.when()
		.get(delete_url);
	return response;
		 
	}
	
	
	
}
