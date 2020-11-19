package com.business.zee;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.mixpanelValidation.Mixpanel;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;
import com.zee5.PWAPages.*;

public class Zee5PWAWEBMixPanelBusinessLogic extends Utilities {

	public Zee5PWAWEBMixPanelBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	String URL = getParameterFromXML("url");

	private int timeout;

	/** Retry Count */
	private int retryCount;

	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
	final static Logger logger = Logger.getLogger("rootLogger");

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void ZeeWEBPWAMixPanelLogin(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			dismissDisplayContentLanguagePopUp();
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			String SubscribedUsername = getParameterFromXML("SubscribedUserName");
			String SubscribedPassword = getParameterFromXML("SubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	public void ZeeWEBPWAMixPanelLoginForParentalControl(String LoginMethod) throws Exception {
		String userType = getParameterFromXML("userType");
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			dismissDisplayContentLanguagePopUp();
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");
			String SUsername = getParameterFromXML("SettingsNonsubscribedUserName");
			String SPassword = getParameterFromXML("SettingsNonsubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");
			String SettingsSubscribedUsername = getParameterFromXML("SettingsSubscribedUserName");
			String SettingsSubscribedPassword = getParameterFromXML("SettingsSubscribedPassword");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SettingsSubscribedUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SettingsSubscribedPassword, "Password field");
			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Dismiss the Display Language pop up
	 */
	public void dismissDisplayContentLanguagePopUp() throws Exception {
		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 90,
				"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
				"Continue on Content Language Pop Up");
	}

