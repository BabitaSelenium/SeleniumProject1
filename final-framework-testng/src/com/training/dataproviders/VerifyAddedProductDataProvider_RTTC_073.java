package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.RTTC_073_DBBean;
import com.training.dao.RetailDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class VerifyAddedProductDataProvider_RTTC_073 {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<RTTC_073_DBBean> list = new RetailDAO().getProducts(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(RTTC_073_DBBean temp : list){
			Object[]  obj = new Object[8]; 
			obj[0] = temp.getpname(); 
			obj[1] = temp.getmTitle(); 
			obj[2] = temp.getmodel(); 
			obj[3] = temp.gettprice();
			obj[4] = temp.getcategory(); 
			obj[5] = temp.getqty();
			obj[6] = temp.getuprice(); 
			obj[7] = temp.getpts();
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
	//	String fileName ="C:/Users/Naveen/Desktop/Testing.xlsx"; 
		String fileName ="C:/Users/BABITAFERNANDEZ/Testing.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/Naveen/Desktop/Testing.xls", "Sheet1"); 
	}
}
