package org.steps;

import java.io.IOException;

import org.config.ConfigReader;
import org.config.EmailUtil;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.BaseClass;
import io.cucumber.java.en.*;
import pages.InventoryManagementPartCreationPage;
import pages.LoginPage;

public class F02_InventoryManagementPartCreationStep extends BaseClass {

	ConfigReader config = new ConfigReader();
	LoginPage loginPage = new LoginPage(driver);
	InventoryManagementPartCreationPage inventoryManagementPartCreationPage = new InventoryManagementPartCreationPage(
			driver);
	String parentWindowHandle;
	WebDriverWait wait;

	@Given("User should be navigated into the Fleet Management system Dashboard with valid credentials")
	public void userShouldBeNavigatedIntoTheFleetManagementSystemDashboardWithValidCredentials() throws IOException {

		String username = config.getUsername();
		String password = config.getPassword();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
//		screenCapture("SignIn for Inventory");

		storeParentWindowHandle();
	}

	@When("User navigates to the Inventory Management section")
	public void userNavigatesToTheInventoryManagementSection() {
		inventoryManagementPartCreationPage.clickInventoryManagementButton();
		switchToChildWindow();
        System.out.println("Actual message: Navigated to Inventory Management system");

	}

	@When("User clicks on the Parts module in the navbar")
	public void userClicksOnThePartsModuleInTheNavbar() {
		inventoryManagementPartCreationPage.clickPartsButton();
	}

	@When("User clicks on the Create Part button")
	public void userClicksOnTheCreatePartButton() {
		inventoryManagementPartCreationPage.navigateToPartModule();
//		inventoryManagementPartCreationPage.clickNewPart();

	}

	@When("User fills in the following data:")
	public void userFillsInTheFollowingData(io.cucumber.datatable.DataTable dataTable) {

//		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
//		for (Map<String, String> row : data) {
//			for (Map.Entry<String, String> entry : row.entrySet()) {
//				String fieldName = entry.getKey();
//				String value = entry.getValue();
//				WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(fieldName)));
//				inputElement.sendKeys(value);
//
//			}
//		}
	}

	@When("User clicks on the Submit button")
	public void userClicksOnTheSubmitButton() {

	}

	@Then("User should see the New Part displayed in the parts table")
	public void userShouldSeeTheNewPartDisplayedInThePartsTable() {

//		 String body = "This is an automated email notification after the test execution.";
//	        EmailUtil.sendEmail(body);
	}

}
