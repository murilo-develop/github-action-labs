# Work Station Rack Pool Deployment

TBD

## Database migrations

Liquibase is the manager of our migrations. In order to perform a migration with no deployment in the target environment is enabled the liquibase-maven-plugin in the pom.xml.

### 1) Create application schema

There is a file in db/init-db/init.sql its content is the schema creation and a plugin addition to the database server.

In local environment either the docker environment or the automated test are performing that sql script automatically.

However, in the real environments in order to protect our application the execution of that script should be done manually in the database.

To do so, go to the database, log-in into and perform each SQL statement in there.

### 2) Create a database application user

It is a good practice to create a unique user to manage data in the database through our application. Usually the username is the name of the application followed by "_connect".

For instance, "workstation_rack_connect".

Create that new role in postgres.

```
    CREATE ROLE workstation_rack_connect WITH password 'REPLACE_IT_WITH_A_REAL_PASSWORD';
```

### 3) Perform Liquibase using Maven

To perform liquibase using maven run the command below from the root path of the application.

```
    ./mvnw liquibase:update -e -Pdb-migration -Ddb.url=jdbc:postgresql://<url>:<port>/<database-name> -Ddb.user=<user> -Ddb.password=<password> -Ddb.connectUsername=<user_connect>
```
