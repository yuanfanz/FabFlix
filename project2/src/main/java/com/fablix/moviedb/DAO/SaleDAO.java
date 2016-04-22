package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.*;

public class SaleDAO {
	
	private Connection connection = dbConnection.getConnection();

	/**
	 * 
	 * @param s
	 * @throws Exception
	 */
	public void addSale(Sale s) throws SQLException{
		
		java.sql.Date saleDate = null;
		
		Date jdob = new Date();
		saleDate = new java.sql.Date(jdob.getTime());
	
		//insert query
		String sql = "insert into sales (customer_id, movie_id, sale_date) values(?, ?, ?)";
				
		// create a Statement from the connection
		PreparedStatement insert = connection.prepareStatement(sql);
		 
	    insert.setInt(1, s.getCustomerId());
	    insert.setObject(2, s.getMovieId());
		insert.setDate(3, saleDate);
		
		System.out.println(s.getCustomerId());
		ArrayList<Integer> m = s.getMovieId();
		for(int i = 0; i < m.size(); ++i){
			System.out.println(m.get(i));
		}
		System.out.println(saleDate);
		System.out.println("***************************************");
		System.out.println("Written into Sale. Success.");
		
		//dbConnection.connectionClose(null, insert, connection);
		dbConnection.rsstmtClose(null, insert);
	}
}
