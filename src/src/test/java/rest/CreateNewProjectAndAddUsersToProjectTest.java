package rest;

import io.restassured.response.Response;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewProjectAndAddUsersToProjectTest {

    private static void validateProjectData(Response getResponse) {
        SoftAssert softAssert = new SoftAssert();
        String actualName = getResponse.jsonPath().getString("name");
        softAssert.assertEquals(actualName, "FebruaryProject", "name in responsePost is not correct");

        String actualSector = getResponse.jsonPath().getString("sector");
        softAssert.assertEquals(actualSector, "private", "sector in responsePost is not correct");

        String technology = getResponse.jsonPath().<String>getList("technology").get(0);
        softAssert.assertEquals(technology, "java", "technology in responsePost is not correct");

        String startDate = getResponse.jsonPath().getString("startDate");
        softAssert.assertEquals(startDate, "27/02/2025", " startDate in responsePost is not correct");

        String ownerId = getResponse.jsonPath().getString("owner.id");
        softAssert.assertEquals(ownerId, "8", " owner in responsePost is not correct");

        softAssert.assertAll();
    }

    @Test
    public void postNewProject() {

        String[] technologies = {"java", "python"};

        Project newProject = new Project("FebruaryProject", "private", technologies,
                "27/02/2025", 8);


        Response postResponse = ProjectService.createProject(newProject);
        Assert.assertEquals(postResponse.getStatusCode(), 201, "Status code should be 200 but it is not");
        Integer projectId = postResponse.jsonPath().getInt("id");

        Response getResponse = ProjectService.getProject(projectId);

        //Verify specific user id
        validateProjectData(getResponse);


        String[] userIds = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Response response = ProjectService.addUsersToProject(projectId, userIds);
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 200 but it is not");
    }
}
