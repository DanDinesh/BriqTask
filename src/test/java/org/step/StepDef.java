package org.step;

import java.time.Duration;

import org.base.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends BaseClass{
	

	@Given("I navigate to www.ebay.com")
	public void i_navigate_to_www_ebay_com() {
	    browserLaunch();
	    urlLaunch("https://www.ebay.com");
	    browserMax();
	    impliCitWait();
	}
	@When("I navigate to Search by category > Electronics > Cell Phones & accessories")
	public void i_navigate_to_search_by_category_electronics_cell_phones_accessories() {
		driver.findElement(By.xpath("//button[contains(text(),'Shop by category')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Cell phones & accessories')]")).click();
	}
	@When("I click Cell Phones & Smartphones in the left-hand side navigation section")
	public void i_click_cell_phones_smartphones_in_the_left_hand_side_navigation_section() {
		driver.findElement(By.xpath("//a[contains(text(),'Cell Phones & Smartphones')]")).click();
	}
	@When("I scroll down to {string}")
	public void i_scroll_down_to(String string) {
		WebElement allListings = driver.findElement(By.xpath("//h2[contains(text(),'All Listings')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", allListings);
	}
	@When("I click {string}")
	public void i_click(String string) {
	    driver.findElement(By.xpath("//button[contains(text(),'All Filters')]")).click();
	}
	@When("I select screen size as {string}")
	public void i_select_screen_size_as(String string) throws InterruptedException {
		
	    driver.findElement(By.xpath("//div[@role='tab' and (contains(@id,'Screen'))]//span")).click();
	    driver.findElement(By.xpath("//span[contains(text(),'5.5')]//parent::label//preceding-sibling::span")).click();
	}
	@When("I select price range as {string}")
	public void i_select_price_range_as(String string) {
		driver.findElement(By.xpath("//div[@role='tab' and (contains(@id,'price'))]//span")).click();
	    driver.findElement(By.xpath("//input[contains(@class,'from') and (contains(@class,'range'))]")).sendKeys("100");
	    driver.findElement(By.xpath("//input[contains(@class,'to') and (contains(@class,'range'))]")).sendKeys("200");
	    
	}
	@When("I select item location as {string}")
	public void i_select_item_location_as(String string) {
		driver.findElement(By.xpath("//div[@role='tab' and (contains(@id,'location'))]//span")).click();
		driver.findElement(By.xpath("//span[text()='US Only']//parent::label//preceding-sibling::span")).click();
	}
	
	@When("I now click {string}")
	public void i_now_click(String string) {
		driver.findElement(By.xpath("//button[text()='Apply']")).click();;
	}

	@Then("I should see the filter tags applied")
	public void i_should_see_the_filter_tags_applied() {
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'filters applied')]")).isDisplayed());
	}
	
	//=======================================================================================

	@Given("I am on the eBay homepage")
	public void i_am_on_the_e_bay_homepage() {
	    driver.get("https://www.ebay.com");
	}
	@When("I type {string} in the search bar")
	public void i_type_in_the_search_bar(String string) {
	    driver.findElement(By.xpath("//input[contains(@class,'input')]")).sendKeys("Macbook");
	    
	}
	@When("I select the {string} category")
	public void i_select_the_category(String string) {
	    WebElement category = driver.findElement(By.xpath("//select[contains(@id,'cat')]"));
	    Select s = new Select(category);
	    s.selectByVisibleText("Computers/Tablets & Networking");
	}
	@When("I click the {string} button")
	public void i_click_the_button(String string) {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	@Then("the page should load completely")
	public void the_page_should_load_completely() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='srp-river-results']")));
	}
	@Then("the first result name should match with the search string")
	public void the_first_result_name_should_match_with_the_search_string() {
		String firstResultName = driver.findElements(By.xpath("//*[@id='srp-river-results']//div[@class='s-item__title']//span//span")).get(0).getText();
        Assert.assertTrue(firstResultName.contains("MacBook"));
        
        
	}







}
