package uscis.dhs.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.internal.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class BaseTestMethods {
	static BaseAPPMethods Config = new BaseAPPMethods();
	public static int speed = 50;
	public static final String driverLocation = System.getProperty("user.dir")+"/driver/";
	public static final String osID = System.getProperty("os.name");
	public static String brwVer = Config.configProp().getProperty("browser_ver");
	protected BaseTestMethods(){
		
	}
	
	protected WebDriver selenium =null;
	protected File file =null;
	
	public void lunchSite(String seleniumBrowser, String seleniumUrl){
		if (seleniumBrowser.equals("chrome")){
			
			System.out.println("Test running in: "+ osID +"machine");
			
			if (osID.toLowerCase().contains("mac")) {
				
				file = new File(driverLocation+"chromedriver");	
			} else if (osID.toLowerCase().contains("windows")) {
				
				if(brwVer.equals("2.41")) {
					file = new File(driverLocation+"2_41/chromedriver.exe");
				}else {
					file = new File(driverLocation+"chromedriver.exe");
				}
				
			} else {
				file = new File(driverLocation+"chromedriver");
			}

			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		    ChromeOptions options = new ChromeOptions();
		    options.setExperimentalOption("useAutomationExtension", false);
		    
			selenium = new ChromeDriver(options);
		}
	selenium.get(seleniumUrl);	
	}
	
	public void enter_text(By by, String val) throws TestExecutionException{
		try{
			Thread.sleep(speed);
			//setFocus(by)
			selenium.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			selenium.findElement(by).sendKeys(val);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	protected boolean isDisplayed(By by) throws TestExecutionException{
		try{
			Thread.sleep(speed);
			selenium.findElement(by).isDisplayed();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;				
		}
	}
	
	protected String get_text(By by) throws TestExecutionException{
		try{
			String get_text = selenium.findElement(by).getText();
			return get_text;
		}catch(Exception e){
			e.printStackTrace();			
			return null;
		}
	}	
	
	protected boolean verify_text(String actual, String expected) throws TestExecutionException{
		try{
			Assert.assertEquals(expected.toLowerCase(), actual.toLowerCase());
			return true;
		}catch(AssertionError e){
			setupAfterTest();
			e.printStackTrace();
			Assert.fail("Unable to find expected value: " + expected + " error: "+ e);	
			return false;	
		}
	}
	
	protected void click(By by) throws TestExecutionException{
		try{
			Thread.sleep(speed);
			selenium.findElement(by).click();
		}catch(Exception e){
			setupAfterTest();
			e.printStackTrace();
			Assert.fail("Unable to find expected element: " + by + " error: "+ e);				
		}
	}
	
	public void alertOK() {
		try{
			WebDriverWait wait = new WebDriverWait(selenium, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = selenium.switchTo().alert();
			alert.accept();
		}catch(Exception e){
			setupAfterTest();
			e.printStackTrace();
			Assert.fail("Unable to find alert window "+ e);				
		}
	}
	
	public void setupAfterTest() {
		selenium.quit();
	}
	
}

