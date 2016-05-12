package com.fablix.moviedb.db;
import java.sql.*;

public class dbConnection {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/moviedb";
	private static final String USER = "root";
	private static final String PASSWORD = "1993zhangtianle";
	
	private static Connection connection = null;
		
	public static Connection getConnection(){			
					
			try {
				// Incorporate mySQL driver
				Class.forName("com.mysql.jdbc.Driver").newInstance();
					
				if (connection == null){
					connection = DriverManager.getConnection(URL, USER, PASSWORD);
				}
				// Connect to the test database

				return connection;
			
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.err.println(e.getMessage());
				//System.err.println(e.getSQLState());
//				System.out.println("An error occured when connecting to database!");
				return null;
			} 
	}
	
	public static void rsstmtClose(ResultSet rs, Statement ps)
	{
		if (rs!=null){
			try{
				rs.close();
				rs=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Close ResultSet failed!");
			}
		}
		
		if (ps!=null){
			try{
				ps.close();
				ps=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Close Statement failed!");
			}
		}
	}
	
	public static void connectionClose(){
		if(connection != null)
		{
			try {
				connection.close();
				connection = null;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Close Connection failed!");
			}
		}
	}
	
	
	public static boolean ifUserRight(String username){
		
		if (username.equals(USER)){
			return true;
		}else{
			return false;
		}
	
	}
	
	public static boolean ifPWDRight(String password){
		
		if (password.equals(PASSWORD)){
			return true;
		}else{
			return false;
		}
	
	}

}
