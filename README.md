# Receipt Processor Service

## Overview

This service is designed to process receipts according to a set of predefined rules, calculate points awarded based on the receipt content, and provide a REST API for interacting with the system. It's built using Spring Boot and can be run in any environment that supports Java and Docker.

## Prerequisites

- Docker
- (Optional) Java 11 or newer if running without Docker

## Getting Started

These instructions will cover usage information for the docker container.

### Building the Application

1. Clone the repository to your local machine:

   ```bash
   git clone <repository-url>
   cd <repository-name>

2. To build the Docker image, run the following command in the terminal:

    ```bash
    docker build -t receipt-processor-service .
    ```

    This will create a Docker image named `receipt-processor-service` using the Dockerfile in the current directory.

### Running the Application

Before running the application, ensure that port `9092` on your machine is not being used by another application. If necessary, terminate any applications currently using port `9092` to avoid port conflicts.

3. To run the application in a Docker container, use the following command:

    ```bash
    docker run -p 9092:9092 receipt-processor-service

## API Documentation

The application's REST API documentation is available through Swagger UI. This interactive documentation allows you to view and test the API endpoints directly from your web browser.

After starting the application, you can access the Swagger UI at the following URL:

[http://localhost:9092/swagger-ui/index.html](http://localhost:9092/swagger-ui/index.html)

The Swagger UI provides a comprehensive overview of all available API endpoints, including request methods, request payloads, and response objects. You can also try out the API endpoints directly from the Swagger UI by submitting requests and observing the responses.

## Quick Start Guide

This guide provides a quick overview of how to interact with the API endpoints. For detailed information, including all available endpoints and their specifications, please refer to the Swagger documentation available at `http://localhost:9092/swagger-ui/index.html` after starting the application.

### Example API Usage

#### 1. Retrieving Points for a Receipt

  - **Endpoint:** GET /receipts/{id}/points
  
  - **URL Parameters:** Replace {id} with the unique ID of the receipt.
  
  - **Description:** Retrieves the points awarded for a processed receipt, identified by the unique ID.

#### 2. Processing a Receipt

  - **Endpoint:** `POST /receipts/process`
  
  - **Content-Type:** `application/json`
  
  - **Description:** Sends receipt data for processing and returns a unique ID for the receipt.
  
  - **Request Body Example:**
      ```json
      {
        "retailer": "Example Store",
        "purchaseDate": "2024-01-22",
        "purchaseTime": "15:00",
        "items": [
          {"shortDescription": "Item 1", "price": "10.00"},
          {"shortDescription": "Item 2", "price": "20.00"}
        ],
        "total": "30.00"
      }
