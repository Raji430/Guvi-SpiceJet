package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.BaseClass;
import com.utils.Utility;

public class LoginPage extends Utility {
	
	// Login using mobile/email
	public static void loginToPage(String userName, String paswrd, String loginOption) throws Exception {
		// WebElement loginOptionMobile =
		// driver.findElement(By.xpath("//div[text()='Mobile Number']"));
		WebElement loginOptionEmail = driver.findElement(By.xpath("//div[@class='css-1dbjc4n']//div[text()='Email']"));
		WebElement loginInputMobile = driver.findElement(By.xpath("//input[@type='number']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));

		switch (loginOption) {
		case "MobileNumber": {
			// clickOnElement(loginOptionMobile);
			sendInput(loginInputMobile, userName);
			break;
		}
		case "Email": {
			clickOnElement(loginOptionEmail);
			WebElement loginInputEmail = driver.findElement(By.xpath("//input[@type='email']"));
			sendInput(loginInputEmail, userName);
		}
		}
		sendInput(password, paswrd);

		WebElement login = driver.findElement(By.xpath("//div[@data-testid='login-cta']"));
		waitForElement(login);
		clickOnElement(login);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	}

	//Login success by returning user details
	public static String loginSuccess(String loginSucc) throws Exception {

		String userLogin = null;
		WebElement errorMsg = driver.findElement(By.xpath("//div[text()='" + loginSucc + "']"));
		waitForElement(errorMsg);
		userLogin = errorMsg.getText();

		return userLogin;
	}

	//Invalid Credentials by returning error messages
	public static String loginInvalidCred(String loginErrMsg) throws Exception {
		String errMsg = null;
		WebElement errorMsg = driver.findElement(By.xpath("//div[text()='" + loginErrMsg + "']"));
		waitForElement(errorMsg);
		errMsg = errorMsg.getText();

		return errMsg;
	}
}
