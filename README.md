# ecommerce-order-processing
The app consists of Spring-Boot based 5 different micro-services, 1 api-gateway service and 1 discovery service.

## Features
1. Uses Netflix Eureka for service discovery
2. Uses Kafka for bulk order processing
3. Uses Swagger OpenApi version 3.0 for API documentation (available at http://localhost:8080/swagger)
4. Unit tests written for item-service, order-service, address-service, payment-method-service
5. Normalized database schema available in Database Schema.xlsx
6. Exposes /orders-bulk endpoint as a client for processing orders in bulk
7. Exposes /orders-bulk/status endpoint as a client for processing order status updates in bulk

All the micro-services are dockerized and deployable to docker using docker-compose.yml file included in the project.

## Prerequisites

1. Java 11
2. Docker

## Steps to run the project

1. execute build_run.sh

## Improvements

1. Logging and metrics can be improved, can use ELK stack to ingest logs and generate metrics
2. Async communication among microservices. Instead of communicating via Http, the microservices can send messages to each other.
3. Unit testing can be improved
4. Integration tests can be added
5. Batch processing servers should be different from normal API servers
6. 

## Bugs

1. Updating bulk status removes items, addresses, payment methods