package com.fablix.moviedb.DAO;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Table;


public class TableDAO {
	private Connection connection = dbConnection.getConnection();
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Table> getTables() throws SQLException{
	
		ArrayList<Table> tableList = new ArrayList<Table>();
		//HashMap<String, String> attri_type = new HashMap<String, String>();
		
		//look up attribute name and types
//		String stars = "show tables";
//		PreparedStatement select = connection.prepareStatement(stars);
//		ResultSet result = select.executeQuery();

		DatabaseMetaData metaData = connection.getMetaData();
		ResultSet tableSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});
		
		while(tableSet.next()){
			Table table = new Table();			
			table.setName(tableSet.getString("TABLE_NAME"));
			tableList.add(table);
		}
		
		for (Table table : tableList){
			ResultSet columnSet = metaData.getColumns(null, "%", table.getName(), "%");
			HashMap<String, String> attri_type = null;
			while(columnSet.next()){
			
				    if (table.getAttri_type()==null){
				    	attri_type = new HashMap<String, String>();
				    }
					attri_type.put(columnSet.getString("COLUMN_NAME"),columnSet.getString("TYPE_NAME"));
					table.setAttri_type(attri_type);

			}
		}
		dbConnection.rsstmtClose(tableSet, null);
		//dbConnection.connectionClose(tableSet, null, connection);
		return tableList;
	}
	
}
