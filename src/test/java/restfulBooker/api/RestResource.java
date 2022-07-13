package restfulBooker.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static restfulBooker.api.SpecBuilder.*;
import static restfulBooker.api.TokenManager.createToken;

public class RestResource {

    public static Response post(Object request, String path)
    {
        return given(getRequestSpec()).
                body(request).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();

    }

    public static Response postForToken(Object request, String path)
    {
        return given(getRequestSpec()).
                body(request).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();

    }

    public static Response get(String path)
    {
        return  given(getRequestSpec()).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response put(Object object, String path){
        return given(getRequestSpec()).
                body(object).
                cookie("token", createToken()).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response patch(Object object, String path)
    {
        return given(getRequestSpec()).
                body(object).
                cookie("token", createToken()).
                when().
                patch(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String path)
    {
        return given(getRequestSpec()).
                cookie("token", createToken()).
                when().
                delete(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }
}
