package athena.api.attendance;

import athena.BaseClass;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AttendanceController extends BaseClass {
    String baseURL;

    @Test(priority = 1)
    public void createAttendanceValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .body(getGeneratedString("\\attendance\\"+"create_attendance_valid.json"))
                .when()
                .post()
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Successfully marked your attendance"));

    }

    @Test(priority = 2)
    public void getAttendanceValidTest() throws IOException{
        int x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("username", "himesha.sakalasooriya@acentura.com")
                        .header("endDate", 2021-06-20)
                        .header("startDate", 2021-06-18)
                        .when()
                        .get()
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Get Data List: " + jsonStr);

        int size = response.jsonPath().getList("attendanceInfoId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("attendanceInfoId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }

    }

}
