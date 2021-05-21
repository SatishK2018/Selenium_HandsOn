package com.handsOn.exercise.dataproviders;

//import java.util.List;
import org.testng.annotations.DataProvider;
import com.handsOn.exercise.readexcel.ReadExcel;

public class GratuityCalculatorDataProvider {

	@DataProvider(name = "xls-GratuityCalculatorData_PositiveCases")
	public Object[][] getXLSDataPositiveCasesGratuityCalculator(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("./resources/Gratuity Calculator.xls", "Positive Cases"); 
	}
	
	@DataProvider(name = "xls-GratuityCalculatorData_NegativeCases")
	public Object[][] getXLSDataNegativeCasesGratuityCalculator(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("./resources/Gratuity Calculator.xls", "Negative Cases"); 
	}
	
}
