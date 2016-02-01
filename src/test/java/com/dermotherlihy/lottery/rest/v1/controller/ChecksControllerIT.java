package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.rest.v1.BaseTestController;
import com.dermotherlihy.lottery.rest.v1.resource.request.CheckRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

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
                .put(ChecksController.PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("checks/create-check-response.json"))
                .body("checkId", notNullValue())
                .body("ticketId", equalTo(ticketId.intValue()))
                .body("outcomes.size()", equalTo(NUMBER_OF_LINES))
                .body("outcomes[0].line.numbers", notNullValue())
                .body("outcomes[0].result", notNullValue());

    }

    @Test
    public void testCreateCheckIsIdempotent() throws JsonProcessingException {
        long ticketId = createTicketAndReturnId();
        CheckRequest checkRequest = getCheckRequest(ticketId);

        Long firstCheckId = extractId(extractSelfLinkFromResponse(createCheck(checkRequest)));
        Long secondCheckId = extractId(extractSelfLinkFromResponse(createCheck(checkRequest)));

        Assert.assertThat(firstCheckId, is(secondCheckId));

    }

    @Test
    public void testGetCheckByIdSucceeds() throws JsonProcessingException {
        Long ticketId = createTicketAndReturnId();
        CheckRequest checkRequest = getCheckRequest(ticketId);
        String checkUrl = createCheck(checkRequest).extract().path("_links.self.href");;
        defaultGiven()
                .when()
                .get(checkUrl)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("checks/create-check-response.json"))
                .body("ticketId", equalTo(ticketId.intValue()))
                .body("outcomes.size()", equalTo(NUMBER_OF_LINES))
                .body("outcomes[0].line.numbers",notNullValue())
                .body("outcomes[0].result", notNullValue());
    }
    @Test
    public void testGetCheckByIdFailsWhenCalledWithNonExistentId() throws JsonProcessingException {
        defaultGiven()
                .when()
                .get(ChecksController.PATH+"/"+NON_EXISTENT_ID)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("message",equalTo("Please specify a valid check id"));
    }

}
