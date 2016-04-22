package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.CreditCards;

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
		//rs.close();
		//prepstmt.close();
		dbConnection.rsstmtClose(rs, prepstmt);
		//connection.close();
		
		return ret;

	}
public Boolean confirmCre(Map<String, Object> params) throws SQLException{
		
		//get the connection from dbConnection
		//Connection conn = dbConnection.getConnection();
	    //
		boolean result = false;
		String sql = "select * from creditcards where id=?";
		
		if(!params.containsKey("firstname") || !params.containsKey("lastname") || !params.containsKey("ccnum") || !params.containsKey("extime") )
		{
			return result;
		}
		else
		{
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			
			prepstmt.setString(1, (String) params.get("ccnum"));
			ResultSet rs = prepstmt.executeQuery();
			
			
			if(rs.next())
			{
				    CreditCards cc = new CreditCards();
					cc.setId(rs.getString(1));
					cc.setFirst_name(rs.getString(2));
					cc.setLast_name(rs.getString(3));
					cc.setExpiration(rs.getDate(4));
					String expirationDate = cc.getExpiration().toString();
					if(cc.getFirst_name().equals(params.get("firstname")) && cc.getLast_name().equals(params.get("lastname")) && expirationDate.equals(params.get("extime")))
					{
						result = true;
					}
			}
		
			else
			{
				
				return result;
			}
			//rs.close();
			//prepstmt.close();
			dbConnection.rsstmtClose(rs, prepstmt);
			//connection.close();
			
			return result;
		}
		
		
		

	}

}
