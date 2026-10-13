
package com.naufal.qa.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostApiTest {

    private static final String BASE_URL =
            "https://jsonplaceholder.typicode.com";

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void getPostByIdReturnsCorrectData() {
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertEquals(1, response.jsonPath().getInt("id"));
        assertEquals(1, response.jsonPath().getInt("userId"));

        String title = response.jsonPath().getString("title");

        assertNotNull(title, "Post title should not be null");
        assertFalse(title.isBlank(), "Post title should not be empty");
    }

    @Test
    void getPostsByUserIdReturnsMatchingPosts() {
        Response response = RestAssured
                .given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int postCount = response.jsonPath().getList("$").size();

        assertFalse(
                postCount == 0,
                "The API should return at least one post"
        );

        List<Integer> userIds =
                response.jsonPath().getList("userId", Integer.class);

        for (Integer userId : userIds) {
            assertEquals(
                    1,
                    userId,
                    "Every returned post should belong to user 1"
            );
        }
    }

    @Test
    void createPostReturnsCreatedResource() {
        String title = "QA Automation Test";
        String body = "Testing POST request with REST Assured";
        int userId = 1;

        String requestBody = """
                {
                    "title": "QA Automation Test",
                    "body": "Testing POST request with REST Assured",
                    "userId": 1
                }
                """;

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        assertEquals(
                title,
                response.jsonPath().getString("title")
        );

        assertEquals(
                body,
                response.jsonPath().getString("body")
        );

        assertEquals(
                userId,
                response.jsonPath().getInt("userId")
        );

        assertNotNull(
                response.jsonPath().get("id"),
                "Created response should contain an ID"
        );
    }
}