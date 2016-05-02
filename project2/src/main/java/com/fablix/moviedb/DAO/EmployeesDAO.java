package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fablix.moviedb.db.dbConnection;

public class EmployeesDAO {
	private Connection connection = dbConnection.getConnection();

	
	public boolean isAuthenticate(String email, String password) throws SQLException{
		ResultSet result;
		PreparedStatement stmt;
		boolean isAuthnct = false;
		String sql = "select password from employees where email = ?";
		stmt = connection.prepareStatement(sql);
		
		stmt.setString(1,email);
		result = stmt.executeQuery();
		if (result.next()){
			if (result.getString(1).equals(password)){
				
				isAuthnct = true;
			}else{
				
				isAuthnct = false;
			}
		}else{
			
			isAuthnct = false;
		}
		
		dbConnection.rsstmtClose(result, stmt);
		return isAuthnct;
	}

}
