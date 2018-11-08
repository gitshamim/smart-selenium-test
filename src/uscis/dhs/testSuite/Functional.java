package uscis.dhs.testSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.*;
import org.apache.*;
import org.apache.log4j.Logger;

import uscis.dhs.database.databaseConnection;
import uscis.dhs.sqlQuery.sqlStatement;
import uscis.dhs.support.BaseAPPMethods;
import uscis.dhs.support.BaseDBMethods;
import uscis.dhs.support.BaseTestMethods;
import uscis.dhs.support.TestExecutionException;

public class Functional {

	static BaseAPPMethods Test = new BaseAPPMethods();
	static BaseDBMethods DB = new BaseDBMethods();
	static sqlStatement sqlQRY = new sqlStatement();
	
	public static String conf_url = Test.configProp().getProperty("url");
	public static String browser_slct = Test.configProp().getProperty("browser");
	public static String u_id = Test.configProp().getProperty("user_id");
	public static String pwd = Test.configProp().getProperty("pass");
	
	public static String env_tier = Test.configProp().getProperty("tier");
	
    public static String seleniumBrowser = browser_slct;
    public static String seleniumUrl = conf_url;
    
    public static String exp_pg_hdr = Test.testData().getProperty("home_page_hdr");
    public static String exp_wlc_msg = Test.testData().getProperty("welcome_msg");
    public static String exp_comeonin_lnk = Test.testData().getProperty("link_txt");
    public static String exp_error_pg_hdr = Test.testData().getProperty("error_pg_title");
    public static String exp_comeonin_pg_hdr = Test.testData().getProperty("comeonin_pg_hdr");
    public static Properties url_list = Test.testData();
    
    public static String appRelease = "Functional Test";
    private static Logger logger = Logger.getLogger("TestSuite");

	@Test
	public void verifySmartReportUpAndRunning() throws Exception{
		String testName = "001_verify_smart_app_up_and_running";
		String testDesc = "This test will ensure smart app up and running";
		try{
			logger.info("---------------Begin Test case: " + testName + "--------------");
			logger.info("Test Description: " + testDesc);
			logger.info("Test: " + appRelease);
			logger.info("Tier: " + env_tier);
			DB.query_select_by_table_name(sqlQRY.selectSQLByTableName("test_table", ""));
			Test.lunchSite(seleniumBrowser, seleniumUrl);
			Test.verify_home_page_title(exp_pg_hdr);
			Test.setupAfterTest();
			logger.info("---------------End of Test "+ testName + "---------------------");
		}catch(Exception e){
			logger.error("Test "+testName+" failed. Error: -"+e.getMessage());
			logger.info("---------------End of Test "+ testName + "---------------------");
			Test.setupAfterTest();
		}
	}
	
	
}