package com.fablix.moviedb.test;

import com.fablix.moviedb.QueryExec.QueryExecute;

public class TextSQL {
	public static void main (String[] args){
		
		QueryExecute.exeuteQuery("INSERT INTO movies (id,title,director,year) VALUES (111,'ddd','aaa',1345);");
		QueryExecute.exeuteQuery("Select * from movies where id = 123");
		//QueryExecute.exeuteQuery("Select * from movies");
//		INSERT INTO movies (column1,column2,column3,...)
//		VALUES (value1,value2,value3,...);
	
	}
	
}
