# API MCA BACKEND

## Description
The MCA BACKEND API provides services for perform stock updates of existing video games using Kafka messaging queues, 
as well as exposing the API to the front end to perform the relevant requests and obtain the desired information from the embedded database..

## Base URL
The base URL for all endpoints is: `http://localhost:8080/api`

## Prerequisites
Before using the API, ensure that all services are up and running by executing the following command:

```bash
docker-compose up -d
```
The services launched are as follows:
#### 1. simulado
#### 2. zookeeper-server
#### 3. kafka-server
#### 4. kafdrop-server

## Endpoints

### 1. Get Sagas By Game ID
#### Description
This endpoint returns a list of all sagas related with a game ID

#### Method
`GET`

#### Route
`/game/{gameId}/saga`

#### Parameters
`{gameId}`: The ID of VideoGame.

#### Example Request
```bash
curl -X GET http://localhost:8080/api/game/1/saga
```
#### Postman Collection
You can find a Postman collection [Postman's collection](docs/MCA_BackEnd.postman_collection.json) containing requests to test this endpoint:

##### 1. getSagasByGameIdOK: A request to retrieve sagas by a valid game ID.
##### 2. getSagasByGameId404: A request to simulate a scenario where the specified game ID is not found.
##### 3. getSagasByGameId400: A request to simulate a scenario where an invalid game ID format is provided.


### 2. Get Sagas Related By Saga ID
#### Description
This endpoint returns a list of all sagas related with a saga ID

#### Method
`GET`

#### Route
`/game-saga/{sagaId}/related-sagas`

#### Parameters
`{sagaId}`: The ID of Saga.

#### Example Request
```bash
curl -X GET http://localhost:8080/api/game-saga/1/related-sagas
```
#### Postman Collection
You can find a Postman collection [Postman's collection](docs/MCA%20BackEnd.postman_collection.json) containing requests to test this endpoint:

##### 1. getSagasRelatedBySagaIdOK: A request to retrieve sagas by a valid game ID.
##### 2. getSagasRelatedBySagaId404: A request to simulate a scenario where the specified game ID is not found.
##### 3. getSagasRelatedBySagaId400: A request to simulate a scenario where an invalid game ID format is provided.

## Kafka Messaging System
### Overview
The Kafka messaging system facilitates the communication between different components of the MCA BACKEND API. The system consists of two main components: the KafkaMessageProducer and the KafkaMessageConsumer.

### KafkaMessageProducer
The KafkaMessageProducer is responsible for sending events stored in an [events.csv](src/main/resources/events.csv) file to a Kafka topic. Each event in the CSV file represents a change in stock for a specific video game. The producer sends these events to the Kafka topic, where they can be consumed by other components of the system.

### KafkaMessageConsumer
The KafkaMessageConsumer listens to the Kafka topic for incoming events. When an event is received, the consumer maps the event to a corresponding object and updates the existing stock in the database accordingly. This allows the system to keep track of stock changes in real-time and ensure accurate stock management.

### Usage
To utilize the Kafka messaging system, follow these steps:

#### 1. Ensure that Kafka, Zookeeper, and Kafdrop are up and running using the provided docker-compose.yml file.
#### 2. Start the KafkaMessageProducer to send events to the Kafka topic.
#### 3. Start the KafkaMessageConsumer to listen for incoming events and update the stock in the database.
#### 4. With this messaging system in place, the MCA BACKEND API can efficiently manage stock updates and ensure data consistency across the platform.


