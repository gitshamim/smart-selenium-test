package uscis.dhs.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.*;

import uscis.dhs.pageObject.home_page_obj;
import uscis.dhs.pageObject.comeon_in_page_obj;
import uscis.dhs.pageObject.error_page_obj;

public class BaseAPPMethods extends BaseTestMethods{

	static home_page_obj ObjHome = new home_page_obj();
	static error_page_obj ObjErorr = new error_page_obj();
	static comeon_in_page_obj ObjComeon = new comeon_in_page_obj();
	
	public BaseAPPMethods(){}
	
	public Properties configProp(){
		Properties prop = new Properties();
		InputStream input = null;
				
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	public Properties testData(){
		Properties testdata = new Properties();
		InputStream input = null;
				
		try {
			input = new FileInputStream("test.data");
			testdata.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return testdata;
	}
	
	public void verify_home_page_title(String expected_value)throws TestExecutionException{
		boolean pg_ttle = isDisplayed(ObjHome.page_title_lbl());
		if (pg_ttle == true){
			String actual_value = get_text(ObjHome.page_title_lbl());
			verify_text(expected_value, actual_value);
		}else{
			setupAfterTest();
		    Assert.fail("Unable to find expected value from :" + ObjHome.page_title_lbl() + "object");	
		}
	}
	
	public void verify_home_page_welcome_msg(String expected_value)throws TestExecutionException{
		boolean pg_wc_msg = isDisplayed(ObjHome.welcome_msg_lbl());
		if (pg_wc_msg == true){
			String actual_value = get_text(ObjHome.welcome_msg_lbl());
			verify_text(expected_value, actual_value);
		}else{
			setupAfterTest();
		    Assert.fail("Unable to find expected value from :" + ObjHome.welcome_msg_lbl() + "object");	
		}
	}	
	
	public void verify_home_page_come_on_in_link(String expected_value)throws TestExecutionException{
		boolean pg_lnk_txt = isDisplayed(ObjHome.come_on_in_lnk());
		if (pg_lnk_txt == true){
			String actual_value = get_text(ObjHome.come_on_in_lnk());
			verify_text(expected_value, actual_value);
		}else{
			setupAfterTest();
		    Assert.fail("Unable to find expected value from :" + ObjHome.come_on_in_lnk() + "object");	
		}
	}	
	
	public void click_come_on_in_link()throws TestExecutionException{
		boolean link_obj = isDisplayed(ObjHome.come_on_in_lnk());
		if (link_obj == true){
			click(ObjHome.come_on_in_lnk());
		}else{
			setupAfterTest();
		    Assert.fail("Unable to find expected element :" + ObjHome.come_on_in_lnk() + "");	
		}
	}	
	
	public void verify_comeonin_page_header(String expected_value)throws TestExecutionException{
		boolean pg_ttle = isDisplayed(ObjComeon.msg_comeonin_lbl());
		if (pg_ttle == true){
			String actual_value = get_text(ObjComeon.msg_comeonin_lbl());
			verify_text(expected_value, actual_value);
		}else{
			setupAfterTest();
		    Assert.fail("Unable to find expected value from :" + ObjComeon.msg_comeonin_lbl() + "object");	
		}
	}
	
	public void wait_for(int tm){
		try {
			Thread.sleep(tm);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
