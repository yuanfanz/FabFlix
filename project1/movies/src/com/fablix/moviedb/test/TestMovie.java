package com.fablix.moviedb.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fablix.moviedb.action.MoviesAction;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.view.Print;

public class TestMovie 
{
	public static void main(String[] args)
	{
		
		
		//test by first_name and last_name
		/*try
		{
			List<Movies> li = movie.getMoviesByStarName("Michael", "Douglas");
			Print p = new Print();
			p.printMovies(li);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}*/
		
		//test by lats_name
		/*try
		{
			List<Movies> li = movie.getMoviesByStarName("", "Douglas");
			Print p = new Print();
			p.printMovies(li);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}*/
		
		//test by star id
//		try
//		{
//			List<Movies> li = movie.getMoviesByStarId(490009);
//			Print.printMovies(li);
//			
//		}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("An error occured when connecting to database");
//		}
		
		//test by first_name
		try
		{
			MoviesAction movie = new MoviesAction();
			List<Movies> li = movie.getMoviesByStarName("Michael", "");
			Print.printMovies(li);
			
			//MoviesAction movie2 = new MoviesAction();
			List<Movies> li2 = movie.getMoviesByStarId(490009);
			//Print p = new Print();
			
			Print.printMovies(li2);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

}
