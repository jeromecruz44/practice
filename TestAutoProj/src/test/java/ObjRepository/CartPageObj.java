package ObjRepository;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPageObj {

	WebDriver driver;
	WebDriverWait wait;
	public CartPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Cart table row elements
	By cartItemRows = By.cssSelector("tr.cart-item");	
	
	//Validate items in the cart using provided data
	public void validateCartItems(String[][] dataArray) {

		int cartItemCnt = driver.findElements(cartItemRows).size();
		for (int index = 1; index <= cartItemCnt; index++) {
			By cartStaticDetails = By.xpath("//tr[contains(@class, 'cart-item')]["+index+"]/td");
			List<WebElement> arrCartItemDetails = driver.findElements(cartStaticDetails);
			String cartItemName = arrCartItemDetails.get(0).getText();
			BigDecimal cartItemPrice = new BigDecimal(arrCartItemDetails.get(1).getText().replace("$", ""));
			BigDecimal cartItemTotal = new BigDecimal(arrCartItemDetails.get(3).getText().replace("$", ""));
			By cartItemCountInput = By.xpath("//tr[contains(@class, 'cart-item')]["+index+"]/td[3]/input");
			String cartItemCount = driver.findElement(cartItemCountInput).getAttribute("value");
			Assert.assertEquals(cartItemName, dataArray[index-1][0]);
			Assert.assertEquals(cartItemCount, dataArray[index-1][1]);
			Assert.assertEquals(cartItemPrice.multiply(new BigDecimal(cartItemCount)), cartItemTotal);
		}			
	}
}
