package org.steps;



import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.config.ConfigReader;
import org.junit.Assert;

import hooks.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class F01_LoginStep extends BaseClass{
	
    ConfigReader config = new ConfigReader();
    LoginPage loginPage = new LoginPage(driver);

	
	@Given("User should be in Fleet Management Login Page")
	public void userShouldBeInFleetManagementLoginPage() {

        // Assert that the login form is displayed
        assertTrue(loginPage.isLoginFormDisplayed());

	}

	@When("User should Enter userName and password")
	public void userShouldEnterUserNameAndPassword() {

		 String username = config.getUsername();
	        String password = config.getPassword();
	        loginPage.enterUsername(username);
	        loginPage.enterPassword(password);
//	        setText(userName, username); (i.e: Without Page Object Model)
//	        setText(pass, password);
	}

	@When("User should Click Login button")
	public void userShouldClickLoginButton() throws IOException {
		loginPage.clickLogin();
//        screenCapture("SignIn success");
	}

	@Then("User should validate Login success message")
	public void userShouldValidateLoginSuccessMessage() throws IOException {
		
		  String expectedMessage = "Login successful"; // Replace with the actual expected message
	        String actualMessage = loginPage.getSuccessMessage();
	        System.out.println("Actual message: " + actualMessage);
	        Assert.assertEquals(expectedMessage.toLowerCase(), actualMessage.toLowerCase());
	        
//	        screenCapture("Login_Successfully");
	  
	}
}
