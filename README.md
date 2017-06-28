# COMP 3350 - Software Engineering I
## Group 2 - save on flight

### Iteration 1:
We based our project’s 3-tier architecture off of the sample project’s architecture (presentation, business, and persistence). Currently, we have four packages: business, objects, persistence, and presentation.
 
In the business package, we have five data access objects (one for each table in our database) as well as the the business logic class for sorting flights on the comparison page (SortFlights).
 
Next, the objects package contains all of our domain-specific classes. Again, there is one for each table in the database. Additionally, there are a couple extra classes for communication between the presentation and business layers.
 
The persistence package contains the interface for the database (DataAccess) as well our database stub, which consists of five classes. Each table in our database is represented by one class.
 
Finally, the presentation package contains all classes related to the application’s user interface. Here, we currently have two Activities (SplashActivity and MainActivity), as well as three fragments (HomeFragment, SearchFragment, and ViewFlightsFragment). Upon starting the app, the user will see a splash screen (SplashActivity) before being directed to the home screen (MainActivity). When switching between the home screen, search screen, and flight viewing (comparison) screens, we simply replace the current fragment defining the UI with the one we want.
 
The team’s log file is found in the root directory of our project (log.md). The GitHub repository can be found at https://github.com/andylun1206/save-on-flight.
 
One major implemented feature in this iteration was the ability to search for flights that match some specified set of criteria (round trip vs. one way, origin, destination, etc.). From the home screen, the user can get to this feature in the GUI by either using the “SEARCH FOR FLIGHTS” button or by selecting the “Search for Flights” option from the navigation drawer on the left. Since our stub database has a very small number of flight entries, it is highly unlikely that a random search will give any results. One search which will give results is:
- Trip type: One Way
- Origin: YWG
- Destination: YYZ
- Departure Date: 2017-11-11 (November 11, 2017)
- Number of Travellers: 1

Currently, both the one way and the return trip options only return one way trips.
 
Another feature of our app is the ability to view a list of flights and to sort them based on various criteria. Upon performing a search which yields a non-empty result, you will be brought to the view flights screen. The list of flights returned from the search will be shown in the order it came in. To compare the list of flights, three buttons allow the user to sort the flights based on price, departure time, and duration.

### Iteration 2:
This iteration, we finished the big user story “searching for flights”. The ability to search for return trips was finished. One search which will give results is:
- Trip Type: Return
- Origin: Winnipeg YWG
- Destination: Toronto YYZ
- Departure Date: 2017-11-11 (November 11, 2017)
- Return Date: 2017-12-11 (December 11, 2017)
- Number of Travellers: 1 

An autocomplete feature was also added to the ‘Origin’ and ‘Destination’ EditTexts for big airports in Canada (e.g. Winnipeg YWG and Toronto YYZ). 

The feature of comparing flights, found in the view flights screen, was also updated to accommodate return trips. 

After a user selects their flight(s) on the view flights screen, they are taken to a newly implemented trip summary page. Here, they can see detailed information about their flights. It gives the user the option to either go back and modify their search or to proceed to the payment screen.

The payment screen provides input fields for the user’s credit card information and personal information. We used Stripe’s CardInputWidget for inputting credit card information which also handles validation. One combination of credit card number, expiry date, and security code which passes validation is:
- Credit Card Number: 4242 4242 4242 4242
- Expiry Date: 8/18
- Security Code: 850

Upon payment, the new booked flights are added to the database.

Booked flights can be viewed by clicking the button “BOOKED FLIGHTS” from the homepage or selecting it from the navigation drawer. In the view booked flights page, there is an input field for passenger ID. Pressing the “VIEW FLIGHTS” button will update the screen to show a list of all flights that the passenger with the specified ID has booked. 

Additionally, various improvements and bug fixes from the first iteration were completed. This includes:
- Applied builder pattern to the Flight class
- Removed buttons for unimplemented features
- Extracted comparators outside of the SortFlightsImpl class
- Set max lengths for EditTexts
- Set appropriate input types for EditTexts

Moreover, we ported our stub database to a HSQL database. The stub database was kept for testing purposes and dependency injection was used to switch between the two.




