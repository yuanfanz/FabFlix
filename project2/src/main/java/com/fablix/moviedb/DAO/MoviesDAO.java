package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Pager;

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
		dbConnection.rsstmtClose(result, selectStars);

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
		dbConnection.rsstmtClose(result, selectStars);
		return movies;
		
	}

    /**
     *     
     * @param params
     * @return
     * @throws SQLException
     */
    public Pager<Movies> searchMovies(Map<String, Object> params, String orderWord, String ascDesc, boolean subMatch, int pageSize, int currentPage) throws SQLException{
    	List<Movies> movies = new ArrayList<Movies>();
    	Pager<Movies> moviePage = new Pager<Movies>();
    	
    	String sqlHead = "SELECT movies.* FROM movies ";
    	String sqlJoinStar = "JOIN stars_in_movies ON movies.id = stars_in_movies.movie_id "
				+ "JOIN stars ON stars.id = stars_in_movies.star_id ";
    	String sqlOrder = "ORDER by movies." + orderWord+ " " + ascDesc;
    	
    	StringBuffer sql = new StringBuffer();
    	sql.append(sqlHead);
    	
    	if (params!=null && params.size()!=0){
    		
    		if (params.containsKey("first_name")||params.containsKey("last_name")){
    			sql.append(sqlJoinStar + "where 1=1 ");
    			for (String name:params.keySet()){
    				if ( name.equals("first_name")||name.equals("last_name") ) {
    					
    					if (subMatch){
    						sql.append("and "+ "stars." + name +" Like '%"+ params.get(name)+"%' ");
    					}else{
    						sql.append("and "+ "stars." + name +" Like '"+ params.get(name)+"' ");
    					}
    				
    				}else{
    					if (subMatch){
    						sql.append("and "+ "movies." + name +" Like '%"+ params.get(name)+"%' ");
    					}else{
    						sql.append("and "+ "movies." + name +" Like '"+ params.get(name)+"' ");
    					}
    					
    				}
    			}
    		
    		}else{
    			sql.append("where 1=1 ");
    			for (String name: params.keySet()){
    				if (subMatch){
    					sql.append("and "+ name + " Like '%" + params.get(name) + "%' ");
    				}else{
    					sql.append("and "+ name + " Like '" + params.get(name) + "' ");
    				}
    				
    			}
    		}
    			sql.append(sqlOrder);
    	
    	}else{
    		return moviePage;
    	}
    	
    	
    	PreparedStatement ptmt = connection.prepareStatement(sql.toString());
    	ResultSet result = ptmt.executeQuery();
    	
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
    	moviePage = new Pager<Movies>(pageSize, currentPage, movies);
    	dbConnection.rsstmtClose(result, ptmt);
    	return moviePage;      	
    }
    
//    public Pager<Movies> browsAllMovies(String title, String genre, int pageSize, int currentPage, String order) throws SQLException{
//    	String sql = "select * from moives ";
//    	String ascOrder = "order by ? asc";
//    	String descOrder = "order by ? desc";
//    }
    
    public Movies getMovieById(int id) throws SQLException{
    		
    		Movies m = new Movies();
    		String sql = "select * from movies where id=?";
    		
    		// create a Statement from the connection
    		PreparedStatement prepstmt = connection.prepareStatement(sql);
    		
    		prepstmt.setInt(1, id);
    		ResultSet rs = prepstmt.executeQuery();
    		
    		while(rs.next()){
        		
        		m.setId(rs.getInt(1));
    			m.setTitle(rs.getString(2));
    			m.setYear(rs.getInt(3));
    			m.setDirector(rs.getString(4));
    			m.setBanner_url(rs.getString(5));
    			m.setTrailer_url(rs.getString(6));
    		}
    		
    		//rs.close();
    		//prepstmt.close();
    		dbConnection.rsstmtClose(rs, prepstmt);
    		//connection.close();
    		
    		return m;
    }
}
