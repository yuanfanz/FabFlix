package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Customers;

public class CustomerDAO {
	//get the connection from dbConnection
	private Connection connection = dbConnection.getConnection();
	
	public boolean addCustomer(Customers c) throws SQLException
	{
		boolean ret;
		//get the connection from dbConnection
		//Connection conn = dbConnection.getConnection();
				
		//insert query
		String check="select cc_id from customers where cc_id=?";
		String sql = "insert into customers (first_name, last_name, cc_id, address, email, password) values(?, ?, ?, ?, ?, ?)";
				
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		PreparedStatement checkstmt = connection.prepareStatement(check);
				
		checkstmt.setString(1, c.getCc_id());
		ResultSet checkrs = checkstmt.executeQuery();
				
		int i=0;
		while(checkrs.next())
		{
			i++;
		}
		if(i != 0)
		{
			System.out.print("Duplicate Creditcard number! Add customer fail!");
			return false;
		}
		else
		{
			//compile sql query
			//customer credit card check
			CreditcardsDAO cre = new CreditcardsDAO();
			
			if(cre.findcre(c.getCc_id()))
			{
				//add customer
				//single customer name
				prepstmt.setString(1, c.getFirst_name());
				prepstmt.setString(2, c.getLast_name());
				prepstmt.setString(3, c.getCc_id());
				prepstmt.setString(4, c.getAddress());
				prepstmt.setString(5, c.getEmail());
				prepstmt.setString(6, c.getPassword());
				//run sql query
				prepstmt.executeUpdate();
				System.out.println("Add customer successfully! ");
				ret = true;
			}
			else
			{
				System.out.println("Invalid Credicard number! Add customer fail!");
				ret = false;
			}
					
		}
		checkrs.close();
		checkstmt.close();
		prepstmt.close();
		connection.close();
		return ret;
		
	}
	
	
	public Boolean delCustomer(String id) throws SQLException
	{
		boolean ret;
		
		//print customer
		String sqlprint = " select * from customers where cc_id=?";
		PreparedStatement stmt = connection.prepareStatement(sqlprint);
		
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		
		
		int i=0;
		while(rs.next())
		{
			i++;
		}
		ResultSet rss = stmt.executeQuery();
		if(i != 0)
		{
			
			rss.next();
			System.out.println("The deleted customer information: ");
			System.out.println("first_name = "+ rss.getString("first_name"));
			System.out.println("last_name = "+rss.getString("last_name"));
			System.out.println("cc_id = "+rss.getString("cc_id"));
			System.out.println("address = "+rss.getString("address"));
			System.out.println("email = "+rss.getString("email"));
			System.out.println("password = "+rss.getString("password"));
			
			//insert query
			String sql = " delete from customers where cc_id=?";
					
			// create a Statement from the connection
			PreparedStatement prepstmt = connection.prepareStatement(sql);
					
			//compile sql query
			prepstmt.setString(1, id);

			//run sql query
			prepstmt.executeUpdate();
			ret = true;
		}
		else
		{
			System.out.print("No customer exist!");
			ret = false;
		}	
		
		rss.close();
		rs.close();
		stmt.close();
		connection.close();
		return ret;
	}
	
}
