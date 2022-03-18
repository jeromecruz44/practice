package ObjRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObj {

	WebDriver driver;
	
	public HomePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Navigation elements
	@FindBy(linkText = "Contact") WebElement contactLink;
	@FindBy(linkText = "Shop") WebElement shopLink;
	
	//Navigate to Contact Page
	public void navigateToContact() {
		contactLink.click();
	}
	
	//Navigate to Shop Page
	public void navigateToShop() {
		shopLink.click();
	}
	
}
