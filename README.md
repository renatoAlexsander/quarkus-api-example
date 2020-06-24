[![Build Status](https://travis-ci.org/renatoAlexsander/quarkus-api-example.svg?branch=master)](https://travis-ci.org/renatoAlexsander/quarkus-api-example)

Before you run the project, you should have a postgresql configured.

`docker pull postgres` to download postgresql docker container
`docker run --name some-postgres -e "POSTGRES_PASSWORD=postgres" -p 5432:5432 -d postgres` to run docker container called some-postgres at port 5232, by default your username is "postgres".

after you need to create a database called `library`

to start project dev profile
./mvnw quarkus:dev
