# Deploying and Running Multiple Spring Boot Microservices with MySql using Docker Compose
Building multiple microservices using Spring Boot, and deploying and running them using docker compose.

## Table of contents
* [Architecture](#architecture)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
* [Sample Requests](#sample-requests)
* [About me](#about-me)
* [Acknowledgments](#acknowledgments)

## Architecture
The project follows the *microservices* architectural style. 
We have three independent microservices (User, Site and Organization), which connect to MySql database and  communicate with each other using RestTemplate. We use *Docker Compose* for deploying and running them on docker containers.

## Technologies
This project is created using the following technologies:

1. Java 11
2. Maven Dependency Management	
3. Spring Boot in microservices development:
	
	+ Spring Web
	+ Spring Data JPA
	+ Spring Devtools
	+ Spring Actuator
	
4. MySql database
5. Docker

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
You need to install the following software:
 * Java JDK 1.8+
 * Maven 3.0+
 * Git client
 * Docker Compose: To [install docker-compose](https://docs.docker.com/compose/install/)

### Developing Steps
The steps to be taken in order to create working microservices-based system using Spring boot and Docker:
	
**Step 1.** Building the *microservices* using Spring Boot and communication between them by RestTemplate:

+ [Account microservice](account-service)
+ [Payment microservice](payment-service)

**Step 2.** Creating the *Dockerfile* for each service. Docker file is a list of commands that we want the docker engine to execute. 

Go to the directory for each spring boot project and create a Dockerfile:

+ Account microservice [Dockerfile](account-service/Dockerfile)
+ Payment microservice [Dockerfile](payment-service/Dockerfile)

For example Dockerfile for Account service:

```bash
FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8083
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.yml"]
```

**Step 3.** Creating [docker-compose.yml](docker-compose.yml) as follows:

```bash
version: '3'
services:
  account-service:
    build:
      context: account-service
      dockerfile: account-service/Dockerfile
    ports:
      - 8083:8083
    networks:
      - my-network
  payment-service:
    build:
      context: ./payment-service
      dockerfile: Dockerfile
    ports:
      - 8084:8084
    networks:
      - my-network

networks:
  my-network:
```
**Step 4.** Running and testing applications: 

Run docker-compose up and Compose starts and runs your entire services.
 
### Setup
To run this project, install it locally as follow:

1. **Clone the application**

	```bash
	git clone https://github.com/mozahidone/microservice.git
	```

2. **Create a JAR file for each service**

	Run maven command - *clean install*, and a jar file gets created in the target directory for each service like so:

	```bash
	cd base
	maven clean install
	```	
	+ *service_directory*: the directory of the service (account-service and payment-service).	

3. **Start the User, Site, Organization and MySql using docker-compose**

	The project includes a [*docker-compose.yml*](docker-compose.yml)  file so you can Run *docker-compose up* to start entire services, no installation needed.

	```bash
	cd base
	docker-compose up -d
	```
	You can see - 
	- Building an image from Dockerfile for each service and image for MySql database If it does not exist.
	- Building containers (account-service, payment-service and mysql) using the images.
	- Starting the services (account-service, payment-service and MySql).
	
4. **Check the created images**

	Use the following command to check the created images:

	```bash
	docker images
	```
	
	You should find the created images like in the next figure:
	![created images](images/docker-images.png)	

5. **Check the created containers**

	Use the following command to check the created containers:

	```bash
	docker ps -a
	```
		
6. **Check the logs**

	Use the following command to check the logs for each container:

	```bash
	docker container logs CONTAINER_ID
	```
	
### Running

To access the services use the following endpoints

**Run the account/payment microservices**
	
The account microservice will start on port `8083`, So you'll be able to visit the user microservice under address `http://localhost:8083`. 
	
The site microservice will start on port `8084`, So you'll be able to visit the site microservice under address `http://localhost:8084`.

+ View info about microservices (account and payment)
	* http://localhost:8083/actuator/info
	* http://localhost:8084/actuator/info

+ Check Health for every microservice
	* http://localhost:8083/actuator/health
	* http://localhost:8084/actuator/health
	
+ Access services' APIs:
	* http://localhost:8083/account


## Sample Requests
CURL GET command samples for different (account and payment) microservices:

### account APIs
* Display info about account microservice
	
	```bash
	curl -X GET 'http://localhost:8083/actuator/info' 
	```
	
* Check Health for account microservice

	```bash
	curl -X GET 'http://localhost:8083/actuator/health'
	```
	
* List all accounts
	
	```bash
	curl -X GET 'http://localhost:8083/api/account'
	```

* Find account by Id

	```bash
	curl -X GET 	'http://localhost:8083/api/account/1'
	```

## About me

I am Mozahidul Islam. Senior Software Engineer. I like software development. You can contact me via:

* [LinkedIn+](https://www.linkedin.com/in/mozahidone/)

_**Any improvement or comment about the project is always welcome! As well as others shared their code publicly I want to share mine! Thanks!**_

## Acknowledgments

Thanks for reading. 
Did I help you?

+ Share it with someone you think it might be helpful.
+ Give a star to this project
