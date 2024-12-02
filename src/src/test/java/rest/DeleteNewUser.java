package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteNewUser {
    @Test
    public void deletedUserNotFound() {
        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", "Karolina222");
        body.put("surname", "Testowa");
        body.put("email", "testowa@gmail.com");
        body.put("position", "Test_Engineer");
        //POST /users + user as body (see model below)
        Response responsePost = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("http://localhost:8080/users");
        responsePost.prettyPrint();

        //Verify responsePost 200
        Assert.assertEquals(responsePost.getStatusCode(),202,"Status code should be 200 but it is not");
        Integer userId = responsePost.jsonPath().getInt("id");

        Response responseGet = RestAssured
                .given()
                .get("http://localhost:8080/users/"+ userId);
        responseGet.prettyPrint();

        //Verify specific user id
        SoftAssert softAssert = new SoftAssert();
        String actualName = responseGet.jsonPath().getString("name");
        softAssert.assertEquals(actualName,"Karolina222","name in responsePost is not correct");

        String actualSurName = responseGet.jsonPath().getString("surname");
        softAssert.assertEquals(actualSurName,"Testowa","surname in responsePost is not correct");

        String email = responseGet.jsonPath().getString("email");
        softAssert.assertEquals(email,"testowa@gmail.com","email in responsePost is not correct");

        String position = responseGet.jsonPath().getString("position");
        softAssert.assertEquals(position,"Test_Engineer","position in responsePost is not correct");

        softAssert.assertAll();


        Response responseDelete = RestAssured
                .given()
                .delete("http://localhost:8080/users/"+ userId);

        responseDelete.prettyPrint();

        Assert.assertEquals(responseDelete.getStatusCode(),202,"Status code should be 200 but it is not");

        Response responseGetAfterDelete = RestAssured
                .given()
                .get("http://localhost:8080/users/"+ userId);

        Assert.assertEquals(responseGetAfterDelete.getStatusCode(),404,"Status code should be 404 but it is not");

    }
}
