package com.fablix.moviedb.model;

import java.util.Date;

public class Stars {
	private int id;
	private String first_name;
	private String last_name;
	private Date dob;
	private String photo_url; 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getFirst_name()+this.getLast_name()).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj){
			return true;
		}
		
		if(obj instanceof Stars){
			Stars s = (Stars)obj;
			if( this.getFirst_name().equals(s.getFirst_name()) && this.getLast_name().equals(s.getLast_name()) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
}
