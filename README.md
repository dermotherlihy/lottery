# Lottery Service

### Overview

This service has the following REST endpoints

/v1/tickets             POST

/v1/tickets             GET

/v1/tickets/{id}/lines  POST

/v1/tickets/{id}        GET

/v1/checks              PUT

/v1/checks/{id}         GET


### Instructions

To start the service, check out the code and run the following maven command

mvn clean install //build jar and runs unit tests

or

mvn clean install -Pintegration //builds jar, runs unit and integration tests


then

java -jar target/lottery-service-0.0.1-SNAPSHOT.jar

To test the service

A postman collection is available at

https://www.getpostman.com/collections/ea17faedd95e8fdd1239

with some sample API calls
