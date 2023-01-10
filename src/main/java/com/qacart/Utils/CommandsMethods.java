package com.qacart.Utils;

import com.app.base.Base;

import io.appium.java_client.MobileElement;

public class CommandsMethods extends Base {
	
	
	
	public void writeText(MobileElement element , String text) {
		
		element.sendKeys(text);
	}
	
	public void clickButton(MobileElement element) {
		
		element.click();
	}

}
