package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fablix.moviedb.db.dbConnection;

public class CreditcardsDAO{
	
	//get the connection from dbConnection
	private Connection connection = dbConnection.getConnection();

	/**
	 * 
	 * @param cre
	 * @return
	 * @throws Exception
	 */
	public Boolean findcre(String cre) throws SQLException{
		
		//get the connection from dbConnection
		//Connection conn = dbConnection.getConnection();
		
		String sql = "select id from creditcards where id=?";
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		
		prepstmt.setString(1, cre);
		ResultSet rs = prepstmt.executeQuery();
		boolean ret;
		int i = 0;
		while(rs.next())
		{
			i++;
		}
		
		if(i != 0)
		{
			ret = true;
		}
		else
		{
			ret = false;
		}
		rs.close();
		prepstmt.close();
		connection.close();
		
		return ret;

	}

}
