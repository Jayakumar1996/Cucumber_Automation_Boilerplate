package hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.testng.Assert;

public class BaseClass {
    public static WebDriver driver;
    public static JavascriptExecutor js;
    protected String parentWindowHandle;

    // Setup driver based on browser name
    public static void setup(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Unsupported browser: " + browserName);
        }
    }

    // Setup for multi-platform support
    public static void multiPlatform(String browserName, String platform) {
        if (List.of("windows", "mac", "linux").contains(platform.toLowerCase())) {
            setup(browserName);
        } else {
            System.out.println("Invalid platform name");
        }
    }
    
	public static void deleteAllCookies(){
		driver.manage().deleteAllCookies();	
	}
	
	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	
	public static void sendKeys(WebElement e, String data) {
		e.sendKeys(data);
	}

    // Send text using JavaScript
    public void textSendByJS(WebElement element, String keysToSend) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", element, keysToSend);
    }

    // Set text in an element located by By
    public void setText(By by, String text) {
        try {
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Click an element located by By
    public void click(By by) {
        try {
            driver.findElement(by).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get current URL
    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Quit driver
    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Drag and drop between elements located by By
    public static void dragAndDrop(By fromLocator, By toLocator) {
        WebElement from = driver.findElement(fromLocator);
        WebElement to = driver.findElement(toLocator);
        new Actions(driver).dragAndDrop(from, to).perform();
    }

    // Scroll to an element using JavaScript
    public void scroll(By elementLocator, String direction) {
        WebElement element = driver.findElement(elementLocator);
        js = (JavascriptExecutor) driver;
        boolean scrollDown = direction.equalsIgnoreCase("down");
        js.executeScript("arguments[0].scrollIntoView(arguments[1])", element, scrollDown);
    }

    // Capture screenshot
    public void screenCapture(String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("Screenshots/" + name + ".jpeg"));
    }

    // Save a screenshot to a specific path
    public void saveScreenshot(String sourcePath, String destinationPath) {
        try {
            Files.createDirectories(Paths.get(destinationPath).getParent());
            Files.move(Paths.get(sourcePath), Paths.get(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Select an option by value
    public void selectByValue(By elementLocator, String value) {
        new Select(driver.findElement(elementLocator)).selectByValue(value);
    }

    // Switch to a specific frame
    public void frame(By elementLocator) {
        driver.switchTo().frame(driver.findElement(elementLocator));
    }

    // Refresh the browser
    public void refresh() {
        driver.navigate().refresh();
    }

    // Store parent window handle
    public void storeParentWindowHandle() {
        parentWindowHandle = driver.getWindowHandle();
    }

    // Switch to a child window
    public void switchToChildWindow() {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    // Explicit waits
    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForElementToBeInvisible(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBePresent(By locator, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitForTitleToBe(String title, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.titleIs(title));
    }

    public boolean waitForUrlToContain(String fraction, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.urlContains(fraction));
    }

    // Take screenshot
    public void takeScreenshot(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(fileName));
    }

    // Switch to new tab
    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1));
    }

    // Switch to main tab
    public void switchToMainTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (!tabs.isEmpty()) driver.switchTo().window(tabs.get(0));
    }

    // Construct file path relative to project directory
    public String constructProjectFilePath(String... paths) {
        return Paths.get(System.getProperty("user.dir"), paths).toString();
    }

    // Upload file
    public void uploadFile(By fileInputLocator, String filePath) {
        File file = new File(filePath);
        Assert.assertTrue(file.exists(), "File not found: " + filePath);
        driver.findElement(fileInputLocator).sendKeys(filePath);
    }

    // Read Excel file
    public String readExcel(int rownum, int cellnum) throws IOException {
        FileInputStream stream = new FileInputStream(new File("data.xlsx"));
        Workbook book = new XSSFWorkbook(stream);
        Sheet sheet = book.getSheetAt(0);
        Cell cell = sheet.getRow(rownum).getCell(cellnum);
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue());
                } else {
                    return BigDecimal.valueOf(cell.getNumericCellValue()).toString();
                }
            default:
                return "";
        }
    }
    
	public static String generateRandomName() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();

        // Generate a random name of a specific length (e.g., 8 characters)
        int nameLength = 8;
        Random random = new Random();

        for (int i = 0; i < nameLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            name.append(randomChar);
        }

        return name.toString();

    }
	
	 public static String generateRandomColor() {
	        Random random = new Random();
	        // Generate random values for red, green, and blue components
	        int red = random.nextInt(256);
	        int green = random.nextInt(256);
	        int blue = random.nextInt(256);

	        // Convert RGB values to hexadecimal format
	        return String.format("#%02x%02x%02x", red, green, blue);
	    }
}
