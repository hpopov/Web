# ITCampus
ITCampus is a platform, where students can create their profiles as applicants, fill their personal qualities (their university marks are supposed to be loaded automatically, but, unfortunately KPI Campus API is still under development). On the other side, employers can also register here, browse the list of potential candidates and pick up the Junior (or even Middle) they need.    
ITCampus is written using Spring Boot + Angular stack.

## Prerequisities

In order to run **backend** part of the app successfully, you should have the next items installed:
- Docker (with docker-compose);
- JDK 1.8+;
- Maven (optional).  

In order to run **frontend** part, the next must be installed:  
- Node.js (tested on 12, but initially project was written using lower version);
- NPM;  
Alternatively, you can just make use of NVM.

### Backend configuration

Currently, image server is introduced as a matter of communication with filesystem on backend server directly. In order to have file manipulation working, please, make sure to have `itcampus.file.directory` and `itcampus.user.defaultAvatarPath` properties configured in application.properties. Otherwise you won't be able to load temporal images using REST API (see **Interactive REST API with Swagger** section).  
`itcampus.file.directory` must point to an **existing** directory and `itcampus.user.defaultAvatarPath` must point to an **existing** file in that directory.  
Unfortunately, the images are not preloaded, so for existing user (tordek) there will be no images initially. However, it can be fixed using REST API to update user and user projects images.

## Launch Procedure

To run **backend** app locally, you have to perform the following set of actions (from the bash-console of the project root folder):  
- `cd ITCampusWithBackend` -- it is a directory where backend app is being hold;
- `docker-compose up -d` to start dockerized mysql local database. If any errors are logged, then you probably have another MySQL launched locally. Make sure you stop that instance and enter the command above again;
- `mvn spring-boot:run` to launch an app itself (in case you don't have Maven installed, you can run `mvnw` instead of `mvn`). This will use SSL and 8443 port on localhost.  

To run **frontend** app locally:  
- `cd ITCampus` -- a directory, that contains Angular app;
- `npm i --saveDev` to install npm libraries. Ignore all the vulnerablility notices, as this is for demo purposes only;
- `ng serve` to launch Angular app. This will use plain HTTP and 4200 port on localhost;  

## Navigating the app

When the backend server is started, it automatically populate database with some initial data. You can reveal it, both using Angular app and Swagger documentation.  
There are, however, some essential knowledge, that cannot be discovered, it is initial user credentials:
- Username: **tordek**
- Password: **tordek**

### Using angular application

The main page loaded is the login page. You have to enter user credentials (either prepopulated ones, that are listed above, or your own one, if you register a user using REST API, as stated below);
When login attempt is successful, you will be redirected to user profile page. You can explore it and, if it is the profile of the user on behalf of which you are acting, you are also allowed to edit its information.  

### Interactive REST API with Swagger

This project leverages **Open API Specification** annotations and springdoc-openapi-ui library, so REST API interactive documentation is automatically generated.  
To visit and check it out, follow https://localhost:8443/swagger-ui.html  
As the SSL certificate configured in this project is self-signed one, you probably will be prompted with an alert in your browser. Just accept the 'risk' and move on.
