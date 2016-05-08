package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.Movies;

public class GenreInMvDAO {
	private Connection connection = dbConnection.getConnection();
	private MoviesDAO mDAO = new MoviesDAO(); 
	private GenresDAO gDAO = new GenresDAO();
	
	public void insertGenInMv(HashMap<Movies,HashSet<Genres>> genreInMovie) throws SQLException{
		System.out.println("Updating the genres in movies...");
		connection.setAutoCommit(false);
		
		String sql = "insert into genres_in_movies (genre_id, movie_id) values(?,?);";
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		if (!genreInMovie.isEmpty()){
			for (Movies m : genreInMovie.keySet()){
				int movieid = mDAO.getIdByTitle(m.getTitle());
				
				for (Genres g : genreInMovie.get(m)){
					int genreid = gDAO.getIdByName(g.getName());
					
					if (movieid != -1 && genreid != -1){
						prepstmt.setInt(1, genreid);
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
