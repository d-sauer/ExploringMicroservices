# Exploring Microservices
Dependencies
- Java 8
- Spring Boot 1.2.+
- Gradle 2.+


## How to run
All services are located in **'services/<service_name>'**.

Run service from console type:

```
$gradle bootRun
```

Run service from IDE:
Each service has _Applicatio.java_ in the root package with _main_ method to run. By that it's possible to easily run service in debug mode.


## How to create new microservices
+ All microservices are located in **services** folder.
+ All microservices have:
   - Application.java
   - To enable CXP nature, add @EnableCXP annotation to Application class, or any other @Configuration class   


  - AppConfiguration.java in root package. Used to expose microservice configuration when is packed with other microservices as monolith WAR.
    This file is references under META-INF/spring.factories

```
package ms.services.time;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ms.services.time.rest.TimeController.class })
public class AppConfiguration {
// list of used beans
}
```

Example of spring.factories
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=ms.monolithic.MonolithicConfiguration
```


- Register configuration that can be discoverable when microservices is packed in JAR.
Create file in project folder: _src/main/resources/META-INF/spring.factories_. 
And add a reference to microservice _AppConfiguration_

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=ms.services.time.AppConfiguration
```


## Build JAR/WAR
To build the executable JAR/WAR for each microservice, run gradle command in the service folder.
Gradle will create jar and war files inside **build/libs**
```
$gradle build
```

To create a deployable war file for servlet container as Tomcat, WebSphere, etc. execute gradle command, which will create war file inside **build/libs**. This war file doesn't contain Spring Boot files and other library dependencies to run as standalone war/jar file.
```
$gradle war
```


## Project structure
+ api
+ commons
+ services
  - calculator
  - time
  - userService (example with one DataSource: user)
  - bankService (example with two DataSources: bank, audit)


# HowTo

## Enable CXP nature
For enabling CXP nature we simply need to add ```@EnableCXP``` annotation to startup class.

## Swagger integration
By enabling CXP nature with ```@EnableCXP``` there is also Swagger integration for automate documenting RESTfull API.
To access Swagger use this URL's: http://localhost:8080/swagger-ui.html and http://localhost:8080/v2/api-docs/
 
## Features
- Support for nesting properties file inside monolithic build with microservice namespace. Support to target specific property value for specific microservice
- Support for custom creating DataSource and merging datasource (for monolithic build)

## ToDo
- Simplify DataSource configuration, by providing default properties