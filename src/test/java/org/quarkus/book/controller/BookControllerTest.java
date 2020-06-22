package org.quarkus.book.controller;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BookControllerTest {

    @Test
    public void testBooksEndpoints() {
        RestAssured.given()
            .when()
            .get("/api/books")
            .then()
            .statusCode(200);
    }
}