package com.fablix.moviedb.action;

import java.sql.SQLException;
import java.util.List;

import com.fablix.moviedb.DAO.TableDAO;
import com.fablix.moviedb.model.Table;


public class TableAction {
	
	public List<Table> getTables() throws SQLException{
		
		TableDAO tableDao = new TableDAO();
		return tableDao.getTables();	
	}
}
