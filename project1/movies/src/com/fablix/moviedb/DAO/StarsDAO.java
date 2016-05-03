package com.fablix.moviedb.DAO;

//import com.fablix.moviedb.action.StarsAction;
import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;
//import com.fablix.moviedb.view.Print;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;

public class StarsDAO {
	
	private Connection connection = dbConnection.getConnection();

	/**
	 * 
	 * @param s
	 * @throws Exception
	 */
	public void addStar(Stars s) throws SQLException{
			
		String first_name = s.getFirst_name();
		String last_name = s.getLast_name();
		if(first_name.length() != 0 && last_name.length() == 0){
			last_name = first_name;
			first_name = "";
		}
		
		java.sql.Date dob = null;
		if(s.getDob() != null){
			Date sdob = s.getDob();
			dob = new java.sql.Date(sdob.getTime());
		}
		String photo_url = s.getPhoto_url();
		
		//insert new star
		String insertStar = "INSERT INTO stars (first_name, last_name, dob, photo_url) VALUES (?,?,?,?)";
	    PreparedStatement insert = connection.prepareStatement(insertStar);
			 
	    insert.setString(1, first_name);
	    insert.setString(2, last_name);
		insert.setDate(3, dob);
		insert.setString(4, photo_url);
		
		System.out.println("Number of star details added: " + insert.executeUpdate());
		dbConnection.connectionClose(null, insert, connection);
	}
	

	
		
}

