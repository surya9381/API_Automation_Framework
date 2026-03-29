package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().fullName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setUserStatus(0);
		
		logger=LogManager.getLogger(this.getClass());
		logger.debug("debugging .......");
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("******* User is creating ***********");
		Response response = UserEndPoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(),200);
		response.then().log().all();
		
		logger.info("******* User is created ***********");
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("******* Reading the user info ***********");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("******* User data is displayed ***********");
	}
	
	@Test(priority = 3)
	public void testUpdateUser()
	{
		logger.info("******* updating user data ***********");
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("******* User data is updated ***********");
	}
	
	@Test(priority = 4)
	public void testDeleteUser()
	{
		logger.info("******* Deleting user ***********");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("******* User is deleted ***********");
	}
	
}
