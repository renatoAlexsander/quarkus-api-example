[![Build Status](https://travis-ci.org/renatoAlexsander/quarkus-api-example.svg?branch=master)](https://travis-ci.org/renatoAlexsander/quarkus-api-example)

docker pull postgres
docker run --name some-postgres -e "POSTGRES_PASSWORD=postgres" -p 5432:5432 -d postgres

after create a database called `library`

to start project dev profile
./mvnw quarkus:dev
