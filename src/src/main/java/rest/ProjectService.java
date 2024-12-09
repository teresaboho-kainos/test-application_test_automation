package rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Project;
import org.json.JSONArray;
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


    static Response addUsersToProject(Integer projectId, String [] userIds) {
        // Create JSON body
        //Add user to project: POST /projects/users/{projectId} + array of user ids as body
       //
        //POST /Project
        JSONArray body = new JSONArray();
        body.put(userIds);

        System.out.println(userIds);
        Response postResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("http://localhost:8080/projects/users/"+projectId);
        postResponse.prettyPrint();
        return postResponse;
    }
}
