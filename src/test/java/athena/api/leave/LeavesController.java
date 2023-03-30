package athena.api.leave;

import athena.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LeavesController extends BaseClass {
    String baseURL;

    @Test(priority = 1)
    public void createLeavesValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .body(getGeneratedString("\\leave\\"+"create_leave_valid.json"))
                .when()
                .post()
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Leave already taken for this date!"));

    }

    @Test(priority = 2)
    public void updateLeavesValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .body("{\n" +
                        "    \"leaveId\": 44,\n" +
                        "    \"username\": \"ravindu.wijesooriya@acentura.com\",\n" +
                        "    \"date\": \"2021-11-24\",\n" +
                        "    \"leaveType\": 1,\n" +
                        "    \"isHalfDay\": true,\n" +
                        "    \"leaveNote\": \"Leave note update\",\n" +
                        "    \"coWorker\": \"archchana.sivarajah@acentura.com\",\n" +
                        "    \"leaveStatusId\": 2\n" +
                        "}")
                .when()
                .put()
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Leave is already approved or rejected!"));

    }

    @Test(priority = 3)
    public void getNonWorkingDayValidTest() throws IOException{
        int x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("username", "ravindu.wijesooriya@acentura.com")
                        .when()
                        .get()
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Get Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.leaveId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.leaveId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }

    }
}
