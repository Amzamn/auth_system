# Simple JavaFX Authentication System with MySQL

This repository contains a simple JavaFX project that implements a basic authentication system with MySQL database integration. The project consists of three main scenes: login, signup, and home.

## Features

- **Login:** Users can log in with their email and password. If the credentials are correct, they are redirected to the home page.
- **Signup:** New users can create an account by providing their full name, email, and password.
- **Home:** Upon successful login or signup, users are redirected to the home page, where they can access their profile information and perform other actions.

## Technologies Used

- **JavaFX:** For building the user interface and handling user interactions.
- **MySQL:** For storing user data such as email, password, and full name.
- **JDBC:** For connecting Java application with the MySQL database.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/NabilMouzouna/Basic_authentication.git
    ```

2. Set up MySQL database:
    - Create a MySQL database and a table to store user data. You can use the following SQL script as a reference:
        ```sql
        CREATE TABLE users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            fullname VARCHAR(100),
            email VARCHAR(100),
            password VARCHAR(100)
        );
        ```

3. Configure database connection:
    - Update the MySQL connection settings in the Java code to match your database configuration.

4. Build and run the project:
    - Open the project in your favorite Java IDE.
    - Build and run the project to launch the JavaFX application.

## Usage

- Launch the application.
- If you are a new user, click on the "Signup" button to create an account.
- If you already have an account, enter your email and password and click on the "Login" button.
- Upon successful authentication, you will be redirected to the home page.

## Notes

- This is a simple authentication system and does not implement advanced security measures such as encryption or authorization management. Use it for educational or prototyping purposes only.
- Ensure to handle exceptions and edge cases appropriately, especially when dealing with user input and database operations.
- Contributions and feedback are welcome. Feel free to fork the repository and submit pull requests to improve the project.
