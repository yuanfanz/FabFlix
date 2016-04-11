package com.fablix.moviedb.view;

import java.util.List;
import java.util.Set;

import com.fablix.moviedb.model.Customers;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;
import com.fablix.moviedb.model.Table;

public class Print {
	
	public static void printMovies(List<Movies> l){

		if(l.size() == 0){
			System.out.println("Movies not found");
		}else
		{
		System.out.println("Related movies found: ");
		System.out.println("");

		for(int i = 0; i < l.size(); ++i){
			System.out.println("Id = " + l.get(i).getId());
			System.out.println("Title = " + l.get(i).getTitle());
			System.out.println("Year = " + l.get(i).getYear());
			System.out.println("Director = " + l.get(i).getDirector());
			System.out.println("Banner_url = " + l.get(i).getBanner_url());
			System.out.println("Trailer_url = " + l.get(i).getTrailer_url());
			System.out.println("");
		 }
	}
}

	public static void printAddedStars(Stars s){
		System.out.println("The following Star added: ");
		System.out.println("");
		System.out.println("Id = " + s.getId());
		System.out.println("Name = " + s.getFirst_name() + s.getLast_name());
		System.out.println("DOB = " + s.getDob());
		System.out.println("photoURL = " + s.getPhoto_url());
		System.out.println("");
	}
	
	public static void printAddedCustomers(Customers c){

		System.out.println("The following Customer added: ");
		System.out.println("");
		System.out.println("Id = " + c.getId());
		System.out.println("Name = " + c.getFirst_name() + c.getLast_name());
		System.out.println("Cc_id = " + c.getCc_id());
		System.out.println("Address = " + c.getAddress());
		System.out.println("Email = " + c.getEmail());
		System.out.println("");
	}
	
	public static void printTables(List<Table> l){
		if(l.size() == 0){
			System.out.println("Tables not found");
		} else {
			System.out.println("All Tables: ");
		    System.out.println("");
		    for(int i = 0; i < l.size(); ++i){
		    	System.out.println("Name = " + l.get(i).getName());
			    Set<String> keys = l.get(i).getAttri_type().keySet();
			    for(String key: keys) {
			    	System.out.println("Attribute = " + key + "  Type = " + l.get(i).getAttri_type().get(key));
			    	}
			    System.out.println("");
			}
		}
	}
}
