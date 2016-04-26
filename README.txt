 # PLEASE MAKE SURE that you have the Apache Maven 3.3.9
   Because we use the maven for our project management.
 
 How to compile
*———————————————*
1. How to compile on localhost
Please type on terminal:

cd ../yourPath/project1/movies
mvn clean compile

2. How to compile on server
Please connect to unbuntu@52.27.36.56 on terminal.
Then you get the connection to server.
Open your terminal, please type:
    /home/ubuntu/makeJava
makeJava is the bash file of Linux

————————————————————————————————————————————————————————————————————————————————————————


  How to run
*————————————*
1. How to run on localhost
1) Please type on terminal:

mvn exec:java -Dexec.mainClass="com.fablix.moviedb.view.View" -Dexec.classpathScope=runtime

2) After that the terminal will ask you to enter the username and password.
3) Please enter the username:root, then press enter for the password.
4) Then the terminal will display: Access succesfully!
   Then the menu will show up!

2. How to run on server
1) Open your terminal, then please Type:
     /home/ubuntu/runJava
   runJava is the bash file of Linux
2) After that the terminal will ask you to enter the username and password.
3) Please enter the username:root, and enter the password:1993zhangtianle.
4) Then the terminal will display: Access succesfully!
————————————————————————————————————————————————————————————————————————————————————————


 What functions does our project provide
*———————————————————————————————————————*
1) This project provide several functions for user to create, read, update and delete different information in the database.
2) After you have entered into the system succesfully, please enter number 1~8 to select different function. the terminal will show a function menu that includes:
   (1) Find A Movie By A Star's Name 
       After you enter number “1” and press enter, you are able to choose to find movies by star’s first name and/or last name by entering number 1 or choose to find movies by star’s id by entering number 2.
       If you select 1 in the former step, you will be asked to enter the star’s first name and last name if you have either or both of them.
       If you select 2 in the former step, you will be asked to enter the star’s id.
       The system will return the information of related movies that found or it will tell you no movies has been found.
   (2) Add A New Star
       After you enter number “2” and press enter, you are able to add a new star’s information into the system.
       You will be asked to enter star’s first name, last name, birth date and photo url.
       If you skip enter first name and last name the system will keep asking you enter at least one name.
       Other information is optional.
   (3) Add A Customer
       After you enter number “3” and press enter, you are able to add a new customer’s information into the system.
       You will be asked to enter customer’s first name, last name, password, credit card number, address and email.
       You can not leave both first name and last name empty and you must enter credit card number, or the system will ask you to enter again.
       The credit card number you provide should be valid, or the customer’s information will not be added.
   (4) Delete A Customer
       After you enter number “4” and press enter, you are able to delete a customer in the system.
       You will be asked to enter the customer’s credit card number.
       If the credit card number is valid the corresponding customer will be deleted, or it will remind you that the customer does not exist.
   (5) Print Schema of All Database Tables
       After you enter number “5” and press enter, the system will show you each attribute and its type of each table in the system.
   (6) Execute SQL Command
       After you enter number “6” and press enter, you are able to enter valid SQL command to do execution.
       If the command you enter is not valid the system will remind you the error.
   (7) Exit the Menu
       After you enter number “7” and press enter, you will be able to exit the menu status and back to the login status.
   (8) Exit Program
       After you enter number “8” and press enter, you will be able to exit the entire program.
————————————————————————————————————————————————————————————————————————————————————————


 SQL commands that may need
*——————————————————————————*
1) select <column name or *> from <table name> where <conditon>;
2) update <table name> set <column1 = newvalue1>, <column2 = newvalue2>, … where <condition>;
3) insert into <table name> values (‘<column1 value1 or NULL>’,‘<column2 value or NULL>’,…);
4) delete from <table name> where <condtion>;
————————————————————————————————————————————————————————————————————————————————————————


 Attention
*——————————*


 Credits
*———————*
Programmer:
Tianle Zhang 74488544
Yuanfan Zhang 18601447
Siyu Zhou 77729957
