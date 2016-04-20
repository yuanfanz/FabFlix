package com.fablix.moviedb.view;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fablix.moviedb.QueryExec.QueryExecute;
import com.fablix.moviedb.action.*;
import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;
import com.fablix.moviedb.model.Customers;
import com.fablix.moviedb.model.Table;

public class View {
	
	private final static String CONTEXT = "\n(1) Find A Movie By A Star's Name\n"
				+ "(2) Add A New Star\n"
				+ "(3) Add A Customer\n"
				+ "(4) Delete A Customer\n"
				+ "(5) Print Schema of All Database Tables\n"
				+ "(6) Execute SQL Command\n"
				+ "(7) Exit the Menu\n"
				+ "(8) Exit Program\n"
				+"Please enter the number(1~8) of the operation: \n";
	
	private static Prompt prompt;
	
	public static void main(String[] args){
		
		prompt = new Prompt();
		
		program:
		while(true){
			//login
			LoginPhase.allowAccess();
			
			menu:
			while(true){
				switch(prompt.promptInt(CONTEXT)){
				case 1:
					
					getMovies();
					break;
					
				case 2:
					
					addStars();
					break;
					
				case 3:
					
					addCustomer();
					break;
					
				case 4:
					
					delCustomer();
					break;
				
				case 5:
					
					printSchema();
					break;
					
				case 6:
					
					String sqlStatement = prompt.promptString("Please Enter the valid MySQL command: ");
					QueryExecute.exeuteQuery(sqlStatement);
					
					break;				
				
				//exit menu
				case 7:
					//dbConnection.close();
					break menu;
				
				//exit program
				case 8:
					// close resource!!!
					//dbConnection.close();
					prompt.closeScanner();
					break program;
	
				default:
					
					System.out.println("Invalid operation!");
					break;
				
				}
			}		
		}		

		System.out.println("EXIT successfully!");
		System.exit(0);
	}
	
	/**
	 * 
	 */
	private static void getMovies(){
		MoviesAction movieaction = new MoviesAction();
		int operationNO = prompt.promptInt("Find a movie by (1)first Name and/or Last Name "
				 + "(2) Id  of the star");
		
		try {
			if ( operationNO == 1 ){
				
				String firstName = prompt.promptString("Please Enter First Name of the star if any:");
				String lastName = prompt.promptString("Please Enter Last Name of the star if any:");
				
				List<Movies> movies = movieaction.getMoviesByStarName(firstName, lastName);
				Print.printMovies(movies);
								
			
			}else if ( operationNO == 2 ){
				
				int id = prompt.promptInt("Please Enter ID of the star: ");
				List<Movies> movies = movieaction.getMoviesByStarId(id);
				Print.printMovies(movies);

			
			}else{
				System.out.println("Please enter a valid operation number!");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.out.println("ERROR : cannot find the movie by stars!");
		}
	}
	

	private static void delCustomer(){
		CustomerAction cusAct = new CustomerAction();
		String id = prompt.promptString("Please Enter the Credit Card Id to delete the customer: ");
		try{
			
			cusAct.del(id);
		
		}catch(Exception e){
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.out.println("ERROR : cannot delete the customer! ");
		}
		
	}

	private static void addStars(){
		StarsAction starsaction = new StarsAction();
		Stars s = new Stars();
		s.setDob(null);
		try{
			while(true){
				String first_name = prompt.promptString("Please Enter First Name of the star if any: ");
				String last_name = prompt.promptString("Please Enter Last Name of the star if any: ");
				
				if(first_name.length() == 0 && last_name.length() == 0){
					System.out.println("Please enter at least one name.");
					continue;
				}
				
				//get date of birth and photo url of the new star
				String dob = prompt.promptString("Please Enter date of birth of the star In yyyy.MM.dd Format if any: ");
				String photo_url = prompt.promptString("Please Enter photo url of the star if any: ");
				
				//change date from string to date.sql
				
				if(dob.length() != 0){
					try{
						DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
						
						Date date = format.parse(dob);
					    java.sql.Date newDob = new java.sql.Date(date.getTime());
					    s.setDob(newDob);
					    
					} catch (Exception e) {

						System.err.println(e.getMessage());
						System.out.println("ERROR : Please enter the correct form of date of birth!");
						continue;
					}
				}
				
			    //set attributes to the new star
				s.setFirst_name(first_name);
				s.setLast_name(last_name);
				s.setPhoto_url(photo_url);
				break; 
			}
			starsaction.addStarByName(s);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.out.println("ERROR : cannot add stars!");
		}
	}
	
	private static void addCustomer(){
		CustomerAction customerAction = new CustomerAction();
		
		Customers c = new Customers();
		try{
			while(true){
				String first_name = prompt.promptString("Please Enter First Name: ");
				String last_name = prompt.promptString("Please Enter Last Name: ");
				if(first_name.length() == 0 && last_name.length() == 0){
					System.out.println("Please enter at least one name.");
					continue;
				}
				
				String password = prompt.promptString("Please Enter the password: ");
				
				//get credit card number and address of the new customer
				String cc_id = prompt.promptString("Please Enter the credit card number: ");
				if(cc_id.length() == 0){
					System.out.println("Please enter your credit card number.");
					continue;
				}
				
				String address = prompt.promptString("Please Enter the address: ");
				String email = prompt.promptString("Please Enter the email: ");
				
				
			    //set attributes to the new customer
				c.setFirst_name(first_name);
				c.setLast_name(last_name);
				c.setPassword(password);
				c.setAddress(address);
				c.setCc_id(cc_id);
				c.setEmail(email);
				break;
			}
			customerAction.add(c);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.out.println("ERROR : cannot add customer!");
		}
	}
	
	private static void printSchema(){
		TableAction tableaction = new TableAction();
		
		try{
		List<Table> tables = tableaction.getTables();
		Print.printTables(tables);	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
			System.out.println("ERROR : cannot print table schema!");
		}
	}
}
