package com.test;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.FlightsPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.SignUpPage;
import com.utils.Utility;

public class SpiceJetTest extends Utility {

	//Launch Browser and open the SpiceJet page
	@BeforeMethod
	public void launchSite() throws Exception {
		Utility.browserLaunch(Utility.getData("Browser"));
		Utility.loadSite(Utility.getData("URL"));		
	}

	// Validate Broken URL
	@BeforeTest
	public void brokenUrlCheck() {
		int respCode = Utility.brokenURL(Utility.getData("URL"), Utility.getData("RespCode"));

		if (respCode >= 400) {
			System.out.println(Utility.getData("URL") + " is a broken link");
		} else {
			System.out.println(Utility.getData("URL") + " is a valid link");
		}
	}
	
	//Validate user is able to Sign Up Successfully
	@Test(priority = 1)
	public void signUp_TC01() throws Exception {
		
		String expSuccMsg = "Member account exists with given email ID";
		
		HomePage.clickOnSignUpButton();
		SignUpPage.signUpInputFormDetails();
		
		String actSuccMsg = SignUpPage.signUpSuccessValMsg(expSuccMsg);
		assertEquals(actSuccMsg, expSuccMsg);
	} 
	
	//Validate error message is shown in SignUp for mandatory fields
	@Test(priority = 2)
	public void signUp_MandCheck_TC02() throws Exception {
		
		String expErrorMsg = "Please fill all mandatory fields marked with an '*' to proceed";
		HomePage.clickOnSignUpButton();
		
		String actErrorMsg = SignUpPage.signUpAllMandFieldCheck();
		assertEquals(actErrorMsg, expErrorMsg);		
	}	

	//Validate user is able to login using Email
	@Test(priority = 3)
	public void login_Email_TC03() throws Exception {
		// Need to get xpath to validate user name after login
		String expUserName = "Hi " + Utility.getData("FirstName");
		String actUserName = null;
		String loginOption = "Email";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(Utility.getData("EmailId"),Utility.getData("Password"), loginOption);
		actUserName = LoginPage.loginSuccess(expUserName);
		assertEquals(actUserName, expUserName);
	} 	
	
	//Validate user is able to login using Mobile Number
	@Test(priority = 4)
	public void login_Mobile_TC04() throws Exception {
		// Need to get xpath to validate user name after login
		String expUserName = "Hi " + Utility.getData("FirstName");
		String actUserName = null;
		String loginOption = "MobileNumber";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(Utility.getData("MobileNumber"),Utility.getData("Password"), loginOption);
		actUserName = LoginPage.loginSuccess(expUserName);
		assertEquals(actUserName, expUserName);
	}	
	
	//Validate the availability of all fields
	@Test(priority = 5)
	public void pageFieldValidation_TC05() throws Exception {
		Map<String, String> pageFields = new HashMap<String, String>();
		pageFields.put("Flights", "Flights");
		pageFields.put("Check-In", "check-in");
		pageFields.put("Flight Status", "flight status");
		pageFields.put("Manage Booking", "manage booking");

		for (Map.Entry<String, String> entry : pageFields.entrySet()) {
			String actFieldText = HomePage.pageFields(entry.getValue()).toString();
			assertEquals(actFieldText, entry.getKey());
		}
	}
	
	//Validate user is able to book ticket for One Way Trip
	@Test(priority = 6)
	public void oneWayFlight_TC06() throws Exception {
	
	//Validating the current URL since page is not navigating to payment phase
		String expSuccessMsg = "https://www.spicejet.com/booking";
		String actSuccessMsg = null;
		
		FlightsPage.tripDetails("OneWay");
		FlightsPage.contactDetails();

		actSuccessMsg = FlightsPage.cardDetails();
		assertEquals(actSuccessMsg, expSuccessMsg);
	}	

	//Validate user is able to book ticket for Round Trip
	@Test(priority = 7)
	public void roundTripFlight_TC07() throws Exception {
		
		//Validating the current URL since page is not navigating to payment phase
		String expSuccessMsg = "https://www.spicejet.com/booking";
		String actSuccessMsg = null;

		FlightsPage.tripDetails("RoundTrip");
		FlightsPage.contactDetails();

		actSuccessMsg = FlightsPage.cardDetails();
		assertEquals(actSuccessMsg, expSuccessMsg);
	}
	
	//Validate error message is thrown when user enters incorrect emailId
	@Test(priority = 8)
	public void login_IncorrectEmail_TC08() throws Exception {

		String userName = Utility.getData("InvalidEmail");
		String paswrd = Utility.getData("Password");
		String expErrMsg = "Invalid Username/Password";
		String actErrMsg = null;
		String loginOption = "Email";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);
	}
	
	//Validate error message is thrown when user enters incorrect mobile number
	@Test(priority = 9)
	public void login_IncorrectMobile_TC09() throws Exception {
		
		String userName = Utility.getData("InvalidMobileNumber");
		String paswrd = Utility.getData("Password");
		String expErrMsg = "Invalid Username/Password";
		String actErrMsg = null;
		String loginOption = "MobileNumber";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);

	}

	//Validate error message is thrown when user enters incorrect password
	@Test(priority = 10)
	public void login_IncorrectPassword_TC10() throws Exception {
		String userName = Utility.getData("EmailId");
		String paswrd = Utility.getData("InvalidPassword");
		String expErrMsg = "Invalid Username/Password";
		String actErrMsg = null;
		String loginOption = "Email";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);
	}
	
	//Validate error message is thrown when user miss to enter mobile number	
	@Test(priority = 11)
	public void login_mandMobileInput_TC11() throws Exception {
		
		String userName = "";
		String paswrd = Utility.getData("Password");
		String expErrMsg = "Please enter a valid mobile number";
		String actErrMsg = null;
		String loginOption = "MobileNumber";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);

	}
	//Validate error message is thrown when user miss to enter emailID
	@Test(priority = 12)
	public void login_mandEmailInput_TC12() throws Exception {
		String userName = "";
		String paswrd = Utility.getData("Password");
		String expErrMsg = "Please enter a valid email address";
		String actErrMsg = null;
		String loginOption = "Email";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);
	} 
	
	//Validate error message is thrown when user miss to enter password
	@Test(priority = 13)
	public void login_mandPaswrdInput_TC13() throws Exception {
		String userName = Utility.getData("EmailId");
		String paswrd = "";
		String expErrMsg = "Please enter a valid password";
		String actErrMsg = null;
		String loginOption = "Email";
		HomePage.clickOnLoginButton();
		LoginPage.loginToPage(userName, paswrd, loginOption);
		actErrMsg = LoginPage.loginInvalidCred(expErrMsg);
		assertEquals(actErrMsg, expErrMsg);
	} 
	
	//Closes the browser
	@AfterMethod
	public void closeSite() {
		Utility.closeCurrentWindow();
	}
}
