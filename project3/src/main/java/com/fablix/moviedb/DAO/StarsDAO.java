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
import java.util.HashSet;
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
	
    public boolean insertStars(HashSet<Stars> starsList) throws SQLException{
    	
    	boolean flag = false;
    	PreparedStatement prepstmt = null;
    	int totalCount = 0;
    	
    	if (starsList!=null && !starsList.isEmpty()){
        	for (Stars s: starsList){
        		String sql = "replace into stars (first_name, last_name, dob, photo_url) values(?, ?, ?, ?);";
        		
        		// create a Statement from the connection
        		prepstmt = connection.prepareStatement(sql);
        		
        		if (s.getFirst_name()==null || s.getLast_name()==null || (s.getFirst_name().length()==0 && s.getLast_name().length()==0)){
        			continue;
        		}
        		
        		
        		prepstmt.setString(1, s.getFirst_name());
				prepstmt.setString(2, s.getLast_name());
				if (s.getDob()!=null){
					prepstmt.setDate(3, new java.sql.Date(s.getDob().getTime()));
				}else{
					prepstmt.setNull(3, java.sql.Types.INTEGER);
				}
				
				prepstmt.setString(4, "");
				
				prepstmt.executeUpdate();
				totalCount++;
				
        	}
        	
        	flag = true;
    	
    	}else{
    		flag = false;
    	}
    	System.out.println("Add " + totalCount + " stars successfully!");
		prepstmt.close();

		
		return flag;
    }
	
	 public int getIdByStarName(String firstName, String lastName) throws SQLException{
	    	//Movies m = new Movies();
	    	int id = -1;
	    	String sql = "select id from stars where first_name = ? and last_name = ?";
	    	PreparedStatement prepstmt = connection.prepareStatement(sql);
	    	prepstmt.setString(1, firstName);
	    	prepstmt.setString(2, lastName);
			
	    	ResultSet rs = prepstmt.executeQuery();
			
			while(rs.next()){
				id = rs.getInt(1);
			}
			
			prepstmt.close();
			rs.close();

	    	return id;
	    }
}

