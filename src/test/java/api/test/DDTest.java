package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProvider;
import io.restassured.response.Response;

public class DDTest { 

	@Test(priority = 1, dataProvider="Data", dataProviderClass = DataProvider.class)
	public void testPostUser(String userID, String username, String fname, String lname, String useremail, String pwd,
			String ph) {

		User userpayload = new User();

		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(username);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);

		Response response = UserEndPoints.CreateUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProvider.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}