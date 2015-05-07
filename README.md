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


## Build JAW/WAR
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
  - shared
  - calculator
  - time


## Features
- Add support for nesting properties file inside monolithic build with microservice namespace. Support to target specific property value for specific micorservice

## ToDo
- simplify develpment
  + remove Microservice extends, and use @EnableCXP (Check Spring-JMS as example @EnableJms)
- add support for YAML configuration
- datasource