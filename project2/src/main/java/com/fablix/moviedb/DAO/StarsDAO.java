package com.fablix.moviedb.DAO;

//import com.fablix.moviedb.action.StarsAction;
import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;
import com.fablix.moviedb.model.Stars;
//import com.fablix.moviedb.view.Print;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		//dbConnection.connectionClose(null, insert, connection);
		dbConnection.rsstmtClose(null, insert);
	}
	
	
	public List<Stars> getStarsByMovie(Movies movie) throws SQLException{
		List<Stars> stars = new ArrayList<Stars>();
		
		String sql = "select stars.* from stars ";
		String sqlJoin = "JOIN stars_in_movies ON stars.id = stars_in_movies.star_id ";
		
		int movieId = movie.getId();
		sql = sql + sqlJoin + "where stars_in_movies.movie_id = ?";
		
		PreparedStatement ptmt = connection.prepareStatement(sql.toString());
		ptmt.setInt(1, movieId);
    	ResultSet result = ptmt.executeQuery();
    	
    	while (result.next()){
			Stars s = new Stars();
			
			s.setId(result.getInt(1));
			s.setFirst_name(result.getString(2));
			s.setLast_name(result.getString(3));
			s.setDob(result.getDate(4));
			s.setPhoto_url(result.getString(5));

			stars.add(s);			
		}
    	
		dbConnection.rsstmtClose(result, ptmt);
		return stars;
	}
	
	public Stars getStarsById(int id) throws SQLException{

		Stars s = new Stars();
		String sql = "select * from stars where id=?";
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		


		
		prepstmt.setInt(1, id);
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
			s.setId(rss.getInt(1));
			s.setFirst_name(rss.getString(2));
			s.setLast_name(rss.getString(3));
			s.setDob(rss.getDate(4));
			s.setPhoto_url(rss.getString(5));
		}	
		//rs.close();
		//prepstmt.close();
		dbConnection.rsstmtClose(rss, prepstmt);
		dbConnection.rsstmtClose(rs, null);
		//connection.close();
		return s;
	}
	
	public Stars getStarsByName(String first, String last) throws SQLException{

		Stars s = new Stars();
		s.setId(-1);
		String sql = "select * from stars where first_name=? AND last_name=?";
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);

		prepstmt.setString(1, first);
		prepstmt.setString(2, last);
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
			s.setId(rss.getInt(1));
			s.setFirst_name(rss.getString(2));
			s.setLast_name(rss.getString(3));
			s.setDob(rss.getDate(4));
			s.setPhoto_url(rss.getString(5));
		}	
		//rs.close();
		//prepstmt.close();
		dbConnection.rsstmtClose(rss, prepstmt);
		dbConnection.rsstmtClose(rs, null);
		//connection.close();
		return s;
	}
	
	public boolean updateStar(String first_name, String last_name, Movies m) throws SQLException
	{
		List<Stars> result = getStarsByMovie(m);
		boolean update = true;
		for(Stars s : result)
		{
			String sfirstname,slastname;
			sfirstname = s.getFirst_name();
			slastname = s.getLast_name();
			if(first_name.equals(sfirstname) && last_name.equals(slastname))
			{
				update = false;
				break;
			}
		}
		if(update == false)
		{
			return false;
		}else{
			Stars updates = new Stars();
			updates.setFirst_name(first_name);
			updates.setLast_name(last_name);
			addStar(updates);
			int star_id = getStarsByName(first_name,last_name).getId();
			int movie_id = m.getId();
			String sql = "INSERT INTO stars_in_movies (star_id, movie_id) VALUES (?,?)";
			
			// create a Statement from the connection
			PreparedStatement prepstmt = connection.prepareStatement(sql);

			prepstmt.setInt(1, star_id);
			prepstmt.setInt(2, movie_id);
			prepstmt.executeQuery();

		}
		return true;
	}
}

