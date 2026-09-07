## About the Project

The **Student Management System** is a Java-based application designed to manage basic student-related information in an organized way. The system provides a simple login mechanism and allows authenticated users to perform different operations on student records.

The application uses **file handling** to store and retrieve information. Student details are stored in `students.txt`, user login information is stored in `users.txt`, and assigned courses are stored in `courses.txt`.

The system demonstrates important Java programming concepts such as **classes, methods, conditional statements, loops, exception handling, file handling, and user input using Scanner**.

## Objective

The main objective of this project is to develop a simple system that can:

- Manage student information
- Search students using their ID
- Assign and view courses
- Delete student records
- Store data permanently using files
- Provide basic user authentication

## Project Structure

```text
StudentManagementSystem/
│
├── .idea/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── org.example/
│       │       └── Main.java
│       │
│       └── resources/
│           ├── users.txt
│           ├── students.txt
│           └── courses.txt
│
├── target/
│
├── pom.xml
│
└── StudentManagementSystem.iml
```

## Working Principle

When the application starts, the user must provide a valid username and password. After successful authentication, the system displays a menu containing different student management operations.

When a user performs an operation, the program reads or updates the appropriate text file. This allows the data to remain available even after the program is closed.

## Conclusion

This project provides a basic implementation of a **Student Management System using Java and file handling**. It is mainly designed to demonstrate how Java concepts can be combined to create a practical application.




