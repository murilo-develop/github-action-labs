#!/bin/bash

./mvnw liquibase:status -e -Pdb-migration -Ddb.url=${DB_URL} -Ddb.user=${DB_USER} -Ddb.password=${DB_PASSWORD} -Ddb.connectUsername=${DB_CONNECT_USERNAME} > liquibase_status.txt

if grep -q "changesets have not been applied" liquibase_status.txt; then
  echo "Pending changesets found. Running liquibase update."
  ./mvnw liquibase:update -e -Pdb-migration -Ddb.url=${DB_URL} -Ddb.user=${DB_USER} -Ddb.password=${DB_PASSWORD} -Ddb.connectUsername=${DB_CONNECT_USERNAME}
else
  echo "No pending changesets found. Skipping liquibase update."
fi
