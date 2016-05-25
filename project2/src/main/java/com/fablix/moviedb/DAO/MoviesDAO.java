package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.MovieInfo;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Pager;
import com.fablix.moviedb.model.Stars;

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
    

public Pager<MovieInfo> browseMoviesByTitle(String prename, String orderWord, String ascDesc, int pageSize, int currentPage) throws SQLException{
		
		Pager<MovieInfo> moviePage = new Pager<MovieInfo>();
    	List<MovieInfo> movies = new ArrayList<MovieInfo>();
    	ResultSet result;
    	ResultSet count = null;
    	int fromIndex = pageSize * (currentPage - 1);
    	
    	
    	String sql = "SELECT * FROM movies ";
    	String countsql = "Select count(movies.id) as totalRecord from movies ";
		
    	if(prename != null){
		String browseString = sql + "WHERE movies.title like '" + prename + "%' "
				+ "ORDER by movies." + orderWord+ " " + ascDesc + " limit "+ fromIndex+","+ pageSize;
		String browseStringCount = countsql + "WHERE movies.title like '" + prename + "%';";
		PreparedStatement browseMovies = connection.prepareStatement(browseString);
		PreparedStatement browseMoviesCount = connection.prepareStatement(browseStringCount);
		
		result = browseMovies.executeQuery();
		count = browseMoviesCount.executeQuery();
				
		while (result.next()){
			Movies movie = new Movies();
			
			MovieInfo m = new MovieInfo();
			movie.setId(result.getInt(1));
			
			ResultSet resultg;
			String sqlg = "SELECT genres.* FROM genres " +
					"JOIN genres_in_movies ON genres_in_movies.genre_id = genres.id " +

					"where genres_in_movies.movie_id = ?;" ;
					
			PreparedStatement browseMoviesg = connection.prepareStatement(sqlg);
			browseMoviesg.setInt(1, movie.getId());
			resultg = browseMoviesg.executeQuery();
			List<Genres> lgenre = new ArrayList<Genres>();
			
			ResultSet results;
			String sqls = "SELECT * from stars "
					+ "JOIN stars_in_movies ON stars.id = stars_in_movies.star_id "
				+ "WHERE stars_in_movies.movie_id = ?;";
			PreparedStatement browseMoviess = connection.prepareStatement(sqls);
			browseMoviess.setInt(1, movie.getId());
			results = browseMoviess.executeQuery();
			List<Stars> lstar = new ArrayList<Stars>();
			
			movie.setTitle(result.getString(2));
			movie.setYear(result.getInt(3));
			movie.setDirector(result.getString(4));
			movie.setBanner_url(result.getString(5));
			movie.setTrailer_url(result.getString(6));
			m.setMovie(movie);
			while(results.next())
			{
				Stars star = new Stars();
				star.setId(results.getInt(1));
				star.setFirst_name(results.getString(2));
				star.setLast_name(results.getString(3));
				star.setDob(results.getDate(4));
				star.setPhoto_url(results.getString(5));
				lstar.add(star);
			}
			m.setStars(lstar);
			while(resultg.next())
			{
				Genres genre = new Genres();
				genre.setId(resultg.getInt(1));
				genre.setName(resultg.getString(2));
				lgenre.add(genre);
			}
			m.setGenres(lgenre);
			
			
			movies.add(m);			
		}

    	

		dbConnection.rsstmtClose(result, browseMovies);
		}
    	count.next();
    	int totalRecord = count.getInt("totalRecord");
    	moviePage = new Pager<MovieInfo>(pageSize, currentPage, totalRecord, movies);
		return moviePage;
	    
	}

