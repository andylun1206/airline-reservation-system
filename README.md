# COMP 3350 - Software Engineering I
## Group 2 - save on flight

### Iteration 1:
We based our project’s 3-tier architecture off of the sample project’s architecture (presentation, business, and 
persistence). So, we have the same five packages: application, business, objects, persistence, and presentation.
 
In the business package, we have five data access objects (one for each table in our database) as well as the the 
business logic class for sorting flights on the comparison page (SortFlights).
 
In the application package we have two classes: Main and Services. These classes provide static methods for opening
 and closing the database connection.
 
Next, the objects package contains all of our domain-specific classes. Again, there is one for each table in the 
database. Additionally, there are a couple extra classes for communication between the presentation and business 
layers.
 
The persistence package contains the interface for the database (DataAccess) as well as our database stub 
(DataAccessStub).
 
Finally, the presentation package contains all classes related to the application’s user interface. Here, we currently
 have two Activities (SplashActivity and MainActivity), as well as three fragments (HomeFragment, SearchFragment, and 
 ViewFlightsFragment). Upon starting the app, the user will see a splash screen (SplashActivity) before being directed 
 to the home screen (MainActivity). When switching between the home screen, search screen, and flight viewing 
 (comparison) screens, we simply replace the current fragment defining the UI with the one we want.
 
The team’s log file is found in the root directory of our project (log.md). 
 
One major implemented feature in this iteration was the ability to search for flights that match some specified set 
of criteria (round trip vs. one way, origin, destination, etc.). From the home screen, the user can get to this feature
 in the GUI by either using the “SEARCH FOR FLIGHTS” button or by selecting the “Search for Flights” option from the 
 navigation drawer. 
