package com.fablix.moviedb.test;
import java.sql.SQLException;
import java.util.Date;

import com.fablix.moviedb.action.StarsAction;
import com.fablix.moviedb.model.Stars;
import com.fablix.moviedb.view.Print;

public class TestStar 
{
	public static void main(String[] args)
	{
		StarsAction star = new StarsAction();
		
		//test
		try
		{
			Stars st = new Stars();
			Date date = new Date();
			date = null;
			
			st.setFirst_name("Nancy");
			st.setLast_name("Tom");
			st.setDob(date);
			st.setPhoto_url("abced");
			Print.printAddedStars(st);
			star.addStarByName(st);
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("An error occured when connecting to database");
		}
		
		
	}

}
