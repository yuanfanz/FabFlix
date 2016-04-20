package com.fablix.moviedb.DAO;

//import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.MovieInfo;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Pager;
import com.fablix.moviedb.model.Stars;

public class MoiveInfoDAO {
	//private Connection connection = dbConnection.getConnection();
	private MoviesDAO mDAO = new MoviesDAO();
	private GenresDAO gDAO = new GenresDAO();
	private StarsDAO sDAO = new StarsDAO();
	
	public List<MovieInfo> searchMovieInfo( Map<String, Object> params, String orderWord, String ascDesc, 
											boolean subMatch, int pageSize, int currentPage ) throws SQLException{
		
		Pager<Movies> movies = mDAO.searchMovies(params, orderWord, ascDesc, subMatch, pageSize, currentPage);
		List<MovieInfo> movieInfos = new ArrayList<MovieInfo>();
		
		if (movies.getDataList()!=null){
			for (Movies m : movies.getDataList()){
				List<Genres> genres = gDAO.getGenreByMovie(m);
				List<Stars> stars = sDAO.getStarsByMovie(m);			
				MovieInfo  movieInfo = new MovieInfo(m,stars,genres);
				movieInfos.add(movieInfo);
			}
		}

		//Pager<MovieInfo> movieInfos= new Pager<MovieInfo>();
		return movieInfos;
	}

}
