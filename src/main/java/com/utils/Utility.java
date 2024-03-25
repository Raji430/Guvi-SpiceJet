package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	public static WebDriver driver;
	public static WebElement element;
	public static HttpURLConnection huc = null;

	// Valid URL
	public static int brokenURL(String url, String respCode) {
		try {
			huc = (HttpURLConnection) (new URL(url).openConnection());

			huc.setRequestMethod("HEAD");

			huc.connect();

			respCode = String.valueOf(huc.getResponseCode());
		} catch (Exception e) {
			System.out.println("Input URL is Broken");
		}
		return Integer.parseInt(respCode);
	}

	// Launch browser
	public static void browserLaunch(String browser) {
		if (browser.equals("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else {
			driver = new FirefoxDriver();
		}
	}

	// Load the URL
	public static void loadSite(String url) {
		driver.get(url);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	// Input Test Data using Excel
	public static String getData(String columnName) {
		String res = null;
		try {
			File file = new File(System.getProperty("user.dir") + "\\TestData.xlsx");
			FileInputStream inputFile = new FileInputStream(file);
			XSSFWorkbook book = new XSSFWorkbook(inputFile);
			XSSFSheet sheet = book.getSheetAt(0);
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell celldata = (Cell) cellIterator.next();

					if (celldata.getStringCellValue().equals(columnName)) {
						celldata.getColumnIndex();
						row = sheet.getRow(1);
						return res = row.getCell(celldata.getColumnIndex()).getStringCellValue();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);

		}
		return res;
	}

	// Click on element using Actions
	public static void clickOnElementActions(WebElement element) throws Exception {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click(element).build().perform();
	}

	// Click on element using Javascript Executor
	public static void clickOnElementJS(WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		waitForElement(element);
		js.executeScript("arguments[0].click();", element);
	}

	// Click on Element
	public static void clickOnElement(WebElement element) throws Exception {
		waitForElement(element);
		element.click();
	}

	// Send Input to textbox fields
	public static void sendInput(WebElement element, String value) throws Exception {
		waitForElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		scrollToElement(element);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);
		waitForElement(element);
		element.click();
		element.sendKeys(value);
	}

	// Scroll to element using Javascript Executor
	public static void scrollJS(WebElement element) throws Exception {
		waitForElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		waitForElement(element);
	}

	// Scroll by Pixel using Javascript Executor
	public static void scrollByPixel() throws Exception {
		waitForElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1000,1000)", "");
		waitForElement(element);
	}

	// Select drop down by visible text
	public static void selectDropDownByVisibleText(WebElement element, String value) throws Exception {

		Select dropDown = new Select(element);
		waitForElement(element);
		dropDown.selectByVisibleText(value);
	}

	// Select drop down by value
	public static void selectDropDownByValue(WebElement element, String value) throws Exception {

		Select dropDown = new Select(element);
		waitForElement(element);
		dropDown.selectByValue(value);
	}

	// Select drop down by index
	public static void selectDropDownByIndex(WebElement element, String value) throws Exception {

		Select dropDown = new Select(element);
		waitForElement(element);
		dropDown.selectByIndex(Integer.valueOf(value));
	}

	// Scroll to element using Actions
	public static void scrollToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.scrollToElement(element);
	}

	// Switch to window
	public static void switchToWindow(String tabName) {
		for (String winHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(winHandle).getTitle().equals(tabName)) {
				break;
			}
		}
	}

	// Find Element method using Xpath
	public static WebElement findElementByXpath(String path) {
		return driver.findElement(By.xpath(path));

	}

	// Wait for element
	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Close the Window
	public static void closeCurrentWindow() {

		ArrayList<String> switchTabs = new ArrayList<String>(driver.getWindowHandles());
		if (switchTabs.size() > 1) {
			driver.switchTo().window(switchTabs.get(1));
			driver.close();
			driver.switchTo().window(switchTabs.get(0));
		}
		driver.close();
	}

	// Quit the browser
	public static void closeBrowser() {
		if (driver == null) {
			driver.quit();
		}
	}
}
