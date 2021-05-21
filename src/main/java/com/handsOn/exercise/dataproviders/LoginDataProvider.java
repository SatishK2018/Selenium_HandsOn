package com.handsOn.exercise.dataproviders;

import org.testng.annotations.DataProvider;
import com.handsOn.exercise.readexcel.ReadExcel;

public class LoginDataProvider {

	@DataProvider(name = "xls-inputs_negative case")
	public Object[][] getXLSDataForNegativeCases(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("./resources/Test Data.xls", "negative case"); 
	}
	
	@DataProvider(name = "xls-inputs_positive case")
	public Object[][] getXLSDataForPositiveCases(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("./resources/Test Data.xls", "positive case"); 
	}
}
