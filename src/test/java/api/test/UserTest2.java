package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest2 {
	 
	Faker faker;
	User userpayload;
	
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void TestPostUser()
	{
		logger.info("********Creating User********");
		Response response= UserEndPoints2.CreateUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	logger.info("****** User is Created********");
	
	}
	
	@Test(priority=2)
	public void testGetUserByName() 
	{
		logger.info("****** Reading User**********");
		Response response= UserEndPoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("******User info is displayed **********");
	}
	
	
	@Test(priority=3)
	public void testupdateUser()
	{
		logger.info("****** Updating  User**********");
		//update data using payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndPoints2.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		logger.info("******User  is Updated* *********");
		//checking data after uddate
		Response responseAfterupdate=UserEndPoints2.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteByName()
	{
		logger.info("****** Deleting  User**********");
	Response response=	UserEndPoints2.deleteUser(this.userpayload.getUsername());
	Assert.assertEquals(response.getStatusCode(),200);
	logger.info("******User is deleted **********");
	
	}
	
	 
}


 