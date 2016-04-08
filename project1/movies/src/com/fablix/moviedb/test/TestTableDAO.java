package com.fablix.moviedb.test;

import java.util.List;

import com.fablix.moviedb.DAO.TableDAO;
import com.fablix.moviedb.action.TableAction;
import com.fablix.moviedb.model.Table;
import com.fablix.moviedb.view.Print;

public class TestTableDAO 
{
	static TableDAO table = new TableDAO();
	
	public static void main(String[] args) throws Exception
	{
		TableAction ta = new TableAction();
		
		List<Table> li = ta.getTables();
		Print.printTables(li);
		TableAction ta2 = new TableAction();
		List<Table> li2 = ta2.getTables();
		//Print p = new Print();
		
		Print.printTables(li2);
		
		
	}

}
