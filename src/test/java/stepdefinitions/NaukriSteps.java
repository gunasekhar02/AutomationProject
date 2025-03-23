package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.*;


public class NaukriSteps {
    WebDriver driver = BaseTest.getDriver(); // Using Singleton WebDriver

    @Given("I navigate to the Naukri login page")
    public void navigateToNaukri() {
        driver.get("https://www.naukri.com/nlogin/login");
    }

    @When("I enter username and password")
    public void loginToNaukri() {
        driver.findElement(By.id("usernameField")).sendKeys("xyx@gmail.com");
        driver.findElement(By.id("passwordField")).sendKeys("kghfvskjkvh");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

    @When("I update the location preference")
    public void updateLocation() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement viewProfileButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='View profile']")));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProfileButton);
    	viewProfileButton.click();

    	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement editButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//em[contains(@class, 'edit')]")));
    	editButton.click();


    	WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Find the "Current location" label and scroll into view
        WebElement currentLocationLabel = wait3.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='lbl required-field' and text()='Current location']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", currentLocationLabel);

        // Wait for the location input field
     // Wait for the location input field
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement locationInput = wait4.until(ExpectedConditions.elementToBeClickable(By.id("locationSugg")));

        // Clear existing text and update location
        locationInput.clear();
        locationInput.sendKeys("Chennai");
        
        // Wait for the auto-suggestion dropdown to appear
        WebElement firstSuggestion = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='Sdrop']/li[1]")));

        // Scroll into view (optional)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstSuggestion);

        // Click the first suggestion
        firstSuggestion.click();

        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(100));

        // Wait until the "Save" button is present
        WebElement saveButton = wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("saveBasicDetailsBtn")));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButton);

        // Wait until the button is clickable
        wait5.until(ExpectedConditions.elementToBeClickable(saveButton));

        // Click the Save button
        saveButton.click();
    }

    @When("I Scroll till i find Upload Container")
    public void ScrollToResume() {
    	// Wait for the upload container to be present
        WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement uploadContainer = wait8.until(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='uploadContainer']"))
        );

        // Scroll to the element using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", uploadContainer);
        
        
        
        WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement updateResumeButton = wait9.until(ExpectedConditions.elementToBeClickable(
        	    By.xpath("//input[normalize-space(@value)='Update resume']")
        	));
        
            // Make the file input element visible (if hidden)
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].style.display = 'block';", updateResumeButton);

            // Provide the path to the file you want to upload
            String filePath = "C:\\Users\\gunas\\Downloads\\Vyhsnavi_resume.pdf";

            // Use sendKeys() to upload the file
            updateResumeButton.sendKeys(filePath);
        
        	        
    }
    
 
    
    @Then("My profile should be updated successfully")
    public void profileUpdated() {
        System.out.println("Profile updated successfully!");
    }
}

