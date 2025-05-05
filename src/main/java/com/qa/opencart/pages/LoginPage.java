package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*; // here you are importing all the static variables in to this class thus, no need to 
// write AppConstants.DEFAULT_TIMEOUT like that. you can simply write the constant name.

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By LoginButton = By.xpath("//input[@value='Login']");
	private final By forgotPasswordLink = By.linkText("Forgotten Password11");
	private final By registerLink = By.linkText("Register");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Getting login page title")
	public String getLoginPageTitle() {
		
		String title = elementUtil.waitForTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login page title is : "+title);
		return title;
	}
	@Step("Getting login page url")
	public String getLoginPageUrl() {
		String url = elementUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Current page url is : "+url);
		return url;
	}

	@Step("Checking forgotpassword link exist or not.")
	public boolean isForgotPasswordLinkDisplayed() {
		return elementUtil.isElementDisplayed(forgotPasswordLink);
		}
	
	@Step("Login with valid username: {0} and password: {1}")
	public AccountsPage doLogin(String userName, String pswd) {

		System.out.println("Login credentials are : username"+userName +"password : "+pswd ); 
		
		elementUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(userName);
		elementUtil.doSendKeys(password, pswd);
		elementUtil.doClick(LoginButton);
		
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to the registration page")
	public RegisterPage navigateToRegisterPage() {
		elementUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
}
