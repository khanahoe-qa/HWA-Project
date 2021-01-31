package com.qa.hwa.acceptanceTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HwaIndexPage {

	private final String URL = "http://localhost:8080/html/index.html";
	
	@FindBy(id = "riders")
	private WebElement riders;
	
	@FindBy(id = "races")
	private WebElement races;
	
	public HwaRidersPage ridersPage;
	public HwaRacesPage racesPage;
	
	public HwaIndexPage(WebDriver driver) {
		driver.get(URL);
		this.ridersPage = PageFactory.initElements(driver, HwaRidersPage.class);
		this.racesPage = PageFactory.initElements(driver, HwaRacesPage.class);
	}
	
	public void navRiders() {
		this.riders.click();
	}
	
	public void navRaces() {
		this.races.click();
	}
}
