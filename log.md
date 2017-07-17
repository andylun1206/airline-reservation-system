## Meetings:
### May 18, 2017
For team coding style, we will use the default coding style 
specified by the autoformat tool in Android Studio (Ctrl + Alt + L).

Database tables: User, Flights, Airports, Airlines, BookedFlights
	Split information into multiple tables to prevent redundant information storage.
	Table Flights will reference User, Airports, and Airlines.
	Table BookedFlights will reference User and Flights.

For iteration 1, we will implement the GUI for the big user story: searching for flights.
This story was chosen because in order to view and compare flights, we first need to be
able to search for flights. If time permits, we'll also implement the GUI for viewing 
the list of flights which match the search criteria.

The person responsible for writing a given class will also be responsible for writing the 
tests for that class.

We will use the GitHub flow model.

### May 24, 2017
Divided iteration 1 user stories into developer tasks. Created an issue on Github for each
developer task.

Decided to model design of the system after the sample project provided, since this is easier
and more time efficient than trying to come up with our own.
- 1 model object for each table in the database.
- 1 database access class for each table in the data base.

### June 10, 2017
Decided to change the way we assign developer tasks. Instead of assigning all of them upfront, just assign one or two. Then, when we finish our assigned tasks, we can pick any free issue on GitHub to work on.

## Iteration 1:
### Contributions


