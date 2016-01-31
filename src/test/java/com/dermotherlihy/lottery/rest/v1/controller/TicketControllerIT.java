package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.config.Application;
import com.dermotherlihy.lottery.rest.v1.resource.request.TicketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URI;
import java.net.URISyntaxException;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TicketControllerIT {

    private static final String NON_EXISTENT_ID = "100";
    private static final int TWENTY_ONE = 21;
    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testCreateTicketSucceeds() throws JsonProcessingException {

        TicketRequest ticketRequest = new TicketRequest(4);
        defaultGiven()
                .body(ticketRequest)
                .when()
                .post(TicketsController.URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("create-ticket.json"))
                .body("status", equalTo("NEW"));


    }

    @Test
    public void createCreateTicketFailsDueToToManyLinesRequested() throws JsonProcessingException {

        TicketRequest ticketRequest = new TicketRequest(30);
        defaultGiven()
                .body(ticketRequest)
                .when()
                .post(TicketsController.URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(HttpStatus.BAD_REQUEST.value()))
                .body("message",equalTo("Max Lines Allowed is 27"));

    }

    @Test
    public void getTicketByIdSucceeds() throws JsonProcessingException, URISyntaxException {
        URI url = createTicket();
        defaultGiven()
                .when().get(url)
                .then().body(matchesJsonSchemaInClasspath("create-ticket.json"))
                .body("status", equalTo("NEW"));

    }

    @Test
    public void getTicketByIdFailsDueToNotExistentTicketId() throws JsonProcessingException {
        defaultGiven()
                .when().get(String.format(TicketsController.URL + "/%s", NON_EXISTENT_ID))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("message",equalTo("Please specify a valid ticket id"));

    }

    @Test
    public void getTicketSucceedsLessThat20TicketsInSearch() throws JsonProcessingException, URISyntaxException {
        createTicket();
        defaultGiven()
                .when().get(TicketsController.URL)
                .then().body(matchesJsonSchemaInClasspath("get-tickets-less-than-20-schema.json"));


    }
    @Test
    public void getTicketSucceedsMoreThanTicketsInSearch() throws JsonProcessingException, URISyntaxException {
        createTickets(TWENTY_ONE);
        defaultGiven()
                .when().get(TicketsController.URL)
                .then().body(matchesJsonSchemaInClasspath("get-tickets-more-than-20-schema.json"));


    }

    private void createTickets(int number) throws URISyntaxException {
        for(int i =0 ; i< number; i++){
            createTicket();
        }
    }


    private URI createTicket() throws URISyntaxException {
        TicketRequest ticketRequest = new TicketRequest(4);
        String ticketURL =defaultGiven()
                .body(ticketRequest)
                .when()
                .post(TicketsController.URL)
                .then()
                .extract().path("_links.self.href");
        return new URI(ticketURL);
    }


    private RequestSpecification defaultGiven() {
        return given().header("Accept", "application/json").contentType(ContentType.JSON);
    }
}
