package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonFunctions {

	//Function to return data input for Contact Page from an excel file
	public ArrayList<String> getContactDataValue(String[] dataPointer) throws IOException {
		
		ArrayList <String> dataArray = new ArrayList<String>();
		FileInputStream file = new FileInputStream(dataPointer[0]);
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet workSheet = workBook.getSheetAt(0);
		for (int rowNum = 1; rowNum <= workSheet.getLastRowNum(); rowNum++) {
			XSSFRow rowData = workSheet.getRow(rowNum);
			if (rowData.getCell(0).getStringCellValue().equalsIgnoreCase(dataPointer[1])) {
				for (int cellNum = 1; cellNum < rowData.getLastCellNum(); cellNum++) {
					dataArray.add(rowData.getCell(cellNum).getStringCellValue());		
				}		
			}
		}
		workBook.close();
		return dataArray;
		
	}
	
	//Function to return cart items to be selected in Shop Page from an excel file
	public String[][] getShopItemDataValue(String dataPath) throws IOException {
			
		FileInputStream file = new FileInputStream(dataPath);
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet workSheet = workBook.getSheetAt(0);
		XSSFRow rowData = workSheet.getRow(0);
		int rowCount = workSheet.getLastRowNum();
		int cellCount = rowData.getLastCellNum();
		String[][] dataArray = new String[rowCount][cellCount];
		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			rowData = workSheet.getRow(rowNum);
			for (int cellNum = 0; cellNum < cellCount; cellNum++) {
				dataArray[rowNum-1][cellNum] = rowData.getCell(cellNum).getStringCellValue();
			}	 
		}
		workBook.close();
		return dataArray;
	}
	
}
