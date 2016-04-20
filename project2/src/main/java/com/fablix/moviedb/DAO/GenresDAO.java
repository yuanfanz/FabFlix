package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.Movies;


public class GenresDAO {
	
	private Connection connection = dbConnection.getConnection();
	
	public List<Genres> getGenreByMovie( Movies movie ) throws SQLException{
		
		List<Genres> genres = new ArrayList<Genres>();
		
		String sql = "select genres.* from genres ";
		String sqlJoin = "JOIN genres_in_movies ON genres.id = genres_in_movies.genre_id ";
		
		int movieId = movie.getId();
		sql = sql + sqlJoin + "where genres_in_movies.movie_id = ?";
		
		PreparedStatement ptmt = connection.prepareStatement(sql.toString());
		ptmt.setInt(1, movieId);
    	ResultSet result = ptmt.executeQuery();
    	
    	while (result.next()){
			Genres g = new Genres();
			
			g.setId(result.getInt(1));
			g.setName(result.getString(2));

			genres.add(g);			
		}
    	
		dbConnection.rsstmtClose(result, ptmt);
		return genres;
	}
}
