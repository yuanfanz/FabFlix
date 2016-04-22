package com.fablix.moviedb.model;

import java.util.Map;

public class Table {
	
	private String name;
	private Map<String,String> attri_type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getAttri_type() {
		return attri_type;
	}
	public void setAttri_type(Map<String, String> attri_type) {
		this.attri_type = attri_type;
	}
}
