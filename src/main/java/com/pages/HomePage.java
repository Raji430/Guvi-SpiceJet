package com.pages;

import org.openqa.selenium.WebElement;

import com.utils.Utility;

public class HomePage extends Utility 
{
	public static String signUpTitle = "SpiceClub - The frequent flyer program of SpiceJet";	
	
	//Click on Sign Up button
	public static void clickOnSignUpButton() throws Exception
	{	
		WebElement signUpButton = findElementByXpath("//div[text()='Signup']");		
		waitForElement(signUpButton);
		clickOnElement(signUpButton);
		Utility.switchToWindow(signUpTitle);
	}
	
	//Click on login button
	public static void clickOnLoginButton() throws Exception
	{
		WebElement loginButton = findElementByXpath("//div[text()='Login']");
		loginButton.click();
	}
	
	//Page Fields
	public static String pageFields(String pageFldText) throws Exception
	{
		WebElement pageFieldElement = findElementByXpath("//div[contains(@data-testid, '"+pageFldText+"')]");
		
		String pageFldName = pageFieldElement.getText();
		
		return pageFldName;
	}
}
