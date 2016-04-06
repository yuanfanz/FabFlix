package com.fablix.moviedb.action;

import java.sql.SQLException;

import com.fablix.moviedb.DAO.StarsDAO;
import com.fablix.moviedb.model.Stars;

public class StarsAction {
	
	/**
	 * 
	 * @param s
	 * @throws Exception
	 */
	
	public void addStarByName(Stars s) throws SQLException{
		
		StarsDAO starDao = new StarsDAO();
		starDao.addStar(s);
	}	
}