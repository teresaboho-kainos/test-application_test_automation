package rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetOneUserId {

@Test
public void getOneUserIdWithoutFilterTest(){
    //Get response  with specific user id
    //GET /users/{userId}


    Response response = RestAssured.get("http://localhost:8080/users/5");
    response.prettyPrint();

    //Verify response 200
    Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200 but it is not");


    //Verify specific user id
    SoftAssert softAssert = new SoftAssert();
    String actualName = response.jsonPath().getString("name");
    softAssert.assertEquals(actualName,"Teresa Malgorzata","name in response is not correct");

    String actualSurName = response.jsonPath().getString("surname");
    softAssert.assertEquals(actualSurName,"Boho","surname in response is not correct");

    String email = response.jsonPath().getString("email");
    softAssert.assertEquals(email,"teresaboho@gmail.com","email in response is not correct");

    String position = response.jsonPath().getString("position");
    softAssert.assertEquals(position,"Test Engineer","position in response is not correct");

    softAssert.assertAll();

    //"name": "name",
    //         "surname": "surname",
    //         "email": "email",
    //         "position": "postion"
    // *
    // *"id": 5,
    //        "email": "teresaboho@gmail.com",
    //        "name": "Teresa Malgorzata",
    //        "surname": "Boho",
    //        "position": "Test Engineer"
    //    }


}
}
