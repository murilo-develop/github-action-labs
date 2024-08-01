# Work Station Rack Pool

Through the APIs provided in this Java WebService we can manage bookings for a pool of Work Station Rack.

## What is a Work Station Rack?

A Work Station Rack it is a set of the Vehicle's hardware components where the product teams can try theirs implementation.

## Why is this service for?

There are different sort of Work Station Racks and consequently each one has its own specific use. What causes concurrence among the product teams.

By using this service each product team can book a timeframe to use a specific Work Station Rack.

Therefore, the product team can plan better and organize themselves according to the Work Station Rack availability.

## Main GOAL

Become the source of truth about the availability of Work Station Racks, also the only tool for the BMW's product teams manage the bookings for a pool of Work Station Rack.

## Application architecture

We aim for a combination of elegance and simplicity. The application design will evolve during development as engineers create code to meet the business needs, without any hard design defined.

However, some standards are necessary to allow the collaboration among the team members.

#### Clean architecture

> The entity-control-boundary (ECB), or entity-boundary-control (EBC), or boundary-control-entity (BCE) is an architectural pattern used in use-case driven object-oriented programming that structures the classes composing high-level object-oriented source code according to their responsibilities in the use-case realization.<sup>[[1]](#ref-1)</sup>.

Get familiar with that concept through the links below.

- [Clean Coder Blog - Clean Architecture](https://blog.cleancoder.com/uncle-bob/2011/11/22/Clean-Architecture.html)
- [Clean Coder Blog - The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Adam Bien - Bureaucratic Design With Java EE](https://www.adam-bien.com/roller/abien/entry/bureaucratic_design_with_java_ee)

The project uses the ECB pattern to decouple the business logic from external part of the product like the database and the UI.

It is flat and easy to organize the application's components.

#### Code structure

```
db
   |-- changelog // Add database changes to a file
   |   |-- local // Used locally in the test context
   |   |   |-- 1.0.0 // Changesets: Define database changes
   |   |   |   |-- ddl // Data Definition Language is a type of SQL command used to define data structures and modify data
   |   |-- master // Used in the pipeline to change a database in some productive environment
   |   |   |-- 1.0.0 // Changesets: Define database changes
   |   |   |   |-- ddl // Data Definition Language is a type of SQL command used to define data structures and modify data
   |-- init-db // Hold script to initialize the database structure
src
   |-- main
   |   |-- docker // Hold docker files used to build application's image
   |   |-- java
   |   |   |-- com
   |   |   |   |-- bmw
   |   |   |   |   |-- ctw
   |   |   |   |   |   |-- workstation
   |   |   |   |   |   |   |-- rack
   |   |   |   |   |   |   |   |-- api
   |   |   |   |   |   |   |   |   |-- booking // Domain package
   |   |   |   |   |   |   |   |   |   |-- boundary // The software in this layer contains application specific business rules. It encapsulates and implements all of the use cases of the system. These use cases orchestrate the flow of data to and from the entities, and direct those entities to use their enterprise wide business rules to achieve the goals of the use case.
   |   |   |   |   |   |   |   |   |   |-- control // The software in this layer is a set of adapters that convert data from the format most convenient for the use cases and entities, to the format most convenient for some external agency such as the Database or the Web.
   |   |   |   |   |   |   |   |   |   |-- entity // Entities encapsulate Enterprise wide business rules. An entity can be an object with methods, or it can be a set of data structures and functions.
   |   |   |   |   |   |   |   |   |-- infrastructure // Hold all classes needed to support the business application
   |   |   |   |   |   |   |   |   |   |-- constants // All common constants, like the ROUTES used in the Resources path
   |   |   |   |   |   |   |   |   |   |-- database // General database rules, most used to audit log features
   |   |-- resources
   |-- test
   |   |-- java
   |   |   |-- com
   |   |   |   |-- bmw
   |   |   |   |   |-- ctw
   |   |   |   |   |   |-- workstation
   |   |   |   |   |   |   |-- rack
   |   |   |   |   |   |   |   |-- api
   |   |   |-- testsupport
   |   |-- resources
   |   |   |-- assets // Hold static content (css, img, html, js) to support the application's document
   |   |   |   |-- img
```

Reference books:

- [Object-Oriented Software Engineering: A Use Case Driven Approach](https://www.amazon.com/Object-Oriented-Software-Engineering-Approach/dp/0201544350)
- [Clean Architecture: A Craftsman's Guide to Software Structure and Design](https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164)

## Development environment setup

### Stack

* [Java 21](https://www.azul.com/downloads/#zulu)
* [Maven](https://maven.apache.org/)<sup>[1](#note-1)</sup>
* [JakartaEE](https://jakarta.ee/specifications/)
* [Eclipse Microprofile programming model for JakartaEE](https://projects.eclipse.org/projects/technology.microprofile)
* [Quarkus WebServer](https://quarkus.io/about/)
* [Postgres](https://www.postgresql.org/about/)
* [Docker](https://docs.docker.com/get-started/overview/)

### [Set IntelliJ](SETUP-INTELLIJ.md)

## Running the application

> <span style="color: #800080">**_IMPORTANT_**</span>
> 
> Docker must be installed, it should be up and running in the environment before try to run the application.

We two different ways to run the application.

### Containerized (Docker)

#### 1) Build the application
- Open an external terminal or an embedded terminal in IntelliJ and the command bellow.<br/>
    ```
    ./mvnw clean package
    ```
  Should be displayed as output success messages about test execution and a build success message.

#### 2) Build and startup the application's containers
- Open an external terminal or an embedded terminal in IntelliJ and the command bellow.<br/>
    ```
    docker-compose up --build -d
    ```
  Should be display as output success messages about the creation and start-up of application's container.

#### 3) Do a smoke test
- Open the file [application-service.http](src/test/resources/application-service.http)
- Search for "Run with: No Environment" in the file top bar.
- Change it to local.
- Click up on the play button on the left of any http resource described, for instance, "Create a Team".

### Quarkus standalone + Database Containerized (Docker)

#### 1) Build and startup the database containers
- Open an external terminal or an embedded terminal in IntelliJ and the command bellow.<br/>
    ```
    docker-compose -f docker-compose-sources.yml up --build -d
    ```
  Should be display as output success messages about the creation and start-up of application's container.

#### 2) Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

#### 3) Packaging and running the application using über-jar

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

#### 4) Do a smoke test
- Open the file [application-service.http](src/test/resources/application-service.http)
- Search for "Run with: No Environment" in the file top bar.
- Change it to local.
- Click up on the play button on the left of any http resource described, for instance, "Create a Team".

> <span style="color: #0000ff">**_NOTE_**</span>
> 
> In both running mode when you perform the smoke test your request must be processed by the application successfully, otherwise check the logs to see what is happening.

## Swagger-ui

By default, Swagger UI is accessible at **/q/swagger-ui**

## References
<a id="ref-1">[1]</a>
Entity-control-boundary (2023) Wikipedia. Available at: https://en.wikipedia.org/wiki/Entity-control-boundary (Accessed: 14 June 2024).

## Notes
<a id="note-1">[1]</a>
Or use the wrapped maven within the application root folder
