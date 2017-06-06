## Meeting:
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


## Iteration 1:
### Contributions


#### Business Logic (Shenyun Wang, YouTian Zhang)


#### Database (Zhengyu Gu, Long Yu)


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
    