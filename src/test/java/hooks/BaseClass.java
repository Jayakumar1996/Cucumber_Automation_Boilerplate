package hooks;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseClass {
	public static WebDriver driver;
	public static JavascriptExecutor js;
	protected String parentWindowHandle;

	public static void setup(String browserName) {

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();

		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}
	}

	public void textSendByJS(WebElement element, String keysToSend) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + keysToSend + "')", element);

	}

	public void setText(By by, String text) {
		try {
			WebElement element = driver.findElement(by);
			String value = element.getText();
//			element.clear();
			element.sendKeys(text);
			System.out.println("Sent keys to element with XPath: " + value);
		} catch (Exception e) {
			WebElement element = driver.findElement(by);
			String value = element.getText();
			System.out.println("Unable to send keys to element with XPath: " + value);
			e.printStackTrace();
		}
	}

	public void click(By by) {

		try {
			WebElement element = driver.findElement(by);
			String value = element.getText();
			element.click();
			System.out.println("Clicked element with XPath: " + value);
		} catch (Exception e) {
			WebElement element = driver.findElement(by);
			String value = element.getText();
			System.out.println("Unable to click element with XPath: " + value);

		}
	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("Unable to click element with XPath: " + url);
		return url;
	}

//need set drivet null in if condition
	public static void quit() {
		if (driver != null) {
			driver.quit();
		}
	}

	// change to by

	public static void dragAndDrop(By fromLocator, By toLocator) {
		WebElement from = driver.findElement(fromLocator);
		WebElement to = driver.findElement(toLocator);
		Actions a = new Actions(driver);
		a.dragAndDrop(from, to).perform();
	}

	// change to by
	public void scroll(By elementLocator, String type) {
		WebElement element = driver.findElement(elementLocator);

		switch (type) {
		case "Up":
			js.executeScript("arguments[0].scrollIntoView(false)", element);
			break;

		case "Down":
			js.executeScript("arguments[0].scrollIntoView(true)", element);
			break;

		default:
			System.out.println("Invalid Type");
			break;
		}
	}

// change folder path
	public void screenCapture(String name) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(
				"C:\\Users\\jayakumart\\eclipse-workspace\\Fleet_Automation\\Screenshot" + name + ".jpeg");
		FileUtils.copyFile(source, target);
	}

	public void saveScreenshot(String sourcePath, String destinationPath) {
		try {
			// Ensure the destination directory exists
			Files.createDirectories(Paths.get(destinationPath).getParent());

			// Move the screenshot file
			Files.move(Paths.get(sourcePath), Paths.get(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// change to by
	public void selectByValue(By elementLocator, String value) {
		WebElement element = driver.findElement(elementLocator);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void frame(By elementLocator) {
		WebElement element = driver.findElement(elementLocator);
		driver.switchTo().frame(element);
	}

	public void refresh() {
		driver.navigate().refresh();
	}

//	public String windowHandling(int index) {
//
//		// To find Parent Windows ID
//		String parentWindowsID = driver.getWindowHandle();
//
//		// To find All windows ID
//		Set<String> allWindowsID = driver.getWindowHandles();
//
//		// To add all windows into List
//		List<String> li = new LinkedList<>();
//		li.addAll(allWindowsID);
//
//		// To get Required windows ID
//		String childWindowsID = li.get(index);
//
//		return childWindowsID;
//	}
	// Method to store the parent window handle
	public void storeParentWindowHandle() {
		parentWindowHandle = driver.getWindowHandle();
	}

	// Method to switch to the child window
	public void switchToChildWindow() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String windowHandle : allWindowHandles) {
			if (!windowHandle.equals(parentWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
	}

	// Explicit Waits
	public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBePresent(By locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public Boolean waitForTitleToBe(String title, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.titleIs(title));
	}

	public Boolean waitForUrlToContain(String fraction, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.urlContains(fraction));
	}

}
