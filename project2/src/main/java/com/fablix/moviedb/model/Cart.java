package com.fablix.moviedb.model;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Set;

public class Cart {
	
	//array of movies bought
	private HashMap<Movies, Integer> movies;
	
	//total money of movies
	private Double totalPrice;
		
    public Cart(){
    		movies = new HashMap<Movies, Integer>();
    		totalPrice = 0.0;
    }
	
	public HashMap<Movies, Integer> getMovies() {
		return movies;
	}

	public void setMovies(HashMap<Movies, Integer> movies) {
		this.movies = movies;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	//add movie into cart
	public boolean changeQuantity(Movies item, int number){
		
		if(movies.containsKey(item)){
			int num = movies.get(item) + number;
			if(num < 0){
				num = 0;
			}
			if(num > 100){
				num = 100;
			}
			movies.put(item, num);
		} else {
			if(number < 0){
				number = 0;
			}
			if(number > 100){
				number = 100;
			}
			movies.put(item, number);
		}
		
		calTotalPrice(); //calculate the total price each operate
		return true;	
	}
	
	//delete movies from cart
	public boolean removeMoviesFromCart(Movies item){
		movies.remove(item);
		calTotalPrice();
		return true;
	}
	
	public boolean change(Movies item, int number){
		
		if(movies.containsKey(item)){
			movies.remove(item);
			if(number < 0){
				number = 0;
			}
			if(number > 100){
				number = 100;
			}
			movies.put(item, number);
		} else {
			if(number < 0){
				number = 0;
			}
			if(number > 100){
				number = 100;
			}
			movies.put(item, number);
		}
		return true;
	}
		
	//get total price of the cart
	public double calTotalPrice(){
		double sum = 0.0;

		this.setTotalPrice(sum);
		return this.getTotalPrice();
	}
}
