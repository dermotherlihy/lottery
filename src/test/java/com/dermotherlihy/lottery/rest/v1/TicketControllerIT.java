package com.dermotherlihy.lottery.rest.v1;

import com.dermotherlihy.lottery.Application;
import com.dermotherlihy.lottery.rest.v1.resource.request.TicketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TicketControllerIT {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void createCreateTicketSucceeds() throws JsonProcessingException {

        TicketRequest ticketRequest = new TicketRequest(4);
        given().header("Accept", "application/json")
                .body(ticketRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(TicketsController.URL)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
