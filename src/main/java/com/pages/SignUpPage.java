package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.Utility;

public class SignUpPage extends Utility
{	
	//Sign Up page Input Form Details
	public static void signUpInputFormDetails() throws Exception
	{	
		WebElement title = findElementByXpath("//label[text()='Title']/../select");		
		WebElement firstName = driver.findElement(By.id("first_name"));	
		WebElement lastName = driver.findElement(By.id("last_name"));
		WebElement country = findElementByXpath("//label[text()='Country/Territory of Residence']/../select");
		WebElement mobileNumber = findElementByXpath("//input[@type='tel']");
		WebElement EmailId = driver.findElement(By.id("email_id"));
		WebElement password = driver.findElement(By.id("new-password"));
		WebElement confirmPassword = driver.findElement(By.id("c-password"));
		WebElement datePicker = findElementByXpath("//img[@class='d-inline-block datepicker']");
		sendInput(EmailId, Utility.getData("EmailId"));		
		
		waitForElement(title);
		selectDropDownByVisibleText(title, Utility.getData("Title"));
		
		sendInput(firstName, Utility.getData("FirstName"));
		sendInput(lastName, Utility.getData("LastName"));
		waitForElement(datePicker);
		scrollToElement(datePicker);
		clickOnElement(datePicker);	
		
		WebElement year = findElementByXpath("//select[@class='react-datepicker__year-select']");
		year.click();
		selectDropDownByVisibleText(year, Utility.getData("Year"));
		
		WebElement month = findElementByXpath("//select[@class='react-datepicker__month-select']");
		selectDropDownByIndex(month, Utility.getData("Month"));
		
		WebElement date = findElementByXpath("//div[text()='"+Utility.getData("Date")+"']");
		clickOnElement(date);
		
		sendInput(password, Utility.getData("Password"));
		sendInput(confirmPassword, Utility.getData("ConfirmPassword"));
		
		selectDropDownByVisibleText(country, Utility.getData("Country"));
		
		sendInput(mobileNumber, Utility.getData("MobileNumber"));
		waitForElement(mobileNumber);		

		WebElement terms = findElementByXpath("//input[@id='defaultCheck1']");
		clickOnElementActions(terms);	
		clickOnElement(terms);
		
		WebElement submitButton = findElementByXpath("//button[@class='btn btn-red']");
		submitButton.click();
		}
	
	//Returns success message after SignUp
	public static String signUpSuccessValMsg(String successMsg) throws Exception
	{
		WebElement succMsg = findElementByXpath("//div[text()='"+successMsg+"']");
		
		String message = succMsg.getText();
		return message;
	}
	
	//Returns error message for mandatory field missing
	public static String signUpAllMandFieldCheck() throws Exception
	{
		scrollByPixel();
		WebElement submitButton = findElementByXpath("//button[@class='btn btn-red']");
		waitForElement(submitButton);
		submitButton.click();
		
		WebElement valMsg = findElementByXpath("(//div[@class='font-13 mt-10 inlineErrors'])[1]");
		
		String message = valMsg.getText();
		return message;
	}
	
}
