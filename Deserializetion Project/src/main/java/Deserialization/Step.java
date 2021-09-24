
package Deserialization;
import Deserialization.ParamsRespons;
import Deserialization.RejectRespons;
import Deserialization.SuccessRespons;
import Variables.Variables;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Step {
    Variables v = new Variables();

    public Step callAPI() {
        RestAssured.baseURI = "https://reqres.in/api";
        return this;
    }

    public Step SuccsesfulParams() {
        JSONObject request = new JSONObject();
        request.put(v.email, v.emailvalue);
        request.put(v.password, v.passwordvalue);

        Response response = given().
                header("Content-Type", "application/json").
                body(request).
                post("/register");

        Assert.assertEquals(200, response.statusCode());
        SuccessRespons successRespons = response.as(SuccessRespons.class);
        System.out.println(successRespons.id);
        System.out.println(successRespons.token);
        return this;
    }

    public Step badRequest() {
        JSONObject requeParams = new JSONObject();
        requeParams.put(v.email, v.emailvaluebad);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(requeParams)
                .post("/register");
        Assert.assertEquals(400, response.statusCode());
        RejectRespons failureResponse = response.as(RejectRespons.class);
        System.out.println(failureResponse.error);
        return this;
    }
    public Step lastcase() {
        JSONObject request = new JSONObject();
        request.put(v.name, v.morpheus);
        request.put(v.job, v.leader);
        Response response = given().
                header("Content-Type", "application/json").
                body(request).
                post("https://reqres.in/api/users ");
        Assert.assertEquals(201, response.statusCode());
        ParamsRespons tet = response.as(ParamsRespons.class);
        System.out.println("result:  " + tet.createdAt);
        System.out.println("result:  " + tet.id);
        System.out.println("result:  " + tet.job);
        System.out.println("result:  " + tet.name);
        return this;


    }

}
