package com.handsOn.exercise.dataproviders;

//import java.util.List;
import org.testng.annotations.DataProvider;
import com.handsOn.exercise.readexcel.ReadExcel;

public class PFCalculatorDataProvider {

	@DataProvider(name = "xls-PF_CalculatorData")
	public Object[][] getXLSDataPFCalculator(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("./resources/PF Calculator.xls", "Sheet1"); 
	}
	

}
