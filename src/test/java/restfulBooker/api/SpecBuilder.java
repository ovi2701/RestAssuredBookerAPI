package restfulBooker.api;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
               // setBaseUri("https://restful-booker.herokuapp.com").
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                setAccept("application/json").
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec()
    {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();

    }
}
