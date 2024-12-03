package rest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewUserTest {
    @Test
    public void postNewUser() {
        Response postResponse = UsersService.createUser();
        //Verify responsePost 200
        Assert.assertEquals(postResponse.getStatusCode(),202,"Status code should be 200 but it is not");

        Integer userId = postResponse.jsonPath().getInt("id");

        Response getResponse = UsersService.getUser(userId);

        //Verify specific user id
        SoftAssert softAssert = new SoftAssert();
        String actualName = getResponse.jsonPath().getString("name");
        softAssert.assertEquals(actualName,"Karolina222","name in responsePost is not correct");

        String actualSurName = getResponse.jsonPath().getString("surname");
        softAssert.assertEquals(actualSurName,"Testowa","surname in responsePost is not correct");

        String email = getResponse.jsonPath().getString("email");
        softAssert.assertEquals(email,"testowa@gmail.com","email in responsePost is not correct");

        String position = getResponse.jsonPath().getString("position");
        softAssert.assertEquals(position,"Test_Engineer","position in responsePost is not correct");

        softAssert.assertAll();


    }

}
