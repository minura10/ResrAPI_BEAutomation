package athena.api.nonworkingday;

import athena.BaseClass;
import athena.ExcelDataReader;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NonWorkingDayController extends BaseClass {
    String baseURL;

    @Test(priority = 1)
    public void createNonWorkingDayValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .body(getGeneratedString("\\nonworkingday\\"+"create_nonworkingDay_valid.json"))
                .when()
                .post()
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Date already exists as non-working day!"));

    }

    @Test(priority = 2)
    public void updateNonWorkingDayValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .body("{\n" +
                        "    \"nonWorkingDayId\": 8,\n" +
                        "    \"date\": \"2022-01-28\",\n" +
                        "    \"isHalfDay\": false,\n" +
                        "    \"holidayDesc\": \"PoyaDay_Update\",\n" +
                        "    \"isEnabled\": true\n" +
                        "}")
                .when()
                .put()
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Non working day updated successfully!"));

    }

    @Test(priority = 3)
    public void getNonWorkingDayValidTest() throws IOException{
        int x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("year", 2025)
                .when()
                .get()
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Get Data List: " + jsonStr);

        int size = response.jsonPath().getList("nonWorkingDayId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("nonWorkingDayId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }

    }

    // Invalid scenarios for createNonWorkingDay
    @DataProvider(name = "createNonWorkingDayInvalidTest")
    public Iterator<Object[]> getNonWorkingDaysCreateTestData(ITestContext context) throws IOException {
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(0,"\\NonWorkingDaysInvalid.xlsx");
        return iterator;
    }
    @Test(dataProvider = "createNonWorkingDayInvalidTest")
    public void createNonWorkingDayInvalidTest(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .body(schema)
                .when()
                .post()
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("message", equalTo(message));

    }

    // Invalid scenarios for UpdateNonWorkingDay
    @DataProvider(name = "UpdateNonWorkingDayInvalidTest")
    public Iterator<Object[]> getNonWorkingDaysUpdateTestData(ITestContext context) throws IOException {
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(1,"\\NonWorkingDaysInvalid.xlsx");
        return iterator;
    }
    @Test(dataProvider = "UpdateNonWorkingDayInvalidTest")
    public void updateNonWorkingDayInvalidTest(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .body(schema)
                .when()
                .put()
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("message", equalTo(message));

    }
}
