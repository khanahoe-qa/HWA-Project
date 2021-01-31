package com.qa.hwa.acceptanceTesting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hwa.HwaProjectApplication;
import com.qa.hwa.acceptanceTesting.pages.HwaIndexPage;

@SpringBootTest(classes = HwaProjectApplication.class)
@ActiveProfiles(profiles = "reg")
public class HwaAcceptanceTesting {

	private static WebDriver driver;
	
	@BeforeAll
	public static void init() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	@Disabled
	public void testCreateRider() {

		HwaIndexPage website = PageFactory.initElements(driver, HwaIndexPage.class);
		website.navRiders();
		Boolean success = website.ridersPage.createRider(driver);
		assertTrue(success);
		
	}
	
	@Test
	@Disabled
	public void testUpdateRider() {
		
		HwaIndexPage website = PageFactory.initElements(driver, HwaIndexPage.class);
		website.navRiders();
		Boolean success = website.ridersPage.updateRider(driver);
		assertTrue(success);
		
	}
	
	@AfterAll
	public static void cleanUp() {
		driver.quit();
		System.out.println("The Selenium driver has been cleaned up");
	}
}
