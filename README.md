# Link Shortener - Backend

This is the backend component of the Link Shortener application. It provides the server-side functionality for generating short URLs and handling redirection to the original URLs.

## Technologies Used

- Java
- Spring Boot
- MongoDB (NoSQL database)

## Prerequisites

Before running the backend application, make sure you have the following dependencies installed on your system:

- Java Development Kit (JDK) 17 or higher
- MongoDB (installed and running)

## Getting Started

Follow these steps to set up and run the backend of the Link Shortener application:

1. Clone the repository:
git clone https://github.com/vivekanand111/linkShortenerServer.git

2. Navigate to the backend directory:
cd directory-path

3. Configure the MongoDB connection:
- Open the `src/main/resources/application.properties` file.
- Set the appropriate values for the `spring.data.mongodb.host` and `spring.data.mongodb.port` properties to match your MongoDB configuration.

4. Run the application:
- You can run the application using your preferred IDE or build tools such as Gradle.
Use Command to Run: ./gradlew bootRun

5. The backend API will be accessible at `http://localhost:8080/api/`.
## API Documentation
The backend API provides the following endpoints:
- `POST /api/links`: Create a short URL by providing the original URL and an expiration time.
- `GET /api/links/{shortUrl}`: Retrieve the original URL associated with the given short URL.


