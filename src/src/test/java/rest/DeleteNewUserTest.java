package rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteNewUserTest {
    @Test
    public void deletedUserNotFound() {

        User newUser = new User("Karolina222","Testowa","testowa@gmail.com","Test_Engineer");


        Response postResponse = UsersService.createUser(newUser);


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
