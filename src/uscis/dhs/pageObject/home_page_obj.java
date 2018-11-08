package uscis.dhs.pageObject;

import org.openqa.selenium.By;

public class home_page_obj {
	
	public By page_title_lbl(){
		By page_title_lbl = By.cssSelector("body > h1");
		return page_title_lbl;
	}
	
	public By welcome_msg_lbl(){
		By welcome_msg_lbl = By.cssSelector("body > p");
		return welcome_msg_lbl;
	}
	
	public By come_on_in_lnk(){
		By come_on_in_lnk = By.cssSelector("body > div > p > a");
		return come_on_in_lnk;
	}
	
}

