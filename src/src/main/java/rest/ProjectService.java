package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Project;
import org.json.JSONObject;

public class ProjectService {

    static Response createProject(Project project) {
        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", project.getName());
        body.put("sector", project.getSector());
        body.put("technology", project.getTechnology());
        body.put("startDate", project.getStartDate());
        body.put("owner", project.getOwner());
        //POST /Project
        Response postResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("http://localhost:8080/projects ");
        postResponse.prettyPrint();
        return postResponse;
    }

    static Response getProject(Integer projectId) {
        Response getResponse = RestAssured
                .given()
                .get("http://localhost:8080/projects/"+ projectId);
        getResponse.prettyPrint();
        return getResponse;
    }
}
