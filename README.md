# Weather Monitoring System

This project provides an API for retrieving weather data and calculating average temperatures. The system consumes weather data, stores it in a database, and provides various endpoints to access the stored data.

## Features

- Retrieve all weather data from the database.
- Get the average temperature for a specific city.
- Supports cities such as Riyadh, Jeddah, Mecca, Medina, Dammam, Khobar, Abha, Tabuk, Taif, and Hail.
- Integrates with Kafka for real-time data consumption.
  
## Endpoints

### 1. Get All Weather Data

- **Endpoint**: `localhost:8081/api/v1/weather/all`
- **Method**: GET
- **Description**: Returns all weather data stored in the database, including the location, latitude, longitude, date, and temperature degree.
- **Sample Response**:
  ```json
  [
      {
          "id": "26054650-356e-4a28-aa60-cf4cbaf8ee1f",
          "location": "Hailar",
          "latitude": 49.2,
          "longitude": 119.7,
          "date": "2024-09-07 21:33",
          "degree": 15.7
      },
      {
          "id": "87a7ef92-f1a5-4a0c-9468-4f8dcd99b26b",
          "location": "Riyadh",
          "latitude": 24.7,
          "longitude": 46.7,
          "date": "2024-09-07 21:35",
          "degree": 36.2
      }
  ]

### Get Average Degree for a specific city

-**Endpoint**: `localhost:8081/api/v1/weather/avg/{city}`
- **Method**: GET
- **Description**: Retrieves the average temperature for a specified city.
- **Sample Response**:
  ```json
    Riyadh average temperature = 41.5
  
