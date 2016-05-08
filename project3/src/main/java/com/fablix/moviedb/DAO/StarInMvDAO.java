package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;

public class StarInMvDAO {
	private Connection connection = dbConnection.getConnection();
	private StarsDAO sDAO = new StarsDAO();
	private MoviesDAO mDAO = new MoviesDAO();
	
	public void insertStrInMv(HashMap<Movies,HashSet<Stars>> starInMovie) throws SQLException{
		System.out.println("Updating the stars in movies...");
		connection.setAutoCommit(false);
		String sql = "insert into stars_in_movies (star_id, movie_id) values(?,?);";
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		
		if (!starInMovie.isEmpty()){
			for (Movies m : starInMovie.keySet()){
				int movieid = mDAO.getIdByTitle(m.getTitle());
				
				for (Stars s : starInMovie.get(m)){
					int starid = sDAO.getIdByStarName(s.getFirst_name(), s.getLast_name());
					
					if (movieid != -1 && starid != -1){
						prepstmt.setInt(1, starid);
						prepstmt.setInt(2, movieid);
						
						prepstmt.executeUpdate();
					}
				
				}
			}
		}
		connection.commit();
		prepstmt.close();
		
	}
}
