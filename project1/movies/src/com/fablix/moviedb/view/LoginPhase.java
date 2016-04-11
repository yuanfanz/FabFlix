package com.fablix.moviedb.view;

import java.io.Console;

import com.fablix.moviedb.db.dbConnection;

public class LoginPhase {
	
	private static Prompt prompt;
	private static Console userCons = System.console();
	
	public static void allowAccess(){
		
		prompt = new Prompt();
		// check if username and pass word is right.
		while(true){
			
			String username = userCons.readLine("Please Enter username: ");

			char[] password = userCons.readPassword("Please Enter password: ");

			if (dbConnection.ifPWDRight( String.valueOf(password) )
					&& dbConnection.ifUserRight(username) ){
				password = null;
				break;
				
			}else{
				System.out.println("Invalid username or Wrong password!");
				
				int Int = prompt.promptInt("Enter '1' to exit program (else continue)");
				System.out.println(Int);
				if ( Int == 1){
					System.out.println("Exiting now");
					System.exit(0);
				}else if (Int == -1){
					continue;
				}
			}
		}
		
		//check if database can be connected.
		while(true){
			
			if (dbConnection.getConnection()!= null){
				break;
			}else{
				System.out.println("Database may not exist, an Error occurred when access to database!");
				if (prompt.promptInt("Enter '1' to exit ") == 1){
					System.out.println("Exiting now");
					System.exit(0);
				}
			}
		}
		
		System.out.println("Access succesfully!");
	}
	
}
