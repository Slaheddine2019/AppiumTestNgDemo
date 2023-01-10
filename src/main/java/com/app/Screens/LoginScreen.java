package com.app.Screens;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.app.base.Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginScreen extends Base {
	
	public LoginScreen() {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	@iOSXCUITFindBy(xpath = "//android.widget.EditText[@index='1']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
	public  MobileElement emailField;
	@iOSXCUITFindBy(xpath = "//android.widget.EditText[@index='1']")
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    public  MobileElement passwordField;
	@iOSXCUITFindBy(xpath ="//android.widget.EditText[@index='1']")
	@AndroidFindBy(xpath ="//*[@text='Login']")
	public  MobileElement LoginButtonField;
	@iOSXCUITFindBy(xpath ="//android.widget.EditText[@index='3']")
	@AndroidFindBy(xpath ="//*[@text='Login']")
	public  MobileElement TextMessage;
        
}
