package uscis.dhs.pageObject;

import org.openqa.selenium.By;

public class error_page_obj {
	
	public By msg_error_lbl(){
		By msg_error_lbl = By.cssSelector("body > h1");
		return msg_error_lbl;
	}
	
}
