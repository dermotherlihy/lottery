package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.rest.v1.BaseTestController;
import com.dermotherlihy.lottery.rest.v1.resource.request.CheckRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
public class ChecksControllerIT extends BaseTestController {

    private static final long NON_EXISTENT_ID = 10000l;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testCreateCheckSucceeds() throws JsonProcessingException {
        Long ticketId = createTicketAndReturnId();
        CheckRequest checkRequest = getCheckRequest(ticketId);
        defaultGiven()
                .body(checkRequest)
                .when()
                .post(ChecksController.PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("checks/create-check-response.json"))
                .body("ticketId", equalTo(ticketId.intValue()));
    }


    @Test
    public void testGetCheckSucceeds() throws JsonProcessingException {
        Long ticketId = createTicketAndReturnId();
        CheckRequest checkRequest = getCheckRequest(ticketId);
        String checkUrl = createCheck(checkRequest).extract().path("_links.self.href");;
        defaultGiven()
                .when()
                .get(checkUrl)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("checks/create-check-response.json"))
                .body("ticketId", equalTo(ticketId.intValue()));
    }
    @Test
    public void testGetCheckFailsWhenCalledWithNonExistentId() throws JsonProcessingException {
        defaultGiven()
                .when()
                .get(ChecksController.PATH+"/"+NON_EXISTENT_ID)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("message",equalTo("Please specify a valid check id"));
    }

}