package TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ObjRepository.CartPageObj;
import ObjRepository.HomePageObj;
import ObjRepository.ShopPageObj;
import Resources.Initialization;
import Resources.CommonFunctions;

public class ShopPageTest extends Initialization {
	
	@BeforeMethod
	public void browseJupiterToys() throws IOException {
		
		driver = initializeDriver();
		driver.get(envProperties.getProperty("url"));
		
		//Navigate to Shop Page
		HomePageObj objHomePg = new HomePageObj(driver);
		objHomePg.navigateToShop();
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.close();
		
	}
	
	@Test 
	public void TC4_shopping_cart_item_validation() throws IOException {
		
		//Select and add item to cart
		ShopPageObj objShopPg = new ShopPageObj(driver);
		String dataPath = envProperties.getProperty("datapath")+"testdataShopPage.xlsx";
		CommonFunctions cf = new CommonFunctions();
		String[][] dataArray = cf.getShopItemDataValue(dataPath);
		objShopPg.selectShopItems(dataArray);
		objShopPg.navigateToCart();
		
		//Validate cart item details
		CartPageObj objCartPg = new CartPageObj(driver);
		objCartPg.validateCartItems(dataArray);
	
	}
}
