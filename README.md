# BankingSystem(Oracle)
Project using Oracle and JDBC


The Banking Management System is a console-based Java application designed to simulate core banking operations by integrating with an Oracle database using JDBC (Java Database Connectivity). The primary objective of this project is to provide a menu-driven interface that allows users to perform operations such as managing customer records, handling account creation, generating loans, and performing deposit and withdrawal transactions. The system supports essential functionalities like adding, deleting, updating customer details, and viewing comprehensive account and loan information by joining related tables like customer, account, loan, and branch. All data manipulation operations (INSERT, DELETE, UPDATE, and SELECT) are executed using SQL queries via JDBC, ensuring real-time synchronization between the application and the database. The application includes proper exception handling and formatted terminal output for clarity and usability. This project consolidates theoretical and practical concepts learned in the database course, making it an effective demonstration of database integration and transaction handling in Java.

Project Flow: Banking Management System

1. Start the Program
Java application is executed.
Establishes a JDBC connection to the Oracle database.
If connection fails, the program exits with an error message.
If successful, a menu is displayed for the user.

2. Display Menu
A switch-case menu is shown repeatedly until the user chooses to exit:

1. Show Customer Records
2. Add Customer Record

    
3. User Selects an Operation
The user enters a numeric option (1–12).
Based on the choice, the respective method is called.

4. Perform Database Operation
Each operation involves the following:

a. Data Input:
Inputs are taken via Scanner from the user.
For example, to add a customer, cust_no, name, phoneno, and city are collected.

b. SQL Query Execution:
SQL queries are prepared using PreparedStatement.
Queries perform actions like:
INSERT INTO (e.g., Add customer/account/loan)
DELETE FROM (e.g., Delete customer)
UPDATE (e.g., Update balance, customer info)
SELECT (e.g., Show details)

c. Output Display:
Results are displayed in a formatted manner using System.out.println or System.out.printf.
If no record is found, appropriate messages are shown.

5. Exception Handling
Each database operation is wrapped in a try-catch block.
Any SQLException or input errors are caught and printed, maintaining program stability.

6. Repeat Until Exit
After completing an operation, the menu is shown again.
This loop continues until the user selects option 12 (Exit).

7. End the Program
JDBC connection is closed.
Program terminates gracefully.