#### Business Logic (Shenyun Wang, You Tian Zhang)
- Problem: Database Access Objects (GitHub issues #9 - #13)
    - One dao for each table in our database
    - Initially did everything like in the sample project
        - After discussion, removed the 'getSequential' methods
            - Replaced with a 'getAll' method that returns a list of all entries in the table
        - Also changed the return types of the methods from String to boolean
    - After finishing implementations, we extracted an interface for each dao
- Problem: Logic for searching for Flights (GitHub issue #14)
    - Should return a List of Flights that match the specified search criteria
    - Initially used long list of parameters for the search method
        - Later encapsulated all the search parameters into a class (SearchCriteria)
    - After discussion, decided to move logic for searching for flights into the database layer
        - With a real database, we would just perform a SQL query 
        - So, we ended up just passing on the method call to the database
- Problem: Logic for sorting Flights (GitHub issue #31)
    - We want to be able to sort a list of Flights by:
        - Date
        - Duration
        - Price
    - Created Comparators for each parameter we want to be able to search by and then just use the built in Collections.sort() method
        - Passed in the Comparator that corresponds to what parameter we are sorting by
    - After implementation, extracted an interface (SortFlights) and renamed the implementation to SortFlightsImpl

#### Database (Zhengyu Gu, Long Yu)
-   Problem: Database Stub (GitHub issue #3)
    - Need a Database to store our Data objects
    - Implemented DataAccessStub, using ArrayList to store our Data objects(make some tables for Data objects)
        - give Database Stub Some basic functions(make it can operate each table)
-   Problem: Data Objects (GitHub issue #4,5,6,7,8)
    - Implemented Data objects class v1 (User, Flights, Airports, Airlines, BookedFlights)
    - add functions to Data objects class
-   Problem: Database Stub interface (GitHub issue #20)
    - create interface for our Database Stub
-   Problem: get time Duration from a Fight object
    - Need to calculating the time Duration between arrival time and departure time
        - adding a new class member: departure time
        - Implemented calculating time Duration function that returns a value that accurate into minutes
-   Problem: Database Stub violate SOLID Principles
    - should not put all of Database tables into one class
    - separate them, make specific classes to store each table

#### GUI (Andy Chi Fung Lun)
- Problem: Home screen/Main Activity
    - Need a home screen/main activity, explored options and found navigation drawer to be optimal choice
    - Implemented home screen v1 with logo and quick navigation buttons
    - Gathered feedback and created home screen v2 using fragments
        - Replaced logo with a wallpaper
        - Switched locations of buttons
- Problem: Search screen (GitHub issue #1)
    - Created simple search page using listview and fragments
        - Gathered icons from material.io
        - Mapped search criteria to SearchCriteria object
    - Improved on search page layout after gathering feedback
        - Added dropdown for different types of trips
        - Added advanced search criterias
    - Mapped search button to transition to view flights screen
- Problem: View Flights screen (GitHub issue #2)
    - Created simple view flight screen using listview and fragments
        - Verified transaction using hardcoded information
        - Added tabs for sort functionality
        - Refactored transaction to use search functionality implemented in business logic
- Problem: General
    - Created a simple splash screen to transition from startup to home screen (GitHub issue #21)
        - Added logo and app name to splash screen
    - Added toast messaging for features that are not implemented in this iteration (GitHub issue #26)
    
## Iteration 2:
### Contributions


#### You Tian Zhang
- Problem: GUI - Payment Screen (GitHub issue #70)
    - Need screen that allows users to input their payment and personal info
    - Initially created layout in xml, implemented with a bunch of EditTexts
    - Then, created PaymentInfo class + builder to model the user's card info
    - Later, switched to just using the Card model object from the Stripe API as well as using their CardInputWidget
        - Takes care of validating card info for us
- Problem: GUI - display booked flight info (GitHub issue #71)
    - Created input field for user's to input their passenger ID
    - All flights that are booked for that passenger ID are shown in a ListView
- Problem: Business - validate payment info (GitHub issue #72)
    - Taken care of in issue #70 by using Stripe's CardInputWidget
- Problem: Don't let users pick return dates before the departure date (GitHub isse #75)
    - Handled by calling the setMinDate() method with the appropriate parameter
        - Pass in the current date if it is the departure date datepicker
        - Pass in the date on the departure date datepicker if it is the return date datepicker
- Problem: Extract comparators to outside of the SortFlightsImpl class (GitHub issue #81)
    - Placed comparators in a new comparators package in the business package
    - Each as their own class
    
#### Andy Chi Fung Lun
- Problem: GUI - Return flights screen (GitHub issue #64, #65)
    - Needed a screen to display flights for return trip if round trip is selected
    - Screen is similar to the first set of flights, should not duplicate code
        - Found way to reuse screen by updating the ListView
    - Requires message passing to other fragments
        - Implemented FragmentNavigation to handle all navigation between fragments
- Problem: Bug fixes from previous iteration (GitHub issue #73, #74, #77, #78, #92, #96)
    - \#73: Keyboard was not losing focus when needed, keyboard will now be hidden if tapped on whitespace
    - \#74: All input fields were EditText, it is now possible to swap to other components
    - \#77: Integer overflow with input fields, there is a set max length now for each field
    - \#78: Fixed through issue #74
    - \#92: Chosen flights were not reset properly after going to view flights screen, fixed by reinitializing
    - \#96: Deleting useless buttons and removing dead code
- Problem: Fixing 'dat constructor' in search criteria (GitHub issue #80)
    - Initially thought to use builder class to replace constructor
    - Ultimately removed constructor since it was only used in testing
    
#### Shenyun Wang
- Problem: GUI - flight summary screen (GitHub issue #63)
	- Screen to display summary of flights chosen by user
	- recieves message passing of flights from previous screen 
	- hide return flight section if searching for one way trip
	
#### Zhengyu Gu and Long Yu
- Problem: Database - Port stub database to HSQL (GitHub issue #66)
	- implemented flight, airport, airline sql table
	- connected database to code
	- created hardcoded data for database
	- created functions copyDatabaseToDevice() and copyAssetsToDirectory() in main activity
	- created application package
- Problem: Database - Dependency injection (Github issue #67)
	- added dependencies into gradle
	- build interfaces for all object tables
	- removed addFunction, updateFunction, removeFunction
	- added different add methods for different tables
		

## Iteration 3:
### Contributions


#### You Tian Zhang
- Problem: Acceptance Testing (GitHub issues #125, #126, #127, #128)
    - Wrote acceptance tests for ticket page, view (comparison) page, payment page, and view booked flights page
    - Used Robotium
- Problem: Integration Testing (GitHub issues #134 - #138)
    - Tested dependency of business layer on the persistence layer
- Problem: Move Toast Creation to ToastHandler (GitHub issue #143)
    - We had created the ToastHandler class to handle creation of Toasts
    - But, we were not using it for all Toast creation in our app
    - Refactored code so that all Toast creation is in the ToastHandler class
- Problem: App would go from search screen to view flights screen for return trip searches even if we didn't have return flights
    - Added additional checks to the SearchCriteria validation that ensured we had return flights
    
#### Andy Chi Fung Lun
- Problem: Create check in page layout (GitHub issue # 130)
    - Created ticket (boarding pass) page instead
    - Includes QR code which can be scanned at the airport (GitHub issue #131)
- Problem: Airline icon on ticket is static (GitHub issue #155)
    - Bug was found after implementing ticket page
    
#### Shenyun Wang
- Problem: Acceptance Testing (GitHub issue numbers #129 and #133)
    - Used Robotium to write acceptance tests for the trip summary and search pages

#### Zhengyu Gu and Long Yu
- update database's storage
    - add some new flights
- Added seat number column to BookedFlight table in the database
    - Needed for the boarding pass
- add mock(unit) testing for testing sql database
    - done with mock test for each table
    

