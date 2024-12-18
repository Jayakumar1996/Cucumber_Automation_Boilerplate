package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryManagementPartCreationPage {

	WebDriver driver;
	WebDriverWait wait;

	By inventoryManagementButton = By.xpath("//h6[text()='Inventory Management']");
	// Locator for the "Parts" button
	By partsButton = By.linkText("Parts");
	By navigateToPartModuleSidebar = By.id("select-parts");
	By createNewPart = By.id("part-create");
	// Locators for part creation fields (example names, adjust as necessary)
	By partNameField = By.name("Part Name");
	By partNumberField = By.name("Part Number");
	By descriptionField = By.name("Description");
	By quantityField = By.name("Quantity");
	By unitPriceField = By.name("Unit Price");
	By submitButton = By.xpath("//button[text()='Submit']");

	// Constructor
	public InventoryManagementPartCreationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void clickInventoryManagementButton() {
		   WebElement element = driver.findElement(inventoryManagementButton);

	        // Scroll the element into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        
	        // Wait for the element to be clickable
	        wait.until(ExpectedConditions.elementToBeClickable(inventoryManagementButton));
	        
	        // Click the element
	        element.click();

	}

	// Method to click on the "Parts" button in Navbar
	public void clickPartsButton() {
		WebElement partsBtn = wait.until(ExpectedConditions.elementToBeClickable(partsButton));
		partsBtn.click();
	}

	public void navigateToPartModule() {
		WebElement navigateToPart = wait.until(ExpectedConditions.elementToBeClickable(navigateToPartModuleSidebar));
		navigateToPart.click();
	}

	public void clickNewPart() {

//		WebElement NewPart = wait.until(ExpectedConditions.elementToBeClickable(createNewPart));
//		NewPart.click();

	}

	public void createPart(String partName, String partNumber, String description, String quantity, String unitPrice) {
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(partNameField)).sendKeys(partName);
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(partNumberField)).sendKeys(partNumber);
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField)).sendKeys(description);
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityField)).sendKeys(quantity);
//	        wait.until(ExpectedConditions.visibilityOfElementLocated(unitPriceField)).sendKeys(unitPrice);
//	        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
	}



}
