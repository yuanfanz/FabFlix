package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fablix.moviedb.db.dbConnection;
//import com.fablix.moviedb.model.MovieInfo;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.MovieInfo;

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
	
	public boolean insertGenre(HashSet<Genres> genresList) throws SQLException{
		boolean flag = false;
    	PreparedStatement prepstmt = null;
    	int totalCount = 0;
    	
    	if (genresList!=null && !genresList.isEmpty()){
        	for (Genres g: genresList){
        		String sql = "insert into genres (name) select * from (select ?) as tmp "
        				     + "where not exists(select name from genres where name = ?) limit 1" ;
        		
        		// create a Statement from the connection
        		prepstmt = connection.prepareStatement(sql);
        		
        		prepstmt.setString(1, g.getName());
        		prepstmt.setString(2, g.getName());
				
        		totalCount += prepstmt.executeUpdate();
				
        	}
        	
        	flag = true;
    	
    	}else{
    		flag = false;
    	}
    	System.out.println("Add " + totalCount + " genre successfully!");
		prepstmt.close();
		
		return flag;
	}
    public int getIdByName(String name) throws SQLException{
    	//Movies m = new Movies();
    	int id = -1;
    	String sql = "select id from genres where name = ?";
    	PreparedStatement prepstmt = connection.prepareStatement(sql);
    	prepstmt.setString(1, name);
		ResultSet rs = prepstmt.executeQuery();
		
		while(rs.next()){
			id = rs.getInt(1);
		}
		
		prepstmt.close();
		rs.close();
    	return id;
    }

}







