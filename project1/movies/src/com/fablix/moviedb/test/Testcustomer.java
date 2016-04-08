package com.fablix.moviedb.test;

import java.sql.SQLException;

import com.fablix.moviedb.DAO.CustomerDAO;
import com.fablix.moviedb.action.CustomerAction;
import com.fablix.moviedb.model.Customers;

public class Testcustomer 
{
	
	
	public static void main(String[] args)
	{
		
		//test normal
		/*try
		{
			//class Customers = class.forname("Customers");
			Customers co = new Customers();
			
			co.setFirst_name("Nancy");
			co.setLast_name("Tom");
			co.setCc_id("941");
			co.setAddress("Irvine");
			co.setEmail("123@uci.edu");
			co.setPassword("1234");
		
			cu.add(co);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}*/
		
		//test when cc_id is not in the creditcard table
		/*try
		{
			Customers co = new Customers();
			
			co.setFirst_name("Nancy");
			co.setLast_name("Tom");
			co.setCc_id("900");
			co.setAddress("Irvine");
			co.setEmail("123@uci.edu");
			co.setPassword("1234");
		
			cu.add(co);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}*/
		
		//test when insert only one name
		try
		{
			CustomerAction cu = new CustomerAction();
			Customers co = new Customers();
			
			cu.del("9912051");
			//System.out.println("2222222222222222222222222222");
			co.setFirst_name("Nancy");
			co.setLast_name("");
			co.setCc_id("9912051");
			co.setAddress("Irvine");
			co.setEmail("123@uci.edu");
			co.setPassword("1234");
			
			cu.add(co);
			
			//CustomerAction cu = new CustomerAction();
			Customers co2 = new Customers();
			
			//cu.del("123");
			co2.setFirst_name("BBBB");
			co2.setLast_name("dsds");
			co2.setCc_id("9912051");
			co2.setAddress("Irvine");
			co2.setEmail("123@uci.edu");
			co2.setPassword("1234");
		
			cu.add(co2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}
		
		//test delCustomer
		/*try
		{
			cu.del("9912051");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}*/
		
		//test when two customers insert
		/*try
		{
			//class Customers = class.forname("Customers");
			Customers co = new Customers();
			
			co.setFirst_name("Nancy");
			co.setLast_name("Tom");
			co.setCc_id("941");
			co.setAddress("Irvine");
			co.setEmail("123@uci.edu");
			co.setPassword("1234");
		
			cu.add(co);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}
		try
		{
			//class Customers = class.forname("Customers");
			Customers co = new Customers();
			
			co.setFirst_name("Nanc");
			co.setLast_name("To");
			co.setCc_id("941");
			co.setAddress("Irvine");
			co.setEmail("123@uci.edu");
			co.setPassword("1234");
		
			cu.add(co);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}
		*/
		
	}

}
