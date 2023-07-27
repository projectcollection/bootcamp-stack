# our base build image
# FROM maven:3.6.0-jdk-8 as maven
FROM maven:3.6.3-jdk-11 as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package -DskipTests

# our final base image
FROM openjdk:11.0.11-jre-slim

# set deployment directory
WORKDIR /my-project
RUN mkdir -p /myproject/uploadFile

# copy over the built artifact from the maven image
COPY --from=maven target/springboot-starterkit-1.0.jar ./

# set the startup command to run your binary
CMD ["java", "-jar", "./springboot-starterkit-1.0.jar"]
