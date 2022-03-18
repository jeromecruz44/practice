package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Initialization {

	public WebDriver driver;
	public Properties envProperties;
	public WebDriverWait wait;
	public WebDriver initializeDriver() throws IOException {
		envProperties = new Properties();
		FileInputStream file = new FileInputStream("src/test/java/Resources/data.properties");
		envProperties.load(file);
		
		String browserName = envProperties.getProperty("browser");
		if (browserName.equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", envProperties.getProperty("driverpath")+"chromedriver.exe");
			driver= new ChromeDriver();		
		}
		else if (browserName.equals("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", envProperties.getProperty("driverpath")+"geckodriver.exe");
			driver = new FirefoxDriver();	
		}
		else if (browserName.equals("EDGE")) {
			System.setProperty("webdriver.edge.driver", envProperties.getProperty("driverpath")+"msedgedriver.exe");
			driver = new EdgeDriver();
		}
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;	
	}	
}
