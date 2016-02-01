package com.dermotherlihy.lottery.rest.v1.controller;

import com.dermotherlihy.lottery.rest.v1.BaseTestController;
import com.dermotherlihy.lottery.rest.v1.resource.request.LinesRequest;
import com.dermotherlihy.lottery.rest.v1.resource.request.TicketRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by dermot.herlihy on 30/01/2016.
 */
public class TicketsControllerIT extends BaseTestController {

    private static final String NON_EXISTENT_ID = "100";
    private static final int TWENTY_ONE = 21;
    private static final String MAX_LINES_ALLOWED_ERROR = "Max Lines Allowed is 27";
    private static final String NEW_TICKET_REQUIRED = "New Ticket Required";
    private static final int TOO_MANY_LINES = 30;

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
                .post(TicketsController.PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("tickets/create-ticket.json"))
                .body("status", equalTo("NEW"))
                .body("ticketId", notNullValue())
                .body("lines.size()", equalTo(NUMBER_OF_LINES))
                .body("lines[0].numbers[0]", notNullValue());
    }

    @Test
    public void testCreateTicketFailsDueToToManyLinesRequested() throws JsonProcessingException {

        TicketRequest ticketRequest = new TicketRequest(30);
        defaultGiven()
                .body(ticketRequest)
                .when()
                .post(TicketsController.PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(HttpStatus.BAD_REQUEST.value()))
                .body("message",equalTo("Max Lines Allowed is 27"));

    }

    @Test
    public void testGetTicketByIdSucceeds() throws JsonProcessingException {
        String url = extractURLFromTicket(createTicket());
        defaultGiven()
                .when().get(url)
                .then().body(matchesJsonSchemaInClasspath("tickets/create-ticket.json"))
                .body("status", equalTo("NEW"))
                .body("ticketId", notNullValue())
                .body("lines.size()", equalTo(NUMBER_OF_LINES))
                .body("lines[0].numbers[0]", notNullValue());

    }

    @Test
    public void testGetTicketByIdFailsDueToNotExistentTicketId() throws JsonProcessingException {
        defaultGiven()
                .when().get(String.format(TicketsController.PATH + "/%s", NON_EXISTENT_ID))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(HttpStatus.NOT_FOUND.value()))
                .body("message",equalTo("Please specify a valid ticket id"));

    }

    @Test
    public void testGetTicketSucceedsLessThat20TicketsInSearch() throws JsonProcessingException {
        createTicket();
        defaultGiven()
                .when().get(TicketsController.PATH)
                .then().body(matchesJsonSchemaInClasspath("tickets/get-tickets-less-than-20-schema.json"))
                .body("_links.self", notNullValue())
                .body("_links.first", nullValue())
                .body("_links.next", nullValue())
                .body("_links.last", nullValue());;


    }
    @Test
    public void testGetTicketSucceedsMoreThan20TicketsInSearch() throws JsonProcessingException {
        createTickets(TWENTY_ONE);
        defaultGiven()
                .when().get(TicketsController.PATH)
                .then()
                .body(matchesJsonSchemaInClasspath("tickets/get-tickets-more-than-20-schema.json"))
                .body("_links.self", notNullValue())
                .body("_links.first", notNullValue())
                .body("_links.next", notNullValue())
                .body("_links.last", notNullValue());


    }

    @Test
    public void testAddLinesToTicketSucceeds() throws JsonProcessingException {
        String url = extractURLFromTicket(createTicket())+"/lines";
        LinesRequest linesRequest = new LinesRequest();
        linesRequest.setNumberOfLines(NUMBER_OF_LINES);
        defaultGiven().body(linesRequest)
                .when().post(url)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("tickets/create-ticket.json"))
                .body("status", equalTo("NEW"))
                .body("ticketId", notNullValue())
                .body("lines.size()", equalTo(2*NUMBER_OF_LINES))
                .body("lines[0].numbers[0]", notNullValue());
    }


    @Test
    public void testAddLinesToTicketFailsIfTryingToAddTooManyLines() throws JsonProcessingException {
        String url = extractURLFromTicket(createTicket())+"/lines";
        LinesRequest linesRequest = new LinesRequest();
        linesRequest.setNumberOfLines(TOO_MANY_LINES);
        defaultGiven().body(linesRequest)
                .when().post(url)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(HttpStatus.BAD_REQUEST.value()))
                .body("message",equalTo(MAX_LINES_ALLOWED_ERROR));

    }
    @Test
    public void testAddLinesToTicketFailsWhenTicketChecked() throws JsonProcessingException {
        String ticketUrl = createCheckedTicket();
        String url = ticketUrl+"/lines";
        LinesRequest linesRequest = new LinesRequest();
        linesRequest.setNumberOfLines(1);
        defaultGiven().body(linesRequest)
                .when().post(url)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(HttpStatus.BAD_REQUEST.value()))
                .body("message",equalTo(NEW_TICKET_REQUIRED));

    }


}
