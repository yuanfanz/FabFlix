package com.fablix.moviedb.model;

import java.util.List;

public class MovieInfo {
	private Movies movie;
	private List<Stars> stars;
	private List<Genres> genres;
	
	public MovieInfo(){
		super();
	}
	
	public MovieInfo(Movies movie, List<Stars> star, List<Genres> genre){
		this.movie = movie;
		this.stars =  star;
		this.genres = genre;		
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public List<Stars> getStars() {
		return stars;
	}

	public void setStars(List<Stars> stars) {
		this.stars = stars;
	}

	public List<Genres> getGenres() {
		return genres;
	}

	public void setGenres(List<Genres> genres) {
		this.genres = genres;
	}
	
	
	
}
