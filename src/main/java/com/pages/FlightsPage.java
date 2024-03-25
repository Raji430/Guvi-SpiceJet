package com.pages;

import org.openqa.selenium.WebElement;

import com.utils.Utility;

public class FlightsPage extends Utility {
	
	//Both One way and RoundTrip selection Details
	public static void tripDetails(String tripMode) throws Exception {

		WebElement from = findElementByXpath("//div[@data-testid='to-testID-origin']");
		clickOnElement(from);

		WebElement deptCountry = findElementByXpath("//div[@class='css-1dbjc4n r-18u37iz']//div[text()='" + Utility.getData("DeptCountry") + "']");
		waitForElement(deptCountry);
		clickOnElement(deptCountry);

		WebElement deptCity = findElementByXpath("//div[@class='css-1dbjc4n']//div[text()='" + Utility.getData("DeptCity") + "']");
		waitForElement(deptCity);
		clickOnElement(deptCity);

		WebElement destCountry = findElementByXpath("//div[@class='css-1dbjc4n r-18u37iz']//div[text()='" + Utility.getData("DestCountry") + "']");
		waitForElement(destCountry);
		clickOnElement(destCountry);
		
		WebElement destCity = findElementByXpath("//div[@class='css-1dbjc4n']//div[text()='" + Utility.getData("DestCity") + "']");
		waitForElement(destCity);
		clickOnElement(destCity);

		WebElement fromdate = findElementByXpath("//div[@data-testid='undefined-month-"
				+ Utility.getData("DepMonth") + "-2024']//div[text()='" + Utility.getData("DepDate") + "']");
		waitForElement(fromdate);
		clickOnElement(fromdate);

		if (tripMode.equals("RoundTrip")) {
			WebElement roundTrip = findElementByXpath("//div[@class='css-1dbjc4n']//div[text()='round trip']");
			waitForElement(roundTrip);
			clickOnElement(roundTrip);

			WebElement returnDate = findElementByXpath("//div[text()='Return Date']");
			clickOnElement(returnDate);

			WebElement todate = findElementByXpath("//div[@data-testid='undefined-month-"
					+ Utility.getData("ReturnMonth") + "-2024']//div[text()='" + Utility.getData("ReturnDate") + "']");
			waitForElement(todate);
			clickOnElement(todate);

		}
		
		WebElement currency = findElementByXpath("//div[text()='Currency']");
		waitForElement(currency);
		clickOnElement(currency);
		WebElement currCountry = findElementByXpath("//div[text()='USD']");
		clickOnElement(currCountry);

		WebElement searchFlight = findElementByXpath("//div[@data-testid='home-page-flight-cta']");
		waitForElement(searchFlight);
		clickOnElement(searchFlight);

		WebElement spiceSaver = findElementByXpath("(//div[contains(@data-testid,'spicesaver-flight')])[2]");
		scrollToElement(spiceSaver);
		waitForElement(spiceSaver);
		clickOnElement(spiceSaver);

		WebElement contButton = findElementByXpath("//div[@data-testid='continue-search-page-cta']");
		waitForElement(contButton);
		clickOnElement(contButton);

	}
	
	//Contact Details
	public static void contactDetails() throws Exception {
		WebElement title = findElementByXpath("//div[@data-testid='title-contact-detail-card']");
		clickOnElement(title);

		WebElement chooseTitle = findElementByXpath("//div[text()='" + Utility.getData("Title") + "']");
		clickOnElement(chooseTitle);

		WebElement firstName = findElementByXpath("//input[contains(@data-testid, 'first-inputbox')]");
		sendInput(firstName, Utility.getData("FirstName"));

		WebElement lastName = findElementByXpath("//input[contains(@data-testid, 'last-inputbox')]");
		sendInput(lastName, Utility.getData("LastName"));

		WebElement contactNum = findElementByXpath("//input[contains(@data-testid, 'contact-number-input-box')]");
		sendInput(contactNum, Utility.getData("MobileNumber"));

		WebElement email = findElementByXpath("//input[contains(@data-testid, 'emailAddress-inputbox')]");
		sendInput(email, Utility.getData("EmailId"));

		WebElement primaryPassenger = findElementByXpath("//div[text()='I am flying as the primary passenger']");
		clickOnElement(primaryPassenger);

		WebElement contPayment = findElementByXpath("//div[@data-testid='traveller-info-continue-cta']");
		clickOnElement(contPayment);

		// Commenting the code since SpiceJet Page has issue with payment page
		/*
		 * WebElement skipComfort = driver.findElement(By.id("skipfrompopup"));
		 * waitForElement(skipComfort); clickOnElement(skipComfort);
		 */
	}

	//Card Details
	public static String cardDetails() throws Exception {

		// Commenting the code since SpiceJet Page has issue with payment page
		/*
		 * WebElement cardNumber = driver.findElement(By.id("card_number"));
		 * sendInput(cardNumber, Utility.getData("CardNumber"));
		 * 
		 * WebElement cardHolderName = driver.findElement(By.id("name_on_card"));
		 * sendInput(cardHolderName, Utility.getData("FirstName"));
		 * 
		 * WebElement expiryMonth = driver.findElement(By.id("card_exp_month"));
		 * sendInput(expiryMonth, Utility.getData("CardExpMonth"));
		 * 
		 * WebElement expiryYear = driver.findElement(By.id("card_exp_year"));
		 * sendInput(expiryYear, Utility.getData("CardExpYear"));
		 * 
		 * WebElement cvvNum = driver.findElement(By.id("security_code"));
		 * sendInput(cvvNum, Utility.getData("CardCVV"));
		 * 
		 * WebElement payTC =
		 * findElementByXpath("//div[@data-testid='paymentTnC']");
		 * clickOnElement(payTC);
		 * 
		 * WebElement proceedToPay =
		 * findElementByXpath("//div[@data-testid=\"common-proceed-to-pay\"]")
		 * ; clickOnElement(proceedToPay);
		 */

		String pageURL = driver.getCurrentUrl();

		return pageURL;

	}

}
