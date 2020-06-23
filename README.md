to start project dev profile
./mvnw quarkus:dev

docker pull postgres
docker run --name some-postgres -e "POSTGRES_PASSWORD=postgres" -p 5432:5432 -d postgres

after create a database called `library`
