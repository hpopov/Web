# ITCampus backend
ITCampus is a platform, where students can create their profiles as applicants, fill their personal qualities (their university marks are supposed to be loaded automatically, but, unfortunately KPI Campus API is still under development). On the other side, employers can also register here, browse the list of potential candidates and pick up the Junior (or even Middle) they need.  

## Prerequisities

In order to run this successfully, you should have the next items installed:
- Docker (with docker-compose);
- JDK 1.8+;
- Maven (optional).

## Launch Procedure

To run this app locally, you have to perform the following set of actions (from the bash-console of the project root folder):  
- `docker-compose up -d` to start dockerized mysql local database. If any errors are logged, then you probably have another MySQL launched locally. Make sure you stop that instance and enter the command above again;
- `mvn spring-boot:run` to launch an app itself (in case you don't have Maven installed, you can run `mvnw` instead of `mvn`).

## API Discovery

This project leverages **Open API Specification** annotations and springdoc-openapi-ui library, so REST API interactive documentation is automatically generated.  
To visit and check it out, follow https://localhost:8443/swagger-ui.html  
As the SSL certificate configured in this project is self-signed one, you probably will be prompted with an alert in your browser. Just accept the 'risk' and move on.
