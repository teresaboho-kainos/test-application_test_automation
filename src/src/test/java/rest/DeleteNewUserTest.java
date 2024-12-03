package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteNewUserTest {
    @Test
    public void deletedUserNotFound() {

        Response postResponse = UsersService.createUser();

        //Verify responsePost 200
        Assert.assertEquals(postResponse.getStatusCode(),202,"Status code should be 200 but it is not");

        Integer userId = postResponse.jsonPath().getInt("id");

        Response deleteResponse = RestAssured
                .given()
                .delete("http://localhost:8080/users/"+ userId);

        deleteResponse.prettyPrint();

        Assert.assertEquals(deleteResponse.getStatusCode(),202,"Status code should be 200 but it is not");

        Response getResponseAfterDelete = UsersService.getUser(userId);

        Assert.assertEquals(getResponseAfterDelete.getStatusCode(),404,"Status code should be 404 but it is not");

    }
}
