package com.fablix.moviedb.action;

import java.sql.SQLException;

import com.fablix.moviedb.DAO.CustomerDAO;
import com.fablix.moviedb.model.Customers;

public class CustomerAction
{
	public void add(Customers customer) throws SQLException
	{
		CustomerDAO cud = new CustomerDAO();
		cud.addCustomer(customer);
	}
	
	public void del(String id) throws SQLException
	{
		CustomerDAO cud = new CustomerDAO();
		cud.delCustomer(id);
	}

}
