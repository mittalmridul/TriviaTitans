# Trivia Titans

This project, **Trivia Titans**, is a Quiz Application . The application is built using **Spring Boot**, with a focus on integrating database connections and demonstrating multithreading concepts.

Developed By : Mridul Mittal
---

## Technologies Used

### 1. **Spring Boot**
- Spring Boot serves as the foundation of this application, simplifying the creation of RESTful services, managing dependencies, and providing a robust backend architecture. It powers features such as routing, dependency injection, and database integration.

### 2. **Database Connection**
- The application uses an **H2 in-memory database** for development and testing purposes. It stores quiz questions and results persistently, with options to configure external databases like MySQL.
- The database is managed using **Spring Data JPA**, allowing seamless integration with Java objects and simplifying CRUD operations.

### 3. **Multithreading Concepts**
- The application incorporates multithreading to manage the quiz timer. A separate thread tracks the countdown for each quiz session, ensuring that users adhere to the allotted time limit without blocking other processes.

### 4. **File Export (I/O)**
- **iText** is used to generate PDF files, enabling users to download their quiz results in different formats.

---

## Features

1. **Quiz Functionality**:
    - Timed quizzes with a countdown timer implemented using multithreading.
    - Randomized questions fetched dynamically from the database for each quiz session.

2. **Results Management**:
    - Displays quiz scores immediately after submission.
    - Stores quiz results persistently in the database.

3. **Export Functionality**:
    - Users can export quiz results as PDF files using dedicated endpoints or UI buttons.

4. **User-Friendly Interface**:
    - Developed using **Thymeleaf templates** for dynamic and interactive web pages.

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher
- **Maven**: For dependency management and build

---

## Installation and Running the Application

### 1. Extract the Project Folder

Download and extract the provided project zip file to your local machine.

### 2. Install Java and Maven

If you don’t already have Java or Maven installed, follow these steps:

#### **Install Java (JDK 17 or higher)**

- Download the JDK from the [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/install/).
- Follow the installation instructions for your operating system.
- Verify the installation:

```bash
java -version
```

#### **Install Maven**

- Download Maven from the [Maven website](https://maven.apache.org/download.cgi).
- Follow the installation instructions for your operating system.
- Verify the installation:

```bash
mvn -version
```

### 3. Build the Project

Navigate to the project folder and use Maven to build the project and download dependencies:

```bash
mvn clean install -DskipTests
```

### 4. Run the Application

Run the application using Maven:

```bash
mvn spring-boot:run
```

### 5. Access the Application

Open your browser and navigate to:

```
http://localhost:8080
```

---

## Endpoints

### Main Endpoints

- **Home Page**: `/`  
  Displays the landing page of the quiz application.

- **Start Quiz**: `/quiz/start`  
  Starts a new quiz session.

- **Submit Quiz**: `/quiz/submit`  
  Submits the quiz and displays the results.

### Export Endpoints

- **Export PDF**: `/api/export/pdf`  
  Downloads the quiz results as a PDF file.

---

## File Structure

```
trivia-titans/
├── src/
│   ├── main/
│   │   ├── java/com/nyujava/main/
│   │   │   ├── controller/       # Controllers for handling HTTP requests
│   │   │   ├── model/            # Models representing database entities
│   │   │   ├── repository/       # Spring Data repositories
│   │   │   ├── service/          # Service layer for business logic
│   │   │   └── QuizApplication.java # Main entry point of the application
│   │   └── resources/
│   │       ├── static/           # Static assets (CSS, JS)
│   │       ├── templates/        # Thymeleaf HTML templates
│   │       └── application.properties # Application configurations
│   └── test/                     # Unit and integration tests
├── pom.xml                       # Maven dependencies
└── README.md                     # Documentation (this file)
```

---

## Testing

Run the unit tests using Maven:

```bash
mvn test
```


---


## License

This project is licensed under the [MIT License](LICENSE).

