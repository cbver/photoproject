## Photoproject
Steps to Run the Project : Project is Maven project can be ran with following Steps

a: With Maven Goal 

`mvn spring-boot:run`
  
  
Goto browser and hit

  http://localhost:8080

First page having login and register links to register, Once user register, user can upload photo and view the uploaded images.

Logout to login with other user

b: The respective Docker images can also be created by

    mvn clean install -Ddocker
  
    docker run -p <port>:9091 photoproject


