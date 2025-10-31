ATM Simulation

This is a simple ATM simulation application built using Java Swing for the graphical user interface and MongoDB as the backend database to store account information, balances, and transaction histories.

Features

User Authentication: Secure login using Card Number and PIN.

Check Balance: View the current account balance.

Cash Deposit: Deposit money into the account.

Cash Withdrawal: Withdraw money, checking against the current balance.

Pin Change: Ability to change the account PIN.

Transaction History: View a mini-statement of recent transactions.

Exit: Log out of the application.

Technologies Used

Frontend: Java Swing

Backend: Java

Database: MongoDB (with the MongoDB Java Driver)

Database Structure

Database Name: atmSimulation

Collections:

accounts

balance

transactions

Prerequisites

Java Development Kit (JDK) 8 or higher

An IDE (like IntelliJ IDEA, Eclipse, or NetBeans)

MongoDB Server (running locally or on a cloud service like MongoDB Atlas)

MongoDB Java Driver library added to your project.

How to Run

Clone the repository:

git clone https://github.com/chiku0989/atm-simulator-system.git


Start MongoDB: Ensure your MongoDB server is running.

Configure Database: Open the project in your IDE and locate the database connection file (e.g., Conn.java). Update the MongoDB connection string if it's not running on the default mongodb://localhost:27017.

Build the Project: Compile all the Java files.

Run the Application: Run the main class (e.g., Login.java or Main.java) to start the application.
