package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public boolean updateGenre(String genre, Movies m) throws SQLException {
		
		List<Genres> result = getGenreByMovie(m);
		boolean update = true;
		for(Genres g : result){
			if(g.getName() == genre){
				update = false;
			}
		}
		if(update == false){
			return true;
		} else {
			Genres updates = new Genres();
			updates.setName(genre);
			addGenre(updates);
			int genre_id = getGenreByName(genre).getId();
			int movie_id = m.getId();
			String sql = "INSERT INTO genres_in_movies (genre_id, movie_id) VALUES (?,?)";
			
			// create a Statement from the connection
			PreparedStatement prepstmt = connection.prepareStatement(sql);

			prepstmt.setInt(1, genre_id);
			prepstmt.setInt(2, movie_id);
			prepstmt.executeQuery();
		}
		return true;
	}
	
	public void addGenre(Genres genre) throws SQLException {
		
		String name = genre.getName();
		
		String insertGenre = "INSERT INTO genres (name) VALUES (?)";
	    PreparedStatement insert = connection.prepareStatement(insertGenre);
			 
	    insert.setString(1, name);
		
		System.out.println("Number of genre added: " + insert.executeUpdate());
		//dbConnection.connectionClose(null, insert, connection);
		dbConnection.rsstmtClose(null, insert);
	}
	
	public Genres getGenreByName(String name) throws SQLException {
		
		Genres genre = new Genres();
		String sql = "select * from genres where name=?";
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);

		prepstmt.setString(1, name);
		ResultSet rs = prepstmt.executeQuery();
		
		int i=0;
		while(rs.next())
		{
			i++;
		}
		ResultSet rss = prepstmt.executeQuery();
		if(i != 0)
		{
			rss.next();
			genre.setId(rss.getInt(1));
			genre.setName(rss.getString(2));
		}	
		
		dbConnection.rsstmtClose(rss, prepstmt);
		dbConnection.rsstmtClose(rs, null);
		//connection.close();
		return genre;
	}
}
