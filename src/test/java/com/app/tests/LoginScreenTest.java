package com.app.tests;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.Screens.LoginScreen;
import com.app.base.Base;
import com.qacart.Utils.CommandsMethods;

public class LoginScreenTest extends Base{
	
	LoginScreen loginScreen ;
	CommandsMethods commandsMethods;
	
@Test
public void testWithValidEmailAndPassword() {

	loginScreen = new LoginScreen ();
	commandsMethods = new CommandsMethods();
	commandsMethods.writeText(loginScreen.emailField, "abcd@gmail.com");
	commandsMethods.writeText(loginScreen.passwordField, "123456");
	commandsMethods.clickButton(loginScreen.LoginButtonField);
	Assert.assertEquals(loginScreen.TextMessage, "valid credentiels");	
}



}
