package com.qa.hwa.acceptanceTesting.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class HwaRidersPage {
	
	@FindAll(@FindBy(css = "#create_form > input"))
	private List<WebElement> createInputs;
	
	@FindBy(id = "create_button")
	private WebElement createButton;
	
	@FindAll(@FindBy(css = "#update_form > input"))
	private List<WebElement> updateInputs;
	
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
	
	public HwaRidersPage() {
		
	}
	
	public void createRider(WebDriver driver, List<String> toCreate) {
		int i=0;
		for(WebElement input: this.createInputs) {
			if(input.getAttribute("id") == "create_button") {
				continue;
			}
			else {
			input.sendKeys(toCreate.get(i));
			i++;
			}
		}
		this.createButton.click();
	}
	
	public void updateRider(WebDriver driver, List<String> toUpdate) {
		int i=0;
		for(WebElement input: this.updateInputs) {
			if(input.getAttribute("id") == "update_button") {
				continue;
			}
			else {
			input.sendKeys(toUpdate.get(i));
			i++;
			}
		}
		this.updateButton.click();
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
