package com.qa.hwa.acceptanceTesting.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HwaRidersPage {
	
	@FindBy(id = "create_name")
	private WebElement createName;
	
	@FindBy(id = "create_dob")
	private WebElement createDob;
	
	@FindBy(id = "create_sex")
	private WebElement createSex;
	
	@FindBy(id = "create_button")
	private WebElement createButton;
	
	@FindBy(id = "update_id")
	private WebElement updateId;
	
	@FindBy(id = "update_name")
	private WebElement updateName;
	
	@FindBy(id = "update_dob")
	private WebElement updateDob;
	
	@FindBy(id = "update_sex")
	private WebElement updateSex;
	
	@FindBy(id = "update_button")
	private WebElement updateButton;
	
	@FindBy(id = "delete_id")
	private WebElement deleteId;
	
	@FindBy(id = "delete_button")
	private WebElement deleteButton;
	
	@FindBy(id = "read_id")
	private WebElement readId;
	
	@FindBy(id = "read_one_button")
	private WebElement readOneButton;
	
	@FindBy(id = "read_all_button")
	private WebElement readAllButton;

	@FindBy(id = "add_rider_id")
	private WebElement addRiderId;
	
	@FindBy(id = "add_race_id")
	private WebElement addRaceId;
	
	@FindBy(id = "add_rider_race_button")
	private WebElement addButton;
	
	@FindBy(id = "remove_rider_race_button")
	private WebElement removeButton;
	
	@FindBy(id = "output")
	private WebElement output;
	
	@FindBy(className = "information")
	private WebElement info;
	
	public HwaRidersPage() {
		
	}
	
	public Boolean createRider(WebDriver driver) {
		
		createName.sendKeys("Phil Space");
		createDob.sendKeys("01-01-1990");
		Select selectSex = new Select(createSex);
		selectSex.selectByValue("m");
		System.out.println(createButton.getAttribute("value"));
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(createButton));
		System.out.println(this.createButton.getText());
		this.createButton.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(info));
		WebElement target = driver.findElement(By.className("information"));
		if(target.findElement(By.tagName("h4")).getText().equals("Rider created")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean updateRider(WebDriver driver) {
		
		this.updateId.sendKeys("1");
		this.updateName.sendKeys("Polly Filla");
		this.updateDob.sendKeys("02021992");
		Select selectSex = new Select(this.updateSex);
		selectSex.selectByValue("f");
		this.updateButton.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(info));
		WebElement target = driver.findElement(By.className("information"));
		if(target.findElement(By.tagName("h4")).getText().equals("Rider created")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void deleteRider(WebDriver driver, Long toDeleteId) {

	}
	
	public void readOneRider(WebDriver driver, Long toReadId) {

	}
	
	public void readAllRiders(WebDriver driver) {

	}
	
	public void addRider(WebDriver driver, Long riderToAddId, Long raceToAddToId) {

	}
	
	public void deleteRider(WebDriver driver, Long riderToRemoveId, Long raceToRemoveFromId) {

	}
}
