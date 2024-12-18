package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	WebDriverWait wait;

	// Locators
	By loginForm = By.tagName("form");
	By userName = By.name("email");
	By pass = By.name("password");
	By login = By.xpath("//button[text()='Sign In']");
	By successMessage = By.xpath("//div[text()='Login successful']");
	
	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	// Method to verify if the login form is displayed
	public boolean isLoginFormDisplayed() {
		WebElement formElement = driver.findElement(loginForm);
		return formElement.isDisplayed();
	}

	// Methods to interact with the elements
	public void enterUsername(String username) {
		driver.findElement(userName).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(pass).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(login).click();
	}

	public String getSuccessMessage() {
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
		return messageElement.getText();
				
	}


}
