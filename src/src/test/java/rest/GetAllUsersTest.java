package rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllUsersTest {

    @Test
    public void getAllUsersWithoutFilterTest() {
        //Get response  with user ids
        Response getResponse = RestAssured.get("http://localhost:8080/users");
        getResponse.prettyPrint();

        //Verify response 200
        Assert.assertEquals(getResponse.getStatusCode(), 200, "Status code should be 200 but it is not");

        //Verify at least 1 user id in response
        List<Integer> userIds = getResponse.jsonPath().getList("id");
        Assert.assertFalse(userIds.isEmpty(), "List of userIds is empty, but it shouldn't be");
    }
}
