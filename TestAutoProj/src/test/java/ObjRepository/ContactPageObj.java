package ObjRepository;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactPageObj {

	WebDriver driver;
	WebDriverWait wait;
	public ContactPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Field elements
	@FindBy(id="forename") WebElement forenameFld;
	@FindBy(id="surname") WebElement surnameFld;
	@FindBy(id="email") WebElement emailFld;
	@FindBy(id="telephone") WebElement telephoneFld;
	@FindBy(id="message") WebElement messageFld;
	@FindBy(linkText = "Submit") WebElement submitBtn;
	
	//Page message elements
	@FindBy(css = "div.alert-error") WebElement alertErrMessage;
	@FindBy(css = "div.alert-success") WebElement alertSuccess;
	@FindBy(id = "forename-err") WebElement forenameFldError;
	@FindBy(id = "email-err") WebElement emailFldError;
	@FindBy(id = "telephone-err") WebElement telephoneFldError;
	@FindBy(id = "message-err") WebElement messageFldError;
	By alertErrCheck = By.cssSelector("div.alert-error");
	By forenameErrCheck = By.id("forename-err");
	By emailErrCheck = By.id("email-err");
	By messageErrCheck = By.id("message-err");
	
	//Set Contact Page inputs using provided data
	public void setContactInput(ArrayList<String> dataArray) {
		forenameFld.sendKeys(dataArray.get(0));
		surnameFld.sendKeys(dataArray.get(1));
		emailFld.sendKeys(dataArray.get(2));
		telephoneFld.sendKeys(dataArray.get(3));
		messageFld.sendKeys(dataArray.get(4));		
	}
	
	//Submit the message
	public void submitFeedback() {
		submitBtn.click();
	}
	
	//Validate mandatory field error message to applicable fields
	public void validateMandatoryFldErrMessage() {
		Assert.assertEquals(alertErrMessage.getText(), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
		Assert.assertEquals(forenameFldError.getText(), "Forename is required");
		Assert.assertEquals(emailFldError.getText(), "Email is required");
		Assert.assertEquals(messageFldError.getText(), "Message is required");
	}
	
	//Validate mandatory field error message absence after providing valid inputs
	public void validateErrMessageAbsence() {
		Assert.assertFalse(isElementPresent(alertErrCheck));
		Assert.assertFalse(isElementPresent(forenameErrCheck));
		Assert.assertFalse(isElementPresent(emailErrCheck));
		Assert.assertFalse(isElementPresent(messageErrCheck));
	}
	
	//Custom method to check element presence
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}
	
	//Validate successful submission of message
	public void validateSuccessSubmission(String userName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(alertSuccess));
		Assert.assertEquals(alertSuccess.getText(), "Thanks "+userName+", we appreciate your feedback.");
	}
	
	//Validate invalid input error to applicable fields
	public void validateInvalidInputErrMessage() {
		Assert.assertEquals(alertErrMessage.getText(), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
		Assert.assertEquals(emailFldError.getText(), "Please enter a valid email");
		Assert.assertEquals(telephoneFldError.getText(), "Please enter a valid telephone number");
	}
}
