package com.fablix.moviedb.action;

import java.sql.SQLException;
import java.util.List;

import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.model.Movies;

public class MoviesAction {
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return List of Movies, if cannot find the record return null.
	 * @throws Exception
	 */
	public List<Movies> getMoviesByStarName(String firstName, String lastName) throws SQLException{
		
		MoviesDAO movieDao = new MoviesDAO();
		return movieDao.getMovieByStarName(firstName, lastName);
		
	}
	
	/**
	 * 
	 * @param id
	 * @return List of Movies, if cannot find the record return null.
	 * @throws Exception
	 */
	public List<Movies> getMoviesByStarId(int id) throws SQLException{
		
		MoviesDAO movieDao = new MoviesDAO();
		return movieDao.getMoviesByStarId(id);
		
	}

}
