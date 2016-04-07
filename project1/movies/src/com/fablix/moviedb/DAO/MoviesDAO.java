package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;

public class MoviesDAO {
	
	private Connection connection = dbConnection.getConnection();
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return List<Movies> , if can't find a record, @return an empty list. 
	 * @throws Exception
	 */
	public List<Movies> getMovieByStarName(String firstName, String lastName) throws SQLException{
		
		List<Movies> movies = new ArrayList<Movies>();
		ResultSet result;
		PreparedStatement selectStars;
		
		String sql = "SELECT movies.* FROM movies "
				+ "JOIN stars_in_movies ON movies.id = stars_in_movies.movie_id "
				+ "JOIN stars ON stars.id = stars_in_movies.star_id ";
		
		//if giving both first name and last name.
		if (firstName.length()!=0 && lastName.length()!=0){
			
			String selectString = sql + "WHERE stars.first_name = ? AND stars.last_name = ?";
			selectStars = connection.prepareStatement(selectString);
			
			selectStars.setString(1, firstName);
			selectStars.setString(2, lastName);
			
			result = selectStars.executeQuery();
		
		//if only giving last name.
		}else if (firstName.length()==0 && lastName.length()!=0){
			
			String selectString = sql + "WHERE stars.last_name = ?";
			selectStars = connection.prepareStatement(selectString);
			
			selectStars.setString(1, lastName);
			
			result = selectStars.executeQuery();
		
		//if only giving first name.
		}else if (firstName.length()!=0 && lastName.length()==0){
			
			String selectString = sql + "WHERE stars.first_name = ?";
			selectStars = connection.prepareStatement(selectString);
			
			selectStars.setString(1, firstName);
			
			result = selectStars.executeQuery();
		
		}else{
			
			return movies;
		
		}
		
		// set the movies' attribute , and add it to the List;
		while (result.next()){
			Movies m = new Movies();
			
			m.setId(result.getInt(1));
			m.setTitle(result.getString(2));
			m.setYear(result.getInt(3));
			m.setDirector(result.getString(4));
			m.setBanner_url(result.getString(5));
			m.setTrailer_url(result.getString(6));
			
			movies.add(m);
		}
		dbConnection.connectionClose(result, selectStars, connection);
		return movies;

	}
	
	/**
	 * 
	 * @param id
	 * @return List<Movies> ,if cannot find the record, @return an empty list.
	 * @throws SQLException 
	 * @throws Exception
	 */
    public List<Movies> getMoviesByStarId(int id) throws SQLException{
		
    	List<Movies> movies = new ArrayList<Movies>();
    	ResultSet result;
    	
    	String sql = "SELECT movies.* FROM movies "
				+ "JOIN stars_in_movies ON movies.id = stars_in_movies.movie_id "
				+ "JOIN stars ON stars.id = stars_in_movies.star_id ";
		

		String selectString = sql + "WHERE stars.id = ?";
		PreparedStatement selectStars = connection.prepareStatement(selectString);
		
		selectStars.setInt(1, id);
		
		result = selectStars.executeQuery();
				
		while (result.next()){
			Movies m = new Movies();
			
			m.setId(result.getInt(1));
			m.setTitle(result.getString(2));
			m.setYear(result.getInt(3));
			m.setDirector(result.getString(4));
			m.setBanner_url(result.getString(5));
			m.setTrailer_url(result.getString(6));
			
			movies.add(m);			
		}
		dbConnection.connectionClose(result, selectStars, connection);
		return movies;
		
	}

}
