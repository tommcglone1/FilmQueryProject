## Film Query App

# Description 

This project is divided into four interconnected packages(entities, database, app, and database test) that allow for the retrieval of information from a relational database using the relational database management system MySQL and the Java database connectivity API. The database contains information for a BlockBuster-type movie rental store including tables holding information on customers, films, staff, stores, and more. However, the queries performed throughout this application access predominantly the information about the films available in the company's catalog. Along with a small portion of the information about the store inventory.

#### Entities

Three entities within this project, actor, inventory, and film, are used for storing instance information retrieved from the database.  

* Actor encapsulates three instance fields,

     - **id** - for the storing an int of the actors S.A.G ID number,
     - **firstName** - a String of the actors first name,
     - **lastName** - a String of the actors last name.

The purpose of this object is to be instantiated with the first and last name fields for each actor in the movie currently being searched. These actor objects, however many there may be, are then stored in a list and presented as a "cast list" for the movie.

* The next entity is Inventory which has two instance fields, 

     - **id** - the id number of the specific disc the film is on, 
     - **condition** - the condition of that disc.

Much like Actor, the purpose of this object is to be instantiated with its two fields for as many discs of the film that exist in the inventory. The objects are then stored in a list and their fields are presented when a user searches for the movie.

* The final entity is film which has fifteen instance fields. I will not discuss all of them here as many self explanatory, but I will highlight the crucial ones for the main portion of the project.

     - **id** - Although id is not a requirement to print to screen, it is crucial for determining which film the user is searching in the lookUpById and findFilmById methods. It is a unique field for each of the 1000 films in the catalog. 
     - **description** - A brief synopsis of the film that is used specifically in the findFilmByKeyword method to allow the user to search for a film by what occurs during it. 
     - **languageName** - The audio language of the film. This information is ascertained through a JOIN statement linking the film table and the language table since the film table only carries a language ID for each film.	
     - **actorList** - The array list that is used to store the unique "cast list" for the movie currently being instantiated and searched.

 	This object is the workhorse of the application. It not only carries all the information for each movie searched, but it also prints its toString to display information for the user, the actor list, the inventory list, and, for the stretch goals, prints all details about the films. 
	

#### Database 

The database package contains the DatabaseAccessor interface and the DatabaseAccessorObject. The interface provides all the methods that must be overridden in its object version. 
The DatabaseAccessorObject is the data access object where all the queries are handled for the application. It contains encapsulated fields for the URL of the sdvid film server where all the film information is stored and the username and password needed to access the database. It also contains a static initialization block to create the driver that will be used to access the information from the server. The methods of this DAO are all similar in function. They connect to the server through the driver and send a query to obtain information. They store that information as instance fields in an encapsulated object and return that object to the film query application where it can be displayed. 

###### *DataBase Example*

For example, the findFilmById method takes a film ID input by a user and returns that film's information. The way it does this is by first connecting to the server through the driver manager classes getConnection method. This method attempts to create a connection to the database using the provided URL, and once that connection is established, the username and password are authenticated by the server. Next, a prepared statement object is created through the connection classes prepareStatement method to store the SQL parameterized query so it can be sent to the database. The prepared statements setInt method is used to set the first and only parameter within the query represented by the ?. This is slightly different within the findFilmByKeyword method as its query takes two parameters and therefore two must be set.
The query itself is rather straightforward. It Selects the film id, title, description, release year, and rating as well as the language name to be later stored in the film object. Then the film and language tables are joined through the film table's language id column and the language tables id column. Finally, using the user input to determine the WHERE clauses parameter.

Next, the query is executed with the information in the prepared statement and returns a ResultSet object. This object is a table of the data from the database and is represented by the filmResult variable. This object is then checked with an if statement to ensure that the result set contains information before the film object is instantiated on the heap. If the query results in no data in the first row of the table, the if statement returns a false boolean and the instantiation is skipped resulting in a null film object. If the query does return data, the film object is instantiated and the information is stored in the object through the Film multi-arg constructor. An important note about this process is that the findActorsByFilmId method located within the class is called to populate the list of actors for the film using the id originally entered by the user. Either the id pulled from the database or the id input by the user could be used in this case each would achieve the same outcome, a list of actors. It is important to note though, that this entire process will occur again in that method using a different query to obtain the actor information. 

Finally, each of the resources are closed, releasing each of their database and JDBC resources. With a finally block to ensure they are closed. This is possible because the entire above process is enclosed in a try-catch block to avoid crashing the program if there is an SQLException. After this, the film is returned to whichever method called it in the FilmQueryApp class.

#### App

The app package is the front-facing portion of this application and contains the user interface that is displayed in the console. The application begins by displaying a menu for the user to select whether they would like to look up a film by ID number, keyword, or exit the application. If the user selects 1 the lookUpById method is called through the switch case and a copy of the scanner object reference variable is passed through.  
 
