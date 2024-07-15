# Demo video


## Problem Statement : 
We are creating a quiz website where a user will be able to create quizzes 
for other people to attempt, attempt quizzes created by other people and review the quizzes the 
user has attempted to see their mistakes along with the score. 
### Project Structure :  
***Backend :*** We have used spring boot as a backend with implementation of microservices 
architecture. 
To make this backend work we have 6 microservices out of which 3 are application specific and 
3 to manage the infrastructure between them. 
Application Specific microservices : 
1. UserService : This service is used to manage the registration and authentication of users. 
It provides the details of users to other services when required Eg. When we create a quiz 
the user creator information is brought from the user service. It also requests other 
services when we want information about the attempts of quizzes by the user. 
2. QuizService : This service is used to store, retrieve and manipulate information about the 
quizzes and questions this is done using the quiz and questions table in our postgres 
database. 
3. Interaction Service : This service is used to keep track of quizzes attempted by our 
users. Here we have used a mongo db database it stores and provides this information 
during quiz review.
### Infrastructure Services :  
1. Service Registry : This service is used to run the eureka server so the other services can 
act as eureka client and register themselves with the eureka server. This allows us to 
make inter service communication easier as now we can provide urls with the service 
name in the resttemplate object in our code.
2. Api Gateway : This microservice is used to provide an api gateway so that all our 
endpoints can be exposed from a single port and then with the help of routes the requests 
are forwarded to other microservices via the service registry. It also performs load 
balancing when we have multiple instances of service running.
3. Config Server : This service is used to handle common configuration of the other services. Eg all 
the services except for service registry have a  client side configuration so we make a public 
github repository make a application.yml file in it and then the config service does the 
configuration of other services from the application.yml file and we don't have to repeat the same 
code 5 times. 
### Articture diagram : 

![image](https://github.com/user-attachments/assets/13673421-731c-4d1b-855c-00915d5fc2ba)
