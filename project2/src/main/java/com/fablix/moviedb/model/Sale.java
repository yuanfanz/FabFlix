package com.fablix.moviedb.model;

import java.util.ArrayList;
import java.util.Date;

public class Sale {
	private int id;
	private int customerId;
	private ArrayList<Integer> movieId;
	private Date saleDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public ArrayList<Integer> getMovieId() {
		return movieId;
	}
	public void setMovieId(ArrayList<Integer> movieId) {
		this.movieId = movieId;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	
}
