package com.dermotherlihy.lottery.rest.v1;

import com.dermotherlihy.lottery.config.Application;
import com.dermotherlihy.lottery.rest.v1.controller.ChecksController;
import com.dermotherlihy.lottery.rest.v1.controller.TicketsController;
import com.dermotherlihy.lottery.rest.v1.resource.request.CheckRequest;
import com.dermotherlihy.lottery.rest.v1.resource.request.TicketRequest;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by dermot.herlihy on 31/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class BaseTestController {

    public  static final int NUMBER_OF_LINES = 4;

    @Value("${local.server.port}")
    protected int serverPort;

    protected String createCheckedTicket() {
        String url = extractSelfLinkFromResponse(createTicket());
        CheckRequest checkRequest = getCheckRequest(extractId(url));
        createCheck(checkRequest);
        return url;
    }

    protected ValidatableResponse createCheck(CheckRequest checkRequest) {
        return defaultGiven().body(checkRequest)
                .when()
                .put(ChecksController.PATH)
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    protected Long extractId(String uri) {
        String urlStr = uri.toString();
        return Long.valueOf(urlStr.substring(urlStr.lastIndexOf('/') +1));
    }

    protected void createTickets(int number) {
        for(int i =0 ; i< number; i++){
            createTicketAndReturnId();
        }
    }

    protected long createTicketAndReturnId(){
        TicketRequest ticketRequest = new TicketRequest(NUMBER_OF_LINES);
        String ticketURL = extractSelfLinkFromResponse(createTicket(ticketRequest));
        return extractId(ticketURL);
    }

    private ValidatableResponse createTicket(TicketRequest ticketRequest) {
        return defaultGiven()
                .body(ticketRequest)
                .when()
                .post(TicketsController.PATH)
                .then();
    }

    protected ValidatableResponse createTicket(){
        TicketRequest ticketRequest = new TicketRequest(4);
        return createTicket(ticketRequest);
    }

    protected CheckRequest getCheckRequest(Long ticketId) {
        CheckRequest checkRequest = new CheckRequest(ticketId);
        return checkRequest;
    }

    protected String extractSelfLinkFromResponse(ValidatableResponse response) {
        return response.extract().path("_links.self.href");
    }


    protected RequestSpecification defaultGiven() {
        return given().header("Accept", "application/json").contentType(ContentType.JSON);
    }
}
