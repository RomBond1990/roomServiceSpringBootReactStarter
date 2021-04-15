## Room Service

You can:
- create a room
- go in and turn on / off the lamp
- you can enter a room located only in your country (check by IP) 
-----
##### Use deploy branch for deployment or for expose a local development server to the Internet 


##### Use master branch for starting from IDE


This project includes:
1. https://github.com/RomBond1990/room-with-bulb-react
2. https://github.com/RomBond1990/room_with_bulb_test


This is a multi-module Spring Boot React Apache Maven starter app with good defaults. The react app is built using [create-react-app](https://github.com/facebookincubator/create-react-app).

This project provides a productive setup for building Spring Boot React applications. The application is divided into two Maven modules:

1. `api`: This contains Java code of the application.
2. `ui`: This contains all react JavaScript code of the application.



## Running the backend for development mode

There are multiple ways to run the backend. For development, you can use your favorite IDE and run the
`com.rbondarovich.Application`

Backend will be accessible at `http://localhost:8080`

## Running the frontend for development mode

**You will need Node 12+ and npm to run the dev server and build the project**.


```
 cd ui
 npm install
 npm start
```
Frontend will be accessible at `http://localhost:3000`

## Running the full application

You can build the package as a single artifact by running the 
```
mvn clean install
```
Next, you can run the application by executing:

```
java -jar api/target/spring-boot-react-starter-api.jar
```

The application will be accessible at `http://localhost:8080`

## Docker Setup

First you have to build a jar in the /api

```
cd api
mvn clean install
cd ..
```

To build the docker images and start the containers using Docker Compose run the following command. 


for *nix systems:

```
sh docker.sh
```
for windows:

```
bash docker.sh
```

You can view running docker containers by executing following command.

```
docker container ps
``` 

To stop and remove all docker container you have to run following command. 
This command should be run from project root.

```
docker-compose stop && docker-compose rm --force
``` 

The application will be accessible at `http://localhost`