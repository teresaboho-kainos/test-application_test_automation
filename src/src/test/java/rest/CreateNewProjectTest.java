package rest;

import io.restassured.response.Response;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewProjectTest {
    @Test
    public void postNewProject() {
        /*
        "name": "API project",
             "sector": "private",
             "technology": ["Java"],
             "startDate": "2023-02-01",
             "owner": 1

             Create project: POST /projects + project as body (see model below)
        *
        * */

        String [] technologies = {"java"};

        Project newProject = new Project("API project","private", technologies,
                "05/12/2024",8);


        Response postResponse = ProjectService.createProject(newProject);
        //Verify responsePost 200

        Assert.assertEquals(postResponse.getStatusCode(),201,"Status code should be 200 but it is not");


        Integer projectId = postResponse.jsonPath().getInt("id");
        Response getResponse = ProjectService.getProject(projectId);

        //Verify specific user id
        SoftAssert softAssert = new SoftAssert();
        String actualName = getResponse.jsonPath().getString("name");
        softAssert.assertEquals(actualName,"API project","name in responsePost is not correct");

        String actualSector = getResponse.jsonPath().getString("sector");
        softAssert.assertEquals(actualSector,"private","sector in responsePost is not correct");

        String technology = getResponse.jsonPath().<String>getList("technology").get(0);
        softAssert.assertEquals(technology,"java","technology in responsePost is not correct");

        String startDate = getResponse.jsonPath().getString("startDate");
        softAssert.assertEquals(startDate,"05/12/2024"," startDate in responsePost is not correct");

        String ownerId = getResponse.jsonPath().getString("owner.id");
        softAssert.assertEquals(ownerId,"8"," owner in responsePost is not correct");

        softAssert.assertAll();


    }

}