public Pager<MovieInfo> getMovieByGenre(String genre, String orderWord, String ascDesc, int pageSize, int currentPage) throws SQLException{
	Pager<MovieInfo> moviePage = new Pager<MovieInfo>();
	List<MovieInfo> movies = new ArrayList<MovieInfo>();
	ResultSet result;
	ResultSet count = null;
	int fromIndex = pageSize * (currentPage - 1);
	
	String sql = "SELECT movies.* FROM movies "
			+ "JOIN genres_in_movies ON genres_in_movies.movie_id = movies.id "

			+ "JOIN genres ON genres.id = genres_in_movies.genre_id ";

	String countsql = "SELECT count(movies.id) as totalRecord FROM movies "
			+ "JOIN genres_in_movies ON genres_in_movies.movie_id = movies.id "
			+ "JOIN genres ON genres.id = genres_in_movies.genre_id ";
	if(genre != null){
		String browseString = sql + "WHERE genres.name = '" + genre + "' "
				+ "ORDER by movies." + orderWord+ " " + ascDesc + " limit "+ fromIndex+","+ pageSize + ";";
		String browseStringCount = countsql + "WHERE genres.name = '" + genre + "';";
		PreparedStatement browseMovies = connection.prepareStatement(browseString);
		PreparedStatement browseMoviesCount = connection.prepareStatement(browseStringCount);
		result = browseMovies.executeQuery();
		count = browseMoviesCount.executeQuery();
	
	
	// set the movies' attribute , and add it to the List;
	while (result.next()){
		Movies movie = new Movies();
		
		MovieInfo m = new MovieInfo();
		movie.setId(result.getInt(1));
		
		ResultSet resultg;
		String sqlg = "SELECT genres.* FROM genres " +
				"JOIN genres_in_movies ON genres_in_movies.genre_id = genres.id " +
				"where genres_in_movies.movie_id = ?;";
		PreparedStatement browseMoviesg = connection.prepareStatement(sqlg);
		browseMoviesg.setInt(1, movie.getId());
		resultg = browseMoviesg.executeQuery();
		List<Genres> lgenre = new ArrayList<Genres>();
		
		ResultSet results;
		String sqls = "SELECT * from stars "
				+ "JOIN stars_in_movies ON stars.id = stars_in_movies.star_id "
			+ "WHERE stars_in_movies.movie_id = ?;";
		PreparedStatement browseMoviess = connection.prepareStatement(sqls);
		browseMoviess.setInt(1, movie.getId());
		results = browseMoviess.executeQuery();
		List<Stars> lstar = new ArrayList<Stars>();
		
		movie.setTitle(result.getString(2));
		movie.setYear(result.getInt(3));
		movie.setDirector(result.getString(4));
		movie.setBanner_url(result.getString(5));
		movie.setTrailer_url(result.getString(6));
		m.setMovie(movie);
		while(results.next())
		{
			Stars star = new Stars();
			star.setId(results.getInt(1));
			star.setFirst_name(results.getString(2));
			star.setLast_name(results.getString(3));
			star.setDob(results.getDate(4));
			star.setPhoto_url(results.getString(5));
			lstar.add(star);
		}
		m.setStars(lstar);
		while(resultg.next())
		{
			Genres genre1 = new Genres();
			genre1.setId(resultg.getInt(1));
			genre1.setName(resultg.getString(2));
			lgenre.add(genre1);
		}
		m.setGenres(lgenre);
		
		
		movies.add(m);			

	}
	dbConnection.rsstmtClose(result, browseMovies);
	}
	count.next();
	int totalRecord = count.getInt("totalRecord");
	moviePage = new Pager<MovieInfo>(pageSize, currentPage, totalRecord, movies);
	return moviePage;
	//return movies;

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
    	
    	String countSqlHead = "Select count(movies.id) as totalRecord from movies ";
    	String sqlHead = "SELECT movies.* FROM movies ";
    	String sqlJoinStar = "JOIN stars_in_movies ON movies.id = stars_in_movies.movie_id "
				+ "JOIN stars ON stars.id = stars_in_movies.star_id ";
    	String sqlOrder = "ORDER by movies." + orderWord+ " " + ascDesc;
    	
    	StringBuffer sql = new StringBuffer();
    	StringBuffer countSql = new StringBuffer();
    	sql.append(sqlHead);
    	countSql.append(countSqlHead);
    	
    	if (params!=null && params.size()!=0){
    		
    		if (params.containsKey("first_name")||params.containsKey("last_name")){
    			
    			sql.append(sqlJoinStar + "where 1=1 ");
    			countSql.append(sqlJoinStar + "where 1=1 ");
    			
    			for (String name:params.keySet()){
    				if ( name.equals("first_name")||name.equals("last_name") ) {
    					
    					if (subMatch){
    						sql.append("and "+ "stars." + name +" Like '%"+ params.get(name)+"%' ");
    						countSql.append("and "+ "stars." + name +" Like '%"+ params.get(name)+"%' ");
    					}else{
    						sql.append("and "+ "stars." + name +" Like '"+ params.get(name)+"' ");
    						countSql.append("and "+ "stars." + name +" Like '"+ params.get(name)+"' ");
    					}
    				
    				}else{
    					if (subMatch){
    						sql.append("and "+ "movies." + name +" Like '%"+ params.get(name)+"%' ");
    						countSql.append("and "+ "movies." + name +" Like '%"+ params.get(name)+"%' ");
    					}else{
    						sql.append("and "+ "movies." + name +" Like '"+ params.get(name)+"' ");
    						countSql.append("and "+ "movies." + name +" Like '"+ params.get(name)+"' ");
    					}
    					
    				}
    			}
    		
    		}else{
    			sql.append("where 1=1 ");
    			countSql.append("where 1=1 ");
    			
    			for (String name: params.keySet()){
    				if (subMatch){
    					sql.append("and "+ name + " Like '%" + params.get(name) + "%' ");
    					countSql.append("and "+ name + " Like '%" + params.get(name) + "%' ");
    				}else{
    					sql.append("and "+ name + " Like '" + params.get(name) + "' ");
    					countSql.append("and "+ name + " Like '" + params.get(name) + "' ");
    					
    				}
    				
    			}
    		}
    		//sort order
    		sql.append(sqlOrder);
    		
    		//pagination
        	int fromIndex = pageSize * (currentPage - 1);
        	sql.append(" limit "+fromIndex+","+pageSize);
    	
    	}else{
    		return moviePage;
    	}
    	

    	
    	PreparedStatement ptmt = connection.prepareStatement(sql.toString());
    	PreparedStatement countPtmt = connection.prepareStatement(countSql.toString());
    	ResultSet result = ptmt.executeQuery();
    	ResultSet countResult = countPtmt.executeQuery();
    	
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
    	//get the total record
    	countResult.next();
    	int totalRecord = countResult.getInt("totalRecord");
    	
    	//create the moviePage
    	moviePage = new Pager<Movies>(pageSize, currentPage, totalRecord, movies);
    	
    	dbConnection.rsstmtClose(result, ptmt);
    	dbConnection.rsstmtClose(countResult, countPtmt);
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
    
    public Movies getMovieByTitle(String title) throws SQLException{
		
		Movies m = new Movies();
		m.setId(-1);
		String sql = "select * from movies where title=?";
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		
		prepstmt.setString(1, title);
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
    
public ArrayList<String> browseMovies(String title) throws SQLException{
		ArrayList<String> list = new ArrayList<String>();
		
		String sql = "SELECT title FROM movies WHERE title LIKE ?";
		String data;
		
		// create a Statement from the connection
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		
		prepstmt.setString(1,"%" + title + "% ");
		ResultSet rs = prepstmt.executeQuery();
		
		while(rs.next()){
    		
			data = rs.getString(1);
            list.add(data);
			
		}
		
		//rs.close();
		//prepstmt.close();
		dbConnection.rsstmtClose(rs, prepstmt);
		//connection.close();
		
		return list;
    }
    
    public void addMovie(Movies m, String first_name, String last_name, String genre) throws SQLException{
		
		String title = m.getTitle();
		String director = m.getDirector();
		int year = m.getYear();
		String banner_url = m.getBanner_url();
		String trailer_url = m.getTrailer_url();
		
		//insert new star
		String insertStar = "INSERT INTO movies (title, director, year, banner_url, trailer_url) VALUES (?,?,?,?,?)";
	    PreparedStatement insert = connection.prepareStatement(insertStar);
			 
	    insert.setString(1, title);
	    insert.setString(2, director);
		insert.setInt(3, year);
		insert.setString(4, banner_url);
		insert.setString(5, trailer_url);
		
		System.out.println("Number of movies added: " + insert.executeUpdate());
		//dbConnection.connectionClose(null, insert, connection);
		StarsDAO sdao = new StarsDAO();
		sdao.updateStar(first_name, last_name, m);
		
		dbConnection.rsstmtClose(null, insert);
	}
}
