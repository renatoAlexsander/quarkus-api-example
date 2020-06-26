[![Build Status](https://travis-ci.org/renatoAlexsander/quarkus-api-example.svg?branch=master)](https://travis-ci.org/renatoAlexsander/quarkus-api-example)

Execute that project using GraalVM to better perfomance

you should have postgresql in your machine.

you may follow these commands bellow for install postgres image from docker.
`docker pull postgres` download image from docker hub
`docker run --name some-postgres -e "POSTGRES_PASSWORD=postgres" -p 5432:5432 -d postgres` 
run container called some-postgres at port 5432, by default your username is "postgres".

now, execute this command ./mvnw quarkus:dev, to start the project using dev profile.
It can access on address localhost:8082.
