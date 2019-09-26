package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.RTTC_073_DBBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class RetailDAO {
	
	Properties properties; 
	
	public RetailDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<RTTC_073_DBBean> getProducts(){
		String sql = properties.getProperty("get.products"); 
		
		GetConnection gc  = new GetConnection(); 
		List<RTTC_073_DBBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<RTTC_073_DBBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				RTTC_073_DBBean temp = new RTTC_073_DBBean(); 
				temp.setpname(gc.rs1.getString(1));
				temp.setmTitle(gc.rs1.getString(2));
				temp.setmodel(gc.rs1.getString(3));
				temp.settprice(gc.rs1.getString(4));
				temp.setcategory(gc.rs1.getString(5));
				temp.setqty(gc.rs1.getString(6));
				temp.setuprice(gc.rs1.getString(7));
				temp.setpts(gc.rs1.getString(8));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new RetailDAO().getProducts().forEach(System.out :: println);
	}
	
	
}
