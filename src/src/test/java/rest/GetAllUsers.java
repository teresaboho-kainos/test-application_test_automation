package rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllUsers {

@Test
public void getAllUsersWithoutFilterTest(){
    //Get response  with user ids
    Response response = RestAssured.get("http://localhost:8080/users");
    response.prettyPrint();

    //Verify response 200
    Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200 but it is not");

    //Verify at least 1 user id in response
    List<Integer>userIds = response.jsonPath().getList("id");
    Assert.assertTrue(!userIds.isEmpty(),"List of userIds is empty, but it shouldn't be");
}
}
