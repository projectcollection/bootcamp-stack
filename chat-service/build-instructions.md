## How to build chat server 
### this is java based sockets impklemented in spring boot thathas a mongodb backend
### so first task is to connectwitha  database that can be provided as below 
    1. open the file named application.yml in the code of the chat service 
    2. then make sure you have below entry in there 
    server:
        port: 8081
        spring:
        data:
            mongodb:
            uri: mongodb+srv://username:password@cluster0.i6rqc9n.mongodb.net/chat-server?retryWrites=true&w=majority
            
### After specifying the database make sure to build docker as below 
    1. Build using  `docker build -t chat-server:devlatest . ` where . is build context
    2. ensure the image was built using `docker image ls `
    3. if the image is built use` docker run -p 8081:8081 chat-server:devlatest` 
    4. access the url localhost:8081
    5. the same must be accessible on website


======================================Backend Build Steps======================================

## How to build chat server 
### After specifying the database make sure to build docker as below 
    1. Build using  `docker build -t backen-api:devlatest . ` where . is build context
    2. ensure the image was built using `docker image ls `
    3. if the image is built use` docker run -p 8081:8081 backen-api:devlatest` 
    4. access the url localhost:8077
    5. the same must be accessible on website
### 

