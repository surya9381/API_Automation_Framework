package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
 
	User userpayload;
	@Test(priority = 1,dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId,String userName, String fn, String ln,String email, String pwd,String ph)
	{
		userpayload=new User();
		userpayload.setId(Integer.parseInt(userId));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fn);
		userpayload.setLastName(ln);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);	
	}
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUser(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
