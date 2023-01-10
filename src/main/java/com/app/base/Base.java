package com.app.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	
	public static AndroidDriver<MobileElement> driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest logger;


	
	
	@BeforeSuite
	public void Beforesuite () {
		
		extent= new ExtentReports("C:\\Users\\Finance\\eclipse-workspace\\AAAA\\target\\reports\\index.html",true);
		extent.addSystemInfo("framwork","Appium");
		extent.addSystemInfo("Tester","slhaheddine");
		
		
	}
	@AfterSuite
	public void afterSuite() {
		
		extent.flush();
		driver.quit();
	}
	@BeforeMethod
	public void beforeMethod(Method method) {
		
		
		logger= extent.startTest(method.getName());
		
	}
	@AfterMethod
	public void afterMethod(Method method ,ITestResult result) throws IOException  {
		
         TakesScreenshot ts = (TakesScreenshot)driver;
		
		File image = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File("C:\\Users\\Finance\\eclipse-workspace\\AAAA\\target\\reports\\" +method.getName()+".png"));
		
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
			logger.log(LogStatus.FAIL, "test is failed");
			logger.log(LogStatus.FAIL, result.getThrowable());
			logger.log(LogStatus.FAIL, "<a href='" + result.getName() + ".png"
					+ "'><span class='lable info'>Download snapshot </span></a>");
						
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			
			logger.log(LogStatus.PASS, "test is passed");
			logger.log(LogStatus.PASS, "<a href='" + result.getName() + ".png"
					+ "'><span class='lable info'>Download snapshot </span></a>");
								
		}
		else if (result.getStatus()==ITestResult.SKIP){
			
			logger.log(LogStatus.SKIP, "test is skipped");
						
		}
		driver.close();
					
	}
	 
	@Parameters({"deviceName","platformName","platformVersion" })
	@BeforeClass
	public void BeforeTest(String deviceName, String platformName, String platformVersion) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Finance\\eclipse-workspace\\E2EAppiumFramwork\\src\\main\\resources\\config\\config.properties");

		prop.load(fis);
		
		if(platformName.equalsIgnoreCase("Android")) {
			
			File AndroidApp = new File(prop.getProperty("androidAppPath"));
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.APP, AndroidApp.getAbsolutePath());

			 driver = new AndroidDriver<MobileElement>(
					new URL(prop.getProperty("appiumServerLink")), caps);
		}else if(platformName.equalsIgnoreCase("IOS")) {
			
			File IOSAppPath = new File(prop.getProperty("IOSAppPath"));
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,platformVersion);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("IOSAutomationName"));

			
		}
		
	}
	
	@AfterClass
	public void end() {
				
		driver.quit();
	}

}
