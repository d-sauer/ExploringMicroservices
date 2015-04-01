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
   - Application.java in root package which extends _ms.api.service.Microservice_
   
```
package ms.services.time;
import ms.api.service.Microservice;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends Microservice {
    public static void main(String [] args) {
        Microservice.run(Application.class, args);
    }
}
```

  - AppConfiguration.java in root package. Used to expose microservice configuration when is packed with other microservices as monolith WAR.

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


# Roadmap (todo)
+ Build monolithic WAR
  - Build monolithic WAR that contains all microservices in the project.
   - Create application.properties which can be overridden from from parent structure. And be able to manage each service independently from the parent folder.
   - Create service which uses two different database resource, and pack those two services in monolithic build, and manage used database of those two services.

