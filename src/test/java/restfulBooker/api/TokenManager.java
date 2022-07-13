package restfulBooker.api;

import io.restassured.response.Response;
import restfulBooker.utils.ConfigLoader;

import java.util.HashMap;

import static restfulBooker.api.Route.AUTH;

public class TokenManager {
    public static String createToken() {
        HashMap<String, String> tokenBody = new HashMap<>();
        tokenBody.put("username", ConfigLoader.getInstance().getUsername());
        tokenBody.put("password", ConfigLoader.getInstance().getPassword());

        Response response = RestResource.postForToken(tokenBody, AUTH);

        return response.path("token");
    }
}

