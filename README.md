This is our fms ia2 repository
I have made a total of 5 services
#### 1. USER-SERVICE port number 8080
#### 2. QUIZ-SERVICE port number 8081 
#### 3. INTERACTION-SERVICE  port number 8083
#### 4. SERVICE-REGISTRY port number 8761
#### 5. APIGATEWAY-SERVICE port number 8084
## postgres database configuration details for quiz and user service
port : localhost:5432 database name : quiz-website
username and password are whatever you set on your local machine for pgadmin so change the url in application.properties accordingly.
If possible figure out how to use environment variables in spring boot I wasnt able to figure it out so that we can have aur local config isolated and not on githublike it is now.
application.properties define datasource for quiz and user service
spring.datasource.url=jdbc:[your pgadmin username]://localhost:5432/quiz_website
spring.datasource.username=[your pgadmin username]
spring.datasource.password=[your pgadmin password]
## mongo db database configuration for interaction service
spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=quiz-website 
here quiz-website is our database name and this database should contain a collection by name "userquizinteraction"
In all 3 services dont define any tables or documents manually do it via @Entity annotation in the entities package of each service
queries can be written from the repositories package in each service 

Interservice communication is done by the service registory and resTemplate via http requests.

I have tried to implement api gateway but didnt make any progress today the problems described are shown below.
1. For api gateway we are using spring-cloud-gateway dependency and in the documentation it warns us that 
its unstable on versions of springboot greater than 
2. But the spring initializer no longer allows us to choose lower the version of spring boot it only show >=3 versions
So I tried to lower it via pom.xml file but it dosent work as that microservice cannot run and shows error as it was initialised with 3.2.3 version
when I first ran the api gateway service it was running but it used tomcat as its web server. 
But in the tutorial I am refering https://youtu.be/ubHa5I3yP70?feature=shared at time stamp 4:22:22 it shows Netty as web server.
3. I tried to get it working with netty as well but it didnt work those attempts are documented here in this
stackoverflow question raised by me https://stackoverflow.com/questions/78126684/i-want-netty-webserver-instead-of-tomcat-in-my-spring-boot-project-with-webflux
4. Then I wrote the routes in the application.yml file of api quteway microservice and prayed that the requests coming via 8084 port i.e port of api gateway service will be redirected 
but it said that the endpoints werent found the requests to the individual microservices work but not via gateway.
5. After this I gave up made this repository and wrote this readme.md file please try to resolve some of these bugs.
6. I will now be focusing on MP till sunday afternoon
