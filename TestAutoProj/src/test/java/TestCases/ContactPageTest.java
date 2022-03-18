package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ObjRepository.ContactPageObj;
import ObjRepository.HomePageObj;
import Resources.Initialization;
import Resources.CommonFunctions;

public class ContactPageTest extends Initialization {
	
	@BeforeMethod
	public void browseJupiterToys() throws IOException {
		
		driver = initializeDriver();
		driver.get(envProperties.getProperty("url"));
		
		//Navigate to Contact Page
		HomePageObj objHomePg = new HomePageObj(driver);
		objHomePg.navigateToContact();
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		driver.close();
	
	}
	
	@Test 
	public void TC1_mandatory_field_error_validation() throws IOException {
		
		//Validate display of mandatory field error messages
		ContactPageObj objContactPg = new ContactPageObj(driver);
		objContactPg.submitFeedback();
		objContactPg.validateMandatoryFldErrMessage();
		
		//Set Contact Page field values
		String[] dataPointer = {envProperties.getProperty("datapath")+"testdataContactPage.xlsx", "TC1"};		
		CommonFunctions cf = new CommonFunctions();
		ArrayList <String> dataArray = cf.getContactDataValue(dataPointer);
		objContactPg.setContactInput(dataArray);
		
		//Validate absence of mandatory field error messages
		objContactPg.validateErrMessageAbsence();
	
	}
	
	@Test
	public void TC2_successful_feedback_submission_validation() throws IOException {
		
		//Set Contact Page field values then submit
		ContactPageObj objContactPg = new ContactPageObj(driver);
		String[] dataPointer = {envProperties.getProperty("datapath")+"testdataContactPage.xlsx", "TC2"};		
		CommonFunctions cf = new CommonFunctions();
		ArrayList <String> dataArray = cf.getContactDataValue(dataPointer);
		objContactPg.setContactInput(dataArray);
		objContactPg.submitFeedback();
		
		//Validate successful submission of message
		objContactPg.validateSuccessSubmission(dataArray.get(0));
		
	}
	
	@Test
	public void TC3_invalid_field_input_validation() throws IOException {
		
		//Set Contact Page field values then submit
		ContactPageObj objContactPg = new ContactPageObj(driver);
		String[] dataPointer = {envProperties.getProperty("datapath")+"testdataContactPage.xlsx", "TC3"};		
		CommonFunctions cf = new CommonFunctions();
		ArrayList <String> dataArray = cf.getContactDataValue(dataPointer);
		objContactPg.setContactInput(dataArray);
		
		//Validate invalid field input error message
		objContactPg.validateInvalidInputErrMessage();
		
	}

}
