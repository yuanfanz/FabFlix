// JDBC Example - printing a database's metadata
// Coded by Chen Li/Kirill Petrov Winter, 2005
// Slightly revised for ICS185 Spring 2005, by Norman Jacobson


import java.sql.*;                              // Enable SQL processing

public class JDBC1
{
	       
   public static void main(String[] arg) throws Exception
   {
           // Incorporate mySQL driver
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           
           // Connect to the test database
           Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/moviedb","root", "1993zhangtianle");
	 
	       if(!connection.isClosed()){
                 System.out.println("Succeeded connecting to the Database!");
           }         

           // Create an execute an SQL statement to select all of table"Stars" records
           Statement select = connection.createStatement();
           ResultSet result = select.executeQuery("Select stars.first_name, movies.title from stars JOIN movies ON movies.id = stars_in_movies.movie_id");
           
           // Get metatdata from stars; print # of attributes in table
           System.out.println("The results of the query");
           ResultSetMetaData metadata = result.getMetaData();
           System.out.println("There are " + metadata.getColumnCount() + " columns");
           
           // Print type of each attribute
          int totalCount = 0;
          // while (result.next()){
          //   for (int i = 1; i <= metadata.getColumnCount(); i++)
          //            System.out.println(metadata.getColumnName(i).toUpperCase()+" = "+result.getString(i));
          //     System.out.println();
          //     totalCount++;
          // }
          // System.out.println("Total " + totalCount + " results");
          
           //print table's contents, field by field
           while (result.next())
           {
                   System.out.println("Id = " + result.getString(1));
                   // System.out.println("Name = " + result.getString(2) + result.getString(3));
                   // System.out.println("DOB = " + result.getString(4));
                   // System.out.println("photoURL = " + result.getString(5));
                   // System.out.println();
           }
   }
}