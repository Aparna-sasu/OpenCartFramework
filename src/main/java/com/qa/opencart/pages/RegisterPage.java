package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	By registerLink = By.xpath("//*[@id=\"column-right\"]/div/a[2]");
	By fName = By.id("input-firstname");
	By lName = By.name("lastname");
	By email = By.cssSelector("#input-email");
	By telephone = By.xpath("//*[@id=\"input-telephone\"]");
	By pswd = By.cssSelector("#input-password");
	By pswdConfirm = By.xpath("//*[@id=\"input-confirm\"]");
	By privacyPolicy = By.name("agree");
	By confirmButton = By.className("btn-primary");
	By header = By.tagName("h1");
	By subscribeYes = By.xpath("//label[@class='radio-inline'][1]");
	By subscribeNo = By.xpath("//label[@class='radio-inline'][2]");
	By agreeCheckBox = By.name("agree");
	By continueButton = By.xpath("//input[@value='Continue']");
	By sucessMessage = By.xpath("//div[@id='content']/h1");
	By logOutLink = By.linkText("Logout");
	By register = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public boolean userRegistration(String firstName,String lastName,String telephone,String password,String subscribe) throws InterruptedException {
		
		elementUtil.waitForElementVisible(fName, AppConstants.MEDIUM_DEFAULT_TIMEOUT).sendKeys(firstName);
		elementUtil.doSendKeys(lName, lastName);
		elementUtil.doSendKeys(this.email, StringUtils.getrandomEmailId());
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(pswd, password);
		elementUtil.doSendKeys(pswdConfirm, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYes);
			
		
		}
		else {
			elementUtil.doClick(subscribeNo);
		}
			
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(confirmButton);
		
		if(elementUtil.waitForElementVisible(sucessMessage, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText().contains(AppConstants.REGISTER_SUCCESS_MSG)) {
			elementUtil.doClick(logOutLink);
			elementUtil.doClick(register);
			return true;
		}
			
		return false;	
			
			
	}
}