	/**
	 * Function to enter url
	 */
	public void enterURLInWEBBrowser(String browser, String url) {
		extent.HeaderChildNode("Enter Browser URL");
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getWebDriver().get(url);
				extent.extentLogger("enteredURL", "Entered " + url + " in " + browser + " browser");
				logger.info("Entered " + url + " in " + browser + " browser");
			} catch (Exception e) {
				extent.extentLogger("failToEnterURL", "Failed to enter " + url + " in " + browser + " browser");
			}
		}
	}

	/**
	 * Function to select any tab
	 * 
	 */
	public boolean navigateToAnyScreenOnWeb(String screen) throws Exception {
		try {
			if (checkElementDisplayed(PWAHomePage.objHomeBarText(screen), screen + " Tab")) {
				click(PWAHomePage.objHomeBarText(screen), screen + " Tab");
				return true;
			} else {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
				waitTime(2000);
				try {
					WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuTabs(screen));
					logger.info(screen + " Tab is displayed");
					extent.extentLogger("tabDisplayed", screen + " Tab is displayed");
					executor.executeScript("arguments[0].click();", tab);
					logger.info("Clicked on " + screen + " Tab");
					extent.extentLogger("tabClicked", "Clicked on " + screen + " Tab");
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return false;
	}

	/**
	 * Function to scroll down
	 */
	public static void scrollDownWEB() {
		js.executeScript("window.scrollBy(0,250)", "");
	}

	/**
	 * Waits for player loader to complete
	 * 
	 * @throws Exception
	 */
	public void waitForPlayerLoaderToComplete() throws Exception {
		// verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);

		new WebDriverWait(getWebDriver(), 120)
				.until(ExpectedConditions.invisibilityOfElementLocated(PWAPlayerPage.objPlayerLoader));
	}

	/**
	 * Video Player or Live Player Ad verify
	 * 
	 * @param playerType
	 * @throws Exception
	 */
	public void waitForPlayerAdToComplete(String playerType) throws Exception {
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		waitTime(5000);
		main: for (int trial = 0; trial < 120; trial++) {
			try {
				findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
					try {
						getWebDriver().findElement(PWAPlayerPage.objAd);
					} catch (Exception e) {
					}
				}
				if (Math.floorMod(trial, 15) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);

//				//SkipAD
//				if(checkElementExist(PWAPlayerPage.objSkipAd, "SkipAd")){
//					Thread.sleep(5000);
//					click(PWAPlayerPage.objSkipAd, "SkipButton");					
//				}
//				else
//				{
//					System.out.println("No Skip Button Displayed");
//				}

			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						findElement(PWAPlayerPage.objPlayerSeekBar);
					}
					playerDisplayed = true;
					confirmCount++;
					if (confirmCount == 1) {
						if (adWasDisplayed == false) {
							logger.info("Ad did not play");
							extent.extentLogger("AdDidNotPlay", "Ad did not play");
						} else {
							logger.info("Ad play complete");
							extent.extentLogger("AdPlayComplete", "Ad play complete");
						}
						break main;
					}
				} catch (Exception e1) {
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad play failure");
			extent.extentLogger("failedAd", "Ad play failure");
		}
	}

	/**
	 * Function Scroll to Element
	 *
	 * @param element
	 * @throws Exception
	 */
	public void ScrollToTheElementWEB(By element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public void tearDown() {
		getWebDriver().quit();
	}

	public void navigateHome() {
		getWebDriver().get("https://newpwa.zee5.com/");
		getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void Back_TO_TopArrow_Web(String usertype) throws Exception {

		scrollToBottomOfPageWEB();
		if (usertype.equalsIgnoreCase("Guest")) {
			if (checkElementExist(PWAHomePage.objWhatWonderingPopUp, "Wondering popUp")) {
				waitTime(3000);
				click(PWAHomePage.objWhatWonderingPopUpCloseIcon, "Close icon");
			}
		}

		if (checkElementExist(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top")) {
			click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
			System.out.println("Scrolled back to top using Back to top btn");
		}

	}

	/**
	 * The method will wait for the element to be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located. The
	 * method throws error if the element could not be located within the given
	 * seconds
	 */
	public boolean waitForElement(By locator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator);
				logger.info("Located element " + message);
				extent.extentLogger("locatedElement", "Located element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to locate element " + message);
					extent.extentLoggerFail("failedLocateElement", "Failed to locate element " + message);
				}
			}
		}
		return false;
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logout");
		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("Logout"), "Logout option");
		waitTime(3000);
		verifyElementNotPresent(PWAHamburgerMenuPage.objProfileIconWEB, 5);
		if (verifyElementPresent(PWALoginPage.objLoginBtnWEB, "Logout")) {
			logger.info("User successfuly logged out");
			extent.extentLogger("Log out", "User successfuly logged out");
		}
		click(PWAHomePage.objZeeLogo, "Home page");
	}

	public void verifySkipLoginEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Login Event");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Login");
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifySkipLoginByClickingOnLoginInRegistrationPopUp(String userType, String keyword) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login in registration popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			click(PWAPremiumPage.obj1stContentInShowDetailPage, "Content Card");
			waitForElement(PWALoginPage.objLoginLink, 20, "Login Link");
			click(PWALoginPage.objLoginLink, "Login Link");
			waitForElement(PWALoginPage.objSkip, 10, "Skip Login");
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(2000);
			Back(1);
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "sign_in");
		}
	}

	public void verifySkipLoginByClickingOnLoginInGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {

			extent.HeaderChildNode(
					"Verify Skip Login Event during content playback post clicking on login button in Get Premium popup");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");
			click(PWALoginPage.objLoginCTAInPremiumPopup, "Login CTA");
			waitForElement(PWALoginPage.objSkip, 20, "Skip Login");
			click(PWALoginPage.objSkip, "Skip Login");
			waitTime(2000);
			Back(2);
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifySkipRegistrationEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Skip Registration Event");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitTime(3000);
			click(PWALoginPage.objRegisterLink, "Register Link");
			waitTime(2000);
			verifyElementPresentAndClick(PWALoginPage.objSkip, "Skip Registration");
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Registration", "sign_in");
		}
	}

	public void verifyRegisterScreenDisplayEvent(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Register Screen Display Event");
			click(PWALoginPage.objSignUpBtnWEB, "Sign Up For Free");
			waitTime(3000);

		}
	}

	public void verifySkipRegistrationEventDuringContentPlayback(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Skip Registration Event During Content Playback");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");
			click(PWAPremiumPage.obj1stContentInShowDetailPage, "Content Card");
			waitForElement(PWALoginPage.objCloseRegisterPopup, 20, "Skip Registration");
			click(PWALoginPage.objCloseRegisterPopup, "Skip Registration");

		}
	}

	public void verifySubscriptionPageViewedEventViaSubscribeBtn(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Page Viewed Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
		}
	}

	public void verifySubscriptionPageViewedEventViaBuySubscription(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on Buy subscription in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription option");

		}
	}

	public void verifySubscriptionPageViewedEventViaPrepaidCode(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify Subscription Page Viewed Event by clicking on prepaid code option in hamburger menu");
		if (userType.equalsIgnoreCase("Guest")) {
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			click(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code? option");

		}
	}

	public void verifySubscriptionSelectedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

		}
	}

	public void verifySubscriptionSelectedEventByClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Verify Subscription Selected Event By selecting Club Pack");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

		}
	}

	public void verifyPromoCodeResultEventForValid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Valid code");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			click(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, "PNB20", "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);

		}
	}

	public void verifyPromoCodeResultEventForInvalid(String userType) throws Exception {
		extent.HeaderChildNode("Verify Promo Code Result Event For Invalid code");
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");

			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, "sdcrfd", "Prepaid Code");
			click(PWASubscriptionPages.objApplyBtn, "Apply Button");

		}
	}

	public void verifyTVAuthenticationScreenDisplayEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify TV Authentication Screen Display Event");
			click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authenticate Device");
			Back(1);
		}
	}

	public void facebookLogin() throws Exception {
		extent.HeaderChildNode("Login through Facebook");
		getWebDriver().get(URL);
		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");

		waitForElementDisplayed(PWALoginPage.objFacebookIcon, 10);

		checkElementDisplayed(PWALoginPage.objGoogleIcon, "Google icon");
		waitTime(1000);
		checkElementDisplayed(PWALoginPage.objTwitterIcon, "Twitter icon");

		checkElementDisplayed(PWALoginPage.objFacebookIcon, "Facebook icon");

		waitTime(10000);
		click(PWALoginPage.objFacebookIcon, "Facebook Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			checkElementDisplayed(PWALoginPage.objFacebookPageVerificationWeb, "Facebook page");
			verifyElementPresent(PWALoginPage.objFacebookLoginEmailWeb, " Email Field");
			type(PWALoginPage.objFacebookLoginEmailWeb, "igstesttt@gmail.com", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWeb, " Password Field");
			type(PWALoginPage.objFacebookLoginpasswordWeb, "Igs123!@#", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWeb, "Login Button");
			switchToWindow(1);
			waitForElementDisplayed(PWALandingPages.objWebProfileIcon, 20);
			if (verifyElementPresent(PWALandingPages.objWebProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
		}
		logout();
	}

	public void phoneNumberRegistration() throws Exception {
		extent.HeaderChildNode(
				"verifing that user is able to enter Mobile number, Password, date of birth, gender in Registration page");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitForElementDisplayed(PWALoginPage.objEmailField, 5);
		checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		type(PWASignupPage.objOTP1, "1", "OTP box1");
		type(PWASignupPage.objOTP2, "2", "OTP box2");
		type(PWASignupPage.objOTP3, "3", "OTP box3");
		type(PWASignupPage.objOTP4, "4", "OTP box4");
		waitTime(3000);
		verifyElementPresentAndClick(PWASignupPage.objVerifyBtnWeb, "Verified Button");

	}

	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Login through Twitter");

		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitForElementDisplayed(PWALoginPage.objLoginPageheader, 10);

		waitForElementDisplayed(PWALoginPage.objTwitterIcon, 10);
		checkElementDisplayed(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(1000);

		click(PWALoginPage.objTwitterIcon, "twitter Icon");
		switchToWindow(2);

		if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			logout();

		}

		else {
			verifyElementPresent(PWALoginPage.objTwitterEmaildField, " Email Field");
			type(PWALoginPage.objTwitterEmaildField, "zee5latest@gmail.com", "Email Field");

			verifyElementPresent(PWALoginPage.objTwitterPasswordField, " Password Field");
			type(PWALoginPage.objTwitterPasswordField, "User@123", "Password Field");

			verifyElementPresentAndClick(PWALoginPage.objTwitterSignInButton, "Login Button");
			switchToParentWindow();
			waitForElementDisplayed(PWALandingPages.objWebProfileIcon, 20);
			if (checkElementDisplayed(PWALandingPages.objWebProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		}

	}

	public void socialLogin(String LoginMethod) throws Exception {
		switch (LoginMethod) {

		case "twitterLogin":
			twitterLogin();
			waitTime(3000);
			break;

		case "fbLogin":
			facebookLogin();
			waitTime(3000);
			break;

		}
	}

	public void verifyLoginInitiatedEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event for Valid Credentials");
			socialLogin(loginMethod);
		}
	}

	public void verifyLoginResultEventForValidCredentials(String userType, String loginMethod) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event for Valid Credentials");
			socialLogin(loginMethod);
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginButton(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button");
			verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
			waitTime(3000);
			Back(1);
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonOnPlayer(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Screen Display Event By Clicking On Login Button On Player");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			verifyElementPresent(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
			JSClick(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
			waitTime(2000);
			Back(1);
			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			waitTime(3000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInRegistartionScreen(String userType)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Registartion Screen");
			click(PWALoginPage.objWebLoginBtn, "Login button");
			click(PWALoginPage.objRegisterLink, "Register link");
			JSClick(PWALoginPage.objLoginLink, "Login link");
			Back(1);
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifyLoginScreenDisplayEventByClickingOnLoginButtonInGetPremiumPopUp(String userType, String keyword2)
			throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode(
					"Verify Login Screen Display Event By Clicking On Login Button In Get Premium Pop Up");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");

			if (checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				// ScrollToTheElementWEB(PWALoginPage.objLoginCTAInPremiumPopup);
				verifyElementPresentAndClick(PWALoginPage.objLoginCTAInPremiumPopup, "Login link");
				Back(1);
			}
			waitTime(2000);
			LocalStorage local = ((ChromeDriver) getWebDriver()).getLocalStorage();
			Mixpanel.ValidateParameter(local.getItem("guestToken"), "Skip Login", "home");
		}
	}

	public void verifyCarouselBannerClickEvent(String tabName) throws Exception {
		extent.HeaderChildNode(
				"Verify Carousel Banner Click Event And Video View Event For content played from Carousel");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");

	}

	public void verifyCarouselBannerClickEventAndVideoViewEvent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode(
				"Verify Carousel Banner Click Event And Video View Event For content played from Carousel");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		click(PWAPlayerPage.maximizeBtn, "Maximize icon");
		click(PWAPlayerPage.minimizeBtn, "Minimize icon");
		extent.HeaderChildNode(
				"Verify Video Watch Duration event when video is closed abruptly on playing Carousel Content");
		Back(1);

		click(PWAHomePage.objZeeLogo, "Zee Logo");
	}

	public void verifyThumbnailClickEventFromTray(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objThumbnail, "Thumbnail from a tray");
	}

	public void verifyThumbnailClickEventFromViewMorePage(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event For content played from trays");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objViewAllBtn, "View All Button");
		verifyElementPresentAndClick(PWAPremiumPage.objThumbnail, "Thumbnail from View More Page");

	}

	public void verifyThumbnailClickEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from Show detail page");
	}

	public void verifyThumbnailClickEventFromPlaybackPage(String keyword, String userType) throws Exception {
		extent.HeaderChildNode("Verify Thumbnail Click Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		click(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail");
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAPremiumPage.obj1stContentInShowDetailPage, "Thumbnail from playback page");

	}

	public void verifySearchExecutedEvent() throws Exception {
		extent.HeaderChildNode("Verify Search Executed Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, "kam" + "\n", "Search Edit box: ");
		waitTime(4000);

	}

	public void verifyScreenViewEvent(String screen) throws Exception {
		extent.HeaderChildNode("Verify Screen View Event");
		navigateToAnyScreenOnWeb(screen);

	}

	public void clearSearchHistoryEvent(String keyword3) throws Exception {
		extent.HeaderChildNode("Verify Clear Search History Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword3 + "\n", "Search Edit box: " + keyword3);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchCloseButton, "Clear Search Icon");

	}

	public void verifyParentalRestrictionEvent(String userType, String restriction) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {

			extent.HeaderChildNode("Verify Parental Restriction Event");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = getParameterFromXML("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = getParameterFromXML("SettingsSubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");

			switch (restriction) {

			case "Age13+":
				click(PWAHamburgerMenuPage.objRestrict13PlusContent, "Restrict all option");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
				checkElementDisplayed(PWAHomePage.objZeeLogo, "zee logo");
				waitTime(3000);

				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
				checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
				type(PWALoginPage.objPasswordField, password, "Password field");
				waitTime(2000);
				click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
				waitTime(2000);
				checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
				click(PWAHamburgerMenuPage.objParentalLockNoRestrictionOption, "No restriction option");
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
				click(PWAHomePage.objZeeLogo, "zee logo");

			case "RestrictAll":
				click(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
				click(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				waitTime(4000);
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
				checkElementDisplayed(PWAHomePage.objZeeLogo, "zee logo");
				waitTime(3000);

				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				click(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
				checkElementDisplayed(PWALoginPage.objPasswordField, "password field");
				type(PWALoginPage.objPasswordField, password, "Password field");
				waitTime(2000);
				click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
				waitTime(2000);
				checkElementDisplayed(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
				click(PWAHamburgerMenuPage.objParentalLockNoRestrictionOption, "No restriction option");
				click(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
				click(PWAHomePage.objZeeLogo, "zee logo");

			case "NoRestriction":
				click(PWAHamburgerMenuPage.objNoRestrictionSelected, "Restrict all option");
				click(PWAHamburgerMenuPage.objContinueButton, "Continue Button");

			}

		}
	}

	public void verifyShareEventFromPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Playback Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);
	}

	public void verifyRemoveFomWatchlistEventFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			click(PWAPlayerPage.watchListBtn, "Add to Watchlist button");
			waitTime(2000);
			verifyElementPresentAndClick(PWAPlayerPage.watchListBtn, "Remove From Watchlist button");
			waitTime(4000);
		}
	}

	public void verifyAddtoWatchlistFromPlaybackPage(String userType, String keyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event From Playback Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");

			verifyElementPresentAndClick(PWAPlayerPage.watchListBtn, "Add to Watchlist button");
			waitTime(4000);
		}
	}

	public void verifyAddToWatchlistEventByMouseHover(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Add to Watchlist Event by mouse hovering on a Content Card");
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");
		}

	}

	public void verifyRemoveFomWatchlistEventByMouseHover(String userType) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove from Watchlist Event by mouse hovering on a Content Card");
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			click(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");
			actions.moveToElement(contentCard).build().perform();
			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Remove from Watchlist icon");
		}

	}

	public void verifyShareEventByMouseHover() throws Exception {

		extent.HeaderChildNode("Verify Share Event By Mouse Hovering on a Content Card");
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);

		actions.moveToElement(contentCard).build().perform();
		verifyElementPresentAndClick(PWAPremiumPage.objContentCardShareBtn, "Share icon");

		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);

	}

	public void verifySearchCancelledEvent() throws Exception {

		extent.HeaderChildNode("Verify Search Cancelled Event");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		click(PWASearchPage.objSearchCancel, "Close Button");
	}

	public void verifyShareEventFromShowDetailPage(String keyword) throws Exception {

		extent.HeaderChildNode("Verify Share Event From Show Detail Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");

		click(PWAPlayerPage.shareBtn, "Share Option");
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");

		switchToWindow(2);
		Thread.sleep(2000);

		if (checkElementDisplayed(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		getWebDriver().close();
		switchToWindow(1);
		waitTime(3000);
	}

	public void verifyEpisodeListChosenEventFromShowDetailPage(String keyword) throws Exception {

		extent.HeaderChildNode("Verify Episode List Chosen Event in Show Detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(4000);
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		click(PWAShowsPage.objShowDetailEpisodeDropdownValues(2), "Episodes 11-20");
		waitTime(5000);
	}

	public void verifyContentBucketSwipeEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Show Detail page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");
		click(PWAPremiumPage.objLeftArrowBtn, "Left Arrow Button");
	}

	public void verifyViewMoreSelectedEventFromShowDetailPage(String keyword) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Show detail page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitTime(4000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");
	}

	public void verifyViewMoreSelectedEventFromPlaybackPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Playback page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(4000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");
	}

	public void verifyViewMoreSelectedEventFromTray() throws Exception {
		extent.HeaderChildNode("Verify View More Selected Event For content played from Tray");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View All Button");
	}

	public void verifyRemoveFromWatchlistEventFromMyWatchlistPage(String userType, String keyword) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Remove From Watchlist Event for Content from My Watchlist page");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
			waitTime(5000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			click(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");
			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");
			verifyElementPresentAndClick(PWAAddToWatchListPage.objRemoveContentsInWatchList,
					"Remove From Watchlist option");

		}
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getWebDriver().quit();
		relaunch = clearData;
		new Zee5PWAWEBMixPanelBusinessLogic("Chrome");
	}

	public void verifyQualityChangeEvent(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Quality Change Event");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		for (int i = 1; i <= findElements(PWAQualitySettingsPage.objAllQualities).size(); i++) {
			click(PWAQualitySettingsPage.objIndividualQuality(i), "Quality option");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
		}
		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Playback page");
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		click(PWAPremiumPage.objLeftArrowBtn, "Left Arrow Button");
	}

	public void verifyContentBucketSwipeEventInPlaybackPage(String keyword1) throws Exception {

		extent.HeaderChildNode("Verify Content Bucket Swipe Event in Playback page");

		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitTime(5000);

		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		click(PWAPremiumPage.objLeftArrowBtn, "Left Arrow Button");
	}

	public void verifyDisplayAndContentLanguageChangeEvent() throws Exception {
		extent.HeaderChildNode("Verify Display And Content Language Change Event");
		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		JSClick(PWALanguageSettingsPage.objFirstLanguage, "Hindi display language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");

		verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
		JSClick(PWALanguageSettingsPage.objSecondLanguage, "English display language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objAllLangByindex(1), "Hindi content language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
	}

	public void verifyContentBucketSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Content Bucket Swipe Event Across tabs");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objNextArrowBtn, "Right Arrow Button");
		click(PWAPremiumPage.objPreviousArrowBtn, "Left Arrow Button");

	}

	public void verifyCarouselBannerSwipeEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Carousel Banner Swipe Event Across tabs");

		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		click(PWAPremiumPage.objRightArrowBtn, "Right Arrow Button");
		click(PWAPremiumPage.objLeftArrowBtn, "Left Arrow Button");

	}

	public void verifyAdBannerImpressionEvent(String tabName) throws Exception {
		extent.HeaderChildNode("Verify Ad Banner Impression Event Across tabs");

		navigateToAnyScreenOnWeb(tabName);
		waitTime(5000);
		checkElementDisplayed(PWAHomePage.objAdBanner, "Ad Banner");

	}

	public void verifyDefaultSettingRestoredEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Default Setting Restored Event");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
			click(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
			waitTime(3000);
		}
	}

	public void FilterLanguage(String lang) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objSelectLang(lang), lang + " language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
	}

	public void verifyShareEventForUpcomingProgram() throws Exception {
		extent.HeaderChildNode("Verify Share Event For Upcoming Program");
		navigateToAnyScreenOnWeb("Live TV");
		click(PWALiveTVPage.objUpcomingLiveProgramShareBtn, "Share button");
		waitTime(3000);
		click(PWALiveTVPage.objFacebookShareBtn, "Share to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(2);
		if (!checkElementDisplayed(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook")) {
			click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			click(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			click(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(4000);
		}
		click(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		waitTime(7000);
		acceptAlert();
		waitTime(3000);
		switchToParentWindow();
		waitTime(3000);

	}

	public void verifySetReminderEventForUpcomingProgram(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Set Reminder Event");
			navigateToAnyScreenOnWeb("Live TV");
			wouldYouLikeToPopupClose();
			click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
			click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
			FilterLanguage("Bengali");
			click(PWALiveTVPage.objBanglaShow1, "Show detail");
			click(PWALiveTVPage.objRemainderButton, "Reminder option");
			waitTime(3000);
		}

	}

	public void wouldYouLikeToPopupClose() throws Exception {
		if (checkElementDisplayed(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
	}

	public void verifyChangePasswordStartedEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Started Event");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
		}
	}

	public void verifyChangePasswordResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Change Password Result Event");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igsindia123", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);

			click(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
			click(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
			type(PWAHamburgerMenuPage.objChangeOldPassword, "igszee5", "Current password field");
			click(PWAHamburgerMenuPage.objNewPassword, "new password field");
			type(PWAHamburgerMenuPage.objNewPassword, "igsindia123", "new password field");
			click(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
			type(PWAHamburgerMenuPage.objConfirmNewPassword, "igsindia123", "Current confirm field");
			waitTime(3000);
			click(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
			waitTime(2000);
		}
	}

	public void verifyProfileUpdateResultEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Profile Update Result Event");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
			verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
			clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "email field");
			type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "save changes");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "go back button");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		}
	}

	public void verifyCTAsEvent(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Verify CTAs Event");
		navigateToAnyScreenOnWeb(tabName);

		click(PWAHomePage.objSearchBtn, "Search Icon");
		Back(1);
		click(PWAHomePage.objLanguageBtn, "Language button");
		Back(1);
		if (!(userType.equalsIgnoreCase("Guest"))) {
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
			Back(1);
		}

		click(PWAHomePage.objSubscribeBtn, "Subscribe button");

		Back(1);

		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");

		click(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings");
		Back(1);
	}

	public void verifyBannerAutoplayEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Banner Autoplay Event");
		navigateToAnyScreenOnWeb("News");

		waitForElementDisplayed(PWANewsPage.objBannerUnMute, 20);

	}

	public void verifyMuteChangedEventForNewsContent() throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event");
		navigateToAnyScreenOnWeb("News");

		waitForElementDisplayed(PWANewsPage.objBannerUnMute, 20);

		if (checkElementDisplayed(PWANewsPage.objBannerUnMute, "Volume icon") == true) {
			JSClick(PWANewsPage.objBannerUnMute, "Mute Icon");
		} else {
			JSClick(PWANewsPage.objBannerMute, "UnMute Icon");
		}

	}

	public void verifyResumeEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
	}

	public void verifyResumeEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Resume Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyResumeEventForTrailer(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
	}

	public void verifyResumeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
	}

	public void verifyResumeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
	}

	public void verifyResumeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		waitTime(2000);
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
	}

	public void verifyResumeEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Resume Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyResumeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
	}

	public void verifyResumeEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		click(PWAPlayerPage.objPlayerPause, "Pause Icon");
	}

	public void verifyVideoViewEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

	}

	public void verifyVideoViewEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video View Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

		}
	}

	public void verifyVideoViewEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoViewEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoViewEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
	}

	public void verifyVideoViewEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoViewEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video View Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);

		}
	}

	public void verifyVideoViewEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoViewEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoViewEventAfterRefreshingPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video View Event after refreshing a page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		getWebDriver().navigate().refresh();
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

	}

	public void verifyVideoExitEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		Back(1);
	}

	public void verifyVideoExitEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Exit Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			Back(1);
		}
	}

	public void verifyVideoExitEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Video Exit Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			Back(1);
		}
	}

	public void verifyVideoExitEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoExitEventAfterRefreshingPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event after refreshing a page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		getWebDriver().navigate().refresh();
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForFreeContentAbrupt(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForPremiumContentAbrupt(String userType, String keyword1)
			throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			Back(1);
		}
	}

	public void verifyVideoWatchDurationEventForTrailerAbrupt(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForCarouselContentAbrupt() throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when video is closed abruptly For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentInTrayAbrupt() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageAbrupt(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageAbrupt(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when video is closed abruptly For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			Back(1);
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuAbrupt() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistAbrupt(String userType, String keyword)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyPopUpLaunchEventForGetPremiumPopUp(String userType, String keyword2) throws Exception {
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			extent.HeaderChildNode(
					"Verify Pop Up Launch Event when get premium popup is displayed on playing premium content");

			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword2 + "\n", "Search Edit box: " + keyword2);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword2), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword2), "Search Result");
			checkElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP");
		}
	}

	public void verifyPauseEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Pause Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
				click(PWAPlayerPage.objPlayerPlay, "Play Icon");
				waitTime(2000);
				click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			} else {
				click(PWAPlayerPage.objPlayerPause, "Pause Icon");
				waitTime(2000);
				click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			}
		}
	}

	public void verifyPauseEventForTrailer(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Pause Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
				click(PWAPlayerPage.objPlayerPlay, "Play Icon");
				waitTime(2000);
				click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			} else {
				click(PWAPlayerPage.objPlayerPause, "Pause Icon");
				waitTime(2000);
				click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			}
		}
	}

	public void verifyPauseEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Pause Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyResumeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Resume Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyQualityChangeEventForLinearContent() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Linear Content");
		navigateToAnyScreenOnWeb("News");
		click(PWAPremiumPage.objWEBMastheadCarousel, "Linear Content");
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Quality Change Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		}
	}

	public void verifyQualityChangeEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForContentFromMyWatchlistPage(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Quality Change Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			click(PWAPlayerPage.qualityBtn, "Quality option");
			click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		}
	}

	public void verifyQualityChangeEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
	}

	public void verifyQualityChangeEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");

	}

	public void verifyQualityChangeEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		mandatoryRegistrationPopUp(userType);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		waitTime(6000);
	}

	public void verifyQualityChangeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Quality Change Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		click(PWAPlayerPage.qualityBtn, "Quality option");
		click(PWAQualitySettingsPage.objIndividualQuality(2), "Quality Good option");
		waitTime(6000);
	}

	public void playerScrubTillLastWeb() {
		WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
		WebElement progressBar = getWebDriver().findElement(PWAPlayerPage.objPlayerProgressBar);
		Actions action = new Actions(getWebDriver());
		action.clickAndHold(scrubber).moveToElement(progressBar, 350, 0).release().perform();
	}

	public void verifyResumeEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Resume Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyPauseEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Pause Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyVideoViewEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video View Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);

	}

	public void verifyVideoExitEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event for content autoplayed from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		Back(1);

	}

	public void verifyVideoViewEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video View Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
	}

	public void verifyVideoExitEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Video Exit Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		Back(1);
	}

	public void verifyPauseEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Pause Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyResumeEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Resume Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		if ((checkElementDisplayed(PWAPlayerPage.objPlayerPlay, "Play Icon"))) {
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
		} else {
			click(PWAPlayerPage.objPlayerPause, "Pause Icon");
			waitTime(2000);
			click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		}
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkAbrupt(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromSharedLinkComplete(String freeContentURL) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches the content playback shared through shared link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);

	}

	public void verifyVideoWatchDurationEventForFreeContentComplete(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration when user completely watches For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForPremiumContentComplete(String userType, String keyword1)
			throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitTime(6000);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			Back(1);
		}
	}

	public void verifyVideoWatchDurationEventForTrailerComplete(String userType, String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event  when user completely watches Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForCarouselContentComplete() throws Exception {
		extent.HeaderChildNode("Verify Video Watch Duration Event when user completely watches Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentInTrayComplete() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromSearchPageComplete(String keyword1) throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromMyWatchlistPageComplete(String userType, String keyword)
			throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode(
					"Verify Video Watch Duration Event when user completely watches Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			Back(1);
		}
	}

	public void verifyVideoWatchDurationEventForContentInMegamenuComplete() throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentInPlaylistComplete(String userType, String keyword)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when user completely watches Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		Back(1);
	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailComplete(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event When user completely watches the  auto-played content from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);

	}

	public void verifyVideoWatchDurationEventForContentFromUpnextRailAbrupt(String userType, String keyword4)
			throws Exception {
		extent.HeaderChildNode(
				"Verify Video Watch Duration Event when video is closed abruptly on auto-played content from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");

		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		Back(1);

	}

	public void verifyScrubSeekEventForFreeContent(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Free Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitForElement(PWASearchPage.objSearchResult(keyword4), 20, "Search Result");
		click(PWASearchPage.objSearchResult(keyword4), "Search Result");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForPremiumContent(String userType, String keyword1) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Premium Content");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword1), "Search Result");
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			waitTime(5000);
		}
	}

	public void verifyScrubSeekEventForTrailer(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Trailer Content");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForCarouselContent() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Carousel Content");
		waitTime(5000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel Content");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentInTray() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Tray");
		click(PWAPremiumPage.objThumbnail, "Content From a tray");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentFromSearchPage(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content From Search Page");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentFromMyWatchlistPage(String userType, String keyword) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Verify Scrub/Seek Event For Content From My Watchlist Page");
			click(PWAHomePage.objSearchBtn, "Search Icon");
			type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchResult(keyword), 10, "Search Result");
			click(PWASearchPage.objSearchResult(keyword), "Search Result");

			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver().findElement(PWAPremiumPage.obj1stContentInShowDetailPage);
			actions.moveToElement(contentCard).build().perform();

			verifyElementPresentAndClick(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist icon");

			click(PWALandingPages.objWebProfileIcon, "Profile icon");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist option");

			click(PWAAddToWatchListPage.objWatchlistedItems, "Content Card in Watchlist page");
			mandatoryRegistrationPopUp(userType);
			waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
			waitTime(6000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			playerScrubTillLastWeb();
			waitTime(5000);
		}
	}

	public void verifyScrubSeekEventForContentInMegamenu() throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Megamenu");
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHomePage.objHomeBarText("Movies"));
		actions.moveToElement(contentCard).build().perform();

		click(PWAPlayerPage.megaMenuContentCard, "Content Card in Megamenu");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentInPlaylist(String userType, String keyword) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Playlist");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword), "Search Result");
		mandatoryRegistrationPopUp(userType);
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content From a tray");
		mandatoryRegistrationPopUp(userType);
		waitTime(2000);
		click(PWAPremiumPage.objContentInPlaylist, "Content card in Playlist");
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentFromUpnextRail(String userType, String keyword4) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For Content played from Upnext rail");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword4 + "\n", "Search Edit box: " + keyword4);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchResult(keyword4), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		mandatoryRegistrationPopUp(userType);
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		click(PWAPlayerPage.objPlayerPlay, "Play Icon");
		waitTime(6000);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		mandatoryRegistrationPopUp(userType);
		waitForPlayerAdToComplete("Video Player");
		waitTime(6000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyScrubSeekEventForContentFromSharedLink(String freeContentURL) throws Exception {
		extent.HeaderChildNode("Verify Scrub/Seek Event For content played from Shared Link");
		getWebDriver().get(freeContentURL);
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);

		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		playerScrubTillLastWeb();
		waitTime(5000);
	}

	public void verifyMuteChangedEventDuringContentPlayback(String keyword1) throws Exception {
		extent.HeaderChildNode("Verify Mute Changed Event During Content Playback");
		click(PWAHomePage.objSearchBtn, "Search Icon");
		type(PWASearchPage.objSearchEditBox, keyword1 + "\n", "Search Edit box: " + keyword1);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchResult(keyword1), 10, "Search Result");
		click(PWASearchPage.objSearchResult(keyword1), "Search Result");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 20);
		waitTime(6000);
		click(PWAPlayerPage.audioBtn, "Mute Icon");
		waitTime(2000);
		click(PWAPlayerPage.audioBtn, "Unmute Icon");
	}

	public void verifyLoginInitiatedEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Initiated Event post entering invalid credentials");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		}
	}

	public void verifyLoginResultEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Login Result Event post entering invalid credentials");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
		}
	}

	public void verifyToastMessageImpressionEventInSignInScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign In Screen");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		}
	}

	public void verifyToastMessageImpressionEventInOTPScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In OTP Screen");
			click(PWALoginPage.objLoginBtnWEB, "Login button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
		}
	}

	public void verifyRegistrationInitiatedEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Initiated Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
		}
	}

	public void verifyRegistrationResultEventForInvalidCredentials(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Registration Result Event post entering invalid credentials");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
		}
	}

	public void verifyToastMessageImpressionEventInSignUpScreen(String userType) throws Exception {
		if (userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Verify Toast Message Impression Event In Sign Up Screen");
			click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
			waitForElementDisplayed(PWALoginPage.objEmailField, 5);
			checkElementDisplayed(PWALoginPage.objEmailField, "Email/PhoneNo Field");
			type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
			click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
			type(PWASignupPage.objOTP1, "1", "OTP box1");
			type(PWASignupPage.objOTP2, "2", "OTP box2");
			type(PWASignupPage.objOTP3, "3", "OTP box3");
			type(PWASignupPage.objOTP4, "4", "OTP box4");
			waitTime(3000);
			click(PWASignupPage.objVerifyBtnWeb, "Verified Button");
		}
	}

	public void verifySubscriptionCallReturnedEvent(String userType) throws Exception {
		extent.HeaderChildNode(
				"Subscription Call Returned Event when user makes unsuccessful transaction by quitting the payment gateway screen");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objPaymentPageProceedBtn, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5318 3123 4521 9856", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);
			checkElementDisplayed(PWASubscriptionPages.objCancelTransaction, "Cancel Transaction");
			click(PWASubscriptionPages.objCancelTransaction, "Cancel Transaction");

			acceptAlert();
			waitTime(10000);
		}
	}

	public void verifySubscriptionCallInitiatedEvent(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for All access pack");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objPaymentPageProceedBtn, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5318 3123 4521 9856", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);

		}
	}

	public void verifySubscriptionCallInitiatedEventClubPack(String userType) throws Exception {
		extent.HeaderChildNode("Subscription Call Initiated Event for Club pack");

		if (!(userType.equals("SubscribedUser"))) {
			click(PWAHomePage.objSubscribeBtn, "Subscribe button");
			click(PWASubscriptionPages.objClubPack, "Club Pack");
			click(PWASubscriptionPages.objPackAmount1, "Pack");
			click(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(2000);

			if (userType.equals("Guest")) {
				if (checkElementDisplayed(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
					click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
					type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
					verifyElementPresentAndClick(PWASubscriptionPages.objPaymentPageProceedBtn, "Proceed Button");
					// Password Popup
					verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
					verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
					type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
					verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
				}
			}
			waitTime(10000);
			WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);

			click(PWASubscriptionPages.objEnterCardNumber, "Card Number");
			type(PWASubscriptionPages.objEnterCardNumber, "5318 3123 4521 9856", "Card Number");
			click(PWASubscriptionPages.objEnterExpiry, "Expiry");
			type(PWASubscriptionPages.objEnterExpiry, "0224", "Expiry");
			click(PWASubscriptionPages.objEnterCVV, "CVV");
			type(PWASubscriptionPages.objEnterCVV, "123", "CVV");
			click(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
			waitTime(10000);
		}
	}

	public void verifyLogoutEvent(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			logout();
		}
	}
}
