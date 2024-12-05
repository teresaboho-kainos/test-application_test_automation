package rest;

import io.restassured.response.Response;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetOneUserIdTest {
    int userId;


    @BeforeTest
    public void addUser() {
        User newUser = new User("Karolina222","Testowa","testowa@gmail.com","Test_Engineer");

        Response postResponse = UsersService.createUser(newUser);


        userId = postResponse.jsonPath().getInt("id");
    }

    @Test
    public void getOneUserIdWithoutFilterTest() {
        //Get response  with specific user id
        //GET /users/{userId}


        Response getResponse = UsersService.getUser(userId);

        Assert.assertEquals(getResponse.getStatusCode(), 200, "Status code should be 200 but it is not");

        SoftAssert softAssert = new SoftAssert();
        String actualName = getResponse.jsonPath().getString("name");
        softAssert.assertEquals(actualName, "Teresa Malgorzata", "name in response is not correct");

        String actualSurName = getResponse.jsonPath().getString("surname");
        softAssert.assertEquals(actualSurName, "Boho", "surname in response is not correct");

        String email = getResponse.jsonPath().getString("email");
        softAssert.assertEquals(email, "teresaboho@gmail.com", "email in response is not correct");

        String position = getResponse.jsonPath().getString("position");
        softAssert.assertEquals(position, "Test Engineer", "position in response is not correct");

        softAssert.assertAll();

    }
}