###### *Looking up by ID*
 
In this method, the user is asked to enter the id number of the movie. This input is used to call the findFilmById method discussed above. Once the film object is returned, an if statement checks whether the query returned any information from the database. If the object is determined to be null, a message prints telling the user there is no film with that id number and they are returned to the main menu. A while loop could have been used to make the application a little more user-friendly but in the end, was not. If the query does return film information that information is printed to the screen along with the actor list. The actor list is printed separately for ease of readability. The printActorList method iterates through the list using a for loop printing each actor's name on their own line as opposed to all on one line. The user is then prompted if they would like to view all the details of the movie or return to the main menu. If they chose to return to the menu, the else if condition is determined true and the main menu is displayed based on the while loop in the menu method. If they choose to view all details the viewAllFilmDetails method is called and the film id number the user chose is passed to that method. 

Within the viewAllFilmDetails method, the allFilmDetails method from the DAO is called and the user-entered film id is passed to the allFilmDetails method. The film is returned from that method using a near-exact process as in the findFilmById method and the data is stored in a film object represented by the variable allDetailsFilm. This variable is then used to call the printAllDetails method from the film object class, and all the details are printed to the screen. Along with this, the actor list is printed and the inventory list is printed. Displaying each version of the disc for that movie and the condition it is in. After this, the main menu is displayed.

###### *Looking up by keyword*

If the user selects 2 from the main menu the lookUpByKeyword method is called and a copy of the Scanner input variable is passed. The user is prompted to enter a keyword pertaining to the movie title or description they would like to search for. This input is used to call the findFilmByKeyword method in the DAO. This method returns a list of films as there is a possibility that many different films share the same keyword. Once this list is returned it is checked with an if statement to ensure that the list is not empty. If a user inputs a keyword that pertains to no films in the database, an empty list will be returned and a message will be displayed stating that. The user will be sent to the main menu. If the list does contain film objects, the list is iterated through using a for each loop. The film state information is printed out along with the actor list. Similarly, as in the lookUpById method, the user is prompted to view all details of the film, continue with the list of movies or return to the main menu. This third option was provided because lists can become very long depending on the keyword entered and I did not want to make the user have to stop the program and rerun it. If the user selects 1 all the same viewAllFilmDetails method is called again. However, since there is now no user input id number, the films getId method is used to obtain the current films id number. This id number is passed through to the method and is used in the same way as discussed previously in this method.

Once this method is complete the loop reiterates with the next film if the user selects 2 the viewAllFilmDetails method is skipped and the continue keyword is used to restart the loop. If the user selects 3 they are taken back to the main menu. 

From the main menu if the user again selects 3 they are thanked for their queries and the application is terminated by setting the while loops isMakingSelection boolean to false.
 

#### DataBase Test

The DataBase Test package contains the JUINT 5 tests for this application. Each method in the DataBaseAccessorObject class is tested using conditions specific to what they return. If the method returns a single object, that object is checked to not be null and then is checked to ensure the correct information is returned. For example in testFindActorById, the id 13 is passed to the findActorById method and the String "Uma Wood" is checked against actors.getFirstName and .getLastName methods. The same method is then checked to ensure it will provide a null object if the id is invalid.

If the method returns a list, a film id or film keyword is passed to the respective method and the returned list is checked to ensure that it is not empty and then is checked against a condition based on an index of that list. The only time the second check does not occur is on the find InventoryByFilmId in this instance only a check for a non-empty list occurs. As before, a second method is used to ensure that the list is returned empty when an invalid input is used. 

#### Maven

Maven is used in this project to manage the dependencies associated with connecting MySQL to the java project. One important thing maven does is it creates the pom.xml file where the MySQL library dependency can be added. When the project, begins Maven checks the local mac repository for the MySQL library. If it is not found, it downloads the jar files from the Maven default repository based on the artifactId. This is essential to the project because without it the Java classes would not have the ability to use the relational database associated with MySQL. 

# Technologies Used
Java, Eclipse, SQL, Maven

# Lessons Learned

1. One of the biggest lessons I learned from this project is the process of dynamic web design. The connection of java to MySQL through maven dependency management and the actual connection itself to the relational database through the URL and driver were both brand-new concepts to me.

2. Another big takeaway from this project is the usage of the SQL language and query writing. Not only through actually writing queries correctly but also through how those queries can be parameterized to accept outside user input. 

3. A final one of my many takeaways was the use of a data access object design pattern. The DAO provides encapsulation for the database by using its methods to access and manipulate the persistent information and return it as a further encapsulated object. This layer of abstraction is much better than allowing the user of the application, in this case, the user of the FilmQueryApp class, direct access to the database and its information.
