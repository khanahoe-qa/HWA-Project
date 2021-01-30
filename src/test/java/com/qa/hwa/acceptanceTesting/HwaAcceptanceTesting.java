package com.qa.hwa.acceptanceTesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HwaAcceptanceTesting {

	private static WebDriver driver;
	
	@BeforeAll
	public static void init() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testCreateRider() {
		
	}
}
