package ObjRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPageObj {

	WebDriver driver;
	WebDriverWait wait;
	public ShopPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Navigation elements
	@FindBy(id = "nav-cart") WebElement cartLink;
	
	//Navigate to Cart Page
	public void navigateToCart() {
		cartLink.click();
	}
	
	//Select shop items using provided data
	public void selectShopItems(String[][] dataArray) {
		
		for (int index = 0; index < dataArray.length; index++) {
			String itemName = dataArray[index][0];
			int itemCount = Integer.parseInt(dataArray[index][1]);
			By buyItem = By.xpath("//div/h4[text()='"+itemName+"']/following-sibling::p/a[text()='Buy']");
			for (int count = 1; count <= itemCount; count++) {
				driver.findElement(buyItem).click();
			}			
		}	
		
	}	
}
