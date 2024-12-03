package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;
import org.json.JSONObject;

public class UsersService {
User user;

    static Response createUser(user) {
        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", "Karolina222");
        body.put("surname", "Testowa");
        body.put("email", "testowa@gmail.com");
        body.put("position", "Test_Engineer");
        //POST /users + user as body (see model below)
        Response postResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("http://localhost:8080/users ");
        postResponse.prettyPrint();
        return postResponse;
    }

    static Response getUser(Integer userId) {
        Response getResponse = RestAssured
                .given()
                .get("http://localhost:8080/users/"+ userId);
        getResponse.prettyPrint();
        return getResponse;
    }
}
